<img src="https://raw.githubusercontent.com/pranavpandey/dynamic-toasts/master/graphics/dynamic-toasts_512x512.png" width="160" height="160" align="right" hspace="20">

# Dynamic Toasts

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://travis-ci.org/pranavpandey/dynamic-toasts.svg?branch=master)](https://travis-ci.org/pranavpandey/dynamic-toasts)

A simple library to display themed toasts with icon and text on Android 9+ (Gingerbread or above) 
devices.

<img src="https://raw.githubusercontent.com/pranavpandey/dynamic-toasts/master/graphics/dynamic-toasts-preview.png">

---

## Table of Contents

1. [Installation](https://github.com/pranavpandey/dynamic-toasts#installation)
2. [Usage](https://github.com/pranavpandey/dynamic-toasts#usage)
    1. [Default toast](https://github.com/pranavpandey/dynamic-toasts#default-toast)
    2. [Default toast with duration](https://github.com/pranavpandey/dynamic-toasts#default-toast-with-duration)
    3. [Default toast with icon](https://github.com/pranavpandey/dynamic-toasts#default-toast-with-icon)
    4. [Default toast with icon and duration](https://github.com/pranavpandey/dynamic-toasts##default-toast-with-icon-and-duration)
    5. [Error toast](https://github.com/pranavpandey/dynamic-toasts#error-toast)
    6. [Error toast with duration](https://github.com/pranavpandey/dynamic-toasts#error-toast-with-duration)
    7. [Success toast](https://github.com/pranavpandey/dynamic-toasts#success-toast)
    8. [Success toast with duration](https://github.com/pranavpandey/dynamic-toasts#success-toast-with-duration)
    9. [Warning toast](https://github.com/pranavpandey/dynamic-toasts#warning-toast)
    10. [Warning toast with duration](https://github.com/pranavpandey/dynamic-toasts#warning-toast-with-duration)
    11. [Custom toast](https://github.com/pranavpandey/dynamic-toasts#custom-toast)
    12. [Custom toast with duration](https://github.com/pranavpandey/dynamic-toasts#custom-toast-with-duration)
    13. [Custom toast with icon](https://github.com/pranavpandey/dynamic-toasts#custom-toast-with-icon)
    14. [Custom toast with icon and duration](https://github.com/pranavpandey/dynamic-toasts#custom-toast-with-icon-and-duration)
    15. [Dependency](https://github.com/pranavpandey/dynamic-toasts#dependency)
3. [License](https://github.com/pranavpandey/dynamic-toasts#license)

---

## Installation

It can be installed by adding the following dependency to your `build.gradle` file:

```groovy
dependencies {
    compile 'com.pranavpandey.android:dynamic-toasts:0.2.0'
}
```

---

## Usage

It has several method to display toasts based on the requirement. Each method returns a `Toast`
object which can be customised further.

Please call `show` method to display the toast.

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
DynamicToast.make(context, "Custom toast with icon and duration", drawable, tintColor, backgroundColor, duration).show();
```

### Dependency

As it depends on the [Dynamic Utils](https://github.com/pranavpandey/dynamic-utils), its functions
can be used to perform other operations.

---

## License

    Copyright (c) 2017 Pranav Pandey

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
