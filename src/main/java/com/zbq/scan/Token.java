package com.zbq.scan;

/**
 * @author zbq
 * @date 2022/12/13 22:25
 */
public class Token {
    private Token_Type type;
    private String lexeme;
    private double value;
    private MathType funcType;
    private Position where;

    public Token_Type getType() {
        return type;
    }

    public void setType(Token_Type type) {
        this.type = type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public void setLexeme(String lexeme) {
        this.lexeme = lexeme;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public MathType getFuncType() {
        return funcType;
    }

    public void setFuncType(MathType funcType) {
        this.funcType = funcType;
    }

    public Position getWhere() {
        return where;
    }

    public void setWhere(Position where) {
        this.where = where;
    }

    public Token(Token_Type type, String lexeme, double value, MathType funcType, Position where) {
        this.type = type;
        this.lexeme = lexeme;
        this.value = value;
        this.funcType = funcType;
        this.where = where;
    }

    public Token(Token_Type type) {
        this.type = type;
    }
    public Token(){
        this.lexeme="";
    }
}
