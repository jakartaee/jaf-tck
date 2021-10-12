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

package	javasoft.sqe.tests.api.jakarta.activation.MailcapCommandMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.CommandInfo;
import jakarta.activation.MailcapCommandMap;

/**
 * Create a MailcapCommandMap instance, use it to call getAllCommands()
 * method, if it successfully returns non-null array object of type
 * CommandInfo then this test passes otherwise it fails.
 */

public class getAllCommands_Test
{
private static String testMailcap[] = { 
	"text/*;;		x-java-view=rom.pun.activation.viewers.FooViewer",
	"text/*;;		x-java-content-handler=javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestDCH",
	"text/*;;		x-java-view=rom.pun.activation.viewers.MooViewer"  // make sure the last one takes precedence
};

private MailcapCommandMap commandMap = null;
private String message;

@Test
public void run() throws IOException
{
    Status s = getAllCommandsTest();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status getAllCommandsTest()
{
	message = "getAllCommands() test succeeded";

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

	commandMap.addMailcap("text/*;;x-java-view=javasoft.sqe.tests.api.jakarta.activation.TestViewer");
	commandMap.addMailcap(
		"text/*;;x-java-content-handler=javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestDCH");
	if (!runTest("text/plain", "view", 
					"javasoft.sqe.tests.api.jakarta.activation.TestViewer", 
					"javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestDCH"))
		return Status.failed(message);

	// now we can test overriding the previous:	
	commandMap.addMailcap("text/*;;x-java-view=javasoft.sqe.tests.api.jakarta.activation.FooViewer");
	if (!runTest("text/plain", "view", 
					"javasoft.sqe.tests.api.jakarta.activation.FooViewer", 
					"javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestDCH"))
		return Status.failed(message);

	// see how it handles it if I add some garbage.  There's no exception defined, so at best, 
	// it will be ignored, and the results will remain as they were before.

	commandMap.addMailcap("x6`&@:;;fudge&_12345");
	if (!runTest("text/plain", "view", 
					"javasoft.sqe.tests.api.jakarta.activation.FooViewer", 
					"javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestDCH"))
		return Status.failed(message);

    	return Status.passed(message);
}

private boolean runTest(String mimeType, String verb, String className, String dchClassName)
{
	CommandInfo[] lAllCmds = commandMap.getAllCommands(mimeType);	// API TEST
	if (lAllCmds == null){
		message = "getAllCommands returned null";
		return false;
	}

	if (verb == null) {
		if (lAllCmds.length > 0) {
			message = "getAllCommands returned unexpected command(s)";
			return false;
		}
	} else {
		if (lAllCmds.length < 1) {
			message = "getAllCommands unexpectedly empty";
			return false;
		}
	}
	return true;
}

}
