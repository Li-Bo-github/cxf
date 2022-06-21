/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.cxf.rs.security.jose.jaxrs.multipart;

import java.io.IOException;
import java.util.List;

import jakarta.annotation.Priority;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import jakarta.ws.rs.core.MediaType;
import org.apache.cxf.rs.security.jose.jaxrs.Priorities;

@Priority(Priorities.JWS_WRITE_PRIORITY)
public class JwsMultipartClientRequestFilter extends AbstractJwsMultipartSignatureFilter 
    implements ClientRequestFilter {

    @Override
    public void filter(ClientRequestContext ctx) throws IOException {
        MediaType contentType = ctx.getMediaType();
        if (contentType != null && "multipart".equals(contentType.getType())) {
            List<Object> parts = getAttachmentParts(ctx.getEntity());
            ctx.setEntity(parts);
        }
        
    }
}
