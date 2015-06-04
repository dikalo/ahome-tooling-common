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

package com.ait.tooling.common.api.types;

import java.io.Serializable;

public class SearchResult<T extends ISearchable<T>> implements ISearchResult<T>, Serializable
{
    private static final long serialVersionUID = 2440955017561840921L;

    public final String       m_id;

    public final String       m_mime;

    public final String       m_prop;

    public final String       m_desc;

    public final T            m_valu;

    public SearchResult(final T valu, final String id, final String mime, final String prop, final String desc)
    {
        m_id = id;

        m_mime = mime;

        m_prop = prop;

        m_valu = valu;

        m_desc = desc;
    }

    @Override
    public String getID()
    {
        return m_id;
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
}
