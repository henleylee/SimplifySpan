package com.liyunlong.simplifyspan.style;

import android.graphics.Bitmap;

import com.liyunlong.simplifyspan.other.SpecialConvertMode;
import com.liyunlong.simplifyspan.other.SpecialGravity;

/**
 * 特殊图片样式
 */
public class SpecialImageStyle extends BaseSpecialStyle {

    private static final String DEFAULT_IMAGE_PLACEHOLDER = "image";

    private Bitmap bitmap; // Bitmap对象
    private int width; // Bitmap宽度(单位：px)
    private int height; // Bitmap高度(单位：px)

    /**
     * 构造方法
     *
     * @param bitmap Bitmap对象
     */
    public SpecialImageStyle(Bitmap bitmap) {
        this(DEFAULT_IMAGE_PLACEHOLDER, bitmap);
    }

    /**
     * 构造方法
     *
     * @param specialText 特殊样式文本
     * @param bitmap      Bitmap对象
     */
    public SpecialImageStyle(String specialText, Bitmap bitmap) {
        super(specialText);
        this.bitmap = bitmap;
    }

    /**
     * 构造方法
     *
     * @param bitmap Bitmap对象
     * @param width  Bitmap宽度(单位：px)
     * @param height Bitmap高度(单位：px)
     */
    public SpecialImageStyle(Bitmap bitmap, int width, int height) {
        this(DEFAULT_IMAGE_PLACEHOLDER, bitmap, width, height);
    }

    /**
     * 构造方法
     *
     * @param specialText 特殊样式文本
     * @param bitmap      Bitmap对象
     * @param width       Bitmap宽度(单位：px)
     * @param height      Bitmap高度(单位：px)
     */
    public SpecialImageStyle(String specialText, Bitmap bitmap, int width, int height) {
        super(specialText);
        this.bitmap = bitmap;
        this.width = width;
        this.height = height;
    }

    /**
     * 构造方法
     *
     * @param bitmap  Bitmap对象
     * @param width   Bitmap宽度(单位：px)
     * @param height  Bitmap高度(单位：px)
     * @param gravity Bitmap对齐方式
     */
    public SpecialImageStyle(Bitmap bitmap, int width, int height, SpecialGravity gravity) {
        this(DEFAULT_IMAGE_PLACEHOLDER, bitmap, width, height, gravity);
    }

    /**
     * 构造方法
     *
     * @param specialText 特殊样式文本
     * @param bitmap      Bitmap对象
     * @param width       Bitmap宽度(单位：px)
     * @param height      Bitmap高度(单位：px)
     * @param gravity     Bitmap对齐方式
     */
    public SpecialImageStyle(String specialText, Bitmap bitmap, int width, int height, SpecialGravity gravity) {
        super(specialText);
        this.bitmap = bitmap;
        this.width = width;
        this.height = height;
        this.gravity = gravity;
    }

    /**
     * 设置对齐方式
     *
     * @param gravity 对齐方式({@link SpecialGravity#TOP}、{@link SpecialGravity#CENTER}、{@link SpecialGravity#BOTTOM}之一)
     */
    public SpecialImageStyle setGravity(SpecialGravity gravity) {
        this.gravity = gravity;
        return this;
    }

    /**
     * 设置转换模式
     *
     * @param convertMode 转换模式({@link SpecialConvertMode#ONLY_FIRST}、{@link SpecialConvertMode#ALL}、{@link SpecialConvertMode#ONLY_LAST}之一)
     */
    public SpecialImageStyle setConvertMode(SpecialConvertMode convertMode) {
        this.convertMode = convertMode;
        return this;
    }

    /**
     * 返回Bitmap对象
     */
    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * 返回Bitmap宽度(单位：px)
     */
    public int getWidth() {
        return width;
    }

    /**
     * 返回Bitmap高度(单位：px)
     */
    public int getHeight() {
        return height;
    }

}