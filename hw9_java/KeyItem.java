package com.example.dhairyapujara.hw9_final;

/**
 * Created by Dhairya Pujara on 15-04-2017.
 */

public class KeyItem
{
    private String item_id;
    private String item_img;
    private String item_name;
    private String item_title;


    public String getItem_id() {
        return item_id;
    }

    public String getItem_img() {
        return item_img;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getItem_title() {
        return item_title;
    }

    public KeyItem(String id, String name, String img, String title){
        this.item_id = id;
        this.item_img = img;
        this.item_name = name;
        this.item_title = title;
    }
}
