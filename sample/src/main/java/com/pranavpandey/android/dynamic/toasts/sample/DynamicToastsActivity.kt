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

package com.pranavpandey.android.dynamic.toasts.sample

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.pranavpandey.android.dynamic.toasts.DynamicHint
import com.pranavpandey.android.dynamic.toasts.DynamicToast
import com.pranavpandey.android.dynamic.utils.DynamicColorUtils
import com.pranavpandey.android.dynamic.utils.DynamicLinkUtils
import com.pranavpandey.android.dynamic.utils.DynamicPackageUtils
import com.pranavpandey.android.dynamic.utils.DynamicUnitUtils

/**
 * Main activity to show the implementation of [DynamicToast].
 */
class DynamicToastsActivity : AppCompatActivity(), View.OnClickListener {

    companion object {

        /**
         * Open source repository url.
         */
        const val URL_GITHUB = "https://github.com/pranavpandey/dynamic-toasts"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_dynamic_toasts)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setSubtitle(R.string.app_name_sample)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setColorFilter(DynamicColorUtils.getTintColor(
                ContextCompat.getColor(this, R.color.colorAccent)))

        (findViewById<View>(R.id.gradle) as TextView).text = String.format(
                getString(R.string.format_version),
                DynamicPackageUtils.getAppVersion(this))

        fab.setOnClickListener(this)
        findViewById<View>(R.id.toast_default).setOnClickListener(this)
        findViewById<View>(R.id.toast_default_icon).setOnClickListener(this)
        findViewById<View>(R.id.toast_success).setOnClickListener(this)
        findViewById<View>(R.id.toast_error).setOnClickListener(this)
        findViewById<View>(R.id.toast_success).setOnClickListener(this)
        findViewById<View>(R.id.toast_warning).setOnClickListener(this)
        findViewById<View>(R.id.toast_custom_icon).setOnClickListener(this)
        findViewById<View>(R.id.toast_custom).setOnClickListener(this)
        findViewById<View>(R.id.toast_error_color).setOnClickListener(this)
        findViewById<View>(R.id.toast_success_color).setOnClickListener(this)
        findViewById<View>(R.id.toast_warning_color).setOnClickListener(this)
        findViewById<View>(R.id.toast_default_color).setOnClickListener(this)
        findViewById<View>(R.id.toast_error_icon).setOnClickListener(this)
        findViewById<View>(R.id.toast_success_icon).setOnClickListener(this)
        findViewById<View>(R.id.toast_warning_icon).setOnClickListener(this)
        findViewById<View>(R.id.toast_error_icon_disable).setOnClickListener(this)
        findViewById<View>(R.id.toast_success_icon_disable).setOnClickListener(this)
        findViewById<View>(R.id.toast_warning_icon_disable).setOnClickListener(this)
        findViewById<View>(R.id.toast_config_text).setOnClickListener(this)
        findViewById<View>(R.id.toast_config_background).setOnClickListener(this)
        findViewById<View>(R.id.toast_config_icon_size).setOnClickListener(this)
        findViewById<View>(R.id.hint_default).setOnClickListener(this)
        findViewById<View>(R.id.hint_custom).setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_about) {
            AboutDialogFragment.newInstance().showDialog(this)

            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> DynamicLinkUtils.viewUrl(this@DynamicToastsActivity, URL_GITHUB)

            // Default toast without icon.
            R.id.toast_default -> DynamicToast.make(
                    this, getString(R.string.without_icon_desc)).show()

            // Default toast with icon.
            R.id.toast_default_icon -> DynamicToast.make(
                    this, getString(R.string.with_icon_desc),
                    AppCompatResources.getDrawable(
                            this, R.drawable.ic_toast_icon)).show()

            // Error toast.
            R.id.toast_error -> DynamicToast.makeError(
                    this, getString(R.string.error_desc)).show()

            // Success toast.
            R.id.toast_success -> DynamicToast.makeSuccess(
                    this, getString(R.string.success_desc)).show()

            // Warning toast.
            R.id.toast_warning -> DynamicToast.makeWarning(
                    this, getString(R.string.warning_desc)).show()

            // Custom toast without icon.
            R.id.toast_custom -> DynamicToast.make(
                    this, getString(R.string.custom_desc),
                    Color.parseColor("#FFFFFF"), Color.parseColor("#000000"),
                    Toast.LENGTH_LONG).show()

            // Custom toast with icon.
            R.id.toast_custom_icon -> DynamicToast.make(
                    this, getString(R.string.custom_desc),
                    AppCompatResources.getDrawable(this, R.drawable.ic_social_github),
                    Color.parseColor("#FFFFFF"), Color.parseColor("#000000"),
                    Toast.LENGTH_LONG).show()

            // Error toast with custom color.
            R.id.toast_error_color -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setErrorBackgroundColor(Color.parseColor("#673AB7"))
                        .apply()

                DynamicToast.makeError(this, getString(R.string.error_color_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Success toast with custom color.
            R.id.toast_success_color -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setSuccessBackgroundColor(Color.parseColor("#2196F3"))
                        .apply()

                DynamicToast.makeSuccess(
                        this, getString(R.string.success_color_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Warning toast with custom color.
            R.id.toast_warning_color -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setWarningBackgroundColor(Color.parseColor("#8BC34A"))
                        .apply()

                DynamicToast.makeWarning(
                        this, getString(R.string.warning_color_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Default toast with custom color.
            R.id.toast_default_color -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setDefaultBackgroundColor(Color.parseColor("#607d8b"))
                        .setDefaultTintColor(DynamicColorUtils.getTintColor(
                                Color.parseColor("#607d8b")))
                        .apply()

                DynamicToast.make(
                        this, getString(R.string.default_color_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Error toast with custom icon.
            R.id.toast_error_icon -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setErrorIcon(AppCompatResources.getDrawable(
                                this, R.drawable.ic_toast_icon))
                        .apply()

                DynamicToast.makeError(this,
                        getString(R.string.error_icon_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Success toast with custom icon.
            R.id.toast_success_icon -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setSuccessIcon(AppCompatResources.getDrawable(
                                this, R.drawable.ic_toast_icon))
                        .apply()

                DynamicToast.makeSuccess(this,
                        getString(R.string.success_icon_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Warning toast with custom icon.
            R.id.toast_warning_icon -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setWarningIcon(AppCompatResources.getDrawable(
                                this, R.drawable.ic_toast_icon))
                        .apply()

                DynamicToast.makeWarning(this,
                        getString(R.string.warning_icon_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Error toast without icon.
            R.id.toast_error_icon_disable -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setDisableIcon(true)
                        .apply()

                DynamicToast.makeError(this,
                        getString(R.string.error_icon_disable_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Success toast without icon.
            R.id.toast_success_icon_disable -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setDisableIcon(true)
                        .apply()

                DynamicToast.makeSuccess(this,
                        getString(R.string.success_icon_disable_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Warning toast without icon.
            R.id.toast_warning_icon_disable -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setDisableIcon(true)
                        .apply()

                DynamicToast.makeWarning(this, getString(
                        R.string.warning_icon_disable_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Toast with custom text size and typeface.
            R.id.toast_config_text -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setTextSize(18)
                        .setErrorIcon(AppCompatResources.getDrawable(
                                this, R.drawable.ic_toast_icon))
                        .setTextTypeface(Typeface.create(
                                Typeface.SERIF, Typeface.BOLD_ITALIC))
                        .setErrorBackgroundColor(Color.parseColor("#2196F3"))
                        .apply()

                DynamicToast.makeError(this, getString(R.string.text_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Toast with custom background.
            R.id.toast_config_background -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setToastBackground(AppCompatResources.getDrawable(
                                this, R.drawable.custom_toast_background))
                        .apply()

                DynamicToast.makeSuccess(this, getString(R.string.background_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Toast with custom icon size.
            R.id.toast_config_icon_size -> {
                // Customise toast.
                DynamicToast.Config.getInstance()
                        .setIconSize(DynamicUnitUtils.convertDpToPixels(48f))
                        .apply()

                DynamicToast.makeWarning(this, getString(R.string.icon_size_desc)).show()

                // Reset customisations.
                DynamicToast.Config.getInstance().reset()
            }

            // Default hint without icon.
            R.id.hint_default -> DynamicHint.show(v,
                    DynamicHint.make(this, getString(R.string.default_hint)))

            // Custom hint with icon.
            R.id.hint_custom -> {
                // Customise hint.
                DynamicHint.Config.getInstance()
                        .setDefaultBackgroundColor(Color.parseColor("#607d8b"))
                        .setDefaultTintColor(DynamicColorUtils.getTintColor(
                                Color.parseColor("#607d8b")))
                        .apply()

                DynamicHint.show(v, DynamicHint.make(this, getString(R.string.custom_hint),
                        AppCompatResources.getDrawable(this, R.drawable.adt_ic_warning)))

                // Reset customisations.
                DynamicHint.Config.getInstance().reset()
            }
        }
    }
}
