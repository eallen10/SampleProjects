package cosc455001.program1.eallen10;     
    
/*
COURSE: COSC455001	
SUBMITTER: eallen10	
NAMES: Ethan Allen
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public enum TOKEN {

    ARTICLE("a", "the"), // a list of articles
    CONJUNCTION("and", "or"), // A new Conjunction
    NOUN("dog", "cat", "rat", "tree"), // a list of nouns
    VERB("loves", "hates", "eats"), // a list of verbs
    ADJECTIVE("fast", "slow", "delicious"), // Adjectives
    ADVERB("quickly", "silently"),
    PREPOSITION("with", "around", "up"),
    COMMA(","),
    EOS(".", "!"), // End of statement marker. (A character not used for anything else.)
    UNKNOWN(); //An unknown token

    // The lexemes under this token
    private final List<String> lexemeList;

    // The marker symbol for statement termination (if used)
    public static final String EOS_MARKER = ";";

    // Construct the token with the list of lexems
    private TOKEN(String... tokenStrings) {
        lexemeList = new ArrayList<>(tokenStrings.length);
        lexemeList.addAll(Arrays.asList(tokenStrings));
    }

    /**
     * Gets a token from a lexeme by the following rules (in order).
     *
     * 1. If the lexeme is blank return EOF (end of file). 2. If the lexeme
     * corresponds to a known token, return the token. 3. if the lexeme is a
     * sequence of digits, it is a LITERAL. 4. if the lexeme is anything else,
     * it must be an identifier.
     *
     * @param string The Lexeme
     * @return The Token
     */
    public static TOKEN fromLexeme(final String string) {
        // Just to be safe...
        String lexeme = string.trim();

        // An empty string should mean no more tokens to process.
        if (lexeme.isEmpty())
            return EOS;

        // Search through ALL lexemes looking for a match.
        for (TOKEN t : TOKEN.values()) {
            if (t.lexemeList.contains(lexeme)) {
                // early bailout.
                return t;
            }
        }


        // If nothing matches assume an ID.  (What other choice do we have?)
        return UNKNOWN;
    }

}
