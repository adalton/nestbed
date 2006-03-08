#ifndef __REMOTE_MANAGEMENT_H
#define __REMOTE_MANAGEMENT_H

typedef struct Selective_Reboot_Msg {
    uint8_t source;
    uint8_t dest;
    uint8_t imageNumber;
    uint8_t sequenceNum;
    uint8_t type;
} SelectiveRebootMsg;

enum {
    AM_SELECTIVE_REBOOT_MSG = 27,
    AM_RADIO_ADJUSTMENT_MSG
};

enum {
    SEND_FROM_PC,
    SEND,
    ACK
};

#endif
