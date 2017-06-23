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
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
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
     * Default background color for the toast.
     */
    private static final @ColorInt int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#454545");

    /**
     * Default tint color for the toast.
     */
    private static final @ColorInt int DEFAULT_TINT_COLOR = Color.parseColor("#FFFFFF");

    /**
     * Make a standard toast that just contains a text view. Toast
     * duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     *
     * @return Toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast make(@NonNull Context context, @NonNull CharSequence text) {
        return make(context, text, null, DEFAULT_TINT_COLOR ,
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
     * Make a themed toast with icon and the text. Toast
     * duration will be {@link Toast#LENGTH_SHORT}.
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
        return make(context, text, icon, DEFAULT_TINT_COLOR ,
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
     * Make a themed toast with text, background and the tint color. Toast
     * duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param tintColor The toast tint color based on the background. It will
     *                  automatically check for the contrast to provide best
     *                  visibility.
     * @param backgroundColor The toast background color.
     *
     * @return Toast with the supplied parameters. Use {@link Toast#show()}
     *         to display the toast.
     */
    public static Toast make(@NonNull Context context, @NonNull CharSequence text,
                             @ColorInt int tintColor, @ColorInt int backgroundColor) {
        return make(context, text, null, tintColor, backgroundColor, Toast.LENGTH_SHORT);
    }

    /**
     * Make a themed toast with text, background and the tint color. Toast
     * duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param tintColor The toast tint color based on the background. It will
     *                  automatically check for the contrast to provide best
     *                  visibility.
     * @param backgroundColor The toast background color.
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
     * Make a themed toast with text, icon, background and the tint color. Toast
     * duration will be {@link Toast#LENGTH_SHORT}.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param icon The toast icon to show.
     * @param tintColor The toast tint color based on the background. It will
     *                  automatically check for the contrast to provide best
     *                  visibility.
     * @param backgroundColor The toast background color.
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
     * Make a themed toast with text, icon, background and the tint color.
     *
     * @param context The context to use. Usually your {@link android.app.Application}
     *                or {@link android.app.Activity} object.
     * @param text The text to show. Can be formatted text.
     * @param icon The toast icon to show.
     * @param tintColor The toast tint color based on the background. It will
     *                  automatically check for the contrast to provide best
     *                  visibility.
     * @param backgroundColor The toast background color.
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
            toastIcon.setVisibility(View.VISIBLE);
        }

        toastText.setTextColor(tintColor);
        toastText.setText(text);

        DynamicDrawableUtils.setBackground(toastLayout, DynamicDrawableUtils.colorizeDrawable(
                ContextCompat.getDrawable(context, R.drawable.adt_toast_frame),
                backgroundColor, PorterDuff.Mode.MULTIPLY));

        toast.setDuration(duration);
        toast.setView(toastLayout);

        return toast;
    }
}
