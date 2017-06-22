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

import android.view.View;
import android.view.ViewGroup;

/**
 * Defines the adapter with base functionality
 *
 * @param <T> data type to use
 * @param <VH> view holder, must be inherited from {@link KViewHolder}
 *
 * @author Adan Gutierrez Ortiz - 10/05/2017
 * @version 2.0.0
 * @since 2.0.0
 */
public abstract class KHeaderFooterAdapter<T, VH extends KViewHolder<T>>
        extends KAdapter<T> {

    private static final int TYPE_HEADER = Integer.MAX_VALUE;
    private static final int TYPE_FOOTER = Integer.MAX_VALUE - 1;
    private static final int ITEM_MAX_TYPE = Integer.MAX_VALUE - 2;
    private KViewHolder<T> headerViewHolder;
    private KViewHolder<T> footerViewHolder;

    class HFViewHolder extends KViewHolder<T> {
        HFViewHolder(View v) {
            super(v);
        }
    }

    public void setHeaderView(View header){
        if (headerViewHolder == null || header != headerViewHolder.itemView) {
            headerViewHolder = new HFViewHolder(header);
            notifyDataSetChanged();
        }
    }

    public void setFooterView(View foot){
        if (footerViewHolder == null || foot != footerViewHolder.itemView) {
            footerViewHolder = new HFViewHolder(foot);
            notifyDataSetChanged();
        }
    }

    public void removeHeader(){
        if (headerViewHolder != null){
            headerViewHolder = null;
            notifyDataSetChanged();
        }
    }

    public void removeFooter(){
        if (footerViewHolder != null){
            footerViewHolder = null;
            notifyDataSetChanged();
        }
    }

    private boolean isHeader(int position){
        return hasHeader() && position == 0;
    }

    private boolean isFooter(int position){
        return hasFooter() && position == getDataItemCount() + (hasHeader() ? 1 : 0);
    }

    private int itemPositionInData(int rvPosition){
        return rvPosition - (hasHeader() ? 1 : 0);
    }

    private int itemPositionInRV(int dataPosition){
        return dataPosition + (hasHeader() ? 1 : 0);
    }

    public void onItemInserted(int itemPosition) {
        notifyItemInserted(itemPositionInRV(itemPosition));
    }

    public void onItemRemoved(int itemPosition) {
        notifyItemRemoved(itemPositionInRV(itemPosition));
    }

    public void onItemChanged(int itemPosition) {
        notifyItemChanged(itemPositionInRV(itemPosition));
    }

    @Override
    public KViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return headerViewHolder;
        } else if (viewType == TYPE_FOOTER) {
            return footerViewHolder;
        }
        return onCreateViewHolderHeaderFooter(parent, viewType);
    }

    @Override
    public void onBindViewHolder(KViewHolder<T> holder, int position) {
        if (!isEmpty() && !isHeader(position) && !isFooter(position)) {
            holder.onBindViewHolder(items().get(itemPositionInData(position)));
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = getDataItemCount();
        if (hasHeader()) {
            itemCount += 1;
        }
        if (hasFooter()) {
            itemCount += 1;
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return TYPE_HEADER;
        }
        if (isFooter(position)) {
            return TYPE_FOOTER;
        }
        int dataItemType = getDataItemType(itemPositionInData(position));
        if (dataItemType > ITEM_MAX_TYPE) {
            throw new IllegalStateException("getDataItemType() must be less than " + ITEM_MAX_TYPE + ".");
        }
        return dataItemType;
    }

    public int getDataItemCount() {
        return super.getItemCount();
    }

    /**
     * make sure your dataItemType < Integer.MAX_VALUE-1
     *
     * @param position item view position in rv
     * @return item viewType
     */
    public int getDataItemType(int position){
        return 0;
    }

    public boolean hasHeader(){
        return headerViewHolder != null;
    }

    public boolean hasFooter(){
        return footerViewHolder != null;
    }

    public abstract VH onCreateViewHolderHeaderFooter(ViewGroup parent, int viewType);
}
