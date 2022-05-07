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

public class Main implements Notifiable {

    private Controller controller;
    private View view;

    private static final String ENCODED_FOLDER = "src/EncodedFiles/";
    private static final String ENCODED_EXTENSION = ".huffman";
    private static final String DECODED_FOLDER = "src/DecodedFiles/";

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
        switch (s) {
            case "generate" -> {
                if (!controller.isExecuted()) {
                    File file = (File) o;
                    controller.startThreadCreateHuffmanTree(file);
                    view.enableButtons(false, false, false, false);
                }
            }
            case "compressed" -> {
                view.setCompressedInfo((HuffmanTree) o);
                view.enableButtons(true, true, true, false);
            }
            case "encode&save" -> {
                if (!controller.isExecuted()) {
                    File sourceFile = (File) o;
                    File destFile = new File(ENCODED_FOLDER + sourceFile.getName() + ENCODED_EXTENSION);
                    view.enableButtons(false, false, false, false);
                    controller.startThreadZipFile(sourceFile, destFile);
                }
            }
            case "decode&save" -> {
                if (!controller.isExecuted()) {
                    File sourceFile = (File) o;
                    String fileName = sourceFile.getName();
                    File destFile = new File(DECODED_FOLDER + fileName.replace(".huffman", ""));
                    view.enableButtons(false, false, false, false);
                    controller.startThreadUnzipFile(sourceFile, destFile);
                }
            }
            case "encoded" -> {
                view.createEncodeOkMessage();
                view.enableButtons(true, true, true, false);
            }
            case "decoded" -> {
                view.createDecodeOkMessage();
                view.enableButtons(true, false, false, true);
            }
            case "progressBarStart" -> view.clearAndPrepareProgress((int) o);
            case "step" -> view.updateProgress((int) o);
        }
    }


}
