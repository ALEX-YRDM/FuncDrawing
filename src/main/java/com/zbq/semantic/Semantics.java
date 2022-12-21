package com.zbq.semantic;

import com.zbq.pars.ExprNode;

import java.util.LinkedList;

/**
 * @author zbq
 * @date 2022/12/20 12:11
 */
public interface Semantics {
    Point setOrigin(ExprNode x, ExprNode y);
    Point setScale(ExprNode x,ExprNode y);

    Double setRotate(ExprNode root);
    LinkedList<Point> DrawLoop(ExprNode start,
                        ExprNode end,
                        ExprNode step,
                        ExprNode x,
                        ExprNode y);
}
