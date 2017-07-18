# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in E:\Android-Work\SDK/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 代码混淆压缩比，在0~7之间，默认为5，一般不做修改
#-optimizationpasses 5

# 混合时不使用大小写混合，混合后的类名为小写
#-dontusemixedcaseclassnames

# 指定不去忽略非公共库的类
#-dontskipnonpubliclibraryclasses

# 这句话能够使我们的项目混淆后产生映射文件
# 包含有类名->混淆后类名的映射关系
#-verbose

# 指定不去忽略非公共库的类成员
#-dontskipnonpubliclibraryclassmembers

# 不做预校验，preverify是proguard的四个步骤之一，Android不需要preverify，去掉这一步能够加快混淆速度。
-dontpreverify

# 抛出异常时保留代码行号
#-keepattributes SourceFile,LineNumberTable

# 指定混淆是采用的算法，后面的参数是一个过滤器
# 这个过滤器是谷歌推荐的算法，一般不做更改
#-optimizations !code/simplification/cast,!field/*,!class/merging/*

# 排除native方法
-keepclasseswithmembernames class * {
    native <methods>;
}

-keep class cn.EGGMaster.util.Utils {
    private static java.lang.String sendPosts(java.lang.String, java.lang.String);
    #private static java.lang.String getKey(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String);
}