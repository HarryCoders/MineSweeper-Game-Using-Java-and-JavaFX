/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswipper;

import java.util.Random;

/**
 *
 * @author Taleb
 */
public class RandomAIPlayer extends AutoPlayer{
    public int xMax , yMax;
    public RandomAIPlayer(int xMax, int yMax) {
    this.xMax = xMax;
    this.yMax = yMax;
    this.setname("PC");
    }
    
    @Override
    public PlayerMove GetPlayerMove() {
        PlayerMove temp = new PlayerMove();
        String s = name + " Turn : ";

        Random xx = new Random();
        int x,y;
         x = xx.nextInt(yMax);
         y = xx.nextInt(yMax);
                 System.out.println(s +x + ((char)(y+65)));
        temp.player = this;
        Square tempsquare = new Square();
        tempsquare.setX(x);
        tempsquare.setY(y);
        MoveType tempmovetype = new MoveType();
        tempmovetype.type = "Reveal";
        temp.square = tempsquare;
        temp.type = tempmovetype;
        
       return temp;
    }
    
}
