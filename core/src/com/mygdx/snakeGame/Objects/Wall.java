/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Objects;

import com.mygdx.snakeGame.Objects.FieldObject;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.snakeGame.Field.Field;

/**
 * Несъедобный предмет, который убивает змейку
 * @author Anastasia
 */
public class Wall extends InedibleObject{
    /**
     * Конструктор для яблока
     * @param x координата по x
     * @param y координата по y
     * @param field поле, где находится предмет
     */
    public Wall(int x, int y,Field field) {
        super(x, y,field);
        this.image = new Texture("wall.jpeg");
    }
    /**
     * Выполнить действие над змеей - убить
     */
    @Override
    public void changeSnake() {
        field.getSnake().setIsAlive(false);
    }
    
}
