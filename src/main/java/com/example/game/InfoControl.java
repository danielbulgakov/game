package com.example.game;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class InfoControl {
    @FXML
    private final Text textTries, textShots;


    public InfoControl(Text textTries, Text textShots) {
        this.textTries = textTries;
        this.textShots = textShots;
    }

    public void incTries() {
        int val = Integer.parseInt(textTries.getText());
        val++;
        textTries.setText(Integer.toString(val));
    }

    public void incShots() {
        int val = Integer.parseInt(textShots.getText());
        val++;
        textShots.setText(Integer.toString(val));
    }

    public void Reset() {
        textShots.textProperty().set("0");
        textTries.textProperty().set("0");
    }
}
