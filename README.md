# Weather Application in Java (Android)

![Total Views](https://views.whatilearened.today/views/github/pmoschos/WeatherApp.svg)![GitHub last commit](https://img.shields.io/github/last-commit/pmoschos/WeatherApp) ![License](https://img.shields.io/badge/license-MIT-green.svg)

## 🌦️ Overview

This project is an **Android Weather App** built with Java. The app leverages the **OpenWeatherMap API** to fetch real-time weather data for any city entered by the user. It displays weather details such as temperature, wind speed, humidity, sunrise and sunset times, and provides a visual representation using **Picasso** for weather icons.

The app emphasizes intuitive user interaction, real-time data retrieval, and visually appealing UI elements.

---

## Key Features ✨

- **Search Functionality**: Users can search for weather information by city name.
- **Real-Time Weather Data**: Fetches live weather updates using the **OpenWeatherMap API**.
- **Beautiful Icons**: Displays weather conditions visually using **Picasso**.
- **Detailed Metrics**: Shows temperature (current and "feels like"), wind speed, humidity, sunrise, and sunset times.
- **Interactive Sun Progress Bar**: Includes a semicircular arc to visualize the sun's position relative to sunrise and sunset times.

---

## Screenshots 📸

Below are screenshots of the app running on an Android device:

![image](https://github.com/user-attachments/assets/f211e890-a47a-4f7e-97d4-0444f45d9d02)

---

## Libraries Used 📚

### Core Libraries:
- **AndroidX Libraries**:
  ```gradle
  implementation libs.appcompat
  implementation libs.material
  implementation libs.activity
  implementation libs.constraintlayout
  ```
- **Testing Libraries**:
  ```gradle
  testImplementation libs.junit
  androidTestImplementation libs.ext.junit
  androidTestImplementation libs.espresso.core
  ```
- **Volley**: For seamless networking and API requests.
  ```gradle
  implementation 'com.android.volley:volley:1.2.1'
  ```
- **Picasso**: For efficient image loading and caching.
  ```gradle
  implementation 'com.squareup.picasso:picasso:2.71828'
  ```

---

## Technical Requirements 🔧

- **Android Studio**: Version 4.1 or later.
- **Java JDK**: Version 11.
- **Min SDK**: API level 28 (Android 9.0)
- **Target SDK**: API level 34.
- **OpenWeatherMap API Key**: Required for fetching weather data.

---

## Setup and Installation 🚀

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/pmoschos/WeatherApp
   ```

2. **Open in Android Studio**:
   - Open the project in Android Studio.
   - Sync Gradle files to install dependencies.

3. **Add API Key**:
   - Register on [OpenWeatherMap](https://openweathermap.org/) and generate an API key.
   - Replace the placeholder `YOUR_API_KEY` in the following line of `MainActivity.java`:
     ```java
     String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=YOUR_API_KEY";
     ```

4. **Build and Run**:
   - Connect your Android device or use an emulator.
   - Click `Run` to build and launch the app.

---

## User Interface 📱

### 1. **Search Bar**:
   - The user enters a city name in the text input to search for weather data.
   - Triggered by the **search icon** in the `TextInputLayout`.

### 2. **Weather Display**:
   - **Temperature**: Shows current and "feels like" temperature.
   - **Description**: Provides a short textual description of the weather.
   - **Humidity & Wind Speed**: Displays detailed metrics for better insights.

### 3. **Sunrise & Sunset Visual**:
   - Sunrise and sunset times are displayed along with an interactive sun progress bar.

---

## Example Code Snippets 💻

### Fetching Weather Data
```java
String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=YOUR_API_KEY";
JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
    new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            // Parse JSON data
            String main = response.getJSONArray("weather").getJSONObject(0).getString("main");
            String description = response.getJSONArray("weather").getJSONObject(0).getString("description");
            double temp = response.getJSONObject("main").getDouble("temp");
            // Update UI with parsed data
        }
    },
    new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(MainActivity.this, "Error fetching data!", Toast.LENGTH_SHORT).show();
        }
    });
requestQueue.add(jsonObjectRequest);
```

### Image Loading with Picasso
```java
String imageUrl = "https://openweathermap.org/img/wn/" + icon + "@2x.png";
Picasso.get()
    .load(imageUrl)
    .placeholder(R.drawable.ic_sun) // Fallback image
    .error(R.drawable.ic_error)     // Error image
    .into(ivWeather);
```

---

## Future Enhancements 📈

- **Location-based Weather**: Fetch data automatically based on GPS location.
- **Forecast View**: Add a 7-day weather forecast.
- **Theme Support**: Introduce light and dark themes for better usability.


---

## 📢 Stay Updated

Be sure to ⭐ this repository to stay updated with new examples and enhancements!

## 📄 License
🔐 This project is protected under the [MIT License](https://mit-license.org/).


## Contact 📧
Panagiotis Moschos - pan.moschos86@gmail.com

🔗 *Note: This is a Python script and requires a Python interpreter to run.*

---
<h1 align=center>Happy Coding 👨‍💻 </h1>

<p align="center">
  Made with ❤️ by 
  <a href="https://www.linkedin.com/in/panagiotis-moschos" target="_blank">
  Panagiotis Moschos</a> (https://github.com/pmoschos)
</p>






