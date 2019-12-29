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

package javasoft.sqe.tests.api.jakarta.activation.TestClasses;

import jakarta.activation.*;

/**
 * this class extends ActivationDataFlavor and provides 
 * public versions of protected methods for JCK testing
 */

public class TestActivationDataFlavor extends ActivationDataFlavor {

    public TestActivationDataFlavor(String mimeType,
				    String humanReadableName)
    {
	super(mimeType, humanReadableName);
    }

    // we will only override protected methods:
    @SuppressWarnings("deprecation")
    public String testNormalizeMimeTypeParameter(String parameterName,
						 String parameterValue)
    {
	return super.normalizeMimeTypeParameter(parameterName,parameterValue);
    }

    @SuppressWarnings("deprecation")
    public String testNormalizeMimeType(String mimeType)
    {
	return super.normalizeMimeType(mimeType);
    }
}
