package com.ProDuctive;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    // Variables
    Dimension ScreenDimension = new Dimension(700,700);

    // best between 30 - 60;
    int UnitSize = 60;

    // best around half of the unit size variable
    int snkVelocity = 15;

    // color of the body
    Color bodyColor = null;




    Frame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Snake");
        setResizable(false);
        setSize(ScreenDimension);

        this.add(new Panel());

        setVisible(true);
    }

    //just created to get the global variables of this class in the Panel class
    Frame(int rndm){

    }

}
