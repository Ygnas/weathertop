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

  public static void setLatestReadings(Reading latestReadings) {
    latestReadings.weatherCondition = setWeatherCondition(latestReadings);
    latestReadings.temperatureInF = setTemperatureInF(latestReadings);
    latestReadings.windInBeaufort = calculateBeaufort(latestReadings.windSpeed);
    latestReadings.windCompass = getCardinalDirection(latestReadings.windDirection);
    latestReadings.windChill = windChill(latestReadings.temperature, latestReadings.windSpeed);

  }

  public static String setWeatherCondition(Reading reading) {
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

  public static double setTemperatureInF(Reading reading) {
    return reading.temperature * 9 / 5 + 32;
  }

  public static int calculateBeaufort(double windSpeed) {
    if (windSpeed < 1) {
      return 0;
    } else if (windSpeed >= 1 && windSpeed <= 5) {
      return 1;
    } else if (windSpeed >= 6 && windSpeed <= 11) {
      return 2;
    } else if (windSpeed >= 12 && windSpeed <= 19) {
      return 3;
    } else if (windSpeed >= 20 && windSpeed <= 28) {
      return 4;
    } else if (windSpeed >= 29 && windSpeed <= 38) {
      return 5;
    } else if (windSpeed >= 39 && windSpeed <= 49) {
      return 6;
    } else if (windSpeed >= 50 && windSpeed <= 61) {
      return 7;
    } else if (windSpeed >= 62 && windSpeed <= 74) {
      return 8;
    } else if (windSpeed >= 75 && windSpeed <= 88) {
      return 9;
    } else if (windSpeed >= 89 && windSpeed <= 102) {
      return 10;
    } else if (windSpeed >= 103 && windSpeed <= 117) {
      return 11;
    }
    return 0;
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
}
