package com.mygdx.snakeGame.Snake;

import com.mygdx.snakeGame.Objects.FieldObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.snakeGame.Field.Field;
import com.mygdx.snakeGame.Field.SpeedRange;
import com.mygdx.snakeGame.Objects.InedibleObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Сама змея
 *
 * @author Anastasia
 */
public class Snake {

    /**
     * Скорость змеи
     */
    private int speed;
    /**
     * Направление следующего движения змеи
     */
    public int nextDirection;
    /**
     * Составные части змеи
     */
    private ArrayList<SnakesPart> parts = new ArrayList<SnakesPart>();
    /**
     * Жива ли змейка
     */
    private boolean isAlive;
    /**
     * Пора ли сделать шаг
     */
    private int isStep;
    /**
     * Направление текущего движения
     */
    public int direction;
    /**
     * Поле
     */
    private Field field;

    /**
     * Конструктор змеи
     *
     * @param startLength длина змеи, которую создаем
     */
    public Snake(int startLength, Vector2 headPos, Field field) {
        this.field = field;
        isAlive = true;
        isStep = 5;
        this.direction = field.RIGHT;
        this.nextDirection = this.direction;
        for (int i = startLength - 1; i >= 0; i--) {
            Vector2 snakePosition = new Vector2();
            snakePosition.x = headPos.x - i * field.PARTSIZE;
            snakePosition.y = headPos.y;
            this.parts.add(new SnakesPart((int) snakePosition.x, (int) snakePosition.y));
        }
    }

    /**
     * Попытаться съесть объект перед собой
     */
    public void eat(FieldObject object) {
        object.changeSnake();
    }

    /**
     * Сделать шаг в заданном направлении
     */
    public void makeStep(int direction) {
        SnakesPart head = this.parts.get(0); // получили голову змеи
        int len = parts.size() - 1; // получили длину змеи

        for (int i = len; i > 0; i--) { // для каждой частички змеи
            SnakesPart before = parts.get(i - 1); // получаем частичку до текущей
            SnakesPart part = parts.get(i); // и получаем текущую
            part.position.x = before.position.x; // у текущей ставим позицию как у предыдущей
            part.position.y = before.position.y; // у текущей ставим позицию как у предыдущей
            part.form.x = part.position.x;
            part.form.y = part.position.y;
        }
        // ПЕРЕДВИГАЕМ САМУ ГОЛОВУ
        if (direction == field.UP) { // если направление указано вверх
            head.position.y += field.PARTSIZE; // уменьшаем координату по у
            head.form.y = head.position.y;
        }
        if (direction == field.LEFT) { // если направление указано влево
            head.position.x -= field.PARTSIZE; // уменьшаем координату по х
            head.form.x = head.position.x;
        }
        if (direction == field.DOWN) { // если направление указано вниз
            head.position.y -= field.PARTSIZE; // увеличиваем координату по у
            head.form.y = head.position.y;
        }
        if (direction == field.RIGHT) { // если направление указано вправо
            head.position.x += field.PARTSIZE; // увеличиваем координату по х
            head.form.x = head.position.x;
        }
        // если происходит выход за границы поля, то перемещаем на обратную сторону
        if (head.position.x < 0) {
            head.position.x = field.getWidth() - field.PARTSIZE;
            head.form.x = head.position.x;
        } else if (head.position.x > field.getWidth() - field.PARTSIZE) {
            head.position.x = 0;
            head.form.x = head.position.x;
        } else if (head.position.y < 0) {
            head.position.y = field.getHeight() - field.PARTSIZE;
            head.form.y = head.position.y;
        } else if (head.position.y > field.getHeight() - field.PARTSIZE) {
            head.position.y = 0;
            head.form.y = head.position.y;
        }
        checkBitten();
    }

    /**
     * Изменить скорость движения змеи
     *
     * @param speed новая скорость
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Получить скорость движения змеи
     *
     * @return speed скорость
     */
    public int getSpeed() {
        return this.speed;
    }

    /**
     * Изменить напревление движения змеи
     *
     * @param direction новое направление
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * Проверяем, что змейка не умерла
     */
    public void checkBitten() {
        SnakesPart head = parts.get(0);
        for (int i = 4; i < parts.size(); i++) {
            SnakesPart part = parts.get(i);
            if (Intersector.overlaps(head.getForm(), part.getForm())) {
                isAlive = false;
            }
        }
        for (int i = 0; i < parts.size(); i++) {
            SnakesPart part = parts.get(i);
            if (Intersector.overlaps(part.getForm(), this.field.getHunter().getForm())) {
                isAlive = false;
            }
        }
    }

    /**
     * Проверяем, что есть ускорение
     *
     * @param range ускорение от какой области проверяем
     * @return есть ли ускорение
     */
    public boolean checkAcceleration(SpeedRange range) {
        for (int i = 0; i < parts.size(); i++) {
            SnakesPart part = parts.get(i);
            if (Intersector.overlaps(part.getForm(), range.getForm())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Сбросить последнюю часть хвоста
     */
    public void throwTheTail() {
        parts.remove(parts.size() - 1);
    }

    /**
     * Отрастить хвост
     */
    public void growTheTail() {
        SnakesPart end = this.parts.get(this.parts.size() - 1);
        this.parts.add(new SnakesPart((int) end.position.x, (int) end.position.y));
    }

    /**
     * Геттер для поля isAlive
     *
     * @return жива ли змейка
     */
    public boolean getIsAlive() {
        return this.isAlive;
    }

    /**
     * Сеттер для поля isAlive
     *
     * @param isAlive жива ли змейка
     */
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
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
     * Геттер для частей змеи
     *
     * @return части змеи
     */
    public ArrayList<SnakesPart> getParts() {
        return this.parts;
    }

    /**
     * Обновить положение змейки
     */
    public void update() {
        SnakesPart head = this.parts.get(0);
        //Для каждой единицы скорости
        for (int i = 0; i < speed; i++) {
            if (head.position.x % head.image.getWidth() == 0 && head.position.y % head.image.getHeight() == 0 && direction != nextDirection) {
                setDirection(nextDirection);
            } else {
                setNextDirection();
            }
            if (isStep == 5) {
                setDirection(nextDirection);
                isStep = 0;
                makeStep(this.direction);
            } else {
                isStep++;
            }
        }
    }

    /**
     * Устанавливает следующее направление
     */
    private void setNextDirection() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && direction != field.DOWN) {
            nextDirection = field.UP;
        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && direction != field.UP) {
            nextDirection = field.DOWN;
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && direction != field.LEFT) {
            nextDirection = field.RIGHT;
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && direction != field.RIGHT) {
            nextDirection = field.LEFT;
        }
    }

    public void setNextDirection(int nav) {
        if (nav == field.UP && this.direction != field.DOWN
                || nav == field.DOWN && this.direction != field.UP
                || nav == field.RIGHT && this.direction != field.LEFT
                || nav == field.LEFT && this.direction != field.RIGHT) {
            this.nextDirection = nav;
        }
    }

    public boolean checkNewDirection(int dir) {
        SnakesPart head = parts.get(0);
        int posX = (int) head.getPosition().x;
        int posY = (int) head.getPosition().y;
        if (dir == 0) {
            posY += field.PARTSIZE;
        } else if (dir == 2) {
            posY -= field.PARTSIZE;
        } else if (dir == 1) {
            posX -= field.PARTSIZE;
        } else if (dir == 3) {
            posX += field.PARTSIZE;
        }

        if (posX > field.getWidth() - field.PARTSIZE) {
            posX = 0;
        } else if (posX < 0) {
            posX = field.getWidth() - field.PARTSIZE;
        }
        if (posY > field.getHeight() - field.PARTSIZE) {
            posY = 0;
        } else if (posY < 0) {
            posY = field.getHeight() - field.PARTSIZE;
        }
        // к данному моменту нашли новые posX и posY
        return (collisionTail(posX, posY) || collisionBlocks(posX, posY));
    }

    /**
     * Проверка столкновений с хвостом
     */
    public boolean collisionTail(int posX, int posY) {
        for (int i = 4; i < parts.size(); i++) {
            SnakesPart part = parts.get(i);
            Rectangle form = new Rectangle(posX + 2, posY + 2, parts.get(0).image.getWidth() - 4, parts.get(0).image.getHeight() - 4);
            if (Intersector.overlaps(form, part.getForm())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Проверка столкновений с блоками
     */
    public boolean collisionBlocks(int posX, int posY) {
        ArrayList<InedibleObject> blocks = this.field.getWalls();
        Rectangle form = new Rectangle(posX + 2, posY + 2, parts.get(0).image.getWidth() - 4, parts.get(0).image.getHeight() - 4);
        for (InedibleObject o : blocks) {
            if (Intersector.overlaps(o.getForm(), form)) {
                return true;
            }
        }
        return false;
    }
}
