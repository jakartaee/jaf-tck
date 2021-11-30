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

package javasoft.sqe.tests.jakarta.activation.CommandMap;

import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.activation.TestClasses.*;

/** CommandMap is an abstract class. The main functional testing is in the only
 *  provided concrete implementation, MailcapCommandMap, done separately.<p>
 *  We first get default CommandMap object then using it call setDefaultCommandMap()
 *  with CommandMap/Null object parameter and if these operation is successfull
 *  then this testcase passes otherwise it fails.
 */

public class setDefaultCommandMap_Test implements Test
{
	public static void main(String argv[])
	{
		setDefaultCommandMap_Test lTest = new setDefaultCommandMap_Test();
		Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
		lStatus.exit();
	}

	public Status run(String argv[], PrintWriter outLog, PrintWriter outConsole)
	{
		// does getDefaultCommandMap return a MailcapCommandMap?
		CommandMap lCommandMap = CommandMap.getDefaultCommandMap();

		if (!(lCommandMap instanceof MailcapCommandMap))
	    	    return Status.failed("getDefaultCommandMap() did not return MailcapCommandMap");
	 
		// Does setDefaultCommandMap work?   
		lCommandMap = new TestCommandMap(null);
		CommandMap.setDefaultCommandMap(lCommandMap);	// API TEST

		if (!lCommandMap.equals(CommandMap.getDefaultCommandMap()))
		    return Status.failed("getDefaultCommandMap failed to return CommandMap provided in setDefaultCommandMap");

		// Does setDefaultCommandMap(null) reset the default?
		CommandMap.setDefaultCommandMap(null);		// API TEST
		lCommandMap = CommandMap.getDefaultCommandMap();

		if (!(lCommandMap instanceof MailcapCommandMap))
		    return Status.failed("setDefaultCommandMap(null) didn't reset default to MailcapCommandMap");

		return Status.passed("setDefaultCommandMap Test succeeded");
	}
}
