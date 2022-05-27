package controllers;

import models.Member;
import models.Reading;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.Date;

public class StationCtrl extends Controller {

  public static void index(Long id) {
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    if (!member.stations.contains(station)) redirect("/dashboard");
    Logger.info("Station id = " + id);
    render("station.html", station);
  }

  public static void addReading(Long id, int code, float temperature, float windSpeed, float windDirection, int pressure) {
    Member member = Accounts.getLoggedInMember();
    Date date = new Date(System.currentTimeMillis());
    Reading reading = new Reading(code, temperature, windSpeed, pressure, windDirection, date);
    Station station = Station.findById(id);
    station.readings.add(reading);
    station.save();
    redirect("/stations/" + id);
  }

  public static void deleteReading(Long id, Long readingid) {
    Member member = Accounts.getLoggedInMember();
    Station station = Station.findById(id);
    Reading reading = Reading.findById(readingid);
    Logger.info("Removing reading");
    station.readings.remove(reading);
    station.save();
    reading.delete();
    redirect("/stations/" + id);
  }
}
