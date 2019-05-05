package com.gusrinda.kodetree.Model;

public class Trivia {
    private String namaPohon, namaLatin, jenisBatang, jenisAkar, jenisDaun, manfaat, urlFoto;

    /**
     *
     * @param namaPohon untuk nama pohon
     * @param namaLatin untuk nama latin dari pohon
     * @param jenisBatang untuk jenis batang dari pohon
     * @param jenisAkar untuk jenis akar dari pohon
     * @param jenisDaun untuk jenis daun dari pohon
     * @param manfaat untuk manfaat dari pohon
     * @param urlFoto untuk URL foto pohon dari firebase storage
     */
    public Trivia(String namaPohon, String namaLatin, String jenisBatang, String jenisAkar, String jenisDaun, String manfaat, String urlFoto) {
        this.namaPohon = namaPohon;
        this.namaLatin = namaLatin;
        this.jenisBatang = jenisBatang;
        this.jenisAkar = jenisAkar;
        this.jenisDaun = jenisDaun;
        this.manfaat = manfaat;
        this.urlFoto = urlFoto;
    }

    /**
     *
     * @return nama dari pohon
     */
    public String getNamaPohon() {
        return namaPohon;
    }


    /**
     *
     * @return nama latin dari pohon
     */
    public String getNamaLatin() {
        return namaLatin;
    }


    /**
     *
     * @return jenis batang dari pohon
     */
    public String getJenisBatang() {
        return jenisBatang;
    }


    /**
     *
     * @return jenis akar dari pohon
     */
    public String getJenisAkar() {
        return jenisAkar;
    }


    /**
     *
     * @return jenis daun dari pohon
     */
    public String getJenisDaun() {
        return jenisDaun;
    }

    /**
     *
     * @return manfaat dari pohon
     */
    public String getManfaat() {
        return manfaat;
    }

    /**
     *
     * @return URL foto pohon dari firebase storage
     */
    public String getUrlFoto() {
        return urlFoto;
    }
}
