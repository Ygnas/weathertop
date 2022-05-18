package models;

import play.db.jpa.Model;
import utils.StationUtils;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Station extends Model {
  public String name;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Reading> readings = new ArrayList<>();
  public Reading latestReadings;
  public double latitude;
  public double longitude;
  public double temperatureMax;
  public double temperatureMin;
  public double windSpeedMax;
  public double windSpeedMin;
  public double pressureMax;
  public double pressureMin;

  public Station(String name, double latitude, double longitude) {
    this.name = name;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public void updateLatestData() {
    latestReadings = StationUtils.getLatestReading(readings);
    if (latestReadings != null) {
      StationUtils.setLatestReadings(latestReadings);
    }
  }
}