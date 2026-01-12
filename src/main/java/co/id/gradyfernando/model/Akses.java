package co.id.gradyfernando.model;

import java.util.Objects;

public class Akses {

    private String berakhir;
    private String idjabatan;
    private String idrole;
    private String idunit;
    private String iduser;
    private String isplt;
    private String mulai;
    private String namajabatan;
    private String namarole;
    private String namaunit;
    private String isadmin;

    // Default Constructor
    public Akses() {
        this.berakhir = "";
        this.idjabatan = "";
        this.idrole = "";
        this.idunit = "";
        this.iduser = "";
        this.isplt = "";
        this.mulai = "";
        this.namajabatan = "";
        this.namarole = "";
        this.namaunit = "";
        this.isadmin = "";
    }

    // Full Constructor
    public Akses(String berakhir, String idjabatan, String idrole, String idunit, String iduser, 
                 String isplt, String mulai, String namajabatan, String namarole, 
                 String namaunit, String isadmin) {
        this.berakhir = berakhir;
        this.idjabatan = idjabatan;
        this.idrole = idrole;
        this.idunit = idunit;
        this.iduser = iduser;
        this.isplt = isplt;
        this.mulai = mulai;
        this.namajabatan = namajabatan;
        this.namarole = namarole;
        this.namaunit = namaunit;
        this.isadmin = isadmin;
    }

    // Getters and Setters
    public String getBerakhir() { return berakhir; }
    public void setBerakhir(String berakhir) { this.berakhir = berakhir; }

    public String getIdjabatan() { return idjabatan; }
    public void setIdjabatan(String idjabatan) { this.idjabatan = idjabatan; }

    public String getIdrole() { return idrole; }
    public void setIdrole(String idrole) { this.idrole = idrole; }

    public String getIdunit() { return idunit; }
    public void setIdunit(String idunit) { this.idunit = idunit; }

    public String getIduser() { return iduser; }
    public void setIduser(String iduser) { this.iduser = iduser; }

    public String getIsplt() { return isplt; }
    public void setIsplt(String isplt) { this.isplt = isplt; }

    public String getMulai() { return mulai; }
    public void setMulai(String mulai) { this.mulai = mulai; }

    public String getNamajabatan() { return namajabatan; }
    public void setNamajabatan(String namajabatan) { this.namajabatan = namajabatan; }

    public String getNamarole() { return namarole; }
    public void setNamarole(String namarole) { this.namarole = namarole; }

    public String getNamaunit() { return namaunit; }
    public void setNamaunit(String namaunit) { this.namaunit = namaunit; }

    public String getIsadmin() { return isadmin; }
    public void setIsadmin(String isadmin) { this.isadmin = isadmin; }

    // Data Class boilerplate
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Akses akses = (Akses) o;
        return Objects.equals(berakhir, akses.berakhir) && 
               Objects.equals(idjabatan, akses.idjabatan) && 
               Objects.equals(idrole, akses.idrole) && 
               Objects.equals(iduser, akses.iduser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(berakhir, idjabatan, idrole, iduser);
    }

    @Override
    public String toString() {
        return "Akses{" +
                "namajabatan='" + namajabatan + '\'' +
                ", namaunit='" + namaunit + '\'' +
                '}';
    }
}