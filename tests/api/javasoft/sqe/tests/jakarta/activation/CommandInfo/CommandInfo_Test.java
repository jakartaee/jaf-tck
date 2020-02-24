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

/** FileTypeMap is an abstract class. The main functional testing is in the only provided
 *  concrete implementation, MimetypesFileTypeMap, tested separately. <p>
 *  Create CommandInfo objects using this classes constructors, this it successfully
 *  initiates objects of type CommandInfo then testcase passes otherwise it fails.
 */

public class CommandInfo_Test implements Test
{
	private String message = null;
	private DataHandler dh = null;
	public PrintWriter outLog;
	public PrintWriter outConsole;
        public Status status;

	public static void main(String argv[])
	{
		CommandInfo_Test lTest = new CommandInfo_Test();
		Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
		lStatus.exit();
	}

	public Status run(String argv[], PrintWriter outLog, PrintWriter outConsole)
	{
		this.outLog = outLog;
		// Test with a CommandObject
		String verb = "view";
		String className = "javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestCommandObject";

		outLog.println("UNIT TEST:	CommandInfo(verb, className)");

		CommandInfo cInfo = new CommandInfo(verb, className);	// API TEST

		if (!validateInfo(cInfo, verb, className))
		     return Status.failed(message);

		outLog.println("UNIT TEST:	passed\n");
		status = Status.passed("CommandInfo Test succeeded");
		return status;
	}

	private boolean validateInfo(CommandInfo cInfo, String verb, String className)
	{
		String resVerb = cInfo.getCommandName();

    		if (verb == null) {
		    if (resVerb != null){
	    	        message = "getCommandName(): expected null, but got " + resVerb;
	    	        return false;
		    }
    		} else if (!verb.equals(resVerb)) {
			    message = "getCommandName(): expected " + verb + " got " + resVerb;
			return false;
		}
    
		String resClass = cInfo.getCommandClass();

    		if (className == null) {
		    if (resClass != null){
	    		message = "getCommandClass(): expected null, but got " + resClass;
	    		return false;
		    }
    		} else {
			if (!className.equals(resClass)) {
	    		    message = "getCommandClass(): expected " + className + " got " + resClass;
	    		    return false;
			}	
    		}
		return true; 
	}
}
