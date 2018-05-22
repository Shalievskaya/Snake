/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.snakeGame.Field.Field;

/**
 * Съедобный предмет, который ускоряет змейку
 * @author Anastasia
 */
public class Orange extends EdibleObject{
    private boolean visible;
    /**
     * Конструктор для апельсина
     * @param x координата по x
     * @param y координата по y
     * @param field поле, где находится предмет
     */
    public Orange(int x, int y,Field field) {
        super(x, y,field);
        this.image = new Texture("orange.png");
        visible = true;
    }
    /**
     * Выполнить действие над змеей - ускорение
     */
    @Override
    public void changeSnake() {
        if (visible)
            field.getSnake().setSpeed((field.getSnake().getSpeed())+1); // увеличили скорость на 1
    }
    /**
     * Установить значение полю видимость
     */
    public void setVisible (boolean v)
    {
        this.visible=v;
    }
    /**
     * Получить значение поля видимость
     */
    public boolean getVisible (){
        return this.visible;
    }
}
