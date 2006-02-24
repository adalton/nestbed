/* $Id$ */
includes RadioControl;

configuration RadioControlC {
    provides interface StdControl;
}

implementation {
    components Main, RadioControlM as Comp, GenericComm, CC2420ControlM, LedsC;

    Main.StdControl   -> GenericComm.Control;

    Comp.Leds         -> LedsC.Leds;
    Comp.ReceivePower -> GenericComm.ReceiveMsg[AM_POWERMESSAGE];
    Comp.Radio        -> CC2420ControlM.CC2420Control;

    StdControl        =  Comp.StdControl;
}
