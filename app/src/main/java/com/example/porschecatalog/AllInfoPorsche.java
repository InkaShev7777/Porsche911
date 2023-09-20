package com.example.porschecatalog;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.net.URL;

public class AllInfoPorsche extends AppCompatActivity {

    private ImageView imageView;
    private TextView Model;
    private TextView Desc;
    private VideoView videoView;
    private WebView webView;
    private String[] allInfo;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_info_porsche);
        this.imageView = (ImageView) findViewById(R.id.imageViewImage);
        this.Model = (TextView) findViewById(R.id.textViewModel);
        this.Desc = (TextView) findViewById(R.id.textViewDesc);
//        this.videoView = (VideoView)findViewById(R.id.video);
//        this.webView = (WebView)findViewById(R.id.web);

        Intent intent = getIntent();
        String value = intent.getStringExtra("FullInfo");
        allInfo = value.split("%");
        Picasso.get().load(allInfo[3]).into(imageView);
        this.Model.setText(allInfo[0]+" " + " [" + allInfo[1]+"]");
        this.Desc.setText(allInfo[2]);

//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        String videoId = "7UCazDYf2Js"; // Replace with the actual YouTube video ID
//        String videoUrl = "https://www.youtube.com/embed/" + videoId;
//        webView.loadUrl(videoUrl);
//        String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/7UCazDYf2Js?si=mZcr20wlGL-aGgTS\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
//        webView.loadData(video,"text/html","utf-8");
//        webView.setLayerType(WebView.LAYER_TYPE_NONE , null);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebChromeClient(new WebChromeClient());
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
//        this.videoView.setVideoURI(Uri.parse("https://android.codeseasy.com/Video-Files/BigBuckBunny.mp4"));
//        this.videoView.start();
//        MediaController mediaController = new MediaController(this);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
//
//        // Set the video Uri
//        try {
//            videoView.setVideoURI(Uri.parse("https://www.youtube.com/watch?v=2_6lqVoxoa8"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        // Start playing the video
//        videoView.start();
    }
}