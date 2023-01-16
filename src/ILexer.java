package xyz.zepton.lexer;

/*
 * @(#)ILexer.java
 *
 * Title: ZeptoN "Lex" Lexer - ZeptoN programming language lexer interface.
 *
 * Description: Interface to simply check for has token and to get token. 
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

public interface ILexer {

	public boolean hasTok();
	public Token   getTok();
	
}//end interface ILexer
