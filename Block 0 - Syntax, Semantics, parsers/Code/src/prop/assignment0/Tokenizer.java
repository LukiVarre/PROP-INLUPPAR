package prop.assignment0;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Tokenizer implements ITokenizer {
    private Scanner scanner = null;
    private Lexeme nextLex = null;
    private Lexeme currentLex = null;
    private Map<Character, Token> symbols = null;

    public Tokenizer() {
        symbols = new HashMap<Character, Token>();
        symbols.put(';', Token.SEMICOLON);
        symbols.put('=', Token.ASSIGN_OP);
        symbols.put('+', Token.ADD_OP);
        symbols.put('-', Token.SUB_OP);
        symbols.put('*', Token.MULT_OP);
        symbols.put('/', Token.DIV_OP);
        symbols.put('(', Token.LEFT_PAREN);
        symbols.put(')', Token.RIGHT_PAREN);
        symbols.put('[', Token.LEFT_CURLY);
        symbols.put(']', Token.RIGHT_CURLY);
        symbols.put(null, Token.NULL);
    }

    private void consumeWhiteSpace() throws IOException {
        while (Character.isWhitespace(scanner.current()))
            scanner.moveNext();
    }

    private Lexeme extractLexeme() throws IOException, TokenizerException {
        consumeWhiteSpace();
        Character ch = scanner.current();
        StringBuilder stringBuilder = new StringBuilder();
        String lexeme;

        if (ch == Scanner.EOF)
            return new Lexeme(ch, Token.EOF);
        else if (Character.isLetter(ch)) {
            while (Character.isLetter(scanner.current())) {
                stringBuilder.append(scanner.current());
                scanner.moveNext();
            }
            lexeme = stringBuilder.toString();
            return new Lexeme(lexeme, Token.IDENT);
        } else if (Character.isDigit(ch)) {
            while (Character.isDigit(scanner.current())) {
                stringBuilder.append(scanner.current());
                scanner.moveNext();
            }
            lexeme = stringBuilder.toString();
            return new Lexeme(lexeme, Token.INT_LIT);
        } else if (symbols.containsKey(ch)) {
            scanner.moveNext();
            return new Lexeme(ch, symbols.get(ch));
        }
        // TODO: Fråga om return type
        // TODO: Fråga om ab ba ska skrivas ut eller bara ab
        // return new Lexeme(ch, symbols.get(ch));
        //  || Character.isSpaceChar(ch)
        //  || Character.isSpaceChar(scanner.current())
        return null;
    }

    /**
     * Moves current to the next token in the stream.
     */
    @Override
    public void moveNext() throws IOException, TokenizerException {
        if (scanner == null)
            throw new IOException("No open file.");
        currentLex = nextLex;
        if (nextLex.token() != Token.EOF)
            nextLex = extractLexeme();
    }

    /**
     * Opens a file for tokenizing.
     */
    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        scanner = new Scanner();
        scanner.open(fileName);
        scanner.moveNext();
        nextLex = extractLexeme();
    }

    /**
     * Closes the file and releases any system resources associated with it.
     */
    @Override
    public void close() throws IOException {
        if (scanner != null)
            scanner.close();
    }

    /**
     * Returns the current token in the stream.
     */
    @Override
    public Lexeme current() {
        return currentLex;
    }

    public static void main(String[] args) {
        try {
            Tokenizer t = new Tokenizer();
            t.open("./program1.txt");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
