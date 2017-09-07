package com.codecool.snake.entities.enemies;

import com.codecool.snake.Game;
import com.codecool.snake.Main;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

import java.util.Random;

// a simple enemy TODO make better ones.
public class CannabisEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = Main.randInt(1,20);
    private int speed;
    private double direction;

    public CannabisEnemy(Pane pane) {
        super(pane);

        setImage(Globals.cannabisEnemy);
        pane.getChildren().add(this);
        this.speed = setRandomSpeed(1,5);
        Random rnd = new Random();
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        direction = rnd.nextDouble() * 360;
        setRotate(direction);
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
            addNewCannabisEnemy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(SnakeHead player) {
        player.changeHealth(-damage);
        destroy();
        for (int i = 0; i < Main.randInt(1,2); i++) {
            addNewCannabisEnemy();
        }
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
