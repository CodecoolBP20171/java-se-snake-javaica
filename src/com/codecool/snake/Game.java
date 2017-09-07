package com.codecool.snake;

import com.codecool.snake.entities.enemies.OctopusEnemy;
import com.codecool.snake.entities.enemies.JellyfishEnemy;
import com.codecool.snake.entities.enemies.SharkEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.SimplePowerup2;
import com.codecool.snake.entities.powerups.SuperPowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Game extends Pane {

    public Game() {
        new SnakeHead(this, 500, 500);
        new SuperPowerup(this);

        for (int i = 0; i < Main.randInt(1,4); i++) {
            new SharkEnemy(this);
        }

        for (int i = 0; i < Main.randInt(1,4); i++) {
            new JellyfishEnemy(this);
        }

        for (int i = 0; i < Main.randInt(5,10); i++) {
            new SimplePowerup(this);
        }

        for (int i = 0; i < Main.randInt(1,4); i++) {
            new OctopusEnemy(this);
        }

        for (int i = 0; i < Main.randInt(6,9); i++) {
            new SimplePowerup2(this);
        }
    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
                case SPACE: Globals.spaceKeyDown  = false; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
                case SPACE: Globals.spaceKeyDown  = true; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

}
