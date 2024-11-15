#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("timebasedsecuritycode");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("timebasedsecuritycode")
//      }
//    }
extern "C"
JNIEXPORT jstring JNICALL
Java_com_bd_apstech_timebasedsecuritycode_MainActivity_getKey(JNIEnv *env, jclass clazz) {
    return env->NewStringUTF("yourSecretKeyHere");
}