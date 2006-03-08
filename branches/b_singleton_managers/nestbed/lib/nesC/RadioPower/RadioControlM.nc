/* $Id$ */
module RadioControlM {
    provides interface StdControl;
    uses     interface Leds;
    uses     interface ReceiveMsg    as ReceivePower;
    uses     interface CC2420Control as Radio;
}

implementation {
    command result_t StdControl.init() {
        call Leds.init();
        return SUCCESS;
    }

    command result_t StdControl.start() {
        return SUCCESS;
    }

    command result_t StdControl.stop() {
        return SUCCESS;
    }

    event TOS_MsgPtr ReceivePower.receive(TOS_MsgPtr msgPtr) {
        PowerMessage* powerMessage = (PowerMessage*) msgPtr->data;
        call Radio.SetRFPower(powerMessage->powerLevel);
        call Leds.set(powerMessage->powerLevel);
        return msgPtr;
    }
}
