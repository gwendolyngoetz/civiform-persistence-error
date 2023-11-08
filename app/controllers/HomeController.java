package controllers;

import java.util.Optional;

import io.ebean.DB;
import io.ebean.Database;
import models.Applicant;
import play.mvc.Controller;
import play.mvc.Result;

/** Controller for handling methods for the landing pages. */
public class HomeController extends Controller {

    private final Database database;

    public HomeController() {
        this.database = DB.getDefault();
    }

    public Result index() {
        /*
            docker pull postgres:12.16
            docker run --name civipostgres -d -p 5432:5432 -e POSTGRES_PASSWORD=example postgres:12.16
        */


        // Create applicant
        Applicant applicantNew = new Applicant();
        database.insert(applicantNew);

        // Add value and update applicant
        applicantNew.getApplicantData().putString("test123");
        database.update(applicantNew);

        // Load applicant from database and get value
        Applicant applicantQueried = database.find(Applicant.class).setId(applicantNew.id).findOneOrEmpty().get();
        Optional<String> queriedValue = applicantQueried.getApplicantData().readString();

        // Value is empty, but should not be
        if (queriedValue.isEmpty()) {
            throw new RuntimeException(String.format("queriedValue.isEmpty(). id %d", applicantQueried.id));
        }

        return ok(String.format("queriedValue has a value of %s. id %d", queriedValue.get(), applicantQueried.id));
    }
}
