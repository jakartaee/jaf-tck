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

package	javasoft.sqe.tests.api.jakarta.activation.MimeType;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.MimeType; 

/**
 Test: boolean match(MimeType); Determine of the primary and sub type
       of this object is the same as the what is in the given type.
<p>
 Test: boolean match(String); Determine of the primary and sub type of
       this object is the same as the content type described in rawdata.
<p>
 Create a MimeType instance, use it to call match() method with
 mimetype/string parameters, if match returns boolean value then
 this test passes otherwise it fails.
*/

public class match_Test
{
@Test
public void testMatchTest1()
{
    Status s = matchTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status matchTest1()
{	
	try {	// create MimeType objects using constructors
		MimeType defmt = new MimeType();
		MimeType objmt = new MimeType("text", "plain");

		if( objmt == null || defmt == null )
		    return Status.failed("Failed: to create MimeType object using constructor");

		boolean didMatch = objmt.match(objmt);	// API TEST

                if( didMatch == false )
                    return Status.failed("Failed: match(): the primary and sub type different.");

		didMatch = objmt.match(defmt);	// API TEST

		if( didMatch )
		    return Status.failed("Failed: match(): the primary and sub type are same.");
 
	} catch(Exception ex) {
		ex.printStackTrace();
		return Status.failed("match() threw " + ex.toString());
	}

	return Status.passed("match(MimeType) test succeeded");
}

@Test
public void testMatchTest2()
{
    Status s = matchTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status matchTest2()
{
        try {	// create MimeType object using constructor
                MimeType objmt =  new MimeType("text/html");

                if( objmt == null )
                    return Status.failed("Failed: to create MimeType object using constructor");

                if( !objmt.match("text/html") )  	// API TEST
                    return Status.failed("Failed: match(): primary and sub type are same.");

		if( objmt.match("text/plain") )	// API TEST
		    return Status.failed("Failed: match(): primary and sub type different. ");

        } catch(Exception ex) {
		ex.printStackTrace();
                return Status.failed("match() threw " + ex.toString());
        }

        return Status.passed("match(String) test succeeded");
}

}
