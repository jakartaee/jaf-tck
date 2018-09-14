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

package	javasoft.sqe.tests.api.javax.activation.MimeType;

import	java.io.*;
import	java.util.Enumeration;
import	javax.activation.*;
import	com.sun.javatest.*;
import	com.sun.javatest.lib.MultiTest; 

/**
 * Create a MimeType instance, use it to call getParameters() method, if it
 * returns enumerated list of MimeTypeParameterList, then this test passes,
 * otherwise it fails.
 */

public class getParameters_Test extends MultiTest
{

public static void main(String argv[])
{
	getParameters_Test lTest = new getParameters_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status	getParametersTest()
{	
        try {	// create a MimeType object
                MimeType jmt =  new MimeType("text/plain;charset=us-ascii;isoset=iso-9000;abc=xyz");

                if( jmt == null )
                    return Status.failed("Failed: to create MimeType object using constructor");

                MimeTypeParameterList mtpl =  jmt.getParameters();       // API TEST

                if( mtpl == null )
                    return Status.failed("Failed: getParameters() returned null string!");

		Enumeration hlist = mtpl.getNames();

                while( hlist.hasMoreElements() ) {
                       String name = (String)hlist.nextElement();
                       //out.println(name);
                }

        } catch(Exception ex) {
                return Status.failed("getParameters() threw " + ex.toString());
        }

        return Status.passed("getParameters() test succeeded");
}
}
