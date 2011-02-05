/*
 * JBoss, Home of Professional Open Source
 * Copyright 2008, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.torquebox.rails.metadata;

import java.util.regex.Pattern;

public class RailsApplicationMetaData {

    public RailsApplicationMetaData() {
    }

    public void setVersionSpec(String versionSpec) {
        this.versionSpec = versionSpec;
    }

    public String getVersionSpec() {
        return this.versionSpec;
    }

    public boolean isRails2() {
        return getVersionSpec() != null && Pattern.matches( ".*2\\.[0-9]+\\.[0-9]+\\.*", getVersionSpec() );
    }

    public boolean isRails3() {
        return !isRails2();
    }

    public String toString() {
        return "[RailsApplicationMetaData:\n  version=" + versionSpec + "]";
    }

    private String versionSpec;
}
