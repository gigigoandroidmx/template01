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

package gigigo.com.kmvp.domain;

import java.util.List;

/**
 * Defines the base interface for every kmvp interactor
 *
 * @author Juan Godínez Vera - 22/12/2016
 * @author Daniel Moises Ruiz Pérez - 22/12/2016
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IInteractor {

    /**
     * Defines the method to sets the parameters to be invoked when the api so requires
     * @param parameters list of parameters
     */
    void setParams(List<Object> parameters);

    /**
     * Defines the method to be invoked when the use case is executed
     */
    void run();

}
