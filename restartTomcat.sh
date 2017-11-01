#!/bin/sh

sudo service tomcat stop
cd /opt/tomcat/webapps
sudo rm -rf ROOT
sudo service tomcat start
