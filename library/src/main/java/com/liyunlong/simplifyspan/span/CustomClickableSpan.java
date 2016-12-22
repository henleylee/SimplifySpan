package com.liyunlong.simplifyspan.span;

import android.graphics.Color;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.liyunlong.simplifyspan.other.OnClickableSpanListener;
import com.liyunlong.simplifyspan.style.SpecialTextStyle;

/**
 * 自定义{@link ClickableSpan}
 */
public class CustomClickableSpan extends ClickableSpan {

    private SpecialTextStyle mSpecialTextStyle; // 特殊文本样式
    private boolean isPressed; // 是否点击状态
    private int mtSpecialTextColorNormal; // 正常状态字体颜色
    private int mtSpecialTextColorPressed; // 按下状态字体颜色
    private int mClickableSpanBackgroundColorNormal; // 正常状态背景颜色
    private int mClickableSpanBackgroundColorPressed; // 按下状态背景颜色

    public CustomClickableSpan(SpecialTextStyle specialTextStyle) {
        this.mSpecialTextStyle = specialTextStyle;
        this.mtSpecialTextColorNormal = mSpecialTextStyle.getSpecialTextColor();
        this.mtSpecialTextColorPressed = mSpecialTextStyle.getClickableSpanPressedTextColor();
        this.mClickableSpanBackgroundColorNormal = mSpecialTextStyle.getSpecialTextBackgroundColor();
        this.mClickableSpanBackgroundColorPressed = mSpecialTextStyle.getClickableSpanPressedBackgroundColor();
    }

    @Override
    public void onClick(View view) {
        OnClickableSpanListener onClickableSpanListener = mSpecialTextStyle.getOnClickListener();
        if (onClickableSpanListener != null) { // 判断点击监听事件是否为空
            TextView textView = (TextView) view;
            Spanned spanned = (Spanned) textView.getText();
            int start = spanned.getSpanStart(this);
            int end = spanned.getSpanEnd(this);
            onClickableSpanListener.onClick(textView, spanned.subSequence(start, end).toString());
        }
    }

    /**
     * 设置是否点击状态
     *
     * @param isPressed 是否点击状态
     */
    public void setPressed(boolean isPressed) {
        this.isPressed = isPressed;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);

        // 设置正常状态和按下状态的字体颜色
        if (mtSpecialTextColorNormal != 0) { // 判断正常状态字体颜色是否为0
            if (mtSpecialTextColorPressed != 0) { // 判断按下状态字体颜色是否为0
                ds.setColor(isPressed ? mtSpecialTextColorPressed : mtSpecialTextColorNormal);
            } else {
                ds.setColor(mtSpecialTextColorNormal);
            }
        }

        // 设置正常状态和按下状态的背景颜色
        if (mClickableSpanBackgroundColorPressed != 0) { // 判断按下状态背景颜色是否为0
            ds.bgColor = isPressed ? mClickableSpanBackgroundColorPressed : mClickableSpanBackgroundColorNormal == 0 ? Color.TRANSPARENT : mClickableSpanBackgroundColorNormal;
        } else if (mClickableSpanBackgroundColorNormal != 0) { // 判断正常状态背景颜色是否为0
            ds.bgColor = mClickableSpanBackgroundColorNormal;
        }

        // 设置是否显示下划线
        if (mSpecialTextStyle.isShowClickableSpanUnderline()) { // 判断是否显示下划线
            ds.setUnderlineText(true);
        } else {
            ds.setUnderlineText(false);
        }
    }
}
