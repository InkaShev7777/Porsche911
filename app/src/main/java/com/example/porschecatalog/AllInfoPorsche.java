package com.example.porschecatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class AllInfoPorsche extends AppCompatActivity {

    private ImageView imageView;
    private TextView Model;
    private TextView Desc;
    private String[] allInfo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_info_porsche);
        this.imageView = (ImageView) findViewById(R.id.imageViewImage);
        this.Model = (TextView) findViewById(R.id.textViewModel);
        this.Desc = (TextView) findViewById(R.id.textViewDesc);

        //
        //  get info from bef window and set info
        //
        Intent intent = getIntent();
        String value = intent.getStringExtra("FullInfo");
        allInfo = value.split("%");
        Picasso.get().load(allInfo[3]).into(imageView);
        this.Model.setText(allInfo[0]+" " + " [" + allInfo[1]+"]");
        this.Desc.setText(allInfo[2]);

    }
}