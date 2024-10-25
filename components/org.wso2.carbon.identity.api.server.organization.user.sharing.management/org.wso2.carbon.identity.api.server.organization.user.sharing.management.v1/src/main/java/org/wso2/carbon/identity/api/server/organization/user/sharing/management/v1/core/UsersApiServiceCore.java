/*
 * Copyright (c) 2024, WSO2 LLC. (http://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.core;

import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.model.RoleWithAudience;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.model.UserShareRequestBody;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.model.UserShareRequestBodyOrganizations;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.model.UserShareWithAllRequestBody;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.model.UserSharedOrganizationsResponse;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.model.UserSharedRolesResponse;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.model.UserUnshareRequestBody;
import org.wso2.carbon.identity.api.server.organization.user.sharing.management.v1.model.UserUnshareWithAllRequestBody;
import org.wso2.carbon.identity.organization.management.organization.user.sharing.UserSharingPolicyHandlerServiceImpl;
import org.wso2.carbon.identity.organization.management.organization.user.sharing.models.AbstractUserShareDO;
import org.wso2.carbon.identity.organization.management.organization.user.sharing.models.PolicyBearingOrganization;
import org.wso2.carbon.identity.organization.management.organization.user.sharing.models.SharedRole;
import org.wso2.carbon.identity.organization.management.organization.user.sharing.models.UserCriteria;
import org.wso2.carbon.identity.organization.management.organization.user.sharing.models.UserShareGeneralDO;
import org.wso2.carbon.identity.organization.management.organization.user.sharing.models.UserShareSelectiveDO;

import java.util.ArrayList;
import java.util.List;

/**
 * Core service class for handling user sharing management APIs.
 */
public class UsersApiServiceCore {

    /**
     * Handles sharing a user across specific organizations.
     *
     * @param userShareRequestBody Contains details for user sharing.
     */
    public void shareUser(UserShareRequestBody userShareRequestBody) {

        UserSharingPolicyHandlerServiceImpl userSharingPolicyHandlerService = new UserSharingPolicyHandlerServiceImpl();

        List<String> userIds = userShareRequestBody.getUserCriteria().getUserIds();
        List<UserShareRequestBodyOrganizations> organizations = userShareRequestBody.getOrganizations();


        for(String userId : userIds) {

            for(UserShareRequestBodyOrganizations organization : organizations) {

                UserShareSelectiveDO userShareSelectiveDO = new UserShareSelectiveDO();
                userShareSelectiveDO.setUserId(userId);
                userShareSelectiveDO.setOrganizationId(organization.getOrgId());

                List<SharedRole> sharedRolesList = getSharedRoles(organization);

                userShareSelectiveDO.setRoles(sharedRolesList);

                try {
                    userSharingPolicyHandlerService.propagateSelectiveShare(userShareSelectiveDO);
                } catch (Exception e){
                    //do something
                }


            }
        }

    }

    private static List<SharedRole> getSharedRoles(UserShareRequestBodyOrganizations organization) {

        List<SharedRole> sharedRolesList = new ArrayList<>();

        List<RoleWithAudience> roles = organization.getRoles();
        for(RoleWithAudience role : roles) {
            SharedRole sharedRole = new SharedRole();
            sharedRole.setRoleName(role.getDisplayName());
            sharedRole.setAudienceName(role.getAudience().getDisplay());
            sharedRole.setAudienceType(role.getAudience().getType());
            sharedRolesList.add(sharedRole);
        }
        return sharedRolesList;
    }

    /**
     * Handles sharing a user across all organizations.
     *
     * @param userShareWithAllRequestBody Contains details for sharing users with all organizations.
     */
    public void shareUserWithAll(UserShareWithAllRequestBody userShareWithAllRequestBody) {
        // Core logic for sharing the user with all organizations
    }

    /**
     * Handles unsharing a user from specific organizations.
     *
     * @param userUnshareRequestBody Contains details for user unsharing.
     */
    public void unshareUser(UserUnshareRequestBody userUnshareRequestBody) {
        // Core logic for unsharing the user from specified organizations
    }

    /**
     * Handles removing a user's shared access from all organizations.
     *
     * @param userUnshareWithAllRequestBody Contains details for removing shared access.
     */
    public void unshareUserWithAll(UserUnshareWithAllRequestBody userUnshareWithAllRequestBody) {
        // Core logic for removing user's shared access from all organizations
    }

    /**
     * Retrieves the organizations that a user has access to.
     *
     * @param userId    The ID of the user.
     * @param after     The cursor for the next page.
     * @param before    The cursor for the previous page.
     * @param limit     The maximum number of results per page.
     * @param filter    The filter criteria.
     * @param recursive Whether to include child organizations.
     * @return UserSharedOrganizationsResponse containing accessible organizations.
     */
    public UserSharedOrganizationsResponse getSharedOrganizations(String userId, String after, String before,
                                                                  Integer limit, String filter, Boolean recursive) {
        // Core logic to retrieve shared organizations
        UserSharedOrganizationsResponse response = new UserSharedOrganizationsResponse();
        // Populate the response with shared organizations
        return response;
    }

    /**
     * Retrieves the roles assigned to a user within a specified organization.
     *
     * @param userId    The ID of the user.
     * @param orgId     The ID of the organization.
     * @param after     The cursor for the next page.
     * @param before    The cursor for the previous page.
     * @param limit     The maximum number of results per page.
     * @param filter    The filter criteria.
     * @param recursive Whether to include child roles.
     * @return UserSharedRolesResponse containing shared roles.
     */
    public UserSharedRolesResponse getSharedRoles(String userId, String orgId, String after, String before,
                                                  Integer limit, String filter, Boolean recursive) {
        // Core logic to retrieve shared roles for the user in the specified organization
        UserSharedRolesResponse response = new UserSharedRolesResponse();
        // Populate the response with shared roles
        return response;
    }

    /**
     * Helper method to handle exceptions.
     *
     * @param status   HTTP status of the error.
     * @param errorMsg Error message to be returned.
     * @param data     Additional error data.
     * @return APIError object for error response.
     */
    /*private APIError handleException(Response.Status status, String errorMsg, String data) {
        return new APIError(status, new ErrorResponse.Builder()
                .withCode("ERROR_CODE")
                .withMessage(errorMsg)
                .withDescription(data)
                .build());
    }*/
}
