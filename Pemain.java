/**
 * Created by RehanHawari on 3/9/2017.
 */
public class Pemain {
    private String namaPemain;
    private int nomorPemain;
    private int jumlahGol;
    private int jumlahPelanggaran;
    private int jumlahKartuKuning;
    private int jumlahKartuMerah;

    public Pemain(){
    }

    public Pemain(String namaPemain, int nomorPemain) {
        this.namaPemain = namaPemain;
        this.nomorPemain = nomorPemain;
        this.jumlahGol = 0;
        this.jumlahPelanggaran = 0;
        this.jumlahKartuKuning = 0;
        this.jumlahKartuMerah = 0;
    }

    //METHOD SHOW DATA PEMAIN
    public void show(){
        System.out.printf("Nomor : %d\n", nomorPemain);
        System.out.printf("Nama : %s\n", namaPemain);
        System.out.printf("Gol : %d\n", jumlahGol);
        System.out.printf("Pelanggaran : %d\n", jumlahPelanggaran);
        System.out.printf("Kartu Kuning : %d\n", jumlahKartuKuning);
        System.out.printf("Kartu Merah : %d\n", jumlahKartuMerah);
    }

    //SETTER & GETTER
    public String getNamaPemain() {
        return namaPemain;
    }

    public void setNamaPemain(String namaPemain) {
        this.namaPemain = namaPemain;
    }

    public int getNomorPemain() {
        return nomorPemain;
    }

    public void setNomorPemain(int nomorPemain) {
        this.nomorPemain = nomorPemain;
    }

    public int getJumlahGol() {
        return jumlahGol;
    }

    public void setJumlahGol(int jumlahGol) {
        this.jumlahGol = jumlahGol;
    }

    public int getJumlahPelanggaran() {
        return jumlahPelanggaran;
    }

    public void setJumlahPelanggaran(int jumlahPelanggaran) {
        this.jumlahPelanggaran = jumlahPelanggaran;
    }

    public int getJumlahKartuKuning() {
        return jumlahKartuKuning;
    }

    public void setJumlahKartuKuning(int jumlahKartuKuning) {
        this.jumlahKartuKuning = jumlahKartuKuning;
    }

    public int getJumlahKartuMerah() {
        return jumlahKartuMerah;
    }

    public void setJumlahKartuMerah(int jumlahKartuMerah) {
        this.jumlahKartuMerah = jumlahKartuMerah;
    }
}
