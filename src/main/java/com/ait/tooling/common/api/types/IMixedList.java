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

public interface IMixedList extends Serializable
{
    public void clear();

    public boolean isArray(int index);

    public boolean isBoolean(int index);

    public boolean isDouble(int index);

    public boolean isEmpty();

    public boolean isInteger(int index);

    public boolean isNull(int index);

    public boolean isNumber(int index);

    public boolean isObject(int index);

    public boolean isString(int index);

    public boolean isNativeFunction(int index);

    public int size();
}
