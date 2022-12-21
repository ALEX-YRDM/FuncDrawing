package com.zbq.pars;

import com.zbq.scan.MathType;
import com.zbq.scan.Token;

/**
 * @author zbq
 * @date 2022/12/18 13:30
 */
public class T_parser_stuff {
    private int indent;
    private ExprNode start;
    private ExprNode end;
    private ExprNode step;
    private ExprNode x;
    private ExprNode y;
    private ExprNode angle;
    private Token curr_token;
    private double parameter;

    public int getIndent() {
        return indent;
    }

    public void setIndent(int indent) {
        this.indent = indent;
    }

    public ExprNode getStart() {
        return start;
    }

    public void setStart(ExprNode start) {
        this.start = start;
    }

    public ExprNode getEnd() {
        return end;
    }

    public void setEnd(ExprNode end) {
        this.end = end;
    }

    public ExprNode getStep() {
        return step;
    }

    public void setStep(ExprNode step) {
        this.step = step;
    }

    public ExprNode getX() {
        return x;
    }

    public void setX(ExprNode x) {
        this.x = x;
    }

    public ExprNode getY() {
        return y;
    }

    public void setY(ExprNode y) {
        this.y = y;
    }

    public ExprNode getAngle() {
        return angle;
    }

    public void setAngle(ExprNode angle) {
        this.angle = angle;
    }

    public Token getCurr_token() {
        return curr_token;
    }

    public void setCurr_token(Token curr_token) {
        this.curr_token = curr_token;
    }

    public double getParameter() {
        return parameter;
    }

    public void setParameter(double parameter) {
        this.parameter = parameter;
    }
    public T_parser_stuff(){}
}
