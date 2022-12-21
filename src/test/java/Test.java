import com.zbq.pars.Parser;
import com.zbq.pars.ParserImpl;

/**
 * @author zbq
 * @date 2022/12/20 18:12
 */
public class Test {
    public static void main(String[] args) {
            Parser parser=new ParserImpl();
            parser.initParser("F:\\JavaPractice\\FuncDraw\\src\\main\\resources\\test4.txt");
            parser.parser();
    }
}
