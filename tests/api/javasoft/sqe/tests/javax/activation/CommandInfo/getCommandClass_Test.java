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

package	javasoft.sqe.tests.api.javax.activation.CommandInfo;

import	java.io.*;
import	javax.activation.*;
import	com.sun.javatest.*;
import	javasoft.sqe.tests.api.javax.activation.TestClasses.*;

/** FileTypeMap is an abstract class. The main functional testing is in the only
*   provided concrete implementation, MimetypesFileTypeMap, tested separately. <p>
*   If getCommandClass() returns the expected classname then this testcase passes,
*   otherwise it fails.
*/

public class getCommandClass_Test implements Test
{
	private String message = null;
	public PrintWriter outConsole;

	public static void main(String argv[])
	{
		getCommandClass_Test lTest = new getCommandClass_Test();
		Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
		lStatus.exit();
	}

	public Status run(String argv[], PrintWriter outLog, PrintWriter outConsole)
	{
		// Test with a CommandObject
		String verb = "view";
		String className = "javasoft.sqe.tests.api.javax.activation.TestClasses.TestCommandObject";
		CommandInfo cInfo = new CommandInfo(verb, className);

		if (!validateInfo(cInfo, verb, className))
		     return Status.failed(message);

		return Status.passed("getCommandClass() Test succeeded");
	}

	private boolean validateInfo(CommandInfo cInfo, String verb, String className)
	{
                String resClass = cInfo.getCommandClass();	// API TEST

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
