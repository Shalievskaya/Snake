/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Objects.Factory;

import com.mygdx.snakeGame.Field.Field;
import com.mygdx.snakeGame.Objects.FieldObject;
import com.mygdx.snakeGame.Objects.Lemon;

/**
 * Фабрика для создания лимона на поле
 *
 * @author Anastasia
 */
public class LemonCreator extends ObjectCreator {
    /**
     * Создание лимона
     * @param x координата по x
     * @param y координата по y
     * @param field поле, где находится предмет
     * @return созданный лимон
     */
    @Override
    public FieldObject factoryMethod(int x, int y, Field field) {
        return new Lemon(x, y, field);
    }
}
