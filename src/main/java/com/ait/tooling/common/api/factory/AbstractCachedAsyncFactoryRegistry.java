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
import java.util.Objects;

import com.ait.tooling.common.api.java.util.StringOps;
import com.ait.tooling.common.api.types.IValued;

public abstract class AbstractCachedAsyncFactoryRegistry<T, A> extends AbstractAsyncFactoryRegistry<T, A> implements IAsyncCachedFactory<T, A>
{
    private long                                     m_glive = 0;

    private final LinkedHashMap<String, Long>        m_tlive = new LinkedHashMap<String, Long>();

    private final LinkedHashMap<String, TTLEntry<T>> m_cache = new LinkedHashMap<String, TTLEntry<T>>();

    protected AbstractCachedAsyncFactoryRegistry()
    {
    }

    @Override
    public void create(final String type, final A args, final IAsyncFactoryResult<T> callback)
    {
        Objects.requireNonNull(callback);

        final String name = StringOps.requireTrimOrNull(type);

        try
        {
            final TTLEntry<T> entry = getEntry(name);

            if (null != entry)
            {
                callback.accept(entry.getValue());
            }
            else
            {
                super.create(name, args, new IAsyncFactoryResult<T>()
                {
                    @Override
                    public void accept(final T result)
                    {
                        if (null == result)
                        {
                            onFailure(new UndefinedFactoryException(name));
                        }
                        else
                        {
                            m_cache.put(name, new TTLEntry<T>(result, System.currentTimeMillis()));

                            callback.accept(result);
                        }
                    }

                    @Override
                    public void onFailure(final Throwable caught)
                    {
                        callback.onFailure(caught);
                    }
                });
            }
        }
        catch (Exception caught)
        {
            callback.onFailure(caught);
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
    public void setGlobalTTL(final long millis)
    {
        m_glive = Math.max(0, millis);
    }

    @Override
    public void setByTypeTTL(final String name, final long millis)
    {
        m_tlive.put(StringOps.requireTrimOrNull(name), Math.max(0, millis));
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

    private final TTLEntry<T> getEntry(final String type)
    {
        final String name = StringOps.requireTrimOrNull(type);

        TTLEntry<T> entry = m_cache.get(name);

        if (null != entry)
        {
            if (m_glive > 0)
            {
                if ((System.currentTimeMillis() - entry.getBirth()) >= m_glive)
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
                    if ((System.currentTimeMillis() - entry.getBirth()) >= live)
                    {
                        clear(name);

                        entry = null;
                    }
                }
            }
        }
        return entry;
    }

    private static final class TTLEntry<T> implements IValued<T>
    {
        private final T    m_value;

        private final long m_birth;

        public TTLEntry(final T value, final long birth)
        {
            m_value = value;

            m_birth = birth;
        }

        @Override
        public final T getValue()
        {
            return m_value;
        }

        public final long getBirth()
        {
            return m_birth;
        }
    }
}
