package com.cycloneboy.dp.ch5;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Client1 {
    public static void main(String[] args) {
        InstallSoftware invoker = new InstallSoftware();
        invoker.installWizard(new Wizard());
    }
}
