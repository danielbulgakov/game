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

    private TargetsController targetsControl;
    private ShootingController shootingControl;
    private InfoController infoController;

    @FXML
    public void initialize() {
        infoController = new InfoController(textTries, textShot);
        shootingControl = new ShootingController(infoController, gamePane,bigTarget,smallTarget);
        targetsControl = new TargetsController(smallTarget, bigTarget);
    }

    public void onGameStart(MouseEvent mouseEvent) {
        isGameRunning = true;
        targetsControl.GameStart();
    }

    public void onPause(MouseEvent mouseEvent) {
        isGameRunning = false;
        targetsControl.Pause();
    }

    public void onExit(MouseEvent mouseEvent) {
        isGameRunning = false;
        infoController.Reset();
        targetsControl.Stop();
    }

    public void onShoot(MouseEvent mouseEvent) {
        if (!isGameRunning) return;
        if (shootingControl.TryShooting()) {
            infoController.incTries();
        }
    }
}
