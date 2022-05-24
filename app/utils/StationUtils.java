package utils;

import models.Reading;
import models.Station;

import java.util.HashMap;
import java.util.List;

public class StationUtils {

  public static Reading getLatestReading(List<Reading> readings) {
    Reading reading = null;
    if (!readings.isEmpty()) {
      reading = readings.get(readings.size() - 1);
    }
    return reading;
  }

  public static String setWeatherCondition(Reading reading) {
    if (reading == null) return "";
    switch (reading.code) {
      case 100:
        return "Clear";
      case 200:
        return "Partial Clouds";
      case 300:
        return "Cloudy";
      case 400:
        return "Light Showers";
      case 500:
        return "Heavy Showers";
      case 600:
        return "Rain";
      case 700:
        return "Snow";
      case 800:
        return "Thunder";
      default:
        return "";
    }
  }

  public static double getTemperatureInF(Double temperature) {
    return temperature * 9 / 5 + 32;
  }

  public static int calculateBeaufort(double windSpeed) {
    if (windSpeed < 1) {
      return 0;
    } else if (windSpeed <= 5) {
      return 1;
    } else if (windSpeed <= 11) {
      return 2;
    } else if (windSpeed <= 19) {
      return 3;
    } else if (windSpeed <= 28) {
      return 4;
    } else if (windSpeed <= 38) {
      return 5;
    } else if (windSpeed <= 49) {
      return 6;
    } else if (windSpeed <= 61) {
      return 7;
    } else if (windSpeed <= 74) {
      return 8;
    } else if (windSpeed <= 88) {
      return 9;
    } else if (windSpeed <= 102) {
      return 10;
    } else {
      return 11;
    }
  }

  public static String getCardinalDirection(float windDirection) {
    String[] directions = {"North", "North North East", "North East",
        "East North East", "East", "East South East", "South East",
        "South South East", "South", "South South West", "South West",
        "West South West", "West", "West North West", "North West", "North North West", "North"};
    return directions[(int) Math.round((windDirection % 360) / 22.5)];
  }

  public static double windChill(double temp, double wind) {
    double windChill = 13.12 + 0.6215 * temp - 11.37 * Math.pow(wind, 0.16) + 0.3965 * temp * Math.pow(wind, 0.16);
    return (int) (windChill * 100) / 100.0;
  }

  public static void setMinMaxValues(Station station) {
    station.temperatureMin = station.windSpeedMin = station.pressureMin = Float.MAX_VALUE;
    for (Reading reading : station.readings) {
      station.temperatureMin = Math.min(station.temperatureMin, reading.temperature);
      station.temperatureMax = Math.max(station.temperatureMax, reading.temperature);
      station.windSpeedMin = Math.min(station.windSpeedMin, reading.windSpeed);
      station.windSpeedMax = Math.max(station.windSpeedMax, reading.windSpeed);
      station.pressureMin = Math.min(station.pressureMin, reading.pressure);
      station.pressureMax = Math.max(station.pressureMax, reading.pressure);
    }
  }

  public static HashMap<String, String> updateTrends(Station station) {
    HashMap<String, String> stationTrends = null;
    if (station.readings.size() >= 3) {
      stationTrends = new HashMap<>();
      Reading first = station.readings.get(station.readings.size() - 1);
      Reading second = station.readings.get(station.readings.size() - 2);
      Reading third = station.readings.get(station.readings.size() - 3);
      if (first.temperature > second.temperature && second.temperature > third.temperature)
        stationTrends.put("temperature", "arrow up");
      if (first.temperature < second.temperature && second.temperature < third.temperature)
        stationTrends.put("temperature", "arrow down");
      if (first.windSpeed > second.windSpeed && second.windSpeed > third.windSpeed)
        stationTrends.put("wind", "arrow up");
      if (first.windSpeed < second.windSpeed && second.windSpeed < third.windSpeed)
        stationTrends.put("wind", "arrow down");
      if (first.pressure > second.pressure && second.pressure > third.pressure)
        stationTrends.put("pressure", "arrow up");
      if (first.pressure < second.pressure && second.pressure < third.pressure)
        stationTrends.put("pressure", "arrow down");
    }
    return stationTrends;
  }

  public static String weatherConditionIcon(String weatherCondition) {
    HashMap<String, String> weatherIconString = new HashMap<String, String>() {
      {
        put("Clear", "sun icon");
        put("Partial Clouds", "cloud sun");
        put("Cloudy", "cloud icon");
        put("Light Showers", "cloud sun rain icon");
        put("Heavy Showers", "cloud showers heavy icon");
        put("Rain", "cloud rain icon");
        put("Snow", "snowflake icon");
        put("Thunder", "bolt icon");
      }
    };
    return weatherIconString.get(weatherCondition);
  }
}
