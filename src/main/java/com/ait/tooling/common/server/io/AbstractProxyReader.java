/*
 * Copyright (c) 2014,2015,2016 Ahome' Innovation Technologies. All rights reserved.
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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Objects;

public abstract class AbstractProxyReader extends Reader
{
    private final Reader m_proxy;

    protected AbstractProxyReader(final Reader proxy)
    {
        m_proxy = Objects.requireNonNull(proxy);
    }

    protected AbstractProxyReader(final InputStream stream)
    {
        this(new InputStreamReader(stream));
    }

    protected final Reader getProxyReader()
    {
        return m_proxy;
    }

    @Override
    public int read(final CharBuffer target) throws IOException
    {
        return m_proxy.read(target);
    }

    @Override
    public int read(final char[] cbuf, final int off, final int len) throws IOException
    {
        return m_proxy.read(cbuf, off, len);
    }

    @Override
    public int read() throws IOException
    {
        return m_proxy.read();
    }

    @Override
    public int read(final char cbuf[]) throws IOException
    {
        return m_proxy.read(cbuf);
    }

    @Override
    public boolean ready() throws IOException
    {
        return m_proxy.ready();
    }

    @Override
    public long skip(final long n) throws IOException
    {
        return m_proxy.skip(n);
    }

    @Override
    public boolean markSupported()
    {
        return m_proxy.markSupported();
    }

    @Override
    public void mark(final int mark) throws IOException
    {
        m_proxy.mark(mark);
    }

    @Override
    public void reset() throws IOException
    {
        m_proxy.reset();
    }

    @Override
    public void close() throws IOException
    {
        m_proxy.close();
    }
}
