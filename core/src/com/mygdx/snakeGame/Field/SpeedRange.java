/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Field;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Область с ускорением
 * @author Anastasia
 */
public class SpeedRange {
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
     * Активна ли область
     */
    private boolean isActive;
    /**
     * Конструктор для скоростной области
     *
     * @param x координата по x
     * @param y координата по y
     * @param field поле, где находится область
     */
    public SpeedRange(int x, int y, Field field) {
        this.image = new Texture("speed.png");
        this.position = new Vector2(x,y);
        form  = new Rectangle(x + 2, y + 2, image.getWidth() - 4, image.getHeight() - 4);
    }
    /** Возвращает изображение области
     * @return изображение 
     */
    public Texture getImage()
    {
        return image;
    }
    /** Возвращает позицию области
     * @return позиция 
     */
    public Vector2 getPosition()
    {
        return position;
    }
    /** Возвращает rectangle области
     * @return позиция 
     */
    public Rectangle getForm(){
        return form;
    }
    /** Возвращает флаг, действует ли область на змею в данный момент
     * @return флаг 
     */
    public boolean getIsActive()
    {
        return isActive;
    }
    /** Устанавливает флаг, действует ли область на змею в данный момент
     * @param isActive флаг
     */
    public void setIsActive(boolean isActive)
    {
        this.isActive = isActive;
    }
}
