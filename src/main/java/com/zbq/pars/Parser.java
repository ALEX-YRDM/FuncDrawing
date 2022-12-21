package com.zbq.pars;

import com.zbq.scan.Scanner;
import com.zbq.scan.Token_Type;

import java.rmi.server.ExportException;

/**
 * @author zbq
 * @date 2022/12/18 13:33
 */
public interface Parser {
    void initParser(String fileName);
    void parser();
}
