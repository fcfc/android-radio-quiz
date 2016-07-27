package com.ice.quiz2;

public class Answer {
    String number;
    String question;
    String a;
    String b;
    String c;
    String d;
    String correct;

    public Answer(String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
        this.number = s1;
        this.question = s2;
        this.a = s3;
        this.b = s4;
        this.c = s5;
        this.d = s6;
        this.correct = s7;
    }

    public void clear() {
        this.number = null;
        this.question = null;
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.correct = null;
    }
}

