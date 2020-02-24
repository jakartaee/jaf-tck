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

package	javasoft.sqe.tests.api.jakarta.activation.DataHandler;

import  java.io.*;
import  jakarta.activation.*;
import  com.sun.javatest.*;
import  javasoft.sqe.tests.api.jakarta.activation.TestClasses.*;

/** Create a DataHandler object then use it to invoke getBean() api,
 *  if it returns a non-null Object then this testcase passes,
 *  otherwise it fails.
 */

public class getBean_Test implements Test
{
	public static void main(String argv[])
	{
		getBean_Test lTest = new getBean_Test();
		Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
		lStatus.exit();
	}

	public Status run(String argv[], PrintWriter outLog, PrintWriter outConsole)
	{
                String verb = "view";
                String className = "javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestCommandObject";

		// get CommandInfo object
		CommandInfo lCommandInfo = new CommandInfo(verb, className);

		if(!(lCommandInfo instanceof CommandInfo))
	    	    return Status.failed("CommandInfo() failed to return CommandInfo object!");

		Object obj = new Object(); // just a random object
		DataHandler dhl = new DataHandler(obj, "foo/goo");

                if( dhl == null )
                    return Status.failed("DataHandler() failed to return DataHandler object!");

		Object bean = dhl.getBean(lCommandInfo);	// API TEST

		if( bean == null )
		    return Status.failed("getBean failed to return non-null Object!");

		return Status.passed("getBean() test succeeded");
	}
}
