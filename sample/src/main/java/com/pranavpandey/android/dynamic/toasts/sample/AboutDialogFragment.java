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

package com.pranavpandey.android.dynamic.toasts.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pranavpandey.android.dynamic.dialogs.DynamicDialog;
import com.pranavpandey.android.dynamic.dialogs.fragment.DynamicDialogFragment;
import com.pranavpandey.android.dynamic.utils.DynamicLinkUtils;

/**
 * About dialog to show library info.
 */
public class AboutDialogFragment extends DynamicDialogFragment {

    /**
     * Text size for the about dialog.
     * 15 SP
     */
    private static final int TEXT_SIZE = 15;

    /**
     * Text size for the about dialog.
     * 15 SP
     */
    private static final String GOOGLE_PLAY_URL =
            "https://play.google.com/store/apps/dev?id=6608630615059087491";

    public static AboutDialogFragment newInstance() {
        return new AboutDialogFragment();
    }

    @Override
    protected @NonNull DynamicDialog onCustomiseDialog(@NonNull DynamicDialog alertDialog,
                                                       @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_about,
                new LinearLayout(getContext()), false);
        TextView message = view.findViewById(R.id.dialog_about_text);

        message.setText(Html.fromHtml(getString(R.string.about_content)
                .replace("\n", "<br/>")));
        message.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
        message.setLineSpacing(0f, 1.2f);
        message.setMovementMethod(LinkMovementMethod.getInstance());
        message.setLinkTextColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));

        alertDialog.setView(view);
        return alertDialog;
    }

    @Override
    protected @NonNull DynamicDialog.Builder onCustomiseBuilder(
            @NonNull DynamicDialog.Builder alertDialogBuilder,
            @Nullable Bundle savedInstanceState) {
        return alertDialogBuilder.setTitle(R.string.about)
                .setPositiveButton(R.string.more_apps, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DynamicLinkUtils.viewUrl(getContext(), GOOGLE_PLAY_URL);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .setViewRoot(R.id.dialog_about_root);
    }
}
