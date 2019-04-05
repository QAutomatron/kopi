# kopi
[![Build Status](https://travis-ci.org/QAutomatron/kopi.svg?branch=master)](https://travis-ci.org/QAutomatron/kopi)
[![](https://jitpack.io/v/QAutomatron/kopi.svg)](https://jitpack.io/#QAutomatron/kopi)

Element wrapper library for Espresso testing framework

#### How to use with Gradle:

Repository: 
```gradle
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```
Dependency: 
```gradle
	dependencies {
	        androidTestImpl 'com.github.QAutomatron:kopi:0.4.0'
	}
```


#### Usage:

Define element:
```kotlin
val field = Element(withId(R.id.field))
```
Use element:
```kotlin
field.tap()
```
Assert element:
```kotlin
field.sameAs("my_text")
```
Wait for element:
```kotlin
field.waitForVisibility()
```
