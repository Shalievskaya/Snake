/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.java.com.mygdx.snakeGame.modules;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.modules.Module;
import com.mygdx.snakeGame.game.Game;
import com.mygdx.snakeGame.modules.ModeleGame;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.mygdx.snakeGame.Field.Field;
import com.mygdx.snakeGame.Objects.EdibleObject;
import com.mygdx.snakeGame.Objects.Strawberry;
import com.mygdx.snakeGame.Snake.Snake;
import static org.junit.Assert.*;

/**
 *
 * @author Anastasia
 */
public class ModeleGameTest {
    
    public ModeleGameTest() {
    }
    /* Тест на случай, если передана на загрузку модель игры за значением null */
    @org.junit.Test(expected = NullPointerException.class)
    public void testLoadNullModel(){
        System.out.println("load: null gm");
        ModeleGame mg = new ModeleGame();
        mg.load(null,mg);
    }
    /* Тест на случай, если передан на загрузку модель игры со значением null */
    @org.junit.Test(expected = NullPointerException.class)
    public void testLoadNullModule(){
        System.out.println("load: null module");
        ModeleGame mg = new ModeleGame();
        mg.load(null,mg);
    }
    /* Тест на случай, если змейке нужно идти ВВЕРХ */
    @org.junit.Test
    public void testGetNavigateUp(){
        System.out.println("testGetNavigate: UP");
        ModeleGame mg = new ModeleGame();
        Field field = new Field(720,480);
        Vector2 positionSnake = new Vector2(0,24);
        Snake snake = new Snake(1, positionSnake, field);
        Strawberry f = new Strawberry(0,48,field);
        assertEquals(field.UP,mg.getDirection(f,snake));
    }
    /* Тест на случай, если змейке нужно идти ВНИЗ */
    @org.junit.Test
    public void testGetNavigateDown(){
        System.out.println("testGetNavigate: UP");
        ModeleGame mg = new ModeleGame();
        Field field = new Field(720,480);
        Vector2 positionSnake = new Vector2(0,48);
        Snake snake = new Snake(1, positionSnake, field);
        Strawberry f = new Strawberry(0,24,field);
        assertEquals(field.DOWN,mg.getDirection(f,snake));
    }
    /* Тест на случай, если змейке нужно идти ВПРАВО */
    @org.junit.Test
    public void testGetNavigateRight(){
        System.out.println("testGetNavigate: UP");
        ModeleGame mg = new ModeleGame();
        Field field = new Field(720,480);
        Vector2 positionSnake = new Vector2(0,0);
        Snake snake = new Snake(1, positionSnake, field);
        Strawberry f = new Strawberry(24,0,field);
        assertEquals(field.RIGHT,mg.getDirection(f,snake));
    }
    /* Тест на случай, если змейке нужно идти ВЛЕВО */
    @org.junit.Test
    public void testGetNavigateLeft(){
        System.out.println("testGetNavigate: UP");
        ModeleGame mg = new ModeleGame();
        Field field = new Field(720,480);
        Vector2 positionSnake = new Vector2(24,0);
        Snake snake = new Snake(1, positionSnake, field);
        Strawberry f = new Strawberry(0,0,field);
        assertEquals(field.LEFT,mg.getDirection(f,snake));
    }

}
