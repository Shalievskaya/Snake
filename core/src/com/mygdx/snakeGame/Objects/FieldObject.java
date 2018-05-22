/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.snakeGame.Field.Field;

/**
 *
 * @author Anastasia
 */
public abstract class FieldObject {

    /**
     * Позиция объекта
     */
    protected Vector2 position;
    /**
     * Изображение объекта
     */
    protected Texture image;
    /**
     * Форма объекта
     */
    protected Rectangle form;
    /**
     * Поле, где находится объект
     */
    protected Field field;

    /**
     * Получить позицию объекта
     * @return позиция
     */
    public Vector2 getPosition() {
        return this.position;
    }
    /**
     * Сохранить позицию объекта
     * @param pos позиция
     */
    public void setPosition(Vector2 pos) {
        this.position = pos;
        form.x=pos.x;
        form.y=pos.y;
    }
    /**
     * Получить поле
     * @return поле
     */
    public Field getField() {
        return this.field;
    }
    /**
     * Конструктор
     * @param x координата по х
     * @param y координата по у
     * @param field поле, на котором объект находится
     */
    public FieldObject(int x, int y, Field field) {
        image = new Texture("apple.png");
        position = new Vector2(x, y);
        this.field = field;
        form = new Rectangle(x + 2, y + 2, image.getWidth() - 4, image.getHeight() - 4);
    }

    /**
     * Возвращает изображение объекта
     * @return изображение объекта
     */
    public Texture getImage() {
        return this.image;
    }

    /**
     * Возвращает форму объекта
     * @return форма объекта
     */
    public Rectangle getForm() {
        return this.form;
    }

    /**
     * Совершает действие со змейкой
     */
    public abstract void changeSnake();
}
