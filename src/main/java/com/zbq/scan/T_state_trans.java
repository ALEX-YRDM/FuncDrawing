package com.zbq.scan;

import java.util.LinkedList;

/**
 * @author zbq
 * @date 2022/12/15 22:48
 */
public class T_state_trans{
    private int current_state;
    private LinkedList<Character> chars;
    private Character ch;
    private int state_to;

    public T_state_trans(int current_state, LinkedList<Character> chars, int state_to) {
        this.current_state = current_state;
        this.chars = chars;
        this.state_to = state_to;
        this.ch=null;
    }

    public T_state_trans(int current_state, Character ch, int state_to) {
        this.current_state = current_state;
        this.ch = ch;
        this.state_to = state_to;
        this.chars=null;
    }

    public int getCurrent_state() {
        return current_state;
    }

    public void setCurrent_state(int current_state) {
        this.current_state = current_state;
    }

    public LinkedList<Character> getChars() {
        return chars;
    }

    public void setChars(LinkedList<Character> chars) {
        this.chars = chars;
    }

    public int getState_to() {
        return state_to;
    }

    public void setState_to(int state_to) {
        this.state_to = state_to;
    }

    public Character getCh() {
        return ch;
    }

    public void setCh(Character ch) {
        this.ch = ch;
    }
}
