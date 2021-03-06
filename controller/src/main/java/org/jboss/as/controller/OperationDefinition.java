/*
 *
 *  * JBoss, Home of Professional Open Source.
 *  * Copyright 2012, Red Hat, Inc., and individual contributors
 *  * as indicated by the @author tags. See the copyright.txt file in the
 *  * distribution for a full listing of individual contributors.
 *  *
 *  * This is free software; you can redistribute it and/or modify it
 *  * under the terms of the GNU Lesser General Public License as
 *  * published by the Free Software Foundation; either version 2.1 of
 *  * the License, or (at your option) any later version.
 *  *
 *  * This software is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *  * Lesser General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU Lesser General Public
 *  * License along with this software; if not, write to the Free
 *  * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 *  * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 */

package org.jboss.as.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import org.jboss.as.controller.access.management.AccessConstraintDefinition;
import org.jboss.as.controller.descriptions.DescriptionProvider;
import org.jboss.as.controller.registry.OperationEntry;
import org.jboss.dmr.ModelType;

/**
 * Defining characteristics of operation in a {@link org.jboss.as.controller.registry.Resource}
 *
 * @author <a href="mailto:tomaz.cerar@redhat.com">Tomaz Cerar</a>
 */
public abstract class OperationDefinition {
    protected final String name;
    protected final OperationEntry.EntryType entryType;
    protected final EnumSet<OperationEntry.Flag> flags;
    protected final AttributeDefinition[] parameters;
    protected final ModelType replyType;
    protected final ModelType replyValueType;
    protected final boolean replyAllowNull;
    protected final DeprecationData deprecationData;
    protected final AttributeDefinition[] replyParameters;
    protected final List<AccessConstraintDefinition> accessConstraints;


    protected OperationDefinition(String name,
                               OperationEntry.EntryType entryType,
                               EnumSet<OperationEntry.Flag> flags,
                               final ModelType replyType,
                               final ModelType replyValueType,
                               final boolean replyAllowNull,
                               final DeprecationData deprecationData,
                               AttributeDefinition[] replyParameters,
                               AttributeDefinition[] parameters,
                               AccessConstraintDefinition... accessConstraints
    ) {
        this.name = name;
        this.entryType = entryType;
        this.flags = flags;
        this.parameters = parameters;
        this.replyType = replyType;
        this.replyValueType = replyValueType;
        this.replyAllowNull = replyAllowNull;
        this.deprecationData = deprecationData;
        this.replyParameters = replyParameters;
        if (accessConstraints == null) {
            this.accessConstraints = Collections.<AccessConstraintDefinition>emptyList();
        } else {
            this.accessConstraints = Collections.unmodifiableList(Arrays.asList(accessConstraints));
        }
    }

    public String getName() {
        return name;
    }

    public OperationEntry.EntryType getEntryType() {
        return entryType;
    }

    public EnumSet<OperationEntry.Flag> getFlags() {
        return flags;
    }

    public AttributeDefinition[] getParameters() {
        return parameters;
    }

    public ModelType getReplyType() {
        return replyType;
    }

    /**
     * Only required if the reply type is some form of collection.
     */
    public ModelType getReplyValueType() {
        return replyValueType;
    }

    public List<AccessConstraintDefinition> getAccessConstraints() {
        return accessConstraints;
    }

    public abstract DescriptionProvider getDescriptionProvider();
}
