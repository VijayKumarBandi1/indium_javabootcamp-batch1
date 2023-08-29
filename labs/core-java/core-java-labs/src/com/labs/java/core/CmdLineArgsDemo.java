package com.labs.java.core;

public class CmdLineArgsDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 0) {

			System.out.println("no command line arguments passed");
//            return;
			System.exit(0);
		}



		System.out.println(args[0].length());
		System.out.println(args[1].toLowerCase());
		// StringBuilder reversed = new StringBuilder(args[2]).reverse();
		char[] ch = args[2].toCharArray();
		for (int i = ch.length - 1; i > 0; i--) {
			System.out.print(ch[i]);
		}
                     
          System.out.println(args[4].substring(4));
          String[] tokens=args[5].split(","); 
          System.out.println(tokens);
        }
	}


