/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.Controller;
import Model.HuffmanTree;
import View.View;

import javax.swing.*;
import java.io.File;

/**
 * @author ikerg
 */
public class Main implements Notifiable {

    private Controller controller;
    private View view;

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        controller = new Controller(this);
        view = new View(this);
        view.showGui();
    }

    @Override
    public void notify(String s, Object o) {
        if (s.equals("generate")) {
            if (!controller.isExecuted()) {
                File file = (File) o;
                controller.start(file);
            }
        } else if (s.equals("compressedSize")) {
            view.setCompressedInfo((HuffmanTree) o);
        }
    }
}
