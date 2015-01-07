package controllers;

import models.Picture;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import play.Logger;
import play.data.Form;
import play.mvc.*;
import services.PictureDAO;
import services.UserDAO;
import views.html.upload;

import java.io.*;
import java.util.List;

import static play.data.Form.form;

@org.springframework.stereotype.Controller
public class Pictures extends Controller{

    @Autowired
    PictureDAO pictureDAO;
    @Autowired
    UserDAO userDAO;

    public Result upload() throws IOException {

        // Daten aus der Form holen
        Form<Application.PictureForm> pictureForm = Form.form(Application.PictureForm.class);
        Application.PictureForm pictureData = pictureForm.bindFromRequest().get();
        Http.MultipartFormData body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart file = body.getFile("file");
        InputStream stream = new FileInputStream(file.getFile());

        // User aus session ziehen
        User user = userDAO.findUserByName(session("connected"));

        // Bild erstellen
        Picture picture = new Picture();
        picture.setDescription(pictureData.description);
        picture.setName(pictureData.name);
        picture.setCreator(user);
        picture.setMimeType(file.getContentType());
        picture.setData(getFileContents(stream));
        picture.setPublicVisible(pictureData.isPrivate);

        pictureDAO.createPicture(user, picture);


        // fehlerhaft:
        Logger.info("Picture name: " + picture.getName());
        Logger.info(pictureData.name);
        Logger.info("Public? " + picture.isPublicVisible());
        Logger.info("MimeType? " + picture.getMimeType());
        Logger.info("Data? " + picture.getData());
        Logger.info("Owner? " + picture.getOwner().getName());
        return ok(upload.render(form(Application.PictureForm.class)));
    }


    // Byte-Array aus Inputstream ziehen
    private byte[] getFileContents(InputStream in) throws IOException {
        byte[] bytes = null;
        // write the inputStream to a FileOutputStream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int read = 0;
        bytes = new byte[1024];

        while ((read = in.read(bytes)) != -1) {
            bos.write(bytes, 0, read);
        }
        bytes = bos.toByteArray();
        in.close();
        in = null;
        bos.flush();
        bos.close();
        bos = null;
        return bytes;
    }
}
