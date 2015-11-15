package com.example.lexa.myapplication;

import java.util.ArrayList;

public class Manager {
    private static Manager instance;
    private ArrayList<Beer> good = new ArrayList<>();

    Net net = new Net();
    Parse vuvod = new Parse(net);

    public ArrayList<Beer> getGood() {
        return good;
    }

    private Manager() {
    }

    public static class SingletonHolder {
        public static final Manager HOLDER_INSTANCE = new Manager();
    }

    public static Manager getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    public void schit() {
        net.runnne();
        vuvod.runner();
        good.addAll(vuvod.iBeer.getGoods());
        System.out.print(good);
    }
}
