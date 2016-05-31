package audit;

import audit.part.Connect;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Created by jz36 on 25.05.16.
 */
public class Main {

    public static void main( String [] args) throws IOException, ParseException, InterruptedException {
        //View audit = new View();
        Connect conn = new Connect();
        conn.getReindexSite("habrahabr.ru");
        System.out.println("StopSystem");
        Thread.sleep(5000);
        System.out.println("StartSystem");
        System.out.println(conn.getIndex());
    }
}
