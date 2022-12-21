package com.zbq.semantic;

import com.zbq.pars.ExprNode;

import java.util.LinkedList;

/**
 * @author zbq
 * @date 2022/12/20 12:17
 */
public class SemanticImpl implements Semantics{
    private double Origin_x,Origin_y,Scale_x,Scale_y,rot_angle;
    public SemanticImpl(){
        this.Origin_x=0.0;
        this.Origin_y=0.0;
        this.Scale_x=1.0;
        this.Scale_y=1.0;
        this.rot_angle=0;
    }
    double GetExprValue(ExprNode root,double tValue){
        if(root==null) return 0.0;
        switch(root.getOpCode()){
            case PLUS:
                return GetExprValue(root.getLeft(),tValue)+GetExprValue(root.getRight(),tValue);
            case MINUS:
                return GetExprValue(root.getLeft(),tValue)-GetExprValue(root.getRight(),tValue);
            case MUL:
                return GetExprValue(root.getLeft(),tValue)*GetExprValue(root.getRight(),tValue);
            case DIV:
                return GetExprValue(root.getLeft(),tValue)/GetExprValue(root.getRight(),tValue);
            case POWER:
                return Math.pow(GetExprValue(root.getLeft(),tValue),GetExprValue(root.getRight(),tValue));
            case FUNC:
                switch (root.getFunc()){
                    case SIN:
                        return Math.sin(GetExprValue(root.getChild(),tValue));
                    case COS:
                        return Math.cos(GetExprValue(root.getChild(),tValue));
                    case TAN:
                        return Math.tan(GetExprValue(root.getChild(),tValue));
                    case LN:
                        return Math.log(GetExprValue(root.getChild(),tValue));
                    case EXP:
                        return Math.exp(GetExprValue(root.getChild(),tValue));
                    case SQRT:
                        return Math.sqrt(GetExprValue(root.getChild(),tValue));
                }
            case CONST_ID:
                return root.getCaseConst();
            case T:
                return tValue;
            default:
                return 0.0;
        }
    }
    Point CalcPoint(double x,double y){
        double temp=0.0;

        x*=getScale_x();
        y*=getScale_y();

        temp=x*Math.cos(getRot_angle())+y*Math.sin(getRot_angle());
        y=y*Math.cos(getRot_angle())-x*Math.sin(getRot_angle());
        x=temp;

        x+=getOrigin_x();
        y+=getOrigin_y();
        //System.out.println("("+x+","+y+")");

        return new Point(x,y);
    }
    @Override
    public Point setOrigin(ExprNode x, ExprNode y) {
        Origin_x=GetExprValue(x,0);
        Origin_y=GetExprValue(y,0);
        return new Point(GetExprValue(x,0),GetExprValue(y,0));
    }

    @Override
    public Point setScale(ExprNode x, ExprNode y) {
        Scale_x=GetExprValue(x,0);
        Scale_y=GetExprValue(y,0);
        return new Point(GetExprValue(x,0),GetExprValue(y,0));
    }

    @Override
    public Double setRotate(ExprNode root) {
        rot_angle=GetExprValue(root,0);
        return GetExprValue(root,0);
    }

    @Override
    public LinkedList<Point> DrawLoop(ExprNode start, ExprNode end, ExprNode step, ExprNode x, ExprNode y) {
        LinkedList<Point> points=new LinkedList<>();
        System.out.println("start: "+GetExprValue(start,0)+" end: "+GetExprValue(end,0)+" step: "+GetExprValue(step,0));
        for(double i=GetExprValue(start,0);i<=GetExprValue(end,0);i+=GetExprValue(step,0)){
            points.add(CalcPoint(GetExprValue(x,i),GetExprValue(y,i)));
        }
        return points;
    }

    public double getOrigin_x() {
        return Origin_x;
    }

    public void setOrigin_x(double origin_x) {
        Origin_x = origin_x;
    }

    public double getOrigin_y() {
        return Origin_y;
    }

    public void setOrigin_y(double origin_y) {
        Origin_y = origin_y;
    }

    public double getScale_x() {
        return Scale_x;
    }

    public void setScale_x(double scale_x) {
        Scale_x = scale_x;
    }

    public double getScale_y() {
        return Scale_y;
    }

    public void setScale_y(double scale_y) {
        Scale_y = scale_y;
    }

    public double getRot_angle() {
        return rot_angle;
    }

    public void setRot_angle(double rot_angle) {
        this.rot_angle = rot_angle;
    }

}
