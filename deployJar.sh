#!/usr/bin/env bash
JMETER_PATH="E:/Test/apache-jmeter-5.0"
MEDULE_PATH="D:\Workspace\jmeter-plugins\jmeter-utils"


cd $MEDULE_PATH
mvn clean
mvn package

cp target/jmeter-utils-1.0-SNAPSHOT.jar $JMETER_PATH/lib/ext