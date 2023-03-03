package com.example.game;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class ShootingControl {

    private final Arrow a;
    private final Circle sml, big;
    private volatile boolean isFlying = false;
    private final InfoControl infoControl;
    private volatile boolean isRunning = true;

    public ShootingControl(InfoControl infoControl, Pane rootPane, Circle small, Circle big) {
        this.infoControl = infoControl;
        a = new Arrow(25, 250, 100);
        this.big = big;
        sml = small;
        rootPane.getChildren().add(a);
    }

    public boolean TryShooting() {
        if (isFlying) return false;
        isFlying = true;
        Shoot(10);
        return true;
    }

    public void Pause()  {
        if (!isRunning) isRunning = true;
        else isRunning = false;
    }

    public int CheckCollision() {

        if (Contains(sml, a.getLayoutX(), a.getLayoutY())) {
            infoControl.incShots();
            Reset();
            return 1;
        }
        if (Contains(big, a.getLayoutX(), a.getLayoutY())) {
            infoControl.incShots();
            infoControl.incShots();
            Reset();
            return 1;
        }

        if (a.getLayoutX() > 475) {
            Reset();
            return -1;
        }

        return 0;
    }

    public void Reset() {
        Platform.runLater(()-> {
            isFlying = false;
            isRunning = true;
            a.setLayoutX(0);
        });

    }




    private void Shoot(int ticks) {
        new Thread(
                ()->
                {


                        int moving = 10;
                        while (CheckCollision() == 0 )
                        {

                            if (!isRunning)
                                continue;
                            Platform.runLater(()-> {
                                a.setLayoutX(a.getLayoutX() + moving);
                            });

                            try {
                                Thread.sleep(ticks);
                            } catch (InterruptedException ignored) {
                            }

                        }



                }
        ).start();
    }

    private boolean Contains(Circle c, double x, double y) {
        return (Math.sqrt(Math.pow((x + 130 -c.getLayoutX()), 2) + Math.pow((y + 250 -c.getLayoutY()), 2)) < c.getRadius()) ;
    }

}



