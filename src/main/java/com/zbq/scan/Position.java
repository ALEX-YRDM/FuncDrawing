package com.zbq.scan;

/**
 * @author zbq
 * @date 2022/12/13 22:20
 */
public class Position {
    private int line;
    private int col;

    public Position(int line, int col) {
        this.line = line;
        this.col = col;
    }
    public Position(){}

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
