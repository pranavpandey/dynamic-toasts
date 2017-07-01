/*
 * Copyright (C) 2017 Pranav Pandey
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

package com.pranavpandey.android.dynamic.toasts;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pranavpandey.android.dynamic.utils.DynamicColorUtils;
import com.pranavpandey.android.dynamic.utils.DynamicDrawableUtils;

/**
 * A simple library to display themed toasts with icon and text. If no
 * color is supplied, it will display default toast based on vanilla Android.
 */
public class DynamicToast {

    /**
     * Default toastBackground color for the toast.
     */
    private static final @ColorInt int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#454545");

    /**
     * Background color for the error toast.
     */
    private static final @ColorInt int ERROR_BACKGROUND_COLOR = Color.parseColor("#F44336");

    /**
     * Background color for the success toast.
     */
    private static final @ColorInt int SUCCESS_BACKGROUND_COLOR = Color.parseColor("#4CAF50");

    /**
     * Background color for the warning toast.
     */
    private static final @ColorInt int WARNING_BACKGROUND_COLOR = Color.parseColor("#FFEB3B");

    /**
     * Default tint color for the toast.
     */
    private static final @ColorInt int DEFAULT_TINT_COLOR = Color.parseColor("#FFFFFF");

    /**
     * Default text size for the toast in SP. {@code -1} to use system
     * text size.
     */
    private static final @ColorInt int DEFAULT_TEXT_SIZE = -1;

    /**
     * Default typeface used by the toast. {@code null} to use system
     * typeface.
     */
    private static final Typeface DEFAULT_TEXT_TYPEFACE = null;

    /**
     * Default background used by the toast. {@code null} to use system
     * typeface.
     */
    private static final Drawable DEFAULT_TOAST_BACKGROUND = null;

    /**
     * Text size for the toast in SP.
     */
    private static int textSize = DEFAULT_TEXT_SIZE;

    /**
     * Custom typeface used by the toast.
     */
    private static Typeface textTypeface = DEFAULT_TEXT_TYPEFACE;

    /**
     * Custom background used by the toast.
     */
    private static Drawable toastBackground = DEFAULT_TOAST_BACKGROUND;

    /**
     * Make a standard toast that just contains a text view. Toast duration will
     * be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     *
     * @return Toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast make(@NonNull Context context, @NonNull CharSequence text) {
        return make(context, text, null, DEFAULT_TINT_COLOR,
                DEFAULT_BACKGROUND_COLOR, Toast.LENGTH_SHORT);
    }

    /**
     * Make a standard toast that just contains a text view.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param duration How long to display the message. Either
     *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}.
     *
     * @return Toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast make(@NonNull Context context,
                             @NonNull CharSequence text, int duration) {
        return make(context, text, null, DEFAULT_TINT_COLOR,
                DEFAULT_BACKGROUND_COLOR, duration);
    }

    /**
     * Make a error toast with icon and the text. Toast duration will
     * be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     *
     * @return Error toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast makeError(@NonNull Context context, @NonNull CharSequence text) {
        return make(context, text, ContextCompat.getDrawable(
                context, R.drawable.adt_ic_error),
                DynamicColorUtils.getTintColor(ERROR_BACKGROUND_COLOR),
                ERROR_BACKGROUND_COLOR);
    }

    /**
     * Make a error toast with icon and the text.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param duration How long to display the message. Either
     *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}.
     *
     * @return Error toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast makeError(@NonNull Context context,
                             @NonNull CharSequence text, int duration) {
        return make(context, text, ContextCompat.getDrawable(
                context, R.drawable.adt_ic_error),
                DynamicColorUtils.getTintColor(ERROR_BACKGROUND_COLOR),
                ERROR_BACKGROUND_COLOR,
                duration);
    }

    /**
     * Make a success toast with icon and the text. Toast duration will
     * be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     *
     * @return Success toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast makeSuccess(@NonNull Context context, @NonNull CharSequence text) {
        return make(context, text, ContextCompat.getDrawable(
                context, R.drawable.adt_ic_success),
                DynamicColorUtils.getTintColor(SUCCESS_BACKGROUND_COLOR),
                SUCCESS_BACKGROUND_COLOR);
    }

    /**
     * Make a success toast with icon and the text.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param duration How long to display the message. Either
     *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}.
     *
     * @return Success toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast makeSuccess(@NonNull Context context,
                                  @NonNull CharSequence text, int duration) {
        return make(context, text, ContextCompat.getDrawable(
                context, R.drawable.adt_ic_success),
                DynamicColorUtils.getTintColor(SUCCESS_BACKGROUND_COLOR),
                SUCCESS_BACKGROUND_COLOR,
                duration);
    }

    /**
     * Make a warning toast with icon and the text. Toast duration will
     * be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     *
     * @return Warning toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast makeWarning(@NonNull Context context, @NonNull CharSequence text) {
        return make(context, text, ContextCompat.getDrawable(
                context, R.drawable.adt_ic_warning),
                DynamicColorUtils.getTintColor(WARNING_BACKGROUND_COLOR),
                WARNING_BACKGROUND_COLOR);
    }

    /**
     * Make a warning toast with icon and the text.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param duration How long to display the message. Either
     *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}.
     *
     * @return Warning toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast makeWarning(@NonNull Context context,
                                    @NonNull CharSequence text, int duration) {
        return make(context, text, ContextCompat.getDrawable(
                context, R.drawable.adt_ic_warning),
                DynamicColorUtils.getTintColor(WARNING_BACKGROUND_COLOR),
                WARNING_BACKGROUND_COLOR,
                duration);
    }

    /**
     * Make a themed toast with icon and the text. Toast duration
     * will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param icon The toast icon to show.
     *
     * @return Toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast make(@NonNull Context context, @NonNull CharSequence text,
                             @NonNull Drawable icon) {
        return make(context, text, icon, DEFAULT_TINT_COLOR,
                DEFAULT_BACKGROUND_COLOR, Toast.LENGTH_SHORT);
    }

    /**
     * Make a themed toast with icon and the text.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param icon The toast icon to show.
     * @param duration How long to display the message. Either
     *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}.
     *
     * @return Toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast make(@NonNull Context context, @NonNull CharSequence text,
                             @NonNull Drawable icon, int duration) {
        return make(context, text, icon, DEFAULT_TINT_COLOR,
                DEFAULT_BACKGROUND_COLOR, duration);
    }

    /**
     * Make a themed toast with text, toastBackground and the tint color. Toast
     * duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param tintColor The toast tint color based on the toastBackground. It will
     *                  automatically check for the contrast to provide best
     *                  visibility.
     * @param backgroundColor The toast toastBackground color.
     *
     * @return Toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast make(@NonNull Context context, @NonNull CharSequence text,
                             @ColorInt int tintColor, @ColorInt int backgroundColor) {
        return make(context, text, null, tintColor, backgroundColor, Toast.LENGTH_SHORT);
    }

    /**
     * Make a themed toast with text, toastBackground and the tint color. Toast
     * duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param tintColor The toast tint color based on the toastBackground. It will
     *                  automatically check for the contrast to provide best
     *                  visibility.
     * @param backgroundColor The toast toastBackground color.
     * @param duration How long to display the message. Either
     *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}.
     *
     * @return Toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast make(@NonNull Context context, @NonNull CharSequence text,
                             @ColorInt int tintColor, @ColorInt int backgroundColor,
                             int duration) {
        return make(context, text, null, tintColor, backgroundColor, duration);
    }

    /**
     * Make a themed toast with text, icon, toastBackground and the tint color. Toast
     * duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param icon The toast icon to show.
     * @param tintColor The toast tint color based on the toastBackground. It will
     *                  automatically check for the contrast to provide best
     *                  visibility.
     * @param backgroundColor The toast toastBackground color.
     *
     * @return Toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast make(@NonNull Context context, @NonNull CharSequence text,
                             @NonNull Drawable icon, @ColorInt int tintColor,
                             @ColorInt int backgroundColor) {
        return make(context, text, icon, tintColor, backgroundColor, Toast.LENGTH_SHORT);
    }

    /**
     * Make a themed toast with text, icon, toastBackground and the tint color.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param icon The toast icon to show.
     * @param tintColor The toast tint color based on the toastBackground. It will
     *                  automatically check for the contrast to provide best
     *                  visibility.
     * @param backgroundColor The toast toastBackground color.
     * @param duration How long to display the message. Either
     *                 {@link Toast#LENGTH_SHORT} or {@link Toast#LENGTH_LONG}.
     *
     * @return Toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast make(@NonNull Context context, @NonNull CharSequence text,
                             @Nullable Drawable icon, @ColorInt int tintColor,
                             @ColorInt int backgroundColor, int duration) {
        final Toast toast = new Toast(context);

        final View toastLayout = ((LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.adt_layout_toast,
                        new LinearLayout(context), false);

        final ImageView toastIcon = (ImageView)
                toastLayout.findViewById(R.id.adt_toast_icon);
        final TextView toastText =
                (TextView) toastLayout.findViewById(R.id.adt_toast_text);

        tintColor = DynamicColorUtils.getContrastColor(tintColor, backgroundColor);

        if (icon != null) {
            toastIcon.setColorFilter(tintColor);
            toastIcon.setImageDrawable(icon);
        } else {
            toastIcon.setVisibility(View.GONE);
        }

        if (textTypeface != null) {
            toastText.setTypeface(textTypeface);
        }
        if (textSize != DEFAULT_TEXT_SIZE) {
            toastText.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        }
        toastText.setTextColor(tintColor);
        toastText.setText(text);

        if (toastBackground != null) {
            DynamicDrawableUtils.setBackground(toastLayout,
                    DynamicDrawableUtils.colorizeDrawable(toastBackground,
                            backgroundColor, PorterDuff.Mode.MULTIPLY));
        } else {
            DynamicDrawableUtils.setBackground(toastLayout,
                    DynamicDrawableUtils.colorizeDrawable(
                    ContextCompat.getDrawable(context, R.drawable.adt_toast_frame),
                    backgroundColor, PorterDuff.Mode.MULTIPLY));
        }

        toast.setDuration(duration);
        toast.setView(toastLayout);

        return toast;
    }

    /**
     * Configuration class to customise toast attributes.
     */
    public static class Config {

        /**
         * Singleton instance of {@link Config}.
         */
        private static Config sInstance;

        /**
         * Text size for the toast in SP.
         */
        private int textSize = DynamicToast.textSize;

        /**
         * Custom text typeface used by the toast.
         */
        private Typeface textTypeface = null;

        /**
         * Custom background used by the toast.
         */
        private Drawable toastBackground = null;

        /**
         * Making default constructor private to avoid instantiation.
         */
        private Config() { }

        /**
         * Get instance to access public methods. Must be called before accessing
         * methods.
         *
         * @return {@link #sInstance} Singleton {@link Config} instance.
         */
        public static Config getInstance() {
            if (sInstance == null) {
                sInstance = new Config();
            }

            return sInstance;
        }

        /**
         * Setter for {@link #textSize}.
         *
         * @return {@link Config} object to allow for chaining of calls to set
         *         methods.
         */
        public Config setTextSize(int textSize) {
            this.textSize = textSize;

            return this;
        }

        /**
         * Setter for {@link #textTypeface}.
         *
         * @return {@link Config} object to allow for chaining of calls to set
         *         methods.
         */
        public Config setTextTypeface(@NonNull Typeface textTypeface) {
            this.textTypeface = textTypeface;

            return this;
        }

        /**
         * Setter for {@link #toastBackground}.
         *
         * @return {@link Config} object to allow for chaining of calls to set
         *         methods.
         */
        public Config setToastBackground(@NonNull Drawable toastBackground) {
            this.toastBackground = toastBackground;

            return this;
        }

        /**
         * Apply customisations.
         */
        public void apply() {
            DynamicToast.textSize = textSize;
            DynamicToast.textTypeface = textTypeface;
            DynamicToast.toastBackground = toastBackground;

            sInstance = null;
        }

        /**
         * Reset customisations.
         */
        public void reset() {
            DynamicToast.textSize = DynamicToast.DEFAULT_TEXT_SIZE;
            DynamicToast.textTypeface = DynamicToast.DEFAULT_TEXT_TYPEFACE;
            DynamicToast.toastBackground = DynamicToast.DEFAULT_TOAST_BACKGROUND;

            sInstance = null;
        }
    }
}
