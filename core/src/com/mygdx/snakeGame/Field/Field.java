/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.Field;

import com.mygdx.snakeGame.Hunter.Hunter;
import com.mygdx.snakeGame.Objects.FieldObject;
import com.mygdx.snakeGame.Snake.Snake;
import com.mygdx.snakeGame.Snake.SnakesPart;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.snakeGame.Objects.Factory.AppleCreator;
import com.mygdx.snakeGame.Objects.Factory.LemonCreator;
import com.mygdx.snakeGame.Objects.Factory.ObjectCreator;
import com.mygdx.snakeGame.Objects.Factory.OrangeCreator;
import com.mygdx.snakeGame.Objects.Factory.StrawberryCreator;
import com.mygdx.snakeGame.Objects.Factory.WallCreator;
import com.mygdx.snakeGame.Objects.EdibleObject;
import com.mygdx.snakeGame.Objects.InedibleObject;
import java.util.ArrayList;
import java.util.Random;

/**
 * Игровое поле
 *
 * @author Anastasia
 */
public class Field {

    /**
     * Ширина игрового поля
     */
    private final int width;

    /**
     * Высота игрового поля
     */
    private final int height;

    /**
     * Изображение поля
     */
    public Texture background;

    /**
     * Размер объекта на единице поля
     */
    public final int PARTSIZE = 24;

    /**
     * Константа движения вверх по полю
     */
    public final int UP = 0;

    /**
     * Константа движения влево по полю
     */
    public final int LEFT = 1;

    /**
     * Константа движения вниз по полю
     */
    public final int DOWN = 2;

    /**
     * Константа движения вправо по полю
     */
    public final int RIGHT = 3;

    /**
     * Объекты, которые находятся на поле
     */
    private ArrayList<FieldObject> objects;

    /**
     * Области ускорения на поле
     */
    private ArrayList<SpeedRange> ranges;

    /**
     * Сама змейка
     */
    private Snake snake;

    /**
     * Охотник на змейку
     */
    private Hunter hunter;

    /**
     * Конструктор поля
     *
     * @param width ширина
     * @param height высота
     */
    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        /*
        this.background = new Texture("field.jpeg");
        addObjects(); // помещаем на поле объекты
        addSnake();
        addHunter();
        this.ranges = new ArrayList<SpeedRange>();
        for (int i = 0; i < 2; i++) {
            addRanges();
        }
        */
    }

    /**
     * Получение змеи, находящейся на поле
     *
     * @return змея на поле
     */
    public Snake getSnake() {
        return snake;
    }

    /**
     * Добавление змеи на поле
     */
    private void addHunter() {
        Vector2 hunterPosition = generatePosition();
        this.hunter = new Hunter((int) hunterPosition.x, (int) hunterPosition.y); // помещаем на поле охотника
        this.hunter.setField(this);
    }

    /**
     * Добавление областей на поле
     */
    private void addRanges() {
        Vector2 rangePosition = generatePosition();
        this.ranges.add(new SpeedRange((int) rangePosition.x, (int) rangePosition.y, this));
        Vector2 sosed1 = new Vector2(rangePosition.x + 1 * PARTSIZE, rangePosition.y);
        boolean isOk = checkPosition(sosed1);
        if (!isOk) {
            sosed1 = new Vector2(rangePosition.x - 1 * PARTSIZE, rangePosition.y);
        }
        isOk = checkPosition(sosed1);
        if (!isOk) {
            sosed1 = new Vector2(rangePosition.x, rangePosition.y + 1 * PARTSIZE);
        }
        isOk = checkPosition(sosed1);
        if (!isOk) {
            sosed1 = new Vector2(rangePosition.x, rangePosition.y - 1 * PARTSIZE);
        }
        this.ranges.add(new SpeedRange((int) sosed1.x, (int) sosed1.y, this));
        Vector2 sosed2 = new Vector2(sosed1.x + 1 * PARTSIZE, sosed1.y);
        isOk = checkPosition(sosed1);
        if (!isOk) {
            sosed2 = new Vector2(sosed1.x - 1 * PARTSIZE, sosed1.y);
        }
        isOk = checkPosition(sosed2);
        if (!isOk) {
            sosed2 = new Vector2(sosed1.x, sosed1.y + 1 * PARTSIZE);
        }
        isOk = checkPosition(sosed2);
        if (!isOk) {
            sosed2 = new Vector2(sosed1.x, sosed1.y - 1 * PARTSIZE);
        }
        this.ranges.add(new SpeedRange((int) sosed2.x, (int) sosed2.y, this));
    }

    /**
     * Добавление змеи на поле
     */
    private void addSnake() {
        Vector2 snakePosition = new Vector2();
        do {
            snakePosition = generatePosition();
        } while (snakePosition.x < 3 * PARTSIZE);
        this.snake = new Snake(4, snakePosition, this); // помещаем на поле змею
        this.snake.setSpeed(1);
    }

    /**
     * Добавление съедобных и несъедобных объектов на поле
     */
    private void addObjects() {
        objects = new ArrayList<FieldObject>();
        // добавляем съедобные объекты
        ObjectCreator[] fruitCreators = {new AppleCreator(), new OrangeCreator(), new LemonCreator(), new StrawberryCreator()};
        for (ObjectCreator creator : fruitCreators) {
            Vector2 objPosition = generatePosition();
            FieldObject object = creator.factoryMethod((int) objPosition.x, (int) objPosition.y, this);
            objects.add(object);
        }
        ObjectCreator creator = new WallCreator();
        for (int i = 0; i < 10; i++)// создаем препятствия
        {
            Vector2 objPosition = generatePosition();
            FieldObject object = creator.factoryMethod((int) objPosition.x, (int) objPosition.y, this);
            objects.add(object);
        }
    }

    /**
     * Генерация случайной позиции для объекта
     *
     * @return позиция
     */
    public Vector2 generatePosition() {
        Vector2 position = new Vector2();
        Random rand = new Random();
        do {
            position.x = rand.nextInt(30) * PARTSIZE;
            position.y = rand.nextInt(20) * PARTSIZE;
        } while (!checkPosition(position));
        return position;
    }

    /**
     * Проверка, что сгенерированная позиция не занята другими объектами
     *
     * @return свободна ли позиция
     */
    private boolean checkPosition(Vector2 position) {
        boolean freePosition = true;
        for (FieldObject object : objects) {
            if (object.getPosition().equals(position)) {
                freePosition = false;
                break;
            }
        }
        if (this.getSnake() != null) {
            for (SnakesPart part : this.getSnake().getParts()) {
                if (part.getPosition().equals(position)) {
                    freePosition = false;
                    break;
                }
            }
        }
        if (this.getHunter() != null) {
            if (this.getHunter().getPosition().equals(position)) {
                freePosition = false;
            }
        }
        if (this.getRanges() != null) {
            for (SpeedRange part : this.getRanges()) {
                if (part.getPosition().equals(position)) {
                    freePosition = false;
                    break;
                }
            }
        }
        return freePosition;
    }

    /**
     * Удаляем текстуру
     */
    public void dispose() {
        background.dispose();
    }

    /**
     * Получить ширину
     *
     * @return ширина поля
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Получить высоту
     *
     * @return высота поля
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Получить объекты с поля
     *
     * @return объекты с поля
     */
    public ArrayList<FieldObject> getObjects() {
        return this.objects;
    }

    /**
     * Получить съедобные объекты с поля
     *
     * @return съедобные объекты с поля
     */
    public ArrayList<EdibleObject> getFood() {
        ArrayList<EdibleObject> food = new ArrayList<EdibleObject>();
        for (FieldObject fruit : this.objects) {
            if (fruit instanceof EdibleObject) {
                food.add((EdibleObject) fruit);
            }
        }
        return food;
    }

    /**
     * Получить несъедобные объекты с поля
     *
     * @return несъедобные объекты с поля
     */
    public ArrayList<InedibleObject> getWalls() {
        ArrayList<InedibleObject> walls = new ArrayList<InedibleObject>();
        for (FieldObject wall : this.objects) {
            if (wall instanceof InedibleObject) {
                walls.add((InedibleObject) wall);
            }
        }
        return walls;
    }

    /**
     * Получить области ускорения с поля
     *
     * @return области с поля
     */
    public ArrayList<SpeedRange> getRanges() {
        return this.ranges;
    }

    /**
     * Обновление
     */
    public void update() {
        snake.update();
        hunter.update();
    }

    /**
     * Получить охотника
     *
     * @return охотник
     */
    public Hunter getHunter() {
        return this.hunter;
    }
}
