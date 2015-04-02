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

package com.ait.tooling.common.api.config;

import com.ait.tooling.common.api.logging.ILogging;

public final class CommonConfig implements ILogging
{
    private static final CommonConfig INSTANCE = new CommonConfig();

    private ILogging                  m_logging;

    private CommonConfig()
    {
    }

    public static final CommonConfig get()
    {
        return INSTANCE;
    }

    public final synchronized CommonConfig setLogging(final ILogging logging)
    {
        m_logging = logging;

        return this;
    }

    public final IPluginRegistry getPluginRegistry()
    {
        return PluginRegistry.get();
    }

    @Override
    public final void log(final String message)
    {
        if (null != m_logging)
        {
            m_logging.log(message);
        }
    }

    @Override
    public final void warn(final String message)
    {
        if (null != m_logging)
        {
            m_logging.warn(message);
        }
    }

    @Override
    public final void error(String message)
    {
        if (null != m_logging)
        {
            m_logging.error(message);
        }
    }

    @Override
    public final void error(final String message, final Throwable e)
    {
        if (null != m_logging)
        {
            m_logging.error(message, e);
        }
    }
}
