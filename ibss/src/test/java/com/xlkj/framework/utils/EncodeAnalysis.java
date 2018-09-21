package com.xlkj.framework.utils;

public class EncodeAnalysis

{

	public static void main(String[] argv) {

		try {

			System.out.println("1:"+"中文");// 1

			System.out.println("2:"+"中文".getBytes());// 2

			System.out.println("3"+"中文".getBytes("GB2312"));// 3

			System.out.println("4:"+"中文".getBytes("ISO8859_1"));// 4

			System.out.println("5:"+new String("中文".getBytes()));// 5

			System.out.println("6:"+new String("中文".getBytes(), "GB2312"));// 6

			System.out.println("7:"+new String("中文".getBytes(), "ISO8859_1"));// 7

			System.out.println("8:"+new String("中文".getBytes("GB2312")));// 8

			System.out.println("9:"+new String("中文".getBytes("GB2312"), "GB2312"));// 9

			System.out
					.println("10:"+new String("中文".getBytes("GB2312"), "ISO8859_1"));// 10

			System.out.println("11:"+new String("中文".getBytes("ISO8859_1")));// 11

			System.out
					.println("12:"+new String("中文".getBytes("ISO8859_1"), "GB2312"));// 12

			System.out.println("13:"+new String("中文".getBytes("ISO8859_1"),
					"ISO8859_1"));// 13

		}

		catch (Exception e) {

			e.printStackTrace();

		}

	}

}