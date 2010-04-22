/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.data.validation.MaxSize;
import play.data.validation.Required;
import play.data.validation.URL;

/**
 *
 * @author GooF
 */
@Entity
public class UserOpenId extends SwloggerModel {

    @Required
    @URL
    @MaxSize(2000)
    String openIdUrl;
    
    @Required
    @ManyToOne
    public User user;

    public UserOpenId(User user, String openIdUrl) {
        this.openIdUrl = openIdUrl;
        this.user = user;
    }
}
