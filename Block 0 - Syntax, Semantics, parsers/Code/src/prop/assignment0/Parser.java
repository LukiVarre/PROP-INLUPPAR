package prop.assignment0;

import java.io.IOException;

public class Parser implements IParser {
    private Tokenizer tk;

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        tk = new Tokenizer();
        tk.open(fileName);
        moveNext();
    }

    @Override
    public INode parse() throws IOException, TokenizerException, ParserException {
        if (tk == null)
            throw new IOException("No open file.");
        AssignmentNode assignment = createAssignmentNode();
        return assignment;
    }

    @Override
    public void close() throws IOException {
        if (tk != null)
            tk.close();
    }

    private void moveNext() {
        try {
            tk.moveNext();
        } catch (IOException ioe) {
            System.out.println("IOExceptions: " + ioe);
        } catch (TokenizerException tke) {
            System.out.println("TokenizerException: " + tke);
        }
    }

    private AssignmentNode createAssignmentNode() {
        if (tk.current().token().equals(Token.IDENT)) {
            Lexeme id = tk.current();
            moveNext();
            if (tk.current().token().equals(Token.ASSIGN_OP)) {
                Lexeme assign = tk.current();
                ExpressionNode expr = createExpressionNode();
                if (tk.current().token().equals(Token.SEMICOLON)) {
                    Lexeme semiColon = tk.current();
                    return new AssignmentNode(id, assign, expr, semiColon);
                }
            }
        }
        return null;
    }

    private ExpressionNode createExpressionNode() {
        TermNode term = createTermNode();
        if (tk.current().token().equals(Token.SUB_OP) || tk.current().token().equals(Token.ADD_OP)) {
            Lexeme operand = tk.current();
            ExpressionNode expr = createExpressionNode();
            return new ExpressionNode(term, operand, expr);
        }
        return new ExpressionNode(term);
    }

    private TermNode createTermNode() {
        FactorNode factor = createFactorNode();
        moveNext();
        if (tk.current().token().equals(Token.DIV_OP) || tk.current().token().equals(Token.MULT_OP)) {
            Lexeme operand = tk.current();
            TermNode term = createTermNode();
            return new TermNode(factor, operand, term);
        }
        return new TermNode(factor);
    }

    private FactorNode createFactorNode() {
        moveNext();
        if (tk.current().token().equals(Token.INT_LIT)) {
            Lexeme number = tk.current();
            return new FactorNode(number);
        } else if (tk.current().token().equals(Token.LEFT_PAREN)) {
            Lexeme leftParen = tk.current();
            ExpressionNode expr = createExpressionNode();
            if (tk.current().token().equals(Token.RIGHT_PAREN)) {
                Lexeme rightParen = tk.current();
                return new FactorNode(leftParen, expr, rightParen);
            }
        }
        return null;
    }

    private void tabs(StringBuilder builder, int tabs) {
        for (int i = 0; i < tabs; i++) {
            builder.append("\t");
        }
    }

    class AssignmentNode implements INode {
        private ExpressionNode expr;
        private Lexeme id;
        private Lexeme assign;
        private Lexeme semi;

        public AssignmentNode(Lexeme id, Lexeme assign, ExpressionNode expr, Lexeme semi) {
            this.id = id;
            this.assign = assign;
            this.expr = expr;
            this.semi = semi;
        }

        @Override
        public Object evaluate(Object[] args) throws Exception {
            return null;
        }

        @Override
        public void buildString(StringBuilder builder, int tabs) {
            builder.append("AssignmentNode\n");
            tabs(builder, tabs + 1);
            builder.append(id.toString() + "\n");
            tabs(builder, tabs + 1);
            builder.append(assign.toString() + "\n");
            tabs(builder, tabs + 1);
            expr.buildString(builder, tabs + 1);
            tabs(builder, tabs + 1);
            builder.append(semi + "\n");
        }
    }

    class ExpressionNode implements INode {
        private TermNode term = null;
        private ExpressionNode expr = null;
        private Lexeme operator;

        public ExpressionNode(TermNode term, Lexeme operator, ExpressionNode expr) {
            this.term = term;
            this.expr = expr;
            this.operator = operator;
        }

        public ExpressionNode(TermNode term) {
            this.term = term;
        }

        @Override
        public Object evaluate(Object[] args) throws Exception {
            return null;
        }

        @Override
        public void buildString(StringBuilder builder, int tabs) {
            tabs(builder, tabs);
            builder.append("ExpressionNode\n");
            term.buildString(builder, tabs + 1);
            if (operator != null) {
                tabs(builder, tabs + 1);
                builder.append(operator + "\n");
                expr.buildString(builder, tabs + 1);
            }
        }
    }

    class TermNode implements INode {
        private FactorNode factor = null;
        private TermNode term = null;
        private Lexeme operand;

        public TermNode(FactorNode factor, Lexeme operand, TermNode term) {
            this.factor = factor;
            this.term = term;
            this.operand = operand;
        }

        public TermNode(FactorNode factor) {
            this.factor = factor;
        }

        @Override
        public Object evaluate(Object[] args) throws Exception {
            return null;
        }

        @Override
        public void buildString(StringBuilder builder, int tabs) {
            tabs(builder, tabs);
            builder.append("TermNode\n");
            factor.buildString(builder, tabs + 1);
            if (operand != null) {
                tabs(builder, tabs + 1);
                builder.append(operand + "\n");
                term.buildString(builder, tabs + 1);
            }
        }
    }

    class FactorNode implements INode {
        private ExpressionNode expr = null;
        private Lexeme number, leftParen, rightParen;

        public FactorNode(Lexeme number) {
            this.number = number;
        }

        public FactorNode(Lexeme leftParen, ExpressionNode expr, Lexeme rightParen) {
            this.leftParen = leftParen;
            this.expr = expr;
            this.rightParen = rightParen;
        }

        @Override
        public Object evaluate(Object[] args) throws Exception {
            return null;
        }

        @Override
        public void buildString(StringBuilder builder, int tabs) {
            tabs(builder, tabs);
            builder.append("FactorNode\n");
            tabs(builder, tabs + 1);
            if (number != null) {
                builder.append(number + "\n");
            } else if (expr != null) {
                builder.append(leftParen + "\n");
                expr.buildString(builder, tabs + 1);
                tabs(builder, tabs + 1);
                builder.append(rightParen + "\n");
            }
        }
    }
}