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
// Summary: This file provides an implementation
//			of the LinkMonitor interface.
// ---------------


includes ReliableCommMConstants;

module LinkMonitorM {
	provides {
		interface LinkMonitor;
	}
}

implementation {
	// health estimates for each outgoing link
	uint8_t linkHealth[MAX_NODES];

	command void LinkMonitor.init() {
		// initialize health estimates (optimistically)
		memset(linkHealth, DEAD_LINK_MAX, sizeof(linkHealth));
	}
	
	command void LinkMonitor.registerTransmitSuccess(uint8_t nodeId) {
		// the link is alive; assume excellent health
		linkHealth[nodeId-1] = DEAD_LINK_MAX;
	}

	command void LinkMonitor.registerTransmitFailure(uint8_t nodeId) {
		// the link failed; reduce link health
		if(linkHealth[nodeId-1] > 0) {
			linkHealth[nodeId-1]--;
		}
	}

	command bool LinkMonitor.isLinkValid(uint8_t nodeId) {
		// the link is valid if it isn't dead
		return(linkHealth[nodeId-1] > 0);
	}
}
