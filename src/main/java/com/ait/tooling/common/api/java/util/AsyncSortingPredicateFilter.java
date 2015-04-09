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

package com.ait.tooling.common.api.java.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

import com.ait.tooling.common.api.java.util.function.Consumer;
import com.ait.tooling.common.api.java.util.function.Predicate;

public final class AsyncSortingPredicateFilter<T> implements IAsyncFilter<T>
{
    private final Predicate<T>  m_predicate;

    private final Comparator<T> m_compareit;

    public AsyncSortingPredicateFilter(final Predicate<T> predicate, final Comparator<T> compareit)
    {
        m_predicate = Objects.requireNonNull(predicate);

        m_compareit = Objects.requireNonNull(compareit);
    }

    @Override
    public final void filter(final Collection<T> collection, final Consumer<Collection<T>> callback)
    {
        if (collection.isEmpty())
        {
            callback.accept(collection);
        }
        else
        {
            final ArrayList<T> results = new ArrayList<T>(collection.size());

            for (T value : collection)
            {
                if (m_predicate.test(value))
                {
                    results.add(value);
                }
            }
            if (results.size() > 1)
            {
                Collections.sort(results, m_compareit);
            }
            callback.accept(results);
        }
    }
}
