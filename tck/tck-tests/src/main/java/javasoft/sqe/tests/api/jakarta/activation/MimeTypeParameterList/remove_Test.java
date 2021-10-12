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

/** TEST: remove(string); <p>
 *
 * Create an instance of MimeTypeParameterList, then use it to call remove() method
 * with given name string, if it then removes the associated value [we check this by
 * calling get(name)], then this testcase passes, otherwise it fails.
 */

public class remove_Test
{
@Test
public void testRemoveTest()
{
    Status s = removeTest();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status removeTest()
{
        try {
              MimeTypeParameterList mtpl =  new MimeTypeParameterList(";a=b;charset=us-ascii;abc=xyz");

              if( mtpl == null )
                  return Status.failed("Failed: to create MimeTypeParameterList object");

	      mtpl.remove("abc");	// API TEST

	      if( mtpl.get("abc") != null )
		  return Status.failed("remove(string) failed to remove expected value.");

	      mtpl.remove("charset");   // API TEST

	      if( mtpl.get("charset") != null )
                  return Status.failed("remove(string) failed to remove expected value.");

        } catch(Exception ex) {
		ex.printStackTrace();
                return Status.failed("remove(string) threw " + ex.toString());
        }

	return Status.passed("remove(string) test succeeded");
}

}
