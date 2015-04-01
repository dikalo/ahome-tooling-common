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

import java.util.ArrayList;

public final class PluginRegistry
{
    private static PluginRegistry       INSTANCE;

    private final ArrayList<IPlugin<?>> m_plugins = new ArrayList<IPlugin<?>>();

    private PluginRegistry()
    {
    }

    public static final PluginRegistry get()
    {
        if (null == INSTANCE)
        {
            INSTANCE = new PluginRegistry();

            INSTANCE.addPlugin(new AhomeCommonPlugin());
        }
        return INSTANCE;
    }

    public final boolean addPlugin(final IPlugin<?> plugin)
    {
        if (null == plugin)
        {
            return false;
        }
        if (m_plugins.contains(plugin))
        {
            return false;
        }
        for (IPlugin<?> p : m_plugins)
        {
            if (plugin.getNameSpace().equals(p))
            {
                return false;
            }
        }
        m_plugins.add(plugin);

        return true;
    }

    public final IPlugin<?> getPlugin(final String name)
    {
        for (IPlugin<?> p : m_plugins)
        {
            if (p.getNameSpace().equals(name))
            {
                return p;
            }
        }
        return null;
    }
}
