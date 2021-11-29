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
 * Create a MimeType instance, use it to call getPrimaryType() method, if it
 * returns a primary (string) type of this object, then this test passes,
 * otherwise it fails.
 */

public class getPrimaryType_Test extends MultiTest
{
public static void main(String argv[])
{
	getPrimaryType_Test lTest = new getPrimaryType_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status	getPrimaryTypeTest1()
{	
	try {
		MimeType objmt =  new MimeType("text/plain");
		if( objmt == null )
		    return Status.failed("Failed: to create MimeType object using constructor");

                String primetype =  objmt.getPrimaryType();       // API TEST

                if( primetype == null )
                    return Status.failed("Failed: getPrimaryType() returned null string!");
 
	} catch(Exception ex) {
		return Status.failed("getPrimaryType() threw " + ex.toString());
	}

	return Status.passed("getPrimaryType(void) test succeeded");
}

public Status   getPrimaryTypeTest2()
{
        try {
                MimeType objmt =  new MimeType();
                if( objmt == null )
                    return Status.failed("Failed: to create MimeType object using constructor");

                String primetype =  objmt.getPrimaryType();       // API TEST

                if( primetype != null )
                    return Status.passed("getPrimaryType() test succeeded");

        } catch(Exception ex) {
                return Status.failed("getPrimaryType() threw " + ex.toString());
        }

        return Status.failed("getPrimaryType(void) test failed");
}

}
