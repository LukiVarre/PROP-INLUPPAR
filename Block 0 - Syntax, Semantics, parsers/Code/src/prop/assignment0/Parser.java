package prop.assignment0;

import java.io.IOException;

public class Parser implements IParser {
    private Tokenizer tk = null;

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        tk = new Tokenizer();
        tk.open("./program1.txt");
        tk.moveNext();
    }

    @Override
    public INode parse() throws IOException, TokenizerException, ParserException {
        if (tk == null)
            throw new IOException("No open file.");

        //TODO: Check if return value is correct
        return new AssignmentNode(tk);
    }

    @Override
    public void close() throws IOException {
        if (tk != null)
            tk.close();
    }
}