package com.codecool.snake.entities.snakes;

import com.codecool.snake.GameOver;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;


public class SnakeHead extends GameEntity implements Animatable {

    private static float speed = 2;
    private static final float turnRate = 2;
    private GameEntity tail; // the last element. Needed to know where to add the next part.
    private int health;
    private int countParts = 0;

    public SnakeHead(Pane pane, int xc, int yc) {
        super(pane);
        setX(xc);
        setY(yc);
        health = 100;
        Globals.label.setText("Your health is: "+ health);
        tail = this;
        setImage(Globals.snakeHead);
        pane.getChildren().add(this);

        addPart(4);
    }

    public static void setSpeed(float speed) {
        SnakeHead.speed = speed;
    }

    public void step() {
        double dir = getRotate();
        if (Globals.leftKeyDown) {
            dir = dir - turnRate;
        }
        if (Globals.rightKeyDown) {
            dir = dir + turnRate;
        }
        if (Globals.spaceKeyDown) {
            new SnakeFire(pane, getX(), getY(), dir);
            Globals.spaceKeyDown = false;
        }
        // set rotation and position
        setRotate(dir);
        Point2D heading = Utils.directionToVector(dir, speed);
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        System.out.println("getX: " + getX());
        System.out.println("getY: " + getY());
        System.out.println("Dir :" + dir);

        // check if collided with an enemy or a powerup
        for (GameEntity entity : Globals.getGameObjects()) {
            if (getBoundsInParent().intersects(entity.getBoundsInParent())) {
                if (entity instanceof Interactable) {
                    Interactable interactable = (Interactable) entity;
                    interactable.apply(this);
                    System.out.println(interactable.getMessage());
                }
            }
            System.out.println("Health is :" + health);
        }

        // check for game over condition
        if (isOutOfBounds() || health <= 0) {
            System.out.println("Game Over");
            GameOver.display("GAME OVER", "Your snake is " + countParts + " cm.");
            Globals.gameLoop.stop();
        }
    }

    public void addPart(int numParts) {
        for (int i = 0; i < numParts; i++) {
            SnakeBody newPart = new SnakeBody(pane, tail);
            tail = newPart;
        }
        countParts = countParts + numParts;
    }


    public void changeHealth(int diff) {
        health += diff;
        Globals.label.setText("Your health is: " + health);
    }

    public void changeSpeed(int diff) {
        speed += diff;
    }
}
