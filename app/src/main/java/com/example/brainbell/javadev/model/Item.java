package com.example.brainbell.javadev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by BRAINBELL on 12-Sep-17.
 */
public class Item {
    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("avatar_url")
    @Expose
    private String avatar_Url;

    @SerializedName("html_url")
    @Expose
    private String html_Url;

    public Item(String login,String avatar_Url,String html_Url){
        this.login=login;
        this.avatar_Url=avatar_Url;
        this.html_Url=html_Url;
    }


    /**this method will return the login fetched from the JSON file in the Github api*/
    public String getLogin(){
        return login;
    }

    /**this method will return the image fetched from the JSON file in the Github api*/
    public String getAvatar_Url(){
        return avatar_Url;}

    /**this method will return the html address fetched from the JSON file in the Github api*/
    public String getHtml_Url(){
        return html_Url;
    }

}
