/* $Id$ */

includes ReliableComm;

module UniformLossyCommM {
    provides {
        interface StdControl as Control;

        interface SendMsg[uint8_t id];
        interface ReceiveMsg[uint8_t id];
    }

    uses {
        interface SendMsg      as RealSendMsg;
        interface ReceiveMsg   as RealReceiveMsg;
        interface PseudoRandom as Random;
        interface Leds;

        event result_t sendDone();
    }
}

implementation {
    #include "debug.h"

    #define LOSS_PERCENTAGE 25

    struct UlcMessage {
        uint8_t id;
        char    data[RC_PAYLOAD_SIZE - 1];
    };
    typedef struct UlcMessage UlcMessage;

    task void signalSendDoneFail();

    TOS_MsgPtr msgInRoute;
    uint8_t    msgInRouteId;


    command result_t Control.init() {
        call Leds.init();

        msgInRoute = NULL;

        return SUCCESS;
    }


    command result_t SendMsg.send[uint8_t id](uint16_t   addr, uint8_t length,
                                              TOS_MsgPtr msg) {
        result_t result = SUCCESS;

        if ((call Random.rand() % 100) > LOSS_PERCENTAGE) {
            TOS_Msg     message;
            UlcMessage* ulcMessage = (UlcMessage*) message.data;

            ulcMessage->id = id;
            memcpy(ulcMessage->data, msg->data, length);
            memcpy(msg->data, ulcMessage, length + 1);

            debug("Doing real send...");
            result = call RealSendMsg.send(addr, length + 1, msg);
        } else if (msgInRoute != NULL) {
            debug("FAKE send in progress");
            result = FAIL;
        } else {
            debug("Doing FAKE send...");
            msgInRoute   = msg;
            msgInRouteId = id;
            post signalSendDoneFail();
        }

        return result;
    }


    task void signalSendDoneFail() {
        signal sendDone();
        signal SendMsg.sendDone[msgInRouteId](msgInRoute, FAIL);
        msgInRoute = NULL;
    }


    event result_t RealSendMsg.sendDone(TOS_MsgPtr msg, result_t success) {
        TOS_Msg     message;
        UlcMessage* ulcMessage = (UlcMessage*) msg->data;
        result_t    result     = signal sendDone();
        uint8_t     id         = ulcMessage->id;

        memcpy(message.data, ulcMessage->data, DATA_LENGTH);
        memcpy(msg->data, message.data, DATA_LENGTH);

        return rcombine(result,
                        signal SendMsg.sendDone[id](msg, success));

    }


    event TOS_MsgPtr RealReceiveMsg.receive(TOS_MsgPtr msg) {
        TOS_Msg     message;
        UlcMessage* ulcMessage = (UlcMessage*) msg->data;
        uint8_t     id         = ulcMessage->id;

        memcpy(message.data, ulcMessage->data, DATA_LENGTH);
        memcpy(msg->data, message.data, DATA_LENGTH);

        return signal ReceiveMsg.receive[id](msg);
    }





    command result_t Control.start() { return SUCCESS; }
    command result_t Control.stop()  { return SUCCESS; }

    default event TOS_MsgPtr ReceiveMsg.receive[uint8_t id](TOS_MsgPtr msg) {
        return msg;
    }

    default event result_t SendMsg.sendDone[uint8_t id](TOS_MsgPtr msg,
                                                       result_t    success) {
        return SUCCESS;
    }

    default event result_t sendDone() {
        return SUCCESS;
    }
}
