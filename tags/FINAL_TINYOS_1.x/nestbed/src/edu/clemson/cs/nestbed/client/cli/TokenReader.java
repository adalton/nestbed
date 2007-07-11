/* $Id$ */
/*
 * Level.java
 *
 * Network Embedded Sensor Testbed (NESTBed)
 *
 * Copyright (C) 2006-2007
 * Dependable Systems Research Group
 * Department of Computer Science
 * Clemson University
 * Andrew R. Dalton and Jason O. Hallstrom
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
 */
package edu.clemson.cs.nestbed.client.cli;


import java.util.ArrayList;
import java.util.List;


public class TokenReader {
    private enum ReadState { WHITE, BLACK, QUOTE }

    private ReadState state;
    private String    line;
    private int       index = 0;


    public TokenReader(String line) {
        this.line  = line;
        this.state = ReadState.WHITE;
    }


    public String[] readTokens() {
        String[] tokArray = { "exit" };

        if (line != null) {
            List<String> tokens = new ArrayList<String>();

            while (index < line.length()) {
                tokens.add(nextToken());
            }

            if (state == ReadState.QUOTE) {
                System.err.println("Error: Unterminated string constant");
                tokens.clear();
            }

            tokArray =  tokens.toArray(new String[tokens.size()]);
        }

        return tokArray;
    }


    private String nextToken() {
        StringBuffer buffer = new StringBuffer();
        boolean      done   = false;

        while (index < line.length() && !done) {
            switch(state) {
            case WHITE:
                if (Character.isWhitespace(line.charAt(index))) {
                    index++;
                } else if (line.charAt(index) == '"') {
                    state = ReadState.QUOTE;
                    index++;
                } else {
                    state = ReadState.BLACK;
                }
                break;

            case BLACK:
                if (Character.isWhitespace(line.charAt(index))) {
                    state = ReadState.WHITE;
                    done  = true;
                } else if (line.charAt(index) == '"') {
                    state = ReadState.QUOTE;
                    index++;
                } else {
                    buffer.append(line.charAt(index));
                    index++;
                }
                break;

            case QUOTE:
                if (line.charAt(index) != '"') {
                    buffer.append(line.charAt(index));
                } else {
                    state = ReadState.WHITE;
                    done  = true;
                }
                index++;
                break;
            }
        }

        return buffer.toString();
    }
}
