package id.ac.ui.cs.mobileprogramming.rizkhiph.lab;

import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.model.AccessPoint;

public class JniManager {
    static {
        System.loadLibrary("native-lib");
    }

    public AccessPoint createAccessPointInfo(){
        AccessPoint accessPoint = new AccessPoint("DummyAccessPoint FROM JAVA", "08:08:08:08:08:08", -999);

        return accessPoint;
    }

    public native AccessPoint nGetAccessPointInfo(String name, String mac, int strength);
}