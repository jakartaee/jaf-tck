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

package	javasoft.sqe.tests.api.jakarta.activation.FileDataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.FileDataSource;
import jakarta.activation.FileTypeMap;
import jakarta.activation.MimetypesFileTypeMap;
import	javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestFileTypeMap;

/**
 * Create an instance of FileDataSource, then use it to call setFileTypeMap()
 * with {FileTypMap|null} parameter, now call getFileTypeMap(), if the set and
 * the get values are the same then this testcsae passes, otherwise it fails.
 */

public class setFileTypeMap_Test
{
private static final String	kFileName = "FDSTestFile.txt";

@Test
public void testSetFileTypeMapTest() throws IOException
{
    Status s = setFileTypeMapTest();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status setFileTypeMapTest()
{
	FileDataSource fdsFromFile = new FileDataSource(new File(kFileName));
	FileDataSource fdsFromFileName = new FileDataSource(kFileName);

	// This test does not attempt ot test a variety of filename-> mimetype mappings. That's
	// done in MimetypesFileTypeMapTest.  What we do here is prove that 
	// FileDataSource is using the specified default FileTypeMap. We do this by adding
	// and override to the default map, and then testing FileDataSource for reflecting that 
	// change (sneaky huh).	

	MimetypesFileTypeMap typeMap = (MimetypesFileTypeMap)FileTypeMap.getDefaultFileTypeMap();
	typeMap.addMimeTypes("foo/goo  txt"); // override default mapping for txt, since we used a .txt filename in creating fds

	String contentType = fdsFromFile.getContentType();
	if (!"foo/goo".equals(contentType))
		return Status.failed("Failed: Not using FileTypeMap.getDefaultFileTypeMap()");

	// See if we can replace the default file type map with another.	
	fdsFromFile.setFileTypeMap(new TestFileTypeMap());		// API TEST
	if(!TestFileTypeMap.getTestType().equals(fdsFromFile.getContentType()))
		return Status.failed("Failed: Not using FileTypeMap specified in setFileTypeMap");

	// See if setFileTypeMap(null) restores operation to use system default type map
	fdsFromFile.setFileTypeMap(null);		// API TEST
	contentType = fdsFromFile.getContentType();
	if (!"foo/goo".equals(contentType))
		return Status.failed("Failed: setFileTypeMap(null) didn't restore system default");

	return Status.passed("setFileTypeMap() test passed");
}

}
