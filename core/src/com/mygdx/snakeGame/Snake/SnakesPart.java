/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Snake;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Anastasia
 */
public class SnakesPart {
    /**
     * Изображение части змеи
     */
    protected Texture image;
    /**
     * Положение части змеи
     */
    protected Vector2 position;
    /**
     * Силуэт части змеи
     */
    protected Rectangle form;
    
    /** Создаем одну ячейку змеи
    * @param x - координата левого угла изображения
    * @param y - координата нижнего угла изображения
    */
    public SnakesPart(int x, int y){
        image = new Texture("snake.png");
        position = new Vector2(x,y);
        form  = new Rectangle(x + 2, y + 2, image.getWidth() - 4, image.getHeight() - 4);
    }
    /** Возвращает изображение сегмента
     * @return изображение 
     */
    public Texture getImage()
    {
        return image;
    }
    /** Возвращает позицию сегмента
     * @return позиция 
     */
    public Vector2 getPosition()
    {
        return position;
    }
    /** Возвращает rectangle части змеи
     * @return позиция 
     */
    public Rectangle getForm(){
        return form;
    }
}
