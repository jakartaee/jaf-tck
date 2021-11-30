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

package javasoft.sqe.tests.jakarta.activation.DataHandler;

import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import 	com.sun.javatest.lib.MultiTest; 

/**
 * Create DataHandler objects with various type input file strings then use
 * these objects to call getOutputStream() api to fetch OutputStream objects,
 * if this is successfull then this testcase passes otherwise it fails.
 */

public class getOutputStream_Test extends MultiTest
{
private static final String	kFileName = "foo.txt";

// cache objects for use in sequences of tests
private DataSource ds = null; // DataSource for DataHandler(DataSource)
private DataHandler dsdh = null;  // a Dathandler created from a DataSource;

private Object obj = null;     // Object for DataHandler(Object, String)
private DataHandler objdh = null; // a DataHandler created from an Object/mimeType
private DataSource objds = null;  // DataSource returned from getDataSource for the Object case

public static void main(String argv[])
{
	getOutputStream_Test lTest = new getOutputStream_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status getOutputStreamTest()
{
	ds = new FileDataSource(kFileName);
	dsdh = new DataHandler(ds);

	obj = new Object(); // just a random object
	objdh = new DataHandler(obj, "foo/goo");
	Exception refex = null;

	try {
		OutputStream objos = objdh.getOutputStream();	// API TEST
		if (objos != null) {
			objos.close();
			return Status.failed("DataHandler(Object, String).getOutputStream != null");
		}
	} catch (Exception ioex) {
			return Status.failed("DataHandler(Object, String).getOutputStream threw " + ioex.toString());
	}
        // spec states that it will never return null for getDataSource()

        try {
                objds = objdh.getDataSource();
        } catch(Exception ex) {
                return Status.failed("DataHandler(Object,String).getDataSource() threw " + ex.toString());
        }
        if (objds == null)
                return Status.failed("DataHandler(Object, String).getDataSource returned null");

	try {
		OutputStream objos = objds.getOutputStream();	
		if (objos != null) {
			objos.close();
			return Status.failed("DataHandler(Object, String).getDataSource().getOutputStream != null");
		}
	} catch (Exception ioex) {
			return Status.failed("DataHandler(Object, String).getDataSource().getOutputStream threw " + ioex.toString());
	}
	
	try {
		InputStream objis = objdh.getInputStream();
		try {
			objis.close();
		} catch (Exception ex) {
		}
		return Status.failed("DataHandler(Object,String).getInputStream didn't throw");
	} catch(Exception ioex) {
		refex = ioex;
	}
	OutputStream os = null;

	try { // first make sure that the ds functions as expected
		OutputStream refos = ds.getOutputStream();	// API TEST
		os = dsdh.getOutputStream();
	} catch (Exception ex) {
		return Status.failed("DataHandler(DataSource).getOutputStream threw " + ex.toString());
	}
	DataSource no_out_ds = new FileDataSource("foo/goo");
	OutputStream ds_os = null;
	refex = null;
	
	try {
		ds_os = no_out_ds.getOutputStream();	// API TEST
		try {
			ds_os.close();
		} catch (Exception ex) {
		}
		return Status.failed("dataSourceNoOutputTest testing failure: Unable to create unwritable FileDataSource");
	} catch (Exception ex) {
		refex = ex;
	}
	DataHandler no_out_dh = new DataHandler(no_out_ds);
	OutputStream dh_os = null;

	try {
		dh_os = no_out_dh.getOutputStream();	// API TEST
		try {
			dh_os.close();
		} catch (Exception ex) {
		}
		return Status.failed("dataSourceNoOutputTest failed: getOutputStream didn't throw");
	} catch(Exception ex) {
		if (!ex.toString().equals(refex.toString())) 
			return Status.failed("dataSourceNoOutputTest failed: getOutputStream threw " + ex.toString() +
									" not " + refex.toString());
	}

	return Status.passed("getOutputStream() test succeeded");
}

}
