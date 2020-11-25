package id.ac.ui.cs.mobileprogramming.rizkhiph.lab.model;

public class AccessPoint {
    private String name;
    private String mac;
    private int strength;

    public AccessPoint(String name, String mac, int strength) {
        this.name = name;
        this.mac = mac;
        this.strength = strength;
    }

    public String getMac() {
        return mac;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
