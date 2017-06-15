/* Copyright (c) 2016 Gigigo Android Development Team México
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gigigo.template.domain.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Defines a class that provides default values for primitive Java types
 *
 * @author Juan Godínez Vera - 5/30/2017
 * @version 2.0.0
 * @since 2.0.0
 */
public class DefaultValues {

    private static final Map<Class<?>, Object> DEFAULTS;

    static {
        Map<Class<?>, Object> map = new HashMap<Class<?>, Object>();
        put(map, boolean.class, false);
        put(map, char.class, '\0');
        put(map, byte.class, (byte) 0);
        put(map, short.class, (short) 0);
        put(map, int.class, 0);
        put(map, long.class, 0L);
        put(map, float.class, 0f);
        put(map, double.class, 0d);
        put(map, Boolean.class, false);
        put(map, Character.class, '\0');
        put(map, Byte.class, (byte) 0);
        put(map, Short.class, (short) 0);
        put(map, Integer.class, 0);
        put(map, Long.class, 0L);
        put(map, Float.class, 0f);
        put(map, Double.class, 0d);
        DEFAULTS = Collections.unmodifiableMap(map);
    }

    private static <T> void put(Map<Class<?>, Object> map, Class<T> type, T value) {
        map.put(type, value);
    }

    public static <T> T defaultValue(Class<T> type) {
        T t = (T) DEFAULTS.get(checkNotNull(type));
        return t;
    }

    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }

        return reference;
    }

    public static <T> T as(Class<T> classType, Object object, T defaultValue) {
        if(object != null && classType.isInstance(object)) {
            return classType.cast(object);
        }

        return defaultValue;
    }
}
