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

import android.support.annotation.UiThread;

/**
 * Defines the base interface for every kmvp presenter
 *
 * @param <V> the kmvp view
 *
 * @author Juan Godínez Vera - 22/12/2016
 * @author Daniel Moises Ruiz Pérez - 22/12/2016
 * @version 2.0.0
 * @since 1.0.0
 */
public interface IPresenter<V extends IView> {

    /**
     * Defines the method to be called to attaches the view
     *
     * @param view kmvp view
     */
    @UiThread
    void attachView(V view);

    /**
     * Defines the method to be called when the view has been released
     */
    @UiThread
    void detachView();

    /**
     * Defines the method to be invoked when needs refresh the data source.
     * Default value is <code>false</code>
     * @param force <code>true</code>, whether needs refresh, otherwise <code>false</code>
     */
    void forceUpdate(boolean force);

}
