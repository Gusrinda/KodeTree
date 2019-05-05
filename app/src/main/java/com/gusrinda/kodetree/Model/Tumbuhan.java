package com.gusrinda.kodetree.Model;

public class Tumbuhan {
    private String Nama;
    private String Latitude;
    private String Longitude;
    private String imgUrl;

    public Tumbuhan(String Nama, String Latitude, String Longitude, String imgUrl) {
        this.Nama = Nama;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.imgUrl = imgUrl;
    }

    public Tumbuhan() {
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getNama() {
        return Nama;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
