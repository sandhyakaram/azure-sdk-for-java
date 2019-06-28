/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.monitor.v2018_04_16;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.microsoft.rest.serializer.JsonFlatten;

/**
 * The log search rule resource for patch operations.
 */
@JsonFlatten
public class LogSearchRuleResourcePatch {
    /**
     * Resource tags.
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /**
     * The flag which indicates whether the Log Search rule is enabled. Value
     * should be true or false. Possible values include: 'true', 'false'.
     */
    @JsonProperty(value = "properties.enabled")
    private Enabled enabled;

    /**
     * Get resource tags.
     *
     * @return the tags value
     */
    public Map<String, String> tags() {
        return this.tags;
    }

    /**
     * Set resource tags.
     *
     * @param tags the tags value to set
     * @return the LogSearchRuleResourcePatch object itself.
     */
    public LogSearchRuleResourcePatch withTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the flag which indicates whether the Log Search rule is enabled. Value should be true or false. Possible values include: 'true', 'false'.
     *
     * @return the enabled value
     */
    public Enabled enabled() {
        return this.enabled;
    }

    /**
     * Set the flag which indicates whether the Log Search rule is enabled. Value should be true or false. Possible values include: 'true', 'false'.
     *
     * @param enabled the enabled value to set
     * @return the LogSearchRuleResourcePatch object itself.
     */
    public LogSearchRuleResourcePatch withEnabled(Enabled enabled) {
        this.enabled = enabled;
        return this;
    }

}
