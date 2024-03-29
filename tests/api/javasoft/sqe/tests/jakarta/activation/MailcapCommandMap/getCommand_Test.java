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

package javasoft.sqe.tests.jakarta.activation.MailcapCommandMap;

import	java.beans.*;
import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 
import javasoft.sqe.tests.jakarta.activation.TestClasses.TestDCH;

/**
 * Create a MailcapCommandMap instance, use it to call getCommand()
 * method, if it successfully returns non-null object of type
 * CommandInfo then this test passes otherwise it fails.
 */

public class getCommand_Test extends MultiTest
{
private static String testMailcap[] = { 
	"text/*;;		x-java-view=rom.pun.activation.viewers.FooViewer",
	"text/*;;		x-java-content-handler=javasoft.sqe.tests.jakarta.activation.TestClasses.TestDCH",
	"text/*;;		x-java-view=rom.pun.activation.viewers.MooViewer"  // make sure the last one takes precedence
};

private MailcapCommandMap commandMap = null;
private String message;

public static void main(String argv[])
{
	getCommand_Test lTest = new getCommand_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status getCommandTest()
{
	message = "getCommand() test succeeded";

	// test using default mailcap file. We have no idea what could be defined there
	// in any implementation. Test a made up type that is unlikely to appear in any
	// default mapping for the appropriate behavior
	commandMap = new MailcapCommandMap();

	// test a mimeType that's not mapped at all	
	if (!runTest("foo/plain", null, null, null))
		return Status.failed(message);

	// use addMailcap to define a mapping, and one to bind a DataContentHandler
	// the named viewer class need not exist, but the dch class must exist.  Use one we already have:
	// in the default sun mailcap, this also tests overriding an existing mapping. There's no general
	// way to do that, since there's no way to know what types are provided as keys.

	commandMap.addMailcap("text/*;;x-java-view=javasoft.sqe.tests.jakarta.activation.TestViewer");
	commandMap.addMailcap(
		"text/*;;x-java-content-handler=javasoft.sqe.tests.jakarta.activation.TestClasses.TestDCH");
	if (!runTest("text/plain", "view", 
					"javasoft.sqe.tests.jakarta.activation.TestViewer", 
					"javasoft.sqe.tests.jakarta.activation.TestClasses.TestDCH"))
		return Status.failed(message);

	// now we can test overriding the previous:	
	commandMap.addMailcap("text/*;;x-java-view=javasoft.sqe.tests.jakarta.activation.FooViewer");
	if (!runTest("text/plain", "view", 
					"javasoft.sqe.tests.jakarta.activation.FooViewer", 
					"javasoft.sqe.tests.jakarta.activation.TestClasses.TestDCH"))
		return Status.failed(message);

	// see how it handles it if I add some garbage.  There's no exception defined, so at best, 
	// it will be ignored, and the results will remain as they were before.

	commandMap.addMailcap("x6`&@:;;fudge&_12345");
	if (!runTest("text/plain", "view", 
					"javasoft.sqe.tests.jakarta.activation.FooViewer", 
					"javasoft.sqe.tests.jakarta.activation.TestClasses.TestDCH"))
		return Status.failed(message);

    	return Status.passed(message);
}

private boolean runTest(String mimeType, String verb, String className, String dchClassName)
{
	if (verb != null)
	{
		CommandInfo lCmd = commandMap.getCommand(mimeType, verb);	// API TEST
		if (className == null) {
			if (lCmd != null) {
				message = "getCommand returned command for " + mimeType + "," + verb;
				return false;
			}
		}
		else {
			if (lCmd == null) {
				message = "getCommand returned null for:" + mimeType + "," + verb;
				return false;
			}
			if (!verb.equals(lCmd.getCommandName())) {
				message = "getCommand returned " + lCmd.getCommandName() + " for " + verb;
				return false;
			}
		}
	}
	return true;
}

}
