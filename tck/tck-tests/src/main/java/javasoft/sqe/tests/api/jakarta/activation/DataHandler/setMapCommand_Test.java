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

package	javasoft.sqe.tests.api.jakarta.activation.DataHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import com.sun.javatest.Status;

import jakarta.activation.ActivationDataFlavor;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestCommandMap;
import javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestCommandObject;

/**
 * Create instances of DataHandler, then use invoke  setMapCommand() api, with
 * various CommandMap object parameters. If  this operation is successfull
 * then this testcase passes, otherwise it fails.
 */

public class setMapCommand_Test
{
private static final String	kFileName = "foo.txt";
private String message = null;

@Test
public void testSetCommandMapTest() throws IOException
{
    Status s = setCommandMapTest();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status setCommandMapTest()
{
	DataHandler dh1 = new DataHandler(new TestCommandObject(), "foo/plain");

	// Test setting an alternative command map and then clearing it.  We won't actually
	// test the set state here, that's for another test.

	dh1.setCommandMap(new TestCommandMap(null));		// API TEST
	dh1.setCommandMap(null);				// API TEST

	FileDataSource fds = new FileDataSource(kFileName);
	DataHandler dh2 = new DataHandler(fds);
	TestCommandMap tcm = new TestCommandMap(fds.getContentType());

	dh2.setCommandMap(tcm);		// API TEST

        String mimeType = fds.getContentType();
        ActivationDataFlavor refdf = new ActivationDataFlavor(mimeType, "who cares?");
        dh2.setCommandMap(new TestCommandMap(mimeType));

        if (!checkFlavors(dh2, mimeType))
            return Status.failed("DataHandler(DataSource).getTransferDataFlavors() : " + message);

        DataHandler dh3 = new DataHandler(new TestCommandObject(), "foo/goo");
        dh3.setCommandMap(new TestCommandMap("foo/goo"));	// API TEST

        if (!checkFlavors(dh3, "foo/goo"))
	     return Status.failed("DataHandler. using mapped DataContentHandler "  + message);

        return Status.passed("setCommandMap() test succeeded");
}
// The only way to determine if a dh is using the appropriate DataContentHandler
// is to see if  getTransferDataFlavors returns what we expect from a TestDCH
// the assumption is that some other 

private boolean checkFlavors(DataHandler dh, String mimeType)
{
    ActivationDataFlavor dfs[] = dh.getTransferDataFlavors();
    if (dfs == null) {
	message = "getTransferDataFlavors() returned null";
	return false;
    }
    if (dfs.length != 1){
	message = "getTransferDataFlavors() has wrong number of elements";
	return false;
    }
    ActivationDataFlavor df = dfs[0];
    if (!df.isMimeTypeEqual(mimeType)){
	message = "getTransferDataFlavors() contains wrong flavor: " + df.getMimeType();
	return false;
    }
    return true;
}

}
