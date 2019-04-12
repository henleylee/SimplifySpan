package com.henley.simplifyspan.demo;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.henley.simplifyspan.SimplifySpanBuilder;
import com.henley.simplifyspan.other.OnClickableSpanListener;
import com.henley.simplifyspan.other.SpecialConvertMode;
import com.henley.simplifyspan.other.SpecialGravity;
import com.henley.simplifyspan.style.SpecialImageStyle;
import com.henley.simplifyspan.style.SpecialLabelStyle;
import com.henley.simplifyspan.style.SpecialTextStyle;

public class MainActivity extends AppCompatActivity implements OnClickableSpanListener {

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvText1 = (TextView) findViewById(R.id.tv_text1);
        TextView tvText11 = (TextView) findViewById(R.id.tv_text11);
        TextView tvText2 = (TextView) findViewById(R.id.tv_text2);
        TextView tvText21 = (TextView) findViewById(R.id.tv_text21);
        TextView tvText22 = (TextView) findViewById(R.id.tv_text22);
        TextView tvText3 = (TextView) findViewById(R.id.tv_text3);
        TextView tvText4 = (TextView) findViewById(R.id.tv_text4);


        SimplifySpanBuilder simplifySpanBuilder1 = new SimplifySpanBuilder(this, tvText1);
        simplifySpanBuilder1.appendNormalText(" 双12活动开始啦，赶快抢购吧，购的越多优惠越多！！！")
                .appendSpecialStyleToFirst(new SpecialLabelStyle("1212", Color.WHITE, 8, Color.RED, 70, 35)
                        .setTextBold()
                        .setGravity(SpecialGravity.CENTER)
                        .setLabelBackgroundRadius(5))
                .appendNormalTextToFirst(" ")
                .appendSpecialStyleToFirst(new SpecialLabelStyle("天猫", Color.WHITE, 8, 0xFFFF5000, 60, 35)
                        .setGravity(SpecialGravity.CENTER)
                        .setLabelBackgroundRadius(5));
        tvText1.setText(simplifySpanBuilder1.build());


        SimplifySpanBuilder simplifySpanBuilder11 = new SimplifySpanBuilder(this, tvText11);
        simplifySpanBuilder11.appendSpecialStyle(new SpecialLabelStyle("热", Color.WHITE, 7)
                .setBackgroundBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_heart))
                .setPadding(15)
                .setGravity(SpecialGravity.CENTER))
                .appendNormalText("正常")
                .appendSpecialStyle(new SpecialLabelStyle("热", Color.WHITE, 7)
                        .setBackgroundBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_heart))
                        .setPadding(15).setGravity(SpecialGravity.TOP))
                .appendNormalText("正常")
                .appendSpecialStyle(new SpecialLabelStyle("热", Color.WHITE, 7)
                        .setBackgroundBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_heart))
                        .setPadding(15).setGravity(SpecialGravity.BOTTOM))
                .appendNormalText("正常")
                .appendNormalText("\n")
                .appendSpecialStyle(new SpecialLabelStyle("原创", Color.BLACK, 10)
                        .setBackgroundBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_label))
                        .setPadding(5)
                        .setPaddingLeft(15)
                        .setPaddingRight(30)
                        .setGravity(SpecialGravity.TOP))
                .appendNormalText("正常")
                .appendSpecialStyle(new SpecialLabelStyle("原创", Color.BLACK, 10)
                        .setBackgroundBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_label))
                        .setPadding(5)
                        .setPaddingLeft(15)
                        .setPaddingRight(30)
                        .setGravity(SpecialGravity.CENTER))
                .appendNormalText("正常")
                .appendSpecialStyle(new SpecialLabelStyle("原创", Color.BLACK, 10)
                        .setBackgroundBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.ic_label))
                        .setPadding(5)
                        .setPaddingLeft(15)
                        .setPaddingRight(30)
                        .setGravity(SpecialGravity.BOTTOM))
                .appendNormalText("正常")
                .appendNormalText("\n")
                .appendSpecialStyle(new SpecialLabelStyle("原创", Color.WHITE, 10, 0xFFFF5000)
                        .setLabelBackgroundRadius(5)
                        .setPadding(5)
                        .setPaddingLeft(10)
                        .setPaddingRight(10)
                        .setGravity(SpecialGravity.BOTTOM))
                .appendNormalText("正常")
                .appendSpecialStyle(new SpecialLabelStyle("原创", Color.WHITE, 10, 0xFFFF5000)
                        .setLabelBackgroundRadius(5)
                        .setPadding(5)
                        .setPaddingLeft(10)
                        .setPaddingRight(10)
                        .setGravity(SpecialGravity.TOP))
                .appendNormalText("正常")
                .appendSpecialStyle(new SpecialLabelStyle("原创", Color.WHITE, 10, 0xFFFF5000)
                        .setLabelBackgroundRadius(5)
                        .setPadding(5)
                        .setPaddingLeft(10)
                        .setPaddingRight(10)
                        .setGravity(SpecialGravity.CENTER))
                .appendNormalText("正常")
                .appendNormalText("\n")
                .appendSpecialStyle(new SpecialLabelStyle("原创", Color.WHITE, 10, Color.GRAY)
                        .setLabelBackgroundRadius(5)
                        .setBackgroundBorder(Color.BLACK, 2)
                        .setPadding(5)
                        .setPaddingLeft(10)
                        .setPaddingRight(10)
                        .setGravity(SpecialGravity.BOTTOM))
                .appendNormalText("正常")
                .appendSpecialStyle(new SpecialLabelStyle("原创", Color.WHITE, 10, Color.GRAY)
                        .setLabelBackgroundRadius(5)
                        .setBackgroundBorder(Color.BLACK, 2)
                        .setPadding(5)
                        .setPaddingLeft(10)
                        .setPaddingRight(10)
                        .setGravity(SpecialGravity.TOP))
                .appendNormalText("正常")
                .appendSpecialStyle(new SpecialLabelStyle("原创", Color.WHITE, 10, Color.GRAY)
                        .setLabelBackgroundRadius(5)
                        .setBackgroundBorder(Color.BLACK, 2).setPadding(5)
                        .setPaddingLeft(10)
                        .setPaddingRight(10)
                        .setGravity(SpecialGravity.CENTER))
                .appendNormalText("正常")
                .appendNormalText("\n")
                .appendSpecialStyle(new SpecialLabelStyle("原创", Color.RED, 10, Color.TRANSPARENT)
                        .setBackgroundBorder(Color.BLACK, 2).setPadding(5).setPaddingLeft(10)
                        .setPaddingRight(10)
                        .setGravity(SpecialGravity.BOTTOM))
                .appendNormalText("正常")
                .appendSpecialStyle(new SpecialLabelStyle("原创", Color.RED, 10, Color.TRANSPARENT)
                        .setBackgroundBorder(Color.BLACK, 2)
                        .setPadding(5).setPaddingLeft(10)
                        .setPaddingRight(10)
                        .setGravity(SpecialGravity.TOP))
                .appendNormalText("正常")
                .appendSpecialStyle(new SpecialLabelStyle("原创", Color.RED, 10, Color.TRANSPARENT)
                        .setBackgroundBorder(Color.BLACK, 2)
                        .setPadding(5)
                        .setPaddingLeft(10)
                        .setPaddingRight(10)
                        .setGravity(SpecialGravity.CENTER))
                .appendNormalText("正常");
        tvText11.setText(simplifySpanBuilder11.build());


        SimplifySpanBuilder simplifySpanBuilder2 = new SimplifySpanBuilder(this, tvText2);
        simplifySpanBuilder2.appendNormalText("替换所有张字的颜色及字体大小并加粗：\n张歆艺、张馨予、张嘉倪、张涵予、张含韵、张韶涵、张嘉译、张佳宁、大张伟", new SpecialTextStyle("张").setTextBold().setTextSize(20).setSpecialTextColor(0xFFFFA500).setConvertMode(SpecialConvertMode.ALL));
        tvText2.setText(simplifySpanBuilder2.build());

        SimplifySpanBuilder simplifySpanBuilder21 = new SimplifySpanBuilder(this, tvText21);
        simplifySpanBuilder21.appendSpecialStyle(new SpecialTextStyle("居中").setTextSize(12).setGravity(SpecialGravity.CENTER).setSpecialTextColor(Color.BLUE))
                .appendNormalText("正常")
                .appendSpecialStyle(new SpecialTextStyle("顶部").setTextSize(12).setGravity(SpecialGravity.TOP).setSpecialTextColor(0xFFFF5000))
                .appendNormalText("正常")
                .appendSpecialStyle(new SpecialTextStyle("底部").setTextSize(12).setSpecialTextColor(0xFF8B658B));
        tvText21.setText(simplifySpanBuilder21.build());


        SimplifySpanBuilder simplifySpanBuilder22 = new SimplifySpanBuilder(this, tvText22, "正常底部正常居中正常顶部正常",
                new SpecialTextStyle("底部").setTextSize(25).setGravity(SpecialGravity.BOTTOM).setSpecialTextColor(Color.BLUE),
                new SpecialTextStyle("居中").setTextSize(25).setGravity(SpecialGravity.CENTER).setSpecialTextColor(0xFFB03060),
                new SpecialTextStyle("顶部").setTextSize(25).setGravity(SpecialGravity.TOP).setSpecialTextColor(0xFFB0C4DE));
        tvText22.setText(simplifySpanBuilder22.build());


        SimplifySpanBuilder simplifySpanBuilder3 = new SimplifySpanBuilder(this, tvText3);
        simplifySpanBuilder3.appendNormalText("大图片")
                .appendSpecialStyle(new SpecialImageStyle(BitmapFactory.decodeResource(getResources(), R.drawable.ic_hot), 150, 150))
                .appendNormalText("默认")
                .appendSpecialStyle(new SpecialImageStyle(BitmapFactory.decodeResource(getResources(), R.drawable.ic_bulletin), 50, 50))
                .appendNormalText("居中")
                .appendSpecialStyle(new SpecialImageStyle(BitmapFactory.decodeResource(getResources(), R.drawable.ic_bulletin), 50, 50).setGravity(SpecialGravity.CENTER))
                .appendNormalText("顶部")
                .appendSpecialStyle(new SpecialImageStyle(BitmapFactory.decodeResource(getResources(), R.drawable.ic_bulletin), 50, 50).setGravity(SpecialGravity.TOP))
                .appendNormalText("底部")
                .appendSpecialStyle(new SpecialImageStyle(BitmapFactory.decodeResource(getResources(), R.drawable.ic_bulletin), 50, 50).setGravity(SpecialGravity.BOTTOM));
        tvText3.setText(simplifySpanBuilder3.build());


        SimplifySpanBuilder simplifySpanBuilder4 = new SimplifySpanBuilder(this, tvText4);
        simplifySpanBuilder4.appendNormalText("无默认背景")
                .appendSpecialStyle(new SpecialTextStyle("点击【无默认背景】")
                        .setOnClickListener(false, 0xFFFF5000, this)
                        .setSpecialTextColor(Color.BLUE))
                .appendNormalText("\n")
                .appendNormalText("无默认背景显示下划线")
                .appendSpecialStyle(new SpecialTextStyle("点击【无默认背景显示下划线】")
                        .setOnClickListener(true, 0xFFFF5000, Color.WHITE, this)
                        .setSpecialTextColor(0xFFFF5000))
                .appendNormalText("\n")
                .appendNormalText("有默认背景不显示下划线")
                .appendSpecialStyle(new SpecialTextStyle("点击【有默认背景不显示下划线】")
                        .setOnClickListener(false, Color.BLUE, Color.WHITE, this)
                        .setSpecialTextColor(0xFFFF5000)
                        .setSpecialTextBackgroundColor(0xFF87CEEB));
        tvText4.setText(simplifySpanBuilder4.build());
    }

    @Override
    public void onClick(TextView textView, String clickText) {
        showToast(clickText);
    }

    private void showToast(CharSequence message) {
        if (toast == null) {
            toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
}
