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

package com.ait.tooling.common.api.json;

import com.ait.tooling.common.api.types.IMixedStringHashDefinition;

public interface JSONObjectDefinition<L extends JSONArrayDefinition<L, O>, O extends JSONObjectDefinition<L, O>> extends IMixedStringHashDefinition<L, O>, JSONStringifyStrict
{
    public JSONType getJSONType(String name);

    public boolean isJSONType(String name, JSONType type);
}