/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mineswipper;

/**
 *
 * @author Taleb
 */
public class GuiPlayer extends Player {

    public GuiPlayer(String name) 
    {
        this.name = name;
        this.currentScore = 0;
        illegagamemove = new IllegalGameMove();
        illegalsquarename = new IllegalSquareName();
     }
    
    @Override
    public PlayerMove GetPlayerMove() {
        return null;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
