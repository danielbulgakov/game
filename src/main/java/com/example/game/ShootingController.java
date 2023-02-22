package com.example.game;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class ShootingController {

    private final Arrow a;
    private final Circle sml, big;
    private volatile boolean isFlying = false;
    private final InfoController infoController;

    public ShootingController( InfoController infoController,Pane rootPane, Circle small, Circle big) {
        this.infoController = infoController;
        a = new Arrow(25, 250, 100);
        this.big = big;
        sml = small;
        rootPane.getChildren().add(a);
    }

    public boolean TryShooting() {
        if (isFlying) return false;
        isFlying = true;
        Shoot(100);
        return true;
    }

    public int CheckCollision() {

        if (Contains(sml, a.getLayoutX(), a.getLayoutY())) {
            infoController.incShots();
            Reset();
            return 1;
        }
        if (Contains(big, a.getLayoutX(), a.getLayoutY())) {
            infoController.incShots();
            infoController.incShots();
            Reset();
            return 1;
        }

        if (a.getLayoutX() > 475) {
            Reset();
            return -1;
        }

        return 0;
    }

    private void Reset() {
        isFlying = false;
        a.setLayoutX(0);
    }


    private boolean Contains(Circle c, double x, double y) {
        return (Math.sqrt(Math.pow((x + 130 -c.getLayoutX()), 2) + Math.pow((y + 250 -c.getLayoutY()), 2)) < c.getRadius()) ;
    }

    private void Shoot(int ticks) {
        new Thread(
                ()->
                {
                        int moving = 10;
                        while (CheckCollision() == 0 )
                        {
                            a.setLayoutX(a.getLayoutX() + moving);

                            try {
                                Thread.sleep(ticks);
                            } catch (InterruptedException ignored) {
                            }

                        }


                }
        ).start();
    }


}
