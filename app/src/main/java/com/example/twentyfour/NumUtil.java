package com.example.twentyfour;

import java.util.Random;

public class NumUtil {
    public static int[] Randoms(int number) {

        Random rand = new Random();
        int nu[] = new int[4];
        boolean[] bool = new boolean[number + 1];
        int randint = 0;
        for (int i = 0; i < 4; i++) {
            do {
                randint = rand.nextInt(number);
            } while (bool[randint]);
            bool[randint] = true;
            nu[i] = randint;
        }
        return nu;

    }
    public static String str(int numlk){
        String str="";
        if(numlk==0||numlk==1||numlk==41||numlk==42){
            str="14";
        }
        if(numlk==2||numlk==15||numlk==28||numlk==43){
            str="10";
        }
        if(numlk==3||numlk==16||numlk==29||numlk==44){
            str="2";
        }
        if(numlk==4||numlk==17||numlk==30||numlk==45){
            str="3";
        }
        if(numlk==5||numlk==18||numlk==31||numlk==46){
            str="4";
        }
        if(numlk==6||numlk==19||numlk==32||numlk==47){
            str="5";
        }
        if(numlk==7||numlk==20||numlk==33||numlk==48){
            str="6";
        }
        if(numlk==8||numlk==21||numlk==34||numlk==49){
            str="7";
        }
        if(numlk==9||numlk==22||numlk==35||numlk==50){
            str="8";
        }
        if(numlk==10||numlk==23||numlk==36||numlk==51){
            str="9";
        }
        if(numlk==11||numlk==24||numlk==37||numlk==52){
            str="1";
        }
        if(numlk==12||numlk==25||numlk==38||numlk==53){
            str="11";
        }
        if(numlk==13||numlk==26||numlk==39||numlk==54){
            str="13";
        }
        if(numlk==14||numlk==27||numlk==40||numlk==55){
            str="12";
        }
        return str;
    }
}
