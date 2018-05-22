/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Objects;

import com.mygdx.snakeGame.Field.Field;

/**
 * Съедобный объект
 * @author Anastasia
 */
public abstract class EdibleObject extends FieldObject {
    /**
     * Кнструктор съедобного объекта
     * @param x координата по х
     * @param y координата по у
     * @param field само поле
     */
    public EdibleObject(int x, int y, Field field) {
        super(x, y, field);
    }
}
