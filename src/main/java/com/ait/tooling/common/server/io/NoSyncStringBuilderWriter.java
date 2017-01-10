/*
 * Copyright (c) 2017 Ahome' Innovation Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ait.tooling.common.server.io;

import java.io.IOException;
import java.io.Writer;

public class NoSyncStringBuilderWriter extends Writer
{
    private final static int    MINIMUM_CAPACITY = 16;

    private final static int    DEFAULT_CAPACITY = 128;

    private final static int    BOUNDRY_CAPACITY = Integer.MAX_VALUE - MINIMUM_CAPACITY;

    private final StringBuilder m_buff;

    private static final int toMinimumCapacity(int capacity)
    {
        if (capacity <= MINIMUM_CAPACITY)
        {
            return MINIMUM_CAPACITY;
        }
        if (capacity >= BOUNDRY_CAPACITY)
        {
            capacity = capacity - DEFAULT_CAPACITY;
        }
        return capacity + (capacity % MINIMUM_CAPACITY);
    }

    public NoSyncStringBuilderWriter()
    {
        this(DEFAULT_CAPACITY);
    }

    public NoSyncStringBuilderWriter(final String str)
    {
        this(Math.max(DEFAULT_CAPACITY, toMinimumCapacity(str.length())));
    }

    public NoSyncStringBuilderWriter(final int capacity)
    {
        super(new StringBuilder(toMinimumCapacity(capacity)));

        m_buff = ((StringBuilder) super.lock);
    }

    @Override
    public void write(final int c)
    {
        m_buff.append((char) c);
    }

    @Override
    public void write(final char chr[], final int off, final int len)
    {
        if ((off < 0) || (off > chr.length) || (len < 0) || ((off + len) > chr.length) || ((off + len) < 0))
        {
            throw new IndexOutOfBoundsException();
        }
        else if (0 == len)
        {
            return;
        }
        m_buff.append(chr, off, len);
    }

    @Override
    public void write(final String str)
    {
        m_buff.append(str);
    }

    @Override
    public void write(final String str, final int off, final int len)
    {
        m_buff.append(str.substring(off, off + len));
    }

    @Override
    public NoSyncStringBuilderWriter append(final CharSequence chs)
    {
        write((null == chs) ? "null" : chs.toString());

        return this;
    }

    @Override
    public NoSyncStringBuilderWriter append(final CharSequence chs, final int beg, final int end)
    {
        final CharSequence csr = ((null == chs) ? "null" : chs);

        write(csr.subSequence(beg, end).toString());

        return this;
    }

    @Override
    public NoSyncStringBuilderWriter append(final char c)
    {
        write(c);

        return this;
    }

    @Override
    public String toString()
    {
        return m_buff.toString();
    }

    @Override
    public void flush()
    {
    }

    @Override
    public void close() throws IOException
    {
    }

    public NoSyncStringBuilderWriter clear()
    {
        m_buff.setLength(0);

        return this;
    }
}
