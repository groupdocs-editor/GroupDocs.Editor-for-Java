#!/bin/sh

# export LIC_GROUPDOCS=/home/user/license.lic
export MAVEN_OPTS="-Xms512m -Xmx1g"

mvn clean compile exec:java

read -p "Press [Enter] key to continue..."
