package com.codecool.snake.entities;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.enemies.OctopusEnemy;
import com.codecool.snake.entities.enemies.JellyfishEnemy;
import com.codecool.snake.entities.enemies.SharkEnemy;
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



    public void addNewOctopusEnemy() {
        OctopusEnemy newEnemy = new OctopusEnemy(this.pane);
    }

    public void addNewJellyfishEnemy() {
        JellyfishEnemy newEnemy = new JellyfishEnemy(this.pane);
    }

    public void addNewSharkEnemy() {
        SharkEnemy newEnemy = new SharkEnemy(this.pane);
    }
}
