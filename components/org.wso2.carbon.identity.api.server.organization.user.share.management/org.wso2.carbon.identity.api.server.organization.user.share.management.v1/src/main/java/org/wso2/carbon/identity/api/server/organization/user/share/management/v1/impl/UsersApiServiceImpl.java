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

package org.wso2.carbon.identity.api.server.organization.user.share.management.v1.impl;

import org.wso2.carbon.identity.api.server.organization.user.share.management.v1.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.wso2.carbon.identity.api.server.organization.user.share.management.v1.UsersApiService;
import org.wso2.carbon.identity.api.server.organization.user.share.management.v1.core.UsersApiServiceCore;

import javax.ws.rs.core.Response;

public class UsersApiServiceImpl implements UsersApiService {

    @Autowired
    private UsersApiServiceCore usersApiServiceCore;

    @Override
    public Response processUserSharing(UserShareRequestBody userShareRequestBody) {

        usersApiServiceCore.shareUser(userShareRequestBody);
        return Response.noContent().build();
    }

    @Override
    public Response processUserSharingAll(UserShareWithAllRequestBody userShareWithAllRequestBody) {

        usersApiServiceCore.shareUserWithAll(userShareWithAllRequestBody);
        return Response.noContent().build();
    }

    @Override
    public Response processUserUnsharing(UserUnshareRequestBody userUnshareRequestBody) {

        usersApiServiceCore.unshareUser(userUnshareRequestBody);
        return Response.noContent().build();
    }

    @Override
    public Response removeUserSharing(UserUnshareWithAllRequestBody userUnshareWithAllRequestBody) {

        usersApiServiceCore.unshareUserWithAll(userUnshareWithAllRequestBody);
        return Response.noContent().build();
    }

    @Override
    public Response usersUserIdSharedOrganizationsGet(String userId, String after, String before, Integer limit, String filter, Boolean recursive) {

        UserSharedOrganizationsResponse response = usersApiServiceCore.getSharedOrganizations(
                userId, after, before, limit, filter, recursive);
        return Response.ok().entity(response).build();
    }

    @Override
    public Response usersUserIdSharedRolesGet(String userId, String orgId, String after, String before, Integer limit, String filter, Boolean recursive) {

        UserSharedRolesResponse response = usersApiServiceCore.getSharedRoles(
                userId, orgId, after, before, limit, filter, recursive);
        return Response.ok().entity(response).build();
    }
}
