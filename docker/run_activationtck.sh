#!/bin/bash -xe
#
# Copyright (c) 2018, 2021 Oracle and/or its affiliates. All rights reserved.
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

export JAVA_HOME=${JDK11_HOME}

if [[ "$JDK" == "JDK12" || "$JDK" == "jdk12" ]];then
  export JAVA_HOME=${JDK12_HOME}
elif [[ "$JDK" == "JDK13" || "$JDK" == "jdk13" ]];then
  export JAVA_HOME=${JDK13_HOME}
elif [[ "$JDK" == "JDK14" || "$JDK" == "jdk14" ]];then
  export JAVA_HOME=${JDK14_HOME}
elif [[ "$JDK" == "JDK15" || "$JDK" == "jdk15" ]];then
  export JAVA_HOME=${JDK15_HOME}
elif [[ "$JDK" == "JDK16" || "$JDK" == "jdk16" ]];then
  export JAVA_HOME=${JDK16_HOME}
elif [[ "$JDK" == "JDK17" || "$JDK" == "jdk17" ]];then
  export JAVA_HOME=${JDK17_HOME}
fi  

export PATH=$JAVA_HOME/bin:$PATH


WGET_PROPS="--progress=bar:force --no-cache"
if [ -z "$ACTIVATION_BUNDLE_URL" ];then
  export ACTIVATION_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/jakarta/activation/jakarta.activation-api/2.1.0/jakarta.activation-api-2.1.0.jar
fi
wget $WGET_PROPS $ACTIVATION_BUNDLE_URL -O ${WORKSPACE}/jakarta.activation-api.jar

if [ -z "$ANGUS_BUNDLE_URL" ];then
  export ANGUS_BUNDLE_URL=https://jakarta.oss.sonatype.org/content/repositories/staging/org/eclipse/angus/angus-activation/1.0.0-SNAPSHOT/angus-activation-1.0.0-20210811.141336-3.jar
fi
wget $WGET_PROPS $ANGUS_BUNDLE_URL -O ${WORKSPACE}/angus-activation.jar

if [ -z "$GF_BUNDLE_URL" ]; then
  echo "Using default url for GF bundle: $DEFAULT_GF_BUNDLE_URL"
  export GF_BUNDLE_URL=$DEFAULT_GF_BUNDLE_URL
fi

wget $WGET_PROPS $GF_BUNDLE_URL -O latest-glassfish.zip
unzip -q -o latest-glassfish.zip

TOP_GLASSFISH_DIR="glassfish7"
chmod -R 777 ${TOP_GLASSFISH_DIR}

sed -i "s#^TS_HOME=.*#TS_HOME=$TS_HOME#g" ${TS_HOME}/lib/ts.jte
sed -i "s#^TS_HOME=.*#TS_HOME=$TS_HOME#g" ${TS_HOME}/lib/ts.pluggability.jte
sed -i "s#^JAVA_HOME=.*#JAVA_HOME=$JAVA_HOME#g" ${TS_HOME}/lib/ts.jte
sed -i "s#^JAVA_HOME=.*#JAVA_HOME=$JAVA_HOME#g" ${TS_HOME}/lib/ts.pluggability.jte

if [[ "$RUNTIME" == "Glassfish" ]]; then
  sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE/$TOP_GLASSFISH_DIR/glassfish/modules#g" ${TS_HOME}/lib/ts.jte
  sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE/$TOP_GLASSFISH_DIR/glassfish/modules#g" ${TS_HOME}/lib/ts.pluggability.jte

else 
  sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE#g" ${TS_HOME}/lib/ts.jte
  sed -i "s#^JARPATH=.*#JARPATH=$WORKSPACE#g" ${TS_HOME}/lib/ts.pluggability.jte
fi


which ant
ant -version

which java
java -version

export JT_REPORT_DIR=${TS_HOME}/JTreport

cd $TS_HOME
ant -Dreport.dir=$JT_REPORT_DIR run run.pluggability

HOST=`hostname -f`
echo "1 $HOST" > $WORKSPACE/args.txt

mkdir -p $WORKSPACE/results/junitreports/
$JAVA_HOME/bin/java -Djunit.embed.sysout=true -jar ${WORKSPACE}/docker/JTReportParser/JTReportParser.jar $WORKSPACE/args.txt $JT_REPORT_DIR $WORKSPACE/results/junitreports/ 

tar zcvf ${WORKSPACE}/${TCK_NAME}-results.tar.gz $JT_REPORT_DIR ${TS_HOME}/JTwork/ $WORKSPACE/results/junitreports/
