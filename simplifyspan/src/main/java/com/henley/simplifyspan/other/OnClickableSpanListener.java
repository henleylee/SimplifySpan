package com.henley.simplifyspan.other;

import android.widget.TextView;

/**
 * 可点击文本的点击事件监听接口
 * @author Henley
 * @date 2016/12/12 11:37
 */
public interface OnClickableSpanListener {

    void onClick(TextView textView, String clickText);

}
