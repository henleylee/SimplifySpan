package com.liyunlong.simplifyspan.other;

import android.widget.TextView;

/**
 * 可点击文本的点击事件监听接口
 */
public interface OnClickableSpanListener {

    void onClick(TextView textView, String clickText);

}
