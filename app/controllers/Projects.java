/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import models.Project;
import models.User;
import models.UserProject;
import play.mvc.Before;

/**
 *
 * @author GooF
 */
public class Projects extends CRUD {
    
    /**
     * This method checks if a user is logged in.
     * It also stores a url to be used later for
     * url forwarding.
     */
    @Before
    static void checkConnected() {
        if (!session.contains("user_id")) {
            // it is stored in the session because otherwise it gets lost
            // during GAE.login()
            session.put("forwardURL", request.action);
            Application.authenticate();
        }
    }

    public static void createProj(String title, String description) {
        User u = User.findById(Long.valueOf(session.get("user_id")));
        System.out.println("title = " + title);
        System.out.println("desc = " + description);
        Project p = new Project(title, description);
        System.out.println("p = " + p);
        p.save();
        UserProject up = new UserProject();
        up.project = p;
        up.user = u;
        up.save();
        Projects.list();
    }

    public static void list() {
       // request.args.put("where", "userProjects.user.id = " + session.get("user_id"));
//        request.args.put("where", "messages is not null");
//        List<Project> projects = Project.find("byUserProjects.user", User.findById(Long.valueOf(1l))).fetch(100);
        //parent();
        list();
    }

}
