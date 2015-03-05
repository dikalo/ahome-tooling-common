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

public final class Filters
{
    private Filters()
    {
    }

    public static final <T> IFilter<T> make(Predicate<T> predicate)
    {
        return new PredicateFilter<T>(predicate);
    }

    public static final <T> IFilter<T> make(Predicate<T> predicate, Comparator<T> compareit)
    {
        return new SortingPredicateFilter<T>(predicate, compareit);
    }

    public static final <T> IAsyncFilter<T> async(Predicate<T> predicate)
    {
        return new AsyncPredicateFilter<T>(predicate);
    }

    public static final <T> IAsyncFilter<T> async(Predicate<T> predicate, Comparator<T> compareit)
    {
        return new AsyncSortingPredicateFilter<T>(predicate, compareit);
    }

    public static final <T> Collection<T> filter(Collection<T> collection, Predicate<T> predicate)
    {
        Objects.requireNonNull(collection);

        if (collection.isEmpty())
        {
            return collection;
        }
        else
        {
            return make(predicate).filter(collection);
        }
    }

    public static final <T> Collection<T> filter(Collection<T> collection, Predicate<T> predicate, Comparator<T> compareit)
    {
        Objects.requireNonNull(collection);

        if (collection.isEmpty())
        {
            return collection;
        }
        else
        {
            return make(predicate, compareit).filter(collection);
        }
    }

    public static final <T> Collection<T> filter(Collection<T> collection, IFilter<T> filter)
    {
        Objects.requireNonNull(filter);

        Objects.requireNonNull(collection);

        if (collection.isEmpty())
        {
            return collection;
        }
        else
        {
            return filter.filter(collection);
        }
    }

    public static final <T> void filter(Collection<T> collection, Predicate<T> predicate, Consumer<Collection<T>> callback)
    {
        Objects.requireNonNull(collection);

        if (collection.isEmpty())
        {
            callback.accept(collection);
        }
        else
        {
            async(predicate).filter(collection, callback);
        }
    }

    public static final <T> void filter(Collection<T> collection, Predicate<T> predicate, Comparator<T> compareit, Consumer<Collection<T>> callback)
    {
        Objects.requireNonNull(collection);

        if (collection.isEmpty())
        {
            callback.accept(collection);
        }
        else
        {
            async(predicate, compareit).filter(collection, callback);
        }
    }

    private static final class PredicateFilter<T> implements IFilter<T>
    {
        private final Predicate<T> m_predicate;

        public PredicateFilter(final Predicate<T> predicate)
        {
            m_predicate = Objects.requireNonNull(predicate);
        }

        @Override
        public final Collection<T> filter(final Collection<T> collection)
        {
            Objects.requireNonNull(collection);

            if (collection.isEmpty())
            {
                return collection;
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
                return results;
            }
        }
    }

    private static final class AsyncPredicateFilter<T> implements IAsyncFilter<T>
    {
        private final Predicate<T> m_predicate;

        public AsyncPredicateFilter(final Predicate<T> predicate)
        {
            m_predicate = Objects.requireNonNull(predicate);
        }

        @Override
        public final void filter(final Collection<T> collection, final Consumer<Collection<T>> callback)
        {
            Objects.requireNonNull(callback);

            Objects.requireNonNull(collection);

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
                callback.accept(results);
            }
        }
    }

    private static final class AsyncSortingPredicateFilter<T> implements IAsyncFilter<T>
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
            Objects.requireNonNull(callback);

            Objects.requireNonNull(collection);

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

    private static final class SortingPredicateFilter<T> implements IFilter<T>
    {
        private final Predicate<T>  m_predicate;

        private final Comparator<T> m_compareit;

        public SortingPredicateFilter(final Predicate<T> predicate, final Comparator<T> compareit)
        {
            m_predicate = Objects.requireNonNull(predicate);

            m_compareit = Objects.requireNonNull(compareit);
        }

        @Override
        public final Collection<T> filter(final Collection<T> collection)
        {
            Objects.requireNonNull(collection);

            if (collection.isEmpty())
            {
                return collection;
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
                return results;
            }
        }
    }
}