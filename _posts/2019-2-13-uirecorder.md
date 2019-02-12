---
layout: default

title: uirecorder录制UI测试用例

---

## uirecorder录制UI测试用例

1. 官方介绍
UI Recorder is a zero cost UI test case recorder like Selenium IDE.
UI Recorder is more powerful than Selenium IDE!
UI Recorder is easy to use.
测试人员录制之后，用于一些简单的回归测试挺好
2. 使用教程
2.1 pc端
2.1.1 搭建环境
安装node
brew install node

安装selenium
brew install selenium-server-standalone

安装cnpm
npm install -g cnpm --registry=https://registry.npm.taobao.org

安装uirecorder和mocha
cnpm install uirecorder mocha -g

新建文件夹
mkdir test

初始化
cd test 
uirecorder init

开始录制
uirecorder
结束录制，便会生成一个测试用例，默认路径sample/test.spec.js
运行测试用例
npm run server

source run.sh sample/test.spec.js
2.1.2 录制截图


2.1.3 运行测试用例的结果

2.2 安卓端（手机连接电脑，开启usb调试）
2.2.1 搭建环境
安装JDK 1.8 (Java 9 is not supported)

安装Android SDK
brew install android-sdk

设置ANDROID_HOME环境变量

brew install gradle

cnpm i -g macaca-cli

cnpm i -g macaca-android

cd /usr/local/lib/node_modules/macaca-android/node_modules/_uiautomatorwd\@1.0.61\@uiautomatorwd/

./gradlew assembleDebug assembleDebugAndroidTest

macaca server --port 4444

uirecorder init --mobile

开始录制测试用例
uirecorder --mobile sample/test.spec.js

运行测试用例
source run.sh sample/test.spec.js


！！如果有问题，运行macaca doctor，解决红色报警
2.2.2 录制截图

2.2.3 运行测试用例的结果

3. reference
https://macacajs.com/environment-setup
https://github.com/alibaba/uirecorder
