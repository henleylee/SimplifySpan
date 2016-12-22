package com.liyunlong.simplifyspan;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.ThumbnailUtils;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.liyunlong.simplifyspan.other.CustomLinkMovementMethod;
import com.liyunlong.simplifyspan.other.OnClickableSpanListener;
import com.liyunlong.simplifyspan.other.SpecialConvertMode;
import com.liyunlong.simplifyspan.span.CustomAbsoluteSizeSpan;
import com.liyunlong.simplifyspan.span.CustomClickableSpan;
import com.liyunlong.simplifyspan.span.CustomImageSpan;
import com.liyunlong.simplifyspan.span.CustomLabelSpan;
import com.liyunlong.simplifyspan.style.BaseSpecialStyle;
import com.liyunlong.simplifyspan.style.SpecialImageStyle;
import com.liyunlong.simplifyspan.style.SpecialLabelStyle;
import com.liyunlong.simplifyspan.style.SpecialRawSpanStyle;
import com.liyunlong.simplifyspan.style.SpecialTextStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 特殊样式构建辅助类
 */
public class SimplifySpanBuild {

    private Context mContext; // 上下文
    private TextView mTextView; // TextView对象
    private StringBuilder mNormalSizeText; // 正常样式文本字符串变量
    private StringBuilder mStringBuilder; // 最终文本字符串变量
    private StringBuilder mBeforeStringBuilder; // 需要添加到前面的字符串变量
    private List<BaseSpecialStyle> mFinalSpecialStyles; // 最终特殊样式集合
    private List<BaseSpecialStyle> mBeforeSpecialStyles; // 需要添加到前面的特殊样式集合

    /**
     * 构造方法
     *
     * @param context  上下文
     * @param textView TextView对象
     */
    public SimplifySpanBuild(Context context, TextView textView) {
        this(context, textView, null);
    }

    /**
     * 构造方法
     *
     * @param context              上下文
     * @param textView             TextView对象
     * @param initializeNormalText 初始化正常文本
     */
    public SimplifySpanBuild(Context context, TextView textView, String initializeNormalText) {
        init(context, textView, initializeNormalText);
    }

    /**
     * 构造方法
     *
     * @param context              上下文
     * @param textView             TextView对象
     * @param initializeNormalText 初始化正常文本
     * @param normalSpecialStyles  正常文本样式
     */
    public SimplifySpanBuild(Context context, TextView textView, String initializeNormalText, BaseSpecialStyle... normalSpecialStyles) {
        init(context, textView, initializeNormalText, normalSpecialStyles);
    }

    /**
     * 初始化
     *
     * @param context              上下文
     * @param textView             TextView对象
     * @param initializeNormalText 初始化正常文本
     * @param normalSpecialStyles  正常文本样式
     */
    private void init(Context context, TextView textView, String initializeNormalText, BaseSpecialStyle... normalSpecialStyles) {
        this.mContext = context;
        this.mTextView = textView;
        this.mStringBuilder = new StringBuilder(TextUtils.isEmpty(initializeNormalText) ? "" : initializeNormalText);
        this.mBeforeStringBuilder = new StringBuilder("");
        this.mNormalSizeText = new StringBuilder("");
        this.mFinalSpecialStyles = new ArrayList<>();
        this.mBeforeSpecialStyles = new ArrayList<>();

        if (!TextUtils.isEmpty(initializeNormalText)) {
            if (normalSpecialStyles != null && normalSpecialStyles.length > 0) {
                buildNormalSpecialStyles(false, 0, initializeNormalText, normalSpecialStyles);
            } else {
                mNormalSizeText.append(initializeNormalText);
            }
        }
    }

    private void buildNormalSpecialStyles(boolean isAddToBeforeSpecialStyle, int initStartPosition, String finalText, BaseSpecialStyle... normalSpecialStyles) {
        Map<String, Boolean> delTextTagMap = new HashMap<>();
        List<Integer> otherSpecialTextStartPosition;
        for (BaseSpecialStyle specialStyle : normalSpecialStyles) {
            String specialText = specialStyle.getSpecialText();
            // 如果特殊样式文本为空，或最终文本不包含特殊样式文本，则结束此次循环
            if (TextUtils.isEmpty(specialText) || !finalText.contains(specialText)) {
                continue;
            }

            int specialTextLength = specialText.length(); // 特殊样式文本长度
            SpecialConvertMode convertMode = specialStyle.getConvertMode(); // 特殊样式内容转换模式
            switch (convertMode) {
                case ONLY_FIRST: // 仅第一个
                    specialStyle.setStartPositions(new int[]{initStartPosition + finalText.indexOf(specialText)});
                    break;
                case ONLY_LAST: // 仅最后一个
                    specialStyle.setStartPositions(new int[]{initStartPosition + finalText.lastIndexOf(specialText)});
                    break;
                case ALL: // 全部
                    int firstSpecialTextStartPosition = finalText.indexOf(specialText);
                    otherSpecialTextStartPosition = new ArrayList<>();
                    otherSpecialTextStartPosition.add(firstSpecialTextStartPosition);
                    int previousSpecialTextStartPosition = firstSpecialTextStartPosition + specialTextLength;
                    boolean hasNext = true;
                    while (hasNext) {
                        int nextSpecialTextStartPosition = finalText.indexOf(specialText, previousSpecialTextStartPosition);
                        if (nextSpecialTextStartPosition != -1) {
                            otherSpecialTextStartPosition.add(nextSpecialTextStartPosition);
                            previousSpecialTextStartPosition = nextSpecialTextStartPosition + specialTextLength;
                        } else {
                            hasNext = false;
                        }
                    }

                    int[] startPositions = new int[otherSpecialTextStartPosition.size()];
                    for (int i = 0; i < otherSpecialTextStartPosition.size(); i++) {
                        startPositions[i] = initStartPosition + otherSpecialTextStartPosition.get(i);
                    }
                    specialStyle.setStartPositions(startPositions);
                    break;
            }

            // 处理需要剔除的内容
            if (specialStyle instanceof SpecialTextStyle) {
                SpecialTextStyle specialTextUnit = (SpecialTextStyle) specialStyle;
                if (specialTextUnit.getTextSize() > 0) {
                    if (specialStyle.getStartPositions().length > 1) {
                        delTextTagMap.put(specialText, true);
                    } else {
                        delTextTagMap.put(specialText, false);
                    }
                }
            } else if (specialStyle instanceof SpecialImageStyle || specialStyle instanceof SpecialLabelStyle) {
                if (specialStyle.getStartPositions().length > 1) {
                    delTextTagMap.put(specialText, true);
                } else {
                    delTextTagMap.put(specialText, false);
                }
            }
        }

        if (!delTextTagMap.isEmpty()) { // 判断需要剔除的内容是否为空
            for (Map.Entry<String, Boolean> entry : delTextTagMap.entrySet()) {
                if (entry.getValue()) {
                    // 删除全部
                    finalText = finalText.replaceAll(entry.getKey(), "");
                } else {
                    // 删除第一个
                    finalText = finalText.replace(entry.getKey(), "");
                }
            }
        }

        if (isAddToBeforeSpecialStyle) {
            mNormalSizeText.insert(0, finalText);
            mBeforeSpecialStyles.addAll(Arrays.asList(normalSpecialStyles));
        } else {
            mNormalSizeText.append(finalText);
            mFinalSpecialStyles.addAll(Arrays.asList(normalSpecialStyles));
        }
    }

    /**
     * 追加特殊样式内容
     *
     * @param specialStyle 特殊样式集合(不支持{@link SpecialConvertMode})
     */
    public SimplifySpanBuild appendSpecialStyle(BaseSpecialStyle specialStyle) {
        if (specialStyle == null) {
            return this;
        }

        String specialText = specialStyle.getSpecialText();
        if (TextUtils.isEmpty(specialText)) { // 判断特殊样式文本是否为空
            return this;
        }

        // 设置特殊样式文本开始位置
        specialStyle.setStartPositions(new int[]{mStringBuilder.length()});
        mStringBuilder.append(specialText);
        mFinalSpecialStyles.add(specialStyle);
        return this;
    }

    /**
     * 追加正常样式文本
     *
     * @param text                正常样式文本
     * @param normalSpecialStyles 正常样式集合(支持{@link SpecialConvertMode})
     */
    public SimplifySpanBuild appendNormalText(String text, BaseSpecialStyle... normalSpecialStyles) {
        if (TextUtils.isEmpty(text)) { // 判断正常样式文本是否为空
            return this;
        }

        if (normalSpecialStyles != null && normalSpecialStyles.length > 0) { // 判断正常样式集合是否为空
            buildNormalSpecialStyles(false, mStringBuilder.length(), text, normalSpecialStyles);
        } else {
            mNormalSizeText.append(text);
        }

        mStringBuilder.append(text);
        return this;
    }

    /**
     * 追加特殊样式内容到最前面(在现有的内容前面添加特殊样式内容)
     *
     * @param specialStyle 特殊样式内容(不支持{@link SpecialConvertMode})
     */
    public SimplifySpanBuild appendSpecialStyleToFirst(BaseSpecialStyle specialStyle) {
        if (specialStyle == null) {
            return this;
        }

        String specialText = specialStyle.getSpecialText();
        if (TextUtils.isEmpty(specialText)) { // 判断特殊样式文本是否为空
            return this;
        }

        // 设置特殊样式文本开始位置
        int curBeforeFirstPosition = mBeforeStringBuilder.length();
        specialStyle.setStartPositions(new int[]{curBeforeFirstPosition});
        mBeforeStringBuilder.insert(curBeforeFirstPosition, specialText);
        mBeforeSpecialStyles.add(specialStyle);
        return this;
    }

    /**
     * 追加正常样式文本到最前面(在现有的内容前面添加正常样式文本)
     *
     * @param text                正常样式文本
     * @param normalSpecialStyles 正常样式集合(支持{@link SpecialConvertMode})
     */
    public SimplifySpanBuild appendNormalTextToFirst(String text, BaseSpecialStyle... normalSpecialStyles) {
        if (TextUtils.isEmpty(text)) { // 判断正常样式文本是否为空
            return this;
        }

        if (normalSpecialStyles != null && normalSpecialStyles.length > 0) { // 判断正常样式集合是否为空
            buildNormalSpecialStyles(true, mBeforeStringBuilder.length(), text, normalSpecialStyles);
        } else {
            mNormalSizeText.append(text);
        }

        mBeforeStringBuilder.append(text);
        return this;
    }

    /**
     * 构建特殊样式
     *
     * @return {@code SpannableStringBuilder}
     */
    public SpannableStringBuilder build() {
        if (mBeforeStringBuilder.length() > 0) {
            mStringBuilder.insert(0, mBeforeStringBuilder);

            // reset SpecialUnit start pos
            if (!mFinalSpecialStyles.isEmpty()) {
                for (BaseSpecialStyle specialUnit : mFinalSpecialStyles) {
                    for (int i = 0; i < specialUnit.getStartPositions().length; i++) {
                        int oldStartPos = specialUnit.getStartPositions()[i];
                        specialUnit.getStartPositions()[i] = oldStartPos + mBeforeStringBuilder.length();
                    }
                }
            }
        }

        if (!mBeforeSpecialStyles.isEmpty()) {
            mFinalSpecialStyles.addAll(mBeforeSpecialStyles);
        }

        if (mStringBuilder.length() == 0) {
            return null;
        }
        if (mFinalSpecialStyles.isEmpty())
            return new SpannableStringBuilder(mStringBuilder.toString());

        if (mNormalSizeText.length() == 0) {
            mNormalSizeText.append(mStringBuilder);
        }

        String normalSizeText = mNormalSizeText.toString();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(mStringBuilder);
        if (!mFinalSpecialStyles.isEmpty()) {
            loopSpecialStyle(normalSizeText, spannableStringBuilder);
        }

        return spannableStringBuilder;
    }

    private void loopSpecialStyle(String normalSizeText, SpannableStringBuilder spannableStringBuilder) {
        for (BaseSpecialStyle specialStyle : mFinalSpecialStyles) { // 遍历最终特殊样式
            String specialText = specialStyle.getSpecialText();
            if (TextUtils.isEmpty(specialText)) { // 如果特殊样式文本为空，则结束此次循环
                continue;
            }

            handleSpecialStyle(normalSizeText, spannableStringBuilder, specialStyle, specialText);
        }
    }

    private void handleSpecialStyle(String normalSizeText, SpannableStringBuilder spannableStringBuilder, BaseSpecialStyle specialStyle, String specialText) {
        int specialTextLength = specialText.length();

        if (specialStyle instanceof SpecialTextStyle) { // 文本样式
            // text span
            SpecialTextStyle specialTextStyle = (SpecialTextStyle) specialStyle;
            boolean isInitClickListener = false;
            final OnClickableSpanListener onClickListener = specialTextStyle.getOnClickListener();
            for (int startPosition : specialTextStyle.getStartPositions()) {
                // 设置文本字体颜色
                if (specialTextStyle.getSpecialTextColor() != 0) {
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(specialTextStyle.getSpecialTextColor()), startPosition, startPosition + specialTextLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                // 设置文本背景颜色
                if (specialTextStyle.getSpecialTextBackgroundColor() != 0 && null == onClickListener) {
                    spannableStringBuilder.setSpan(new BackgroundColorSpan(specialTextStyle.getSpecialTextBackgroundColor()), startPosition, startPosition + specialTextLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                // 添加下划线
                if (specialTextStyle.isShowUnderline()) {
                    spannableStringBuilder.setSpan(new UnderlineSpan(), startPosition, startPosition + specialTextLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                // 添加删除线
                if (specialTextStyle.isShowStrikeThrough()) {
                    spannableStringBuilder.setSpan(new StrikethroughSpan(), startPosition, startPosition + specialTextLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                // 设置文本加粗
                if (specialTextStyle.isTextBold()) {
                    spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD), startPosition, startPosition + specialTextLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                // 设置文本字体大小
                if (specialTextStyle.getTextSize() > 0) {
                    spannableStringBuilder.setSpan(new CustomAbsoluteSizeSpan(normalSizeText, specialTextStyle.getSpecialText(), Math.round(specialTextStyle.getTextSize()), mTextView, specialTextStyle.getGravity()), startPosition, startPosition + specialTextLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }

                // 设置文本点击事件
                if (onClickListener != null) {
                    if (!isInitClickListener) {
                        isInitClickListener = true;
                        mTextView.setMovementMethod(CustomLinkMovementMethod.getInstance());
                    }
                    spannableStringBuilder.setSpan(new CustomClickableSpan(specialTextStyle), startPosition, startPosition + specialTextLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        } else if (specialStyle instanceof SpecialImageStyle) { // 图片样式
            // image Span
            SpecialImageStyle specialImageStyle = (SpecialImageStyle) specialStyle;
            Bitmap bitmap = specialImageStyle.getBitmap();
            Bitmap finalBitmap = bitmap;
            int imgWidth = specialImageStyle.getWidth();
            int imgHeight = specialImageStyle.getHeight();
            if (imgWidth > 0 && imgHeight > 0) {
                int bitWidth = bitmap.getWidth();
                int bitHeight = bitmap.getHeight();
                if (imgWidth < bitWidth && imgHeight < bitHeight) {
                    Bitmap tempBitmap = ThumbnailUtils.extractThumbnail(bitmap, imgWidth, imgHeight);
                    if (null != tempBitmap) {
                        bitmap.recycle();
                        finalBitmap = tempBitmap;
                    }
                }
            }

            for (int startPosition : specialImageStyle.getStartPositions()) {
                spannableStringBuilder.setSpan(new CustomImageSpan(mContext, normalSizeText, finalBitmap, specialImageStyle.getGravity()), startPosition, startPosition + specialTextLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        } else if (specialStyle instanceof SpecialLabelStyle) { // 标签样式
            // label span
            SpecialLabelStyle specialLabelStyle = (SpecialLabelStyle) specialStyle;
            specialLabelStyle.convertLabelTextSize(sp2px(mContext, specialLabelStyle.getLabelTextSize()));

            for (int startPosition : specialLabelStyle.getStartPositions()) {
                spannableStringBuilder.setSpan(new CustomLabelSpan(normalSizeText, specialLabelStyle), startPosition, startPosition + specialTextLength, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        } else if (specialStyle instanceof SpecialRawSpanStyle) {
            // raw span
            SpecialRawSpanStyle specialRawSpanStyle = (SpecialRawSpanStyle) specialStyle;

            int startPosition = specialRawSpanStyle.getStartPositions()[0];
            spannableStringBuilder.setSpan(specialRawSpanStyle.getSpanObj(), startPosition, startPosition + specialTextLength, specialRawSpanStyle.getFlags());

            // 暂时无法支持所有
        }
    }

    private static float sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return spValue * scale;
    }

}
