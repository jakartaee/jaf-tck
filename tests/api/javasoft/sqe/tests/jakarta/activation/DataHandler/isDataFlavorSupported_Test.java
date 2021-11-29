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

/**
 * Using an instance of DataHandler object, invoke isDataFlavorSupported()
 * api, with a specified data flavors if it returns a boolean value then
 * this testcase passes.
 */

public class isDataFlavorSupported_Test extends MultiTest
{
private static final String	kFileName = "foo.txt";

public static void main(String argv[])
{
	isDataFlavorSupported_Test lTest = new isDataFlavorSupported_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status isDataFlavorSupportedTest()
{	
    DataSource fds = new FileDataSource(kFileName);
    DataHandler dh1 = new DataHandler(fds);
    String mimeType = fds.getContentType();
    ActivationDataFlavor refdf = new ActivationDataFlavor(mimeType, "who cares?");
    dh1.setCommandMap(new TestCommandMap(mimeType));
    
    ActivationDataFlavor wrongMimeDF = new ActivationDataFlavor("moo/poo", "extraneous human readable name");

    if (!dh1.isDataFlavorSupported(refdf))	// API TEST
	return Status.failed("DataHandler(DataSource).isDataSupported(df) unexpected false");
	
    if (dh1.isDataFlavorSupported(wrongMimeDF))		// API TEST
	return Status.failed("DataHandler(DataSource).isDataSupported(wrongMimeDF) unexpected true");
	
    DataHandler dh2 = new DataHandler(new TestCommandObject(), "foo/goo");
    refdf = new ActivationDataFlavor("foo/goo", "anything goes");
    
    dh2.setCommandMap(new TestCommandMap("foo/goo"));
    
    wrongMimeDF = new ActivationDataFlavor("moo/poo", "extraneous human readable name");

    if (!dh2.isDataFlavorSupported(refdf))	// API TEST
	return Status.failed("DataHandler(Object,String).isDataSupported(df) unexpected false");
	
    if (dh2.isDataFlavorSupported(wrongMimeDF))		// API TEST
	return Status.failed("DataHandler(Object,String).isDataSupported(wrongMimeDF) unexpected true");

    return Status.passed("isDataFlavorSupported() test succeeded");
}

}
