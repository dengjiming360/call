package com.example.twentyfour;

public class Kuohao {
    public static boolean whetherStringClose(String str) {
        int parenthesesNums=0;
        int bracketsNums = 0;
        int bracesNums = 0;
        for(int i=0;i<str.length();i++) {
            char c = str.charAt(i);
            if(c=='{') {
                bracesNums+=1;
            }
            if(c=='[') {
                bracketsNums+=1;
            }
            if(c=='(') {
                parenthesesNums+=1;
            }
            if(c=='}') {
                bracesNums-=1;
            }
            if(c==']') {
                bracketsNums-=1;
            }
            if(c==')') {
                parenthesesNums-=1;
            }
            if(parenthesesNums<0 || bracesNums<0||bracketsNums<0) {
                break;
            }
        }
        if(parenthesesNums!=0 || bracesNums!=0|| bracketsNums!=0) {
            return false;
        }else {
            return true;
        }

    }
}
