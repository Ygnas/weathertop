package controllers;

import models.Member;
import models.Station;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller {
  public static void index() {
    Logger.info("Rendering Dashboard");
    Member member = Accounts.getLoggedInMember();
    List<Station> stations = member.stations;
    for (Station station : stations) {
      station.updateLatestData();
    }
    render("dashboard.html", stations);
  }

  public static void addStation(String name,double latitude,double longitude)
  {
    Station station = new Station(name,latitude,longitude);
    Logger.info ("Adding a new station called " + name);
    Member member = Accounts.getLoggedInMember();
    member.stations.add(station);
    member.save();
    redirect ("/dashboard");
  }
}