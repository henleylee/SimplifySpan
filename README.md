# SimplifySpan-master —— 丰富定制化文本Span

## 效果演示 ##
#### 标签Span： ####
![](/screenshots/标签Span.png)
#### 丰富定制化文本Span： ####
![](/screenshots/丰富定制化文本Span.png)
#### 图片Span： ####
![](/screenshots/图片Span.png)
#### 可点击的Span： ####
![](/screenshots/可点击的Span.png)

## 开始使用 ##

#### SpecialTextStyle： ####
```java
        SpecialTextStyle specialTextStyle = new SpecialTextStyle("文本")
                .setTextBold()                          // 设置是否加粗
                .showUnderline()                        // 设置是否显示下划线
                .setTextSize(20)                        // 设置字体大小(单位：sp)
                .setSpecialTextColor(Color.RED)         // 设置字体颜色
                .setConvertMode(SpecialConvertMode.ALL) // 设置转换模式
                .setSpecialTextBackgroundColor(Color.YELLOW)// 设置背景颜色
                .setOnClickListener(true, Color.GRAY, Color.WHITE, this);// 设置可点击文本的点击事件监听
```

#### SpecialLabelStyle： ####
```java
        SpecialLabelStyle specialLabelStyle = new SpecialLabelStyle("文本", Color.WHITE, 10, Color.BLUE)
                .setLabelBackgroundRadius(5)            // 设置标签背景圆角
                .setBackgroundBorder(Color.BLACK, 2)    // 设置是否显示标签背景描边
                .setPadding(10)                         // 设置标签边距(单位：px)
                .setPaddingLeft(10)                     // 设置标签左侧边距(单位：px)
                .setPaddingRight(10)                    // 设置标签右侧边距(单位：px)
                .setGravity(SpecialGravity.CENTER);     // 设置对齐方式
```

#### SpecialImageStyle： ####
```java
        SpecialImageStyle specialImageStyle = new SpecialImageStyle(bitmap, 50, 50)
                .setGravity(SpecialGravity.BOTTOM)      // 设置对齐方式
                .setConvertMode(SpecialConvertMode.ALL);// 设置转换模式
```

#### SimplifySpanBuilder： ####
```java
        SimplifySpanBuilder simplifySpanBuilder = new SimplifySpanBuilder(context, textView)
                .appendNormalText(text, normalSpecialStyles)        // 追加正常样式文本
                .appendSpecialStyleToFirst(specialStyle)            // 追加特殊样式内容到最前面(在现有的内容前面添加特殊样式内容)
                .appendNormalTextToFirst(text, normalSpecialStyles);// 追加正常样式文本到最前面(在现有的内容前面添加正常样式文本)
        SpannableStringBuilder spannableStringBuilder = simplifySpanBuilder.build(); // 构建特殊样式
        textView.setText(spannableStringBuilder);
```
