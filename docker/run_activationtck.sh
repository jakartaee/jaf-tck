#!/bin/bash -xe
#
# Copyright (c) 2018, 2020 Oracle and/or its affiliates. All rights reserved.
#
# This program and the accompanying materials are made available under the
# terms of the Eclipse Public License v. 2.0, which is available at
# http://www.eclipse.org/legal/epl-2.0.
#
# This Source Code may also be made available under the following Secondary
# Licenses when the conditions for such availability set forth in the
# Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
# version 2 with the GNU Classpath Exception, which is available at
# https://www.gnu.org/software/classpath/license.html.
#
# SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0

if ls ${WORKSPACE}/bundles/*activation-tck*.zip 1> /dev/null 2>&1; then
  echo "Using stashed bundle for activation-tck created during the build phase"
  unzip -o ${WORKSPACE}/bundles/*activation-tck*.zip -d ${WORKSPACE}
  TCK_NAME=activation-tck
elif ls ${WORKSPACE}/bundles/*jaftck*.zip 1> /dev/null 2>&1; then
  echo "Using stashed bundle for jaftck created during the build phase"
  unzip ${WORKSPACE}/bundles/*jaftck*.zip -d ${WORKSPACE}
  TCK_NAME=jaftck
else
  echo "[ERROR] TCK bundle not found"
  exit 1
fi

export TS_HOME=${WORKSPACE}/${TCK_NAME}


if [[ "$JDK" == "JDK11" || "$JDK" == "jdk11" ]];then
  export JAVA_HOME=${JDK11_HOME}
  export PATH=$JAVA_HOME/bin:$PATH
fi

WGET_PROPS="--progress=bar:force --no-cache"
if [ -z "$ACTIVATION_BUNDLE_URL" ];then
  export ACTIVATION_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/jakarta/activation/jakarta.activation-api/2.1.0-RC1/jakarta.activation-api-2.1.0-RC1.jar
fi
wget $WGET_PROPS $ACTIVATION_BUNDLE_URL -O ${WORKSPACE}/jakarta.activation-api.jar

sed -i "s#^TS_HOME=.*#TS_HOME=$TS_HOME#g" ${TS_HOME}/lib/ts.jte
sed -i "s#^JAVA_HOME=.*#JAVA_HOME=$JAVA_HOME#g" ${TS_HOME}/lib/ts.jte
sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE#g" ${TS_HOME}/lib/ts.jte


which ant
ant -version

which java
java -version

cd $TS_HOME
ant -Dreport.dir=$WORKSPACE/JTreport/${TCK_NAME} -Dwork.dir=$WORKSPACE/JTwork/${TCK_NAME}  run

HOST=`hostname -f`
echo "1 $TCK_NAME $HOST" > $WORKSPACE/args.txt

mkdir -p $WORKSPACE/results/junitreports/
JT_REPORT_DIR=$WORKSPACE/JTreport
$JAVA_HOME/bin/java -Djunit.embed.sysout=true -jar ${WORKSPACE}/docker/JTReportParser/JTReportParser.jar $WORKSPACE/args.txt $JT_REPORT_DIR $WORKSPACE/results/junitreports/ 

tar zcvf ${WORKSPACE}/${TCK_NAME}-results.tar.gz $WORKSPACE/JTreport/${TCK_NAME} $WORKSPACE/JTwork/${TCK_NAME} $WORKSPACE/results/junitreports/
