/* $Id$ */

includes UniformLossyComm;

configuration UniformLossyCommC {
    provides {
        interface StdControl as Control;

        // The interfaces are parameterized by the active message id
        interface SendMsg[uint8_t id];
        interface ReceiveMsg[uint8_t id];

        // How many packets were received in the past second
        command uint16_t activity();
    }

    uses {
        // Signaled after every send completion for components which wish
        // to retry failed sends
        event result_t sendDone();
    }
}


implementation {
    components Main;
    components LedsC             as LedsImpl;
    components NoLeds            as LedsImpl_;
    components UniformLossyCommM as Module;
    components ReliableCommC;
    components PseudoRandomM;

    Control                         =  Module.Control;
    SendMsg                         =  Module.SendMsg;
    ReceiveMsg                      =  Module.ReceiveMsg;
    activity                        =  ReliableCommC.activity;
    sendDone                        =  Module.sendDone;

    Main.StdControl                 -> ReliableCommC.Control;

    Module.Leds                     -> LedsImpl.Leds;
    Module.Random                   -> PseudoRandomM.PseudoRandom;
    Module.RealSendMsg              -> ReliableCommC.SendMsg[AM_ULC];
    Module.RealReceiveMsg           -> ReliableCommC.ReceiveMsg[AM_ULC];
}
