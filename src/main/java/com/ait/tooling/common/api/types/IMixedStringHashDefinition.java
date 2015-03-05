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

package com.ait.tooling.common.api.types;

public interface IMixedStringHashDefinition<L extends IMixedListDefinition<L, O>, O extends IMixedStringHashDefinition<L, O>> extends IMixedStringHash
{
    public L getAsArray(String name);

    public Boolean getAsBoolean(String name);

    public Double getAsDouble(String name);

    public Integer getAsInteger(String name);

    public Number getAsNumber(String name);

    public O getAsObject(String name);

    public String getAsString(String name);
}
