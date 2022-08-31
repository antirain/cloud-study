package com.hyf.cloud.bridge;

public class PhoneRun {

    public static void main(String[] args) {
        Phone p = new Oppo();
        p.setSoftware(new AppStore());
        p.run();
        p.setSoftware(new Camera());
        p.run();
    }
}
