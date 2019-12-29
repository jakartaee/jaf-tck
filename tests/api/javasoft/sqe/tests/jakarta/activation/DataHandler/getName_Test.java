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

import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import	com.sun.javatest.lib.MultiTest; 

/**
 * Create DataHandler object and then call getName() api, if this operation
 * returns the expected non-null string then this testcase passes.
 */

public class getName_Test extends MultiTest
{
private static final String	kFileName = "foo.txt";

// cache objects for use in sequences of tests
private DataSource ds = null; 		// DataSource for DataHandler(DataSource)
private DataHandler dsdh = null;  	// a Dathandler created from a DataSource;

private Object obj = null;     	  // Object for DataHandler(Object, String)
private DataHandler objdh = null; // a DataHandler created from an Object/mimeType
private DataSource objds = null;  // DataSource returned from getDataSource for the Object case

public static void main(String argv[])
{
	getName_Test lTest = new getName_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status getNameTest()
{
	// setup the DataHandler
	ds = new FileDataSource(kFileName);
	dsdh = new DataHandler(ds);

	if(!ds.getName().equals(dsdh.getName()))	// API TEST
		return Status.failed("DataHandler(DataSource).getName() doesn't match DataSource.getName()");

	obj = new Object(); // just a random object
	objdh = new DataHandler(obj, "foo/goo");

	if (objdh.getName() != null)		// API TEST
		return Status.failed("DataHandler(Object, String).getName != null");

        // spec states that it will never return null for getDataSource()
        try {
                objds = objdh.getDataSource();
        } catch(Exception ex) {
                return Status.failed("DataHandler(Object,String).getDataSource() threw " + ex.toString());
        }
        if (objds == null)
                return Status.failed("DataHandler(Object, String).getDataSource returned null");

	// Test the returned DataSource	as well

	if (objds.getName() != null)		// API TEST
		return Status.failed("DataHandler(Object, String).getDataSource().getName != null");

	return Status.passed("getName() test succeeded");
}

}
