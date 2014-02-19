---
layout: default
title: 声音信号处理

---
##声音信号处理
Spectrogram

声音信号就是一段振动波，将声音信号进行分帧，对每一帧进行FFT傅里叶变换得到该帧的频谱，将音频信号的全部帧的频谱合起来就是该音频信号随时间变换的频谱图，称为声谱Spectrogram。

Cepstrum

将整个音频信号做傅里叶变换，得到频谱图是随频率变换的波形图，将该波形图再次傅里叶变换，得到倒谱cepstrum，它的低频部分即为频谱图的包络的频率，称为倒谱系数。

Mel频率

对频谱进行Mel变换，使之符合人耳对声音的感知。过滤人耳不敏感的部分。

ref  
[http://blog.csdn.net/zouxy09/article/details/9156785](http://blog.csdn.net/zouxy09/article/details/9156785)