/*
 * Copyright (c) 1997, 2019 Oracle and/or its affiliates. All rights reserved.
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

package	javasoft.sqe.tests.api.jakarta.activation.TestClasses;

import	java.io.*;
import  java.io.IOException;
import	jakarta.activation.*;

/** Test DCH that does nothing. Just provides an object 
 */

public class TestDCH implements DataContentHandler {

    private ActivationDataFlavor df = null;
    private static Object obj = null;

    public static String getDefaultMimeType() {
	return "test/test";
    }

    public static String getDataFlavorName() {
	return "madeInTestDCH";
    }

    public static Object getObject()
    {
	if (obj == null)
	    obj = new TestCommandObject();

	return obj;
    }

    public TestDCH(String mimeType)
    {
	this.df = new ActivationDataFlavor(mimeType, getDataFlavorName());
    }

    public TestDCH()
    {
	this(getDefaultMimeType());
    }

    public ActivationDataFlavor[] getTransferDataFlavors()
    {
	ActivationDataFlavor dfs [] = new ActivationDataFlavor[1];
	dfs[0] = df;
	return dfs;
    }

    public Object getTransferData(ActivationDataFlavor df, DataSource ds) throws IOException {
	if (!this.df.isMimeTypeEqual(df.getMimeType()))
	    throw new IOException("Unsupported Flavor: " + df);

	return getObject();
    }
    /**
     * Return an object representing the data in its most preferred form.
     * Generally this will be the form described by the first
     * ActivationDataFlavor returned by the getTransferDataFlavors method.
     */

    public Object getContent(DataSource ds) throws IOException
    {
	return obj;
    }
    /**
     * Convert the object to a byte stream of the specified MIME type
     * and write it to the output stream.
     */

    public void writeTo(Object obj, String mimeType, OutputStream os ) throws IOException {
	// tests not counting on this doing anything for now.
    }
    // methods of TestDCH  (testing convenience)

    public String getMimeType()
    {
	return df.getMimeType();
    }
}
