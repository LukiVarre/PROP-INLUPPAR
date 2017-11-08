package prop.assignment0;

// TODO: Create constructors for the classes & check if have the correct values
// TODO: Write code for the inherited methods
// TODO: Check if AssignmentNode id should be String or char
class AssignmentNode implements INode {
    ExpressionNode expr = null;
    String id;

    public AssignmentNode(Tokenizer tk) {
        expr = new ExpressionNode(tk);
        if (tk.current().equals(Token.IDENT))
            id = tk.toString();
    }

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

    public ExpressionNode(Tokenizer tk) {
        term = new TermNode(tk);
        expr = new ExpressionNode(tk);
    }

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

    public TermNode(Tokenizer tk) {
        term = new TermNode(tk);
        factor = new FactorNode(tk);
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
    ExpressionNode expr = null;
    String number;

    public FactorNode(Tokenizer tk) {
        expr = new ExpressionNode(tk);
        if (tk.current().equals(Token.INT_LIT))
            number = tk.current().toString();
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}

