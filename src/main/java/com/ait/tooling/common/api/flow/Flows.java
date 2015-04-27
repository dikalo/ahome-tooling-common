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

package com.ait.tooling.common.api.flow;

import java.util.Objects;

import com.ait.tooling.common.api.java.util.function.Predicate;

public final class Flows
{
    private Flows()
    {
    }

    public static interface BooleanOp
    {
        public boolean test();
    }

    public static final BooleanOp or(final BooleanOp op, final BooleanOp... ops)
    {
        Objects.requireNonNull(op);

        return new BooleanOp()
        {
            @Override
            public final boolean test()
            {
                if (op.test())
                {
                    return true;
                }
                for (BooleanOp or : ops)
                {
                    if (or.test())
                    {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public static final BooleanOp and(final BooleanOp op, final BooleanOp... ops)
    {
        Objects.requireNonNull(op);
        
        return new BooleanOp()
        {
            @Override
            public final boolean test()
            {
                if (false == op.test())
                {
                    return false;
                }
                for (BooleanOp or : ops)
                {
                    if (false == or.test())
                    {
                        return false;
                    }
                }
                return true;
            }
        };
    }

    public static final BooleanOp not(final BooleanOp op)
    {
        Objects.requireNonNull(op);

        return new BooleanOp()
        {
            @Override
            public final boolean test()
            {
                return (false == op.test());
            }
        };
    }

    public static final <T> BooleanOp compose(final T value, final Predicate<T> predicate)
    {
        Objects.requireNonNull(predicate);

        return new BooleanOp()
        {
            @Override
            public final boolean test()
            {
                return predicate.test(value);
            }
        };
    }

    public static abstract class PredicateBooleanOp<T> implements BooleanOp, Predicate<T>
    {
        private final T m_value;

        public PredicateBooleanOp(final T value)
        {
            m_value = value;
        }

        @Override
        public final boolean test()
        {
            return test(m_value);
        }
    }
}
