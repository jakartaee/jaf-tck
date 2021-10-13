/*
 * Copyright (c) 2021 Oracle and/or its affiliates. All rights reserved.
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource; 

/**
 * Create DataHandler objects with mimetype string, then use it to call
 * getContentType() api if it returns the expected mimetype value then
 * this testcase passes otherwise it fails.
 */

public class getContentType_Test
{
private static final String	kFileName = "foo.txt";

// cache objects for use in sequences of tests
private DataSource ds = null; // DataSource for DataHandler(DataSource)
private DataHandler dsdh = null;  // a Dathandler created from a DataSource;

private Object obj = null;     // Object for DataHandler(Object, String)
private DataHandler objdh = null; // a DataHandler created from an Object/mimeType
private DataSource objds = null;  // DataSource returned from getDataSource for the Object case

@Test
public void testGetContentTypeTest1()
{
    Status s = getContentTypeTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status getContentTypeTest1()
{
	// setup the DataHandler
	ds = new FileDataSource(kFileName);
	dsdh = new DataHandler(ds);

	if (!ds.getContentType().equals(dsdh.getContentType())) 	// API TEST
	     return Status.failed("DataHandler(DataSource).getContentType() doesn't match DataSource.getContentType()");

	return Status.passed("getContentType() test succeeded");
}

@Test
public void testGetContentTypeTest2()
{
    Status s = getContentTypeTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status getContentTypeTest2()
{
	obj = new Object(); // just a random object
	objdh = new DataHandler(obj, "foo/goo");

	// spec states that it will never return null for getDataSource()
	try {
		objds = objdh.getDataSource();
	} catch(Exception ex) {
		return Status.failed("DataHandler(Object,String).getDataSource() threw " + ex.toString());
	}
	if (objds == null)
		return Status.failed("DataHandler(Object, String).getDataSource returned null");

	if (objdh.getName() != null)
		return Status.failed("DataHandler(Object, String).getName != null");

	if (!"foo/goo".equals(objdh.getContentType()))		// API TEST
	      return Status.failed("DataHandler(Object, String)).getContentType != dh constructor arg");

	// Test the returned DataSource	as well
	if (objds.getName() != null)
		return Status.failed("DataHandler(Object, String).getDataSource().getName != null");
	
	if (!"foo/goo".equals(objds.getContentType()))		// API TEST
	      return Status.failed("DataHandler(Object, String).getDataSource().getContentType != dh constructor arg");

	return Status.passed("getContentType() test succeeded");
}
}
