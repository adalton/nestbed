/* $Id$ */
#ifndef __DEBUG_H
#define __DEBUG_H

#define DEBUG

#undef debug

#ifdef DEBUG
#    define debug(format, arg...) do { dbg(DBG_USR1, "%s:%d\n       " format "\n" , __FUNCTION__, __LINE__, ## arg); } while(0)
#else
#    define debug(format, arg...) do { } while(0)
#endif


#endif
