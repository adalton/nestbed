// Copyright (C) 2006 Clemson University 
// Jason O. Hallstrom, Andrew R. Dalton
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; either version 2
// of the License, or (at your option) any later version.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
//
// ---------------
// Summary: This file defines the message (wrapping) 
//			structure used by ReliableCommM.
// ---------------


#ifndef RELIABLEMESSAGE
#define RELIABLEMESSAGE 1

#include "ReliableCommMConstants.h"

typedef struct ReliableMessage {
	// the wrapped payload
	uint8_t data[MAX_PAYLOAD_SIZE];
	
	// the wrapped AM id
	uint8_t type;

	// the wrapped payload length
	uint8_t length;
} ReliableMessage;

#endif
