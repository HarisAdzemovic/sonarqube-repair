import spoon.processing.AbstractProcessor;
import spoon.reflect.code.*;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtExecutableReference;
import java.util.Arrays;

public class ArrayToStringProcessor extends AbstractProcessor<CtInvocation<?>> {

    final String TOSTRING = "toString";
    final String HASHCODE = "hashCode";

    public ArrayToStringProcessor(String projectKey) throws Exception {
        ParseAPI.parse(2116,"",projectKey);
    }

    @Override
    public boolean isToBeProcessed(CtInvocation<?> candidate)
    {
        if(candidate==null||candidate.getTarget()==null)
        {
            return false;
        }
        if(candidate.getTarget().getType().isArray()){
            if(candidate.getExecutable().getSignature().equals(TOSTRING + "()") ||
                    (candidate.getExecutable().getSignature().equals(HASHCODE + "()"))){
                return true;
            }
        }
        return false;
    }
    @Override
    public void process(CtInvocation<?> element) {
        CtExpression prevTarget = element.getTarget();
        CtCodeSnippetExpression newTarget = getFactory().Code().createCodeSnippetExpression("Arrays");
        CtType arraysClass = getFactory().Class().get(Arrays.class);
        CtMethod method = null;
        if(element.getExecutable().getSignature().equals(HASHCODE + "()")){
            method = (CtMethod) arraysClass.getMethodsByName(HASHCODE).get(0);
        } else if(element.getExecutable().getSignature().equals(TOSTRING + "()")){
            method = (CtMethod) arraysClass.getMethodsByName(TOSTRING).get(0);
        } else {
            System.err.println("Unhandled case. Something went wrong.");
        }
        CtExecutableReference refToMethod = getFactory().Executable().createReference(method);
        CtInvocation newInvocation  = getFactory().Code().createInvocation(newTarget, refToMethod, prevTarget);
        element.replace(newInvocation);
    }
}