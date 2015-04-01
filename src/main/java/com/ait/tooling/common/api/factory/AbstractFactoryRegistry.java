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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Objects;

import com.ait.tooling.common.api.java.util.StringOps;

public abstract class AbstractFactoryRegistry<T, A> implements IFactoryRegistry<T, A>
{
    private boolean                                     m_canmodify;

    private final LinkedHashMap<String, IFactory<T, A>> m_factories = new LinkedHashMap<String, IFactory<T, A>>();

    protected AbstractFactoryRegistry()
    {
        this(false);
    }

    protected AbstractFactoryRegistry(final boolean canmodify)
    {
        setModifiable(canmodify);
    }

    protected final void setModifiable(final boolean canmodify)
    {
        m_canmodify = canmodify;
    }

    @Override
    public final boolean isModifiable()
    {
        return m_canmodify;
    }

    @Override
    public final boolean isDefined(final String name)
    {
        return m_factories.containsKey(StringOps.requireTrimOrNull(name));
    }

    @Override
    public final int size()
    {
        return m_factories.size();
    }

    @Override
    public final boolean isEmpty()
    {
        return m_factories.isEmpty();
    }

    @Override
    public final boolean isNull(final String name)
    {
        return (null == get(StringOps.requireTrimOrNull(name)));
    }

    @Override
    public final Collection<String> keys()
    {
        return Collections.unmodifiableSet(m_factories.keySet());
    }

    @Override
    public IFactory<T, A> get(final String name)
    {
        return m_factories.get(StringOps.requireTrimOrNull(name));
    }

    protected void putFactory(String name, final IFactory<T, A> factory)
    {
        name = StringOps.requireTrimOrNull(name);

        final IFactory<T, A> last = get(name);

        if (isModifiable())
        {
            if (null == factory)
            {
                m_factories.remove(name);
            }
            else
            {
                m_factories.put(name, factory);
            }
        }
        else
        {
            if (null == last)
            {
                m_factories.put(name, Objects.requireNonNull(factory));
            }
            else
            {
                throw new UnmodifiableFactoryException(name);
            }
        }
    }

    protected void remove(String name)
    {
        name = StringOps.requireTrimOrNull(name);

        if (isModifiable())
        {
            m_factories.remove(name);
        }
        else
        {
            throw new UnmodifiableFactoryException(name);
        }
    }

    @Override
    public T create(final String type, final A args, final IFactoryContext context) throws FactoryException
    {
        final String name = StringOps.requireTrimOrNull(type);

        final IFactory<T, A> factory = get(name);

        if (null == factory)
        {
            return getDefault(name, args, context);
        }
        else
        {
            return factory.create(name, args, context);
        }
    }

    protected T getDefault(final String name, final A args, final IFactoryContext context) throws FactoryException
    {
        throw new UndefinedFactoryException(name);
    }
}
