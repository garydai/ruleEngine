---
layout: default
title: finger print

---
##fingerprint


All of them (so far) work in essentially the same way. It is generally a two-step process of submission and lookup. First, the raw audio is used to create a fingerprint, which is then submitted to a third-party server. This server analyzes the fingerprint, compares it to other fingerprints, and decides whether it is sufficiently different from known fingerprints as to issue a new ID.


http://wiki.musicbrainz.org/AudioFingerprint

###echoprint

###chromaprint

Chromaprint, the input audio is converted to the sampling rate 11025 Hz and the frame size is 4096 (0.371 s) with 2/3 overlap.

chroma is simply the fractional part of the base-2 logarithm of frequency.

chroma spectrum, , to be a measure of the
strength of a signal with respect to a given value of chroma

The algorithm consists of ﬁve steps: frame segmentation, feature calculation, correlation calculation, correlation ﬁltering, and thumbnail selection

1.生成频谱图chromagram

1.1	对一帧4096个采样点做FFT，取28到2046HZ的信号幅度。

1.2	预处理，28到2046HZ转化成chroma

    c = log2(f) - [log2(f)]
	
		static const int NUM_BANDS = 12;

		inline double FreqToOctave(double freq, double base = 440.0 / 16.0)
		{
			return log(freq / base) / log(2.0);
		}
		double octave = FreqToOctave(freq);
		double note = NUM_BANDS * (octave - floor(octave)); 

		
2.chroma filter

3.chroma Normalizer

4.基于图像的对比
