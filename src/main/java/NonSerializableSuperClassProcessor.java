/**
 * DISABLED as per commit b965cad6f327d8fd0fb97a3af8f6427de61685c4.
 * The test is unable to find the error in the initial state. See commit message above for more info.
 */
/*
/*
import org.json.JSONArray;
import org.json.JSONException;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtStatement;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtConstructor;
import spoon.reflect.declaration.ModifierKind;
import spoon.reflect.reference.CtTypeReference;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class NonSerializableSuperClassProcessor extends AbstractProcessor<CtClass> {

    private JSONArray jsonArray;//array of JSONObjects, each of which is a bug
    private Set<Bug> SetOfBugs;//set of bugs, corresponding to jsonArray
    private Set<Long> SetOfLineNumbers;//set of line numbers corresponding to bugs, just for efficiency
    private Set<String> SetOfFileNames;//-----
    private Bug thisBug;               //current bug. This is set inside isToBeProcessed function
    private String thisBugName;        //name (message) of current thisBug.
    private static int inp=-1;
    private static boolean defolt = false;
    public NonSerializableSuperClassProcessor(String projectKey,boolean defolt1) throws Exception {
        jsonArray= ParseAPI.parse(2055,"",projectKey);
        SetOfBugs = Bug.createSetOfBugs(this.jsonArray);
        SetOfLineNumbers=new HashSet<Long>();
        SetOfFileNames=new HashSet<String>();
        thisBug=new Bug();
        for(Bug bug:SetOfBugs)
        {
            SetOfLineNumbers.add(bug.getLineNumber());
            SetOfFileNames.add(bug.getFileName());
        }
        if(!defolt1)
        {
            Scanner sc = new Scanner(System.in);
            String message = String.format("There are two possible repair for this bug." +
                    " Either add no-argument constructor in super class or remove \"implements Serializable\" " +
                    "from this class. Type 0 for first option and 1 for second.");
            System.out.println(message);
            inp = sc.nextInt();
        }
        else
        {
            defolt = true;
        }
    }

    @Override
    public boolean isToBeProcessed(CtClass element)
    {
        if(element.getSuperclass()==null)
        {
            return false;
        }
        long line =-1;
        String targetName="",fileOfElement="";

        line=(long) element.getPosition().getLine();
        String split1[]=element.getPosition().getFile().toString().split("/");
        fileOfElement=split1[split1.length-1];
        CtClass ct =(CtClass)element.getSuperclass().getTypeDeclaration();
        targetName=ct.getSimpleName();

        if(!SetOfLineNumbers.contains(line)||!SetOfFileNames.contains(fileOfElement))
        {
            return false;
        }
        try {
            thisBug = new Bug();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Bug bug:SetOfBugs)
        {
            if(bug.getLineNumber()!=line||!bug.getFileName().equals(fileOfElement))
            {
                continue;
            }

            String bugName=bug.getName();
            String[] split = bugName.split("\"");
            for(String bugword:split)
            {
                if(targetName.equals(bugword))
                {
                    try
                    {
                        thisBug = new Bug(bug);
                        thisBugName = bugword;
                        return true;
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }
    @Override
    public void process(CtClass element) {

        System.out.println("BUG\n");
        if (defolt || inp == 0) {
            System.out.println("zero selected");
            CtClass ct=element;
            while(true)
            {
                if(ct.getSuperclass()==null)
                    break;
                ct = (CtClass) ct.getSuperclass().getTypeDeclaration();
                if(ct.getSimpleName().equals(thisBugName))
                {
                    break;
                }
            }
            if(!ct.getSimpleName().equals(thisBugName))
            {
                return;
            }
            CtConstructor alreadyPresent = ct.getConstructor();
            if (alreadyPresent != null) {
                return;
            }
            CtConstructor constructor = getFactory().createConstructor();
            CtStatement statement = getFactory().createBlock();
            constructor.setBody(statement);
            constructor.setVisibility(ModifierKind.PUBLIC);
            constructor.setPosition(ct.getPosition());
            ct.addConstructor(constructor);
        } else if (inp == 1) {
            System.out.println("one selected");
            CtTypeReference ctTypeReference = null;

            Set<CtTypeReference<?>> superInterfaces = element.getSuperInterfaces();
            for (CtTypeReference ct : superInterfaces) {
                System.out.println(ct.getQualifiedName());
                if (ct.getQualifiedName().equals("java.io.Serializable")) {
                    ctTypeReference = ct;
                    break;
                }
            }
            if (ctTypeReference != null) {
                element.removeSuperInterface(ctTypeReference);
            }
        }
    }
}
*/