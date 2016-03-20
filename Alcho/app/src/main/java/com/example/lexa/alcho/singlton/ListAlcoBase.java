
package com.example.lexa.alcho.singlton;

import com.example.lexa.alcho.VvodAlcho;
import com.example.lexa.alcho.construct.VvodAlchoBase;

import java.util.ArrayList;

/**
 * Created by Lexa on 27.12.2015.
 */
public class ListAlcoBase {
    private ArrayList<VvodAlchoBase>list = new ArrayList<VvodAlchoBase>();

    public ArrayList<VvodAlchoBase> getList() {
        return list;
    }

    public void setList(ArrayList<VvodAlchoBase> list) {
        this.list = list;
    }

    private static ListAlcoBase instance;

    public static ListAlcoBase getInstance() {
        if(instance ==null){
            instance = new ListAlcoBase();
        }
        return instance;
    }

    private ListAlcoBase() {
    }

    public void add() {
        list.add(VvodAlcho.list);
    }

    public void clear() {
        this.list.clear();
    }
}
