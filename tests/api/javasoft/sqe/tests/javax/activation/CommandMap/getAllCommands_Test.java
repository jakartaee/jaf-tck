/*
 * Copyright (c) 1997, 2018 Oracle and/or its affiliates. All rights reserved.
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

package	javasoft.sqe.tests.api.javax.activation.CommandMap;

import	java.io.*;
import	javax.activation.*;
import	com.sun.javatest.*;
import	javasoft.sqe.tests.api.javax.activation.TestClasses.*;

/** CommandMap is an abstract class. The main functional testing is in the only
 *  provided concrete implementation, MailcapCommandMap, done separately. <p>
 *  Get a CommandMap object by calling getDefaultCommandMap() api then call
 *  getAllCommands() api with string parameter if it returns a CommandInfo
 *  array object then this testcase passes otherwise it fails.
 */

public class getAllCommands_Test implements Test
{
	public static void main(String argv[])
	{
		getAllCommands_Test lTest = new getAllCommands_Test();
		Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
		lStatus.exit();
	}

	public Status run(String argv[], PrintWriter outLog, PrintWriter outConsole)
	{
		// does getDefaultCommandMap return a MailcapCommandMap?
		CommandMap lCommandMap = CommandMap.getDefaultCommandMap();

		if (!(lCommandMap instanceof MailcapCommandMap))
	    	    return Status.failed("getDefaultCommandMap() did not return MailcapCommandMap");
	
		CommandInfo[] comap = lCommandMap.getAllCommands("text/plain");

		if( comap == null )
		    return Status.failed("getAllCommands failed to return CommandInfo array");

		return Status.passed("getAllCommands() Test succeeded");
	}
}
