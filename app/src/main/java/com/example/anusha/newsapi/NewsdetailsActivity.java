package com.example.anusha.newsapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
//import com.squareup.picasso.Picasso;

public class NewsdetailsActivity extends AppCompatActivity {
    ImageView iv;
    TextView tvtitle,tvdesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsdetails);
        iv=findViewById(R.id.imagesearchdetails);
        tvtitle=findViewById(R.id.textbook_details);
        tvdesc=findViewById(R.id.tvdescriptionss);
        String[] s =getIntent().getStringArrayExtra("datas");
        tvtitle.setText(s[0]);
        //Picasso.with(this).load(s[2]).into(iv);
        Glide.with(this).load(s[2]).into(iv);
        tvdesc.setText(s[1]);
    }
}
