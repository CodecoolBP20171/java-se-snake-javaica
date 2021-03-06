package com.codecool.snake.entities.powerups;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeFire;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple powerup that makes the snake grow TODO make other powerups
public class SimplePowerup2 extends GameEntity implements Interactable {

    public SimplePowerup2(Pane pane) {
        super(pane);
        setImage(Globals.powerupStarFish);
        pane.getChildren().add(this);

        Random rnd = new Random();
        setX(rnd.nextDouble() * (Globals.WINDOW_WIDTH-50));
        setY(rnd.nextDouble() * (Globals.WINDOW_HEIGHT-50));
    }

    @Override
    public void apply(SnakeHead snakeHead) {
        snakeHead.changeHealth(2);
        snakeHead.addPart(4);
        snakeHead.changeSpeed(1);
        destroy();
    }

    @Override
    public void fire(SnakeFire snakeFire) {
        destroy();
        new SimplePowerup2(pane);
    }

    @Override
    public String getMessage() {
        return "Got power-up :)";
    }
}
