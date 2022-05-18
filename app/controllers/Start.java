package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Start extends Controller {
  public static void index() {
    Logger.info("Rendering Start");
    Member member = Accounts.getLoggedInMember();
    render("start.html");
  }
}
