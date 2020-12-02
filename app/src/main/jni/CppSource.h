//
// Created by khiyara99 on 02/12/20.
//

#ifndef LAB_CPPSOURCE_H
#define LAB_CPPSOURCE_H

#include <jni.h>

typedef struct{
    char name[64];
    char mac[64];
    int strength;

}AccessPoint;

jobject jniCreateAccessPointInfo(JNIEnv *env, jobject, jstring name, jstring mac, int strength);

#endif //CPPSOURCE_H