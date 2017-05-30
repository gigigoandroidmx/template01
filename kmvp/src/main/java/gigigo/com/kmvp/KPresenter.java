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

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines the presenter with base functionality
 * @param <V> the kmvp view
 *
 * @author Juan Godínez Vera - 21/02/2017
 * @author Daniel Moises Ruiz Pérez - 21/02/2017
 * @version 2.0.0
 * @since 1.0.0
 */
public abstract class KPresenter<V extends IView>
        implements IPresenter<V> {

    protected final String TAG = getClass().getSimpleName();

    private WeakReference<V> viewReference;
    private boolean forceUpdate;
    private List<Object> parameters;

    /**
     * Defines the method to be called to attaches the view
     *
     * @param view kmvp view
     */
    @UiThread
    public void attachView(V view) {
        viewReference = new WeakReference<V>(view);
    }

    /**
     * Defines the method to be called when the view has been released
     */
    @UiThread
    public void detachView() {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }

    /**
     * Defines the method to be invoked when needs refresh the data source.
     * Default value is <code>false</code>
     *
     * @param force <code>true</code>, whether needs refresh, otherwise <code>false</code>
     */
    @Override
    public void forceUpdate(boolean force) {
        forceUpdate = force;
    }

    /**
     * Determines if the view is attached
     * @return <code>true</code>, whether view is attached, otherwise <code>false</code>
     */
    @UiThread
    public boolean isViewAttached() {
        return viewReference != null && viewReference.get() != null;
    }

    /**
     * Gets the attached view
     * @return <code>null</code>, whether view is not attached, otherwise the concrete view instance
     */
    @UiThread
    public V getView() {
        return viewReference == null ? null : viewReference.get();
    }

    /**
     * Gets whether view needs refresh the data source
     * @return <code>true</code>, whether needs refresh, otherwise <code>false</code>
     */
    public boolean isForceUpdate() {
        return forceUpdate;
    }

    /**
     * Defines the method to sets the parameters to be invoked when the api so requires
     *
     * @param parameters list of parameters
     */
    public void setParams(List<Object> parameters) {
        if(parameters == null) return;

        this.parameters = parameters;
    }

    /**
     * Defines the method to sets the parameters to be invoked when the api so requires
     *
     * @param parameters array of parameters
     */
    public void setParams(Object ... parameters) {
        if(parameters == null) return;

        ArrayList<Object> arrayList = new ArrayList(parameters.length);

        for(Object item : parameters) {
            arrayList.add(item);
        }

        setParams(arrayList);
    }

    /**
     * Gets the the parameters to be invoked by the api
     * @return the parameters
     */
    public List<Object> getParams() {
        return this.parameters;
    }
}
