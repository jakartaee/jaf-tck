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

package	javasoft.sqe.tests.api.javax.activation.MimeTypeParameterList;

import	java.beans.*;
import	java.io.*;
import  java.util.Enumeration;
import	javax.activation.*;
import	java.net.*;
import	com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 
import	java.awt.datatransfer.*;
import	javasoft.sqe.tests.api.javax.activation.TestClasses.*;

/**
 * Create an instance of MimeTypeParameterList, then use it to call getNames() method,
 * if it returns an enumeration of all the names in this list, then this testcase
 * passes, otherwise it fails.
 */

public class getNames_Test extends MultiTest
{
public static void main(String argv[])
{
	getNames_Test lTest = new getNames_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status getNamesTest()
{
        try {
              MimeTypeParameterList mtpl =  new MimeTypeParameterList(";charset=us-ascii;isoset=iso-9000;foo=abc");

              if( mtpl == null )
                  return Status.failed("Failed: to create MimeTypeParameterList object using constructor");
	      Enumeration names = mtpl.getNames();	// API TEST

	      while ( names.hasMoreElements() ) {
                      String name = (String)names.nextElement();
                      //out.println(name);
	      }

        } catch(Exception ex) {
                return Status.failed("getNames() threw " + ex.toString());
        }

	return Status.passed("getNames() test succeeded");
}

}
