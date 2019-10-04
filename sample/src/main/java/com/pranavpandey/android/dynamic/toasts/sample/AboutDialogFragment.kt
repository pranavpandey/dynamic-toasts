/*
 * Copyright 2019 Pranav Pandey
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

package com.pranavpandey.android.dynamic.toasts.sample

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.pranavpandey.android.dynamic.dialogs.DynamicDialog
import com.pranavpandey.android.dynamic.dialogs.fragment.DynamicDialogFragment
import com.pranavpandey.android.dynamic.utils.DynamicLinkUtils

/**
 * About dialog to show library info.
 */
class AboutDialogFragment : DynamicDialogFragment() {

    companion object {

        /**
         * Url for other apps on Google Play.
         */
        const val URL_PLAY_STORE =
                "https://play.google.com/store/apps/dev?id=6608630615059087491"

        /**
         * Initialize the new instance of this fragment.
         *
         * @return An instance of [AboutDialogFragment].
         */
        fun newInstance(): AboutDialogFragment {
            return AboutDialogFragment()
        }

        /**
         * Method to handle [Html.fromHtml] deprecation.
         */
        @Suppress("DEPRECATION")
        private fun fromHtml(html: String): Spanned {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(html)
            }
        }
    }

    /**
     * Customise [DynamicDialog] by overriding this method.
     */
    override fun onCustomiseDialog(alertDialog: DynamicDialog,
                                   savedInstanceState: Bundle?): DynamicDialog {
        // Customise dialog to add a custom view.
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_about,
                LinearLayout(context), false)
        val message = view.findViewById<TextView>(R.id.dialog_about_text)

        message.text = fromHtml(getString(R.string.about_content))
        message.setLineSpacing(0f, 1.2f)
        message.movementMethod = LinkMovementMethod.getInstance()
        message.setLinkTextColor(ContextCompat.getColor(context!!, R.color.color_primary))

        alertDialog.setView(view)
        return alertDialog
    }

    /**
     * Customise [DynamicDialog.Builder] by overriding this method.
     */
    override fun onCustomiseBuilder(
            alertDialogBuilder: DynamicDialog.Builder,
            savedInstanceState: Bundle?): DynamicDialog.Builder {
        // Customise dialog builder to add neutral, positive and negative buttons.
        // Also, set a view root to add top and bottom scroll indicators.
        return alertDialogBuilder.setTitle(R.string.about)
                .setPositiveButton(R.string.more_apps) {
                    _, _ -> DynamicLinkUtils.viewUrl(context!!, URL_PLAY_STORE)
                }
                .setNegativeButton(android.R.string.cancel, null)
                .setViewRoot(R.id.dialog_about_root)
    }
}
