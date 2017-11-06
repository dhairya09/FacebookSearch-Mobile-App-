package com.example.dhairyapujara.hw9_final;

/**
 * Created by Dhairya Pujara on 19-04-2017.
 */

public class PostItem
{


        private String post_img;
        private String post_name;
        private String post_time;
        private String post_msg;


        public PostItem(String name, String img, String time, String msg){

            this.post_img = img;
            this.post_name = name;
            this.post_time = time;
            this.post_msg = msg;
        }

    public String getPost_img() {
        return post_img;
    }

    public String getPost_name() {
        return post_name;
    }

    public String getPost_time() {
        return post_time;
    }

    public String getPost_msg() {
        return post_msg;
    }
}
