//
// Created by user on 7/22/22.
//

#include <jni.h>
#include <android/log.h>
#include "a.h"


/*
 * This is the local tag used for the following simplified
 * logging macros.  You can change this preprocessor definition
 * before using the other macros to change the tag.
 */
#ifndef LOG_TAG
#define LOG_TAG "MainActivityxxxx"
#endif

/*
 * Simplified macro to send a verbose log message using the current LOG_TAG.
 */
#ifndef LOGV
#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, LOG_TAG, __VA_ARGS__)
#endif

/*
 * Simplified macro to send a debug log message using the current LOG_TAG.
 */
#ifndef LOGD
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, LOG_TAG, __VA_ARGS__)
#endif

/*
 * Simplified macro to send an info log message using the current LOG_TAG.
 */
#ifndef LOGI
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)
#endif

/*
 * Simplified macro to send a warning log message using the current LOG_TAG.
 */
#ifndef LOGW
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN, LOG_TAG, __VA_ARGS__)
#endif

/*
 * Simplified macro to send an error log message using the current LOG_TAG.
 */
#ifndef LOGE
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, __VA_ARGS__)
#endif

extern "C" void __gcov_flush();

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_nativecodecoveragetest_MainActivity_callNative(JNIEnv* env,jobject thiz) {
    return add(20, 30);
}

extern "C"
JNIEXPORT void JNICALL
Java_com_example_nativecodecoveragetest_MainActivity_nativeFlush(JNIEnv* env,jobject thiz) {
    LOGD("gcov flush");
    __gcov_flush();
}