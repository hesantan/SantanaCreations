/*
 * Copyright 2016 Santana Creations.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package santanacommon.serialization.interfaces;

/**
 * Interface that defines basic operations for a deserializer
 * @author Santana Creations
 * @param <T1>  The type to deserialize
 * @param <T2> The resulting type of the deserialization
 */
interface IDeserializer<T1, T2> {
    
    /**
     * Deserializes a given object type into another 
     * @param object The object to deserialize
     * @return A object representing the the input object
     * in TOutput type
     */
    T2 deserialize(T1 object);
}
