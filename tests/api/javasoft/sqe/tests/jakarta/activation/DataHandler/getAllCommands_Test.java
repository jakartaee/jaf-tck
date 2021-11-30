/*
 * Copyright (c) 1997, 2021 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */

package javasoft.sqe.tests.jakarta.activation.DataHandler;

import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import	com.sun.javatest.lib.MultiTest; 
import javasoft.sqe.tests.jakarta.activation.TestClasses.*;

/** Create a DataHandler object then use it to invoke getAllCommands() api,
 *  if returns non-null array object containing all commands for the type
 *  of data represented in the DataHandler, then this testcase passes.
 */

public class getAllCommands_Test extends MultiTest
{
private static final String kFileName = "foo.txt";
private String message = null;

public static void main(String argv[])
{
	getAllCommands_Test lTest = new getAllCommands_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

private boolean checkCmdInfo(CommandInfo cmdInfo, String verb, String className)
{
	if (cmdInfo == null) {
		message = "unexpected null";
		return false;
	} 
	if (!className.equals(cmdInfo.getCommandClass())) {
		message = "didn't match classname for specified verb";
		return false;
	}
	if (!verb.equals(cmdInfo.getCommandName())) {
		message = "didn't match verb to specified verb";
		return false;
	}
	return true;
}
/*
*  getAllCommandsTest1: Test for getAllCommands() method.
*/
public Status getAllCommandsTest1()
{
	MailcapCommandMap mcm = new MailcapCommandMap(); // make a new mcm
	mcm.addMailcap("foo/plain; ; x-java-view=rom.pun.activation.viewers.FooViewer"); //add a new MT

	CommandMap.setDefaultCommandMap(mcm); // make it the default
	DataHandler dh = new DataHandler(new TestCommandObject(),"foo/plain");

	CommandInfo cmdInfoArray[] = dh.getAllCommands();	// API TEST

	if (cmdInfoArray == null)
	    return Status.failed("DataHandler.getAllCommands returned null");

	if (cmdInfoArray.length != 1)
	    return Status.failed("DataHandler.getAllCommands has wrong number of elements");

	if (!checkCmdInfo(cmdInfoArray[0], "view","rom.pun.activation.viewers.FooViewer"))
	     return Status.failed("DataHandler.getAllCommands()[0]: " + message);

	return Status.passed("getAllCommands() test succeeded");
}
/*
*  getAllCommandsTest2: Another Test for getAllCommands() method.
*/

public Status getAllCommandsTest2()
{
	FileDataSource fds = new FileDataSource(kFileName);
	DataHandler dh = new DataHandler(fds);

	TestCommandMap tcm = new TestCommandMap(fds.getContentType());
	dh.setCommandMap(tcm);

	CommandInfo cmdInfoArray[] = dh.getAllCommands();	// API TEST

	if (cmdInfoArray == null)
		return Status.failed("DataHandler.getAllCommands returned null");

	if (cmdInfoArray.length != 1)
		return Status.failed("DataHandler.getAllCommands has wrong number of elements");

	return Status.passed("getAllCommands() test succeeded");
}

}
