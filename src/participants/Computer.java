/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package participants;

/**
 *
 * @author ehite
 */
public class Computer extends Player {

    public Computer() {
        super();
    }

    private String operationSystem;

    public String getOperationSystem() {
        return this.operationSystem;
    }

    public void setOperationSystem(String value) {
        this.operationSystem = value;
    }

    private int memorySize;

    public int getMemorySize() {
        return this.memorySize;
    }

    public void setMemorySize(int value) {
        this.memorySize = value;
    }
}
