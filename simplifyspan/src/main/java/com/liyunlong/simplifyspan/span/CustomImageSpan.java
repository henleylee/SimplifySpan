package com.liyunlong.simplifyspan.span;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

import com.liyunlong.simplifyspan.other.SpecialGravity;

import java.lang.ref.WeakReference;

/**
 * 自定义{@link ImageSpan}
 *
 * @author liyunlong
 * @date 2016/12/12 11:39
 */
public class CustomImageSpan extends ImageSpan {

    private WeakReference<Drawable> drawableWeakReference; // Drawable的弱引用
    private SpecialGravity gravity; // 对齐方式
    private Rect mRect = new Rect(); // 绘制区域矩形
    private String mNormalSizeText;// 正常字体文本

    public CustomImageSpan(Context context, String normalSizeText, Bitmap bitmap, SpecialGravity gravity) {
        super(context, bitmap, ALIGN_BASELINE);
        this.gravity = gravity;
        this.mNormalSizeText = normalSizeText;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable drawable = getCachedDrawable();
        int drawableHeight = drawable.getIntrinsicHeight();
        int fontDescent = paint.getFontMetricsInt().descent;
        paint.getTextBounds(mNormalSizeText, 0, mNormalSizeText.length(), mRect);
        int textHeight = mRect.height();
        if (drawableHeight > textHeight) { // 如果图片高度大于文本高度，则直接绘制图片
            super.draw(canvas, text, start, end, x, top, y, bottom, paint);
            return;
        }

        canvas.save();

        // 根据对齐方式计算画布在Y轴需要移动的位置
        int baseTransY = bottom - drawable.getBounds().bottom - fontDescent + mRect.bottom;
        int transY = 0;
        switch (gravity) {
            case TOP:
                transY = baseTransY - (textHeight - drawableHeight);
                break;
            case CENTER:
                transY = baseTransY - (textHeight / 2 - drawableHeight / 2);
                break;
            case BOTTOM:
                transY = baseTransY;
                break;
        }
        canvas.translate(x, transY); // 移动画布
        drawable.draw(canvas); // 绘制图片
        canvas.restore();
    }

    /**
     * 获取缓存的Drawable
     */
    private Drawable getCachedDrawable() {
        Drawable drawable = null;

        if (drawableWeakReference != null) {
            drawable = drawableWeakReference.get();
        }

        if (drawable == null) {
            drawable = getDrawable();
            drawableWeakReference = new WeakReference<>(drawable);
        }

        return drawable;
    }

}  