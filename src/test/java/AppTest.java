import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class AppTest {

    public static void main(String[] args) {
      Result result1 =JUnitCore.runClasses(PdfManagerTest.class);
      Result result2=JUnitCore.runClasses(IndexManagerTest.class);
      System.out.println("Failiure count result 1: "+result1.getFailureCount());
      System.out.println(result1.getFailures());
      System.out.println("Failiure count result 2: "+result2.getFailureCount());
      System.out.println(result2.getFailures());

    }

}
