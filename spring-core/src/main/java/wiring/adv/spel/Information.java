package wiring.adv.spel;

import java.util.Arrays;
import java.util.List;

public class Information {

    private String[] colors = {"red", "green", "blue"};
    private List<String> dest = Arrays.asList(new String[]{"N", "S", "E", "W"});

    private String message1;
    private String message2;
    private String message3;
    private String message4;
    private String message5;
    private String message6;
    private String message7;
    private String message8;
    private String message9;
    private String message10;
    private String message11;
    private String message12;

    private Object bean1;
    private Object bean2;

    public String[] getColors() {
        return colors;
    }

    public List<String> getDest() {
        return dest;
    }

    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    public String getMessage3() {
        return message3;
    }

    public void setMessage3(String message3) {
        this.message3 = message3;
    }

    public Object getBean1() {
        return bean1;
    }

    public void setBean1(Object bean1) {
        this.bean1 = bean1;
    }

    public Object getBean2() {
        return bean2;
    }

    public void setBean2(Object bean2) {
        this.bean2 = bean2;
    }

    public String getMessage4() {
        return message4;
    }

    public void setMessage4(String message4) {
        this.message4 = message4;
    }

    public String getMessage5() {
        return message5;
    }

    public void setMessage5(String message5) {
        this.message5 = message5;
    }

    public String getMessage6() {
        return message6;
    }

    public void setMessage6(String message6) {
        this.message6 = message6;
    }

    public String getMessage7() {
        return message7;
    }

    public void setMessage7(String message7) {
        this.message7 = message7;
    }

    public String getMessage8() {
        return message8;
    }

    public void setMessage8(String message8) {
        this.message8 = message8;
    }

    public String getMessage9() {
        return message9;
    }

    public void setMessage9(String message9) {
        this.message9 = message9;
    }

    public String getMessage10() {
        return message10;
    }

    public void setMessage10(String message10) {
        this.message10 = message10;
    }

    public String getMessage11() {
        return message11;
    }

    public void setMessage11(String message11) {
        this.message11 = message11;
    }

    public String getMessage12() {
        return message12;
    }

    public void setMessage12(String message12) {
        this.message12 = message12;
    }

    @Override
    public String toString() {
        return "Information{" +
                "\n message1='" + message1 + '\'' +
                ",\n message2='" + message2 + '\'' +
                ",\n message3='" + message3 + '\'' +
                ",\n message4='" + message4 + '\'' +
                ",\n message5='" + message5 + '\'' +
                ",\n message6='" + message6 + '\'' +
                ",\n message7='" + message7 + '\'' +
                ",\n message8='" + message8 + '\'' +
                ",\n message9='" + message9 + '\'' +
                ",\n message10='" + message10 + '\'' +
                ",\n message11='" + message11 + '\'' +
                ",\n message12='" + message12 + '\'' +
                ",\n bean1=" + bean1 +
                ",\n bean2=" + bean2 +
                '}';
    }
}
