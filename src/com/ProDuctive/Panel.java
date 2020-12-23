package com.ProDuctive;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Panel extends JPanel implements KeyListener, ActionListener {

// Variables:::

    //Main
    Timer timer;
    Random random;
    boolean playingGame = true;
    //Colors
    Color bgColor = Color.black;
    Color snkStroke = Color.white;
    Color snkHead = Color.CYAN;
    Color food = Color.red;

    // multicolor snkake body;

    Color red = Color.red;
    Color blue = Color.BLUE;
    Color orange = Color.ORANGE;
    Color green = Color.GREEN;
    Color pink = Color.PINK;
    Color yello = Color.yellow;
    //Snake Movement
    int snkLength = 1;
    char moveDir;
    int snkVelocity = new Frame(5).snkVelocity;
    //Window stuff
    int scrX = new Frame(5).ScreenDimension.width;
    int scrY = new Frame(5).ScreenDimension.height;
    int unitSize = new Frame(5).UnitSize;
    int delay = 1000 / snkVelocity;
    //Snake Coordinates
    ArrayList<DimCol> snkDimensions = new ArrayList<>();


    int snkX = unitSize;
    int snkY = unitSize;
    int appX;
    int appY;


    //Constructor
    Panel() {

        setBackground(bgColor);
        setPreferredSize(new Frame(5).ScreenDimension);
        random = new Random();
        timer = new Timer(delay, this);
        timer.start();

        //Enabling the detection of keyboard activity
        addKeyListener(this);
        setFocusTraversalKeysEnabled(true);
        setFocusable(true);

        // Setting the random position for the food
        newApple();

        // Residing the default position of the snake


    }

    //Paint
    public void paint(Graphics g) {
        super.paint(g);

        // If the game is over then printing the game over text
        if (!playingGame) {
            gameOver(g);
        }

        // Painting the body of the snake
        for (DimCol dd : snkDimensions) {
            Dimension d = dd.d;
            //painting the body of the snake
            if (snkDimensions.indexOf(dd) != snkDimensions.size() - 1) {

                g.setColor(snkStroke);
                g.fillOval(d.width, d.height, unitSize, unitSize);
                g.setColor(dd.c);
                g.fillOval(d.width + 4, d.height + 4, unitSize - 8, unitSize - 8);


            }
            //painting the head and other parts of mouth of the snake with the different color
            else {
                //filling the background of the snake head
                g.setColor(snkHead);
                g.fillOval(d.width, d.height, unitSize, unitSize);
                //making eyes
                g.setColor(bgColor);
                g.fillOval(d.width + (unitSize / 6), d.height + (unitSize / 6), unitSize / 4, unitSize / 4);//left eye
                //initializing variables for right eye
                int x = d.width + (unitSize / 6) + (unitSize / 2);
                int y = d.height + (unitSize / 6);
                g.fillOval(x, y, unitSize / 4, unitSize / 4); //right eye
                //making mouth
                g.setColor(Color.red);
                g.fillOval(d.width + (unitSize / 6), d.height + (unitSize / 2), unitSize - (unitSize / 3), (unitSize / (2)) - (unitSize / 5));

            }

        }

        // Painting the Plotted snakes food
        g.setColor(food);
        g.fillOval(appX, appY, unitSize, unitSize);

    }

    //Moving the snake by the provided logics
    public void move() {

        // Moving the snake while not the game is over


        //moving the snake by adding snkX and snkY

        //getting the color of the back block
        Color tem;
        tem = rndColor();
        snkDimensions.add(new DimCol((new Dimension(snkX, snkY)), tem));

        //Removing the back part of the snake if it is greater than snake length
        if (snkDimensions.size() > snkLength) {
            snkDimensions.remove(0);
        }


        //moving constantly
        switch (moveDir) {
            case 'r' -> snkX += unitSize;
            case 'l' -> snkX -= unitSize;
            case 'u' -> snkY -= unitSize;
            case 'd' -> snkY += unitSize;
        }

        // Returning the snake to board if getting out of board if boundary collision is disabled
        if(new Frame(5).mode==0){
            if(snkX>scrX-unitSize){
                snkX=0;
            }
            if(snkY>scrY){
                snkY=0;
            }
            if(snkX<0){
                snkX=scrX-unitSize;
            }
            if(snkY<0){
                snkY=scrY-unitSize;
            }
        }


    }

    //Checking the collision of snake head with snake body
    public boolean checkBodyCollision() {
        for (DimCol dd : snkDimensions) {
            Dimension d = dd.d;
            if (d != (snkDimensions.get(snkDimensions.size() - 1)).d) {
                if (d.width == snkDimensions.get(snkDimensions.size() - 1).d.width && d.height == snkDimensions.get(snkDimensions.size() - 1).d.height) {
                    return true;
                }
            }
        }
        return false;
    }

    //Checking the boundary collision
    public boolean checkBoundary() {
        return snkDimensions.get(snkDimensions.size() - 1).d.width < 0 || snkDimensions.get(snkDimensions.size() - 1).d.width > scrX - unitSize || snkDimensions.get(snkDimensions.size() - 1).d.height < 0 || snkDimensions.get(snkDimensions.size() - 1).d.height > scrY - unitSize;
    }

    //Plotting the new apple
    public void newApple() {

        appX = random.nextInt((scrX / unitSize) - 1) * unitSize;
        appY = random.nextInt((scrY / unitSize) - 1) * unitSize;

        //checking if the plotted apple is in the snake body
        for (DimCol dd : snkDimensions) {
            Dimension d = dd.d;
            if (appX == d.width && appY == d.height) {
                newApple();
            }
        }
    }

    //checking for the apple and returning if true
    public boolean checkApple() {
        return (snkDimensions.get(snkDimensions.size() - 1).d.width == appX && snkDimensions.get(snkDimensions.size() - 1).d.height == appY);
    }

    //Returning random objects of Color class for multicolor snake body
    public Color rndColor() {
        if (new Frame(5).bodyColor == null) {
            switch (random.nextInt(6)) {
                case 0:
                    return red;
                case 1:
                    return orange;
                case 2:
                    return blue;
                case 3:
                    return green;
                case 4:
                    return pink;
                case 5:
                    return yello;
            }

        }
        return new Frame(5).bodyColor;
    }

    // making a lighting effect by changing the colors every frame
    public ArrayList<DimCol> chCol(ArrayList<DimCol> k){
        ArrayList<DimCol> m = new ArrayList<>();
        for (DimCol d:k){
            m.add(new DimCol(d.d,rndColor()));
        }
        return m;
    }

    //paint method after game over
    public void gameOver(Graphics g) {
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 200));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over", (scrX - metrics.stringWidth("Game Over")) / 2, scrY / 2);
    }

    //Input control
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==KeyEvent.VK_R){

            dispatchEvent(new WindowEvent(new Frame(), WindowEvent.WINDOW_CLOSING));
        }

        //changing the directions
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                if (moveDir != 'l') {
                    moveDir = 'r';
                }
                break;
            case KeyEvent.VK_LEFT:
                if (moveDir != 'r') {
                    moveDir = 'l';
                }
                break;
            case KeyEvent.VK_UP:
                if (moveDir != 'd') {
                    moveDir = 'u';
                }
                break;
            case KeyEvent.VK_DOWN:
                if (moveDir != 'u') {
                    moveDir = 'd';
                }
                break;
        }


    }

    //Main Control
    public void actionPerformed(ActionEvent e) {

        // moving if game is not over yet and stopping the movement if game is over
        if (playingGame) {
            move();
        }

        // checking the collision of apple with the head of the snake and increase the length of the snake
        if (checkApple()) {
            snkLength++;
            newApple();

        }

        // changing the boundary collison strategy
        if(new Frame(5).mode==1){
            if (checkBodyCollision() || checkBoundary()) {
                playingGame = false;
            }
        }
        if (checkBodyCollision() ) {
            playingGame = false;
        }

        // Recoloring all colors of the body parts of the snake if color variable in Frame.java class is set to null.
        if(new Frame(7).bodyColor==null){
            snkDimensions=chCol(snkDimensions);
        }

        repaint();

    }


    //scrap
    //scrap
    public void keyTyped(KeyEvent e) {

    }

    public void keyReleased(KeyEvent e) {

    }
}
