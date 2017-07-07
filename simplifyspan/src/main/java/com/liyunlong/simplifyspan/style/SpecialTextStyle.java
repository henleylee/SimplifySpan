package com.liyunlong.simplifyspan.style;

import com.liyunlong.simplifyspan.other.OnClickableSpanListener;
import com.liyunlong.simplifyspan.other.SpecialConvertMode;
import com.liyunlong.simplifyspan.other.SpecialGravity;

/**
 * 特殊文本样式
 *
 * @author liyunlong
 * @date 2017/7/7 18:02
 */
public class SpecialTextStyle extends BaseSpecialStyle {

    private int specialTextColor; // 字体颜色
    private int specialTextBackgroundColor; // 背景颜色
    private float textSize; // 字体大小(单位：sp)
    private boolean isShowUnderline; // 是否显示下划线
    private boolean isShowStrikeThrough; // 是否显示删除线
    private boolean isTextBold; // 是否加粗
    private boolean isShowClickableSpanUnderline; // 是否显示可点击文本的下划线
    private OnClickableSpanListener mOnClickListener; // 可点击文本的点击事件监听
    private int mClickableSpanPressedTextColor; // 点击状态字体颜色
    private int mClickableSpanPressedBackgroundColor; // 点击状态背景颜色

    /**
     * 构造方法
     *
     * @param specialText 特殊样式文本
     */
    public SpecialTextStyle(String specialText) {
        super(specialText);
    }

    /**
     * 构造方法
     *
     * @param specialText      特殊样式文本
     * @param specialTextColor 特殊样式文本字体颜色
     */
    public SpecialTextStyle(String specialText, int specialTextColor) {
        this(specialText);
        this.specialTextColor = specialTextColor;
    }

    /**
     * 构造方法
     *
     * @param specialText      特殊样式文本
     * @param specialTextColor 特殊样式文本字体颜色
     * @param textSize         特殊样式文本字体大小(单位：sp)
     */
    public SpecialTextStyle(String specialText, int specialTextColor, float textSize) {
        this(specialText);
        this.specialTextColor = specialTextColor;
        this.textSize = textSize;
    }

    /**
     * 构造方法
     *
     * @param specialText      特殊样式文本
     * @param specialTextColor 特殊样式文本字体颜色
     * @param textSize         特殊样式文本字体大小(单位：sp)
     * @param gravity          特殊样式文本对齐方式
     */
    public SpecialTextStyle(String specialText, int specialTextColor, float textSize, SpecialGravity gravity) {
        this(specialText);
        this.specialTextColor = specialTextColor;
        this.textSize = textSize;
        this.gravity = gravity;
    }

    /**
     * 设置是否加粗
     */
    public SpecialTextStyle setTextBold() {
        isTextBold = true;
        return this;
    }

    /**
     * 设置是否显示下划线
     */
    public SpecialTextStyle showUnderline() {
        isShowUnderline = true;
        return this;
    }

    /**
     * 设置是否显示删除线
     */
    public SpecialTextStyle showStrikeThrough() {
        isShowStrikeThrough = true;
        return this;
    }

    /**
     * 设置字体大小(单位：sp)
     */
    public SpecialTextStyle setTextSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    /**
     * 设置字体颜色
     */
    public SpecialTextStyle setSpecialTextColor(int specialTextColor) {
        this.specialTextColor = specialTextColor;
        return this;
    }

    /**
     * 设置背景颜色
     */
    public SpecialTextStyle setSpecialTextBackgroundColor(int specialTextBackgroundColor) {
        this.specialTextBackgroundColor = specialTextBackgroundColor;
        return this;
    }

    /**
     * 设置对齐方式
     *
     * @param gravity 对齐方式({@link SpecialGravity#TOP}、{@link SpecialGravity#CENTER}、{@link SpecialGravity#BOTTOM}之一)
     */
    public SpecialTextStyle setGravity(SpecialGravity gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * 设置转换模式
     *
     * @param convertMode 转换模式({@link SpecialConvertMode#ONLY_FIRST}、{@link SpecialConvertMode#ALL}、{@link SpecialConvertMode#ONLY_LAST}之一)
     */
    public SpecialTextStyle setConvertMode(SpecialConvertMode convertMode) {
        this.convertMode = convertMode;
        return this;
    }

    /**
     * 设置可点击文本的点击事件监听
     *
     * @param isShowClickableSpanUnderline 是否显示可点击文本的下划线
     * @param onClickListener              可点击文本的点击事件监听
     */
    public SpecialTextStyle setOnClickListener(boolean isShowClickableSpanUnderline, OnClickableSpanListener onClickListener) {
        return setOnClickListener(isShowClickableSpanUnderline, 0, onClickListener);
    }

    /**
     * 设置可点击文本的点击事件监听
     *
     * @param isShowClickableSpanUnderline        是否显示可点击文本的下划线
     * @param clickableSpanPressedBackgroundColor 点击状态背景颜色(不需要设置为0)
     * @param onClickListener                     可点击文本的点击事件监听
     */
    public SpecialTextStyle setOnClickListener(boolean isShowClickableSpanUnderline, int clickableSpanPressedBackgroundColor, OnClickableSpanListener onClickListener) {
        return setOnClickListener(isShowClickableSpanUnderline, clickableSpanPressedBackgroundColor, 0, onClickListener);
    }

    /**
     * 设置可点击文本的点击事件监听
     *
     * @param isShowClickableSpanUnderline        是否显示可点击文本的下划线
     * @param clickableSpanPressedBackgroundColor 点击状态背景颜色(不需要设置为0)
     * @param clickableSpanPressedTextColor       点击状态字体颜色(不需要设置为0)
     * @param onClickListener                     可点击文本的点击事件监听
     */
    public SpecialTextStyle setOnClickListener(boolean isShowClickableSpanUnderline, int clickableSpanPressedBackgroundColor, int clickableSpanPressedTextColor, OnClickableSpanListener onClickListener) {
        this.isShowClickableSpanUnderline = isShowClickableSpanUnderline;
        this.mOnClickListener = onClickListener;
        this.mClickableSpanPressedBackgroundColor = clickableSpanPressedBackgroundColor;
        this.mClickableSpanPressedTextColor = clickableSpanPressedTextColor;
        return this;
    }

    /**
     * 返回字体颜色
     */
    public int getSpecialTextColor() {
        return specialTextColor;
    }

    /**
     * 返回背景颜色
     */
    public int getSpecialTextBackgroundColor() {
        return specialTextBackgroundColor;
    }

    /**
     * 返回字体大小(单位：sp)
     */
    public float getTextSize() {
        return textSize;
    }

    /**
     * 是否显示下划线
     */
    public boolean isShowUnderline() {
        return isShowUnderline;
    }

    /**
     * 是否显示删除线
     */
    public boolean isShowStrikeThrough() {
        return isShowStrikeThrough;
    }

    /**
     * 是否加粗
     */
    public boolean isTextBold() {
        return isTextBold;
    }

    /**
     * 返回是否显示可点击文本的下划线
     */
    public boolean isShowClickableSpanUnderline() {
        return isShowClickableSpanUnderline;
    }

    /**
     * 返回点击状态背景颜色
     */
    public int getClickableSpanPressedBackgroundColor() {
        return mClickableSpanPressedBackgroundColor;
    }

    /**
     * 返回点击状态字体颜色
     */
    public int getClickableSpanPressedTextColor() {
        return mClickableSpanPressedTextColor;
    }

    /**
     * 返回可点击文本的点击事件监听
     */
    public OnClickableSpanListener getOnClickListener() {
        return mOnClickListener;
    }

}