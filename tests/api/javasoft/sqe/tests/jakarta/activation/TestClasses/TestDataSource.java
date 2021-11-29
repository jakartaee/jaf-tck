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

package javasoft.sqe.tests.jakarta.activation.TestClasses;

import jakarta.activation.*;
import java.io.*;

/**
 * A TestDataSource for the DataHandler tests. It will return a 
 * the passed in byte stream via the getinputstream method.
 */

public class TestDataSource implements DataSource {

    byte byte_array[] = null;
    
    public TestDataSource(byte byte_array[]){
	this.byte_array = byte_array;
    }

    public InputStream getInputStream() throws IOException {
	ByteArrayInputStream bis = new ByteArrayInputStream(byte_array);
	return bis;
	
    }
    public OutputStream getOutputStream() throws IOException {
	return null;
    }
    public String getContentType(){
	return "application/octet-stream";
    }
    public String getName() {
	    return "name";
    }
}
