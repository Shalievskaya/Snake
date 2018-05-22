/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.snakeGame.Field.Field;

/**
 * Съедобный предмет, который замедляет змейку
 * @author Anastasia
 */
public class Apple extends EdibleObject{

    /**
     * Конструктор для яблока
     * @param x координата по x
     * @param y координата по y
     * @param field поле, где находится предмет
     */
    public Apple(int x, int y,Field field) {
        super(x, y,field);
        this.image = new Texture("apple.png");
    }
    /**
     * Выполнить действие над змеей - замедление
     */
    @Override
    public void changeSnake() {
        if(field.getSnake().getSpeed()>1)
            field.getSnake().setSpeed((field.getSnake().getSpeed())-1); // уменьшили скорость на 1
    }
}
