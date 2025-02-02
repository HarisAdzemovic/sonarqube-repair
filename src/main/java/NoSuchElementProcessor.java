import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetExpression;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtThrow;
import spoon.reflect.declaration.CtElement;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NoSuchElementProcessor extends AbstractProcessor<CtMethod> {
    public NoSuchElementProcessor(String projectKey) throws Exception {
        ParseAPI.parse(2272,"",projectKey);
    }

    /**
     *
     * @param candidate - Every method of the scanned file
     * @return Whether the method should have the transformation applied to it.
     *
     * We want to process the next() method of any custom Iterator class which doesn't already throw the correct error.
     */
    @Override
    public boolean isToBeProcessed(CtMethod candidate) {
        CtType iteratorInterface = getFactory().Interface().get(Iterator.class);
        CtMethod next = (CtMethod) iteratorInterface.getMethodsByName("next").get(0);
        if(candidate.isOverriding(next)){
            // If next() in the Iterator class is overridden, check if the correct error is thrown.
            Iterator<CtElement> statements = candidate.getBody().descendantIterator();
            CtTypeReference<?> noSuchElementTypeRef = getFactory().Code().createCtTypeReference(java.util.NoSuchElementException.class);
            while (statements.hasNext()) {
                CtElement element = statements.next();
                // If a throw is found, check that it is the correct one
                if(element instanceof CtThrow) {
                    for(CtTypeReference typeRef: element.getReferencedTypes()){
                        if(typeRef.equals(noSuchElementTypeRef)){
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void process(CtMethod method) {
        CtIf anIf = getFactory().Core().createIf();
        CtCodeSnippetExpression expr = getFactory().Core().createCodeSnippetExpression();
        expr.setValue("!hasNext()");
        anIf.setCondition(expr);
        CtType noSuchElementClass = getFactory().Class().get(NoSuchElementException.class);
        CtThrow throwStmnt = getFactory().createCtThrow("");
        throwStmnt.setThrownExpression(
                ((CtExpression<? extends Throwable>)
                        getFactory().createConstructorCall(noSuchElementClass.getReference(), new CtExpression[]{})
                ));
        CtBlock block = getFactory().Core().createBlock();
        block.addStatement(throwStmnt);
        anIf.setThenStatement(block);
        method.getBody().getStatements().get(0).insertBefore(anIf);
    }
}