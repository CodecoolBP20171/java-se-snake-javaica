package com.codecool.snake.entities.enemies;

import com.codecool.snake.Main;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeFire;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;
import java.util.Random;

import static com.codecool.snake.Globals.snakeHead;

// a simple enemy TODO make better ones.
public class OctopusEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = Main.randInt(1,20);

    public OctopusEnemy(Pane pane) {
        super(pane);

        setImage(Globals.octopusEnemy);
        pane.getChildren().add(this);

        int speed = setRandomSpeed(1,5);
      
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);


        double direction = rnd.nextDouble() * 360;

        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
            addNewOctopusEnemy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
        player.setImage(snakeHead);
        for (int i = 0; i < Main.randInt(1,2); i++) {
            addNewOctopusEnemy();
        }
    }

    @Override
    public void fire(SnakeFire snakeFire) {
        destroy();
        new OctopusEnemy(pane);
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
