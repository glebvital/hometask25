package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;


public class Game extends JFrame implements MouseListener {
    private ArrayList<Ball> balls = new ArrayList<>();
    private JLabel totalBalls = new JLabel();
    private int ballCount = 0;

    public Game() {
        setTitle("Bouncy balls");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setResizable(false);
        setLayout(null);
        totalBalls.setBounds(600,10,150,50);
        totalBalls.setText("Balls spawned: "+ballCount);
        add(totalBalls);
        setVisible(true);
        addMouseListener(this);
        new GameThread().start();
    }


    @Override
    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        wall();
        collision();
        balls.forEach(ball -> ball.draw(g));
    }

    private void wall() {
        int x = 0, y = 0, width = getWidth(), height = getHeight();
        balls.forEach(ball -> {
            if (ball.getX() < x || ball.getX() > width - 20) {
                ball.setxStep(-ball.getxStep());
            }
            if (ball.getY() < y || ball.getY() > height - 20) {
                ball.setyStep(-ball.getyStep());
            }
        });
    }

    private void collision(){
        for (int i = 0; i < balls.size(); i++) {
            for (int j = i+1; j < balls.size(); j++) {
                int a = Math.abs(balls.get(i).getX()-balls.get(j).getX());
                int b = Math.abs(balls.get(i).getY()-balls.get(j).getY());
                int c = (int) Math.sqrt(a*a + b*b);
                if (c <= balls.get(i).getSize()+balls.get(j).getSize()){
                    balls.get(i).setxStep(-balls.get(i).getxStep());
                    balls.get(i).setyStep(-balls.get(i).getyStep());
                    balls.get(j).setxStep(-balls.get(j).getxStep());
                    balls.get(j).setyStep(-balls.get(j).getyStep());
                    //мячи побольше "эдят" мячи поменше и в итоге ростут в розмере
                    if (balls.get(i).getSize()>balls.get(j).getSize()&&balls.get(i).getSize()!=50){
                        balls.remove(j);
                        balls.get(i).setSize(balls.get(i).getSize()+10);
                    }
                }
            }
        }
    }

    public void stats(){
        //отображает количество созданых мячов за всё время игры
        ballCount++;
        totalBalls.setText("Balls spawned: "+ballCount);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        stats();
        int x = e.getX();
        int y = e.getY();
        Random random = new Random();
        int xStep = random.nextInt(20) - 10;
        int yStep = random.nextInt(20) - 10;
        Color color = new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
        int size = random.nextInt(20)+10;
        Ball ball = new Ball(x, y, xStep, yStep, color, size);
        balls.add(ball);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    class GameThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(1000/40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
