# 📱 Kotlin : Android App ![Beta Deploy](https://github.com/home-assistant/android/workflows/Beta%20Deploy/badge.svg)

Dicoding submission : Belajar membuat aplikasi android untuk pemula


## Setup App Development Environment

1. Download and install [Android Studio](https://developer.android.com/studio)

2. Download / clone this repository to a folder on your computer

3. Create a Firebase project at [Firebase Console](https://console.firebase.google.com)

4. Create four Android apps, with following package names 
 - `io.homeassistant.companion.android`
 - `io.homeassistant.companion.android.debug`
 - `io.homeassistant.companion.android.minimal`
 - `io.homeassistant.companion.android.minimal.debug`

5. Now download the `google-services.json` file and put it in the project's _/app_, _/automotive_ and _/wear_ folders. This file contains the configuration of the whole project (all four applications). ([You can also use the mock services file instead of generating your own](/.github/mock-google-services.json). The file should contain client IDs for all packages listed above for debugging to work properly.  **If you do not generate your own file FCM push notification will never work, only websocket notifications will**)
6. Start Android Studio, open your source code folder and check if the Gradle build will be successful using Build/Make Module "App". You might have to install the right Android SDK via Tools/SDK Manager first.
7. Run `gradlew assembleDebug` to build all debug versions, this might take a while.
8. If the build is successful, you can run the app by doing the following: click **Run** -> **Run 'app'**.
9. Connect your phone or create a new virtual device following on screen instruction
10. :tada:

If you get stuck while setting up your own environment, you can ask questions in the **#devs_mobile_apps** channel on [Discord](https://discord.gg/c5DvZ4e).

