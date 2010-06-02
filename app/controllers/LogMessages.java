/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import play.mvc.Before;

/**
 *
 * @author GooF
 */
public class LogMessages extends CRUD {

    @Before
    static void checkConnected() {
        Projects.checkConnected();
    }


}
