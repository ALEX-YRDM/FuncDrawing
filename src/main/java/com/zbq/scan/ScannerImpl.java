package com.zbq.scan;



import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;



/**
 * @author zbq
 * @date 2022/12/13 22:38
 */
public class ScannerImpl implements Scanner{
    final Token[] TokenTab={
            new Token(Token_Type.CONST_ID,"PI",3.1415926,null,new Position(0,0)),
            new Token(Token_Type.CONST_ID,"E",2.71828,null,new Position(0,0)),
            new Token(Token_Type.T,"T",0.0,null,new Position(0,0)),
            new Token(Token_Type.FUNC,"SIN", 0.0,MathType.SIN,new Position(0,0)),
            new Token(Token_Type.FUNC,"COS", 0.0,MathType.COS,new Position(0,0)),
            new Token(Token_Type.FUNC,"TAN", 0.0,MathType.TAN,new Position(0,0)),
            new Token(Token_Type.FUNC,"LN", 0.0,MathType.LN,new Position(0,0)),
            new Token(Token_Type.FUNC,"EXP", 0.0,MathType.EXP,new Position(0,0)),
            new Token(Token_Type.FUNC,"SQRT", 0.0,MathType.SQRT,new Position(0,0)),
            new Token(Token_Type.ORIGIN,"ORIGIN",0.0,null,new Position(0,0)),
            new Token(Token_Type.SCALE,"SCALE",0.0,null,new Position(0,0)),
            new Token(Token_Type.ROT,"ROT",0.0,null,new Position(0,0)),
            new Token(Token_Type.IS,"IS",0.0,null,new Position(0,0)),
            new Token(Token_Type.FOR,"FOR",0.0,null,new Position(0,0)),
            new Token(Token_Type.FROM,"FROM",0.0,null,new Position(0,0)),
            new Token(Token_Type.TO,"TO",0.0,null,new Position(0,0)),
            new Token(Token_Type.STEP,"STEP",0.0,null,new Position(0,0)),
            new Token(Token_Type.DRAW,"DRAW",0.0,null,new Position(0,0)),
    };
    private String fileName;

    public ScannerImpl(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public ScannerImpl(){}

    LinkedList<Character> chars;

    @Override
    public void readFile() {
        if(getFileName()==null)
            throw new NullPointerException("not found resource file");
        chars=getAllChars(getFileName());
    }

    static Position current_pos=new Position(1,0);
    DfaImpl dfa=InitDfa();
    public char getChar(){
        if(chars.size()==0){
            return (char)-1;
        }
        char ch=chars.getFirst();
        chars.removeFirst();
        if(ch=='\n'){
            current_pos.setLine(current_pos.getLine()+1);
            current_pos.setCol(0);
            return ch;
        }else{
            current_pos.setCol(current_pos.getCol()+1);
            return Character.toUpperCase(ch);
        }
    }
    public DfaImpl InitDfa(){
        T_final_state[] fs={
            new T_final_state(1,Token_Type.ID),
            new T_final_state(2,Token_Type.CONST_ID),
            new T_final_state(3,Token_Type.CONST_ID),
            new T_final_state(4,Token_Type.MUL),
            new T_final_state(5,Token_Type.POWER),
            new T_final_state(6,Token_Type.DIV),
            new T_final_state(7,Token_Type.MINUS),
            new T_final_state(8,Token_Type.PLUS),
            new T_final_state(9,Token_Type.COMMA),
            new T_final_state(10,Token_Type.SEMICO),
            new T_final_state(11,Token_Type.L_BRACKET),
            new T_final_state(12,Token_Type.R_BRACKET),
            new T_final_state(13,Token_Type.COMMENT),
            new T_final_state(-1,Token_Type.ERRTOKEN)
        };
        LinkedList<Character> Letter=new LinkedList<>();
        LinkedList<Character> Digit=new LinkedList<>();
        for(char ch='0';ch<='9';ch++){
            Digit.add(ch);
        }
        for(char ch='a';ch<='z';ch++){
            Letter.add(ch);
        }
        for(char ch='A';ch<='Z';ch++){
            Letter.add(ch);
        }
        T_state_trans[] st={
                new T_state_trans(0,Letter,1),
                new T_state_trans(0,Digit,2),
                new T_state_trans(0,'*',4),
                new T_state_trans(0,'/',6),
                new T_state_trans(0,'-',7),
                new T_state_trans(0,'+',8),
                new T_state_trans(0,',',9),
                new T_state_trans(0,';',10),
                new T_state_trans(0,'(',11),
                new T_state_trans(0,')',12),
                new T_state_trans(1,Letter,1),
                new T_state_trans(1,Digit,1),
                new T_state_trans(2,Digit,2),
                new T_state_trans(2,'.',3),
                new T_state_trans(3,Digit,3),
                new T_state_trans(4,'*',5),
                new T_state_trans(6,'/',13),
                new T_state_trans(7,'-',13),
        };
        return new DfaImpl(0,fs,st);
    }
    public int is_space(char c){
        if(c<0||c>0x7e){
            return 0;
        }
        if(Character.isWhitespace(c)) return 0;
        return 1;
    }
    @Override
    public Token getToken() {
        int first_char;
        int last_state=-1;
        Position where=null;
        Token theToken=new Token();
        int to_bo_continue;
        if(chars.size()==0){
            return null;
        }

        do{
            first_char=pre_process(theToken);
            if(first_char==65535){
                return null;
            }
            theToken.setWhere(new Position(current_pos.getLine(),current_pos.getCol()));
            last_state=scan_move(theToken,first_char);

            to_bo_continue=post_process(theToken,last_state);
        }while(to_bo_continue!=0);

        return theToken;
    }

    public LinkedList<Character> getAllChars(String fileName){
        FileReader fl=null;
        LinkedList<Character> chars=null;
        try {
            System.out.println("读取文件中...");
            fl=new FileReader(fileName);
            chars=new LinkedList<>();
            int m;
            while((m=fl.read())!=-1){
                chars.add((char)m);
                System.out.print((char)m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(fl!=null){
                try {
                    fl.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return chars;
    }

    public Token JudgeKeyToken(String str){
        for(Token token: TokenTab){
            if(str.equals(token.getLexeme())){
                return token;
            }
        }
        return new Token(Token_Type.ERRTOKEN);
    }

    public int pre_process(Token pToken){
        pToken.setLexeme("");
        char current_char;
        while(true){
            current_char=getChar();
            if(is_space(current_char)!=0) break;
            if(current_char==65535) return 65535;
        }
        return current_char;
    }

    int scan_move(Token pToken,int first_char){
        int current_state,next_state;
        int current_char;

        current_char=first_char;
        current_state=dfa.get_start_state();
        while(true){
            next_state=dfa.move(current_state,(char)current_char);
            if(next_state<0){
                if(pToken.getLexeme().equals("")){

                }else{
                    if(current_char!=65535){
                        chars.addFirst((char)current_char);
                        current_pos.setCol(current_pos.getCol()-1);
                    }
                }
                break;
            }
            pToken.setLexeme(pToken.getLexeme()+(char)current_char);
            current_state=next_state;
            current_char=getChar();
            //if(chars.size()==0) break;
        }

        return current_state;
    }

    int post_process(Token pToken,int last_state){
        int to_bo_continue=0;
        Token_Type tk=dfa.state_is_final(last_state);
        switch(tk){
            case ID:
            {
                Token id=JudgeKeyToken(pToken.getLexeme());
                if(id.getType()==Token_Type.ERRTOKEN)
                    pToken.setType(Token_Type.ERRTOKEN);
                else{
                    pToken.setType(id.getType());
                    pToken.setFuncType(id.getFuncType());
                    pToken.setValue(id.getValue());
                }

            }
            break;
            case CONST_ID:
                Token id=JudgeKeyToken(pToken.getLexeme());
                pToken.setType(tk);
                if(id.getType()==Token_Type.ERRTOKEN)
                    pToken.setValue(Double.valueOf(pToken.getLexeme()));
                else{
                    pToken.setValue(id.getValue());
                }
                break;
            case COMMENT:
            {
                int c;
                while(true){
                    c=getChar();
                    if(c=='\n') break;
                }
            }
            to_bo_continue=1;
            break;
            default:
                pToken.setType(tk);
                break;
        }
        return to_bo_continue;
    }

    /*public static void main(String[] args) {
        Token token;
        Scanner scanner=new ScannerImpl("F:\\JavaPractice\\FuncDraw\\src\\main\\resources\\test.txt");
        scanner.readFile();
        System.out.println("记号类别        字符串         常数值         函数      位置");
        System.out.println("___________________________________________________________");
        while((token=scanner.getToken())!=null){
                System.out.printf("%-10s %12s %12f %12s (%d,%d)\n", token.getType(),token.getLexeme(),token.getValue(), token.getFuncType(),token.getWhere().getLine(),token.getWhere().getCol());
        }
        System.out.println("___________________________________________________________");

    }*/

}
