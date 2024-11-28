package com.fefox.lox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TokenTest {

    @Test
    public void testConstructor() {
        TokenType type = TokenType.IDENTIFIER;
        String lexeme = "test";
        Object literal = "hello";
        int line = 1;

        Token token = new Token(type, lexeme, literal, line);

        assertEquals(type, token.type);
        assertEquals(lexeme, token.lexeme);
        assertEquals(literal, token.literal);
        assertEquals(line, token.line);
    }

    @Test
    public void testToString() {
        TokenType type = TokenType.IDENTIFIER;
        String lexeme = "test";
        Object literal = "hello";
        int line = 1;

        Token token = new Token(type, lexeme, literal, line);

        String expected = type + " " + lexeme + " " + literal;
        assertEquals(expected, token.toString());
    }

    @Test
    public void testToStringWithNullLiteral() {
        TokenType type = TokenType.IDENTIFIER;
        String lexeme = "test";
        Object literal = null;
        int line = 1;

        Token token = new Token(type, lexeme, literal, line);

        String expected = type + " " + lexeme + " " + literal;
        assertEquals(expected, token.toString());
    }
}