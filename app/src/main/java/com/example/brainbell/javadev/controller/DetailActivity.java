package com.example.brainbell.javadev.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.brainbell.javadev.R;

import java.util.zip.Inflater;

/**
 * Created by BRAINBELL on 12-Sep-17.
 */
public class DetailActivity extends AppCompatActivity{
    TextView Link, Username;


    Toolbar mActionBarToolBar;
    ImageView imageView;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageView =(ImageView) findViewById(R.id.user_image_header);
        Username=(TextView) findViewById(R.id.username);

        Link=(TextView) findViewById(R.id.link);

        String username=getIntent().getExtras().getString("login");
        String avatarUrl=getIntent().getExtras().getString("avatar_url");
        String link=getIntent().getExtras().getString("html_url");

        //making the link linked to the web page
        Link.setText(link);
        Linkify.addLinks(Link, Linkify.WEB_URLS);

        Username.setText(username);
        Glide.with(this)
                .load(avatarUrl)
                .placeholder(R.drawable.placeholder2)
                .into(imageView);

        getSupportActionBar().setTitle("Profile of:"+username);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflate menu resource file
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_detail,menu);
        MenuItem menuItem=menu.findItem(R.id.action_share);
        menuItem.setIntent(createShare());

        return true;
    }
    private Intent createShare(){
        String username=getIntent().getExtras().getString("login");
        String link=getIntent().getExtras().getString("html_url");
        Intent shareIntent= ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Check out this awesome developer @" + username + "," + link)
                .getIntent();
        return shareIntent;
    }




}

