package ihuiee.webservices.ems_android_app;

import java.io.Serializable;

public class Call implements Serializable {
    private String anw_1;
    private String anw_2;
    private String anw_3;
    private String anw_4;
    private String anw_5;

    public Call() {
    }

    public Call(String anw_1, String anw_2, String anw_3, String anw_4, String anw_5) {
        this.anw_1 = anw_1;
        this.anw_2 = anw_2;
        this.anw_3 = anw_3;
        this.anw_4 = anw_4;
        this.anw_5 = anw_5;
    }

    public String getAnw_1() {
        return anw_1;
    }

    public void setAnw_1(String anw_1) {
        this.anw_1 = anw_1;
    }

    public String getAnw_2() {
        return anw_2;
    }

    public void setAnw_2(String anw_2) {
        this.anw_2 = anw_2;
    }

    public String getAnw_3() {
        return anw_3;
    }

    public void setAnw_3(String anw_3) {
        this.anw_3 = anw_3;
    }

    public String getAnw_4() {
        return anw_4;
    }

    public void setAnw_4(String anw_4) {
        this.anw_4 = anw_4;
    }

    public String getAnw_5() {
        return anw_5;
    }

    public void setAnw_5(String anw_5) {
        this.anw_5 = anw_5;
    }
}
