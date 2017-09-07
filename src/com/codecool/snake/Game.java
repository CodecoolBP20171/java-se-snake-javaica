package com.codecool.snake;

import com.codecool.snake.entities.enemies.CannabisEnemy;
import com.codecool.snake.entities.enemies.JamaicaEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerup;
import com.codecool.snake.entities.powerups.SimplePowerup2;
import com.codecool.snake.entities.powerups.SuperPowerup;
import com.codecool.snake.entities.snakes.SnakeHead;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.Random;

public class Game extends Pane {

    public Game() {
        new SnakeHead(this, 500, 500);

        for (int i = 0; i < Main.randInt(1,4); i++) {
            new SimpleEnemy(this);
        }


        for (int i = 0; i < Main.randInt(1,4); i++) {
            new JamaicaEnemy(this);
        }


        for (int i = 0; i < Main.randInt(1,4); i++) {
            new SimplePowerup(this);
        }


        for (int i = 0; i < Main.randInt(1,4); i++) {
            new CannabisEnemy(this);
        }

    }

    public void start() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = true; break;
                case RIGHT: Globals.rightKeyDown  = true; break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case LEFT:  Globals.leftKeyDown  = false; break;
                case RIGHT: Globals.rightKeyDown  = false; break;
            }
        });
        Globals.gameLoop = new GameLoop();
        Globals.gameLoop.start();
    }

    public static int randInt(int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt((max - min) + 1) + min;
        return randomNumber;
    }
}
