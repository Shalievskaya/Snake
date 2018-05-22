/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.snakeGame.Field.Field;

/**
 * Съедобный предмет, который увеличивает длину змейки
 * @author Anastasia
 */
public class Strawberry extends EdibleObject{
    /**
     * Конструктор для яблока
     * @param x координата по x
     * @param y координата по y
     * @param field поле, где находится предмет
     */
    public Strawberry(int x, int y,Field field) {
        super(x, y,field);
        this.image = new Texture("strawberry.png");
    }
    /**
     * Выполнить действие над змеей - увеличть длину
     */
    @Override
    public void changeSnake() {
        field.getSnake().growTheTail();
    }
    
}
