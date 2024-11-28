package com.fefox.lox;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScannerTest {

    @Test
    public void testScanTokens_EmptySource() {
        Scanner scanner = new Scanner("");
        List<Token> tokens = scanner.scanTokens();
        assertEquals(1, tokens.size());
        assertEquals(TokenType.EOF, tokens.get(0).type);
    }

    @Test
    public void testScanTokens_SingleToken() {
        Scanner scanner = new Scanner("(");
        List<Token> tokens = scanner.scanTokens();
        assertEquals(2, tokens.size());
        assertEquals(TokenType.LEFT_PAREN, tokens.get(0).type);
        assertEquals(TokenType.EOF, tokens.get(1).type);
    }

    @Test
    public void testScanTokens_MultipleTokens() {
        Scanner scanner = new Scanner("( ) + -");
        List<Token> tokens = scanner.scanTokens();
        assertEquals(5, tokens.size());
        assertEquals(TokenType.LEFT_PAREN, tokens.get(0).type);
        assertEquals(TokenType.RIGHT_PAREN, tokens.get(1).type);
        assertEquals(TokenType.PLUS, tokens.get(2).type);
        assertEquals(TokenType.MINUS, tokens.get(3).type);
        assertEquals(TokenType.EOF, tokens.get(4).type);
    }

    @Test
    public void testScanTokens_Identifier() {
        Scanner scanner = new Scanner("hello");
        List<Token> tokens = scanner.scanTokens();
        assertEquals(2, tokens.size());
        assertEquals(TokenType.IDENTIFIER, tokens.get(0).type);
        assertEquals(TokenType.EOF, tokens.get(1).type);
    }

    @Test
    public void testScanTokens_Keyword() {
        Scanner scanner = new Scanner("if");
        List<Token> tokens = scanner.scanTokens();
        assertEquals(2, tokens.size());
        assertEquals(TokenType.IF, tokens.get(0).type);
        assertEquals(TokenType.EOF, tokens.get(1).type);
    }

    @Test
    public void testScanTokens_String() {
        Scanner scanner = new Scanner("\"hello\" ");
        List<Token> tokens = scanner.scanTokens();
        assertEquals(2, tokens.size());
        assertEquals(TokenType.STRING, tokens.get(0).type);
        assertEquals("hello", tokens.get(0).literal);
        assertEquals(TokenType.EOF, tokens.get(1).type);
    }

    @Test
    public void testScanTokens_Number() {
        Scanner scanner = new Scanner("123.45");
        List<Token> tokens = scanner.scanTokens();
        assertEquals(2, tokens.size());
        assertEquals(TokenType.NUMBER, tokens.get(0).type);
        assertEquals(123.45, tokens.get(0).literal);
        assertEquals(TokenType.EOF, tokens.get(1).type);
    }

    @Test
    public void testScanTokens_BlockComment() {
        Scanner scanner = new Scanner("/* hello */");
        List<Token> tokens = scanner.scanTokens();
        assertEquals(1, tokens.size());
        assertEquals(TokenType.EOF, tokens.get(0).type);
    }

    @Test
    public void testScanTokens_UnterminatedString() {
        Scanner scanner = new Scanner("\"hello");
        List<Token> tokens = scanner.scanTokens();
        assertEquals(1, tokens.size());
        assertEquals(TokenType.EOF, tokens.get(0).type);
        assertTrue(Lox.hadError);
    }

    @Test
    public void testScanTokens_UnterminatedBlockComment() {
        Scanner scanner = new Scanner("/* hello");
        List<Token> tokens = scanner.scanTokens();
        assertEquals(1, tokens.size());
        assertEquals(TokenType.EOF, tokens.get(0).type);
        assertTrue(Lox.hadError);
    }

}