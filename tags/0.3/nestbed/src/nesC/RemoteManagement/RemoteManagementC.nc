includes RemoteManagement;

configuration RemoteManagementC {
    provides {
        interface StdControl;
    }
}

implementation {
    components Main;
    components RemoteManagementM;
    components NetProgC;
    components LedsC;
    components GenericComm;
    components RandomLFSR;


    StdControl                   =  RemoteManagementM.StdControl;

    Main.StdControl              -> NetProgC.StdControl;
    RemoteManagementM.NetProg    -> NetProgC.NetProg;
    RemoteManagementM.Leds       -> LedsC.Leds;
    RemoteManagementM.Random     -> RandomLFSR.Random;
    RemoteManagementM.SendMsg    -> GenericComm.SendMsg[AM_SELECTIVE_REBOOT_MSG];
    RemoteManagementM.ReceiveMsg -> GenericComm.ReceiveMsg[AM_SELECTIVE_REBOOT_MSG];
}
