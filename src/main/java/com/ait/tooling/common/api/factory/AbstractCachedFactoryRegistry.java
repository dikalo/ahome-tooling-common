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
   
   Author: Dean S. Jones
 */

package com.ait.tooling.common.api.factory;

import java.util.LinkedHashMap;

import com.ait.tooling.common.api.java.util.StringOps;

public abstract class AbstractCachedFactoryRegistry<T, A> extends AbstractFactoryRegistry<T, A> implements ICachedFactory<T, A>
{
    private long                                           m_glive = 0;

    private final LinkedHashMap<String, Long>              m_tlive = new LinkedHashMap<String, Long>();

    private final LinkedHashMap<String, TTLCachedValue<T>> m_cache = new LinkedHashMap<String, TTLCachedValue<T>>();

    protected AbstractCachedFactoryRegistry()
    {
        this(false);
    }

    protected AbstractCachedFactoryRegistry(final boolean canmodify)
    {
        super(canmodify);
    }

    @Override
    public T create(final String type, final A args, final IFactoryContext context) throws FactoryException
    {
        final String name = StringOps.requireTrimOrNull(type);

        final TTLCachedValue<T> entry = getEntry(name);

        if (null != entry)
        {
            return entry.getValue();
        }
        else
        {
            final T result = super.create(name, args, context);

            if (null == result)
            {
                throw new UndefinedFactoryException(name);
            }
            m_cache.put(name, new TTLCachedValue<T>(result, getTimeStamp()));

            return result;
        }
    }

    @Override
    public void clear()
    {
        m_cache.clear();
    }

    @Override
    public void clear(final String name)
    {
        m_cache.remove(StringOps.requireTrimOrNull(name));
    }

    @Override
    public void setGlobalTTL(final long live)
    {
        m_glive = Math.max(0, live);
    }

    @Override
    public void setByTypeTTL(final String name, final long live)
    {
        m_tlive.put(StringOps.requireTrimOrNull(name), Math.max(0, live));
    }

    @Override
    public void clearGlobalTTL()
    {
        m_glive = 0;
    }

    @Override
    public void clearByTypeTTL()
    {
        m_tlive.clear();
    }

    @Override
    public void clearByTypeTTL(final String name)
    {
        m_tlive.remove(StringOps.requireTrimOrNull(name));
    }

    @Override
    public void clearExpired()
    {
        final String[] list = StringOps.toArray(m_cache.keySet());

        for (int i = 0; i < list.length; i++)
        {
            clearExpired(list[i]);
        }
    }

    @Override
    public void clearExpired(final String name)
    {
        getEntry(name);
    }

    private final TTLCachedValue<T> getEntry(final String type)
    {
        final String name = StringOps.requireTrimOrNull(type);

        TTLCachedValue<T> entry = m_cache.get(name);

        if (null != entry)
        {
            if (m_glive > 0)
            {
                if ((getTimeStamp() - entry.getTimeStamp()) >= m_glive)
                {
                    clear(name);

                    entry = null;
                }
            }
            if (null != entry)
            {
                final Long live = m_tlive.get(name);

                if (null != live)
                {
                    if ((getTimeStamp() - entry.getTimeStamp()) >= live)
                    {
                        clear(name);

                        entry = null;
                    }
                }
            }
        }
        return entry;
    }

    protected long getTimeStamp()
    {
        return System.currentTimeMillis();
    }
}
