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

package com.ait.tooling.common.api.factory;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Objects;

import com.ait.tooling.common.api.java.util.StringOps;
import com.ait.tooling.common.api.java.util.function.Supplier;
import com.ait.tooling.common.api.types.IStringValued;

public abstract class AbstractRegistry<F> implements IRegistry<F>
{
    private boolean                                  m_canmodify;

    private final LinkedHashMap<String, F>           m_factories = new LinkedHashMap<String, F>();

    private final LinkedHashMap<String, Supplier<F>> m_suppliers = new LinkedHashMap<String, Supplier<F>>();

    protected AbstractRegistry()
    {
        this(false);
    }

    protected AbstractRegistry(final boolean canmodify)
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
        return m_suppliers.containsKey(StringOps.requireTrimOrNull(name));
    }

    @Override
    public final int size()
    {
        return m_suppliers.size();
    }

    @Override
    public final boolean isEmpty()
    {
        return m_suppliers.isEmpty();
    }

    @Override
    public final boolean isNull(final String name)
    {
        return (null == m_suppliers.get(StringOps.requireTrimOrNull(name)));
    }

    @Override
    public final Collection<String> keys()
    {
        return Collections.unmodifiableSet(m_suppliers.keySet());
    }

    @Override
    public synchronized F get(String name)
    {
        name = StringOps.requireTrimOrNull(name);

        F factory = m_factories.get(name);

        if (null == factory)
        {
            Supplier<F> supplier = m_suppliers.get(name);

            if (null != supplier)
            {
                factory = supplier.get();

                if (null != factory)
                {
                    m_factories.put(name, factory);
                }
            }
        }
        return factory;
    }
    
    protected synchronized void putFactorySupplier(final IStringValued name, final Supplier<F> supplier)
    {
        putFactorySupplier(name.getValue(), supplier);
    }

    protected synchronized void putFactorySupplier(String name, Supplier<F> supplier)
    {
        name = StringOps.requireTrimOrNull(name);

        supplier = Objects.requireNonNull(supplier);

        if (isModifiable())
        {
            m_factories.remove(name);

            m_suppliers.put(name, supplier);
        }
        else
        {
            if (isDefined(name))
            {
                throw new UnmodifiableRegistryException(name);
            }
            else
            {
                m_suppliers.put(name, supplier);
            }
        }
    }
}
