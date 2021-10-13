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

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.FileDataSource;

/**
 * Create an instance of FileDataSource class and use it to call getName()
 * api, if this method returns non-null object then this testcase passes,
 * otherwise it fails.
 */

public class getName_Test
{
private static final String	kFileName = "FDSTestFile.txt";

@Test
public void testGetNameTest()
{
    Status s = getNameTest();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status getNameTest()
{
	FileDataSource fdsFromFile = new FileDataSource(new File(kFileName));
	FileDataSource fdsFromFileName = new FileDataSource(kFileName);
	String name1 = fdsFromFile.getName();		// API TEST
	String name2 = fdsFromFileName.getName();	// API TEST

	if( name1 == null )
	    return Status.failed("getName() test failed");
	if( name2 == null )
	    return Status.failed("getName() test failed");

	return Status.passed("getName() test passed");
}

}
