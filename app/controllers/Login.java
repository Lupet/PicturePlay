package controllers;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import play.Logger;
import play.data.Form;
import play.mvc.*;
import services.UserDAO;
import views.html.*;

import static play.data.Form.form;

@org.springframework.stereotype.Controller
public class Login extends Controller {

    @Autowired
    UserDAO userDAO;

    public Result doLogin(){
        boolean authorized = false;
        // Daten aus der Form holen
        Form<Application.UserForm> loginForm = Form.form(Application.UserForm.class);
        Application.UserForm loginData = loginForm.bindFromRequest().get();

        // User suchen
        User user = userDAO.findUserByName(loginData.username, loginData.password);

        if (user == null){
            Logger.info("Fehler bei Anmeldung");
        }
        else{
            session("connected", user.getName());
            Logger.info("User " + loginData.username + " angemeldet");
            authorized = true;
        }

        // wenn Logindaten korrekt, in privaten Bereich weiterleiten, ansonsten Seite neuladen
        return authorized ? ok(member.render(user.getName())) : unauthorized(views.html.login.render(form(Application.UserForm.class)));
    }

}
