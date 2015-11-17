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

package com.ait.tooling.common.api.types;

import java.io.Serializable;
import java.util.Objects;

public class SearchResult<T extends Comparable<T>> implements ISearchResult<T>, Serializable
{
    private static final long serialVersionUID = 2440955017561840921L;

    private final T           m_valu;

    private final long        m_time;

    private final String      m_iden;

    private final String      m_mime;

    private final String      m_prop;

    private final String      m_desc;

    private final String      m_path;

    public SearchResult(final T valu, final String iden, final String mime, final String prop, final String desc, final String path, final long time)
    {
        m_valu = Objects.requireNonNull(valu);

        m_iden = Objects.requireNonNull(iden);

        m_mime = Objects.requireNonNull(mime);

        m_prop = Objects.requireNonNull(prop);

        m_desc = Objects.requireNonNull(desc);

        m_path = Objects.requireNonNull(path);

        m_time = time;
    }

    @Override
    public String getId()
    {
        return m_iden;
    }

    @Override
    public long getTime()
    {
        return m_time;
    }

    @Override
    public String getPath()
    {
        return m_path;
    }

    @Override
    public String getMimeType()
    {
        return m_mime;
    }

    @Override
    public String getProperty()
    {
        return m_prop;
    }

    @Override
    public T getValue()
    {
        return m_valu;
    }

    @Override
    public String getDescription()
    {
        return m_desc;
    }

    @Override
    public int compareTo(final T other)
    {
        return getValue().compareTo(other);
    }
}
