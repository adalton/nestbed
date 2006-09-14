/* $Id:$ */
/*
 * Copyright (C) 2006 Free Software Initiative of Japan
 *
 * Author:      NIIBE Yutaka <gniibe at fsij.org>
 * Modified by: Andrew R. Dalton <andy.dalton at gmail.com>
 *
 * This file can be distributed under the terms and conditions of the
 * GNU General Public License version 2 (or later).
 *
 */

#include <usb.h>
#include <stdio.h>
#include <string.h>


#define USB_RT_PORT             (USB_TYPE_CLASS | USB_RECIP_OTHER)
#define USB_PORT_FEAT_POWER     8
#define MAX_LINE_LENGTH         128
#define SERIAL_MAX_LEN          128
#define DEVICE_FILE             "/proc/bus/usb/devices"
#define FORMAT                  "%[^=]%c%d%[^=]%c%[^=]%c%d%[^=]%c%d%[^=]%c%[^=]%c%d"
#define NUM_FIELDS              16




static void usage(const char* progname) {
    fprintf(stderr, "Usage: %s -s <SERIAL>\n", progname);
}


static int getHubDeviceInformation(int  busNum,    int  devDevNum,
                                   int* hubDevNum, int* hubPort) {
    int   result     = 0;
    FILE* usbDevices = fopen(DEVICE_FILE, "r");
    int   bus;
    int   prnt;
    int   port;
    int   dev;
    char  trash[MAX_LINE_LENGTH];
    char  equals;

    if (usbDevices != NULL) {
        char lineBuffer[MAX_LINE_LENGTH];

        while (     (fgets(lineBuffer, MAX_LINE_LENGTH, usbDevices) != NULL)
                 && !result) {

            if (lineBuffer[0] == 'T') {
                int count = sscanf(lineBuffer, FORMAT,
                                   trash, &equals, &bus,
                                   trash, &equals, trash, &equals, &prnt,
                                   trash, &equals, &port,
                                   trash, &equals, trash, &equals, &dev);

                if (count == NUM_FIELDS) {
                    if ( (bus == busNum) && (dev == devDevNum) ) {
                        *hubDevNum = prnt;
                        *hubPort   = port + 1;
                        result     = 1;
                    }
                }
            }
        }

        fclose(usbDevices);
    }

    return result;
}


static void init(void) {
    usb_init();
    usb_find_busses();
    usb_find_devices();
}


static void parseArgs(int argc, const char* argv[], char* serial) {
    int i;

    for (i = 1; i < argc; i++) {
        if (argv[i][0] == '-') {
            switch (argv[i][1]) {
            case 's':
                if (++i >= argc) {
                    usage(argv[0]);
                    exit(1);
                }
                strncpy(serial, argv[i], SERIAL_MAX_LEN);
                serial[SERIAL_MAX_LEN - 1] = '\0';
                break;

            default:
                usage(argv[0]);
                exit(1);
            }
        }
    }
}


int main(int argc, const char* argv[]) {
    char            serial[SERIAL_MAX_LEN] = { '\0' };
    struct usb_bus* busses;
    struct usb_bus* bus;

    parseArgs(argc, argv, serial);

    if (serial[0] == '\0') {
        usage(argv[0]);
        exit(1);
    }

    init();

    if ( (busses = usb_get_busses()) == NULL) {
        perror("usb_get_busses");
        exit(1);
    }


    for (bus = busses; bus != NULL; bus = bus->next) {
        struct usb_device* dev;

        for (dev = bus->devices; dev != NULL; dev = dev->next) {
            if (dev->descriptor.bDeviceClass != USB_CLASS_HUB) {
                char               devSerial[SERIAL_MAX_LEN];
                usb_dev_handle*    udh;

                if ( (udh = usb_open(dev)) == NULL) {
                    perror("usb_open");
                    exit(1);
                }

                if (usb_get_string_simple(udh, dev->descriptor.iSerialNumber,
                                          devSerial, SERIAL_MAX_LEN) < 0) {
                    perror("usb_get_string_simple");
                    exit(1);
                }

                if (strncmp(serial, devSerial, SERIAL_MAX_LEN) == 0) {
                    int busNum    = atoi(bus->dirname);
                    int devDevNum = atoi(dev->filename);
                    int hubDevNum;
                    int hubPort;

                    if (getHubDeviceInformation(busNum,     devDevNum,
                                                &hubDevNum, &hubPort)) {

                        // We found the device!
                        printf("bus=%d\ndevice=%d\nport=%d\n",
                               busNum, hubDevNum, hubPort);
                        exit(0);

                    } else {
                        fprintf(stderr, "Unable to lookup device information.");
                        fprintf(stderr, "  Bus: %d, Dev: %d\n",
                                busNum, devDevNum);
                        exit(1);
                    }
                }
                usb_close(udh);
            }
        }
    }

    fprintf(stderr, "Device not found.\n");
    return 1;
}
