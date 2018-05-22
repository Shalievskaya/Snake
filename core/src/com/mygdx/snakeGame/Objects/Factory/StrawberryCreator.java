/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Objects.Factory;

import com.mygdx.snakeGame.Field.Field;
import com.mygdx.snakeGame.Objects.FieldObject;
import com.mygdx.snakeGame.Objects.Strawberry;

/**
 * Фабрика для создания клубники на поле
 *
 * @author Anastasia
 */
public class StrawberryCreator extends ObjectCreator {
    /**
     * Создание клубники
     * @param x координата по x
     * @param y координата по y
     * @param field поле, где находится предмет
     * @return созданная клубника
     */
    @Override
    public FieldObject factoryMethod(int x, int y, Field field) {
        return new Strawberry(x, y, field);
    }
}
