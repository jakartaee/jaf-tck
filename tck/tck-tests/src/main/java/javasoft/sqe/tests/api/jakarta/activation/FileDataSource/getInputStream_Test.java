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
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.FileDataSource;

/**
 * Create an instance of FileDataSource, use it to invoke getInputStream()
 * method, if it returns an InputStream representing the data then this
 * testcase passes, if it throws an appropriate exception then it fails.
 */

public class getInputStream_Test
{
private static final String	kFileName = "FDSTestFile.txt";
private String message;
private String msgPrefix;

@Test
public void testGetInputStreamTest1()
{
    Status s = getInputStreamTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status getInputStreamTest1()
{
	FileDataSource fdsFromFile = new FileDataSource(new File(kFileName));
	FileDataSource fdsFromFileName = new FileDataSource(kFileName);
        boolean lPassed = true;
        message = "ioTest succeeded";

        msgPrefix = "FileDataSource(File) ";
        if (!testIO(fdsFromFile))
                return Status.failed(msgPrefix + message);

        msgPrefix = "FileDataSource(fileName) ";
        if (!testIO(fdsFromFileName))
                return Status.failed(msgPrefix + message);

        return Status.passed("getInputStream() " + message);
}

@Test
public void testGetInputStreamTest2()
{
    Status s = getInputStreamTest2();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status getInputStreamTest2()
{
        InputStream is = null;
        FileDataSource lSource = new FileDataSource("MissingFile");

        try {
                is = lSource.getInputStream();          // API TEST
                is.close();
        } catch (FileNotFoundException fnfex) {
                return Status.passed("getInputStream() test passed");
        } catch (Exception ex) {
                return Status.failed("Failed: unexpected exception " + ex.toString());
        }

        return Status.failed("Failed: getInputStream() didn't throw");
}

private boolean testIO(FileDataSource fds)
{
	try
	{
		//	write some data
		OutputStream lFileOStream = fds.getOutputStream();
		if(lFileOStream == null) {
			message = "ioTest failed: getOutputStream returned null";
			return false;
		}
		
		byte[] lFileBuffer = { 34, 35, 36, 37 };
		lFileOStream.write(lFileBuffer);
		lFileOStream.flush();
		lFileOStream.close();
		
		//	read the same data
		InputStream lFileIStream = fds.getInputStream();	// API TEST
		lFileIStream.read(lFileBuffer);
		if((lFileBuffer[0] != 34) || (lFileBuffer[1] != 35) || (lFileBuffer[2] != 36) || (lFileBuffer[3] != 37)) {
			message = "ioTest failed: data read from DataSource didn't match what was written";
			return false;
		}
		lFileIStream = null;

	} catch(Exception inException) {
		message = "ioTest failed with Exception: " + inException.toString();
		return false;
	}
	return true;
}

}
