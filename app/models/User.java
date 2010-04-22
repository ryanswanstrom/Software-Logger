/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import play.data.validation.Email;
import play.data.validation.Required;
import play.data.validation.URL;

/**
 *
 * @author GooF
 */
@Entity
public class User extends SwloggerModel {

    public String guid;
    public String name;
    @Email
    public String email;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<UserOpenId> openIds;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<UserProject> userProjects;

    public User() {
        this(null, null);
    }

    public User(String name, String email) {
        this.openIds = new ArrayList<UserOpenId>();
        this.userProjects = new ArrayList<UserProject>();
        this.name = name;
        this.email = email;
        this.guid = UUID.randomUUID().toString();
    }

    public void addOpenId(@Required @URL String openIdUrl) {
        //TODO: later on call validateAndSave() with Play 1.1
        UserOpenId openId = new UserOpenId(this, openIdUrl).save();
        this.openIds.add(openId);
    }

    /*
     * This method will create a new user if one does not exist or
     * return the current user associated with the openID URL.
     */
    public static User register(String openIdUrl, String email) {
        List<UserOpenId> openIds = UserOpenId.find("byOpenIdUrl", openIdUrl).fetch();
        User user;
        if (openIds.size() > 1) {
            // what to do if more than one openID
            LogMessage mess = new LogMessage();
            mess.title = "Too many open IDs in the DB";
            mess.message = "The open ID [" + openIdUrl + "] has more than one"
                    + "entry in the database.";
            mess.level = LogLevel.find("byValue", 200).first();
            mess.project = Project.find("byTitle", "swlogger").first();
            mess.save();
            user = openIds.get(0).user;
        } else if (openIds.size() == 1) {
            // return the user with the openid
            UserOpenId openId = openIds.get(0);
            //TODO: what if more than one user are returned
            user = openId.user;
        } else {
            // register a new user
            user = new User(null, email).save();
            user.addOpenId(openIdUrl);
        }

        return user;
    }

    public String toString() {
        return (this.email != null) ? this.email : "";
    }
}
