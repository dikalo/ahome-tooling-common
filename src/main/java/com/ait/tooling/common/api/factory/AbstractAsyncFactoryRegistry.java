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

package com.ait.tooling.common.api.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import com.ait.tooling.common.api.types.IAsyncCallback;

public abstract class AbstractAsyncFactoryRegistry<F> implements IAsyncFactoryRegistry<F>
{
    private boolean                                  m_canmodify;

    private final LinkedHashMap<String, F>           m_factories = new LinkedHashMap<String, F>();

    private final LinkedHashMap<String, Supplier<F>> m_suppliers = new LinkedHashMap<String, Supplier<F>>();

    protected AbstractAsyncFactoryRegistry()
    {
        this(false);
    }

    protected AbstractAsyncFactoryRegistry(final boolean canmodify)
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
        return m_suppliers.containsKey(Objects.requireNonNull(name));
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
        return (null == m_suppliers.get(Objects.requireNonNull(name)));
    }

    @Override
    public final List<String> keys()
    {
        return Collections.unmodifiableList(new ArrayList<String>(m_suppliers.keySet()));
    }

    public synchronized void get(final String name, final IAsyncCallback<F> callback)
    {
        Objects.requireNonNull(callback);

        F factory = m_factories.get(Objects.requireNonNull(name));

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
        if (null == factory)
        {
            callback.onFailure(new UndefinedTypeRegistryException(name));
        }
        else
        {
            callback.onSuccess(factory);
        }
    }

    protected synchronized void putFactorySupplier(final String name, final Supplier<F> supplier)
    {
        Objects.requireNonNull(name);

        Objects.requireNonNull(supplier);

        if (isModifiable())
        {
            m_factories.remove(name);

            m_suppliers.put(name, supplier);
        }
        else
        {
            if (isDefined(name))
            {
                throw new UnmodifiableTypeRegistryException(name);
            }
            else
            {
                m_suppliers.put(name, supplier);
            }
        }
    }

    @Override
    public synchronized void clear()
    {
        if (isModifiable())
        {
            m_factories.clear();

            m_suppliers.clear();
        }
        else
        {
            throw new UnmodifiableTypeRegistryException("clear()");
        }
    }
}
