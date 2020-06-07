package com.example.nueva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.nueva.fragments.PageFragmet3;
import com.example.nueva.fragments.PageFragmet4;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter adapter;

    private final static int NUM_PAGES = 2;
    //private ViewPager mViewPager;
    private List<ImageView> dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main2);

        List<Fragment> list = new ArrayList<>();
        list.add(new PageFragmet3());
        list.add(new PageFragmet4());

        viewPager = findViewById(R.id.pager);
        adapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        viewPager.setAdapter(adapter);

        addDots();
    }

    public void openMain1(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }



    public void addDots() {
        dots = new ArrayList<>();
        LinearLayout dotsLayout = (LinearLayout)findViewById(R.id.dots);

        for(int i = 0; i < NUM_PAGES; i++) {
            ImageView dot = new ImageView(this);
            dot.setImageDrawable(getResources().getDrawable(R.drawable.dotdisable));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            dotsLayout.addView(dot, params);

            dots.add(dot);
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void selectDot(int idx) {
        Resources res = getResources();
        for(int i = 0; i < NUM_PAGES; i++) {
            int drawableId = (i==idx)?(R.drawable.dotenable):(R.drawable.dotdisable);
            Drawable drawable = res.getDrawable(drawableId);
            dots.get(i).setImageDrawable(drawable);
        }
    }
}
