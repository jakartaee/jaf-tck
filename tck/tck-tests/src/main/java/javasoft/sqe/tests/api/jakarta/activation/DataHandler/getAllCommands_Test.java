/*
 * Copyright (c) 2021 Oracle and/or its affiliates. All rights reserved.
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

package	javasoft.sqe.tests.api.jakarta.activation.DataHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.CommandInfo;
import jakarta.activation.CommandMap;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.activation.MailcapCommandMap;
import javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestCommandMap;
import javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestCommandObject;

/** Create a DataHandler object then use it to invoke getAllCommands() api,
 *  if returns non-null array object containing all commands for the type
 *  of data represented in the DataHandler, then this testcase passes.
 */

public class getAllCommands_Test
{
private static final String kFileName = "foo.txt";
private String message = null;

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

@Test
public void testGetAllCommandsTest1()
{
    Status s = getAllCommandsTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
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

@Test
public void testGetAllCommandsTest2()
{
    Status s = getAllCommandsTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
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
