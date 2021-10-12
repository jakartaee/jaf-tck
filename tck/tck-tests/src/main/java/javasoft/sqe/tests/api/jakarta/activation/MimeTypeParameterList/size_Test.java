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
 * Create an instance of MimeTypeParameterList, then use it to call size() method,
 * if it returns the number of name-value pairs in this list, then this testcase
 * passes, otherwise it fails.
 */

public class size_Test
{
@Test
public void testSizeTest()
{
    Status s = sizeTest();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status sizeTest()
{
        try {
              MimeTypeParameterList mtpl =  new MimeTypeParameterList(";charset=us-ascii;isoset=iso-9000;foo=abc");

              if( mtpl == null )
                  return Status.failed("Failed: to create MimeTypeParameterList object using constructor");
	      int value = mtpl.size();	// API TEST

	      if( value == 0 )
		  return Status.failed("size() returned unexpected zero value.");

	      MimeTypeParameterList mtpol = new MimeTypeParameterList();

              value = mtpol.size();       // API TEST

              if( value != 0 )
                  return Status.failed("size() returned unexpected non-zero value.");

        } catch(Exception ex) {
                return Status.failed("size() threw " + ex.toString());
        }

	return Status.passed("size() test succeeded");
}

}
