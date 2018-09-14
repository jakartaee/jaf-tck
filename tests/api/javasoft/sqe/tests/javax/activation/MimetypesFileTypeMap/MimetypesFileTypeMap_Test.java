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

package	javasoft.sqe.tests.api.javax.activation.MimetypesFileTypeMap;

import	java.io.*;
import	java.nio.charset.StandardCharsets;
import	javax.activation.*;
import	com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 

/**
 Test: MimetypesFileTypeMap() ; default constructor  <p>
 Test: MimetypesFileTypeMap(String) ; specify a mimetypes file to append to the registry. <p>
 Test: MimetypesFileTypeMap(InputStream) ; specify a mimetypes file as an InputStream <p>

 If we successfully can create objects of MimetypesFileTypeMap using the above constructors,
 then this testcase passes, otherwise it fails.
*/

public class MimetypesFileTypeMap_Test extends MultiTest
{
private final static String kRefFilename = "test.txt";
private final static String kAltFilename = "test.text";
private final static String kPrependFilename = "testMimetype";

// provide different mappings for system defined file extentions to prove 
// overriding of system mimetypes. Also shows that last one wins

private static String testMimetypes[] = { 
	"foo/goo		txt text",
	"hoo/moo		txt"
};

private MimetypesFileTypeMap typesMap = null;
private File testMtypesFile = null;
private String message = null;
private String refType = null;

public static void main(String argv[])
{
	MimetypesFileTypeMap_Test lTest = new MimetypesFileTypeMap_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

private boolean setUpReference()
{
	typesMap = new MimetypesFileTypeMap();		// API TEST
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

private void setUpPrependFile() throws IOException
{
	// The easiest way to make sure that the file is available and has the correct content
	// is open it and stick the content in it:
	testMtypesFile = new File(kPrependFilename);
	FileOutputStream os = new FileOutputStream(testMtypesFile);
	
	for (int i = 0; i < testMimetypes.length; i++)
	{
		byte[] bytes = testMimetypes[i].getBytes(StandardCharsets.US_ASCII);
		os.write(bytes); os.write('\n');
	}
	os.close();
}

public Status MimetypesFileTypeMapTest1()
{
	if (!setUpReference())
		return Status.failed(message);
		
	// add a mapping with a syntax error.  should be a no-op
	typesMap.addMimeTypes("&*^\\_");
	
	// add a mapping that won't override the one we just tried	
	typesMap.addMimeTypes("foo/goo  text");
	String resType = validateType("test.txt", refType);
	if (message != null)
		return Status.failed("addMimeTypes affected unrelated mapping" + message);
		
	// see if the added mapping is working
	resType = validateType("test.text", "foo/goo"); // result should match  added mime type.
	if (message != null)
		return Status.failed("getContentType didn't reflect addMimeTypes: " + message);
		
	// add a mapping that will override the original
	typesMap.addMimeTypes("foo/goo txt");
	resType = validateType("test.txt", "foo/goo");
	if (message != null)
		return Status.failed("addMimeTypes didn't override previous mapping " + message);
		
	return Status.passed("MimetypesFileTypeMap() test succeeded");
}

public Status MimetypesFileTypeMapTest2() throws IOException
{
	if (!setUpReference())
		return Status.failed(message);
		
	// we need an additonal reference result that won't be affected by the prepended file
	String altRefType = validateType("test.html", null);

	setUpPrependFile();

	typesMap = new MimetypesFileTypeMap(kPrependFilename);		// API TEST
	
	// add a mapping with a syntax error.  should be a no-op
	typesMap.addMimeTypes("&*^\\_");
	
	// test something that isn't overridden by testMimeType
	String resType = validateType("foo.html", altRefType);
	if (message != null)
		return Status.failed(message);
		
	// test stuff that testMimetype overrides
	resType = validateType("foo.txt", "hoo/moo");
	if (message != null)
		return Status.failed(message);
	resType = validateType("foo.text", "foo/goo");
	if (message != null)
		return Status.failed(message);
		
	// use addMimeTypes to override an existing mapping
	typesMap.addMimeTypes("foo/goo  txt");
	resType = validateType("foo.txt", "foo/goo");
	if (message != null)
		return Status.failed(message);		

	return Status.passed("MimetypesFileTypeMap(String) test succeeded");
}

public Status MimetypesFileTypeMapTest3() throws FileNotFoundException, IOException
{
	if (!setUpReference())
		return Status.failed(message);

	setUpPrependFile();
	InputStream is = new FileInputStream(testMtypesFile);

	// we need an additonal reference result that won't be affected by the prepended stream
	String altRefType = validateType("test.html", null);
	
	// create a map with prepended stream:
	typesMap = new MimetypesFileTypeMap(is);	// API TEST

	// Note that the rest is identical with the File test, since the input stream is from the file

	typesMap = new MimetypesFileTypeMap(kPrependFilename);		// API TEST

	// add a mapping with a syntax error.  should be a no-op
	typesMap.addMimeTypes("&*^\\_");

	// test something that isn't overridden by testMimeType
	String resType = validateType("foo.html", altRefType);
	if (message != null)
		return Status.failed(message);

	// test stuff that testMimetype overrides
	resType = validateType("foo.txt", "hoo/moo");
	if (message != null)
		return Status.failed(message);
	resType = validateType("foo.text", "foo/goo");
	if (message != null)
		return Status.failed(message);

	// use addMimeTypes to override an existing mapping
	typesMap.addMimeTypes("foo/goo  txt");
	resType = validateType("foo.txt", "foo/goo");
	if (message != null)
		return Status.failed(message);		

	return Status.passed("MimetypesFileTypeMap(InputStream) test succeeded");
}

}
