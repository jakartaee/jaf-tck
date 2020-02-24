/*
 * Copyright (c) 1997, 2019 Oracle and/or its affiliates. All rights reserved.
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

package	javasoft.sqe.tests.api.jakarta.activation.CommandInfo;

import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import	javasoft.sqe.tests.api.jakarta.activation.TestClasses.*;

/** FileTypeMap is an abstract class. The main functional testing is in the only
 *  provided concrete implementation, MimetypesFileTypeMap, tested separately. <p>
 *  Create a CommandInfo object with desired CommandName and then invoke getCommandName()
 *  api if it returns the expected value then this testcase passes otherwsie it fails.
 */

public class getCommandName_Test implements Test
{
	private String message = null;
	public PrintWriter outLog;
	public PrintWriter outConsole;

	public static void main(String argv[])
	{
		getCommandName_Test lTest = new getCommandName_Test();
		Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
		lStatus.exit();
	}

	public Status run(String argv[], PrintWriter outLog, PrintWriter outConsole)
	{
		this.outLog = outLog;
		// Test with a CommandObject
		String verb = "view";
		String className = "javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestCommandObject";

		CommandInfo cInfo = new CommandInfo(verb, className);

		outLog.println("UNIT TEST:      getCommandName()");

		if (!validateInfo(cInfo, verb, className))
		     return Status.failed(message);

		outLog.println("UNIT TEST:	passed\n");
		return Status.passed("getCommandName() Test succeeded");
	}

	private boolean validateInfo(CommandInfo cInfo, String verb, String className)
	{
		String resVerb = cInfo.getCommandName();	// API TEST

    		if (verb == null) {
		    if (resVerb != null){
	    	        message = "getCommandName(): expected null, but got " + resVerb;
	    	        return false;
		    }
    		} else if (!verb.equals(resVerb)) {
			    message = "getCommandName(): expected " + verb + " got " + resVerb;
			return false;
		}
		return true; 
	}
}
