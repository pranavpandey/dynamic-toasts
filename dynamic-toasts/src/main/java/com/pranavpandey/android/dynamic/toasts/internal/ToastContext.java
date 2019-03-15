/*
 * Copyright 2018 Pranav Pandey
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

package com.pranavpandey.android.dynamic.toasts.internal;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

/**
 * A ContextWrapper to fix bad token exception.
 */
public final class ToastContext extends ContextWrapper {

    /**
     * Boast used by this context wrapper.
     */
    private @NonNull Toast toast;

    /**
     * Constructor to initialize an object of this class.
     *
     * @param base The base context for this wrapper.
     * @param toast The toast for this wrapper.
     */
    public ToastContext(@NonNull Context base, @NonNull Toast toast) {
        super(base);

        this.toast = toast;
    }

    @Override
    public Context getApplicationContext() {
        return new ApplicationContextWrapper(getBaseContext().getApplicationContext());
    }

    /**
     * A ContextWrapper to initialize window manager service.
     */
    final class ApplicationContextWrapper extends ContextWrapper {

        /**
         * Constructor to initialize an object of this class.
         *
         * @param base The base context for this wrapper.
         */
        private ApplicationContextWrapper(@NonNull Context base) {
            super(base);
        }

        @Override
        public Object getSystemService(@NonNull String name) {
            if (Context.WINDOW_SERVICE.equals(name)) {
                return new WindowManagerWrapper(
                        (WindowManager) getBaseContext().getSystemService(name));
            }
            return super.getSystemService(name);
        }
    }

    /**
     * A WindowManager to fix the bad token exception.
     */
    final class WindowManagerWrapper implements WindowManager {

        /**
         * The base window manager used by this wrapper.
         */
        private final @NonNull WindowManager base;

        /**
         * Constructor to initialize an object of this class.
         *
         * @param base The base window manager for this wrapper.
         */
        private WindowManagerWrapper(@NonNull WindowManager base) {
            this.base = base;
        }

        @Override
        public Display getDefaultDisplay() {
            return base.getDefaultDisplay();
        }

        @Override
        public void removeViewImmediate(View view) {
            base.removeViewImmediate(view);
        }

        @Override
        public void addView(View view, ViewGroup.LayoutParams params) {
            try {
                base.addView(view, params);
            } catch (BadTokenException e) {
                e.printStackTrace();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

        @Override
        public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
            base.updateViewLayout(view, params);
        }

        @Override
        public void removeView(View view) {
            base.removeView(view);
        }
    }
}
