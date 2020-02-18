/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.datafactory.v2018_06_01;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * A copy activity sink for a CosmosDB (MongoDB API) database.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", defaultImpl = CosmosDbMongoDbApiSink.class)
@JsonTypeName("CosmosDbMongoDbApiSink")
public class CosmosDbMongoDbApiSink extends CopySink {
    /**
     * Specifies whether the document with same key to be overwritten (upsert)
     * rather than throw exception (insert). The default value is "insert".
     * Type: string (or Expression with resultType string). Type: string (or
     * Expression with resultType string).
     */
    @JsonProperty(value = "writeBehavior")
    private Object writeBehavior;

    /**
     * Get specifies whether the document with same key to be overwritten (upsert) rather than throw exception (insert). The default value is "insert". Type: string (or Expression with resultType string). Type: string (or Expression with resultType string).
     *
     * @return the writeBehavior value
     */
    public Object writeBehavior() {
        return this.writeBehavior;
    }

    /**
     * Set specifies whether the document with same key to be overwritten (upsert) rather than throw exception (insert). The default value is "insert". Type: string (or Expression with resultType string). Type: string (or Expression with resultType string).
     *
     * @param writeBehavior the writeBehavior value to set
     * @return the CosmosDbMongoDbApiSink object itself.
     */
    public CosmosDbMongoDbApiSink withWriteBehavior(Object writeBehavior) {
        this.writeBehavior = writeBehavior;
        return this;
    }

}