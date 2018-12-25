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
 public abstract class Player {
    public String name;
    public int currentScore;
    public IllegalGameMove illegagamemove;
    public IllegalSquareName illegalsquarename;
    
    public abstract PlayerMove GetPlayerMove();
}
