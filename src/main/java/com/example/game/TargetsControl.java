package com.example.game;

import javafx.application.Platform;
import javafx.scene.shape.Circle;

public class TargetsControl {
    private final Circle s, b;
    private final double max_y = 500;
    private volatile boolean isRunning = true;
    private volatile boolean isExit = false;
    private boolean isGaming = false;

    public TargetsControl(Circle sml, Circle big) {
        s = sml;
        b = big;
    }

    public void GameStart () {
        if (isGaming) return;
        Reset();
        circleStart(b, 10);
        circleStart(s, 5);
    }

    public void Reset() {
        isGaming = true;
        isRunning = true;
        isExit = false;
    }


    public void Pause()  {
        if (!isRunning) isRunning = true;
        else isRunning = false;
    }

    public void Stop() {
        isExit = true;
        Platform.runLater(()-> {
            s.setLayoutY(250);
            b.setLayoutY(250);
            isGaming = false;
        });

    }



    private void circleStart(Circle c, int tick){
        Thread t = new Thread(
                ()->
                {
                    if (isRunning) {
                    int moving = 5;
                    while (!isExit)
                    {
                        if (!isRunning) continue;
                        double y = c.getLayoutY();
                        double new_y = y + moving;

                        double new_pos;
                        if (new_y > max_y - c.getRadius() || new_y < 0 + c.getRadius() ) {
                            moving = -moving;
                            new_pos = y + moving;
                        } else {
                            new_pos = new_y;
                        }

                        Platform.runLater(()->c.setLayoutY(new_pos));

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
