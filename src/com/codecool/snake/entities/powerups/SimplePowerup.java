package com.codecool.snake.entities.powerups;

import com.codecool.snake.Main;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeFire;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class SimplePowerup extends GameEntity implements Interactable {

    public SimplePowerup(Pane pane) {
        super(pane);
        setImage(Globals.powerupShell);

        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH-150));
        setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT-150));
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeHealth(3);
        snakeHead.addPart(2);
        destroy();
        SnakeHead.setSpeed(4);
    }

    @Override
    public void fire(SnakeFire snakeFire) {
        destroy();
        new SimplePowerup(pane);

    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
