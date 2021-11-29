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
import  java.net.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import	com.sun.javatest.lib.MultiTest; 
import javasoft.sqe.tests.jakarta.activation.TestClasses.*;

/**
 * Invoke all constructors to create DataHandler objects. If these operations
 * are successfull then this testcase passes otherwise it fails.
 */

public class DataHandler_Test extends MultiTest
{
private static final String kFileName = "foo.txt";

// cache objects for use in sequences of tests
private DataSource ds = null; 		// DataSource for DataHandler(DataSource)
private DataHandler dsdh = null;  	// a Dathandler created from a DataSource;

private DataHandler objdh = null; 	// a DataHandler created from an Object/mimeType
private DataSource objds = null;  	// DataSource returned from getDataSource for the Object case

public static void main(String argv[])
{
	DataHandler_Test lTest = new DataHandler_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

/**
 *	dataHandlerTest1: a contructor test for DataHandler(DataSource);
 *
 * @return	Status object
 */
public Status	dataHandlerTest1()
{	
	//	setup the DataHandler
	ds = new FileDataSource(kFileName);
	dsdh = new DataHandler(ds);		// API TEST

	try {
		if(!ds.equals(dsdh.getDataSource()))
			return Status.failed("DataHandler(DataSource).getDataSource() doesn't match constructor arg");
	}catch(Exception ex) {
		return Status.failed("DataHandler(DataSource).getDataSource() threw " + ex.toString());
	}
	
	if(!ds.getName().equals(dsdh.getName()))
		return Status.failed("DataHandler(DataSource).getName() doesn't match DataSource.getName()");

	if (!ds.getContentType().equals(dsdh.getContentType()))
		return Status.failed("DataHandler(DataSource).getContentType() doesn't match DataSource.getContentType()");

	return Status.passed("DataHandler(DataSource) test succeeded");
}
/**
 *	dataHandlerTest2: test a contructor DataHandler(String, String);
 *
 * @return	Status object
 */
public Status dataHandlerTest2()
{
	objdh = new DataHandler(new TestCommandObject(), "foo/goo");	// API TEST

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
	
	if (!"foo/goo".equals(objdh.getContentType()))
		return Status.failed("DataHandler(Object, String)).getContentType != dh constructor arg");
	
	try {
		OutputStream objos = objdh.getOutputStream();
		if (objos != null) {
			objos.close();
			return Status.failed("DataHandler(Object, String).getOutputStream != null");
		}
	} catch (Exception ex) {
			return Status.failed("DataHandler(Object, String).getOutputStream threw " + ex.toString());
	}

	// Test the returned DataSource	as well
	if (objds.getName() != null)
		return Status.failed("DataHandler(Object, String).getDataSource().getName != null");
	
	if (!"foo/goo".equals(objds.getContentType()))
		return Status.failed("DataHandler(Object, String).getDataSource().getContentType != dh constructor arg");

	try {
		OutputStream objos = objds.getOutputStream();	
		if (objos != null) {
			objos.close();
			return Status.failed("DataHandler(Object, String).getDataSource().getOutputStream != null");
		}
	} catch (Exception ioex) {
			return Status.failed("DataHandler(Object, String).getDataSource().getOutputStream threw " + ioex.toString());
	}

	return Status.passed("DataHandler(Object, String) test succeeded");
}
/**
*	dataHandlerTest3: attempt to construct a DataSource that cannot provide an output stream
*	test for appropriate exception from DataHandler.getOutputStream
*
* @return	Status object
*/	
public Status dataHandlerTest3()
{
	DataSource no_out_ds = new FileDataSource("foo/goo");
	OutputStream ds_os = null;
	Exception refex = null;

	try {
		ds_os = no_out_ds.getOutputStream();
		try {
			ds_os.close();
		} catch (Exception ex) {
		}
		return Status.failed("dataSourceNoOutputTest testing failure: Unable to create unwritable FileDataSource");
	} catch (Exception ex) {
		refex = ex;
	}

	DataHandler no_out_dh = new DataHandler(no_out_ds);	// API TEST
	OutputStream dh_os = null;

	try {
		dh_os = no_out_dh.getOutputStream();
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
	return Status.passed("DataHandler(DataSource) test succeeded");
}
/**
*	dataHandlerTest4: attempt to constrcut a DataSource that cannot provide an input stream
*	test for appropriate exception from DataHandler.getInputStream, writeTo (which copies
*	getInputStream to the provide OutputStream.
*
* @return	Status object
* @throws	IOException	never
*/	
public Status dataHandlerTest4() throws IOException
{
	DataSource no_in_ds = new FileDataSource("missingFile");
	InputStream ds_is = null;
	Exception refex = null;

	try {
		ds_is = no_in_ds.getInputStream();
		try {
			ds_is.close();
		} catch (Exception ex) {
		}
		return Status.failed("dataSourceNoInputTest testing failure: Unable to create ureadable FileDataSource");
	} catch(Exception ex ){
		refex = ex;
	}

	DataHandler no_in_dh = new DataHandler(no_in_ds);	// API TEST
	InputStream dh_is = null;

	try {
		dh_is = no_in_dh.getInputStream();
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

	OutputStream bos = new ByteArrayOutputStream();
	try {
		no_in_dh.writeTo(bos);
		return Status.failed("dataSourceNoInputTest failed: writeTo didn't throw");
	} catch(Exception ex) {
		if (!ex.toString().equals(refex.toString())) 
			return Status.failed("dataSourceNoInputTest failed: writeTo threw " + ex.toString() +
									" not " + refex.toString());
	} finally {
		bos.close();
	}
	return Status.passed("DataHandler(DataSource) test succeeded");
}
/**
* dataHandlerTest5:  Test the DataHandler(URL) constructor. This is just a convenience for
* DataHandler(new URLDataSource(URL)). Since DataHandler's correct use of DataSource
* is validated using FileDataSource, here we just verify that getDataSource produces
* an URLDataSource instance with the correct URL in it. 
*
* @return	Status object
* @throws IOException	never
*/
public Status dataHandlerTest5() throws IOException
{
    String fileURLSpec = "file:test.txt";
    URL url = new URL(fileURLSpec);
    DataHandler dh = new DataHandler(url);	// API TEST

    DataSource ds = dh.getDataSource();
    if (!(ds instanceof URLDataSource))
	return Status.failed("DataHandler(URL).getDataSource: expected a URLDataSource got " + ds.getClass().getName());

    URLDataSource uds = (URLDataSource)ds;

    if (!url.equals(uds.getURL()))
	return Status.failed("DataHandler(url).getDataSource().getURL() != URL");

    return Status.passed("DataHandler(URL) test succeeded");
}
} // end of DataHandler_Test
