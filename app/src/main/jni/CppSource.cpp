//
// Created by khiyara99 on 02/12/20.
//

#include <string>
#include "CppSource.h"

jobject jniCreateAccessPointInfo(JNIEnv *env, jobject, jstring name, jstring mac, int strength){
    jobject accessPointObject = NULL;
    jclass accessPointClass = env->FindClass("id/ac/ui/cs/mobileprogramming/rizkhiph/lab/model/AccessPoint");

    jfieldID nameField  = env->GetFieldID(accessPointClass, "name", "Ljava/lang/String;");
    jfieldID macField  = env->GetFieldID(accessPointClass, "mac", "Ljava/lang/String;");
    jfieldID strengthField   = env->GetFieldID(accessPointClass, "strength", "I");

    accessPointObject = env->AllocObject(accessPointClass);

    // append to prove it is from C++ code
    const char *outputName = env->GetStringUTFChars(name, NULL);
    std::string tmp = outputName;
    std::string appendedName = tmp + " FROM C++";
    jstring nameJS = env->NewStringUTF(appendedName.c_str());
    env->SetObjectField(accessPointObject, nameField, nameJS);

    const char *outputMac = env->GetStringUTFChars(name, NULL);
    std::string tmp2 = outputMac;
    std::string appendedMac = tmp2 + " FROM C++";
    jstring macJS = env->NewStringUTF(appendedMac.c_str());
    env->SetObjectField(accessPointObject, macField, macJS);

//    jstring macJS = env->NewStringUTF(mac);
//    env->SetObjectField(accessPointObject, macField, macJS);

    env->SetIntField(accessPointObject, strengthField, (jint)strength);

    return accessPointObject;
}