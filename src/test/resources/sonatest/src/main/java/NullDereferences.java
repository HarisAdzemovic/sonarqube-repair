/**
 * DISABLED as per commit b965cad6f327d8fd0fb97a3af8f6427de61685c4
 * The processor is non-functioning. See commit message above for more info.
 */
/*
/*
import java.io.*;

public class NullDereferences
{

    public void Ndrf()
    {
        String str=null,a=null,b=null;
        if(a == null && (b != null || b.length()>0)){} // Noncompliant
        if((str) == null && str.length() == 0){} 
        if((str == null) && str.length() == 0){}
    }
    private String extractVariable(String value) {
        if (value != null && value.startsWith("$")) {
            value = getProperty(value.substring(2, value.length() - 1));
        }
        return value;
    }
    int getSourceVersion() {
        String javaVersion = getProperty("java.version");
        if (javaVersion != null) {
            return Integer.parseInt(extractVariable(javaVersion).substring(2));// Noncompliant
        }
        javaVersion = getProperty("java.src.version");
        if (javaVersion != null) {
            return Integer.parseInt(extractVariable(javaVersion).substring(2));// Noncompliant
        }
        javaVersion = getProperty("maven.compiler.source");
        if (javaVersion != null) {
            return Integer.parseInt(extractVariable(javaVersion).substring(2));// Noncompliant
        }
        javaVersion = getProperty("maven.compile.source");
        if (javaVersion != null) {
            return Integer.parseInt(extractVariable(javaVersion).substring(2));// Noncompliant
        }
        // return the current compliance level of spoon
        int x=5;
        return x*x;
    }
    private String getProperty(String key) {
        String value = extractVariable("hello");
        if (value == null) {
            return null;
        }
        return value;
    }
    @CheckForNull
    private String getName(){
        return null;
    }

    public boolean isNameEmpty() {
        return getName().length() == 0; // Noncompliant; the result of getName() could be null, but isn't null-checked
    }

    private void nullCheck(int s)
    {
        if(s == null){
            System.out.println(s.toString()); // Noncompliant;
        }
    }


}
*/
