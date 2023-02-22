package com.example.game;

import javafx.scene.shape.*;
import javafx.util.Pair;

public class Arrow extends Path {
    private static final double defaultArrowLength = 30.0;

    public Arrow(double startX, double startY, double arrowLength){

        strokeWidthProperty().set(3);
        double endX = startX + arrowLength;

        getElements().add(new MoveTo(startX, startY));
        getElements().add(new LineTo(endX, startY));
        getElements().add(new LineTo(endX - arrowLength / 5, startY + arrowLength / 5));
        getElements().add(new MoveTo(endX, startY));
        getElements().add(new LineTo(endX - arrowLength / 5, startY - arrowLength / 5));
        getElements().add(new MoveTo(endX, startY));
        
        
    }

    public Arrow(double startX, double startY){
        this(startX, startY, defaultArrowLength);
    }

}