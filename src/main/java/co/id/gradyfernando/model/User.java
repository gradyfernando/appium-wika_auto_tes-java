package co.id.gradyfernando.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private String email;
    private String iduser;
    private List<Akses> list_akses;
    private List<String> menu;
    private String nama;
    private int num_role;
    private String redirect;
    private String token;
    private String username;
    private List<Instruksi> list_perintah;
    private final String nip;
    private String nohp;
    private final String fotoprofile;
    private final String email2;
    private final String lembarview;
    private final String filtertglsurat;
    private final String opsi;
    private final String lockaddsuratkeluar;
    private final String lastlogin;
    private final String nik;
    private final String expireddate;
    private final String startdate;
    private final String notification_connected;
    private final Boolean pin_created;

    // Default Constructor (matches Kotlin's default values)
    public User() {
        this.email = "";
        this.iduser = "";
        this.list_akses = new ArrayList<>();
        this.menu = new ArrayList<>();
        this.nama = "";
        this.num_role = 0;
        this.redirect = "";
        this.token = "";
        this.username = "";
        this.list_perintah = new ArrayList<>();
        this.nip = "";
        this.nohp = "";
        this.fotoprofile = "";
        this.email2 = "";
        this.lembarview = "";
        this.filtertglsurat = "";
        this.opsi = "";
        this.lockaddsuratkeluar = "";
        this.lastlogin = "";
        this.nik = "";
        this.expireddate = "";
        this.startdate = "";
        this.notification_connected = "";
        this.pin_created = false;
    }

    // Full Constructor
    public User(String email, String iduser, List<Akses> list_akses, List<String> menu, String nama, int num_role, String redirect, String token, String username, List<Instruksi> list_perintah, String nip, String nohp, String fotoprofile, String email2, String lembarview, String filtertglsurat, String opsi, String lockaddsuratkeluar, String lastlogin, String nik, String expireddate, String startdate, String notification_connected, Boolean pin_created) {
        this.email = email;
        this.iduser = iduser;
        this.list_akses = list_akses;
        this.menu = menu;
        this.nama = nama;
        this.num_role = num_role;
        this.redirect = redirect;
        this.token = token;
        this.username = username;
        this.list_perintah = list_perintah;
        this.nip = nip;
        this.nohp = nohp;
        this.fotoprofile = fotoprofile;
        this.email2 = email2;
        this.lembarview = lembarview;
        this.filtertglsurat = filtertglsurat;
        this.opsi = opsi;
        this.lockaddsuratkeluar = lockaddsuratkeluar;
        this.lastlogin = lastlogin;
        this.nik = nik;
        this.expireddate = expireddate;
        this.startdate = startdate;
        this.notification_connected = notification_connected;
        this.pin_created = pin_created;
    }

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getIduser() { return iduser; }
    public void setIduser(String iduser) { this.iduser = iduser; }

    public List<Akses> getList_akses() { return list_akses; }
    public void setList_akses(List<Akses> list_akses) { this.list_akses = list_akses; }

    public List<String> getMenu() { return menu; }
    public void setMenu(List<String> menu) { this.menu = menu; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public int getNum_role() { return num_role; }
    public void setNum_role(int num_role) { this.num_role = num_role; }

    public String getRedirect() { return redirect; }
    public void setRedirect(String redirect) { this.redirect = redirect; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public List<Instruksi> getList_perintah() { return list_perintah; }
    public void setList_perintah(List<Instruksi> list_perintah) { this.list_perintah = list_perintah; }

    public String getNip() { return nip; }

    public String getNohp() { return nohp; }
    public void setNohp(String nohp) { this.nohp = nohp; }

    public String getFotoprofile() { return fotoprofile; }

    public String getEmail2() { return email2; }

    public String getLembarview() { return lembarview; }

    public String getFiltertglsurat() { return filtertglsurat; }

    public String getOpsi() { return opsi; }

    public String getLockaddsuratkeluar() { return lockaddsuratkeluar; }

    public String getLastlogin() { return lastlogin; }

    public String getNik() { return nik; }

    public String getExpireddate() { return expireddate; }

    public String getStartdate() { return startdate; }

    public String getNotification_connected() { return notification_connected; }

    public Boolean getPin_created() { return pin_created; }

    // Equals, HashCode, and ToString (To mimic Kotlin Data Class behavior)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return num_role == user.num_role && Objects.equals(email, user.email) && Objects.equals(iduser, user.iduser) && Objects.equals(list_akses, user.list_akses) && Objects.equals(menu, user.menu) && Objects.equals(nama, user.nama) && Objects.equals(redirect, user.redirect) && Objects.equals(token, user.token) && Objects.equals(username, user.username) && Objects.equals(list_perintah, user.list_perintah) && Objects.equals(nip, user.nip) && Objects.equals(nohp, user.nohp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, iduser, list_akses, menu, nama, num_role, redirect, token, username, list_perintah, nip, nohp);
    }

    @Override
    public String toString() {
        return "User{" + "email='" + email + '\'' + ", iduser='" + iduser + '\'' + ", nama='" + nama + '\'' + '}';
    }
}