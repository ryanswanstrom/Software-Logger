/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import models.Project;
import models.User;
import models.UserProject;

/**
 *
 * @author GooF
 */
public class Projects extends CRUD {

    public static void createProj(String title, String description) {
        User u = User.findById(Long.valueOf(session.get("user_id")));
        System.out.println("hello");
        Project p = new Project(title, description).save();
        UserProject up = new UserProject();
        up.project = p;
        up.user = u;
        up.save();
        Projects.list();
    }

    public static void list() {
        request.args.put("where", "userProjects.user.id = " + session.get("user_id"));
        parent();
    }

}
