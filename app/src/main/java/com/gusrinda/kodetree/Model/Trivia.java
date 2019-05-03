package com.gusrinda.kodetree.Model;

public class Trivia {
    private String namaPohon, namaLatin, jenisBatang, jenisAkar, jenisDaun, manfaat;

    public Trivia(String namaPohon, String namaLatin, String jenisBatang, String jenisAkar, String jenisDaun, String manfaat) {
        this.namaPohon = namaPohon;
        this.namaLatin = namaLatin;
        this.jenisBatang = jenisBatang;
        this.jenisAkar = jenisAkar;
        this.jenisDaun = jenisDaun;
        this.manfaat = manfaat;
    }

    public String getNamaPohon() {
        return namaPohon;
    }

    public void setNamaPohon(String namaPohon) {
        this.namaPohon = namaPohon;
    }

    public String getNamaLatin() {
        return namaLatin;
    }

    public void setNamaLatin(String namaLatin) {
        this.namaLatin = namaLatin;
    }

    public String getJenisBatang() {
        return jenisBatang;
    }

    public void setJenisBatang(String jenisBatang) {
        this.jenisBatang = jenisBatang;
    }

    public String getJenisAkar() {
        return jenisAkar;
    }

    public void setJenisAkar(String jenisAkar) {
        this.jenisAkar = jenisAkar;
    }

    public String getJenisDaun() {
        return jenisDaun;
    }

    public void setJenisDaun(String jenisDaun) {
        this.jenisDaun = jenisDaun;
    }

    public String getManfaat() {
        return manfaat;
    }

    public void setManfaat(String manfaat) {
        this.manfaat = manfaat;
    }
}
