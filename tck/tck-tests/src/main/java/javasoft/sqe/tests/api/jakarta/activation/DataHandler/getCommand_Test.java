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

/** Create a DataHandler object then use it to invoke getCommand() api,
 *  if this call returns a CommandInfo for specified command name, then
 *  this testcase passes, otherwise it fails.
 */

public class getCommand_Test
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
public void testGetCommandTest1()
{
    Status s = getCommandTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

/**
*  getCommandTest1: Test the behavior of the CommandMap wrapper calls when using the default
* command map. We need to have a know mapping to validate, so first, we install a default
* MailcapCommandMap and customize it with a distinctive mapping.
* We use the DataHandler(obj, mimetype) constructor here, in order to easily
* specify the mimetime that is the key to the distinctive mapping that we're providing.
*
* @return	Status object
*/

public Status getCommandTest1()
{
	DataHandler dh = new DataHandler(new TestCommandObject(), "foo/plain");

	// bug note:  If we were to perform any operation on dh that causes it to access
	// a command map at this point, the rest of this code won't work because it would 
	// cache the current default CommandMap, and not the one we're about to set!

	// first make sure that the CommandMap class has a MailcapCommandMap as the default
	// and prime that map with a distinctive mapping.
	MailcapCommandMap defaultCM = new MailcapCommandMap();
	CommandMap.setDefaultCommandMap(defaultCM);

	// there's no direct access to the dh commmand map object, so prove that it's using
	// the appropriate default by adding a characteristic value to the default map
	defaultCM.addMailcap("foo/*;;		x-java-view=rom.pun.activation.viewers.FooViewer");

	CommandInfo cmdInfo = dh.getCommand("view");		// API TEST

	if (!checkCmdInfo(cmdInfo, "view", "rom.pun.activation.viewers.FooViewer"))
		return Status.failed("DataHandler.getCommand: " +  message);

	CommandInfo cmdInfoArray[] = dh.getAllCommands();
	if (cmdInfoArray == null)
		return Status.failed("DataHandler.getAllCommands returned null");

	if (cmdInfoArray.length != 1)
		return Status.failed("DataHandler.getAllCommands has wrong number of elements");

	if (!checkCmdInfo(cmdInfoArray[0], "view", "rom.pun.activation.viewers.FooViewer"))
		return Status.failed("DataHandler.getAllCommands()[0]: " + message);

	cmdInfo = dh.getCommand("view");		// API TEST
	if (!checkCmdInfo(cmdInfo, "view", "rom.pun.activation.viewers.FooViewer"))
		return Status.failed("DataHandler.getCommand: after setCommandMap(null) " +  message);

	return Status.passed("getCommand(String) test succeeded");
}		

@Test
public void testGetCommandTest2()
{
    Status s = getCommandTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

/**
*  getCommandTest2: Test the behavior of the CommandMap wrapper calls when using a custom
*  CommandMap.  For variation from the defaultCommandMapTest above, we'll use 
*  the DataHandler(DataSource) constructor.  TestCommandMap will be configured to 
*  require the mimeType which is provided by the DataSource in order to function correctly.
*
* @return	Status object
*/

public Status getCommandTest2()
{
	FileDataSource fds = new FileDataSource(kFileName);
	DataHandler dh = new DataHandler(fds);

	// Just to keep the code cleaner below, will stash some static validation values
	// from TestCommandMap
	String className = TestCommandMap.getClassName();
	String verb = TestCommandMap.getVerb();
	String preferredClassName = TestCommandMap.getPreferredClassName();
	String preferredVerb = TestCommandMap.getPreferredVerb();
	String incorrectMimeTypeVerb = TestCommandMap.getIncorrectMimeTypeVerb();

	TestCommandMap tcm = new TestCommandMap(fds.getContentType());

	dh.setCommandMap(tcm);

	// TestCommandMap will use the provided verb, rather than the distinctive Verb in this case.
	CommandInfo cmdInfo = dh.getCommand("view");		// API TEST

	if (!checkCmdInfo(cmdInfo, "view", className))
		return Status.failed("DataHandler.getCommand: " + message);
		
	return Status.passed("getCommand(String) test succeeded");
}
}
