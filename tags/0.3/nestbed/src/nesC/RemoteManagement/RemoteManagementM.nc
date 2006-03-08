includes DelugeMsgs;

module RemoteManagementM {
    provides {
        interface StdControl;
    }

    uses {
        interface NetProg;
        interface Leds;
        interface Random;
        interface SendMsg;
        interface ReceiveMsg;
    }
}


implementation {

    TOS_Msg  sendBuffer;
    TOS_Msg  ackBuffer;
    bool     sendInProgress;
    bool     ackInProgress;

    int8_t   parent;
    bool     iAmRebooting;
    uint8_t  bootImgNum;
    uint16_t sequenceNum;


    command result_t StdControl.init() {
        call Leds.init(); 
        call Random.init();

        sendInProgress = FALSE;
        ackInProgress  = FALSE;
        iAmRebooting   = FALSE;
        parent         = -1;

        return SUCCESS;
    }

    command result_t StdControl.start() {
        return SUCCESS;
    }

    command result_t StdControl.stop() {
        return SUCCESS;
    }


    event result_t SendMsg.sendDone(TOS_MsgPtr msg, result_t success) {

        if (msg == &sendBuffer) {
            sendInProgress = FALSE;
            call Leds.greenToggle();
        } else if (msg == &ackBuffer) {
            if (iAmRebooting) {
                call NetProg.programImgAndReboot(bootImgNum);
            }
            ackInProgress  = FALSE;
            call Leds.yellowToggle();
        } else {
            // Bad mojo
            call Leds.set(0xFF);
        }

        return SUCCESS;
    }


    event TOS_MsgPtr ReceiveMsg.receive(TOS_MsgPtr msg) {
        SelectiveRebootMsg* srMsg = (SelectiveRebootMsg*) msg->data;

        if (srMsg->sequenceNum <= sequenceNum) {
            return msg;
        }
        sequenceNum = srMsg->sequenceNum;

        switch (srMsg->type) {
        case SEND:
        case SEND_FROM_PC: {
            if (srMsg->dest == TOS_LOCAL_ADDRESS && !ackInProgress) {
                // I'm the one rebooting
                uint16_t            address;
                SelectiveRebootMsg* ackMsg =
                                        (SelectiveRebootMsg*) ackBuffer.data;

                parent  = srMsg->source;
                address = (TOS_LOCAL_ADDRESS != 0)
                                ? TOS_BCAST_ADDR
                                : TOS_UART_ADDR;

                memcpy(ackMsg, srMsg, sizeof(SelectiveRebootMsg));

                ackMsg->source      = TOS_LOCAL_ADDRESS;
                ackMsg->dest        = parent;
                ackMsg->type        = ACK;
                ackMsg->sequenceNum = sequenceNum + 1;

                bootImgNum           = ackMsg->imageNumber;
                iAmRebooting         = TRUE;


                ackInProgress = TRUE;
                call Leds.yellowToggle();

                call SendMsg.send(address, sizeof(SelectiveRebootMsg),
                                  &ackBuffer);

            } else if (!sendInProgress) { // Otherwise, relay this message
                SelectiveRebootMsg* outMsg =
                                        (SelectiveRebootMsg*) sendBuffer.data;

                parent = srMsg->source;

                memcpy(outMsg, srMsg, sizeof(SelectiveRebootMsg));

                outMsg->source = TOS_LOCAL_ADDRESS;
                outMsg->type   = SEND;

                sendInProgress = TRUE;
                call Leds.greenToggle();

                call SendMsg.send(TOS_BCAST_ADDR, sizeof(SelectiveRebootMsg),
                                  &sendBuffer);
            }
            break;
        }



        case ACK: {
            uint16_t  address;

            if (ackInProgress) { // If I'm already ACKing
                // Do nothing
            } else if (srMsg->dest == TOS_LOCAL_ADDRESS) { // Relay this message

                SelectiveRebootMsg* ackMsg =
                                        (SelectiveRebootMsg*) ackBuffer.data;

                memcpy(ackMsg, srMsg, sizeof(SelectiveRebootMsg));

                ackMsg->dest  = parent;
                ackInProgress = TRUE;
                call Leds.yellowToggle();

                address = (TOS_LOCAL_ADDRESS != 0)
                                ? TOS_BCAST_ADDR
                                : TOS_UART_ADDR;

                call SendMsg.send(address, sizeof(SelectiveRebootMsg),
                                  &ackBuffer);
            } else {
                // Ignore
            }
            break;
        }



        default:
            // bad mojo
            break;
        }

        return msg;
    }
}
