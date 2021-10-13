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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.FileDataSource;

/**
 * Create an instance of type FileDataSource, if successfull
 * then this testcase passes, otherwise it fails.
 */

public class FileDataSource_Test
{
private static final String	kFileName = "FDSTestFile.txt";

@Test
public void testFileDataSourceTest1() throws IOException
{
    Status s = FileDataSourceTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status FileDataSourceTest1()
{
	FileDataSource fdsFromFile = new FileDataSource(new File(kFileName));		// API TEST
	FileDataSource fdsFromFileName = new FileDataSource(kFileName);		// API TEST
	if (!kFileName.equals(fdsFromFileName.getName()))
		return Status.failed("FileDataSource(fileName) nameTest failed");
	if (!kFileName.equals(fdsFromFile.getName()))
		return Status.failed("FileDataSource(File) nameTest failed");

	return Status.passed("FileDataSource() test passed");
}

@Test
public void testFileDataSourceTest2() throws IOException
{
    Status s = FileDataSourceTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status FileDataSourceTest2()
{
	InputStream is = null;
	FileDataSource lSource = new FileDataSource("MissingFile");	// API TEST

	try {
		is = lSource.getInputStream();
		is.close();
	} catch (FileNotFoundException fnfex) {
		return Status.passed("FileDataSource() no input test passed");
	} catch (Exception ex) {
		return Status.failed("FileDataSource() no input test failed: unexpected exception " + ex.toString());
	}

	return Status.failed("FileDataSource() no input test failed: getInputStream() didn't throw");
}

@Test
public void testFileDataSourceTest3() throws IOException
{
    Status s = FileDataSourceTest3();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status FileDataSourceTest3() throws IOException
{
	File lFile = new File("bad/name");

	// lets make sure this worked:
	try {
	    OutputStream os = new FileOutputStream(lFile);
	    os.close();
	    return Status.failed("no output test testing failure: unable to prevent output");
	} catch(IOException ioex) {
	}

	OutputStream os = null;
	FileDataSource fds = new FileDataSource(lFile); 	// API TEST

	try {
		os = fds.getOutputStream();
	} catch (IOException ioex) {
		return Status.passed("FileDataSource() no output test passed");
	} catch (Exception ex) {
		return Status.failed("FileDataSource() no output test failed:  unexpected exception " + ex.toString());
	}
	os.close();

	return Status.failed("FileDataSource() no output test failed: getOutputStream didn't throw");
}

}
