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
public class SquareStatus {

  public SquareStatus()
   {
       currentStatus = status.Closed;
   }
    public static enum status 
    {
        Closed , OpenedEmpty,OpenedNumber , OpenedMine,Marked
        ,oneMine,twoMines,threeMines,fourMines,fiveMines,sixMines,sevenMines,eightMines
    }
    status currentStatus;
    
    public status getStatus(){
       return currentStatus;
    }
    public void setStatus(status s){
       currentStatus = s;
    }
}
