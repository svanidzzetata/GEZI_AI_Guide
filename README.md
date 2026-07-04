# 🗺️ Gezi AI Guide - AI Tourist Assistant App

**Gezi AI Guide** არის უახლესი თაობის მობილური აპლიკაცია (Android / Jetpack Compose), რომელიც მოგზაურებს სთავაზობს პერსონალიზებულ, რეალურ დროში გენერირებულ ტურისტულ მარშრუტებს საქართველოში ხელოვნური ინტელექტის (Google Gemini AI) დახმარებით. აპლიკაცია აერთიანებს თანამედროვე UI/UX სტანდარტებს, ლოკალურ მონაცემთა ბაზებს, ინტერაქტიულ რუკებსა და მრავალენოვან მხარდაჭერას, რაც მას სრულყოფილ კომერციულ პროდუქტად აქცევს.

---

## 🚀 ძირითადი ფუნქციონალი (Features)

* **🤖 Gemini AI პერსონალური გიდი:** ინტეგრირებული Google Gemini AI SDK, რომელიც აანალიზებს მომხმარებლის ლოკაციას, ინტერესებს და აგენერირებს ოპტიმალურ ტურისტულ გეგმებს.
* **🗺️ Google Maps-ის ინტეგრაცია:** ინტერაქტიული რუკა, სადაც მომხმარებელს შეუძლია რეალურ დროში დაინახოს ისტორიული ძეგლები, კულტურული ობიექტები და ბუნებრივი ღირსშესანიშნაობები.
* **📊 მომხმარებლის პრემიუმ დეშბორდი (Profile Dashboard):** * **ამინდის ვიჯეტი (Live Weather Widget):** თბილისისა და სხვა ქალაქების მიმდინარე ამინდის ავტომატური ჩვენება.
    * **სტატისტიკის პანელი:** გენერირებული მარშრუტების, შენახული ადგილების, მოგზაურობის დღეებისა და AI-სთან საუბრების დეტალური ანალიტიკა.
    * **მარშრუტების ქრონოლოგია (Recommended Itineraries):** ვიზუალური ვერტიკალური Timeline ქარდები (მაგ. *Old Tbilisi Heritage Walk*, *Caucasus Mountain Escape*), სადაც თითოეულ ლოკაციას ახლავს სამუშაო საათები, ფოტო და აღწერა.
* **🌓 Dark Mode (მუქი თემა):** სრული UI/UX ადაპტაცია როგორც დღის (Light), ისე ღამის (Dark) რეჟიმისთვის, რაც უზრუნველყოფს კომფორტულ ნავიგაციას ნებისმიერ განათებაზე.
* **🌐 მრავალენოვანი მხარდაჭერა (Localization):** სრული ორენოვანი ინტეგრაცია (**ქართული 🇬🇪 და ინგლისური 🇬🇧**). აპლიკაცია ავტომატურად ერგება სისტემურ ენას ან იცვლება პარამეტრებიდან.
* **📦 ლოკალური ქეშირება (Room Database):** ადგილების და მარშრუტების შენახვა მოწყობილობის შიდა მეხსიერებაში ხაზგარეშე (Offline) რეჟიმში მუშაობისთვის.

---

## 🛠️ ტექნოლოგიური სტეკი (Tech Stack)

* **ენა:** Kotlin (100%)
* **UI ჩარჩო:** Jetpack Compose (Declarative UI)
* **არქიტექტურა:** MVVM (Model-View-ViewModel) + Clean Architecture-ის პრინციპები
* **ხელოვნური ინტელექტი:** Google Generative AI SDK (Gemini API)
* **მონაცემთა ბაზა:** Room DB (SQLite აბსტრაქცია ქეშირებისთვის)
* **ქსელი & API:** Retrofit 2 & Gson (ამინდისა და გარე მონაცემების მისაღებად)
* **ნავიგაცია:** Jetpack Compose Navigation Component
* **ასინქრონული კოდი:** Kotlin Coroutines & Flow
* **Dependency Management:** Gradle Kotlin DSL (`.kts`) ვერსიების კატალოგით (`libs.versions.toml`)

---

## 📂 პროექტის სტრუქტურა (Project Structure)

```text
com.example.geziaiguide/
│
├── data/                 # მონაცემების ფენა (Repositories, Room DB, Local/Remote Data Sources)
│   ├── local/            # Room Database, Entity-ები და DAO
│   └── remote/           # Retrofit API სერვისები და Gemini SDK
│
├── ui/                   # ვიზუალური ფენა (Compose Screens, Components, Theme)
│   ├── screens/          # ძირითადი ეკრანები (Home, Map, Profile/Dashboard, Places)
│   ├── components/       # ხელახლა გამოყენებადი Compose ელემენტები (Timeline, WeatherCard)
│   └── theme/            # Color.kt, Theme.kt (Dark/Light Mode), Type.kt
│
├── viewmodel/            # ბიზნეს ლოგიკა და UI State-ის მართვა (PlacesViewModel, ProfileViewModel)
│
└── MainActivity.kt       # აპლიკაციის მთავარი შესასვლელი წერტილი და Navigation Graph