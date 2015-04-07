# Instructions for installing the **NESTbed** system

# Introduction #
This page will serve as a guide to individuals interested in installing the **NESTbed** system locally.

# Necessary Software #
**NESTbed** is a Java-based implementation of a wireless sensor network testbed.  Although it is written in Java, it depends on a set of third-party software packages.  Those software packages include:

  * Linux (The software was developed using [Gentoo](http://www.gentoo.org/), but other Linux distributions may work as well).
  * [udev](http://www.kernel.org/pub/linux/utils/kernel/hotplug/udev.html)
  * [libusb](http://libusb.sourceforge.net/)
  * [MySQL](http://www.mysql.org/)
  * [TinyOS 2.x](http://www.tinyos.net/)
  * A web server such as [Apache](http://www.apache.org/)
  * [Java 6](http://java.sun.com/)
  * [Apache Ant](http://ant.apache.org/)
  * [cpptasks](http://ant-contrib.sourceforge.net/) - Ant support for compiling C/C++ applications

# Installation #
These instructions assume you will install **NESTbed** in `/opt/nestbed`.

  1. Install all necessary software (listed above)
  1. `cd /opt`
  1. `svn co http://nestbed.googlecode.com/svn/trunk/nestbed`
  1. `cd nestbed/misc`
  1. Verify the paths in `99nestbed` and `nestbed` point to your TinyOS installation
  1. Update `common.properties` to include the IP of the machine that will host the **NESTbed** server (e.g., `testbed.rmi.baseurl=//<yourServerIp>/nestbed/`).
  1. Update `nestbed.jnlp` to include `codebase` and `href` references to the web server hosting the client application.
  1. If you want to install in a location other than `/opt/nestbed`, modify `server.properties` and modify the line that reads `nestbed.dir.root=/opt/nestbed`.
  1. In `serverLog.conf`, set the location where you want the server logs generated.  The default is `/var/www/localhost/htdocs/nestbed/serverLog.txt`
  1. cd ..
  1. `ant dist` (This will build the **NESTbed** software) **NOTE: need to resolve the keystore stuff**
  1. `mkdir </path/to/a/directory/available/from/the/webserver>`
  1. `cp -R dist/* </path/to/a/directory/available/from/the/webserver>`
  1. `cp misc/99nestbed /etc/env.d/`
  1. `env-update`
  1. `cp misc/nestbed /etc/init.d/`
  1. `cp misc/10-moteiv.rules /etc/udev/rules.d/`
  1. `udevstart` (This may not be necessary)
  1. `useradd -d / -n -s /sbin/nologin nestbed`
  1. `chown -R nestbed:nestbed /opt/nestbed`
  1. `/etc/init.d/nestbed start`










