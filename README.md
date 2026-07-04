# Gezi AI Guide

Gezi AI Guide is a modern Android application designed to help users explore beautiful places in Georgia. The app features an AI-powered travel assistant, a curated list of destinations, and a bookmarking system to save your favorite spots.

## Features

-   **Explore Destinations**: Browse a curated list of famous places in Georgia with beautiful images and ratings.
-   **AI Travel Assistant**: Chat with a Google Gemini-powered AI to get travel tips, history, and recommendations.
-   **Favorites**: Bookmark your favorite places for quick access later.
-   **Interactive UI**: Smooth animations using Lottie and a modern Material 3 design.
-   **Persistent Storage**: Uses Room database to store places and bookmark states locally.

## Architecture & Tech Stack

The app follows modern Android development practices and the **MVVM (Model-View-ViewModel)** architectural pattern.

-   **UI Framework**: Jetpack Compose (100% Kotlin, no XML layouts).
-   **Architecture**: MVVM with Clean Architecture principles.
-   **Dependency Injection**: ViewModel Factories are used for manual DI.
-   **Networking**: Retrofit & Google Generative AI SDK (Gemini).
-   **Database**: Room Persistence Library for local data.
-   **Image Loading**: Coil (Compose Image Loader).
-   **Animations**: Lottie for interactive loading states.
-   **Language**: Kotlin.

## Screens

1.  **Places Screen**: A list of must-visit destinations in Georgia.
2.  **Favorites Screen**: A dedicated space for your bookmarked locations.
3.  **AI Chat Screen**: An interactive chat interface where users can ask questions about Georgian travel.

## Prerequisites

-   Android Studio Koala or newer.
-   JDK 21.
-   An active internet connection for the AI features.

## Setup

1.  Clone the repository.
2.  Open the project in Android Studio.
3.  Ensure the `local.properties` file contains your Gemini API key (if applicable/required by current implementation).
4.  Build and run the app on an emulator or a physical device.

## Final Exam Note
This project was developed as a final exam project for the Mobile App Development course, demonstrating proficiency in Jetpack Compose, MVVM, and Room database integration.
