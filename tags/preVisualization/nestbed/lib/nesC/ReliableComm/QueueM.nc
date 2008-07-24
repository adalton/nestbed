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
// Summary: This file defines an implementation of the 
//          Queue interface.
// ---------------


includes QueueMConstants;
includes QueueEntry;
includes QueueHeapBlock;

module QueueM {
	provides {
		interface Queue;
	}	
}

implementation {
	// the virtual heap used to store queue heap blocks
	QueueHeapBlock heap[MAX_QUEUE_SIZE];
	
	// the head of the free list; the length of the free list
	uint8_t freeHead;
	uint8_t freeLength;

	// the head and tail of the queue list; the length 
	// of the queue list
	uint8_t queueHead;
	uint8_t queueTail;
	uint8_t queueLength;

	// internal heap management functions
	void initHeap();
	uint8_t alloc();
	void dealloc(uint8_t heapIndex);
	
	command void Queue.init() {
		// initialize the heap
		initHeap();

		// initialize the queue
		queueHead = QNULL;
		queueTail = QNULL;
		queueLength = 0;
	}
	
	command void Queue.enqueue(QueueEntry *entry) {
		uint8_t block;
		
		block = alloc();
		memcpy(&(heap[block].entry), entry, sizeof(QueueEntry));
		heap[block].next = QNULL;

		if(queueTail != QNULL) {
			heap[queueTail].next = block;
		} else {
			queueHead = block;
		}
		queueTail = block;
		
		queueLength++;
	}

	command void Queue.dequeue(QueueEntry *entry) {
		uint8_t oldHead;
		
		memcpy(entry, &(heap[queueHead].entry), sizeof(QueueEntry));
		oldHead = queueHead;
		queueHead = heap[queueHead].next;
		dealloc(oldHead);

		if(queueHead == QNULL) {
			queueTail = QNULL;
		}

		queueLength--;
	}
	
	command uint8_t Queue.length() {
		return(queueLength);
	}

	command uint8_t Queue.capacity() {
		return(MAX_QUEUE_SIZE);
	}

	void initHeap() {
		uint8_t heapIndex = 0;
		
		// link the heap blocks (for the free list)
		for(heapIndex = 0; heapIndex < MAX_QUEUE_SIZE; heapIndex++) {
			if(heapIndex != (MAX_QUEUE_SIZE-1)) {
				heap[heapIndex].next = heapIndex+1;
			} else {
				heap[heapIndex].next = QNULL;
			}
		}

		// initialize the free list 
		freeHead = 0;
		freeLength = MAX_QUEUE_SIZE;
	}

	uint8_t alloc() {
		uint8_t block;

		block = freeHead;
		freeHead = heap[freeHead].next;
		freeLength--;
		return(block);
	}

	void dealloc(uint8_t heapIndex) {
		// return the unused block to the free list
		heap[heapIndex].next = freeHead;
		freeHead = heapIndex;
		freeLength++;
	}
}
