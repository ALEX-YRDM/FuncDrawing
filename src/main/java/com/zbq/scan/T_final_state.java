package com.zbq.scan;

/**
 * @author zbq
 * @date 2022/12/15 22:48
 */
public class T_final_state{
    private int state;
    private Token_Type kink;

    public T_final_state(int state, Token_Type kink) {
        this.state = state;
        this.kink = kink;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Token_Type getKink() {
        return kink;
    }

    public void setKink(Token_Type kink) {
        this.kink = kink;
    }
}
