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

package com.ait.tooling.common.api.java.util;

import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

import com.ait.tooling.common.api.java.util.function.Consumer;
import com.ait.tooling.common.api.java.util.function.Predicate;

public final class Filters
{
    private Filters()
    {
    }

    public static final <T> IFilter<T> make(final Predicate<T> predicate)
    {
        return new PredicateFilter<T>(Objects.requireNonNull(predicate));
    }

    public static final <T> IFilter<T> make(final Predicate<T> predicate, final Comparator<T> compareit)
    {
        return new SortingPredicateFilter<T>(Objects.requireNonNull(predicate), Objects.requireNonNull(compareit));
    }

    public static final <T> IAsyncFilter<T> async(final Predicate<T> predicate)
    {
        return new AsyncPredicateFilter<T>(Objects.requireNonNull(predicate));
    }

    public static final <T> IAsyncFilter<T> async(final Predicate<T> predicate, final Comparator<T> compareit)
    {
        return new AsyncSortingPredicateFilter<T>(Objects.requireNonNull(predicate), Objects.requireNonNull(compareit));
    }

    public static final <T> Collection<T> filter(final Collection<T> collection, final Predicate<T> predicate)
    {
        Objects.requireNonNull(predicate);

        if (collection.isEmpty())
        {
            return collection;
        }
        else
        {
            return make(predicate).filter(collection);
        }
    }

    public static final <T> Collection<T> filter(final Collection<T> collection, final Predicate<T> predicate, final Comparator<T> compareit)
    {
        Objects.requireNonNull(predicate);

        Objects.requireNonNull(compareit);

        if (collection.isEmpty())
        {
            return collection;
        }
        else
        {
            return make(predicate, compareit).filter(collection);
        }
    }

    public static final <T> Collection<T> filter(final Collection<T> collection, final IFilter<T> filter)
    {
        Objects.requireNonNull(filter);

        if (collection.isEmpty())
        {
            return collection;
        }
        else
        {
            return filter.filter(collection);
        }
    }

    public static final <T> void filter(final Collection<T> collection, final Predicate<T> predicate, final Consumer<Collection<T>> callback)
    {
        Objects.requireNonNull(predicate);

        Objects.requireNonNull(callback);

        if (collection.isEmpty())
        {
            callback.accept(collection);
        }
        else
        {
            async(predicate).filter(collection, callback);
        }
    }

    public static final <T> void filter(final Collection<T> collection, final Predicate<T> predicate, final Comparator<T> compareit, final Consumer<Collection<T>> callback)
    {
        Objects.requireNonNull(predicate);

        Objects.requireNonNull(compareit);

        Objects.requireNonNull(callback);

        if (collection.isEmpty())
        {
            callback.accept(collection);
        }
        else
        {
            async(predicate, compareit).filter(collection, callback);
        }
    }
}