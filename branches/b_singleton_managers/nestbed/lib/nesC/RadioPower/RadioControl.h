/* $Id$ */
#ifndef __RADIO_CONTROL_H
#define __RADIO_CONTROL_H

enum {
    AM_POWERMESSAGE = 17
};

typedef struct PowerMessage {
    uint8_t powerLevel;
} PowerMessage;

#endif
