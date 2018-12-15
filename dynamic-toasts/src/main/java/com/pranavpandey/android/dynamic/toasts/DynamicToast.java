/*
 * Copyright 2017 Pranav Pandey
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

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.pranavpandey.android.dynamic.utils.DynamicColorUtils;
import com.pranavpandey.android.dynamic.utils.DynamicDrawableUtils;

/**
 * Helper class to display themed toasts with icon and text. If no color is supplied, it will
 * display default toast based on the vanilla Android.
 */
public class DynamicToast {

    /**
     * Default background color for the toast.
     */
    private static final @ColorInt int ADT_DEFAULT_BG_COLOR =
            Color.parseColor("#454545");

    /**
     * Default tint color for the toast.
     */
    private static final @ColorInt int ADT_DEFAULT_TINT_COLOR =
            Color.parseColor("#FFFFFF");

    /**
     * Default background color for the error toast.
     */
    private static final @ColorInt int ADT_DEFAULT_ERROR_BG_COLOR =
            Color.parseColor("#F44336");

    /**
     * Default background color for the success toast.
     */
    private static final @ColorInt int ADT_DEFAULT_SUCCESS_BG_COLOR =
            Color.parseColor("#4CAF50");

    /**
     * Default background color for the warning toast.
     */
    private static final @ColorInt int ADT_DEFAULT_WARNING_BG_COLOR =
            Color.parseColor("#FFEB3B");

    /**
     * Default value for the {@link #disableIcon}.
     * <p>{@code false} to enable the toast icon.
     */
    private static final boolean ADT_DEFAULT_DISABLE_ICON = false;

    /**
     * Default icon size for the toast in pixels.
     * <p>{@code -1} to use in-built icon size.
     */
    private static final @ColorInt int ADT_DEFAULT_ICON_SIZE = -1;

    /**
     * Default text size for the toast in SP.
     * <p>{@code -1} to use system text size.
     *
     * @see TypedValue#COMPLEX_UNIT_SP;
     */
    private static final @ColorInt int ADT_DEFAULT_TEXT_SIZE = -1;

    /**
     * Background color for the default toast.
     */
    private static @ColorInt int defaultBackgroundColor = ADT_DEFAULT_BG_COLOR;

    /**
     * Tint color for the default toast.
     */
    private static @ColorInt int defaultTintColor = ADT_DEFAULT_TINT_COLOR;

    /**
     * Background color for the error toast.
     */
    private static @ColorInt int errorBackgroundColor = ADT_DEFAULT_ERROR_BG_COLOR;

    /**
     * Background color for the success toast.
     */
    private static @ColorInt int successBackgroundColor = ADT_DEFAULT_SUCCESS_BG_COLOR;

    /**
     * Background color for the warning toast.
     */
    private static @ColorInt int warningBackgroundColor = ADT_DEFAULT_WARNING_BG_COLOR;

    /**
     * Custom icon for the error toast.
     * <p>{@code null} to use the default icon.
     */
    private static Drawable errorIcon = null;

    /**
     * Custom icon for the success toast.
     * <p>{@code null} to use the default icon.
     */
    private static Drawable successIcon = null;

    /**
     * Custom icon for the warning toast.
     * <p>{@code null} to use the default icon.
     */
    private static Drawable warningIcon = null;

    /**
     * {@code true} to disable icon for all the toasts.
     */
    private static boolean disableIcon = ADT_DEFAULT_DISABLE_ICON;

    /**
     * Icon size for the toast in pixels.
     */
    private static int iconSize = ADT_DEFAULT_ICON_SIZE;

    /**
     * Text size for the toast in SP.
     *
     * @see TypedValue#COMPLEX_UNIT_SP;
     */
    private static int textSize = ADT_DEFAULT_TEXT_SIZE;

    /**
     * Custom typeface used by the toast.
     * <p>{@code null} to use the system typeface.
     */
    private static Typeface textTypeface = null;

    /**
     * Custom background used by the toast.
     * <p>{@code null} to use the default background.
     */
    private static Drawable toastBackground = null;

    /**
     * Make a standard toast that just contains a text view.
     * <p>The toast duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast make(@NonNull Context context, @Nullable CharSequence text) {
        return make(context, text, null, defaultTintColor,
                defaultBackgroundColor, Toast.LENGTH_SHORT);
    }

    /**
     * Make a standard toast that just contains a text view.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     * @param duration The duration for the toast, either {@link Toast#LENGTH_SHORT}
     *                 or {@link Toast#LENGTH_LONG}.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast make(@NonNull Context context,
            @Nullable CharSequence text, int duration) {
        return make(context, text, null, defaultTintColor,
                defaultBackgroundColor, duration);
    }

    /**
     * Make a error toast with icon and the text.
     * <p>The toast duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast makeError(@NonNull Context context, @Nullable CharSequence text) {
        return make(context, text, errorIcon != null ? errorIcon
                        : ContextCompat.getDrawable(context, R.drawable.adt_ic_error),
                DynamicColorUtils.getTintColor(errorBackgroundColor), errorBackgroundColor);
    }

    /**
     * Make a error toast with icon and the text.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     * @param duration The duration for the toast, either {@link Toast#LENGTH_SHORT}
     *                 or {@link Toast#LENGTH_LONG}.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast makeError(@NonNull Context context,
            @Nullable CharSequence text, int duration) {
        return make(context, text, ContextCompat.getDrawable(
                context, R.drawable.adt_ic_error),
                DynamicColorUtils.getTintColor(errorBackgroundColor),
                errorBackgroundColor, duration);
    }

    /**
     * Make a success toast with icon and the text.
     * <p>The toast duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast makeSuccess(@NonNull Context context,
            @Nullable CharSequence text) {
        return make(context, text, successIcon != null ? successIcon
                        : ContextCompat.getDrawable(context, R.drawable.adt_ic_success),
                DynamicColorUtils.getTintColor(successBackgroundColor), successBackgroundColor);
    }

    /**
     * Make a success toast with icon and the text.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     * @param duration The duration for the toast, either {@link Toast#LENGTH_SHORT}
     *                 or {@link Toast#LENGTH_LONG}.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast makeSuccess(@NonNull Context context,
            @Nullable CharSequence text, int duration) {
        return make(context, text, ContextCompat.getDrawable(
                context, R.drawable.adt_ic_success),
                DynamicColorUtils.getTintColor(successBackgroundColor),
                successBackgroundColor, duration);
    }

    /**
     * Make a warning toast with icon and the text.
     * <p>The toast duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast makeWarning(@NonNull Context context,
            @Nullable CharSequence text) {
        return make(context, text, warningIcon != null ? warningIcon
                        : ContextCompat.getDrawable(context, R.drawable.adt_ic_warning),
                DynamicColorUtils.getTintColor(warningBackgroundColor), warningBackgroundColor);
    }

    /**
     * Make a warning toast with icon and the text.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     * @param duration The duration for the toast, either {@link Toast#LENGTH_SHORT}
     *                 or {@link Toast#LENGTH_LONG}.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast makeWarning(@NonNull Context context,
            @Nullable CharSequence text, int duration) {
        return make(context, text, ContextCompat.getDrawable(
                context, R.drawable.adt_ic_warning),
                DynamicColorUtils.getTintColor(warningBackgroundColor),
                warningBackgroundColor, duration);
    }

    /**
     * Make a error toast with icon and the text.
     * <p>The toast duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     * @param icon The toast icon to show.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast make(@NonNull Context context,
            @Nullable CharSequence text, @Nullable Drawable icon) {
        return make(context, text, icon, defaultTintColor,
                defaultBackgroundColor, Toast.LENGTH_SHORT);
    }

    /**
     * Make a themed toast with icon and the text.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     * @param icon The toast icon to show.
     * @param duration The duration for the toast, either {@link Toast#LENGTH_SHORT}
     *                 or {@link Toast#LENGTH_LONG}.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast make(@NonNull Context context, @Nullable CharSequence text,
            @Nullable Drawable icon, int duration) {
        return make(context, text, icon, defaultTintColor,
                defaultBackgroundColor, duration);
    }

    /**
     * Make a themed toast with icon and the text.
     * <p>The toast duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     * @param tintColor The toast tint color based on the toast background.
     *                  <p>It will automatically check for the contrast to provide best visibility.
     * @param backgroundColor The toast background color.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast make(@NonNull Context context, @Nullable CharSequence text,
            @ColorInt int tintColor, @ColorInt int backgroundColor) {
        return make(context, text, null, tintColor, backgroundColor, Toast.LENGTH_SHORT);
    }

    /**
     * Make a themed toast with text, background and the tint color.
     * <p>The toast duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     * @param tintColor The toast tint color based on the toast background.
     *                  <p>It will automatically check for the contrast to provide best visibility.
     * @param backgroundColor The toast background color.
     * @param duration The duration for the toast, either {@link Toast#LENGTH_SHORT}
     *                 or {@link Toast#LENGTH_LONG}.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast make(@NonNull Context context, @Nullable CharSequence text,
            @ColorInt int tintColor, @ColorInt int backgroundColor, int duration) {
        return make(context, text, null, tintColor, backgroundColor, duration);
    }

    /**
     * Make a themed toast with text, icon, background and the tint color.
     * <p>The toast duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     * @param icon The toast icon to show.
     * @param tintColor The toast tint color based on the toast background.
     *                  <p>It will automatically check for the contrast to provide best visibility.
     * @param backgroundColor The toast background color.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast make(@NonNull Context context, @Nullable CharSequence text,
            @Nullable Drawable icon, @ColorInt int tintColor, @ColorInt int backgroundColor) {
        return make(context, text, icon, tintColor, backgroundColor, Toast.LENGTH_SHORT);
    }

    /**
     * Make a themed toast with text, icon, background and the tint color.
     *
     * @param context The context to use.
     * @param text The text to show. Can be formatted text.
     * @param icon The toast icon to show.
     * @param tintColor The toast tint color based on the toast background.
     *                  <p>It will automatically check for the contrast to provide best visibility.
     * @param backgroundColor The toast background color.
     * @param duration The duration for the toast, either {@link Toast#LENGTH_SHORT}
     *                 or {@link Toast#LENGTH_LONG}.
     *
     * @return The toast with the supplied parameters.
     *         <p>Use {@link Toast#show()} to display the toast.
     */
    public static @NonNull Toast make(@NonNull Context context, @Nullable CharSequence text,
            @Nullable Drawable icon, @ColorInt int tintColor, @ColorInt int backgroundColor,
            int duration) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            context = context.getApplicationContext();
        }

        tintColor = DynamicColorUtils.getContrastColor(tintColor, backgroundColor);

        Toast toast = new Toast(context);
        View toastLayout = LayoutInflater.from(context).inflate(
                R.layout.adt_layout_toast, new LinearLayout(context), false);
        ImageView toastIcon = toastLayout.findViewById(R.id.adt_toast_icon);
        TextView toastText = toastLayout.findViewById(R.id.adt_toast_text);

        if (icon != null && !disableIcon) {
            if (iconSize != ADT_DEFAULT_ICON_SIZE) {
                toastIcon.getLayoutParams().width = iconSize;
                toastIcon.getLayoutParams().height = iconSize;
                toastIcon.requestLayout();
            }
            toastIcon.setColorFilter(tintColor);
            toastIcon.setImageDrawable(icon);
        } else {
            toastIcon.setVisibility(View.GONE);
        }

        if (textTypeface != null) {
            toastText.setTypeface(textTypeface);
        }
        if (textSize != ADT_DEFAULT_TEXT_SIZE) {
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
                            ContextCompat.getDrawable(context, R.drawable.adt_toast_background),
                            backgroundColor, PorterDuff.Mode.MULTIPLY));
        }

        toast.setDuration(duration);
        toast.setView(toastLayout);

        return toast;
    }

    /**
     * Configuration class to customise the toast attributes.
     */
    public static class Config {

        /**
         * Singleton instance of {@link Config}.
         */
        private static Config sInstance;

        /**
         * Background color for the default toast.
         */
        private @ColorInt int defaultBackgroundColor = DynamicToast.defaultBackgroundColor;

        /**
         * Tint color for the default toast.
         */
        private @ColorInt int defaultTintColor = DynamicToast.defaultTintColor;

        /**
         * Background color for the error toast.
         */
        private @ColorInt int errorBackgroundColor = DynamicToast.errorBackgroundColor;

        /**
         * Background color for the success toast.
         */
        private @ColorInt int successBackgroundColor = DynamicToast.successBackgroundColor;

        /**
         * Background color for the warning toast.
         */
        private @ColorInt int warningBackgroundColor = DynamicToast.warningBackgroundColor;

        /**
         * Custom icon for the error toast.
         */
        private Drawable errorIcon = DynamicToast.errorIcon;

        /**
         * Custom icon for the success toast.
         */
        private Drawable successIcon = DynamicToast.successIcon;

        /**
         * Custom icon for the warning toast.
         */
        private Drawable warningIcon = DynamicToast.warningIcon;

        /**
         * {@code true} to disable icon for all the toasts.
         */
        private boolean disableIcon = DynamicToast.disableIcon;

        /**
         * Icon size for the toast in pixels.
         */
        private int iconSize = DynamicToast.iconSize;

        /**
         * Text size for the toast in SP.
         *
         * @see TypedValue#COMPLEX_UNIT_SP;
         */
        private @ColorInt int textSize = DynamicToast.textSize;

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
         * Get instance to access public methods. Must be called before accessing methods.
         *
         * @return The singleton instance of this class.
         */
        public static Config getInstance() {
            if (sInstance == null) {
                sInstance = new Config();
            }

            return sInstance;
        }

        /**
         * Set the default background color.
         *
         * @param defaultBackgroundColor The background color to be set.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setDefaultBackgroundColor(@ColorInt int defaultBackgroundColor) {
            this.defaultBackgroundColor = defaultBackgroundColor;

            return this;
        }

        /**
         * Set the default tint color.
         *
         * @param defaultTintColor The tint color to be set.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setDefaultTintColor(@ColorInt int defaultTintColor) {
            this.defaultTintColor = defaultTintColor;

            return this;
        }

        /**
         * Set the error background color.
         *
         * @param errorBackgroundColor The error background color to be set.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setErrorBackgroundColor(@ColorInt int errorBackgroundColor) {
            this.errorBackgroundColor = errorBackgroundColor;

            return this;
        }

        /**
         * Set the success background color.
         *
         * @param successBackgroundColor The success background color
         *         to be set.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setSuccessBackgroundColor(@ColorInt int successBackgroundColor) {
            this.successBackgroundColor = successBackgroundColor;

            return this;
        }

        /**
         * Set the warning background color.
         *
         * @param warningBackgroundColor The warning background color to be set.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setWarningBackgroundColor(@ColorInt int warningBackgroundColor) {
            this.warningBackgroundColor = warningBackgroundColor;

            return this;
        }

        /**
         * Set the error icon.
         * <p>Pass {@code null} to use the default icon.
         *
         * @param errorIcon The error icon to be set.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setErrorIcon(@Nullable Drawable errorIcon) {
            this.errorIcon = errorIcon;

            return this;
        }

        /**
         * Set the success icon.
         * <p>Pass {@code null} to use the default icon.
         *
         * @param successIcon The success icon to be set.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setSuccessIcon(@Nullable Drawable successIcon) {
            this.successIcon = successIcon;

            return this;
        }

        /**
         * Set the warning icon.
         * <p>Pass {@code null} to use the default icon.
         *
         * @param warningIcon The warning icon to be set.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setWarningIcon(@Nullable Drawable warningIcon) {
            this.warningIcon = warningIcon;

            return this;
        }

        /**
         * Set the icon visibility.
         *
         * @param disableIcon {@code true} to disable icon for all the hints.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setDisableIcon(boolean disableIcon) {
            this.disableIcon = disableIcon;

            return this;
        }

        /**
         * Set the icon size.
         *
         * @param iconSize The icon size to be set in {@code pixels}.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setIconSize(int iconSize) {
            this.iconSize = iconSize;

            return this;
        }

        /**
         * Set the text size.
         *
         * @param textSize The text size to be set in {@code sp}.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setTextSize(int textSize) {
            this.textSize = textSize;

            return this;
        }

        /**
         * Set the text typeface.
         * <p>Pass {@code null} to use the default typeface.
         *
         * @param textTypeface The text typeface to be set.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setTextTypeface(@Nullable Typeface textTypeface) {
            this.textTypeface = textTypeface;

            return this;
        }

        /**
         * Set the toast background.
         * <p>Pass {@code null} to use the default background.
         *
         * @param toastBackground The toast background to be set.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public Config setToastBackground(@Nullable Drawable toastBackground) {
            this.toastBackground = toastBackground;

            return this;
        }

        /**
         * Apply customisations.
         */
        public void apply() {
            DynamicToast.defaultBackgroundColor = defaultBackgroundColor;
            DynamicToast.defaultTintColor = defaultTintColor;
            DynamicToast.errorBackgroundColor = errorBackgroundColor;
            DynamicToast.successBackgroundColor = successBackgroundColor;
            DynamicToast.warningBackgroundColor = warningBackgroundColor;
            DynamicToast.errorIcon = errorIcon;
            DynamicToast.successIcon = successIcon;
            DynamicToast.warningIcon = warningIcon;
            DynamicToast.disableIcon = disableIcon;
            DynamicToast.iconSize = iconSize;
            DynamicToast.textSize = textSize;
            DynamicToast.textTypeface = textTypeface;
            DynamicToast.toastBackground = toastBackground;

            sInstance = null;
        }

        /**
         * Reset customisations.
         */
        public void reset() {
            DynamicToast.defaultBackgroundColor = ADT_DEFAULT_BG_COLOR;
            DynamicToast.defaultTintColor = ADT_DEFAULT_TINT_COLOR;
            DynamicToast.errorBackgroundColor = ADT_DEFAULT_ERROR_BG_COLOR;
            DynamicToast.successBackgroundColor = ADT_DEFAULT_SUCCESS_BG_COLOR;
            DynamicToast.warningBackgroundColor = ADT_DEFAULT_WARNING_BG_COLOR;
            DynamicToast.errorIcon = null;
            DynamicToast.successIcon = null;
            DynamicToast.warningIcon = null;
            DynamicToast.disableIcon = ADT_DEFAULT_DISABLE_ICON;
            DynamicToast.iconSize = ADT_DEFAULT_ICON_SIZE;
            DynamicToast.textSize = ADT_DEFAULT_TEXT_SIZE;
            DynamicToast.textTypeface = null;
            DynamicToast.toastBackground = null;

            sInstance = null;
        }
    }
}
