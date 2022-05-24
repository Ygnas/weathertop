package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Reading extends Model {
  public int code;
  public double temperature;
  public double windSpeed;
  public int pressure;
  public float windDirection;
  public Date date;

  public Reading(int code, double temperature, double windSpeed, int pressure, float windDirection, Date date) {
    this.code = code;
    this.temperature = temperature;
    this.windSpeed = windSpeed;
    this.pressure = pressure;
    this.windDirection = windDirection;
    this.date = date;
  }
}