package com.example.osid;

import com.example.osid.POJOs.Insuline;

import java.util.Comparator;

public class CustomComparatorInsuline implements Comparator<Insuline> {
    @Override
    public int compare(Insuline insuline, Insuline t1) {
        return insuline.getFecha().compareTo(t1.getFecha());
    }
}
