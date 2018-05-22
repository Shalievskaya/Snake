/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Hunter;

import com.mygdx.snakeGame.Snake.Snake;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.snakeGame.Field.Field;

/**
 *
 * @author Anastasia
 */
public class Hunter {

    /**
     * Изображение охотника
     */
    protected Texture image;
    /**
     * Положение охотника
     */
    protected Vector2 position;
    /**
     * Силуэт охотника
     */
    protected Rectangle form;
    /**
     * Пора ли сделать шаг
     */
    private int isStep;
    /**
     * Направление текущего движения
     */
    private int direction;
    /**
     * Поле, на котором двигается охотник
     */
    private Field field;

    /**
     * Создание охотника, конструктор
     *
     * @param x - координата левого угла изображения
     * @param y - координата нижнего угла изображения
     */
    public Hunter(int x, int y) {
        isStep = 0;
        image = new Texture("hunter.png");
        position = new Vector2(x, y);
        form = new Rectangle(x + 2, y + 2, image.getWidth() - 4, image.getHeight() - 4);
    }

    /**
     * Возвращает изображение сегмента
     *
     * @return изображение
     */
    public Texture getImage() {
        return image;
    }

    /**
     * Возвращает позицию сегмента
     *
     * @return позиция
     */
    public Vector2 getPosition() {
        return position;
    }

    
    
    /**
     * Возвращает rectangle охотника
     *
     * @return позиция
     */
    public Rectangle getForm() {
        return form;
    }

    /**
     * Обновить позицию охотника
     */
    public void update() {
        if (this.position.x % this.image.getWidth() == 0 && this.position.y % this.image.getHeight() == 0) {
            int maxDiff = 0;
            direction = field.RIGHT;
            if (Math.abs(this.field.getSnake().getParts().get(0).getPosition().x - this.position.x) > maxDiff && (this.field.getSnake().getParts().get(0).getPosition().x - this.position.x) > 0) {
                maxDiff = (int) (this.field.getSnake().getParts().get(0).getPosition().x - this.position.x);
                direction = field.RIGHT;
            }
            if (Math.abs(this.field.getSnake().getParts().get(0).getPosition().x - this.position.x) > maxDiff && (this.field.getSnake().getParts().get(0).getPosition().x - this.position.x) < 0) {
                maxDiff = (int) (this.field.getSnake().getParts().get(0).getPosition().x - this.position.x);
                direction = field.LEFT;
            }
            if (Math.abs(this.field.getSnake().getParts().get(0).getPosition().y - this.position.y) > maxDiff && (this.field.getSnake().getParts().get(0).getPosition().y - this.position.y) > 0) {
                maxDiff = (int) (this.field.getSnake().getParts().get(0).getPosition().y - this.position.y);
                direction = field.UP;
            }
            if (Math.abs(this.field.getSnake().getParts().get(0).getPosition().y - this.position.y) > maxDiff && (this.field.getSnake().getParts().get(0).getPosition().y - this.position.y) < 0) {
                maxDiff = (int) (this.field.getSnake().getParts().get(0).getPosition().y - this.position.y);
                direction = field.DOWN;
            }
        }
        if (isStep > 25) {
            makeStep(direction);
            isStep = 0;
        } else {
            isStep++;
        }
    }

    /**
     * Сеттер для поля 
     *
     * @param field поле
     */
    public void setField(Field field) {
        this.field = field;
    }
    
    /**
     * Сделать шаг в нужном направлении
     *
     * @param direction направление шага
     */
    public void makeStep(int direction) {
        if (direction == field.UP) { // если направление указано вверх
            this.position.y += field.PARTSIZE; // уменьшаем координату по у
            this.form.y = this.position.y;
        }
        if (direction == field.LEFT) { // если направление указано влево
            this.position.x -= field.PARTSIZE; // уменьшаем координату по х
            this.form.x = this.position.x;
        }
        if (direction == field.DOWN) { // если направление указано вниз
            this.position.y -= field.PARTSIZE; // увеличиваем координату по у
            this.form.y = this.position.y;
        }
        if (direction == field.RIGHT) { // если направление указано вправо
            this.position.x += field.PARTSIZE; // увеличиваем координату по х
            this.form.x = this.position.x;
        }
        // если происходит выход за границы поля, то перемещаем на обратную сторону
        if (this.position.x < 0) {
            this.position.x = field.getWidth() - field.PARTSIZE;
            this.form.x = this.position.x;
        } else if (this.position.x > field.getWidth() - field.PARTSIZE) {
            this.position.x = 0;
            this.form.x = this.position.x;
        } else if (this.position.y < 0) {
            this.position.y = field.getHeight() - field.PARTSIZE;
            this.form.y = this.position.y;
        } else if (this.position.y > field.getHeight() - field.PARTSIZE) {
            this.position.y = 0;
            this.form.y = this.position.y;
        }
    }

    /**
     * Возвращает направление движения
     *
     * @return направление
     */
    public int getDirection() {
        return direction;
    }
}
