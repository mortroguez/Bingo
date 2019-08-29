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
public class Human extends Player {

    public Human() {
        super();
    }

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int value) {
        age = value;
    }
}
