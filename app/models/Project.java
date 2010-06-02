/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.data.validation.Required;

/**
 *
 * @author GooF
 */
@Entity
public class Project extends SwloggerModel {

    @Required
    public String title;
    public String description;
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    public List<LogMessage> messages;

    @OneToMany(mappedBy="project",cascade=CascadeType.ALL)
    public List<UserProject> userProjects;

    public Project() {
        this("Default", "No Description");
    }

    public Project(String title, String desc) {
        this.userProjects = new ArrayList<UserProject>();
        this.title = title;
        this.description = desc;
    }

    @Override
    public String toString() {
        return this.title;
    }
}
