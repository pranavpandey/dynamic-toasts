<img src="https://raw.githubusercontent.com/pranavpandey/dynamic-toasts/master/graphics/dynamic-toasts.png" width="160" height="160" align="right" hspace="20">

# Dynamic Toasts

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://travis-ci.org/pranavpandey/dynamic-toasts.svg?branch=master)](https://travis-ci.org/pranavpandey/dynamic-toasts)
[![Download](https://api.bintray.com/packages/pranavpandey/android/dynamic-toasts/images/download.svg)](https://bintray.com/pranavpandey/android/dynamic-toasts/_latestVersion)

A simple library to display themed toasts with icon and text on Android 2.3 (API 9) and above
devices.

> Since v0.4.0, it uses [26.x.x support libraries](https://developer.android.com/topic/libraries/support-library/revisions.html#26-0-0)
so, minimum SDK will be Android 4.0 (API 14).
<br/>Since v2.0.0, it uses [AndroidX](https://developer.android.com/jetpack/androidx/) so, first
[migrate](https://developer.android.com/jetpack/androidx/migrate) your project to AndroidX.

<img src="https://raw.githubusercontent.com/pranavpandey/dynamic-toasts/master/graphics/dynamic-toasts-preview.png">

---

## Contents

- [Installation](https://github.com/pranavpandey/dynamic-toasts#installation)
- [Usage](https://github.com/pranavpandey/dynamic-toasts#usage)
    - [Configuration](https://github.com/pranavpandey/dynamic-toasts#cofiguaration)
    - [Default toast](https://github.com/pranavpandey/dynamic-toasts#default-toast)
    - [Default toast with duration](https://github.com/pranavpandey/dynamic-toasts#default-toast-with-duration)
    - [Default toast with icon](https://github.com/pranavpandey/dynamic-toasts#default-toast-with-icon)
    - [Default toast with icon and duration](https://github.com/pranavpandey/dynamic-toasts##default-toast-with-icon-and-duration)
    - [Error toast](https://github.com/pranavpandey/dynamic-toasts#error-toast)
    - [Error toast with duration](https://github.com/pranavpandey/dynamic-toasts#error-toast-with-duration)
    - [Success toast](https://github.com/pranavpandey/dynamic-toasts#success-toast)
    - [Success toast with duration](https://github.com/pranavpandey/dynamic-toasts#success-toast-with-duration)
    - [Warning toast](https://github.com/pranavpandey/dynamic-toasts#warning-toast)
    - [Warning toast with duration](https://github.com/pranavpandey/dynamic-toasts#warning-toast-with-duration)
    - [Custom toast](https://github.com/pranavpandey/dynamic-toasts#custom-toast)
    - [Custom toast with duration](https://github.com/pranavpandey/dynamic-toasts#custom-toast-with-duration)
    - [Custom toast with icon](https://github.com/pranavpandey/dynamic-toasts#custom-toast-with-icon)
    - [Custom toast with icon and duration](https://github.com/pranavpandey/dynamic-toasts#custom-toast-with-icon-and-duration)
    - [Cheat sheets](https://github.com/pranavpandey/dynamic-toasts#cheat-sheets)
    - [Dependency](https://github.com/pranavpandey/dynamic-toasts#dependency)
- [License](https://github.com/pranavpandey/dynamic-toasts#license)

---

## Installation

It can be installed by adding the following dependency to your `build.gradle` file:

```groovy
dependencies {
    // For AndroidX enabled projects.
    implementation 'com.pranavpandey.android:dynamic-toasts:3.1.0'

    // For legacy projects.
    implementation 'com.pranavpandey.android:dynamic-toasts:1.3.0'
}
```

---

## Usage

It has several method to display toasts based on the requirement. Each method returns a `Toast`
object which can be customised further.

Please call `show()` method to display the toast.

> For complete reference, please read the [documentation](https://pranavpandey.github.io/dynamic-toasts).

### Configuration

Optional configuration to customise the toasts further like custom background color or drawable, 
custom text size, typeface or icon size, etc.

Various methods can be called anywhere in the app to do customisations.

```java
DynamicToast.Config.getInstance()
    // Background color for default toast.
    .setDefaultBackgroundColor(@ColorInt int defaultBackgroundColor)
    // Tint color for default toast.
    .setDefaultTintColor(@ColorInt int defaultTintColor)
    // Background color for error toast.
    .setErrorBackgroundColor(@ColorInt int errorBackgroundColor)
    // Background color for success toast.
    .setSuccessBackgroundColor(@ColorInt int successBackgroundColor)
    // Background color for warning toast.
    .setWarningBackgroundColor(@ColorInt int warningBackgroundColor)
    // Custom icon for error toast. Pass `null` to use default icon.
    .setErrorIcon(@Nullable Drawable errorIcon)
    // Custom icon for success toast. Pass `null` to use default icon.
    .setSuccessIcon(@Nullable Drawable successIcon)
    // Custom icon for warning toast. Pass `null` to use default icon.
    .setWarningIcon(@Nullable Drawable warningIcon)
    // Disable icon for all the toasts.
    .setDisableIcon(boolean disableIcon)
    // Custom icon size in `pixels` for all the toasts.
    .setIconSize(int iconSize)
    // Custom text size in `SP` for all the toasts.
    .setTextSize(int textSize)
    // Custom text typeface for all the toasts. Pass `null` to use system typeface.
    .setTextTypeface(@Nullable Typeface textTypeface)
    // Custom background drawable for all the toasts. Pass `null` to use default background.
    .setToastBackground(@Nullable Drawable toastBackground)
    // Apply customisations.
    .apply();
```

Call `reset()` method to reset all the customisations.

```java
// Reset customisations.
DynamicToast.Config.getInstance().reset();
```

### Default toast

Simple toast based on the vanilla Android theme for `Toast.LENGTH_SHORT` duration.

```java
DynamicToast.make(context, "Default toast").show();
```

### Default toast with duration

Simple toast based on the vanilla Android theme for supplied duration.

```java
DynamicToast.make(context, "Default toast with duration", duration).show();
```

### Default toast with icon

Simple toast based on the vanilla Android theme with a icon for `Toast.LENGTH_SHORT` duration.

```java
DynamicToast.make(context, "Default toast with icon", drawable).show();
```

### Default toast with icon and duration

Simple toast based on the vanilla Android theme with a icon for supplied duration.

```java
DynamicToast.make(context, "Default toast with icon and duration", drawable, duration).show();
```

### Error toast

Error toast with `#F44336` background for `Toast.LENGTH_SHORT` duration.

```java
DynamicToast.makeError(context, "Error toast").show();
```

### Error toast with duration

Error toast with `#F44336` background for supplied duration.

```java
DynamicToast.makeError(context, "Error toast with duration", duration).show();
```

### Success toast

Success toast with `#4CAF50` background for `Toast.LENGTH_SHORT` duration.

```java
DynamicToast.makeSuccess(context, "Success toast").show();
```

### Success toast with duration

Success toast with `#4CAF50` background for supplied duration.

```java
DynamicToast.makeSuccess(context, "Success toast with duration", duration).show();
```

### Warning toast

Warning toast with `#FFEB3B` background for `Toast.LENGTH_SHORT` duration.

```java
DynamicToast.makeWarning(context, "Warning toast").show();
```

### Warning toast with duration

Warning toast with `#FFEB3B` background for supplied duration.

```java
DynamicToast.makeWarning(context, "Warning toast with duration", duration).show();
```

### Custom toast

Custom toast based on the supplied background and tint color for `Toast.LENGTH_SHORT` duration.

```java
DynamicToast.make(context, "Custom toast", tintColor, backgroundColor).show();
```

### Custom toast with duration

Custom toast based on the supplied background and tint color for supplied duration.

```java
DynamicToast.make(context, "Custom toast with duration", tintColor, backgroundColor, duration).show();
```

### Custom toast with icon

Custom toast based on the supplied icon, background and tint color theme for `Toast.LENGTH_SHORT` 
duration.

```java
DynamicToast.make(context, "Custom toast with icon", drawable, tintColor, backgroundColor).show();
```

### Custom toast with icon and duration

Custom toast based on the supplied icon, background and tint color theme for supplied duration.

```java
DynamicToast.make(context, "Custom toast with icon and duration", drawable, 
        tintColor, backgroundColor, duration).show();
```

### Cheat sheets

Use dynamic hint to display cheat sheets for any `view`. All the methods are same as explained 
above, just replace `DynamicToast` with `DynamicHint` to create a cheat sheet.

> Use `DynamicHint.show(view, toast)` method to display it according to the anchor view position.

### Dependency

It depends on the [dynamic-utils](https://github.com/pranavpandey/dynamic-utils) to perform
various internal operations. So, its functions can also be used to perform other useful operations.

---

## Author

Pranav Pandey

[![GitHub](https://img.shields.io/github/followers/pranavpandey?label=GitHub&style=social)](https://github.com/pranavpandey)
[![Follow on Twitter](https://img.shields.io/twitter/follow/pranavpandeydev?label=Follow&style=social)](https://twitter.com/intent/follow?screen_name=pranavpandeydev)
[![Donate via PayPal](https://img.shields.io/static/v1?label=Donate&message=PayPal&color=blue)](https://paypal.me/pranavpandeydev)

---

## License

    Copyright 2019 Pranav Pandey

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
