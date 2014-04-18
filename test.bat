@echo off
cd /d %~dp0
adb push libs/armeabi/BinderTest  /data/local/tmp/bindertest
adb shell chmod 755  /data/local/tmp/bindertest
pause