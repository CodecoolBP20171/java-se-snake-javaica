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
public class SharkEnemy extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private static final int damage = 10;

    public SharkEnemy(Pane pane) {
        super(pane);

        setImage(Globals.sharkEnemy);
        pane.getChildren().add(this);
        int speed = 1;
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
            Random randomdir = new Random();
            double dir = 10;
            if (getX() > Globals.WINDOW_WIDTH){
                dir = randomdir.nextInt((360-180)+1) + 180;
            }
            else if  (getX() < 0) {
                 dir = randomdir.nextInt((90 -0) +1 );
            }

            else if (getY() > Globals.WINDOW_HEIGHT) {
                dir = randomdir.nextInt((90 -0) +1 );
            }

            else if (getY() < 0) {
                dir = randomdir.nextInt((270 -90) +1 ) + 90;
            }
            setRotate(dir);
            heading = Utils.directionToVector(dir, 2);
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
            addNewSharkEnemy();
        }
    }

    @Override
    public void fire(SnakeFire snakeFire) {
        destroy();
        new SharkEnemy(pane);
    }

    @Override
    public String getMessage() {
        return "10 damage";
    }
}
