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

/**
 * Create an instance of MimeTypeParameterList, then use it to call isEmpty() method,
 * if it returns a boolean value for this list, then this testcase passes, otherwise
 * it fails.
 */

public class isEmpty_Test
{
@Test
public void testIsEmptyTest()
{
    Status s = isEmptyTest();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status isEmptyTest()
{
        try {
              MimeTypeParameterList mtpl1 =  new MimeTypeParameterList(";charset=us-ascii;isoset=iso-9000;foo=abc");

              if( mtpl1 == null )
                  return Status.failed("Failed: to create MimeTypeParameterList object.");

	      if( mtpl1.isEmpty() )	// API TEST
		  return Status.failed("isEmpty() returned unexpected false value.");

	      MimeTypeParameterList mtpl2 = new MimeTypeParameterList();

	      if( mtpl2 == null )
                  return Status.failed("Failed: to create MimeTypeParameterList object.");

              if( !mtpl2.isEmpty() )	// API TEST
                  return Status.failed("isEmpty() returned unexpected true value.");

        } catch(Exception ex) {
                return Status.failed("isEmpty() threw " + ex.toString());
        }

	return Status.passed("isEmpty() test succeeded");
}

}
