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

/** Using instances of DataHandler we invoke getPreferredCommands() api, if this
 *  method returns a non-null array object of type CommandInfo, of size greater
 *  then zero then this testcase passes otherwise it fails.
 */

public class getPreferredCommands_Test extends MultiTest
{
private static final String	kFileName = "foo.txt";
private String message = null;

public static void main(String argv[])
{
	getPreferredCommands_Test lTest = new getPreferredCommands_Test();
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

public Status getPreferredCommandsTest()
{
	DataHandler dh1 = new DataHandler(new TestCommandObject(), "foo/plain");
	
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

	// UNIT TEST 1:

        CommandInfo cmdInfoArray[] = dh1.getAllCommands();
        if (cmdInfoArray == null)
                return Status.failed("DataHandler.getAllCommands returned null");

        if (cmdInfoArray.length != 1)
                return Status.failed("DataHandler.getAllCommands has wrong number of elements");

        if (!checkCmdInfo(cmdInfoArray[0], "view", "rom.pun.activation.viewers.FooViewer"))
                return Status.failed("DataHandler.getAllCommands()[0]: " + message);

	// Definition of getPreferredCommands is implementation dependent.  It should be a subset
	// (proper or improper) of getAllCommands though.

	cmdInfoArray = dh1.getPreferredCommands();	// API TEST

	if (cmdInfoArray == null)
		return Status.failed("DataHandler.getPreferredCommands returned null");

	if (cmdInfoArray.length > 1)
		return Status.failed("DataHandler.getPreferredCommands returned too many commands");
	else if (cmdInfoArray.length == 1 && 
			!checkCmdInfo(cmdInfoArray[0], "view",  "rom.pun.activation.viewers.FooViewer"))
		return Status.failed("DataHandler.getPreferredCommands[0]: "  + message);

	// END UNIT TEST 1:
	// UNIT TEST 2:

	FileDataSource fds = new FileDataSource(kFileName);
	DataHandler dh2 = new DataHandler(fds);
	
	// Just to keep the code cleaner below, will stash some static validation values
	// from TestCommandMap
	String className = TestCommandMap.getClassName();
	String verb = TestCommandMap.getVerb();
	String preferredClassName = TestCommandMap.getPreferredClassName();
	String preferredVerb = TestCommandMap.getPreferredVerb();
	String incorrectMimeTypeVerb = TestCommandMap.getIncorrectMimeTypeVerb();
	
	TestCommandMap tcm = new TestCommandMap(fds.getContentType());
	
	dh2.setCommandMap(tcm);

        CommandInfo cmdInfoArray2[] = dh2.getAllCommands();
        if (cmdInfoArray2 == null)
                return Status.failed("DataHandler.getAllCommands returned null");

        if (cmdInfoArray2.length != 1)
                return Status.failed("DataHandler.getAllCommands has wrong number of elements");

        if (!checkCmdInfo(cmdInfoArray2[0], verb, className)){
                if (incorrectMimeTypeVerb.equals(cmdInfoArray2[0].getCommandName()))
                        return Status.failed ("DataHandler.getAllCommands passed incorrect mimeType to CommandMap");
                else
                        return Status.failed("DataHandler.getPreferredCommands[0]: "  + message);

        }
	// Definition of getPreferredCommands is implementation dependent.  It should be a subset
	// (proper or improper) of getAllCommands though.

	cmdInfoArray2 = dh2.getPreferredCommands();	// API TEST

	if (cmdInfoArray2 == null)
		return Status.failed("DataHandler.getPreferredCommands returned null");
		
	if (cmdInfoArray2.length != 1)
		return Status.failed("DataHandler.getPreferredCommands returned wrong number of elements");
		
	if (!checkCmdInfo(cmdInfoArray2[0], preferredVerb,  preferredClassName)) {
		if (incorrectMimeTypeVerb.equals(cmdInfoArray2[0].getCommandName()))
			return Status.failed ("DataHandler.getPreferredCommands passed incorrect mimeType to CommandMap");
		else
			return Status.failed("DataHandler.getPreferredCommands[0]: "  + message);
	}
	// END UNIT TEST 2:

	return Status.passed("getPreferredCommands() test succeeded");
}

}
