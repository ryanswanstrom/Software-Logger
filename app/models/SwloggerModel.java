/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models;

import java.util.Date;
import javax.persistence.Entity;
import play.db.jpa.JPASupport;
import play.db.jpa.Model;

/**
 *
 * @author GooF
 */
@Entity
public class SwloggerModel extends Model {
    public Date dateCreated;
    public Date lastUpdated;

    @Override
    public <T extends JPASupport> T save() {
        if (this.id == null) {
            this.dateCreated = new Date();
        }
        this.lastUpdated = new Date();

        return super.save();
    }
}
