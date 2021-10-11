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
import java.util.List;
import java.util.Map;

import jakarta.activation.MailcapRegistry;
import jakarta.activation.spi.MailcapRegistryProvider;

public class MyMailcapRegistryProvider implements MailcapRegistryProvider {
    
    private static final String[] MIME_TYPES = new String[] {"MIME/Pluggability_Test"};

    @Override
    public MailcapRegistry getByFileName(String arg0) throws IOException {
        return new MyMailcapRegistry();
    }

    @Override
    public MailcapRegistry getByInputStream(InputStream arg0) throws IOException {
        return null;
    }

    @Override
    public MailcapRegistry getInMemory() {
        return null;
    }

    private static class MyMailcapRegistry implements MailcapRegistry {
        @Override
        public void appendToMailcap(String paramString) {        }
        @Override
        public Map<String, List<String>> getMailcapFallbackList(String paramString) {
            return null;
        }
        @Override
        public Map<String, List<String>> getMailcapList(String paramString) {
            return null;
        }
        @Override
        public String[] getMimeTypes() {
            return MIME_TYPES;
        }
        @Override
        public String[] getNativeCommands(String paramString) {
            return null;
        }
    }
}
