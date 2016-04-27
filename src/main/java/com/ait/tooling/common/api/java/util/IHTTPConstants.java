/*
   Copyright (c) 2014,2015,2016 Ahome' Innovation Technologies. All rights reserved.

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
    public static final String CHARSET_UTF_8                    = "UTF-8";

    public static final String CONTENT_TEXT_PLAIN               = "text/plain";

    public static final String CONTENT_TYPE_HTML                = "text/html";

    public static final String CONTENT_TYPE_TEXT_XML            = "text/xml";

    public static final String CONTENT_TYPE_APPLICATION_XML     = "application/xml";

    public static final String CONTENT_TYPE_APPLICATION_JSON    = "application/json";

    public static final String ACCEPT_HEADER                    = "Accept";

    public static final String CONTENT_TYPE_HEADER              = "Content-Type";

    public static final String USER_AGENT_HEADER                = "User-Agent";

    public static final String CACHE_CONTROL_HEADER             = "Cache-Control";

    public static final String CONTENT_LENGTH_HEADER            = "Content-Length";

    public static final String DATE_HEADER                      = "Date";

    public static final String PRAGMA_HEADER                    = "Pragma";

    public static final String ACCEPT_CHARSET_HEADER            = "Accept-Charset";

    public static final String ACCEPT_ENCODING_HEADER           = "Accept-Encoding";

    public static final String ACCEPT_LANGUAGE_HEADER           = "Accept-Language";

    public static final String COOKIE_HEADER                    = "Cookie";

    public static final String IF_UNMODIFIED_SINCE_HEADER       = "If-Unmodified-Since";

    public static final String REFERER_HEADER                   = "Referer";

    public static final String UPGRADE_HEADER                   = "Upgrade";

    public static final String CONTENT_DISPOSITION_HEADER       = "Content-Disposition";

    public static final String EXPIRES_HEADER                   = "Expires";

    public static final String SET_COOKIE_HEADER                = "Set-Cookie";

    public static final String STRICT_TRANSPORT_SECURITY_HEADER = "Strict-Transport-Security";

    public static final String X_FRAME_OPTIONS_HEADER           = "X-Frame-Options";

    public static final String X_POWERED_BY_HEADER              = "X-Powered-By";

    public static final String X_XSS_PROTECTION_HEADER          = "X-XSS-Protection";

    public static final String X_FORWARDED_FOR_HEADER           = "X-Forwarded-For";

    public static final String X_USER_ID_HEADER                 = "X-User-ID";

    public static final String X_USER_NAME_HEADER               = "X-User-Name";

    public static final String X_CLIENT_VERSION_HEADER          = "X-Client-Version";

    public static final String X_SESSION_ID_HEADER              = "X-Session-ID";

    public static final String X_CLIENT_UUID_HEADER             = "X-Client-UUID";

    public static final String X_SESSION_UUID_HEADER            = "X-Session-UUID";

    public static final String X_CLIENT_NAME_HEADER             = "X-Client-Name";

    public static final String X_SCHEMA_VERSION_HEADER          = "X-Schema-Version";

    public static final String X_XSRF_TOKEN_HEADER              = "X-Request-XSRFToken";

    public static final String X_CLIENT_API_TOKEN_HEADER        = "X-Client-API-Token";

    public static final String X_STRICT_JSON_FORMAT_HEADER      = "X-Strict-JSON-Format";

    public static final String X_CONTENT_TYPE_OPTIONS_HEADER    = "X-Content-Type-Options";

    public static final String CACHE_CONTROL_MAX_AGE_PREFIX     = "max-age=";

    public static final String NO_CACHE_PRAGMA_HEADER_VALUE     = "no-cache";

    public static final String NO_CACHE_CONTROL_HEADER_VALUE    = "no-cache, no-store, must-revalidate";

    public static final long   DAY_IN_SECONDS                   = 86400L;

    public static final long   DAY_IN_MILLISECONDS              = 86400000L;

    public static final long   WEEK_IN_SECONDS                  = 604800L;

    public static final long   WEEK_IN_MILLISECONDS             = 604800000L;

    public static final long   YEAR_IN_SECONDS                  = 31536000L;

    public static final long   YEAR_IN_MILLISECONDS             = 31536000000L;
}
