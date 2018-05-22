/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.snakeGame.modules;
import com.mygdx.modules.Module;
import com.mygdx.snakeGame.game.Game;
/**
 *
 * @author Anastasia
 */
public class ModuleGameOver implements Module{

    Game gm;
    
    @Override
    public void load(Game gm, Module batch) {
        this.gm = gm;
        this.gm.setModule(batch);
    }

    @Override
    public int run() {
        return 0;
    }

    @Override
    public void unload() {
        
    }
    
}