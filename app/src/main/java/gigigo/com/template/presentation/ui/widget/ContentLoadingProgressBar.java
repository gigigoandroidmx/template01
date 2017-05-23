/*
 * Copyright (C) 2013 The Android Open Source Project
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

package gigigo.com.template.presentation.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;

/**
 * @author https://gist.github.com/anonymous/8668391
 *
 * ContentLoadingProgressBar implements a ProgressBar that waits a minimum time to be
 * dismissed before showing. Once visible, the progress bar will be visible for
 * a minimum amount of time to avoid "flashes" in the UI when an event could take
 * a largely variable time to complete (from none, to a user perceivable amount)
 */
public class ContentLoadingProgressBar
        extends ProgressBar {
    private static final int MIN_SHOW_TIME = 500; // ms
    private static final int MIN_DELAY = 500; // ms

    private long startTime = -1;
    private boolean postedHide = false;
    private boolean postedShow = false;
    private boolean dismissed = false;

    private final Runnable delayedHide = new Runnable() {
        @Override
        public void run() {
            postedHide = false;
            startTime = -1;
            setVisibility(View.GONE);
        }
    };

    private final Runnable delayedShow = new Runnable() {
        @Override
        public void run() {
            postedShow = false;
            if (!dismissed) {
                startTime = System.currentTimeMillis();
                setVisibility(View.VISIBLE);
            }
        }
    };

    public ContentLoadingProgressBar(Context context) {
        super(context);
    }

    public ContentLoadingProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ContentLoadingProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks();
    }

    private void removeCallbacks() {
        removeCallbacks(delayedHide);
        removeCallbacks(delayedShow);
    }

    /**
     * Hide the progress view if it is visible. The progress view will not be
     * hidden until it has been shown for at least a minimum show time. If the
     * progress view was not yet visible, cancels showing the progress view.
     */
    public void hide() {
        dismissed = true;
        removeCallbacks(delayedShow);
        long diff = System.currentTimeMillis() - startTime;
        if (diff >= MIN_SHOW_TIME || startTime == -1) {
            // The progress spinner has been shown long enough
            // OR was not shown yet. If it wasn't shown yet,
            // it will just never be shown.
            setVisibility(View.GONE);
        } else {
            // The progress spinner is shown, but not long enough,
            // so put a delayed message in to hide it when its been
            // shown long enough.
            if (!postedHide) {
                postDelayed(delayedHide, MIN_SHOW_TIME - diff);
                postedHide = true;
            }
        }
    }

    /**
     * Show the progress view after waiting for a minimum delay. If
     * during that time, hide() is called, the view is never made visible.
     */
    public void show() {
        // Reset the start time.
        startTime = -1;
        dismissed = false;
        removeCallbacks(delayedHide);
        if (!postedShow) {
            postDelayed(delayedShow, MIN_DELAY);
            postedShow = true;
        }
    }
}
