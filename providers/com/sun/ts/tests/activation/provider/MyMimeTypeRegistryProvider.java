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

package com.sun.ts.tests.activation.provider;

import java.io.IOException;
import java.io.InputStream;

import jakarta.activation.MimeTypeEntry;
import jakarta.activation.MimeTypeRegistry;
import jakarta.activation.spi.MimeTypeRegistryProvider;

public class MyMimeTypeRegistryProvider implements MimeTypeRegistryProvider {

    private static final String MIME_TYPES = "MIMEType/Pluggability_Test";

    @Override
    public MimeTypeRegistry getByFileName(String arg0) throws IOException {
        return new MyMimeTypeRegistry();
    }

    @Override
    public MimeTypeRegistry getByInputStream(InputStream arg0) throws IOException {
        return null;
    }

    @Override
    public MimeTypeRegistry getInMemory() {
        return null;
    }

    private static class MyMimeTypeRegistry implements MimeTypeRegistry {
        @Override
        public void appendToRegistry(String paramString) {}
        @Override
        public MimeTypeEntry getMimeTypeEntry(String paramString) {
            return null;
        }
        @Override
        public String getMIMETypeString(String file_ext) {
            return MIME_TYPES;
        }
    }
}
