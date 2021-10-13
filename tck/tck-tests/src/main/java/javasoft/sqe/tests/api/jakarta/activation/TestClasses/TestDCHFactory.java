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

package	javasoft.sqe.tests.api.jakarta.activation.TestClasses;

import	jakarta.activation.*;

/** Test uitlity used by DataHandler class.
 */

public class TestDCHFactory implements DataContentHandlerFactory
{
    private String mimeType = null;

    public TestDCHFactory() {
    }

    public TestDCHFactory(String mimeType)
    {
	this.mimeType = mimeType;
    }
    // We allow the factory to make a distinctive TestDCH based upon an optional
    // mimeType constructor argument, to allow tests to prove that 
    // DataHandler.setDataContentHandlerFactory overrides the affect setCommandMap.

    public DataContentHandler createDataContentHandler(String mimeType)
    {
	if (this.mimeType != null)
	    return new TestDCH(this.mimeType);
	else
	    return new TestDCH(mimeType);
    }
}
