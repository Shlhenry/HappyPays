package com.futeng.happypays.baowen.iso8583;

public enum LengthType {
	FIX_LEN,   /*(LENgth fix 是指由ISO_8583中的长度决定该域的长度)*/
	LLVAR_LEN,   /*(LENgth 00~99)*/
	LLLVAR_LEN   /*(LENgth 00~999)*/
}
