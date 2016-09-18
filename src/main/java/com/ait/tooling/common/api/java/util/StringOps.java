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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public final class StringOps
{
    private static final String  SEPR               = ", ";

    public static final String   NULL_STRING        = null;

    public static final String   EMPTY_STRING       = "";

    public static final String[] EMPTY_STRING_ARRAY = new String[0];

    protected StringOps()
    {
    }

    public static final String[] toArray(final Collection<String> collection)
    {
        Objects.requireNonNull(collection);

        if (collection.isEmpty())
        {
            return EMPTY_STRING_ARRAY;
        }
        return collection.toArray(EMPTY_STRING_ARRAY);
    }

    public static final String[] toArray(final String... collection)
    {
        if ((null == collection) || (collection.length < 1))
        {
            return EMPTY_STRING_ARRAY;
        }
        return collection;
    }

    public static final String[] toUniqueArray(final Collection<String> collection)
    {
        Objects.requireNonNull(collection);

        if (collection.isEmpty())
        {
            return EMPTY_STRING_ARRAY;
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
            return EMPTY_STRING_ARRAY;
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

    public static final String toPrintableString(final Collection<String> collection)
    {
        if (null == collection)
        {
            return "null";
        }
        if (collection.isEmpty())
        {
            return "[]";
        }
        return toPrintableString(toArray(collection));
    }

    public static final String toPrintableString(final String... list)
    {
        if (null == list)
        {
            return "null";
        }
        if (list.length == 0)
        {
            return "[]";
        }
        final StringBuilder builder = new StringBuilder();

        builder.append('[');

        for (String item : list)
        {
            if (null != item)
            {
                builder.append('"').append(escapeForJavaScript(item)).append('"');
            }
            else
            {
                builder.append("null");
            }
            builder.append(SEPR);
        }
        final int sepr = SEPR.length();

        final int leng = builder.length();

        final int tail = builder.lastIndexOf(SEPR);

        if ((tail >= 0) && (tail == (leng - sepr)))
        {
            builder.setLength(leng - sepr);
        }
        return builder.append(']').toString();
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

    public static final String toTrimOrElse(String string, String otherwise)
    {
        string = toTrimOrNull(string);

        if (null == string)
        {
            return otherwise;
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

    public static final String requireTrimOrNull(final String string, final Supplier<String> supplier)
    {
        return Objects.requireNonNull(toTrimOrNull(string), Objects.requireNonNull(supplier));
    }

    public static final boolean isDigits(final String string)
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
            if (false == Character.isDigit(string.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    public static final boolean isAlpha(final String string)
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

    public static final boolean isAlphaOrDigits(final String string)
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

    public static final boolean isAlphaOrDigitsStartsAlpha(final String string)
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

    public static final String escapeForJavaScript(final String string)
    {
        if (null == string)
        {
            return "null";
        }
        if (string.isEmpty())
        {
            return string;
        }
        return escapeForJavaScript(string, new StringBuilder()).toString();
    }

    public static final StringBuilder escapeForJavaScript(final String string, final StringBuilder builder)
    {
        if (null == string)
        {
            return builder.append("null");
        }
        final int leng = string.length();

        for (int i = 0; i < leng; i++)
        {
            final char c = string.charAt(i);

            if (((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z')) || (c == ' ') || ((c >= '0') && (c <= '9')))
            {
                builder.append(c);// ASCII will be most common, this improves write speed about 5%, FWIW.
            }
            else
            {
                switch (c)
                {
                    case '"':
                        builder.append("\\\"");
                        break;
                    case '\\':
                        builder.append("\\\\");
                        break;
                    case '\b':
                        builder.append("\\b");
                        break;
                    case '\f':
                        builder.append("\\f");
                        break;
                    case '\n':
                        builder.append("\\n");
                        break;
                    case '\r':
                        builder.append("\\r");
                        break;
                    case '\t':
                        builder.append("\\t");
                        break;
                    case '/':
                        builder.append("\\/");
                        break;
                    default:
                        // Reference: http://www.unicode.org/versions/Unicode5.1.0/

                        if (((c >= '\u0000') && (c <= '\u001F')) || ((c >= '\u007F') && (c <= '\u009F')) || ((c >= '\u2000') && (c <= '\u20FF')))
                        {
                            final String unic = Integer.toHexString(c);

                            final int size = 4 - unic.length();

                            builder.append("\\u");

                            for (int k = 0; k < size; k++)
                            {
                                builder.append('0');
                            }
                            builder.append(unic.toUpperCase());
                        }
                        else
                        {
                            builder.append(c);
                        }
                        break;
                }
            }
        }
        return builder;
    }

    public static final String format(final String format, final Object... values)
    {
        return String.format(Objects.requireNonNull(format), values);
    }

    public static final String interp(String format, final Map<String, ?> hashes)
    {
        Objects.requireNonNull(hashes);

        Objects.requireNonNull(format);

        final ArrayList<Object> args = new ArrayList<Object>(0);

        int b = 0;

        while (true)
        {
            final int i = format.indexOf("{", b);

            if (i == -1)
            {
                break;// done
            }
            final int e = format.indexOf('}', i + 1);

            if (e == -1)
            {
                break;// done
            }
            if (b != i)
            {
                args.add(hashes.get(format.substring(b, i)));
            }
            format = format.substring(i + 1, e);

            b = e + 1;
        }
        return format(format, args.toArray());
    }
}
