package com.liyunlong.simplifyspan.span;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ThumbnailUtils;
import android.text.style.ReplacementSpan;

import com.liyunlong.simplifyspan.style.SpecialLabelStyle;

/**
 * 自定义{@link ReplacementSpan}
 *
 * @author liyunlong
 * @date 2016/12/12 11:39
 */
public class CustomLabelSpan extends ReplacementSpan {

    private SpecialLabelStyle mSpecialLabelStyle; // 特殊标签样式
    private String mSpecialText; // 特殊文本
    private float mFinalWidth; // 最终宽度(单位：px)
    private float mFinalHeight; // 最终高度(单位：px)
    private int mSpecialTextHeight = 0; // 特殊文本高度
    private float mSpecialTextWidth = 0; // 特殊文本宽度
    private int mLineTextHeight = 0; // 文本行高(单位：px)
    private int mLinetextBaselineOffset = 0;
    private int mSpecialTextBaselineOffset = 0;
    private float mLabelBackgroundRadius; // 背景圆角
    private Bitmap mBackgroundBitmap; // 背景图片
    private String mNormalSizeText; // 正常字体文本

    private int mPaddingTop = 0; // 上边距
    private int mPaddingBottom = 0; // 下边距
    private int mPaddingLeft = 0; // 左边距
    private int mPaddingRight = 0; // 右边距
    private boolean isLabelBackgroundCenter = true; // 背景是否居中
    private boolean isDrawBitmap = false; // 是否需要绘制图片
    private RectF backgroundRect; // 背景区域矩形
    private boolean isInit = true; // 是否初始化

    public CustomLabelSpan(String normalSizeText, SpecialLabelStyle specialLabelStyle) {
        this.mNormalSizeText = normalSizeText;
        this.mSpecialLabelStyle = specialLabelStyle;
        this.mSpecialText = mSpecialLabelStyle.getSpecialText();
        this.mBackgroundBitmap = mSpecialLabelStyle.getBackgroundBitmap();
        if (mBackgroundBitmap == null) { // 判断背景图片是否为空
            this.isDrawBitmap = false;
            this.mLabelBackgroundRadius = mSpecialLabelStyle.getLabelBackgroundRadius();
            if (this.mLabelBackgroundRadius > 0) {
                this.backgroundRect = new RectF();
            }
        } else {
            this.isDrawBitmap = true;
        }
        initPadding();
    }

    private void initPadding() {
        // 如果指定了背景尺寸，则忽略边距
        if (mSpecialLabelStyle.getLabelBackgroundHeight() > 0 || mSpecialLabelStyle.getLabelBackgroundWidth() > 0) {
            return;
        }

        int allPadding = mSpecialLabelStyle.getPadding(); // 整体边距
        // 将整体边距作为上边距和下边距
        this.mPaddingTop = allPadding;
        this.mPaddingBottom = allPadding;
        // 初始化左边距
        int paddingLeft = mSpecialLabelStyle.getPaddingLeft();
        if (paddingLeft > 0) { // 判断左边距是否大于0
            this.mPaddingLeft = paddingLeft;
        } else {
            this.mPaddingLeft = allPadding;
        }
        // 初始化右边距
        int paddingRight = mSpecialLabelStyle.getPaddingRight();
        if (paddingRight > 0) { // 判断右边距是否大于0
            this.mPaddingRight = paddingRight;
        } else {
            this.mPaddingRight = allPadding;
        }
        // 如果上下左右的边距都不为0，则背景不居中
        if (mPaddingTop > 0 || mPaddingBottom > 0 || mPaddingLeft > 0 || mPaddingRight > 0) {
            this.isLabelBackgroundCenter = false;
        } else {
            this.isLabelBackgroundCenter = true;
        }
    }

    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt metricsInt) {
        if (isInit) {
            isInit = false;
            initFinalHeight(paint);
            initFinalWidth(paint);

            // 重新创建背景图片
            if (isDrawBitmap) { // 需要绘制图片
                Bitmap newBitmap = ThumbnailUtils.extractThumbnail(mBackgroundBitmap, Math.round(mFinalWidth), Math.round(mFinalHeight));
                if (null != newBitmap) {
                    mBackgroundBitmap.recycle();
                    mBackgroundBitmap = newBitmap;
                }
            }
        }

        return Math.round(mFinalWidth);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        float labelTextSize = mSpecialLabelStyle.getLabelTextSize();
        if (labelTextSize > 0 && labelTextSize != paint.getTextSize()) { // 设置画笔字体大小
            paint.setTextSize(labelTextSize);
        }

        float newStartY = y;
        int newTextY = y;
        // 根据对齐方式计算背景和文本的Y坐标
        switch (mSpecialLabelStyle.getGravity()) {
            case TOP:
                newStartY -= (mLineTextHeight - mLinetextBaselineOffset);
                newTextY -= (mLineTextHeight - mSpecialTextHeight - (mLinetextBaselineOffset - mSpecialTextBaselineOffset) - mPaddingTop);
                break;
            case CENTER:
                newStartY -= (mLineTextHeight / 2 + mFinalHeight / 2 - mLinetextBaselineOffset);
                newTextY -= (mLineTextHeight / 2 - mSpecialTextHeight / 2 - (mLinetextBaselineOffset - mSpecialTextBaselineOffset));
                break;
            case BOTTOM:
                newStartY -= mFinalHeight - mLinetextBaselineOffset;
                newTextY -= mPaddingBottom - (mLinetextBaselineOffset - mSpecialTextBaselineOffset);
                break;
        }

        if (isDrawBitmap) { // 需要绘制图片
            // 绘制背景图片
            canvas.drawBitmap(mBackgroundBitmap, x, newStartY, paint);
        } else { // 不需要绘制图片
            paint.setColor(mSpecialLabelStyle.getLabelBackgroundColor());
            if (mLabelBackgroundRadius > 0) { // 判断背景圆角是否大于0
                backgroundRect.top = newStartY;
                backgroundRect.bottom = newStartY + mFinalHeight;
                backgroundRect.left = x;
                backgroundRect.right = x + mFinalWidth;
                if (mSpecialLabelStyle.isShowBackgroundBorder()) { // 判断是否显示描边
                    float borderWidth = mSpecialLabelStyle.getLabelBackgroundBorderWidth();
                    // 绘制背景区域
                    canvas.drawRect(x, newStartY, x + mFinalWidth, newStartY + mFinalHeight, paint);

                    // 绘制描边
                    paint.setColor(mSpecialLabelStyle.getLabelBackgroundBorderColor());
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(borderWidth);
                    canvas.drawRoundRect(backgroundRect, mLabelBackgroundRadius, mLabelBackgroundRadius, paint);

                    // 恢复画笔
                    paint.setStyle(Paint.Style.FILL);
                } else {
                    // 绘制背景区域
                    canvas.drawRoundRect(backgroundRect, mLabelBackgroundRadius, mLabelBackgroundRadius, paint);
                }
            } else {
                // 绘制背景区域
                canvas.drawRect(x, newStartY, x + mFinalWidth, newStartY + mFinalHeight, paint);

                if (mSpecialLabelStyle.isShowBackgroundBorder()) { // 判断是否显示描边
                    // 绘制描边
                    paint.setColor(mSpecialLabelStyle.getLabelBackgroundBorderColor());
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setStrokeWidth(mSpecialLabelStyle.getLabelBackgroundBorderWidth());
                    canvas.drawRect(x, newStartY, x + mFinalWidth, newStartY + mFinalHeight, paint);

                    // 恢复画笔
                    paint.setStyle(Paint.Style.FILL);
                }
            }
        }

        paint.setColor(mSpecialLabelStyle.getLabelTextColor());
        float newTextX;
        if (isLabelBackgroundCenter) { // 判断背景是否居中
            newTextX = x + Math.round(mFinalWidth / 2f - mSpecialTextWidth / 2f);
        } else {
            newTextX = x + mPaddingLeft;
        }
        if (mSpecialLabelStyle.isTextBold()) { // 判断是否加粗
            paint.setFakeBoldText(true);
        }
        // 绘制标签文本
        canvas.drawText(mSpecialText, newTextX, newTextY, paint);
    }

    /**
     * 初始化最终宽度
     */
    private float initFinalWidth(Paint paint) {
        if (mFinalWidth <= 0) {
            float labelTextSize = mSpecialLabelStyle.getLabelTextSize();
            if (labelTextSize > 0 && labelTextSize != paint.getTextSize()) {
                paint.setTextSize(labelTextSize);
            }

            int labelBgWidth = mSpecialLabelStyle.getLabelBackgroundWidth();
            mSpecialTextWidth = paint.measureText(mSpecialText, 0, mSpecialText.length());
            if (labelBgWidth > 0 && labelBgWidth > mSpecialTextWidth) {
                mFinalWidth = labelBgWidth;
            } else {
                mFinalWidth = mSpecialTextWidth + mPaddingLeft + mPaddingRight;
            }
        }

        return mFinalWidth;
    }

    /**
     * 初始化最终高度
     */
    private float initFinalHeight(Paint paint) {
        if (mFinalHeight <= 0) {
            int labelBgHeight = mSpecialLabelStyle.getLabelBackgroundHeight();

            Rect specialTextRect = new Rect();
            paint.getTextBounds(mNormalSizeText, 0, mNormalSizeText.length(), specialTextRect);
            mLineTextHeight = specialTextRect.height();
            mLinetextBaselineOffset = specialTextRect.bottom;

            float labelTextSize = mSpecialLabelStyle.getLabelTextSize();
            if (labelTextSize > 0 && labelTextSize != paint.getTextSize()) {
                paint.setTextSize(labelTextSize);
            }

            paint.getTextBounds(mSpecialText, 0, mSpecialText.length(), specialTextRect);
            mSpecialTextHeight = specialTextRect.height();
            mSpecialTextBaselineOffset = specialTextRect.bottom;

            if (labelBgHeight > 0 && labelBgHeight > mSpecialTextHeight && labelBgHeight <= mLineTextHeight) {
                mFinalHeight = labelBgHeight;
            } else {
                mFinalHeight = mSpecialTextHeight + mPaddingTop + mPaddingBottom;
            }

            if (mFinalHeight > mLineTextHeight) {
                mFinalHeight = mLineTextHeight;
            }
        }

        return mFinalHeight;
    }

}