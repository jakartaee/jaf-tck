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
import javasoft.sqe.tests.api.jakarta.activation.TestClasses.TestDCH;

/**
 * Create DataHandler instances, use them to invoke getTransferData() api, if
 * this method returns an object that represents the data to be transferred,
 * then this testcase passes, otherwise it fails.
 */

public class getTransferData_Test
{
private static final String	kFileName = "foo.txt";
private String message = null;

@Test
public void testGetTransferDataTest1() throws IOException
{
    Status s = getTransferDataTest1();
    assertEquals(Status.PASSED, s.getType(), "Status " + s);
}

/*
*/
public Status getTransferDataTest1() throws IOException
{
    DataSource fds = new FileDataSource(kFileName);
    DataHandler dh = new DataHandler(fds);
    String mimeType = fds.getContentType();
    ActivationDataFlavor refdf = new ActivationDataFlavor(mimeType, "who cares?");
    dh.setCommandMap(new TestCommandMap(mimeType));
    
    if (!checkFlavors(dh, mimeType))
	return Status.failed("DataHandler(DataSource).getTransferDataFlavors() : " + message);
	
    Object refObj = TestDCH.getObject();
    Object obj = null;

    try {
	obj = dh.getTransferData(refdf);	// API TEST
    } catch(IOException ex) {
	return Status.failed("DataHandler(DataSource).getTransferData(df) threw UnsupportedDataException");
    } catch (Exception ex) {
	return Status.failed("DataHandler(DataSource).getTransferData(df) threw IOException");
    }

    if (!refObj.equals(obj)) //TestDCH always returns the same object!
	return Status.failed("DataHandler(DataSource).getTransferData() returned wrong object");
    
    ActivationDataFlavor wrongMimeDF = new ActivationDataFlavor("moo/poo", "extraneous human readable name");
    try {
	obj = dh.getTransferData(wrongMimeDF);		// API TEST
	return Status.failed("DataHandler(DataSource).getTransferData(wrongMimeDF) didn't throw");
    } catch(IOException ex) {
    } catch (Exception ex) {
	return Status.failed("DataHandler(DataSource).getTransferData(wrongMimeDF) threw IOException");
    }
    return Status.passed("getTransferData() test succeeded");
}
/*
*/
public Status getTransferDataTest2() throws IOException
{
    DataHandler dh = new DataHandler(new TestCommandObject(), "foo/goo");
    ActivationDataFlavor refdf = new ActivationDataFlavor(new TestCommandObject().getClass(), "foo/goo", "anything goes");
    dh.setCommandMap(new TestCommandMap("foo/goo"));
    if (!checkFlavors(dh, "foo/goo"))
	return Status.failed("DataHandler(Object,String).getTransferDataFlavors() : " + message);
    
    Object refObj = TestDCH.getObject(); 
    // this will fail due to bugtraq ID #4105102
    Object obj = null;

    try {
	obj = dh.getTransferData(refdf);	// API TEST
    } catch(IOException ex) {
	return Status.failed("DataHandler(Object,String).getTransferData(df) threw UnsupportedDataException");
    } catch (Exception ex) {
	return Status.failed("DataHandler(Object,String).getTransferData(df) threw IOException");
    }
     

// this is a bad test, it should never pass 
 //    if (!refObj.equals(obj)) //TestDCH always returns the same object!
// 	return Status.failed("DataHandler(Object,String).getTransferData() returned wrong object");

    ActivationDataFlavor wrongMimeDF = new ActivationDataFlavor("moo/poo", "extraneous human readable name");
    try {
	obj = dh.getTransferData(wrongMimeDF);		// API TEST
	return Status.failed("DataHandler(Object,String).getTransferData(wrongMimeDF) didn't throw");
    } catch(IOException ex) {
    } catch (Exception ex) {
	return Status.failed("DataHandler(Object,String).getTransferData(wrongMimeDF) threw IOException");
    }
    return Status.passed("getTransferData() test succeeded");
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
	System.out.println("dfs.length = " + dfs.length);
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
