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

package javasoft.sqe.tests.jakarta.activation.MimeType;

import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import	com.sun.javatest.lib.MultiTest; 

/**
 * Create a MimeType instance, use it to call setPrimaryType() method
 * with primary type string parameter, then call getPrimaryType(), if
 * set/get values are equal, then this test passes otherwise it fails.
 */

public class setPrimaryType_Test extends MultiTest
{
public static void main(String argv[])
{
	setPrimaryType_Test lTest = new setPrimaryType_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status setPrimaryTypeTest()
{
	try {	// create MimeType object
		MimeType objmt =  new MimeType();

		if( objmt == null )
		    return Status.failed("Failed: to create MimeType object using constructor");

                objmt.setPrimaryType("html");       // API TEST

                if( !(objmt.getPrimaryType()).equals("html") )
                    return Status.failed("Failed: setPrimaryType(String) returned different string.");

                objmt.setPrimaryType("text");       // API TEST

                if( !(objmt.getPrimaryType()).equals("text") )
                    return Status.failed("Failed: setPrimaryType(String) returned different string.");

                objmt.setPrimaryType("");       // API TEST

                if( !(objmt.getPrimaryType()).equals("") )
                    return Status.failed("Failed: setPrimaryType(String) returned different string.");

	} catch(Exception ex) {
		ex.printStackTrace();
		return Status.failed("setPrimaryType(string) threw " + ex.toString());
	}

	return Status.passed("setPrimaryType(string) test succeeded");
}

}
