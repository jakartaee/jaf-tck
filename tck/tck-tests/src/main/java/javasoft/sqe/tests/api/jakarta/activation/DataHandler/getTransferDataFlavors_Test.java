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
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestCommandMap;
import javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestCommandObject;

/**
 * Use an instance of type DataHandler to invoke getTransferDataFlavors() api,
 * if this method returns DataFlavors in which this data is available then
 * this testcase passes otherwise it fails.
 */

public class getTransferDataFlavors_Test
{
private static final String	kFileName = "foo.txt";
private String message = null;

@Test
public void testGetTransferDataFlavorsTest1() throws IOException
{
    Status s = getTransferDataFlavorsTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

public Status getTransferDataFlavorsTest1()
{
    DataHandler dh = new DataHandler(new TestCommandObject(), "foo/goo");
    dh.setCommandMap(new TestCommandMap("foo/goo"));

    if (!checkFlavors(dh, "foo/goo"))
	return Status.failed("DataHandler. using mapped DataContentHandler "  + message);

    return Status.passed("getTransferDataFlavors() test succeeded");
}
/**
* Test is based upon undocumented relationship:
* Transferrable interface is passed through to DataContentHandler: bug ID 4107946
*
* @return	Status object
* @throws	IOException			never
*/
public Status getTransferDataFlavorsTest2() throws IOException
{
    DataSource fds = new FileDataSource(kFileName);
    DataHandler dh = new DataHandler(fds);
    String mimeType = fds.getContentType();
    ActivationDataFlavor refdf = new ActivationDataFlavor(mimeType, "who cares?");
    dh.setCommandMap(new TestCommandMap(mimeType));

    if (!checkFlavors(dh, mimeType))
	return Status.failed("DataHandler(DataSource).getTransferDataFlavors() : " + message);

    return Status.passed("getTransferDataFlavors() test succeeded");
}
/**
* This test design is based upon undocumented wrapping of a DataContentHandler by the Transferable
* implementaiton in DataHandler. bug ID #4107946
*
* @return	Status object
* @throws	IOException			never
*/
public Status getTransferDataFlavorsTest3() throws IOException
{
    DataHandler dh = new DataHandler(new TestCommandObject(), "foo/goo");
    ActivationDataFlavor refdf = new ActivationDataFlavor("foo/goo", "anything goes");

    dh.setCommandMap(new TestCommandMap("foo/goo"));

    if (!checkFlavors(dh, "foo/goo"))
	return Status.failed("DataHandler(Object,String).getTransferDataFlavors() : " + message);

    return Status.passed("getTransferDataFlavors() test succeeded");
}
// The only way to determine if a dh is using the appropriate DataContentHandler
// is to see if  getTransferDataFlavors returns what we expect from a TestDCH
// the assumption is that some other

private boolean checkFlavors(DataHandler dh, String mimeType)
{
    ActivationDataFlavor dfs[] = dh.getTransferDataFlavors();	// API TEST
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
