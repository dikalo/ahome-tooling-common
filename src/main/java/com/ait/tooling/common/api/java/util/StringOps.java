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

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Objects;

public final class StringOps
{
    public final static String[] EMPTY = new String[0];

    public final static String   NULL  = null;

    private StringOps()
    {
    }

    public static final String[] toArray(final Collection<String> collection)
    {
        Objects.requireNonNull(collection);

        if (collection.isEmpty())
        {
            return EMPTY;
        }
        return collection.toArray(EMPTY);
    }

    public static final String[] toArray(final String... collection)
    {
        if ((null == collection) || (collection.length < 1))
        {
            return EMPTY;
        }
        return collection;
    }

    public static final String[] toUniqueArray(final Collection<String> collection)
    {
        Objects.requireNonNull(collection);

        if (collection.isEmpty())
        {
            return EMPTY;
        }
        final LinkedHashSet<String> uniq = new LinkedHashSet<String>();

        for (String s : collection)
        {
            if (null != s)
            {
                uniq.add(s);
            }
        }
        return toArray(uniq);
    }

    public static final String[] toUniqueArray(final String... collection)
    {
        if ((null == collection) || (collection.length < 1))
        {
            return EMPTY;
        }
        final LinkedHashSet<String> uniq = new LinkedHashSet<String>();

        for (int i = 0; i < collection.length; i++)
        {
            final String s = collection[i];

            if (null != s)
            {
                uniq.add(s);
            }
        }
        return toArray(uniq);
    }

    public static final Collection<String> toUnique(final Collection<String> collection)
    {
        Objects.requireNonNull(collection);

        if (collection.isEmpty())
        {
            return collection;
        }
        final LinkedHashSet<String> uniq = new LinkedHashSet<String>();

        for (String s : collection)
        {
            if (null != s)
            {
                uniq.add(s);
            }
        }
        return uniq;
    }

    public static final String toTrimOrNull(String string)
    {
        if (null == string)
        {
            return null;
        }
        if ((string = string.trim()).isEmpty())
        {
            return null;
        }
        return string;
    }

    public static final String requireTrimOrNull(final String string)
    {
        return Objects.requireNonNull(toTrimOrNull(string));
    }

    public static final String requireTrimOrNull(final String string, final String reason)
    {
        return Objects.requireNonNull(toTrimOrNull(string), Objects.requireNonNull(reason));
    }

    public static final boolean isAlphabetic(final String string)
    {
        if (null == string)
        {
            return false;
        }
        final int leng = string.length();

        if (leng < 1)
        {
            return false;
        }
        for (int i = 0; i < leng; i++)
        {
            if (false == Character.isLetter(string.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public static final boolean isAlphanumeric(final String string)
    {
        if (null == string)
        {
            return false;
        }
        final int leng = string.length();

        if (leng < 1)
        {
            return false;
        }
        for (int i = 0; i < leng; i++)
        {
            if (false == Character.isLetterOrDigit(string.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public static final boolean isAlphanumericStartsAlphabetic(final String string)
    {
        if (null == string)
        {
            return false;
        }
        final int leng = string.length();

        if (leng < 1)
        {
            return false;
        }
        if (false == Character.isLetter(string.charAt(0)))
        {
            return false;
        }
        for (int i = 1; i < leng; i++)
        {
            if (false == Character.isLetterOrDigit(string.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public static final boolean isVersionID(final String string)
    {
        if (null == string)
        {
            return false;
        }
        final int leng = string.length();

        if (leng < 1)
        {
            return false;
        }
        int dots = 0;

        boolean digi = false;

        for (int i = 0; i < leng; i++)
        {
            char c = string.charAt(i);

            if (false == Character.isDigit(c))
            {
                if (c == '.')
                {
                    if (false == digi)
                    {
                        return false;
                    }
                    digi = false;

                    dots++;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                digi = true;
            }
        }
        return ((true == digi) && (dots > 0));
    }

    public static final String reverse(final String string)
    {
        if (null == string)
        {
            return string;
        }
        if (string.length() < 2)
        {
            return string;
        }
        return new StringBuilder(string).reverse().toString();
    }
}
