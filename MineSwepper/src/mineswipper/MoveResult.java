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
public class MoveResult {
    private int scoreChange;
    private SquareStatus newStatus;
    
    public void setScoreChange(int scoreChange) {
        this.scoreChange = scoreChange;
    }

    public void setNewStatus(SquareStatus newStatus) {
        this.newStatus = newStatus;
    }

    public int getScoreChange() {
        return scoreChange;
    }

    public SquareStatus getNewStatus() {
        return newStatus;
    }

    
}
