package com.example.game;

import javafx.scene.shape.Circle;

public class TargetsController {
    private final Circle s, b;
    private final double max_y = 500;
    private volatile boolean running = true;
    private volatile boolean exit = false;
    private boolean gaming = false;

    public TargetsController(Circle sml, Circle big) {
        s = sml;
        b = big;
    }

    public void GameStart () {
        if (gaming) return;
        Reset();
        circleStart(b, 100);
        circleStart(s, 50);
    }

    private void Reset() {
        gaming = true;
        running = true;
        exit = false;
    }


    public void Pause()  {
        if (!running) running = true;
        else running = false;
    }

    public void Stop() {
        exit = true;
        s.setLayoutY(250);
        b.setLayoutY(250);
        gaming = false;
    }



    private void circleStart(Circle c, int tick){
        Thread t = new Thread(
                ()->
                {
                    if (running) {
                    int moving = 5;
                    while (!exit)
                    {
                        if (!running) continue;
                        double y = c.getLayoutY();
                        double new_y = y + moving;

                        if (new_y > max_y - c.getRadius() || new_y < 0 + c.getRadius() ) {
                            moving = -moving;
                            c.setLayoutY(y + moving);
                        } else {
                            c.setLayoutY(new_y);
                        }

                        try {
                            Thread.sleep(tick);
                        } catch (InterruptedException ignored) {
                        }

                    }

                    }
                }
        );
        t.start();

    }
}
