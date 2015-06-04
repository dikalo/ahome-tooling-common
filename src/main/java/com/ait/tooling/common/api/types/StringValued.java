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
import java.util.Objects;

public class StringValued implements IStringValued, Serializable
{
    private static final long serialVersionUID = 6963123034935215387L;

    private final String      m_value;

    public StringValued(final String value)
    {
        m_value = Objects.requireNonNull(value);
    }

    @Override
    public String getValue()
    {
        return m_value;
    }

    @Override
    public String toString()
    {
        return m_value;
    }

    @Override
    public boolean equals(final Object other)
    {
        if ((other == null) || (false == (other instanceof IStringValued)))
        {
            return false;
        }
        if (this == other)
        {
            return true;
        }
        final IStringValued that = ((IStringValued) other);

        return (that.getValue().equals(getValue()));
    }

    @Override
    public int hashCode()
    {
        return getValue().hashCode();
    }
}
