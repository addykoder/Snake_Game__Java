package com.ProDuctive;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class Frame extends JFrame  {
    // Variables
    Dimension ScreenDimension = new Dimension(600, 600);

    // must be a factor of the screen width and height otherwise it may lead to bugs
    int UnitSize = 50;

    // best around half of the unit size variable
    int snkVelocity = 15;

    // color of the body || multicolor if set to null
    Color bodyColor =null;

    // Game mode 0- For boundary collision disabled. 1- for boundary collision enabled.
    int mode=0;


    Frame() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake");
        setResizable(false);
        setSize(ScreenDimension);
        this.add(new Panel());

        setVisible(true);


    }

    //just created to get the global variables of this class in the Panel class
    Frame(int rndm) {
    }




}
