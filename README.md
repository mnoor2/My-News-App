# My News App

My News App is a news application developed in Kotlin using Android Studio. It fetches news from a news API and displays it in a user-friendly interface.

## Major Components

- **Kotlin**: The main programming language used for development.
- **Android Studio**: The IDE used for the project.
- **Retrofit**: A type-safe HTTP client for Android and Java.
- **Moshi**: A modern JSON library for Android and Java. It makes it easy to parse JSON into Kotlin and Java objects.
- **Glide**: An image loading and caching library for Android focused on smooth scrolling.

## Setup

1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build the project and run on an emulator or a real device.

## How to Use API Key

The API key is stored in a separate file named `ApiKey.kt` which you will create by right clicking the package name and doing `New` > `Kotlin Class/File` > `Object` and then naming it `ApiKey` 

You can replace `"your_api_key_here"` with your actual API key.
Get your API key from here: [NewsAPI](https://newsapi.org/)

```kotlin
package edu.msudenver.cs3013.mynewsapp

object ApiKey {
    const val KEY = "your_api_key_here"
}
```
## Usage

1. On launching the app, you will see a list of news articles.
2. You can scroll through the list to see more articles.
3. Clicking on an article will open a detailed view of the article.
4. In the detailed view, you can see the full article image, title, description, and content.
5. There is also a link to the full article which opens in your default browser when clicked.
6. There is a back button on the action bar that takes you back to the main activity.
