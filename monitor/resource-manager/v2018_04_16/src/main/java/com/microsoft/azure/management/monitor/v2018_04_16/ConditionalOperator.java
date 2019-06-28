/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.monitor.v2018_04_16;

import java.util.Collection;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.microsoft.rest.ExpandableStringEnum;

/**
 * Defines values for ConditionalOperator.
 */
public final class ConditionalOperator extends ExpandableStringEnum<ConditionalOperator> {
    /** Static value GreaterThan for ConditionalOperator. */
    public static final ConditionalOperator GREATER_THAN = fromString("GreaterThan");

    /** Static value LessThan for ConditionalOperator. */
    public static final ConditionalOperator LESS_THAN = fromString("LessThan");

    /** Static value Equal for ConditionalOperator. */
    public static final ConditionalOperator EQUAL = fromString("Equal");

    /**
     * Creates or finds a ConditionalOperator from its string representation.
     * @param name a name to look for
     * @return the corresponding ConditionalOperator
     */
    @JsonCreator
    public static ConditionalOperator fromString(String name) {
        return fromString(name, ConditionalOperator.class);
    }

    /**
     * @return known ConditionalOperator values
     */
    public static Collection<ConditionalOperator> values() {
        return values(ConditionalOperator.class);
    }
}
