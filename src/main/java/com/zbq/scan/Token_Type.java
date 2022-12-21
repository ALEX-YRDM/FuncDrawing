package com.zbq.scan;

/**
 * @author zbq
 * @date 2022/12/13 22:19
 */
public enum Token_Type {
    COMMENT,
    ID,

    ORIGIN, SCALE, ROT, IS, TO,
    STEP, DRAW, FOR, FROM,
    T,

    SEMICO, L_BRACKET, R_BRACKET, COMMA,
    PLUS,MINUS,MUL,DIV,POWER,
    FUNC,
    CONST_ID,

    NONTOKEN,
    ERRTOKEN
}
