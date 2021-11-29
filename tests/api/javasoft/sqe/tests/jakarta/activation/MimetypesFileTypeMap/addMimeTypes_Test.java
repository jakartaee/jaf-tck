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

package javasoft.sqe.tests.jakarta.activation.MimetypesFileTypeMap;

import	java.io.*;
import	jakarta.activation.*;
import	com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 

/**
 * Create an instance of MimetypesFileTypeMap then call addMimeTypes() method
 * with mimetype string, if this operation is successfull then this test passes,
 * otherwise it fails.
 */

public class addMimeTypes_Test extends MultiTest
{
private final static String kRefFilename = "test.txt";
private MimetypesFileTypeMap typesMap = null;
private String message = null;
private String refType = null;

public static void main(String argv[])
{
	addMimeTypes_Test lTest = new addMimeTypes_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

private boolean setUpReference()
{
	typesMap = new MimetypesFileTypeMap();
	refType = validateType(kRefFilename, null);
	return (message == null);
}

private String validateType(String fName, String expectedType)
{
	message = null;
	String res1 = typesMap.getContentType(fName);
	
	if (res1 == null) {
		message = "getContentType(" + fName + ") returned null";
		return res1;
	}

	if (expectedType == null) {
		try {
			MimeType mType = new MimeType(res1);
		} catch(MimeTypeParseException mtex) {
			message = "getContentType(" + fName +") produced invalid mimeType: " + res1;
			return res1;
		}
	} else {
		if (!expectedType.equals(res1)) {
			message = "getContentType(" + fName +") produced invalid mimeType: " + res1;
			return res1;
		}
	}

	String res2 = typesMap.getContentType(new File(fName));
	if(!res1.equals(res2)) {
		message = "getContentType(File) returned " + res2 + " should be " + res1;
		return res1;
	}
	return res1;
}

public Status addMimeTypesTest()
{
	if (!setUpReference())
		return Status.failed(message);

	// add a mapping with a syntax error.  should be a no-op
	typesMap.addMimeTypes("&*^\\_");		// API TEST

	// add a mapping that won't override the one we just tried	
	typesMap.addMimeTypes("foo/goo  text");		// API TEST

	String resType = validateType("test.txt", refType);
	if (message != null)
		return Status.failed("addMimeTypes affected unrelated mapping" + message);
		
	// see if the added mapping is working
	resType = validateType("test.text", "foo/goo"); // result should match  added mime type.
	if (message != null)
		return Status.failed("getContentType didn't reflect addMimeTypes: " + message);
		
	// add a mapping that will override the original
	typesMap.addMimeTypes("foo/goo txt");		// API TEST

	resType = validateType("test.txt", "foo/goo");
	if (message != null)
		return Status.failed("addMimeTypes didn't override previous mapping " + message);
		
	return Status.passed("addMimeTypes() test succeeded");
}

}
