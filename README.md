<img src="https://raw.githubusercontent.com/pranavpandey/dynamic-toasts/master/graphics/dynamic-toasts_512x512.png" width="160" height="160" align="right" hspace="20">

# Dynamic Toasts

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://travis-ci.org/pranavpandey/dynamic-toasts.svg?branch=master)](https://travis-ci.org/pranavpandey/dynamic-toasts)

A simple library to display themed toasts with icon and text on Android 9+ (Gingerbread or above) 
devices.

---

## Table of Contents

1. [Installation](https://github.com/pranavpandey/dynamic-toasts#installation)
2. [Usage](https://github.com/pranavpandey/dynamic-toasts#usage)
    1. [Default toast](https://github.com/pranavpandey/dynamic-toasts#default-toast)
    2. [Default toast with duration](https://github.com/pranavpandey/dynamic-toasts#default-toast-with-duration)
    3. [Default toast with icon](https://github.com/pranavpandey/dynamic-toasts#default-toast-with-icon)
    4. [Default toast icon and duration](https://github.com/pranavpandey/dynamic-toasts##default-toast-with-icon-and-duration)
    5. [Custom toast](https://github.com/pranavpandey/dynamic-toasts#custom-toast)
    6. [Custom toast with duration](https://github.com/pranavpandey/dynamic-toasts#custom-toast-with-duration)
    7. [Custom toast with icon](https://github.com/pranavpandey/dynamic-toasts#custom-toast-with-icon)
    8. [Custom toast icon and duration](https://github.com/pranavpandey/dynamic-toasts#custom-toast-with-icon-and-duration)
    9. [Dependency](https://github.com/pranavpandey/dynamic-toasts#dependency)
3. [License](https://github.com/pranavpandey/dynamic-toasts#license)

---

## Installation

It can be installed by adding the following dependency to your `build.gradle` file:

```groovy
dependencies {
    compile 'com.pranavpandey.android:dynamic-toasts:0.1.0'
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
DynamicToast.make(context, "Default Toast").show();
```

### Default toast with duration

Simple toast based on the vanilla Android theme for supplied duration.

```java
DynamicToast.make(context, "Default Toast", duration).show();
```

### Default toast with icon

Simple toast based on the vanilla Android theme with a icon for `Toast.LENGTH_SHORT` duration.

```java
DynamicToast.make(context, "Default Toast with icon", drawable).show();
```

### Default toast with icon and duration

Simple toast based on the vanilla Android theme with a icon for supplied duration.

```java
DynamicToast.make(context, "Default Toast with icon", drawable, duration).show();
```

### Custom toast

Custom toast based on the supplied background and tint color for `Toast.LENGTH_SHORT` duration.

```java
DynamicToast.make(context, "Custom Toast", tintColor, backgroundColor).show();
```

### Custom toast with duration

Custom toast based on the supplied background and tint color for supplied duration.

```java
DynamicToast.make(context, "Custom Toast", tintColor, backgroundColor, duration).show();
```

### Custom toast with icon

Custom toast based on the supplied icon, background and tint color theme for `Toast.LENGTH_SHORT` 
duration.

```java
DynamicToast.make(context, "Custom Toast with icon", drawable, tintColor, backgroundColor).show();
```

### Custom toast with icon and duration

Custom toast based on the supplied icon, background and tint color theme for supplied duration.

```java
DynamicToast.make(context, "Custom Toast with icon", drawable, tintColor, backgroundColor, duration).show();
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
