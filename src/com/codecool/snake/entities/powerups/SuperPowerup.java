package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeBody;
import com.codecool.snake.entities.snakes.SnakeHead;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

// a simple powerup that makes the snake grow TODO make other powerups
public class SuperPowerup extends GameEntity implements Interactable {

    public SuperPowerup(Pane pane) {
        super(pane);

        setImage(Globals.superPowerupShell);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH-100));
        setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT-100));

    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeHealth(20);
        snakeHead.getRotate();
        snakeHead.changeSpeed(-1);
        //SnakeBody.setHistorySize(2);
        destroy();
        snakeHead.setImage(Globals.happy);
        for (int i =0; i < ThreadLocalRandom.current().nextInt(1,3); i++){
            new SimplePowerup(pane);
        }
        for (int i =0; i < ThreadLocalRandom.current().nextInt(2,4); i++){
            new SimplePowerup2(pane);
        }
        new SuperPowerup(pane);
    }


    @Override
    public String getMessage() {
        return "Got Super power-up :)";
    }
}
