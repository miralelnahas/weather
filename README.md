# Weather forecast

## Installation

You can download latest APK file from the [releases](https://github.com/miralelnahas/weather/releases/tag/release%2F1.0) page


## Building and Running

To build and run the application, you need to obtain your api key from [here](https://home.openweathermap.org/api_keys). Once you have your api key, add it to your `local.properties` file in the following format:

API_KEY=****

Ensure that you replace `****` with your actual access token.

## Application Architecture

In Weather Forecase App, I followed clean architecture using Model-View-ViewModel (MVVM) pattern

*Clean Architecture Layers:*
- **UI Layer:** Interacts with the user interface.
- **Domain Layer:** Defines user-triggered actions and models
- **Data Layer:** Abstract definition of all the data sources and repositories

*To Acheive this, I divided my app into four modules:*
- **App Module:** Manages app-level dependencies.
- **UI Module:** Handles user interface-related code.
- **Domain Module:** Implements use cases and models
- **Data Module:** Implements the data sources and repositories

## Important Technologies Used:
- **Hilt:** Dependency injection framework.
- **Navigation Component:** Handles navigation between different screens.
- **Room Database:** Used for local caching.
- **Glide:** Image loading library for retrieving images from a server.
- **Retrofit:** For API Requests
