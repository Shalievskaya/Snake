package com.mygdx.snakeGame.game;

import com.mygdx.snakeGame.Field.Field;
import com.mygdx.snakeGame.Objects.FieldObject;
import com.mygdx.snakeGame.Objects.EdibleObject;
import com.mygdx.snakeGame.Objects.InedibleObject;
import com.mygdx.snakeGame.Snake.Snake;
import com.mygdx.snakeGame.Snake.SnakesPart;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.snakeGame.Field.SpeedRange;
import com.mygdx.snakeGame.Objects.Wall;
import java.util.ArrayList;
import com.mygdx.modules.Module;
import com.mygdx.modules.ModuleEngine;
import com.mygdx.snakeGame.Objects.Orange;

public class Game extends ApplicationAdapter {

    /**
     * Счет игрока
     */
    private int score;
    /**
     * Отображение объектов
     */
    SpriteBatch batch;
    /**
     * Поле, на котором происходит игра
     */
    public Field field;
    /**
     * Ширина игрового поля
     */
    public static final int WIDTH = 720;
    /**
     * Высота игрового поля
     */
    public static final int HEIGHT = 480;
    /**
     * Конец игры
     */
    public static boolean gameOver;
    /**
     * Счетчик игры на экране
     */
    BitmapFont scoreBitMap;

    public Module mg;

    private static int timeTochange = 0;

    /**
     * Создание игры
     */
    @Override
    public void create() {
        field = new Field(WIDTH, HEIGHT);
        batch = new SpriteBatch();
        Gdx.gl.glClearColor(1, 1, 1, 1);
        gameOver = false;
        score = 0;
        scoreBitMap = new BitmapFont();
    }

    public void setModule(Module module) {
        mg = module;
    }

    /**
     * Обновление процесса игры
     */
    @Override
    public void render() {
        if(field.getSnake().getSpeed()==0)
            field.getSnake().setSpeed(1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        field.update();
        update();
        ArrayList<SnakesPart> snake = field.getSnake().getParts();
        batch.begin();
        batch.draw(field.background, 0, 0, field.background.getWidth(), field.background.getHeight());
        if (Gdx.input.isKeyPressed(Input.Keys.B)) {
            String[] arr = new String[1];
            arr[0] = "..\\build\\classes\\main\\com\\mygdx\\snakeGame\\modules\\";
            ModuleEngine.main(arr, this);
        }
        if (mg != null) {
            mg.run();
        }
        for (FieldObject o : field.getObjects()) {
            if (!(o instanceof Orange && !((Orange) o).getVisible())) {
                batch.draw(o.getImage(), o.getPosition().x, o.getPosition().y);
            }
        }
        for (SpeedRange o : field.getRanges()) {
            batch.draw(o.getImage(), o.getPosition().x, o.getPosition().y);
        }
        batch.draw(field.getHunter().getImage(), field.getHunter().getPosition().x, field.getHunter().getPosition().y);
        for (SnakesPart part : snake) {
            batch.draw(part.getImage(), part.getPosition().x, part.getPosition().y);
        }
        scoreBitMap.setColor(Color.WHITE);
        scoreBitMap.draw(batch, "Score:" + String.valueOf(this.score), 3, field.background.getHeight() - 10);
        batch.end();
    }

    /**
     * Проверка отношений между объектами на поле, которые могут вызвать
     * изменения в процессе игры
     */
    public void update() {
        if (timeTochange == 25) {
            ((Orange) (field.getObjects().get(1))).setVisible(!((Orange) (field.getObjects().get(1))).getVisible());
            timeTochange = 0;
        } else {
            timeTochange++;
        }
        SnakesPart head = field.getSnake().getParts().get(0);
        for (FieldObject o : field.getObjects()) {
            if (Intersector.overlaps(head.getForm(), o.getForm())) {
                o.setPosition(field.generatePosition());
                field.getSnake().eat(o);
                score += 10 * field.getSnake().getSpeed();
            }
        }
        if (field.getSnake().getIsAlive() == false) {
            field.dispose();
            field = new Field(WIDTH, HEIGHT);
            field.getSnake().setSpeed(1);
            score = 0;
        }
        for (SpeedRange o : field.getRanges()) {
            boolean flag = field.getSnake().checkAcceleration(o);
            if (flag) // если пересекаются змея и данная область
            {
                //проверяем, это новое пересечение или старое
                if (o.getIsActive() == false) { // если новое, увеличиваем скорость
                    field.getSnake().setSpeed(field.getSnake().getSpeed() + 2);
                    o.setIsActive(true);
                }
            } else// если не пересеклись
            // проверяем было ли раньше в данном месте пересечение
             if (o.getIsActive() == true) { // если новое, увеличиваем скорость
                    field.getSnake().setSpeed(field.getSnake().getSpeed() - 2);
                    o.setIsActive(false);
                }
        }
    }

    public ArrayList<EdibleObject> getFood() {
        return field.getFood();
    }

    public Vector2 getSnakePosition() {
        return (field.getSnake().getParts()).get(0).getPosition();
    }

    public void setNavigate(int direction) {
        field.getSnake().setNextDirection(direction);
    }

    public boolean checkNavigate(int newNav) {
        return !field.getSnake().checkNewDirection(newNav);
    }

    public int getSnakeNavigate() {
        return field.getSnake().direction;
    }

    public int getSnakeNextNavigate() {
        return field.getSnake().nextDirection;
    }
}
