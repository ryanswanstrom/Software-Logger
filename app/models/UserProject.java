/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import play.data.validation.Required;

/**
 *
 * @author GooF
 */
@Entity
public class UserProject extends SwloggerModel {

    @ManyToOne
    @Required
    public User user;

    @ManyToOne
    @Required
    public Project project;

}
