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

package com.ait.tooling.common.api.java.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.ait.tooling.common.api.java.util.function.Predicate;

public final class Predicates
{
    protected Predicates()
    {
    }

    public static final <T> Predicate<T> notNull()
    {
        return new Predicate<T>()
        {
            @Override
            public final boolean test(final T value)
            {
                return (null != value);
            }
        };
    }

    public static final <T> Predicate<T> negate(final Predicate<? super T> predicate)
    {
        Objects.requireNonNull(predicate);

        return new Predicate<T>()
        {
            @Override
            public final boolean test(final T value)
            {
                return (false == predicate.test(value));
            }
        };
    }

    @SafeVarargs
    public static final <T> Predicate<T> and(final Predicate<? super T>... predicates)
    {
        return and(Arrays.asList(Objects.requireNonNull(predicates)));
    }

    public static final <T> Predicate<T> and(final List<Predicate<? super T>> list)
    {
        Objects.requireNonNull(list);

        return new Predicate<T>()
        {
            @Override
            public final boolean test(final T value)
            {
                for (Predicate<? super T> pred : list)
                {
                    if (false == pred.test(value))
                    {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    @SafeVarargs
    public static final <T> Predicate<T> or(final Predicate<? super T>... predicates)
    {
        return or(Arrays.asList(Objects.requireNonNull(predicates)));
    }

    public static final <T> Predicate<T> or(final List<Predicate<? super T>> list)
    {
        Objects.requireNonNull(list);

        return new Predicate<T>()
        {
            @Override
            public final boolean test(final T value)
            {
                for (Predicate<? super T> pred : list)
                {
                    if (pred.test(value))
                    {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public static final <T> Predicate<T> isEqual(final T object)
    {
        return new Predicate<T>()
        {
            @Override
            public final boolean test(final T value)
            {
                if (value == object)
                {
                    return true;
                }
                if (null != value)
                {
                    return value.equals(object);
                }
                if (null != object)
                {
                    return object.equals(value);
                }
                return false;
            }
        };
    }
}