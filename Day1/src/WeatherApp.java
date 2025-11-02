package Day1.src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApp {
// Replace with your own OpenWeather API key
private static final String API_KEY = "b2aeacf1b39a79050f17a4f7f6f7a614";
private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

public static void main(String[] args) {
String city = "London"; // You can change this or take user input
getWeather(city);
}

public static void getWeather(String city) {
try {
// Build URL with query parameters
String urlString = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";

// Create connection
@SuppressWarnings("deprecation")
URL url = new URL(urlString);
HttpURLConnection conn = (HttpURLConnection) url.openConnection();
conn.setRequestMethod("GET");

// Check response
int responseCode = conn.getResponseCode();
if (responseCode == HttpURLConnection.HTTP_OK) {
BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
StringBuilder response = new StringBuilder();
String line;
while ((line = in.readLine()) != null) {
response.append(line);
}
in.close();

// Parse JSON response
JSONObject json = new JSONObject(response.toString());
JSONObject main = json.getJSONObject("main");
double temp = main.getDouble("temp");
int humidity = main.getInt("humidity");
String weather = json.getJSONArray("weather").getJSONObject(0).getString("description");

System.out.println("City: " + city);
System.out.println("Temperature: " + temp + " °C");
System.out.println("Humidity: " + humidity + "%");
System.out.println("Weather: " + weather);
} else {
System.out.println("Error: Unable to fetch weather data. HTTP code " + responseCode);
}
} catch (Exception e) {
e.printStackTrace();
}
}
}