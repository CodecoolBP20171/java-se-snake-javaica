package com.codecool.snake;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOver {

    public static void display(String title, String health) {
        Stage window = new Stage();
        window.setWidth(300);
        window.setHeight(200);
        window.setTitle(title);
        //window.toBack();

        //window.initModality(Modality.APPLICATION_MODAL);

        Label text = new Label(health);
        Button closeButton = new Button("OK");
        closeButton.setOnMouseClicked(e -> System.exit(0));

        VBox layout = new VBox(75);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(text, closeButton);

        window.setScene(new Scene(layout));

        window.show();
    }
}
