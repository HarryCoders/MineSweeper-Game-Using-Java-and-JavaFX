/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswipper;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Taleb
 */
public class Square {

    private int x,y;
    private Mine mine;
    private List<Player>playerMoves;
    private SquareStatus currentSquareStatus;

    public Square() {
        playerMoves = new ArrayList<Player>();
        mine = null;
    }
    
    
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Mine getMine() {
        return mine;
    }

    public List<Player> getPlayerMoves() {
        return playerMoves;
    }

    public SquareStatus getCurrentSquareStatus() {
        return currentSquareStatus;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMine(Mine mine) {
        this.mine = mine;
    }

    public void setPlayerMove(Player playermove) {
        playerMoves.add(playermove);
    }

    public void setCurrentSquareStatus(SquareStatus currentSquareStatus) {
        this.currentSquareStatus = currentSquareStatus;
    }
    
    public boolean isMine()
    {
        if(mine!=null)
               return true;
        
     return false;
    }

}
