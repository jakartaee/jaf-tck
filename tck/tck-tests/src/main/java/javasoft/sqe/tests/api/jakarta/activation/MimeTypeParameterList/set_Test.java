/*
 * Copyright (c) 2021 Oracle and/or its affiliates. All rights reserved.
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

package	javasoft.sqe.tests.api.jakarta.activation.MimeTypeParameterList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.MimeTypeParameterList;

/** TEST: set(string name, string value); <p>
 *
 * Create an instance of MimeTypeParameterList, then use it to call set() method,
 * with name/value parameters, if it returns a the expected value for given name,
 * then this testcase passes, otherwise it fails.
 */

public class set_Test
{
@Test
public void testSetTest()
{
    Status s = setTest();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
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
