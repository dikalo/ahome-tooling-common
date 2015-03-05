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

import java.util.Objects;

import com.ait.tooling.common.api.java.util.function.Predicate;

public final class Predicates
{
    private Predicates()
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

    public static final <T> Predicate<T> and(final Predicate<? super T> p1, final Predicate<? super T> p2)
    {
        Objects.requireNonNull(p1);

        Objects.requireNonNull(p2);

        return new Predicate<T>()
        {
            @Override
            public final boolean test(final T value)
            {
                return (p1.test(value) && p2.test(value));
            }
        };
    }

    public static final <T> Predicate<T> or(final Predicate<? super T> p1, final Predicate<? super T> p2)
    {
        Objects.requireNonNull(p1);

        Objects.requireNonNull(p2);

        return new Predicate<T>()
        {
            @Override
            public final boolean test(final T value)
            {
                return (p1.test(value) || p2.test(value));
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
                if (value != null)
                {
                    return value.equals(object);
                }
                return false;
            }
        };
    }
}