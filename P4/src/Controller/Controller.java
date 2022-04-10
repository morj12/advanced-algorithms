/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Main.Notifiable;

/**
 *
 * @author ikerg
 */
public class Controller implements Notifiable {

    private Notifiable main;

    public Controller(Notifiable main) {
        this.main = main;
    }

    @Override
    public void notify(String s, Object o) {

    }
}
