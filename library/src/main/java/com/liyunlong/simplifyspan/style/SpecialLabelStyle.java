package com.liyunlong.simplifyspan.style;

import android.graphics.Bitmap;

import com.liyunlong.simplifyspan.other.SpecialConvertMode;
import com.liyunlong.simplifyspan.other.SpecialGravity;

/**
 * 特殊标签样式
 */
public class SpecialLabelStyle extends BaseSpecialStyle {

    private int labelTextColor; // 字体颜色
    private float labelTextSize; // 字体大小(单位：sp)
    private Bitmap mBackgroundBitmap; // 背景图片
    private int labelBackgroundColor; // 背景颜色
    private float labelBackgroundRadius; // 背景圆角
    private int labelBackgroundWidth; // 背景宽度(单位：px)
    private int labelBackgroundHeight; // 背景高度(单位：px)
    private int padding; // 边距(单位：px)
    private int paddingLeft; // 左侧边距(单位：px)
    private int paddingRight; // 右侧边距(单位：px)
    private float labelBackgroundBorderWidth; // 描边宽度(单位：px)
    private int labelBackgroundBorderColor; // 描边颜色
    private boolean isShowBackgroundBorder; // 是否显示描边
    private boolean isTextBold; // 是否加粗

    /**
     * 构造方法
     *
     * @param specialText          特殊样式文本
     * @param labelTextColor       标签字体颜色
     * @param labelTextSize        标签字体大小(单位：sp)
     * @param labelBackgroundColor 标签背景颜色
     */
    public SpecialLabelStyle(String specialText, int labelTextColor, float labelTextSize, int labelBackgroundColor) {
        super(specialText);
        this.labelTextSize = labelTextSize;
        this.labelTextColor = labelTextColor;
        this.labelBackgroundColor = labelBackgroundColor;
    }

    /**
     * 构造方法
     *
     * @param specialText           特殊样式文本
     * @param labelTextColor        标签字体颜色
     * @param labelTextSize         标签字体大小(单位：sp)
     * @param labelBackgroundColor  标签背景颜色
     * @param labelBackgroundWidth  标签背景宽度(单位：px)
     * @param labelBackgroundHeight 标签背景高度(单位：px)
     */
    public SpecialLabelStyle(String specialText, int labelTextColor, float labelTextSize, int labelBackgroundColor, int labelBackgroundWidth, int labelBackgroundHeight) {
        super(specialText);
        this.labelTextSize = labelTextSize;
        this.labelTextColor = labelTextColor;
        this.labelBackgroundColor = labelBackgroundColor;
        this.labelBackgroundWidth = labelBackgroundWidth;
        this.labelBackgroundHeight = labelBackgroundHeight;
    }

    /**
     * 构造方法
     *
     * @param specialText    特殊样式文本
     * @param labelTextColor 标签字体颜色
     * @param labelTextSize  标签字体大小(单位：sp)
     * @param bitmap         标签背景图片
     */
    public SpecialLabelStyle(String specialText, int labelTextColor, float labelTextSize, Bitmap bitmap) {
        super(specialText);
        this.labelTextSize = labelTextSize;
        this.labelTextColor = labelTextColor;
        this.mBackgroundBitmap = bitmap;
    }

    /**
     * 构造方法
     *
     * @param specialText           特殊样式文本
     * @param labelTextColor        标签字体颜色
     * @param labelTextSize         标签字体大小(单位：sp)
     * @param bitmap                标签背景图片
     * @param labelBackgroundWidth  标签背景宽度(单位：px)
     * @param labelBackgroundHeight 标签背景高度(单位：px)
     */
    public SpecialLabelStyle(String specialText, int labelTextColor, float labelTextSize, Bitmap bitmap, int labelBackgroundWidth, int labelBackgroundHeight) {
        super(specialText);
        this.labelTextSize = labelTextSize;
        this.labelTextColor = labelTextColor;
        this.mBackgroundBitmap = bitmap;
        this.labelBackgroundWidth = labelBackgroundWidth;
        this.labelBackgroundHeight = labelBackgroundHeight;
    }

    /**
     * 设置标签背景尺寸
     *
     * @param labelBackgroundWidth  背景宽度(单位：px)
     * @param labelBackgroundHeight 背景高度(单位：px)
     */
    public SpecialLabelStyle setLabelBackgroundSize(int labelBackgroundWidth, int labelBackgroundHeight) {
        this.labelBackgroundWidth = labelBackgroundWidth;
        this.labelBackgroundHeight = labelBackgroundHeight;
        return this;
    }

    /**
     * 设置标签背景图片
     *
     * @param bitmap 背景图片
     */
    public SpecialLabelStyle setBackgroundBitmap(Bitmap bitmap) {
        this.mBackgroundBitmap = bitmap;
        return this;
    }

    /**
     * 设置标签边距
     *
     * @param padding 边距(单位：px)
     */
    public SpecialLabelStyle setPadding(int padding) {
        this.padding = padding;
        return this;
    }

    /**
     * 设置标签左侧边距
     *
     * @param paddingLeft 左侧边距(单位：px)
     */
    public SpecialLabelStyle setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
        return this;
    }

    /**
     * 设置标签右侧边距
     *
     * @param paddingRight 右侧边距(单位：px)
     */
    public SpecialLabelStyle setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
        return this;
    }

    /**
     * 设置标签背景圆角
     *
     * @param labelBackgroundRadius 背景圆角
     */
    public SpecialLabelStyle setLabelBackgroundRadius(float labelBackgroundRadius) {
        this.labelBackgroundRadius = labelBackgroundRadius;
        return this;
    }

    /**
     * 设置是否显示标签背景描边
     *
     * @param labelBackgroundBorderColor 描边颜色
     * @param labelBackgroundBorderWidth 描边宽度(单位：px)
     */
    public SpecialLabelStyle showBackgroundBorder(int labelBackgroundBorderColor, float labelBackgroundBorderWidth) {
        this.isShowBackgroundBorder = true;
        this.labelBackgroundBorderColor = labelBackgroundBorderColor;
        this.labelBackgroundBorderWidth = labelBackgroundBorderWidth;
        return this;
    }

    /**
     * 设置是否加粗
     */
    public SpecialLabelStyle setTextBold() {
        this.isTextBold = true;
        return this;
    }

    /**
     * 设置对齐方式
     *
     * @param gravity 对齐方式({@link SpecialGravity#TOP}、{@link SpecialGravity#CENTER}、{@link SpecialGravity#BOTTOM}之一)
     */
    public SpecialLabelStyle setGravity(SpecialGravity gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * 设置转换模式
     *
     * @param convertMode 转换模式({@link SpecialConvertMode#ONLY_FIRST}、{@link SpecialConvertMode#ALL}、{@link SpecialConvertMode#ONLY_LAST}之一)
     */
    public SpecialLabelStyle setConvertMode(SpecialConvertMode convertMode) {
        this.convertMode = convertMode;
        return this;
    }

    /**
     * 返回背景颜色
     */
    public int getLabelBackgroundColor() {
        return labelBackgroundColor;
    }

    /**
     * 返回背景高度(单位：px)
     */
    public int getLabelBackgroundHeight() {
        return labelBackgroundHeight;
    }

    /**
     * 返回背景宽度(单位：px)
     */
    public int getLabelBackgroundWidth() {
        return labelBackgroundWidth;
    }

    /**
     * 返回文本颜色
     */
    public int getLabelTextColor() {
        return labelTextColor;
    }

    /**
     * 返回文本字体大小(单位：sp)
     */
    public float getLabelTextSize() {
        return labelTextSize;
    }

    /**
     * 返回边距(单位：sp)
     */
    public int getPadding() {
        return padding;
    }

    /**
     * 返回左侧边距(单位：sp)
     */
    public int getPaddingLeft() {
        return paddingLeft;
    }

    /**
     * 返回右侧边距(单位：sp)
     */
    public int getPaddingRight() {
        return paddingRight;
    }

    /**
     * 返回背景圆角
     */
    public float getLabelBackgroundRadius() {
        return labelBackgroundRadius;
    }

    /**
     * 是否加粗
     */
    public boolean isTextBold() {
        return isTextBold;
    }

    /**
     * 是否显示背景描边
     */
    public boolean isShowBackgroundBorder() {
        return isShowBackgroundBorder;
    }

    /**
     * 返回背景描边宽度(单位：px)
     */
    public float getLabelBackgroundBorderWidth() {
        return labelBackgroundBorderWidth;
    }

    /**
     * 返回背景描边颜色
     */
    public int getLabelBackgroundBorderColor() {
        return labelBackgroundBorderColor;
    }

    /**
     * 返回背景图片
     */
    public Bitmap getBackgroundBitmap() {
        return mBackgroundBitmap;
    }

    /**
     * Use only in SimplifySpanBuild
     *
     * @param labelTextSize
     */
    public void convertLabelTextSize(float labelTextSize) {
        this.labelTextSize = labelTextSize;
    }
}