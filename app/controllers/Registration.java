package controllers;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import play.Logger;
import play.data.Form;
import play.mvc.*;
import services.UserDAO;
import views.html.login;
import views.html.register;

import static play.data.Form.form;

@org.springframework.stereotype.Controller
public class Registration extends Controller {

    @Autowired
    private UserDAO userDAO;

    public Result doRegistration() {
        boolean created = false;
        Form<Application.UserForm> registrationForm = Form.form(Application.UserForm.class);
        Application.UserForm regData = registrationForm.bindFromRequest().get();

        User user = userDAO.findUserByName(regData.username);

        if (user == null) {
            user = new User(regData.email, regData.password, regData.username, User.ROLE_USER);
            userDAO.createUser(user);
            created = true;
        }

        Logger.info(created ? "    User " + user.getName() + " erstellt." : "    User existiert bereits.");

        return created ? created(login.render(form(Application.UserForm.class))) : forbidden(register.render(form(Application.UserForm.class))) ;
    }
}
