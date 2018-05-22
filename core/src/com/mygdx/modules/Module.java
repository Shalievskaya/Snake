/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.modules;
import com.mygdx.snakeGame.game.Game;
/**
 * Определяем интерфейс модулей. Пусть модуль сначала загружается, потом исполняется, загружая результат,
 * и затем уже выгружается.
 * @author Anastasia
 */
public interface Module {

  public static final int EXIT_SUCCESS = 0;
  public static final int EXIT_FAILURE = 1;

  public void load(Game game,Module batch);
  public int run();
  public void unload();
}
