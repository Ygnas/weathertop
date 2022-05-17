package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public int pressure;
  public String weatherCondition;
  public double temperatureInF;
  public int windInBeaufort;
  public float windDirection;
  public String windCompass;
  public double windChill;

  public Reading(int code, double temperature, double windSpeed, int pressure, float windDirection) {
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.pressure = pressure;
    this.windDirection = windDirection;
  }
}