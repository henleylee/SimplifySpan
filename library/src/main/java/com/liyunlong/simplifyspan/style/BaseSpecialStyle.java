package com.liyunlong.simplifyspan.style;

import com.liyunlong.simplifyspan.other.SpecialConvertMode;
import com.liyunlong.simplifyspan.other.SpecialGravity;

/**
 * 特殊样式基类
 */
public class BaseSpecialStyle {

    private String specialText; // 特殊样式文本
    private int[] startPositions;  // 特殊样式文本开始位置
    protected SpecialGravity gravity = SpecialGravity.CENTER; // 特殊样式文本对齐方式
    protected SpecialConvertMode convertMode = SpecialConvertMode.ONLY_FIRST; // 特殊样式文本转换模式

    /**
     * 构造方法
     *
     * @param specialText 特殊样式文本
     */
    public BaseSpecialStyle(String specialText) {
        this.specialText = specialText;
    }

    /**
     * 返回特殊样式文本
     */
    public String getSpecialText() {
        return specialText;
    }

    /**
     * 返回特殊样式文本对齐方式
     */
    public SpecialGravity getGravity() {
        return gravity;
    }

    /**
     * 返回特殊样式文本转换模式
     */
    public SpecialConvertMode getConvertMode() {
        return convertMode;
    }

    /**
     * 设置特殊样式文本开始位置
     *
     * @param startPositions 特殊样式文本开始位置
     */
    public void setStartPositions(int[] startPositions) {
        this.startPositions = startPositions;
    }

    /**
     * 返回特殊样式文本开始位置
     */
    public int[] getStartPositions() {
        return startPositions;
    }

}