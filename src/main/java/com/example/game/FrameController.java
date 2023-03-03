package com.example.game;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class FrameController {
    @FXML
    private Text textTries,textShot;
    @FXML
    private Circle bigTarget, smallTarget;
    @FXML
    private Pane gamePane;
    private boolean isGameRunning = false;

    private TargetsControl targetsControl;
    private ShootingControl shootingControl;
    private InfoControl infoControl;

    @FXML
    public void initialize() {
        infoControl = new InfoControl(textTries, textShot);
        shootingControl = new ShootingControl(infoControl, gamePane,bigTarget,smallTarget);
        targetsControl = new TargetsControl(smallTarget, bigTarget);
    }

    public void onGameStart(MouseEvent mouseEvent) {
        isGameRunning = true;
        targetsControl.GameStart();
    }

    public void onPause(MouseEvent mouseEvent) {
        isGameRunning = !isGameRunning;
        targetsControl.Pause();
        shootingControl.Pause();
    }

    public void onExit(MouseEvent mouseEvent) {
        isGameRunning = false;
        infoControl.Reset();
        targetsControl.Stop();
        shootingControl.Pause();
        shootingControl.Reset();
    }

    public void onShoot(MouseEvent mouseEvent) {
        if (!isGameRunning) return;
        if (shootingControl.TryShooting()) {
            infoControl.incTries();
        }
    }
}
