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

  public Station(String name) {
    this.name = name;
  }

  public void updateLatestData() {
    latestReadings = StationUtils.getLatestReading(readings);
    if (latestReadings != null) {
      StationUtils.setLatestReadings(latestReadings);
    }
  }
}