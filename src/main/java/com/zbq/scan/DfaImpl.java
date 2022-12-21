package com.zbq.scan;

/**
 * @author zbq
 * @date 2022/12/15 9:55
 */
public class DfaImpl implements Dfa {
    private int start_state;
    private T_final_state[] final_state;
    private T_state_trans[] transfers;

    @Override
    public int get_start_state() {
        return getStart_state();
    }

    @Override
    public int move(int start_src, char ch) {
        for(T_state_trans st: getTransfers()){
            if(st.getCurrent_state()==start_src){
                if(st.getChars()!=null&&st.getChars().contains(ch)){
                    return st.getState_to();
                }
                if(st.getCh()!=null&&st.getCh()==ch){
                    return st.getState_to();
                }
            }
        }
        return -1;
    }

    @Override
    public Token_Type state_is_final(int state) {
        for( T_final_state fs:getFinal_state()){
            if(fs.getState()==state){
                return fs.getKink();
            }
        }
        return Token_Type.ERRTOKEN;
    }

    public DfaImpl(int start_state, T_final_state[] final_state, T_state_trans[] transfers) {
        this.start_state = start_state;
        this.final_state = final_state;
        this.transfers = transfers;
    }

    public int getStart_state() {
        return start_state;
    }

    public void setStart_state(int start_state) {
        this.start_state = start_state;
    }

    public T_final_state[] getFinal_state() {
        return final_state;
    }

    public void setFinal_state(T_final_state[] final_state) {
        this.final_state = final_state;
    }

    public T_state_trans[] getTransfers() {
        return transfers;
    }

    public void setTransfers(T_state_trans[] transfers) {
        this.transfers = transfers;
    }
}



