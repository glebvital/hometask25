package org.example;

import java.awt.*;
import java.util.Random;

public class Ball {
    private int x,y, xStep, yStep, size;
    private Color color;

    public Ball(int x, int y, int xStep, int yStep, Color color, int size) {
        this.x = x;
        this.y = y;
        this.xStep = xStep;
        this.yStep = yStep;
        this.color = color;
        this.size = size;
        new Move().start();
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getxStep() {
        return xStep;
    }

    public void setxStep(int xStep) {
        this.xStep = xStep;
    }

    public int getyStep() {
        return yStep;
    }

    public void setyStep(int yStep) {
        this.yStep = yStep;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public class Move extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    x += xStep;
                    y += yStep;
                    Thread.sleep(1000/40);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
