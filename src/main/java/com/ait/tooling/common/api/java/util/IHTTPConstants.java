/*
   Copyright (c) 2014,2015 Ahome' Innovation Technologies. All rights reserved.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package com.ait.tooling.common.api.java.util;

public interface IHTTPConstants
{
    public static final String CHARSET_UTF_8                 = "UTF-8";

    public static final String CONTENT_TEXT_PLAIN            = "text/plain";

    public static final String CONTENT_TYPE_HTML             = "text/html";

    public static final String CONTENT_TYPE_TEXT_XML         = "text/xml";

    public static final String CONTENT_TYPE_APPLICATION_XML  = "application/xml";

    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";

    public static final String ACCEPT_HEADER                 = "Accept";

    public static final String CONTENT_TYPE_HEADER           = "Content-Type";

    public static final String USER_AGENT_HEADER             = "User-Agent";

    public static final String FORWARDED_FOR_HEADER          = "X-Forwarded-For";

    public static final String USER_ID_HEADER                = "X-User-ID";

    public static final String USER_NAME_HEADER              = "X-User-Name";

    public static final String CLIENT_VERSION_HEADER         = "X-Client-Version";

    public static final String SESSION_ID_HEADER             = "X-Session-ID";

    public static final String CLIENT_UUID_HEADER            = "X-Client-UUID";

    public static final String SESSION_UUID_HEADER           = "X-Session-UUID";

    public static final String CLIENT_NAME_HEADER            = "X-Client-Name";

    public static final String SCHEMA_VERSION_HEADER         = "X-Schema-Version";

    public static final String XSRF_TOKEN_HEADER             = "X-Request-XSRFToken";
}
