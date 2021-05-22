/*
 * Copyright 2017-2021 Pranav Pandey
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
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
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

import com.pranavpandey.android.dynamic.toasts.internal.ToastCompat;
import com.pranavpandey.android.dynamic.utils.DynamicColorUtils;
import com.pranavpandey.android.dynamic.utils.DynamicDrawableUtils;
import com.pranavpandey.android.dynamic.utils.DynamicUnitUtils;

/**
 * Helper class to display themed cheat sheets with icon and text by using {@link Toast}.
 * <p>If no color is supplied, it will display default hint based on the Android support library.
 */
public class DynamicHint {

    /**
     * The minimum top inset to mimic status bar.
     */
    private static final int ADT_INSET_TOP = 32;

    /**
     * The minimum height for anchor, in dips (density-independent pixels).
     * <p>It will be used to avoid overlapping for the smaller view height.
     */
    private static final int ADT_MIN_ANCHOR_HEIGHT = 48;

    /**
     * The toast offset, in dips (density-independent pixels).
     * <p>It will be used to determine whether the toast should appear above or below the UI element.
     */
    private static final int ADT_TOAST_OFFSET = 4;

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
     * Default value for the {@link #tintIcon}.
     * <p>{@code true} to tint the toast icon.
     */
    private static final boolean ADT_DEFAULT_TINT_ICON = true;

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
    private static @Nullable @ColorInt Integer defaultBackgroundColor = ADT_DEFAULT_BG_COLOR;

    /**
     * Tint color for the default toast.
     */
    private static @Nullable @ColorInt Integer defaultTintColor = ADT_DEFAULT_TINT_COLOR;

    /**
     * Background color for the error toast.
     */
    private static @Nullable @ColorInt Integer errorBackgroundColor = ADT_DEFAULT_ERROR_BG_COLOR;

    /**
     * Background color for the success toast.
     */
    private static @Nullable @ColorInt Integer successBackgroundColor =
            ADT_DEFAULT_SUCCESS_BG_COLOR;

    /**
     * Background color for the warning toast.
     */
    private static @Nullable @ColorInt Integer warningBackgroundColor =
            ADT_DEFAULT_WARNING_BG_COLOR;

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
     * {@code true} to tint icon for all the toasts.
     */
    private static boolean tintIcon = ADT_DEFAULT_TINT_ICON;

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
     * Generate tint color according to the supplied color, otherwise return the default value.
     *
     * @param color The color to be used to generate the tint color.
     * @param defaultColor The default value for the tint color.
     *
     * @return The generated tint color according to the supplied color, otherwise return the
     *         default value.
     */
    private static @Nullable @ColorInt Integer generateTintColor(
            @Nullable @ColorInt Integer color, @Nullable @ColorInt Integer defaultColor) {
        if (color != null) {
            return DynamicColorUtils.getTintColor(color);
        }

        return defaultColor;
    }

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
                generateTintColor(errorBackgroundColor, defaultTintColor), errorBackgroundColor);
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
        return make(context, text, ContextCompat.getDrawable(context, R.drawable.adt_ic_error),
                generateTintColor(errorBackgroundColor, defaultTintColor),
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
                generateTintColor(successBackgroundColor, defaultTintColor), successBackgroundColor);
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
        return make(context, text, ContextCompat.getDrawable(context, R.drawable.adt_ic_success),
                generateTintColor(successBackgroundColor, defaultTintColor),
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
                generateTintColor(warningBackgroundColor, defaultTintColor), warningBackgroundColor);
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
        return make(context, text, ContextCompat.getDrawable(context, R.drawable.adt_ic_warning),
                generateTintColor(warningBackgroundColor, defaultTintColor),
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
        return make(context, text, icon, defaultTintColor, defaultBackgroundColor, duration);
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
            @Nullable @ColorInt Integer tintColor, @Nullable @ColorInt Integer backgroundColor) {
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
    public static @NonNull Toast make(@NonNull Context context,
            @Nullable CharSequence text, @Nullable @ColorInt Integer tintColor,
            @Nullable @ColorInt Integer backgroundColor, int duration) {
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
            @Nullable Drawable icon, @Nullable @ColorInt Integer tintColor,
            @Nullable @ColorInt Integer backgroundColor) {
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
            @Nullable Drawable icon, @Nullable @ColorInt Integer tintColor,
            @Nullable @ColorInt Integer backgroundColor, int duration) {
        if (context instanceof Activity && ((Activity) context).isFinishing()) {
            context = context.getApplicationContext();
        }

        @Nullable @ColorInt Integer toastTintColor = tintColor;
        if (tintColor != null && backgroundColor != null) {
            toastTintColor = DynamicColorUtils.getContrastColor(toastTintColor, backgroundColor);
        }

        ToastCompat toast = new ToastCompat(context, new Toast(context));
        View toastLayout = LayoutInflater.from(context).inflate(
                R.layout.adt_layout_hint, new LinearLayout(context), false);
        ImageView toastIcon = toastLayout.findViewById(R.id.adt_hint_icon);
        TextView toastText = toastLayout.findViewById(R.id.adt_hint_text);

        if (!disableIcon && icon != null) {
            if (iconSize != ADT_DEFAULT_ICON_SIZE) {
                toastIcon.getLayoutParams().width = iconSize;
                toastIcon.getLayoutParams().height = iconSize;
                toastIcon.requestLayout();
            }

            if (tintIcon && toastTintColor != null) {
                toastIcon.setColorFilter(toastTintColor);
            } else {
                toastIcon.clearColorFilter();
            }
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

        if (toastTintColor != null) {
            toastText.setTextColor(toastTintColor);
        }
        toastText.setText(text);

        if (toastBackground != null) {
            DynamicDrawableUtils.setBackground(toastLayout, backgroundColor != null
                    ? DynamicDrawableUtils.colorizeDrawable(toastBackground,
                    backgroundColor, PorterDuff.Mode.MULTIPLY) : toastBackground);
        } else {
            DynamicDrawableUtils.setBackground(toastLayout, backgroundColor != null
                    ? DynamicDrawableUtils.colorizeDrawable(ContextCompat.getDrawable(context,
                    R.drawable.adt_hint_background), backgroundColor, PorterDuff.Mode.MULTIPLY)
                    : ContextCompat.getDrawable(context, R.drawable.adt_hint_background));
        }

        toast.setDuration(duration);
        toast.setView(toastLayout);

        return toast;
    }

    /**
     * Configuration class to customise the {@link DynamicHint} attributes.
     */
    public static class Config {

        /**
         * Singleton instance of {@link Config}.
         */
        private static Config sInstance;

        /**
         * Background color for the default toast.
         */
        private @ColorInt Integer defaultBackgroundColor = DynamicHint.defaultBackgroundColor;

        /**
         * Tint color for the default toast.
         */
        private @ColorInt Integer defaultTintColor = DynamicHint.defaultTintColor;

        /**
         * Background color for the error toast.
         */
        private @ColorInt Integer errorBackgroundColor = DynamicHint.errorBackgroundColor;

        /**
         * Background color for the success toast.
         */
        private @ColorInt Integer successBackgroundColor = DynamicHint.successBackgroundColor;

        /**
         * Background color for the warning toast.
         */
        private @ColorInt Integer warningBackgroundColor = DynamicHint.warningBackgroundColor;

        /**
         * Custom icon for the error toast.
         */
        private Drawable errorIcon = DynamicHint.errorIcon;

        /**
         * Custom icon for the success toast.
         */
        private Drawable successIcon = DynamicHint.successIcon;

        /**
         * Custom icon for the warning toast.
         */
        private Drawable warningIcon = DynamicHint.warningIcon;

        /**
         * {@code true} to disable icon for all the toasts.
         */
        private boolean disableIcon = DynamicHint.disableIcon;

        /**
         * {@code true} to tint icon for all the toasts.
         */
        private boolean tintIcon = DynamicHint.tintIcon;

        /**
         * Icon size for the toast in pixels.
         */
        private int iconSize = DynamicHint.iconSize;

        /**
         * Text size for the toast in SP.
         *
         * @see TypedValue#COMPLEX_UNIT_SP;
         */
        private @ColorInt int textSize = DynamicHint.textSize;

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
        public static @NonNull Config getInstance() {
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
        public @NonNull Config setDefaultBackgroundColor(
                @Nullable @ColorInt Integer defaultBackgroundColor) {
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
        public @NonNull Config setDefaultTintColor(@Nullable @ColorInt Integer defaultTintColor) {
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
        public @NonNull Config setErrorBackgroundColor(
                @Nullable @ColorInt Integer errorBackgroundColor) {
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
        public @NonNull Config setSuccessBackgroundColor(
                @Nullable @ColorInt Integer successBackgroundColor) {
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
        public @NonNull Config setWarningBackgroundColor(
                @Nullable @ColorInt Integer warningBackgroundColor) {
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
        public @NonNull Config setErrorIcon(@Nullable Drawable errorIcon) {
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
        public @NonNull Config setSuccessIcon(@Nullable Drawable successIcon) {
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
        public @NonNull Config setWarningIcon(@Nullable Drawable warningIcon) {
            this.warningIcon = warningIcon;

            return this;
        }

        /**
         * Set the icon visibility.
         *
         * @param disableIcon {@code true} to disable icon for all the toasts.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public @NonNull Config setDisableIcon(boolean disableIcon) {
            this.disableIcon = disableIcon;

            return this;
        }

        /**
         * Set whether to tint the icon.
         *
         * @param tintIcon {@code true} to tint icon for all the toasts.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public @NonNull Config setTintIcon(boolean tintIcon) {
            this.tintIcon = tintIcon;

            return this;
        }

        /**
         * Set the icon size.
         *
         * @param iconSize The icon size to be set in {@code pixels}.
         *
         * @return The {@link Config} object to allow for chaining of calls to set methods.
         */
        public @NonNull Config setIconSize(int iconSize) {
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
        public @NonNull Config setTextSize(int textSize) {
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
        public @NonNull Config setTextTypeface(@Nullable Typeface textTypeface) {
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
        public @NonNull Config setToastBackground(@Nullable Drawable toastBackground) {
            this.toastBackground = toastBackground;

            return this;
        }

        /**
         * Apply customisations.
         */
        public void apply() {
            DynamicHint.defaultBackgroundColor = defaultBackgroundColor;
            DynamicHint.defaultTintColor = defaultTintColor;
            DynamicHint.errorBackgroundColor = errorBackgroundColor;
            DynamicHint.successBackgroundColor = successBackgroundColor;
            DynamicHint.warningBackgroundColor = warningBackgroundColor;
            DynamicHint.errorIcon = errorIcon;
            DynamicHint.successIcon = successIcon;
            DynamicHint.warningIcon = warningIcon;
            DynamicHint.disableIcon = disableIcon;
            DynamicHint.tintIcon = tintIcon;
            DynamicHint.iconSize = iconSize;
            DynamicHint.textSize = textSize;
            DynamicHint.textTypeface = textTypeface;
            DynamicHint.toastBackground = toastBackground;

            sInstance = null;
        }

        /**
         * Reset customisations.
         */
        public void reset() {
            DynamicHint.defaultBackgroundColor = ADT_DEFAULT_BG_COLOR;
            DynamicHint.defaultTintColor = ADT_DEFAULT_TINT_COLOR;
            DynamicHint.errorBackgroundColor = ADT_DEFAULT_ERROR_BG_COLOR;
            DynamicHint.successBackgroundColor = ADT_DEFAULT_SUCCESS_BG_COLOR;
            DynamicHint.warningBackgroundColor = ADT_DEFAULT_WARNING_BG_COLOR;
            DynamicHint.errorIcon = null;
            DynamicHint.successIcon = null;
            DynamicHint.warningIcon = null;
            DynamicHint.disableIcon = ADT_DEFAULT_DISABLE_ICON;
            DynamicHint.tintIcon = ADT_DEFAULT_TINT_ICON;
            DynamicHint.iconSize = ADT_DEFAULT_ICON_SIZE;
            DynamicHint.textSize = ADT_DEFAULT_TEXT_SIZE;
            DynamicHint.textTypeface = null;
            DynamicHint.toastBackground = null;

            sInstance = null;
        }
    }

    /**
     * Show toast above or below according to the anchor view position.
     *
     * @param anchor The anchor view to show the toast.
     * @param toast The toast to be displayed.
     */
    public static void show(@NonNull View anchor, @NonNull Toast toast) {
        show(anchor, toast, ADT_TOAST_OFFSET);
    }

    /**
     * Show toast above or below according to the anchor view position.
     *
     * @param anchor The anchor view to show the toast.
     * @param toast The toast to be displayed.
     * @param offset The toast vertical offset in dips.
     */
    @SuppressWarnings("deprecation")
    public static void show(@NonNull View anchor, @NonNull Toast toast, int offset) {
        Rect displayFrame = new Rect();
        int[] screenLocation = new int[2];
        anchor.getWindowVisibleDisplayFrame(displayFrame);
        anchor.getLocationOnScreen(screenLocation);
        int anchorLeft = screenLocation[0];
        int anchorTop = Math.max(0, screenLocation[1]
                - DynamicUnitUtils.convertDpToPixels(ADT_INSET_TOP));
        int minAnchorHeight = DynamicUnitUtils.convertDpToPixels(ADT_MIN_ANCHOR_HEIGHT);
        int yOffset = DynamicUnitUtils.convertDpToPixels(offset);

        DisplayMetrics metrics = anchor.getResources().getDisplayMetrics();
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                metrics.widthPixels, View.MeasureSpec.UNSPECIFIED);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(
                metrics.heightPixels, View.MeasureSpec.UNSPECIFIED);
        int toastWidth = DynamicUnitUtils.convertDpToPixels(ADT_MIN_ANCHOR_HEIGHT);

        if (toast.getView() != null) {
            toast.getView().measure(widthMeasureSpec, heightMeasureSpec);
            toastWidth = toast.getView().getMeasuredWidth();
        }

        if (anchorTop < displayFrame.top + yOffset) {
            toast.setGravity(Gravity.START | Gravity.TOP,
                    anchorLeft + (anchor.getWidth() - toastWidth) / 2,
                    anchorTop + Math.max(minAnchorHeight, anchor.getHeight()) + yOffset);
        } else {
            toast.setGravity(Gravity.START | Gravity.TOP,
                    anchorLeft + (anchor.getWidth() - toastWidth) / 2,
                    anchorTop - Math.max(minAnchorHeight, anchor.getHeight()) - yOffset);
        }

        toast.show();
    }
}
