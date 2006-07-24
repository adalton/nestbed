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
// Summary: This file defines the configuration constants 
//          used across the ReliableCommM implementation.
// ---------------


#ifndef RELIABLECOMMM_CONSTANTS
#define RELIABLECOMMM_CONSTANTS 1

// the maximum number of send attempts (0<;<256)
enum { MAX_SEND_ATTEMPTS = 5 };

// the maximum payload size (do not change)
//enum { MAX_PAYLOAD_SIZE = 20 };
enum { MAX_PAYLOAD_SIZE = (DATA_LENGTH - 2) };

// the active message id used by ReliableCommM
enum { AM_RELIABLEMESSAGE = 50 };

// the maximum number of nodes
enum { MAX_NODES = 50 };

// the maximum number of *retransmission rounds* 
// before a link is considered (temporarily) 
// dead (0<;<256)
enum { DEAD_LINK_MAX = 2 };

// the probability that a dead link will be 
// retried (and consequently re-evaluated)
// (0<=;<=100)
enum { DEAD_LINK_RETRY_PROB = 10 };

// the retransmit delay incurred on congestion
enum { CONGESTION_DELAY = 16 };
	
#endif
