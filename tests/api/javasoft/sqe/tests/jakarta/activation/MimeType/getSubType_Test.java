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

package	javasoft.sqe.tests.api.jakarta.activation.MimeType;

import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import	com.sun.javatest.lib.MultiTest; 

/**
 * Create a MimeType instance, use it to call getSubType() method, if
 * it returns subtype string of this object then this test passes.
 */

public class getSubType_Test extends MultiTest
{

public static void main(String argv[])
{
	getSubType_Test lTest = new getSubType_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status	getSubTypeTest1()
{	
	try {
		MimeType objmt =  new MimeType();

		if( objmt == null )
		    return Status.failed("Failed: to create MimeType object using constructor");

                String subtype =  objmt.getSubType();       // API TEST

                if( subtype == null )
                    return Status.failed("Failed: getSubType() returned null string!");
 
	} catch(Exception ex) {
		return Status.failed("getSubType() threw " + ex.toString());
	}

	return Status.passed("getSubType(void) test succeeded");
}

public Status   getSubTypeTest2()
{
        try {
                MimeType objmt =  new MimeType("text/plain");

                if( objmt == null )
                    return Status.failed("Failed: to create MimeType object using constructor");

                String subtype =  objmt.getSubType();       // API TEST

                if( subtype == null )
                    return Status.failed("Failed: getSubType() returned null string!");

        } catch(Exception ex) {
                return Status.failed("getSubType() threw " + ex.toString());
        }

        return Status.passed("getSubType(void) test succeeded");
}

}
