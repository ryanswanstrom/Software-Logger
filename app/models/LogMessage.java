/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import play.data.validation.MaxSize;
import play.data.validation.Required;

/**
 *
 * @author GooF
 */
@Entity
public class LogMessage extends SwloggerModel {

    @Required
    public String title;
    @Required
    @Lob
    @MaxSize(Integer.MAX_VALUE - 1) // - 1 just for fun
    public String message;
    @Required
    public Date messageTime = new Date();
    @Required
    @ManyToOne
    public LogLevel level;
    @Required
    @ManyToOne
    public Project project;
    
    @Override
    public String toString() {
        return this.title;
    }
}
