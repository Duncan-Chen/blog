package com.duncan.blog.utils;

import java.util.UUID;

public class MyUUID {

	public static String UU64() {
		return UU64(UUID.randomUUID());
	}
	
	private static final char[] _UU64 = "-0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	private static String UU64(UUID uuid) {
		long L = uuid.getLeastSignificantBits();
		long R = uuid.getMostSignificantBits();
		System.out.println(uuid.toString());
		System.out.println(L + " " + R);
		return "";
	}
	
	public static void main(String[] args) {
		int num = -7;
		System.out.println(Integer.toBinaryString(num) + " : " + num);
		int rnum = num >> 1;
		System.out.println(Integer.toBinaryString(rnum) + " : " + rnum);
		int lnum = num << 1;
		System.out.println(Integer.toBinaryString(lnum) + " : " + lnum);
		int nnum = num >>> 1;
		System.out.println(Integer.toBinaryString(nnum) + " : " + nnum);
	}
	
	

}
