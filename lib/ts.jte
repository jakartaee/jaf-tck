#
# Copyright (c) 2018, 2019 Oracle and/or its affiliates. All rights reserved.
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
#

#########################################################################
#                                                                   	#
#   JavaTest Environment file for TCK                                   #
#                                                                       #
#   The platform specific environments in this file will likely 	#
#   have to be modified during installation of the TCK.                 #
#   You can modify values inline, here, or you can override them in 	#
#   a separate file, and give both files to JavaTest.			#
#                                                                       #
#########################################################################

# User defined environment variables

# Set this to your TCK installation directory.

# Use this style naming convention for Windows.
##TS_HOME=D:\\tck\\jaftck

# And this style naming convention for UNIX.
# TS_HOME=/home/abc/jaftck
TS_HOME=MUST-BE-SET

# Set this to the installation of your JDK.

##JAVA_HOME=D:\\jdk1.8
##JAVA_HOME=/usr/jdk1.8
JAVA_HOME=MUST-BE-SET

# Set this to the directory containing the Activation .jar 
# file (i.e., jakarta.activation.jar)
JARPATH=MUST-BE-SET

# Adjust these as necessary for Windows or UNIX

##LOCAL_CLASSES=$JARPATH\\jakarta.activation.jar
LOCAL_CLASSES=$JARPATH/jakarta.activation.jar

##TESTCLASS=$TS_HOME\\classes
TESTCLASS=$TS_HOME/classes

##extraOtherJVMClassPath=$TS_HOME\\sigtest.jar;$LOCAL_CLASSES;$TESTCLASS
extraOtherJVMClassPath=$TS_HOME/sigtest.jar:$LOCAL_CLASSES:$TESTCLASS

##javatestClassDir=$TS_HOME\\javatest.jar
javatestClassDir=$TS_HOME/javatest.jar

##SYSTEMROOT=C:\\WINNT

# Directory to store the input files or data for the TCK tests
# This should be set to the installation <Directory of the TCK>/tests/testdata
#JAVAMAIL_TESTDATA_DIR=$TS_HOME\\tests\\testdata
JAVAMAIL_TESTDATA_DIR=$TS_HOME/tests/testdata

#Test URL for signature tests.
# This should be set to the installation <Directory of the TCK>/tests
testURL=file://$TS_HOME/tests


# Shorthand forms, for convenience.
# These should not need to be changed.

# TestFinder macros
TCKTestFinder=com.sun.javatest.finder.HTMLTestFinder

# Script macros
TCKScript=com.sun.javatest.lib.StdTestScript
TCKReportScript=com.sun.javatest.lib.ReportScript

# Command Macros
ExecTCKTestOtherJVMCmd=com.sun.javatest.lib.ExecStdTestOtherJVMCmd
JDKCompileCommand=com.sun.javatest.lib.JavaCompileCommand
ProcessCommand=com.sun.javatest.lib.ProcessCommand

#---------- "Reporting" environment --------------------------------------------


# Use this environment to report on previously executed tests
# without running them again.  You can select a different set
# of tests than that previously run: e.g. to do a big run,
# break it in pieces, run the pieces separately, and then
# do a single report of all tests using this environment.

# Environment jck-report
#
env.jck-report.description=Report on previously executed tests
env.jck-report.finder=$TCKTestFinder
env.jck-report.script=$TCKReportScript



#---------- JDK/UNIX configuration ------------------------------------------

# Environment jafUnix
#
env.jafUnix.description=\
	Run the runtime tests in a separate JVM (process) on UNIX
env.jafUnix.finder=$TCKTestFinder
env.jafUnix.script=$TCKScript
 
#
# The runtime to be tested ...
#
env.jafUnix.command.execute=$ExecTCKTestOtherJVMCmd \
	CLASSPATH=$testSuiteRootDir/../classes$:$testClassDir:\
	$javatestClassDir$:$extraOtherJVMClassPath \
	HOME=${user.home} \
	$JAVA_HOME/bin/java -verify $testExecuteClass $testExecuteArgs $testDebug


#---------- JDK/Windows NT configuration ---------------------------------------

# Environment jafWindows
#
env.jafWindows.description=\
	Run the runtime tests in a separate JVM (process) on Windows NT
env.jafWindows.finder=$TCKTestFinder
env.jafWindows.script=$TCKScript

#
#
# This environment will run the runtime tests
# in a separate JVM (process) on Windows NT.
#
# The runtime to be tested ...
# 	The SystemRoot variable should be set to the Windows NT system 
#	installation directory.
#
# You can either set the value of SystemRoot explicitly, or you can set it to
# refer to a system property that you set on the command line when you start
# JavaTest, which might in turn be set from your Windows NT SystemRoot variable.
#
env.jafWindows.command.execute=$ExecTCKTestOtherJVMCmd \
	CLASSPATH=$testSuiteRootDir$/..$/classes$:$javatestClassDir$:$extraOtherJVMClassPath \
	SystemRoot=${SYSTEMROOT} \
	windir=${SYSTEMROOT} \
	$JAVA_HOME$/bin$/java -verify $testExecuteClass $testExecuteArgs $testDebug
