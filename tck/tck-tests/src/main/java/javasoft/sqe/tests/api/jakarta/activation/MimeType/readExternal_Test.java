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

package javasoft.sqe.tests.api.jakarta.activation.MimeType;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.MimeType; 

/** Test: readExternal(ObjectInput); <p>
 *
 *   The object implements the readExternal method to restore its
 *   contents by calling the methods of DataInput for primitive types
 *   and readObject for objects, strings and arrays. We first write out
 *   a string object using writeExternal() method and then read it back, if
 *   both string values match then this testcase passes, otherwise it fails.
 */

public class readExternal_Test
{
@Test
public void testReadExternalTest()
{
    Status s = readExternalTest();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status readExternalTest()
{
	String mime_text = "TEXT/plain; charset=us-ascii";

	try {	// create MimeType object
	    MimeType objmt =  new MimeType(mime_text);
	    
	    if( objmt == null )
		return Status.failed("Failed: to create MimeType object using a constructor");
	    
	    // setup a stream to write out the MT
	    FileOutputStream fos = new FileOutputStream("junkread");

	    if(fos == null)
		return Status.failed("Failed to create FileOutputStream object.");
	    
	    ObjectOutputStream p = new ObjectOutputStream(fos);
	    if(p == null)
		return Status.failed("Failed to create FileOutputStream object.");
	    
	    // write out the MT and close
	    objmt.writeExternal(p);
	    fos.flush();
	    fos.close();

	    FileInputStream fis = new FileInputStream("junkread");

	    if( fis == null )
		return Status.failed("Failed to create FileInputStream object.");

	    ObjectInputStream ips = new ObjectInputStream((InputStream)fis);

	    if( ips == null )
		return Status.failed("Failed to create ObjectInputStream object.");

	    objmt.readExternal((ObjectInput)ips);	// API TEST

	    if(!objmt.match(mime_text))
		return Status.failed("Type externalized doesn't match original type!");

	} catch(Exception ex) {
	    return Status.failed("readExternal(ObjectInput) threw " + ex.toString());
	}
	
	return Status.passed("readExternal(ObjectInput) test succeeded");
}

}
