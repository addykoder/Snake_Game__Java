package com.ProDuctive;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class Frame extends JFrame implements KeyListener {
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
        addKeyListener(this);
        this.add(new Panel());

        setVisible(true);


    }

    //just created to get the global variables of this class in the Panel class
    Frame(int rndm) {
    }



    // closing and reopening the window if pressed the retry key
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_R){
            new Frame();
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
