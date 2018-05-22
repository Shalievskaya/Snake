/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Objects.Factory;

import com.mygdx.snakeGame.Field.Field;
import com.mygdx.snakeGame.Objects.Apple;
import com.mygdx.snakeGame.Objects.FieldObject;

/**
 * Фабрика для создания яблок на поле
 *
 * @author Anastasia
 */
public class AppleCreator extends ObjectCreator {
    /**
     * Создание яблока
     * @param x координата по x
     * @param y координата по y
     * @param field поле, где находится предмет
     * @return созданное яблоко
     */
    @Override
    public FieldObject factoryMethod(int x, int y, Field field) {
        return new Apple(x, y, field);
    }
}
