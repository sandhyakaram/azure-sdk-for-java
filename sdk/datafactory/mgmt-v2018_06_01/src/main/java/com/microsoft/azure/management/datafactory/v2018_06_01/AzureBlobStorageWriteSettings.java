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
 * Azure blob write settings.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", defaultImpl = AzureBlobStorageWriteSettings.class)
@JsonTypeName("AzureBlobStorageWriteSettings")
public class AzureBlobStorageWriteSettings extends StoreWriteSettings {
    /**
     * Indicates the block size(MB) when writing data to blob. Type: integer
     * (or Expression with resultType integer).
     */
    @JsonProperty(value = "blockSizeInMB")
    private Object blockSizeInMB;

    /**
     * Get indicates the block size(MB) when writing data to blob. Type: integer (or Expression with resultType integer).
     *
     * @return the blockSizeInMB value
     */
    public Object blockSizeInMB() {
        return this.blockSizeInMB;
    }

    /**
     * Set indicates the block size(MB) when writing data to blob. Type: integer (or Expression with resultType integer).
     *
     * @param blockSizeInMB the blockSizeInMB value to set
     * @return the AzureBlobStorageWriteSettings object itself.
     */
    public AzureBlobStorageWriteSettings withBlockSizeInMB(Object blockSizeInMB) {
        this.blockSizeInMB = blockSizeInMB;
        return this;
    }

}