# 🗺️ Gezi AI Guide - AI Tourist Assistant App

**Gezi AI Guide** is a next-generation mobile application built using Android and Jetpack Compose. It leverages the power of Google Gemini AI to provide travelers with personalized, real-time itineraries for exploring Georgia. The app seamlessly integrates modern UI/UX standards, local caching, interactive maps, and complete localization, making it a production-ready, high-quality digital product.

---

## 🚀 Core Features

* **🤖 Gemini AI Personal Guide:** Embedded with the Google Gemini AI SDK to analyze user preferences, current location, and generate highly customized travel plans.
* **🗺️ Interactive Google Maps:** Built-in map integration allowing users to explore historical sites, cultural landmarks, and natural wonders in real-time.
* **📊 Premium Profile Dashboard:**
    * **Live Weather Widget:** Displays real-time weather updates (e.g., current conditions in Tbilisi) directly inside the profile page.
    * **Analytics Grid:** Tracks generated routes, saved places, remaining trip days, and active AI chat counts.
    * **Recommended Itineraries:** Beautiful vertical timeline cards (e.g., *Old Tbilisi Heritage Walk*, *Caucasus Mountain Escape*) complete with venue hours, photos, and descriptions.
* **🌓 Full Dark Mode Support:** Completely adaptive UI/UX designed for both Light and Dark modes to ensure comfortable navigation under any lighting conditions.
* **🌐 Multilingual Localization:** Seamless dual-language support for **Georgian 🇬🇪 and English 🇬🇧**, automatically matching the system language or user preferences.
* **📦 Offline Caching (Room Database):** Saves popular destinations and generated itineraries locally to ensure seamless access even without an internet connection.

---

## 🛠️ Tech Stack

* **Language:** Kotlin (100%)
* **UI Framework:** Jetpack Compose (Declarative UI)
* **Architecture:** MVVM (Model-View-ViewModel) + Clean Architecture Principles
* **Artificial Intelligence:** Google Generative AI SDK (Gemini API)
* **Local Database:** Room DB (SQLite abstraction layer for offline caching)
* **Networking & API:** Retrofit 2 & Gson (for weather and remote travel data fetching)
* **Navigation:** Jetpack Compose Navigation Component
* **Asynchronous Execution:** Kotlin Coroutines & Flow
* **Dependency Management:** Gradle Kotlin DSL (`.kts`) via Version Catalogs (`libs.versions.toml`)

---

## 📂 Project Structure

```text
com.example.geziaiguide/
│
├── data/                 # Data Layer (Repositories, Room DB, Local/Remote Data Sources)
│   ├── local/            # Room Database Entities and DAOs
│   └── remote/           # Retrofit API Interfaces and Gemini SDK Integration
│
├── ui/                   # Presentation Layer (Compose Screens, Custom Components, Theme)
│   ├── screens/          # Core views (Home, Map, Profile/Dashboard, Places)
│   ├── components/       # Reusable Compose elements (Timeline, WeatherCard)
│   └── theme/            # Styling definitions: Color.kt, Theme.kt (Dark/Light), Type.kt
│
├── viewmodel/            # Business Logic & UI State Management (PlacesViewModel, ProfileViewModel)
│
└── MainActivity.kt       # Application Entry Point & Navigation Graph Setup