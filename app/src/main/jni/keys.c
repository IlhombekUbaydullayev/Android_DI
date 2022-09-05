#include <jni.h>
JNIEXPORT jstring JNICALL
Java_com_example_androiddi_utils_Keys_getPublicKey(JNIEnv *env, jobject instance) {
    return (*env)->  NewStringUTF(env, "long_public_key");
}
JNIEXPORT jstring JNICALL
Java_com_example_androiddi_utils_Keys_getPrivateKey(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "long_private_key");
}