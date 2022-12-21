package com.zbq.pars;



import com.zbq.scan.*;
import com.zbq.semantic.SemanticImpl;
import com.zbq.semantic.Semantics;
import com.zbq.ui.Draw;
import com.zbq.semantic.DrawingData;


/**
 * @author zbq
 * @date 2022/12/18 13:45
 */
public class ParserImpl implements Parser{
    private T_parser_stuff parser_data;

    private Scanner scanner;

    private Semantics semantics;

    private DrawingData drawingData;

    private Draw maindraw;

    @Override
    public void initParser(String fileName) {
        scanner=new ScannerImpl(fileName);
        scanner.readFile();
        semantics=new SemanticImpl();
        drawingData=new DrawingData();
        parser_data=new T_parser_stuff();
        maindraw=new Draw();
    }

    @Override
    public void parser() {
        FetchToken();
        program();
    }


    void FetchToken(){
        parser_data.setCurr_token(scanner.getToken());
        if(parser_data.getCurr_token()==null) return;
        if(parser_data.getCurr_token().getType()==Token_Type.ERRTOKEN) SyntaxError(1);
    }
    void MatchToken(Token_Type expected){
        if(parser_data.getCurr_token().getType()!=expected)
            SyntaxError(2);
        else
        {
            int i;
            for(i=0;i<parser_data.getIndent();i++){
                System.out.print(" ");
            }
            System.out.println("matchtoken "+parser_data.getCurr_token().getLexeme());
        }
        FetchToken();
    }
    void SyntaxError(int case_of) {
        switch (case_of){
            case 1:
                System.out.println("line: "+parser_data.getCurr_token().getWhere().getLine()+"非法单词 "+parser_data.getCurr_token().getLexeme());
                break;
            case 2:
                System.out.println("line: "+parser_data.getCurr_token().getWhere().getLine()+" "+parser_data.getCurr_token().getLexeme()+" 不是预期记号");
                break;
        }
        System.exit(1);
    }
    void printSyntaxTree(ExprNode root,int indent){}

    //递归下降子程序
    void program(){
        enter("program");
        //while(parser_data.getCurr_token().getType()!=Token_Type.NONTOKEN){
        while(parser_data.getCurr_token()!=null){
            statement();
            MatchToken(Token_Type.SEMICO);
        }
        quit("program");
        maindraw.show();
    }
    void statement(){
        enter("statement");
        switch(parser_data.getCurr_token().getType()){
            case ORIGIN: origin_statement();break;
            case SCALE: scale_statement();break;
            case ROT:rot_statement();break;
            case FOR:for_statement();break;
            default:SyntaxError(2);
        }
        quit("statement");
    }
    void for_statement(){
        enter("for_statement");

        MatchToken(Token_Type.FOR);
        MatchToken(Token_Type.T);
        MatchToken(Token_Type.FROM);
            parser_data.setStart(expression());
            tree_trace(parser_data.getStart());
        MatchToken(Token_Type.TO);
            parser_data.setEnd(expression());
            tree_trace(parser_data.getEnd());
        MatchToken(Token_Type.STEP);
            parser_data.setStep(expression());
            tree_trace(parser_data.getStep());
        MatchToken(Token_Type.DRAW);
        MatchToken(Token_Type.L_BRACKET);
            parser_data.setX(expression());
            tree_trace(parser_data.getX());
        MatchToken(Token_Type.COMMA);
            parser_data.setY(expression());
            tree_trace(parser_data.getY());
        MatchToken(Token_Type.R_BRACKET);
        drawingData.setPoints(semantics.DrawLoop(parser_data.getStart(),parser_data.getEnd(),parser_data.getStep(),
                parser_data.getX(),parser_data.getY()));
        maindraw.addData(drawingData);
        quit("for_statement");

    }
    void origin_statement(){
        enter("origin_statement");

        MatchToken(Token_Type.ORIGIN);
        MatchToken(Token_Type.IS);
        MatchToken(Token_Type.L_BRACKET);
        parser_data.setX(expression());
        tree_trace(parser_data.getX());
        MatchToken(Token_Type.COMMA);
        parser_data.setY(expression());
        tree_trace(parser_data.getY());
        MatchToken(Token_Type.R_BRACKET);
        drawingData.setOrigin(semantics.setOrigin(parser_data.getX(),parser_data.getY()));
        quit("origin_statement");
    }
    void rot_statement(){
        enter("rot_statement");

        MatchToken(Token_Type.ROT);
        MatchToken(Token_Type.IS);
        parser_data.setAngle(expression());
        tree_trace(parser_data.getAngle());
        drawingData.setRot(semantics.setRotate(parser_data.getAngle()));
        quit("rot_statement");
    }
    void scale_statement(){
        enter("scale_statement");

        MatchToken(Token_Type.SCALE);
        MatchToken(Token_Type.IS);
        MatchToken(Token_Type.L_BRACKET);
        parser_data.setX(expression());
        tree_trace(parser_data.getX());
        MatchToken(Token_Type.COMMA);
        parser_data.setY(expression());
        tree_trace(parser_data.getY());
        MatchToken(Token_Type.R_BRACKET);
        drawingData.setScale(semantics.setScale(parser_data.getX(),parser_data.getY()));
        quit("scale_statement");
    }
    ExprNode expression(){
        ExprNode left,right;
        Token_Type lastType;

        enter("expression");
        left=term();
        while(parser_data.getCurr_token().getType()==Token_Type.PLUS||parser_data.getCurr_token().getType()==Token_Type.MINUS){
            lastType=parser_data.getCurr_token().getType();
            MatchToken(lastType);
            right=term();
            left=MakeExprNode(lastType,left,right);
        }
        quit("expression");
        return left;
    }
    ExprNode term(){
        ExprNode left,right;
        Token_Type lastType;
        left=factor();
        while(parser_data.getCurr_token().getType()==Token_Type.MUL||parser_data.getCurr_token().getType()==Token_Type.DIV){
            lastType=parser_data.getCurr_token().getType();
            MatchToken(lastType);
            right=factor();
            left=MakeExprNode(lastType,left,right);
        }
        return left;
    }
    ExprNode factor(){
        ExprNode left,right;
        if(parser_data.getCurr_token().getType()==Token_Type.PLUS){
            MatchToken(Token_Type.PLUS);
            right=factor();
        }else if(parser_data.getCurr_token().getType()==Token_Type.MINUS){
            MatchToken(Token_Type.MINUS);
            right=factor();
            left=new ExprNode();
            left.setOpCode(Token_Type.CONST_ID);
            left.setCaseConst(0.0);
            right=MakeExprNode(Token_Type.MINUS,left,right);
        }else{
            right=component();
        }
        return right;
    }
    ExprNode component(){
        ExprNode left,right;
        left=atom();

        if(parser_data.getCurr_token().getType()==Token_Type.POWER){
            MatchToken(Token_Type.POWER);
            right=component();
            left=MakeExprNode(Token_Type.POWER,left,right);

        }
        return left;
    }
    ExprNode atom(){
        Token t=parser_data.getCurr_token();
        ExprNode ptr=null,tmp=null;
        switch(parser_data.getCurr_token().getType()){
            case CONST_ID:
                MatchToken(Token_Type.CONST_ID);
                ptr=MakeExprNode(Token_Type.CONST_ID,t.getValue());
                break;
            case T:
                MatchToken(Token_Type.T);
                ptr=MakeExprNode(Token_Type.T);
                break;
            case FUNC:
                MatchToken(Token_Type.FUNC);
                MatchToken(Token_Type.L_BRACKET);
                tmp=expression();
                ptr=MakeExprNode(Token_Type.FUNC,t.getFuncType(),tmp);
                MatchToken(Token_Type.R_BRACKET);
                break;
            case L_BRACKET:
                MatchToken(Token_Type.L_BRACKET);
                ptr=expression();
                MatchToken(Token_Type.R_BRACKET);
                break;
            default:
                SyntaxError(2);
        }
        return ptr;
    }
    ExprNode MakeExprNode(Token_Type opcode,ExprNode left,ExprNode right){
        ExprNode ptr=new ExprNode();
        ptr.setOpCode(opcode);
        ptr.setLeft(left);
        ptr.setRight(right);
        return ptr;
    }
    ExprNode MakeExprNode(Token_Type opcode,double value){
        ExprNode ptr=new ExprNode();
        ptr.setOpCode(opcode);
        ptr.setCaseConst(value);
        return ptr;
    }
    ExprNode MakeExprNode(Token_Type opcode){
        ExprNode ptr=new ExprNode();
        ptr.setOpCode(opcode);
        ptr.setCaseParmPtr(parser_data.getParameter());
        return ptr;
    }
    ExprNode MakeExprNode(Token_Type opcode, MathType mtype,ExprNode node){
        ExprNode ptr=new ExprNode();
        ptr.setOpCode(opcode);
        ptr.setFunc(mtype);
        ptr.setChild(node);
        return ptr;
    }
    //跟踪调试
    void enter(String str){
        int i;
        for(i=0;i<parser_data.getIndent();i++) System.out.print(" ");
        System.out.println("enter in "+str);
        parser_data.setIndent(parser_data.getIndent()+2);
    }
    void quit(String str){
        int i;
        parser_data.setIndent(parser_data.getIndent()-2);
        for(i=0;i<parser_data.getIndent();i++) System.out.print(" ");
        System.out.println("quit from "+str);
    }
    //void call_match(String str){}
    void tree_trace(ExprNode x){}

}
