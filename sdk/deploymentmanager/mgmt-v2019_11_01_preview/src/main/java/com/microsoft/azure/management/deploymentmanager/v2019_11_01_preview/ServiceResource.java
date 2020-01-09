/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.management.deploymentmanager.v2019_11_01_preview;

import com.microsoft.azure.arm.model.HasInner;
import com.microsoft.azure.management.deploymentmanager.v2019_11_01_preview.implementation.ServiceResourceInner;
import com.microsoft.azure.arm.model.Indexable;
import com.microsoft.azure.arm.model.Refreshable;
import com.microsoft.azure.arm.model.Updatable;
import com.microsoft.azure.arm.model.Appliable;
import com.microsoft.azure.arm.model.Creatable;
import com.microsoft.azure.arm.resources.models.HasManager;
import com.microsoft.azure.management.deploymentmanager.v2019_11_01_preview.implementation.DeploymentManagerManager;
import java.util.Map;

/**
 * Type representing ServiceResource.
 */
public interface ServiceResource extends HasInner<ServiceResourceInner>, Indexable, Refreshable<ServiceResource>, Updatable<ServiceResource.Update>, HasManager<DeploymentManagerManager> {
    /**
     * @return the id value.
     */
    String id();

    /**
     * @return the location value.
     */
    String location();

    /**
     * @return the name value.
     */
    String name();

    /**
     * @return the tags value.
     */
    Map<String, String> tags();

    /**
     * @return the targetLocation value.
     */
    String targetLocation();

    /**
     * @return the targetSubscriptionId value.
     */
    String targetSubscriptionId();

    /**
     * @return the type value.
     */
    String type();

    /**
     * The entirety of the ServiceResource definition.
     */
    interface Definition extends DefinitionStages.Blank, DefinitionStages.WithServiceTopology, DefinitionStages.WithLocation, DefinitionStages.WithTargetLocation, DefinitionStages.WithTargetSubscriptionId, DefinitionStages.WithCreate {
    }

    /**
     * Grouping of ServiceResource definition stages.
     */
    interface DefinitionStages {
        /**
         * The first stage of a ServiceResource definition.
         */
        interface Blank extends WithServiceTopology {
        }

        /**
         * The stage of the serviceresource definition allowing to specify ServiceTopology.
         */
        interface WithServiceTopology {
           /**
            * Specifies resourceGroupName, serviceTopologyName.
            * @param resourceGroupName The name of the resource group. The name is case insensitive
            * @param serviceTopologyName The name of the service topology
            * @return the next definition stage
            */
            WithLocation withExistingServiceTopology(String resourceGroupName, String serviceTopologyName);
        }

        /**
         * The stage of the serviceresource definition allowing to specify Location.
         */
        interface WithLocation {
           /**
            * Specifies location.
            * @param location The geo-location where the resource lives
            * @return the next definition stage
            */
            WithTargetLocation withLocation(String location);
        }

        /**
         * The stage of the serviceresource definition allowing to specify TargetLocation.
         */
        interface WithTargetLocation {
           /**
            * Specifies targetLocation.
            * @param targetLocation The Azure location to which the resources in the service belong to or should be deployed to
            * @return the next definition stage
            */
            WithTargetSubscriptionId withTargetLocation(String targetLocation);
        }

        /**
         * The stage of the serviceresource definition allowing to specify TargetSubscriptionId.
         */
        interface WithTargetSubscriptionId {
           /**
            * Specifies targetSubscriptionId.
            * @param targetSubscriptionId The subscription to which the resources in the service belong to or should be deployed to
            * @return the next definition stage
            */
            WithCreate withTargetSubscriptionId(String targetSubscriptionId);
        }

        /**
         * The stage of the serviceresource definition allowing to specify Tags.
         */
        interface WithTags {
            /**
             * Specifies tags.
             * @param tags Resource tags
             * @return the next definition stage
             */
            WithCreate withTags(Map<String, String> tags);
        }

        /**
         * The stage of the definition which contains all the minimum required inputs for
         * the resource to be created (via {@link WithCreate#create()}), but also allows
         * for any other optional settings to be specified.
         */
        interface WithCreate extends Creatable<ServiceResource>, DefinitionStages.WithTags {
        }
    }
    /**
     * The template for a ServiceResource update operation, containing all the settings that can be modified.
     */
    interface Update extends Appliable<ServiceResource>, UpdateStages.WithTags {
    }

    /**
     * Grouping of ServiceResource update stages.
     */
    interface UpdateStages {
        /**
         * The stage of the serviceresource update allowing to specify Tags.
         */
        interface WithTags {
            /**
             * Specifies tags.
             * @param tags Resource tags
             * @return the next update stage
             */
            Update withTags(Map<String, String> tags);
        }

    }
}
