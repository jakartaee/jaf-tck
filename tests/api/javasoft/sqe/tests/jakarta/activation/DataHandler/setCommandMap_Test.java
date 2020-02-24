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
import	javasoft.sqe.tests.api.jakarta.activation.TestClasses.*;

/**
 * Create instances of DataHandler, then use invoke  setCommandMap() api, with
 * various CommandMap object parameters. If  this operation is successfull
 * then this testcase passes, otherwise it fails.
 */

public class setCommandMap_Test extends MultiTest
{
private static final String	kFileName = "foo.txt";
private String message = null;

public static void main(String argv[])
{
	setCommandMap_Test lTest = new setCommandMap_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status setCommandMapTest()
{
	DataHandler dh1 = new DataHandler(new TestCommandObject(), "foo/plain");

	// Test setting an alternative command map and then clearing it.  We won't actually
	// test the set state here, that's for another test.

	dh1.setCommandMap(null);			// API TEST
	dh1.setCommandMap(new TestCommandMap(null));	// API TEST

	if( CommandMap.getDefaultCommandMap() == null )
	    return Status.failed("default CommandMap is null");

	FileDataSource fds = new FileDataSource(kFileName);
	DataHandler dh2 = new DataHandler(fds);
	TestCommandMap tcm = new TestCommandMap(fds.getContentType());

	dh2.setCommandMap(tcm);		// API TEST

        if( CommandMap.getDefaultCommandMap() == null )
            return Status.failed("default CommandMap is null");                

        String mimeType = fds.getContentType();
        ActivationDataFlavor refdf = new ActivationDataFlavor(mimeType, "who cares?");
        dh2.setCommandMap(new TestCommandMap(mimeType));	// API TEST

        if (!checkFlavors(dh2, mimeType))
            return Status.failed("DataHandler(DataSource).getTransferDataFlavors() : " + message);

        DataHandler dh3 = new DataHandler(new TestCommandObject(), "foo/goo");
        dh3.setCommandMap(new TestCommandMap("foo/goo"));	// API TEST

        if (!checkFlavors(dh3, "foo/goo"))
	     return Status.failed("DataHandler. using mapped DataContentHandler "  + message);

        return Status.passed("setCommandMap() test succeeded");
}
// The only way to determine if a dh is using the appropriate DataContentHandler
// is to see if  getTransferDataFlavors returns what we expect from a TestDCH
// the assumption is that some other 

private boolean checkFlavors(DataHandler dh, String mimeType)
{
    ActivationDataFlavor dfs[] = dh.getTransferDataFlavors();

    if (dfs == null) {
	message = "getTransferDataFlavors() returned null";
	return false;
    }
    if (dfs.length != 1){
	message = "getTransferDataFlavors() has wrong number of elements";
	return false;
    }
    ActivationDataFlavor df = dfs[0];

    if (!df.isMimeTypeEqual(mimeType)){
	message = "getTransferDataFlavors() contains wrong flavor: " + df.getMimeType();
	return false;
    }
    return true;
}

}
