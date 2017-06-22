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

package gigigo.com.kmvp;

/**
 * Defines an interface for manipulation operations on a collection of a specified type
 *
 * @param <T> data type to use
 *
 * @author Juan Godínez Vera - 26/04/2017
 * @author Adan Gutierrez Ortiz - 09/05/2017
 * @version 2.0.0
 * @since 2.0.0
 */
public interface IEnumerable<T> {

    /**
     * Adds an object to the end of the sequence
     * @param item
     */
    void add(T item);

    /**
     * Adds the elements of the specified collection to the end of the sequence
     * @param items
     */
    void addRange(Iterable<T> items);

    /**
     * Adds the array elements of the specified collection to the end of the sequence
     * @param items
     */
    void addRange(T... items);

    /**
     * Replaces the elements of the specified collection on the sequence
     * @param items
     */
    void set(Iterable<T> items);

    /**
     * Replaces the array elements of the specified collection on the sequence
     * @param items
     */
    void set(T... items);

    /**
     * Updates the first occurrence of a specific object from the sequence
     * @param item
     */
    void update(T item);

    /**
     * Removes the first occurrence of a specific object from the sequence
     * @param item
     */
    void remove(T item);

    /**
     * Removes the object at the specified position in the sequence
     * @param index
     */
    void remove(int index);

    /**
     * Retrieves all the elements that match the conditions defined by the specified predicate.
     * @param predicate
     * @return
     */
    Iterable<T> where(IPredicate<T> predicate);

    /**
     * Removes all elements from the sequence
     */
    void clear();

    /**
     * Gets all elements from the sequence
     * @return
     */
    Iterable<T> items();
}
