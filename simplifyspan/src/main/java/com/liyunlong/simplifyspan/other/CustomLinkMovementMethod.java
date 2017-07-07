package com.liyunlong.simplifyspan.other;

import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

import com.liyunlong.simplifyspan.span.CustomClickableSpan;

/**
 * 自定义{@link LinkMovementMethod}
 *
 * @author liyunlong
 * @date 2017/7/7 18:02
 */
public class CustomLinkMovementMethod extends LinkMovementMethod {

    private static CustomLinkMovementMethod sInstance; // 自定义LinkMovementMethod实例
    private CustomClickableSpan mCustomClickableSpan; // 自定义点击链接样式

    public static CustomLinkMovementMethod getInstance() {
        if (sInstance == null) {
            sInstance = new CustomLinkMovementMethod();
        }
        return sInstance;
    }

    @Override
    public boolean onTouchEvent(TextView textView, Spannable spannable, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            mCustomClickableSpan = getPressedSpan(textView, spannable, event);
            if (mCustomClickableSpan != null) {
                mCustomClickableSpan.setPressed(true); // 设置为点击状态
                Selection.setSelection(spannable, spannable.getSpanStart(mCustomClickableSpan), spannable.getSpanEnd(mCustomClickableSpan));
            }
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            CustomClickableSpan touchedSpan = getPressedSpan(textView, spannable, event);
            if (mCustomClickableSpan != null && touchedSpan != mCustomClickableSpan) {
                mCustomClickableSpan.setPressed(false); // 设置为非点击状态
                mCustomClickableSpan = null;
                Selection.removeSelection(spannable);
            }
        } else {
            if (mCustomClickableSpan != null) {
                mCustomClickableSpan.setPressed(false); // 设置为非点击状态
                super.onTouchEvent(textView, spannable, event);
            }
            mCustomClickableSpan = null;
            Selection.removeSelection(spannable);
        }
        return true;
    }

    /**
     * 获取文本的点击链接事件
     *
     * @param textView  操作的TextView对象
     * @param spannable 文本样式
     * @param event     触摸事件
     */
    private CustomClickableSpan getPressedSpan(TextView textView, Spannable spannable, MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        x -= textView.getTotalPaddingLeft();
        y -= textView.getTotalPaddingTop();

        x += textView.getScrollX();
        y += textView.getScrollY();

        Layout layout = textView.getLayout();
        int line = layout.getLineForVertical(y);
        int off = layout.getOffsetForHorizontal(line, x);

        CustomClickableSpan[] link = spannable.getSpans(off, off, CustomClickableSpan.class);
        CustomClickableSpan touchedSpan = null;
        if (link.length > 0) {
            touchedSpan = link[0];
        }
        return touchedSpan;
    }

}