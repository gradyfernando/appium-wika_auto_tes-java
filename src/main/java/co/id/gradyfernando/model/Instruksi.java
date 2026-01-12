package co.id.gradyfernando.model;

import java.util.Objects;

public class Instruksi {

    private String t_userid;
    private String iddisposisiperintah;
    private String t_ipaddress;
    private String nama;
    private String t_updatetime;
    private String t_inserttime;
    private String checked;
    private boolean selected;

    // Default Constructor
    public Instruksi() {
        this.t_userid = "";
        this.iddisposisiperintah = "";
        this.t_ipaddress = "";
        this.nama = "";
        this.t_updatetime = "";
        this.t_inserttime = "";
        this.checked = "";
        this.selected = false;
    }

    // Full Constructor
    public Instruksi(String t_userid, String iddisposisiperintah, String t_ipaddress, String nama, String t_updatetime, String t_inserttime, String checked, boolean selected) {
        this.t_userid = t_userid;
        this.iddisposisiperintah = iddisposisiperintah;
        this.t_ipaddress = t_ipaddress;
        this.nama = nama;
        this.t_updatetime = t_updatetime;
        this.t_inserttime = t_inserttime;
        this.checked = checked;
        this.selected = selected;
    }

    // Getters and Setters
    public String getT_userid() { return t_userid; }
    public void setT_userid(String t_userid) { this.t_userid = t_userid; }

    public String getIddisposisiperintah() { return iddisposisiperintah; }
    public void setIddisposisiperintah(String iddisposisiperintah) { this.iddisposisiperintah = iddisposisiperintah; }

    public String getT_ipaddress() { return t_ipaddress; }
    public void setT_ipaddress(String t_ipaddress) { this.t_ipaddress = t_ipaddress; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getT_updatetime() { return t_updatetime; }
    public void setT_updatetime(String t_updatetime) { this.t_updatetime = t_updatetime; }

    public String getT_inserttime() { return t_inserttime; }
    public void setT_inserttime(String t_inserttime) { this.t_inserttime = t_inserttime; }

    public String getChecked() { return checked; }
    public void setChecked(String checked) { this.checked = checked; }

    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }

    // Data Class boilerplate
    @Override
    public String toString() {
        return "Instruksi{" +
                "nama='" + nama + '\'' +
                ", selected=" + selected +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruksi instruksi = (Instruksi) o;
        return selected == instruksi.selected && 
               Objects.equals(t_userid, instruksi.t_userid) && 
               Objects.equals(iddisposisiperintah, instruksi.iddisposisiperintah) && 
               Objects.equals(nama, instruksi.nama);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t_userid, iddisposisiperintah, nama, selected);
    }
}