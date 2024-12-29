package gr.aueb.cf.openweatherapp2024a.activities;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.MessageFormat;
import java.util.Objects;

import gr.aueb.cf.openweatherapp2024a.R;
import gr.aueb.cf.openweatherapp2024a.utils.SunPositionUtil;
import gr.aueb.cf.openweatherapp2024a.utils.TimeFormatterUtil;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout searchbar;
    private TextInputEditText searchInput;
    private ImageView ivWeather;
    private TextView tvWeatherDescription;
    private TextView tvTemperature;
    private TextView tvWindSpeed;
    private TextView tvHumidity;
    private TextView tvFeelTemp;
    private TextView tvSunriseTime;
    private TextView tvSunsetTime;
    private ImageView sunIcon;
    private FrameLayout arcContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize view
        initView();

        searchbar.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get text enter in the search bar
                String searchText = Objects.requireNonNull(searchbar.getEditText()).getText().toString().trim();

                if (!searchText.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Searching for: " + searchText, Toast.LENGTH_SHORT).show();

                    // Create a request queue
                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    String city = Objects.requireNonNull(searchInput.getText()).toString().trim();

                    // Construct my target url
                    String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=9172fac9d0b1b74505f6807281e786ec";

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                // Extracting data from the response
                                System.out.println("Response: " + response.toString());

                                // 1. Weather -> main, description, icon
                                JSONArray weatherArray = response.getJSONArray("weather");
                                JSONObject weatherObject = weatherArray.getJSONObject(0);
                                String main = weatherObject.getString("main");
                                String description = weatherObject.getString("description");
                                String icon = weatherObject.getString("icon");

                                // 2. Main -> temp, feels_like, humidity
                                JSONObject mainObject = response.getJSONObject("main");
                                double temp = mainObject.getDouble("temp");
                                double feelsLike = mainObject.getDouble("feels_like");
                                int humidity = mainObject.getInt("humidity");

                                // 3. Wind -> speed
                                JSONObject windObject = response.getJSONObject("wind");
                                double windSpeed = windObject.getDouble("speed");

                                // 4. Sys -> sunrise, sunset
                                JSONObject sysObject = response.getJSONObject("sys");
                                long sunrise = sysObject.getLong("sunrise");
                                long sunset = sysObject.getLong("sunset");

                                // Update my UI !
                                // weahter icon
                                String imageUrl = "https://openweathermap.org/img/wn/" + icon +"@2x.png";

                                // Load image using Picasso Library
                                Picasso.get()
                                        .load(imageUrl)
                                        .placeholder(R.drawable.ic_sun) // Optional: placeholder
                                        .error(R.drawable.ic_error) // Optional: error image
                                        .into(ivWeather);

                                tvWeatherDescription.setText(description);
                                tvTemperature.setText(String.format("%s %sC",
                                        String.format("%s", (int)(temp - 273.15)),
                                        String.format("%s", Html.fromHtml(getString(R.string.temperature_text), Html.FROM_HTML_MODE_LEGACY))
                                ));
                                tvWindSpeed.setText(String.format("%s km/h", windSpeed));
                                tvHumidity.setText(MessageFormat.format("{0}%", String.format("%s", humidity)));
                                tvFeelTemp.setText(String.format("%s %sC",
                                        String.format("%s", (int)(feelsLike - 273.15)),
                                        String.format("%s", Html.fromHtml(getString(R.string.temperature_text), Html.FROM_HTML_MODE_LEGACY))
                                ));
                                String sunriseTime = TimeFormatterUtil.getFormattedTime(sunrise);
                                tvSunriseTime.setText(sunriseTime);

                                String sunsetTime = TimeFormatterUtil.getFormattedTime(sunset);
                                tvSunsetTime.setText(sunsetTime);

                                // Calculate sun progress
                                double sunProgress = SunPositionUtil.calculateSunProgress(sunrise, sunset);
                                SunPositionUtil.setSunPosition(arcContainer, sunIcon, sunProgress);


                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    // TODO: Add the request to request queue
                    requestQueue.add(jsonObjectRequest);

                } else {
                    Toast.makeText(MainActivity.this, "Search field is empty", Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    private void initView() {
        searchbar = findViewById(R.id.search_bar);
        searchInput = findViewById(R.id.search_input);
        ivWeather = findViewById(R.id.iv_weather);
        tvWeatherDescription = findViewById(R.id.tv_weather_description);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvWindSpeed = findViewById(R.id.tv_wind_speed);
        tvHumidity = findViewById(R.id.tv_humidity);
        tvFeelTemp = findViewById(R.id.tv_feel_temp);
        tvSunriseTime = findViewById(R.id.tv_sunrise_time);
        tvSunsetTime = findViewById(R.id.tv_sunset_time);
        sunIcon = findViewById(R.id.sun_icon);
        arcContainer = findViewById(R.id.arc_container);
    }
}