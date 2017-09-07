package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.Main;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.enemies.CannabisEnemy;
import com.codecool.snake.entities.enemies.JamaicaEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.Random;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected Pane pane;

    protected GameEntity(Pane pane) {
        this.pane = pane;
        // add to the main loop.
        Globals.addGameObject(this);
    }

    public void destroy() {
        if (getParent() != null) {
            pane.getChildren().remove(this);
        }
        Globals.removeGameObject(this);
    }

    protected boolean isOutOfBounds() {
        if (getX() > Globals.WINDOW_WIDTH || getX() < 0 ||
                getY() > Globals.WINDOW_HEIGHT || getY() < 0) {
            return true;
        }
        return false;
    }

    protected int setRandomSpeed(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) + 1) + min;
        return randomNumber;
    }

    public void changeDirectionToRandom(double dir, int speed) {
        if (dir > 0) {
            dir -= -180;
            setRotate(dir);
            Point2D newHeading = Utils.directionToVector(dir, speed);
            setX(getX() + newHeading.getX());
            setY(getY() + newHeading.getY());
        } else {
            dir += 180;
            setRotate(dir);
            Point2D newHeading = Utils.directionToVector(dir, speed);
            setX(getX() + newHeading.getX());
            setY(getY() + newHeading.getY());
        }
    }

    public void addNewCannabisEnemy() {
        CannabisEnemy newEnemy = new CannabisEnemy(this.pane);
    }

    public void addNewJamaicaEnemy() {
        JamaicaEnemy newEnemy = new JamaicaEnemy(this.pane);
    }

    public void addNewSimpleEnemy() {
        SimpleEnemy newEnemy = new SimpleEnemy(this.pane);
    }
}
