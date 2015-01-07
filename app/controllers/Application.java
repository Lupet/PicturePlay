package controllers;

import models.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import play.mvc.Controller;
import play.mvc.Result;
import services.PictureDAO;
import services.UserDAO;
import views.html.*;

import java.util.List;

import static play.data.Form.form;

@org.springframework.stereotype.Controller
public class Application extends Controller {

    @Autowired
    PictureDAO pictureDAO;
    @Autowired
    UserDAO userDAO;

    // Navigation
    public Result index() {
        return ok(index.render());
    }
    public Result login() {
        return ok(login.render(form(UserForm.class)));
    }
    public Result register() {
        return ok(register.render(form(UserForm.class)));
    }
    public Result upload() {
        return ok(upload.render(form(PictureForm.class)));
    }
    public Result view() {

        // pictures des users
        List<Picture> pictures = pictureDAO.getPictures(userDAO.findUserByName(session("connected")), 0, 200, false);

        return ok(view.render(pictures));
    }

    // Als Willkommenstext den Name aus der Session fischen
    public Result member() {
        String name = session("connected");
        return ok(member.render(name));
    }

    // Logout, Session löschen
    public Result logout(){
        session().clear();
        return ok(index.render());
    }




    // Forms
    public static class UserForm {
        public String username;
        public String email;
        public String password;
    }

    public static class PictureForm {
        public String description;
        public String name;
        public boolean isPrivate;
    }

}