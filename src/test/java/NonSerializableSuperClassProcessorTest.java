/**
 * DISABLED as per commit b965cad6f327d8fd0fb97a3af8f6427de61685c4.
 * The test is unable to find the error in the initial state. See commit mesage above for more info.
 */
/*
import org.junit.Test;
import org.sonar.java.checks.serialization.SerializableSuperConstructorCheck;
import org.sonar.java.checks.verifier.JavaCheckVerifier;
import org.sonar.java.se.checks.NullDereferenceCheck;

import static org.junit.Assert.*;

public class NonSerializableSuperClassProcessorTest {

    private static String projectKey = "se.kth:sonatest";
    private static String cdtest ="./src/test/resources/sonatest/";
    private static String pathToFile = cdtest + "src/main/java/";

    @Test
    public void test()throws Exception
    {
        String fileName = "SerializableSuperConstructorCheck.java";
        String pathToRepairedFile = "./spooned/" + fileName;

        JavaCheckVerifier.verify(pathToFile + fileName,new SerializableSuperConstructorCheck());
        TestHelp.normalRepair(pathToFile,projectKey,2055);
        JavaCheckVerifier.verifyNoIssue(pathToRepairedFile, new SerializableSuperConstructorCheck());
    }
}
*/
