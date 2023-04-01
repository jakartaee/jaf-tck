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

package javasoft.sqe.tests.jakarta.activation.FileDataSource;

import	java.io.*;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import	jakarta.activation.*;
import	com.sun.javatest.*;
import com.sun.javatest.lib.MultiTest; 
import javasoft.sqe.tests.jakarta.activation.TestClasses.TestFileTypeMap;

/**
 * Create an instance of type FileDataSource, if successfull
 * then this testcase passes, otherwise it fails.
 */

public class FileDataSource_Test extends MultiTest
{
private static final String	kFileName = "FDSTestFile.txt";

public static void main(String argv[])
{
	FileDataSource_Test lTest = new FileDataSource_Test();
	Status lStatus = lTest.run(argv, new PrintWriter(System.err, true), new PrintWriter(System.out, true));
	lStatus.exit();
}

public void init()
{

}

public Status FileDataSourceTest1()
{
	FileDataSource fdsFromFile = new FileDataSource(new File(kFileName));		// API TEST
	FileDataSource fdsFromFileName = new FileDataSource(kFileName);		// API TEST
	FileDataSource fdsFromPath = new FileDataSource(Paths.get(kFileName));		// API TEST
	if (!kFileName.equals(fdsFromFileName.getName()))
		return Status.failed("FileDataSource(fileName) nameTest failed");
	if (!kFileName.equals(fdsFromFile.getName()))
		return Status.failed("FileDataSource(File) nameTest failed");
	if (!kFileName.equals(fdsFromPath.getName()))
		return Status.failed("FileDataSource(Path) nameTest failed");

	return Status.passed("FileDataSource() test passed");
}

public Status FileDataSourceTest2()
{
	InputStream is = null;
	FileDataSource lSource = new FileDataSource("MissingFile");	// API TEST

	try {
		is = lSource.getInputStream();
		is.close();
	} catch (FileNotFoundException | NoSuchFileException fnfex) {
		return Status.passed("FileDataSource() no input test passed");
	} catch (Exception ex) {
		return Status.failed("FileDataSource() no input test failed: unexpected exception " + ex.toString());
	}

	return Status.failed("FileDataSource() no input test failed: getInputStream() didn't throw");
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
