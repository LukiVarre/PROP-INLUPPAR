package prop.assignment0;

class AssignmentNode implements INode {
    private ExpressionNode expr = null;
    private String id;

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
        builder.append(id + " = " + expr + ";");
    }
}

class ExpressionNode implements INode {
    private TermNode term = null;
    private ExpressionNode expr = null;

    public ExpressionNode(Tokenizer tk) {
        if (tk.current().equals("ExpressionNode")) {
            term = new TermNode(tk);
            expr = new ExpressionNode(tk);
        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append(term + "[(" + " + " + ")" + expr + "]");
    }
}

class TermNode implements INode {
    private FactorNode factor = null;
    private TermNode term = null;

    public TermNode(Tokenizer tk) {
        if (tk.current().equals("TermNode")) {
            factor = new FactorNode(tk);
            term = new TermNode(tk);
        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append(factor + "[(" + " * " + ")" + term + "]");
    }
}

class FactorNode implements INode {
    private ExpressionNode expr = null;
    private int number;

    public FactorNode(Tokenizer tk) {
        if (tk.current().equals("FactorNode")) {
            expr = new ExpressionNode(tk);
            if (tk.current().equals(Token.INT_LIT))
                number = Integer.parseInt(tk.current().toString());
        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append(number + "(" + expr + ")");

    }
}

