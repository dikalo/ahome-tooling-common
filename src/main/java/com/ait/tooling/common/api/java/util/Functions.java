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

import java.util.Map;
import java.util.Objects;

import com.ait.tooling.common.api.java.util.function.Function;
import com.ait.tooling.common.api.java.util.function.Predicate;

public final class Functions
{
    private Functions()
    {
    }

    public static final <T> Function<T, T> identity()
    {
        return new Function<T, T>()
        {
            @Override
            public final T apply(final T target)
            {
                return target;
            }
        };
    }

    public static final <V, T, R> Function<V, R> compose(final Function<? super V, ? extends T> before, final Function<? super T, ? extends R> after)
    {
        Objects.requireNonNull(after);
        
        Objects.requireNonNull(before);
        
        return new Function<V, R>()
        {
            @Override
            public final R apply(final V target)
            {
                return after.apply(before.apply(target));
            }
        };
    }

    public static final <T> Function<T, Boolean> forPredicate(final Predicate<T> predicate)
    {
        Objects.requireNonNull(predicate);

        return new Function<T, Boolean>()
        {
            @Override
            public final Boolean apply(final T target)
            {
                return predicate.test(target);
            }
        };
    }

    public static final <K, V> Function<K, V> forMap(final Map<K, V> map)
    {
        Objects.requireNonNull(map);
        
        return new Function<K, V>()
        {
            @Override
            public final V apply(K key)
            {
                return map.get(key);
            }
        };
    }

    public static final <K, V> Function<K, V> forMap(final Map<K, V> map, final V value)
    {
        Objects.requireNonNull(map);

        return new Function<K, V>()
        {
            @Override
            public final V apply(K key)
            {
                final V result = map.get(key);

                if (null == result)
                {
                    return value;
                }
                return result;
            }
        };
    }

    public static final Function<String, String> toTrimOrNull()
    {
        return new Function<String, String>()
        {
            @Override
            public final String apply(final String target)
            {
                return StringOps.toTrimOrNull(target);
            }
        };
    }
}