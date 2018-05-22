/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.modules;

import com.mygdx.modules.Module;
import com.mygdx.snakeGame.Objects.EdibleObject;
import com.mygdx.snakeGame.game.Game;
import java.util.Random;

/**
 *
 * @author Anastasia
 */
public class ModeleGame implements Module {

    Game gm;

    @Override
    public void load(Game gm, Module batch) {
        this.gm = gm;
        this.gm.setModule(batch);
    }

    @Override
    public void unload() {
        System.out.println("unload");
    }

    @Override
    public int run() {
        int dir = gm.field.LEFT;
        int curDir = gm.field.getSnake().direction;
        for (EdibleObject f : gm.getFood()) {
            if (Math.abs(f.getPosition().x - gm.getSnakePosition().x) < 23) {
                if ((f.getPosition().y - gm.getSnakePosition().y > 0) && gm.checkNavigate(gm.field.UP) && curDir!=gm.field.DOWN) {
                    dir = gm.field.UP;
                } else if ((f.getPosition().y - gm.getSnakePosition().y) < 0 && gm.checkNavigate(gm.field.DOWN) && curDir!=gm.field.UP ) {
                    dir = gm.field.DOWN;
                }
                break;
            } else if (Math.abs(f.getPosition().y - gm.getSnakePosition().y) < 23) {
                if ((f.getPosition().x - gm.getSnakePosition().x) > 0 && gm.checkNavigate(gm.field.RIGHT) && curDir!=gm.field.LEFT) {
                    dir = gm.field.RIGHT;
                } else if ((f.getPosition().x - gm.getSnakePosition().x) < 0 && gm.checkNavigate(gm.field.LEFT) && curDir!=gm.field.RIGHT) {
                    dir = gm.field.LEFT;
                }
                break;
            } else if (f == gm.getFood().get(2)) {
                dir = gm.field.RIGHT;
                break;
            } else if (f == gm.getFood().get(1)) {
                dir = gm.field.UP;
                break;
            }
        }
        if (gm.checkNavigate(dir)) {
            gm.setNavigate(dir);
        } else {
            dir = changeDir(dir);
            if (gm.checkNavigate(dir)) {
                gm.setNavigate(dir);
            } else {
                dir = changeDir(dir);
                if (gm.checkNavigate(dir)) {
                    gm.setNavigate(dir);
                } else {
                    dir = changeDir(dir);
                    if (gm.checkNavigate(dir)) {
                        gm.setNavigate(dir);
                    }
                }
            }
        }
        return 0;
    }

    private int changeDir(int dir) {
        if (dir < 3) {
            return dir + 1;
        } else {
            return 0;
        }
    }

    private boolean checkDir(int i) {
        if (i == gm.field.DOWN && gm.field.getSnake().direction == gm.field.UP || i == gm.field.UP && gm.field.getSnake().direction == gm.field.DOWN || i == gm.field.RIGHT && gm.field.getSnake().direction == gm.field.LEFT || i == gm.field.LEFT && gm.field.getSnake().direction == gm.field.RIGHT) {
            return false;
        }
        return true;
    }
}
