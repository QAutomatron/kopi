# kopi
[![](https://jitpack.io/v/QAutomatron/kopi.svg)](https://jitpack.io/#QAutomatron/kopi)

Element wrapper library for Espresso testing framework

#### How to use with Gradle:
Add repository (jitpack):
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
	        androidTestImpl 'com.github.QAutomatron:kopi:version'
	}
```
#### Usage:
```kotlin
val fieldById = Element(withId(R.id.field))
// Tap element
fieldById.tap()
// Assert field
fieldById.sameAs("my_text")
// Wait for visibility
fieldById.waitForVisibility()
```
