package cosc455001.program1.eallen10;     
/*
COURSE: COSC455001	
SUBMITTER: eallen10	
NAMES: Ethan Allen
*/

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class SyntaxAnalyzer {

    int nodeCount = 0;
    private final LexicalAnalyzer lexer; // The lexer which will provide the tokens

    /**
     * The constructor initializes the terminal literals in their vectors.
     */
    public SyntaxAnalyzer(LexicalAnalyzer lexer) {
        this.lexer = lexer;
    }

    /**
     * Begin analyzing...
     */
    public void analyze() throws ParseException {
        System.out.println("digraph ParseTree {");
        System.out.printf("\t{\"%s\" [label=\"PARSE TREE\" shape=diamond]};%n", nodeCount);

        sentence(nodeCount);

        System.out.println("}");

        // Open the default web browser.
       // openWebGraphViz();
    }

    // This method implements the BNF rule for a sentence from Section 2.2.
    // <S> ::= <NP> <V> <NP> <COMPOUND> <EOS>
    protected void sentence(int from) throws ParseException {
        int node = ++nodeCount;
        log("<S>", from, node);

        NounPhrase(node);
        VerbPhrase(node);
        NounPhrase(node);
        PrepPhrase(node);
        Compound(node);
		if(from > 1)
        	EndOfStatement(node);
    }
	
	void VerbPhrase(int from) throws ParseException {
        int node = ++nodeCount;
        log("<VP>", from, node);
        if (TOKEN.ADVERB == lexer.curToken) {
            AdVerb(node);
        }

        Verb(node);
    }
    
    void AdVerb(int from) throws ParseException {
        log("<AV>", from, ++nodeCount);
        word(nodeCount);

        if (TOKEN.ADVERB != lexer.curToken) {
            raiseException(TOKEN.ADVERB, from);
        }

        lexer.parseNextToken();
    }
    
    void PrepPhrase(int from) throws ParseException {
        int node = ++nodeCount;
        log("<PP>", from, node);

        if (TOKEN.PREPOSITION == lexer.curToken) {
            Preposition(node);
            NounPhrase(node);
        } else {
            log("<EMPTY>", node, ++nodeCount);
        }
    }
    
    void Preposition(int from) throws ParseException {
        int node = ++nodeCount;
        log("<P>", from, node);
        word(node);

        if (TOKEN.PREPOSITION != lexer.curToken) {
            raiseException(TOKEN.PREPOSITION, from);
        }

        lexer.parseNextToken();
    }
    
    void word(int from) throws ParseException
    {
    	int node = ++nodeCount;
    	log(lexer.lexemeBuffer.toString(), from, node);
    }

    // This method implements the BNF rule for a noun phrase from Section 2.2.
    // <NP> ::= <NEW_ARTICLE> <AN>
    void NounPhrase(int from) throws ParseException {
        int node = ++nodeCount;
        log("<NP>", from, node);

        Article(node);
        AdjNoun(node);
    }

    // This method implements the BNF rule for a noun phrase from Section 2.2.
    // <AN> ::= <ADJ> <N> | <N> 
    void AdjNoun(int from) throws ParseException {
        int node = ++nodeCount;
        log("<AN>", from, node);
        if (TOKEN.ADJECTIVE == lexer.curToken) {
            Adjective(node);
        }
        if(TOKEN.COMMA == lexer.curToken)
        {
        	AdjList(node);
        }
		if(TOKEN.NOUN == lexer.curToken)
		{
        	Noun(node);
		}
    }
    
    void AdjList(int from) throws ParseException
    {
    	int node = ++ nodeCount;
    	log("<AL>", from, node);
    	word(node);
    	
    	lexer.parseNextToken();
    	
    	Adjective(node);
    	AdjNoun(node);
    	
    }

    // This method implements the BNF rule for a verb from Section 2.2.
    // <ADJ> ::= fast | slow
    void Adjective(int from) throws ParseException {
        int node = ++nodeCount;
        log("<ADJ>", from, node);
        word(node);

        if (TOKEN.ADJECTIVE != lexer.curToken) {
            raiseException(TOKEN.ADJECTIVE, from);
        }

        lexer.parseNextToken();
    }

    // Allow for infinitely long compond sentances.
    // <COMPOUND> ::= <CONJUNCTION> <S> | <EMPTY>
    void Compound(int from) throws ParseException {
        int node = ++nodeCount;
        log("<COMPOUND>", from, node);
        

        if (TOKEN.CONJUNCTION == lexer.curToken) {
        	word(node);
            Conjunction(node);
        } else {
            log("<EMPTY>", node, ++nodeCount);
        }
    }

    // Handle Conjunctions.
    // <CONJUNCTION> ::= CONJUNCTION <S>
    void Conjunction(int from) throws ParseException {
        log("<CONJUNCTION>", from, ++nodeCount);
        //word(nodeCount);
        lexer.parseNextToken();
        sentence(nodeCount); // recursively parse the next sentence
    }

    // This method implements the BNF rule for a verb from Section 2.2.
    // <V> ::= loves | hates | eats
    void Verb(int from) throws ParseException {
        log("<V>", from, ++nodeCount);
        word(nodeCount);

        if (TOKEN.VERB != lexer.curToken) {
            raiseException(TOKEN.VERB, from);
        }

        lexer.parseNextToken();
    }

    // This method implements the BNF rule for a noun from Section 2.2.
    // <N> ::= dog | cat | rat
    void Noun(int from) throws ParseException {
        log("<N>", from, ++nodeCount);
        word(nodeCount);

        if (TOKEN.NOUN != lexer.curToken) {
            raiseException(TOKEN.NOUN, from);
        }

        lexer.parseNextToken();
    }

    // This method implements the BNF rule for an article from Section 2.2.
    // <A> ::= a | the
    void Article(int from) throws ParseException {
        log("<A>", from, ++nodeCount);
        word(nodeCount);

        if (TOKEN.ARTICLE != lexer.curToken) {
            raiseException(TOKEN.ARTICLE, from);
        }

        lexer.parseNextToken();
    }

    // End of statement, however it's been defined.
    void EndOfStatement(int from) throws ParseException {
    	log("<EOS>", from, ++nodeCount);
        word(nodeCount);
        if (TOKEN.EOS != lexer.curToken) {
            raiseException(TOKEN.EOS, from);
        }
    }

    // Show our progress as we go...
    private void log(String bnf, int from, int to) {

        System.out.printf("\t\"%s\" -> {\"%s\" [label=\"%s\"]};%n", from, to, bnf);
        
    }

    // Handle all of the errors in one place for cleaner parser code.
    private void raiseException(TOKEN expected, int from) throws ParseException {
        final String template = "SYNTAX ERROR: '%s' was expected but '%s' was found.";
        String err = String.format(template, expected.toString(), lexer.lexemeBuffer);

        System.out.printf("\t\"%s\" -> {\"%s\"};%n}%n", from, err);
        throw new ParseException(err);
    }

    /**
     * To automatically open a browser...
     */
    private void openWebGraphViz() {
        System.out.println("\nCopy/Paste the above output into: http://www.webgraphviz.com/\n");

        // Automatically open the default browser with the url:
        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(new URI("http://www.webgraphviz.com/"));
            }
        } catch (IOException | URISyntaxException ex) {
            java.util.logging.Logger.getAnonymousLogger().log(java.util.logging.Level.WARNING, "Could not open browser", ex);
        }
    }
}
