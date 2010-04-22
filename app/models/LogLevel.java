/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.Entity;
import play.data.validation.Required;

/**
 *
 * @author GooF
 */
@Entity
public class LogLevel extends SwloggerModel {

    @Required
    public Integer value;
    @Required
    public String title;
    public String description;

//    @OneToMany(cascade=CascadeType.ALL)
//    public List<LogMessage> messages;

    public String toString() {
        return this.title + " (" + this.value + ")";
    }
}
