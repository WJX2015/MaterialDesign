package com.example.lenovo_g50_70.materialdesign;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class GirlActivity extends AppCompatActivity {
    public static final String GIRL_NAME = "girl_name";
    public static final String GIRL_IMAGE_ID = "girl_image_id";
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private ImageView girlImageView;
    private TextView girlContentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_girl);
        Intent intent = getIntent();
        String girlName = intent.getStringExtra(GIRL_NAME);
        int girlImageid = intent.getIntExtra(GIRL_IMAGE_ID, 0);
        toolbar = (Toolbar) findViewById(R.id.ToolBar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        girlImageView = (ImageView) findViewById(R.id.girl_image_view);
        girlContentText = (TextView) findViewById(R.id.girl_content_text);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(girlName);
        Glide.with(this).load(girlImageid).into(girlImageView);
        String girlContent = generateGirlContent(girlName);
        girlContentText.setText(girlContent);
    }

    /**
     * @return 数据填充
     */
    private String generateGirlContent(String girlName) {
        StringBuilder girlContent = new StringBuilder();
        if (girlName.equals("双鱼座")) {
            girlContent.append(getString(R.string.ShuangYu));
        } else if (girlName.equals("摩羯座")) {
            girlContent.append(getString(R.string.MoJie));
        } else if (girlName.equals("水瓶座")) {
            girlContent.append(getString(R.string.ShuiPing));
        } else if (girlName.equals("白羊座")) {
            girlContent.append(getString(R.string.Bai_yang));
        } else if (girlName.equals("双子座")) {
            girlContent.append(getString(R.string.ShuangZi));
        } else if (girlName.equals("巨蟹座")) {
            girlContent.append(getString(R.string.JuXie));
        } else if (girlName.equals("天羯座")) {
            girlContent.append(getString(R.string.TianXie));
        } else if (girlName.equals("金牛座")) {
            girlContent.append(getString(R.string.JinNiu));
        } else if (girlName.equals("天枰座")) {
            girlContent.append(getString(R.string.TianPing));
        } else if (girlName.equals("射手座")) {
            girlContent.append(getString(R.string.SheShou));
        } else if (girlName.equals("狮子座")) {
            girlContent.append(getString(R.string.ShiZi));
        } else if (girlName.equals("处女座")) {
            girlContent.append(getString(R.string.ChuNv));
        }
        return girlContent.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
