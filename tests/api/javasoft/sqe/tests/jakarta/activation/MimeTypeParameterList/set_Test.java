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

package javasoft.sqe.tests.jakarta.activation.MimeTypeParameterList;

import	java.beans.*;
import	java.io.*;
import	jakarta.activation.*;
import	java.net.*;
import	com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 
import javasoft.sqe.tests.jakarta.activation.TestClasses.*;

/** TEST: set(string name, string value); <p>
 *
 * Create an instance of MimeTypeParameterList, then use it to call set() method,
 * with name/value parameters, if it returns a the expected value for given name,
 * then this testcase passes, otherwise it fails.
 */

public class set_Test extends MultiTest
{
public static void main(String argv[])
{
	set_Test lTest = new set_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status setTest()
{
        try {
              MimeTypeParameterList mtpl =  new MimeTypeParameterList(";charset=us-ascii;abc=xyz");

              if( mtpl == null )
                  return Status.failed("Failed: to create MimeTypeParameterList object");

	      mtpl.set("abc", "foo");	// API TEST

	      if( !(mtpl.get("abc")).equals("foo") )
		  return Status.failed("set(string,string) failed to set expected value.");

	      mtpl.set("jaf", "test");   // API TEST

	      if( !(mtpl.get("jaf")).equals("test") )
                  return Status.failed("set(string,string) failed to set expected value.");

        } catch(Exception ex) {
		ex.printStackTrace();
                return Status.failed("set() threw " + ex.toString());
        }

	return Status.passed("set(string,string) test succeeded");
}

}
