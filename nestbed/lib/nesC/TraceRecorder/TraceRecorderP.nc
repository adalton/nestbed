/* $Id$ */
/*
 * TraceRecorderP.nc
 *
 * nesC Analysis and Instrumentation Framework
 *
 * Copyright (C) 2007
 * Dependable Systems Research Group
 * School of Computing
 * Clemson University
 * Andrew R. Dalton and Jason O. Hallstrom
 * All rights reserved.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the
 *
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301, USA.
 *
 */
#include "TraceRecorder.h"

module TraceRecorderP {
    provides interface TraceRecorder;
    uses     interface Boot;
    uses     interface LogWrite;
}
implementation {

    enum { ENTER, EXIT, SEND, RECEIVE };

    task void appendToLog();

    TraceLogEntry traceEntries[NUM_BUFFERS];
    const bool    TRACE_ENABLED = FALSE;
    uint16_t      magicValue    = 0;
    bool          booted        = FALSE;
    bool          readyToAppend = FALSE;
    uint8_t       rowIndex      = 0;
    uint16_t      entryIndex    = 0;
    bool          appending     = FALSE;
    uint8_t       appendingRow;


    event void Boot.booted() {
        if (TRACE_ENABLED) {
            bool _booted;

            atomic {
                _booted = booted;
                booted = TRUE;
            }

            if (!_booted) {
                call LogWrite.erase();
            }
        }
    }

    bool update() {
        bool okay = TRUE;

        entryIndex = entryIndex + 1;

        // If we just filled the current log ...
        if (entryIndex >= ENTRIES_PER_LOG) {
            if (readyToAppend && !appending) {
                if (post appendToLog() == SUCCESS) {
                    appending    = TRUE;
                    appendingRow = rowIndex;
                    rowIndex     = (rowIndex + 1) % NUM_BUFFERS;
                } else {
                    // This is bad -- we can't post a task
                    // This can only happen if we're outrunning the
                    // task scheduler
                    okay = FALSE;
                }
            } else {
                // This is bad -- we're logging too fast
                // Either the erase is not finish, or the last append
                // is not finished.
                okay = FALSE;
            }

            // No matter what, the entryIndex has run off the end of the
            // array -- we have to reset it.
            entryIndex = 0;
        }

        return okay;
    }


    task void appendToLog() {
        uint8_t _appendingRow;

        atomic _appendingRow = appendingRow;

        if (call LogWrite.append(&traceEntries[_appendingRow],
                                 sizeof(TraceLogEntry)) != SUCCESS) {
            //
            // This is bad -- we were unable to append to the log
            // for some reason.  We set 'appending' to 'TRUE' when
            // we posted the task.  Because the append was unsuccessful,
            // we set it back to 'FALSE' here.
            //
            atomic appending = FALSE;
        }
    }


    event void LogWrite.appendDone(void* buf,         storage_len_t len,
                                   bool  recordsLost, error_t       error) {
        atomic appending = FALSE;
    }



    async command bool TraceRecorder.enter(uint16_t moduleId, uint16_t functionId) {
        bool okay = FALSE;

        if (TRACE_ENABLED) {
            atomic {
                if (readyToAppend) {
                    traceEntries[rowIndex].entries[entryIndex].type                   = ENTER;
                    traceEntries[rowIndex].entries[entryIndex].u.callTrace.functionId = functionId;
                    traceEntries[rowIndex].entries[entryIndex].u.callTrace.moduleId   = moduleId;
                    okay = update();
                }
            }
        } else {
            okay = TRUE;
        }

        return okay;
    }

    async command bool TraceRecorder.exit(uint16_t moduleId, uint16_t functionId) {
        bool okay = FALSE;

        if (TRACE_ENABLED) {
            atomic {
                if (readyToAppend) {
                    traceEntries[rowIndex].entries[entryIndex].type                   = EXIT;
                    traceEntries[rowIndex].entries[entryIndex].u.callTrace.functionId = functionId;
                    traceEntries[rowIndex].entries[entryIndex].u.callTrace.moduleId   = moduleId;
                    okay = update();
                }
            }
        } else {
            okay = TRUE;
        }

        return okay;
    }


    command bool TraceRecorder.send(am_addr_t  destination,
                                    message_t* message,
                                    uint8_t    length,
                                    void*      payload) {
        bool okay = FALSE;

        if (TRACE_ENABLED) {
            atomic {
                if (readyToAppend) {
                    uint16_t* magicField;

                    // Make sure we're on a word-aligned boundary
                    if ((length % 2) != 0) {
                        length = length + 1;
                    }

                    magicField  = payload + length;
                    *magicField = magicValue++;

                    traceEntries[rowIndex].entries[entryIndex].type                 = SEND;
                    traceEntries[rowIndex].entries[entryIndex].u.radioTrace.address = destination;
                    traceEntries[rowIndex].entries[entryIndex].u.radioTrace.magic   = *magicField;
                    okay = update();
                }
            }
        } else {
            okay = TRUE;
        }

        return okay;
    }

    command bool TraceRecorder.receive(message_t* message, void* payload, uint8_t length, am_addr_t source) {
        bool okay = FALSE;

        if (TRACE_ENABLED) {
            atomic {
                if (readyToAppend) {
                    uint16_t* magicField;

                    if ((length % 2) != 0) {
                        length = length + 1;
                    }

                    magicField = payload + length;

                    traceEntries[rowIndex].entries[entryIndex].type                 = RECEIVE;
                    traceEntries[rowIndex].entries[entryIndex].u.radioTrace.address = source;
                    traceEntries[rowIndex].entries[entryIndex].u.radioTrace.magic   = *magicField;
                    okay = update();
                }
            }
        } else {
            okay = TRUE;
        }

        return okay;
    }


    event void LogWrite.eraseDone(error_t error) {
        if (error == SUCCESS) {
            atomic readyToAppend = TRUE;
        }
    }


    event void LogWrite.syncDone(error_t error)  { }
}
