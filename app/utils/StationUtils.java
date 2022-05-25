package utils;

import models.Reading;
import models.Station;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class StationUtils {

  /**
   * This method is used to get the latest reading
   *
   * @param readings This is a list of Station readings
   * @return This returns the latest reading
   */
  public static Reading getLatestReading(List<Reading> readings) {
    Reading reading = null;
    if (!readings.isEmpty()) {
      reading = readings.get(readings.size() - 1);
    }
    return reading;
  }

  /**
   * This method is used get the Weather Condition
   *
   * @param reading This is a reading
   * @return This returns a String of weather condition
   */
  public static String getWeatherCondition(Reading reading) {
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

  /**
   * This method is used to convert celsius to fahrenheit
   *
   * @param temperature This is temperature in celsius
   * @return This returns temperature to fahrenheit
   */
  public static double getTemperatureInF(Double temperature) {
    return temperature * 9 / 5 + 32;
  }

  /**
   * This method is used to get Beaufort value
   *
   * @param windSpeed This is wind speed
   * @return This returns an int based on wind Speed
   */
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

  /**
   * This method is used to get the Cardinal Direction
   *
   * @param windDirection This is a list of Station readings
   * @return This returns the Cardinal Direction
   */
  public static String getCardinalDirection(float windDirection) {
    String[] directions = {"North", "North North East", "North East",
        "East North East", "East", "East South East", "South East",
        "South South East", "South", "South South West", "South West",
        "West South West", "West", "West North West", "North West", "North North West", "North"};
    return directions[(int) Math.round((windDirection % 360) / 22.5)];
  }

  /**
   * This method is used to calculate how the temperature feels like
   *
   * @param temp This is a temperature
   * @param wind This is a wind speed
   * @return This returns how the temperature feels like
   */
  public static double windChill(double temp, double wind) {
    double windChill = 13.12 + 0.6215 * temp - 11.37 * Math.pow(wind, 0.16) + 0.3965 * temp * Math.pow(wind, 0.16);
    return (int) (windChill * 100) / 100.0;
  }

  /**
   * This method is used to set min/max values for each station
   *
   * @param station This is a Station
   */
  public static HashMap<String, Double> getMinMaxValues(Station station) {
    HashMap<String, Double> minMaxValues = new HashMap<>();
    minMaxValues.put("temperatureMin",Collections.min(station.readings, Comparator.comparing(reading -> reading.temperature)).temperature);
    minMaxValues.put("temperatureMax",Collections.max(station.readings, Comparator.comparing(reading -> reading.temperature)).temperature);
    minMaxValues.put("windSpeedMin",Collections.min(station.readings, Comparator.comparing(reading -> reading.windSpeed)).windSpeed);
    minMaxValues.put("windSpeedMax",Collections.max(station.readings, Comparator.comparing(reading -> reading.windSpeed)).windSpeed);
    minMaxValues.put("pressureMin",(double)Collections.min(station.readings, Comparator.comparing(reading -> reading.pressure)).pressure);
    minMaxValues.put("pressureMax",(double)Collections.max(station.readings, Comparator.comparing(reading -> reading.pressure)).pressure);
    return minMaxValues;
  }

  /**
   * This method is used to get the temperature, wind and pressure trends
   *
   * @param station This is a station
   * @return This returns a HashMap of trends
   */
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

  /**
   * This method is used to get the weather icon based on weatherCondition
   *
   * @param weatherCondition This is a weather Condition
   * @return This returns a HashMap of weather icons
   */
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
