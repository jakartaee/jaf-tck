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
import	com.sun.javatest.lib.MultiTest; 
import javasoft.sqe.tests.jakarta.activation.TestClasses.*;
import javasoft.sqe.tests.jakarta.activation.TestClasses.TestDataSource;

/**
 * Create DataHandler objects with various type input file strings then use
 * these objects to call getInputStream() api to fetch InputStream objects,
 * if this is successfull then this testcase passes otherwise it fails.
 */

public class getInputStream_Test extends MultiTest
{
private static final String	kFileName = "foo.txt";

// cache objects for use in sequences of tests
private DataSource ds = null; 	  // DataSource for DataHandler(DataSource)
private DataHandler dsdh = null;  // a Dathandler created from a DataSource;

private Object obj = null;        // Object for DataHandler(Object, String)
private DataHandler objdh = null; // a DataHandler created from an Object/mimeType
private DataSource objds = null;  // DataSource returned from getDataSource for the Object case

private byte bytes[] = {0,1,2,3,4,5,6,7,8,9};

public static void main(String argv[])
{
	getInputStream_Test lTest = new getInputStream_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status getInputStreamTest()
{	
	// setup the DataHandler
	// ds = new FileDataSource(kFileName);
	ds = new TestDataSource(bytes);
	dsdh = new DataHandler(ds);

	obj = new Object(); // just a random object
	objdh = new DataHandler(obj, "foo/goo");
	Exception refex = null;

	try {
		InputStream objis = objdh.getInputStream();	// API TEST
		try {
			objis.close();
		} catch (Exception ex) {
		}
		return Status.failed("DataHandler(Object,String).getInputStream didn't throw");
	} catch(Exception ioex) {
		refex = ioex;
	}
        // spec states that it will never return null for getDataSource()

        try {
                objds = objdh.getDataSource();
        }catch(Exception ex) {
                return Status.failed("DataHandler(Object,String).getDataSource() threw " + ex.toString());
        }

        if (objds == null)
                return Status.failed("DataHandler(Object, String).getDataSource returned null");

	try {
		InputStream objis = objds.getInputStream();	// API TEST
		try {
			objis.close();
		} catch (Exception ex){
		}
		return Status.failed("DataHandler(Object,String).getDataSource.getInputStream didn't throw");
	} catch(Exception ioex) {
		if (!ioex.toString().equals(refex.toString())) 
			return Status.failed("objNoInputTest failed: getInputStream threw " + ioex.toString() +
						" not " + refex.toString());
	}
	InputStream is = null;

	try {
		is = dsdh.getInputStream();	// API TEST
	} catch (Exception ex) {
		return Status.failed("DataHandler(DataSource).getInputStream() threw " + ex.toString());
	}

	try {
		if (!validateInput(is))
			return Status.failed("DataHandler(DataSource).getInputStream(): stream content error");
	} catch (Exception ex) {
		return Status.failed("DataHandler(DataSource).getInputStream().read() threw " + ex.toString());
	} finally {
		try {
			is.close();
		} catch (Exception ex) {
			return Status.failed("DataHandler(DataSource).getInputStream().close() threw " + ex.toString());
		}
	}
	DataSource no_in_ds = new FileDataSource("missingFile");
	InputStream ds_is = null;
	refex = null;

	try {
		ds_is = no_in_ds.getInputStream();	// API TEST
		try {
			ds_is.close();
		} catch (Exception ex) {
		}
		return Status.failed("dataSourceNoInputTest testing failure: Unable to create ureadable FileDataSource");
	} catch(Exception ex ){
		refex = ex;
	}
	DataHandler no_in_dh = new DataHandler(no_in_ds);
	InputStream dh_is = null;

	try {
		dh_is = no_in_dh.getInputStream();	// API TEST
		try {
			dh_is.close();
		} catch (Exception ex) {
		}
		return Status.failed("dataSourceNoInputTest failed: getInputStream didn't throw");
	} catch(Exception ex) {
		if (!ex.toString().equals(refex.toString())) 
			return Status.failed("dataSourceNoInputTest failed: getInputtStream threw " + ex.toString() +
									" not " + refex.toString());
	}
	return Status.passed("getInputStream() test succeeded");
}

private boolean validateInput(InputStream is) throws IOException {

        for (int i = 0; i < bytes.length; i++) {
	    byte my_byte = (byte)is.read();
// 	    System.out.println("my_byte = " + my_byte + " byte[" +
// 			       i + "] = " + bytes[i]);
	    if (my_byte != (int)bytes[i]){
		// System.out.println("here...");
                        return false;
	    }
	}

        if (is.read() != -1){
	    System.out.println("no, here!");

	    return false;
	}

        return true;
}

}
