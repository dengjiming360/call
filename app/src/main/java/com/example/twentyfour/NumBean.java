package com.example.twentyfour;

public class NumBean {
    public String k="";
    public String n="";

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public NumBean(String k, String n) {
        this.k=k;
        this.n=n;
    }
    public boolean isDuiying(String k2,String n2){
        if(k2.equals(k)&&n2.equals(n)){
            return true;
        }
        else{
            return false;
        }
    }
}
