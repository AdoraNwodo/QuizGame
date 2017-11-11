package com.example.nenneadora.quizgame;

import android.app.Activity;

public class Question extends Activity {

    private int ID;
    private String QUESTION;
    private String OPTA;
    private String OPTB;
    private String OPTC;
    private String OPTD;
    private byte[] IMAGEDATA;

    private String ANSWER;

    public Question() {
        ID = 0;
        QUESTION = "";
        OPTA = "";
        OPTB = "";
        OPTC = "";
        OPTD = "";

        ANSWER = "";
    }

    public Question(String question, String opta, String optb, String optc,
                    String optd, String answer) {
        QUESTION = question;
        OPTA = opta;
        OPTB = optb;
        OPTC = optc;
        OPTD = optd;

        ANSWER = answer;

    }

    public Question(String question, byte[] image, String opta, String optb, String optc, String answer){
        QUESTION = question;
        OPTA = opta;
        OPTB = optb;
        OPTC = optc;
        IMAGEDATA = image;

        ANSWER = answer;
    }

    public int getID() {
        return ID;
    }

    public String getQUESTION() {

        return QUESTION;
    }

    public String getOPTA() {

        return OPTA;
    }

    public String getOPTB() {

        return OPTB;
    }

    public String getOPTC() {

        return OPTC;
    }
    public String getOPTD() {

        return OPTD;
    }

    public String getANSWER() {

        return ANSWER;
    }
    public byte[] getIMAGE() {

        return IMAGEDATA;
    }

    public void setIMAGE( byte[] imagedata) {

        IMAGEDATA = imagedata;
    }

    public void setID(int id) {

        ID = id;
    }

    public void setQUESTION(String qUESTION) {

        QUESTION = qUESTION;
    }

    public void setOPTA(String oPTA) {

        OPTA = oPTA;
    }

    public void setOPTB(String oPTB) {

        OPTB = oPTB;
    }

    public void setOPTC(String oPTC) {

        OPTC = oPTC;
    }

    public void setOPTD(String oPTD) {

        OPTD = oPTD;
    }

    public void setANSWER(String aNSWER) {
        ANSWER = aNSWER;
    }

}
