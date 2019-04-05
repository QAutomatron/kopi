# kopi
[![Build Status](https://travis-ci.org/QAutomatron/kopi.svg?branch=master)](https://travis-ci.org/QAutomatron/kopi)
[![](https://jitpack.io/v/QAutomatron/kopi.svg)](https://jitpack.io/#QAutomatron/kopi)

Element wrapper library for Espresso testing framework

#### How to use with Gradle:

Repository: 
```
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
```
Dependency: 
```
	dependencies {
	        androidTestImpl 'com.github.QAutomatron:kopi:0.4.0'
	}
```


#### Usage:

Define element:
```
val field = Element(withId(R.id.field))
```
Use element:
```
field.tap()
```
Assert element:
```
field.sameAs("my_text")
```
Wait for element:
```
field.waitForVisibility()
```
