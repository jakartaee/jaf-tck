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

package	javasoft.sqe.tests.api.jakarta.activation.FileTypeMap;

import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import	javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestFileTypeMap;

/** FileTypeMap is an abstract class. The main functional testing is in the only provided
 *  concrete implementation, MimetypesFileTypeMap, tested separately.<p>
 *  Get a default FileTypeMap object then use it to call setDefaultFileTypeMap() api
 *  with {FileTypeMap|null} parameter, then call getDefaultFileTypeMap() method, if
 *  set/get values are the same then this testcase passes, otherwise it fails.
 */

public class setDefaultFileTypeMap_Test implements Test
{

public static void main(String argv[])
{
	setDefaultFileTypeMap_Test lTest = new setDefaultFileTypeMap_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status run(String argv[], PrintWriter outLog, PrintWriter outConsole)
{
	// does getDefaultFileTypeMap return a MimetypesFileTypeMap?
	FileTypeMap systemTypeMap = FileTypeMap.getDefaultFileTypeMap();

	if (systemTypeMap == null)
	    return Status.failed("getDefaultFileTypeMap() returned null");
	 
	// Does setDefaultFileTypeMapMap work?   
	FileTypeMap testTypeMap = new TestFileTypeMap();
	FileTypeMap.setDefaultFileTypeMap(testTypeMap); 	// API TEST

	if (!testTypeMap.equals(FileTypeMap.getDefaultFileTypeMap()))
	    return Status.failed("getDefaultFileTypeMap failed to return FileTypeMap provided in setDefaultFileTypeMap");
    
	// Does setDefaultFileTypeMap(null) reset the default?  Note that this will return a new instance
	// of MimetypesFileTypeMap. The original instance was not cached when setDefaultFileTypeMap was called.

	FileTypeMap.setDefaultFileTypeMap(null);	// API TEST
	FileTypeMap restoredSysTypeMap = FileTypeMap.getDefaultFileTypeMap();

	if( restoredSysTypeMap == null)
	    return Status.failed("after setDefaultFileTypeMap(null) getDefaultFileTypeMap() returned null");

	if(!restoredSysTypeMap.getClass().equals(systemTypeMap.getClass()))
	    return Status.failed("setDefaultFileTypeMap(null) didn't reset default to original system map");

	return Status.passed("setDefaultFileTypeMap() test succeeded");
}

}
