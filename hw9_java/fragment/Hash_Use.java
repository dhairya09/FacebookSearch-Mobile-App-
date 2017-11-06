package com.example.dhairyapujara.hw9_final.fragment;

import com.example.dhairyapujara.hw9_final.KeyItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Dhairya Pujara on 19-04-2017.
 */

public class Hash_Use
{
    public static HashMap<String,KeyItem> mapuser =  new HashMap<>();
    public static HashMap<String,KeyItem> mappage =  new HashMap<>();
    public static HashMap<String,KeyItem> mapevent =  new HashMap<>();
    public static HashMap<String,KeyItem> mapplace =  new HashMap<>();
    public static HashMap<String,KeyItem> mapgroup =  new HashMap<>();

    public static List<KeyItem> favuserslist = new ArrayList<>();
    public static List<KeyItem> favpageslist = new ArrayList<>();
    public static List<KeyItem> faveventslist = new ArrayList<>();
    public static List<KeyItem> favplaceslist = new ArrayList<>();
    public static List<KeyItem> favgroupslist = new ArrayList<>();
}
