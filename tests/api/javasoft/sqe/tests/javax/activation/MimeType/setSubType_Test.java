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

package javasoft.sqe.tests.api.javax.activation.MimeType;

import	java.io.*;
import	javax.activation.*;
import	com.sun.javatest.*;
import	com.sun.javatest.lib.MultiTest; 

/**
 * Create a MimeType instance, use it to call setSubType() method
 * with subtype string parameter, then call getSubType(), if set/get
 * value is equal, then this test passes otherwise it fails.
 */

public class setSubType_Test extends MultiTest
{
public static void main(String argv[])
{
	setSubType_Test lTest = new setSubType_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status setSubTypeTest()
{
	try {	// create MimeType object
		MimeType objmt =  new MimeType();

		if( objmt == null )
		    return Status.failed("Failed: to create MimeType object using constructor");

                objmt.setSubType("html");       // API TEST

                if( !(objmt.getSubType()).equals("html") )
                    return Status.failed("Failed: setSubType(string) returned null string!");

                objmt.setSubType("");       // API TEST

                if( !(objmt.getSubType()).equals("") )
                    return Status.failed("Failed: setSubType(string) returned null string!");
 
	} catch(Exception ex) {
		ex.printStackTrace();
		return Status.failed("setSubType() threw " + ex.toString());
	}

	return Status.passed("setSubType(string) test succeeded");
}

}
