# Compose Capybara

**An Educational Game to Master the Basics of Jetpack Compose Layouts**

Compose Capybara is a playful and interactive game designed to teach anyone the basics of Jetpack Compose layouts. The game is built entirely using **Kotlin** and leverages **Compose Multiplatform** to run seamlessly on **Web, Desktop, iOS, and Android** while sharing almost all of the same code.

Compose Capybara was made for [JetBrain's Kotlin Multiplatform Contest](https://kotlinconf.com/contest/) but I have enjoyed building it so much that I hope to add more features (e.g., infinite mode, support for modifiers, adaptive layouts) in the future. 

## Features
- 🎮 **20 Engaging Levels**: Progressively learn Compose layout concepts.
- 🎨 **Animations and playful style**: Enhance understanding through dynamic visuals.
- 💾 **Level Completion Saving**: Resume where you left off.
- 🧩 **Clickable Hints**: Get help when you need it.
- 🔧 **Difficulty Settings**: Tailor the challenge to your skill level.
- 👁 **Colorblind Mode**: Accessibility-friendly design.
- 🧭 **Smooth Navigation**: Powered by Compose Type-Safe Navigation.

### Demos
https://github.com/user-attachments/assets/5467ffa9-e840-4c7b-a3ae-925072a5e4be

https://github.com/user-attachments/assets/b2b5a6ce-e528-4c7b-a3fb-3a115498d72d

https://github.com/user-attachments/assets/06e11317-78ae-4117-9d68-b623946ccaf2

https://github.com/user-attachments/assets/721e9707-a4a3-4779-9154-c541125cf18c

## Technologies

• [**Compose Multiplatform**](https://www.jetbrains.com/lp/compose-multiplatform/)**:** Enabling almost 100% shared code across all platforms (Web, iOS, Android, Desktop)

• [**Koin**](https://insert-koin.io/)**:** Lightweight dependency injection framework.

• [**Multiplatform Settings**](https://github.com/russhwolf/multiplatform-settings)**:** Used for game settings, colorblind mode, and level completion saving.

## Architecture Overview

Compose Capybara is built using a **configuration-driven UI architecture**. 

Each level is defined by a `LevelConfig`, which specifies the layout (`StageLayout`) with container type (`Box`, `Column`, or `Row`) and element (`Capybara` or `Cushion`) positions. The `LevelConfig` also specifies instructions, clickable hints, and valid answers. 

`LevelStage()` is a generic composable that dynamically renders the UI based on this `LevelConfig`, eliminating the need to interact with UI code to create a new level. 

This architecture allowed for rapid development of 20 levels with rich instructions and hints.
### Benefits
- **Scalability**: Adding new levels requires no UI code changes—only the definition of a `LevelConfig`. This will also allow for a simpler implementation of an infinite mode in the future.
- **Maintainability**: The repository-driven approach separates the data from the UI, making the app easier to extend and debug.
- **Reusability**: The `LevelStage` composable is generic and reusable, designed to handle any `LevelConfig` passed to it. Optionally, if a `StageLayout` is null, one can specify a custom (non-config driven) layout.

## Inspiration

I began my programming journey in the final year of my undergraduate degree and came across [Flexbox Froggy](https://github.com/thomaspark/flexboxfroggy/) (a game to learn CSS flexbox) when self-studying web development. At the time, I was contemplating a career switch from neuroscience to software engineering. The game made programming feel approachable and playful, and it resonated deeply with me, showing how impactful software can be.  

With Compose Capybara, I wanted to bring that same sense of joy and accessibility to learning **Jetpack Compose layouts** while exploring the incredible capabilities of **Compose Multiplatform**. My goal was to create a resource that inspires others to fall in love with programming, just as I did.

## Environment Setup

This project is built with **Kotlin Multiplatform** and leverages **Compose Multiplatform**, enabling it to run on Web, Android, Desktop, and iOS. Below are the instructions to set up and run the project on each platform. Compose Capybara is best experienced on Web and Desktop, but it will run (with occasional ui quirks) on Android and iOS tablets. Adaptive Layouts for different screen sizes are still in development.

If you want to run the project locally, you'll first need to [clone the repository](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository).

For the the easiest setup, download the latest version of [Android Studio](https://developer.android.com/studio) and install the [Kotlin Multiplatform plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform) which will help with making running Compose Capybara locally much simpler. 

The [Run your application](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-multiplatform-create-first-app.html#run-your-application) instructions from JetBrains regarding setting up a development environment for a Kotlin Multiplatform project are handy as well.
### Web

The web version of this project is published to GitHub Pages for easy access. 

[Play Now](https://taxikab119.github.io/compose-capybara/)

You can also clone the repository and run it it locally following the Run your application instructions mentioned earlier.

### Desktop

Once you have cloned the repository you can run the desktop configuration of Compose Capybara using a Gradle run configuration with `composeApp:run`
### Android

>*Note*, in its current form, Compose Capybara will only run well on Tablets. Adaptive Layouts for different screen sizes are on the way.

To run your application on an Android emulator:

1. Ensure you have an Android virtual device (Pixel Tablet recommended). Otherwise, [create one](https://developer.android.com/studio/run/managing-avds#createavd).
2. In the list of run configurations, select `composeApp`.
3. Choose your virtual device and click **Run**:

### iOS

>**Warning** You need a Mac with macOS to write and run iOS-specific code on simulated or real devices. This is an Apple requirement. 
>
>Also, as with Android, Compose Capybara will only run well on Tablet form factors (like an iPad). Adaptive Layouts for different screen sizes are on the way.

1. Ensure you have an iOS simulator (iPad recommended). Otherwise [create one](https://developer.apple.com/documentation/safari-developer-tools/installing-xcode-and-simulators)
2. Android Studio, select `iosApp` in the list of run configurations and click Run.
