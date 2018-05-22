/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Objects.Factory;
import com.mygdx.snakeGame.Field.Field;
import com.mygdx.snakeGame.Objects.FieldObject;

/**
 * Абстрактный класс-фабрика для всех объектов на поле
 * @author Anastasia
 */
abstract public class ObjectCreator {

    /**
     * Метод - фабрика для всех объектов на поле
     * @param x координата по х
     * @param y координата по у
     * @param field поле
     * @return созданный объект
     */
    public abstract FieldObject factoryMethod(int x, int y,Field field);
}
