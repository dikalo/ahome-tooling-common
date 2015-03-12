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

package com.ait.tooling.common.api.factory;

import java.util.Objects;

import com.ait.tooling.common.api.types.ITimeStamped;
import com.ait.tooling.common.api.types.IValued;

public final class TTLCachedValue<T> implements IValued<T>, ITimeStamped
{
    private final T    m_value;

    private final long m_stamp;

    public TTLCachedValue(final T value, final long timestamp)
    {
        m_value = Objects.requireNonNull(value);

        m_stamp = Math.max(timestamp, 0);
    }

    @Override
    public final T getValue()
    {
        return m_value;
    }

    @Override
    public final long getTimeStamp()
    {
        return m_stamp;
    }
}