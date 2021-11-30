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

package javasoft.sqe.tests.jakarta.activation.FileTypeMap;

import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import javasoft.sqe.tests.jakarta.activation.TestClasses.TestFileTypeMap;

/** FileTypeMap is an abstract class. The main functional testing is in the only provided
 *  concrete implementation, MimetypesFileTypeMap, tested separately.<p> 
 *  Create an instance of FileTypeMap, then use it to call getContentType() api,
 *  with various {File|String} parameters. If this method returns a non-null 
 *  string object then this testcase passes, otherwise it fails.
 */

public class getContentType_Test implements Test
{

public static void main(String argv[])
{
	getContentType_Test lTest = new getContentType_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status run(String argv[], PrintWriter outLog, PrintWriter outConsole)
{
	File fob = new File("testfile.txt");
	FileTypeMap ftm = FileTypeMap.getDefaultFileTypeMap();
	String content1 = ftm.getContentType(fob);		// API TEST

	if( content1 == null )
	    return Status.failed("getContentType() returned null");

	String content2 = ftm.getContentType("testfile.txt");	// API TEST

	if( content2 == null )
	    return Status.failed("getContentType() returned null");

	return Status.passed("getContentType() test succeeded");
}

}
