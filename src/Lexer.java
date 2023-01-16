package xyz.zepton.lexer;

/*
 * @(#) Lexer.java
 *
 * Title: ZeptoN "Lex" Lexer - ZeptoN programming language lexical analyzer.
 *
 * Description: Lexer to simply check for has token and to get token; when token
 *              is present returns Token.
 *             
 *
 * @author William F. Gilreath (will@zepton.xyz)
 * @version 1.0  1/15/2023
 *
 * Copyright (c) © 2023 William F. Gilreath. All Rights Reserved.
 *
 * License: This software is subject to the terms of the GNU General Public License (GPL)
 *     version 3.0 available at the following link: http://www.gnu.org/copyleft/gpl.html.
 *
 * You must accept the terms of the GNU General Public License (GPL) license agreement
 *     to use this software.
 *
 **/


//import java.io.BufferedReader;
import java.io.File;
//import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;

import xyz.zepton.lexer.ZeptonLexerConstants.TokenType;

//Lexer add option to skip comments ??

public class Lexer implements ILexer {

	private ZeptonLexer lexer = null;
	
	public Lexer(final File file) {
		
		try {
			String content = new String(Files.readAllBytes(file.toPath()));
			this.lexer = new ZeptonLexer(file.toString(), content);
	
		} catch(Exception ex) {
			throw new RuntimeException(ex.toString());
		}//end try
		
	}//end Lexer
	
	public Lexer(final Reader rdr) {
		
		try {
		
			int val;
	        StringBuilder str = new StringBuilder();
	        while ((val = rdr.read()) != -1) {
	            str.append((char) val);
	        }//end while

	        this.lexer = new ZeptonLexer(str.toString());
		
		} catch(Exception ex) {
			throw new RuntimeException(ex.toString());	
		}//end try
		
	}//end Lexer
	
	public Lexer(final String str) {
		this.lexer = new ZeptonLexer(str);
	}//end Lexer
	
	
	private boolean eofFlag = false;
	private Token   buffer  = null;
	
	public boolean hasTok() {
		
		if(this.eofFlag) return false;
		
		if(buffer == null) {
			buffer = this.getTok();
		}//end if
		
		if(this.buffer.getType() == TokenType.EOF) {
			this.eofFlag = true;
			return false;
		}//end if

		return true;
		
	}//end hasTok

	//Q: need EOF token, once get, keep returning ?? Mar 21 2022
	
	private static Token EOF_TOKEN = null;
	
	public Token getTok() {
				
		if(this.eofFlag) return EOF_TOKEN;
		
		Token tok = null;
		
		if(this.buffer != null) {
			tok = this.buffer;
			this.buffer = null;
			return tok;
		}//end if
		
		tok = this.lexer.getNextToken(tok);
		
		if(tok.getType() == TokenType.EOF) {
			this.eofFlag    = true;
			Lexer.EOF_TOKEN = tok;
		}//end if
		
		return tok;
		
	}//end getTok
	
	
}//end class Lexer
