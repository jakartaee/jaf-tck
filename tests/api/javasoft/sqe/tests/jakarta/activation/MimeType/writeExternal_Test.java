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

package javasoft.sqe.tests.api.jakarta.activation.MimeType;

import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import	com.sun.javatest.lib.MultiTest; 

/** Test: writeExternal(ObjectOutput); <p>
 *
 * The object implements the writeExternal method to save its contents
 * by calling the methods of DataOutput for its primitive values or
 * calling the writeObject method of ObjectOutput for objects, strings
 * and arrays. If this invocation is successfull then this test passes.
 */

public class writeExternal_Test extends MultiTest
{
public static void main(String argv[])
{
	writeExternal_Test lTest = new writeExternal_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public Status writeExternalTest()
{
	try {	// create MimeType object
		MimeType objmt =  new MimeType();

		if( objmt == null )
		    return Status.failed("Failed: to create MimeType object using a constructor");

		FileOutputStream fos = new FileOutputStream("junkwrite");

		if( fos == null )
		    return Status.failed("Failed to create FileOutputStream object.");

		ObjectOutputStream oos = new ObjectOutputStream((OutputStream)fos);

                if( oos == null )
                    return Status.failed("Failed to create ObjectOutputStream object.");

		objmt.writeExternal((ObjectOutput)oos);	// API TEST

	} catch(Exception ex) {
		ex.printStackTrace();
		return Status.failed("writeExternal(ObjectOutput) threw " + ex.toString());
	}

	return Status.passed("writeExternal(ObjectOutput) test succeeded");
}

}
