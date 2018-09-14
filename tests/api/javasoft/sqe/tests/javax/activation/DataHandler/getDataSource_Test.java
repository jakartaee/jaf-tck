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

package	javasoft.sqe.tests.api.javax.activation.DataHandler;

import	java.io.*;
import	java.net.URL;
import	javax.activation.*;
import	com.sun.javatest.*;
import	com.sun.javatest.lib.MultiTest; 

/**
 * Create a DataHandler object pass it various types of strings then
 * use these objects to call getDataSource() api if this operation
 * successfully returns DataSource objects then this testcase passes.
 */

public class getDataSource_Test extends MultiTest
{
private static final String	kFileName = "foo.txt";

// cache objects for use in sequences of tests
private DataSource ds = null;     // DataSource for DataHandler(DataSource)
private DataHandler dsdh = null;  // a Dathandler created from a DataSource;

private Object obj = null;     	  // Object for DataHandler(Object, String)
private DataHandler objdh = null; // a DataHandler created from an Object/mimeType
private DataSource objds = null;  // DataSource returned from getDataSource for the Object case

public static void main(String argv[])
{
	getDataSource_Test lTest = new getDataSource_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status getDataSourceTest1() throws IOException
{	
	// setup the DataHandler
	ds = new FileDataSource(kFileName);
	dsdh = new DataHandler(ds);

	try {
		if(!ds.equals(dsdh.getDataSource()))	// API TEST
			return Status.failed("DataHandler(DataSource).getDataSource() doesn't match constructor arg");
	}catch(Exception ex) {
		return Status.failed("DataHandler(DataSource).getDataSource() threw " + ex.toString());
	}
	return Status.passed("getDataSource() test succeeded");
}

public Status getDataSourceTest2() throws IOException
{
	obj = new Object(); // just a random object
	objdh = new DataHandler(obj, "foo/goo");

	// spec states that it will never return null for getDataSource()
	try {
		objds = objdh.getDataSource();		// API TEST
	}catch(Exception ex) {
		return Status.failed("DataHandler(Object,String).getDataSource() threw "+ ex.toString());
	}
	if (objds == null)
	    return Status.failed("DataHandler(Object, String).getDataSource returned null");

	if (objdh.getName() != null)
	    return Status.failed("DataHandler(Object, String).getName != null");

	if (!"foo/goo".equals(objdh.getContentType()))
	      return Status.failed("DataHandler(Object, String)).getContentType != dh constructor arg");

	return Status.passed("getDataSource() test succeeded");
}

public Status getDataSourceTest3()
{
    String fileURLSpec = "file:test.txt";

	try {
	      URL url = new URL(fileURLSpec);
	      DataHandler dh = new DataHandler(url);

	      DataSource ds = dh.getDataSource(); 	// API TEST

	      if (!(ds instanceof URLDataSource))
		    return Status.failed("DataHandler(URL).getDataSource: expected a URLDataSource got " + ds.getClass().getName());

	      URLDataSource uds = (URLDataSource)ds;

	      if (!url.equals(uds.getURL()))
		  return Status.failed("DataHandler(url).getDataSource().getURL() != URL");
	} catch (Exception e) {
		return Status.failed("Exception caught!");
	}
	return Status.passed("getDataSource() test succeeded");
}
}
