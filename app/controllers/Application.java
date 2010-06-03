package controllers;

import java.util.Date;
import models.LogLevel;
import models.LogMessage;
import models.Project;
import models.User;
import play.libs.OpenID;
import play.libs.OpenID.UserInfo;
import play.mvc.Controller;
import play.mvc.Http.Request;

public class Application extends Controller {

    public static void index() {
        if (session.contains("forwardURL")) {
            String redirectPage = session.get("forwardURL");
            session.remove("forwardURL");
            redirect(redirectPage);
        }
        render();
    }

    public static void about() {
        render();
    }

    public static void features() {
        render();
    }

    public static void logout() {
        session.clear();
        index();
    }

    public static void authenticate() {
        if (OpenID.isAuthenticationResponse()) {
            // Retrieve the verified id
            UserInfo userInfo = OpenID.getVerifiedID();
            if (userInfo == null) {
                flash.put("error", "Oops. Authentication has failed");
                index();
            } else {
                System.out.println("Adding session for userid: "+ userInfo.id + ", " + userInfo.extensions.get("email"));
                final String email = userInfo.extensions.get("email");
                User user = User.register(userInfo.id, email);
                session.put("user_id", user.id);
                //session.put("user.email", user.extensions.get("email"));
                index();
            }
        } else {
            // Verify the id
            String act = session.get("forwardURL");
            System.out.println("going to yahoo: " + act);
            System.out.println("going to yahoo Request: " + Request.current().getBase());
            if (!OpenID.id("https://me.yahoo.com").required("email",
                    "http://axschema.org/contact/email").verify()) {
                flash.put("error", "Oops. Cannot contact yahoo");
                System.out.println("cannot contact yahoo");
                index();
            }
        }
    }


    public static void addMore(String message) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Project p = new Project();
            p.title = "Project " + i;
            p.description = "What a great new project";
            p.save();
            for (int j = 0; j < 100; j++) {
                LogMessage lm = new LogMessage();
                lm.project = p;
                lm.level = LogLevel.findById(Long.valueOf(j % 5 + 1));
                lm.message = "A very long message for project: " + p.toString() + ": " + message;
                lm.messageTime = new Date();
                lm.title = "Message " + i + ", " + j;
                lm.save();
            }

        }
        long end = System.currentTimeMillis();
        long total = end - start;
        System.out.println("time for 1000 adds to DB is " + total);
        // Add WS call to enter time info
        Application.index();
    }
    
}
