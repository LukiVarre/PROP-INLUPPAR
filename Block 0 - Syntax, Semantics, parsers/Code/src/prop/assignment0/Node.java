package prop.assignment0;

// TODO: Create constructors for the classes & check if have the correct values
// TODO: Write code for the inherited methods
// TODO: Check if AssignmentNode id should be String or char
class AssignmentNode implements INode {
    ExpressionNode expr = null;
    String id;

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}

class ExpressionNode implements INode {
    TermNode term = null;
    ExpressionNode expr = null;

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}

class TermNode implements INode {
    FactorNode factor = null;
    TermNode term = null;

    public TermNode() {
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}

class FactorNode implements INode {
    int number;
    ExpressionNode expr = null;

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}

