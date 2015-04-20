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

package com.ait.tooling.common.api.hash;

import java.util.Objects;

import com.ait.tooling.common.api.java.util.StringOps;

public final class Hasher implements ISHA_512_HASH_SALT
{
    private final ISHA_512_HASH m_hash;

    public Hasher(final ISHA_512_HASH hash)
    {
        m_hash = Objects.requireNonNull(hash);
    }

    @Override
    public final String SHA512(String text, String salt)
    {
        text = Objects.requireNonNull(text);

        salt = StringOps.requireTrimOrNull(salt);

        return SHA512(text, salt, salt.length());
    }

    @Override
    public final String SHA512(String text, String salt, int iter)
    {
        text = Objects.requireNonNull(text);

        salt = StringOps.requireTrimOrNull(salt);

        if (iter < 1)
        {
            return m_hash.SHA512(text + salt);
        }
        if ((iter < 5) || (iter > 25))
        {
            iter = 13;
        }
        for (int i = 0; i < iter; i++)
        {
            if ((i % 2) == 0)
            {
                text = text + salt;
            }
            else
            {
                text = salt + text;
            }
            text = StringOps.reverse(m_hash.SHA512(text));
        }
        return text;
    }
}
