/*
 * nesc.jflex
 *
 * Network Embedded Sensor Testbed (NESTBed)
 *
 * Copyright (C) 2006
 * Dependable Systems Research Group
 * Department of Computer Science
 * Clemson University
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the
 *
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301, USA.
 */
package edu.clemson.cs.nestbed.server.nesc.parser;

import java_cup.*;
import java_cup.runtime.*;

%%
%public
%class Lexer
%unicode
%cup
%char
%line
%column
%function next_token

%{
    private StringBuffer string           = new StringBuffer();
    private StringBuffer preProcessorText = new StringBuffer();
    private boolean      preProcessorCont = false;
    private StringBuffer braceContent     = new StringBuffer();
    private int          braceCount       = 0;

    public static void main(String[] args) throws Exception {
        Lexer lexer = new Lexer(System.in);

        while (!lexer.zzAtEOF) {
            System.out.println(lexer.next_token());
        }
    }

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}

LineTerminator       = \r
                     |\n
                     |\r\n
InputCharacter       = [^\r\n]
WhiteSpace           = {LineTerminator}
                     | [ \t\f]
Comment              = {TraditionalComment}
                     | {EndOfLineComment}
                     | {DocumentationComment}
TraditionalComment   = "/*" [^*] ~"*/"
                     | "/*" "*"+ "/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*
Identifier           = [:jletter:] [:jletterdigit:]*
DecIntegerLiteral    = 0
                     | [1-9][0-9]*

%state STRING
%state BRACE
%state PREPROCESSOR

%%

<YYINITIAL> {
    "includes" {
        return symbol(Token.INCLUDES);
    }

    "configuration" {
        return symbol(Token.CONFIGURATION);
    }

    "provides" {
        return symbol(Token.PROVIDES);
    }

    "uses" {
        return symbol(Token.USES);
    }

    "interface" {
        return symbol(Token.INTERFACE);
    }

    "implementation" {
        return symbol(Token.IMPLEMENTATION);
    }

    "components" {
        return symbol(Token.COMPONENTS);
    }

    "as" {
        return symbol(Token.AS);
    }

    "->" {
        return symbol(Token.WIRES_TO, yytext());
    }

    "=" {
        return symbol(Token.EQUALS, yytext());
    }

    {Identifier} {
        return symbol(Token.IDENTIFIER, yytext());
    }

    {Comment} {
        /* ignore */
    }

    {WhiteSpace} {
        /* ignore */
    }

    {DecIntegerLiteral} {
        return symbol(Token.INTEGER_LITERAL, new Integer(yytext()));
    }


    "\"" {
        string.setLength(0);
        yybegin(STRING);
    }

    ";" {
        return symbol(Token.SEMI_COLON);
    }

    "(" {
        return symbol(Token.OPEN_PAREN);
    }

    ")" {
        return symbol(Token.CLOSE_PAREN);
    }

    "{" {
        return symbol(Token.OPEN_CURLY);
    }

    "}" {
        return symbol(Token.CLOSE_CURLY);
    }

    "," {
        return symbol(Token.COMMA);
    }

    "." {
        return symbol(Token.DOT);
    }

/*
    "[" {
        return symbol(Token.OPEN_BRACE);
    }
*/

    "[" {
        braceCount++;
        if (braceCount == 1) {
            braceContent.setLength(0);
            yybegin(BRACE);
            return symbol(Token.OPEN_BRACE);
        }
    }

/*
    "]" {
        return symbol(Token.CLOSE_BRACE);
    }
*/
    
    "+" {
        return symbol(Token.PLUS);
    }

    "-" {
        return symbol(Token.MINUS);
    }

    "*" {
        return symbol(Token.TIMES);
    }

    "/" {
        return symbol(Token.DIVIDE);
    }

    "%" {
        return symbol(Token.DIVIDE);
    }

    "<<" {
        return symbol(Token.LSHIFT);
    }

    ">>" {
        return symbol(Token.RSHIFT);
    }

    "<" {
        return symbol(Token.LESS_THAN);
    }

    ">" {
        return symbol(Token.GREATER_THAN);
    }
    
    "==" {
        return symbol(Token.EQCOMPARE);
    }

    "&" {
        return symbol(Token.BITWISE_AND);
    }

    "|" {
        return symbol(Token.BITWISE_OR);
    }

    "^" {
        return symbol(Token.BITWISE_NOT);
    }

    "&&" {
        return symbol(Token.AND);
    }

    "||" {
        return symbol(Token.OR);
    }

    "?" {
        return symbol(Token.QUESTION);
    }

    ":" {
        return symbol(Token.COLON);
    }

    "#" {
        preProcessorText.setLength(0);
        preProcessorText.append(yytext());
        yybegin(PREPROCESSOR);
    }
}

<PREPROCESSOR> {
    "." {
        preProcessorText.append(yytext());
        preProcessorCont = false;
    }

    "\\" {
        preProcessorCont = true;
        preProcessorText.append(yytext());
    }

    "\n" {
        if (preProcessorCont) {
            preProcessorCont = false;
        } else {
            yybegin(YYINITIAL);
        }
        preProcessorText.append(yytext());
        return symbol(Token.PREPROCESSOR, preProcessorText.toString());
    }
}

<STRING> {
    \" {
        yybegin(YYINITIAL);
        return symbol(Token.STRING, string.toString());
    }

    [^\n\r\"\\]+ {
        string.append( yytext() );
    }

    \\t {
        string.append('\t');
    }

    \\n {
        string.append('\n');
    }

    \\r {
        string.append('\r');
    }

    \\\" {
        string.append('\"');
    }

    \\ {
        string.append('\\');
    }
}


<BRACE> {
    "[" {
        braceContent.append(yytext());
        braceCount++;
    }

    "]" {
        --braceCount;
        if (braceCount == 0) {
            yybegin(YYINITIAL);
            return symbol(Token.CLOSE_BRACE, braceContent.toString());
        } else {
        braceContent.append(yytext());
        }
    }

    . {
        braceContent.append(yytext());
    }
}

/* error fallback */
.|\n {
    throw new Error("Illegal character <"+ yytext()+">");
}
/* vim: :set ft=java: */
