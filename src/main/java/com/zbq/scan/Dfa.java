package com.zbq.scan;

/**
 * @author zbq
 * @date 2022/12/15 10:10
 */
public interface Dfa {
    int get_start_state();
    int move(int start_src,char ch);
    Token_Type state_is_final(int state);
}
