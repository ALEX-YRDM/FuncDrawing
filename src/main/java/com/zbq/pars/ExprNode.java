package com.zbq.pars;

import com.zbq.scan.MathType;
import com.zbq.scan.Token_Type;

/**
 * @author zbq
 * @date 2022/12/18 13:27
 */
public class ExprNode {
    private Token_Type opCode;
    private ExprNode left,right;
    private ExprNode child;

    private MathType func;
    private double CaseConst;
    private Double CaseParmPtr;

    public MathType getFunc() {
        return func;
    }

    public void setFunc(MathType func) {
        this.func = func;
    }

    public Token_Type getOpCode() {
        return opCode;
    }

    public void setOpCode(Token_Type opCode) {
        this.opCode = opCode;
    }

    public ExprNode getLeft() {
        return left;
    }

    public void setLeft(ExprNode left) {
        this.left = left;
    }

    public ExprNode getRight() {
        return right;
    }

    public void setRight(ExprNode right) {
        this.right = right;
    }

    public ExprNode getChild() {
        return child;
    }

    public void setChild(ExprNode child) {
        this.child = child;
    }

    public double getCaseConst() {
        return CaseConst;
    }

    public void setCaseConst(double caseConst) {
        CaseConst = caseConst;
    }

    public Double getCaseParmPtr() {
        return CaseParmPtr;
    }

    public void setCaseParmPtr(Double caseParmPtr) {
        CaseParmPtr = caseParmPtr;
    }
    public ExprNode(){}
}
