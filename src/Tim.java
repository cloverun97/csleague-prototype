/**
 * Created by RehanHawari on 3/9/2017.
 */
import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;

public class Tim {
    private Random random;
    private String namaTim;
    private int peringkat;
    private int jumlahMenang;
    private int jumlahKalah;
    private int jumlahSeri;
    private int jumlahPoin;
    private int jumlahGol;
    private int jumlahKebobolan;
    private Pemain[] pemain;
    private ArrayList<String> arrNamaPemain = new ArrayList<String>();      //array untuk menyimpan nama pemain ke-i yang ada pada tim sesuai indeks pemain[i]
    private ArrayList<Integer> arrNomorPemain = new ArrayList<Integer>();

    String[] arrayOfPlayerName = {"Arnold", "Kaidou", "Chopper", "Pica", "Enel",
            "Zoro", "Pedro", "Beckman", "Ace", "Shiryu", "Sakazuki", "Marco",
            "Garp", "Dadan", "Sengoku", "Sanji", "Magellan", "Dragon", "Sabo",
            "Smoker", "Luffy", "Franky", "Borsalino", "Buggy", "Crocodile",
            "Shanks", "Yasopp", "Coby", "Burgess", "Usopp", "Law", "Kid", "Bege",
            "Yonji", "Doffy", "Edward", "Mihawk", "Shanks", "Jinbei", "Killer",
            "Robin", "Roger", "Shiki", "Rayleigh", "Robb", "Kuma", "Moriah",
            "Teach", "Pagaya", "Conis", "Hachi", "Brook", "Kinemon", "Vergo",
            "Caesar", "Momo", "Mohji", "Cabaji", "Jozu", "Vista", "Doma", "Augur",
            "Drake", "Ivankov", "Charlotte", "Bellamy", "Demaro", "Dorry",
            "Brogy", "Kuro", "Zeff", "Gin", "Pearl", "Alvide", "Apoo", "Kuzan",
            "Nami", "Brook", "Hancock", "Koala"};


    public Tim(){
        random = new Random();
    }

    public Tim(String namaTim) {
        random = new Random();
        this.namaTim = namaTim;
        this.peringkat = 0;
        this.jumlahMenang = 0;
        this.jumlahKalah = 0;
        this.jumlahSeri = 0;
        this.jumlahPoin = 0;
        this.jumlahGol = 0;
        this.jumlahKebobolan = 0;
        pemain = new Pemain[5];

        for (int i = 0; i < 5; i++){
            int randomNama = random.nextInt(arrayOfPlayerName.length);
            
            //SELAMA randomNama TIDAK ADA PADA arrNamaPemain, 
            //MAKA AKAN TERUS LOOP
            while (arrNamaPemain.contains(arrayOfPlayerName[randomNama])){
                randomNama = random.nextInt(arrayOfPlayerName.length);
            }

            arrNamaPemain.add(arrayOfPlayerName[randomNama]);
            int randomNomor = random.nextInt(99) + 1; //offset 1, [0,99) +1
            
            //SELAMA randomNomor TIDAK ADA PADA arrNomorPemain, 
            //MAKA AKAN TERUS LOOP
            while(arrNomorPemain.contains(randomNomor)){
                randomNomor = random.nextInt(99) + 1;
            }

            arrNomorPemain.add(randomNomor); 
            pemain[i] = new Pemain(arrayOfPlayerName[randomNama], randomNomor);
        }
    }

    //ALL METHODS UPDATE
    public void updateGol(int nomorPemain){
        int i = arrNomorPemain.indexOf(nomorPemain);
        pemain[i].setJumlahGol(pemain[i].getJumlahGol() + 1);
    }

    public void updateKartuKuning(int nomorPemain){
        int i = arrNomorPemain.indexOf(nomorPemain);
        pemain[i].setJumlahKartuKuning(pemain[i].getJumlahKartuKuning() + 1);
        //in case kartu kuningnya 2
        if (pemain[i].getJumlahKartuKuning() == 2){
            updateKartuMerah(nomorPemain);
        }
        updatePelanggaran(nomorPemain);
    }

    public void updateKartuMerah(int nomorPemain){
        int i = arrNomorPemain.indexOf(nomorPemain);
        pemain[i].setJumlahKartuMerah(pemain[i].getJumlahKartuMerah() + 1);
        updatePelanggaran(nomorPemain);
    }

    public void updatePelanggaran(int nomorPemain){
        int i = arrNomorPemain.indexOf(nomorPemain);
        pemain[i].setJumlahPelanggaran(pemain[i].getJumlahPelanggaran() + 1);
    }

    //METHOD UNTUK MEMBANTU METHOD PENCETAK GOL PADA LIGA
    public Pair<Integer, Pair<String, String>> getAllPemain(int idxPemain){
        Pair<String, String> subPair = new Pair<String, String>(pemain[idxPemain].getNamaPemain(), namaTim);
        Pair<Integer, Pair<String, String>> ret = new Pair<Integer, Pair<String, String>>(pemain[idxPemain].getJumlahGol(), subPair);
        return ret;
    }

    //ALL METHODS SHOW
    public void showDaftarPemain(){
        System.out.print("Nomor |   Nama   | Gol | Pelanggaran | Kartu Kuning | Kartu Merah\n");
        System.out.print("--------------------------------------------------------------\n");
        for (int i = 0; i < 5; i++) {
            String nomor = pemain[i].getNomorPemain() + "";
            String gol = pemain[i].getJumlahGol() + "";
            String pelanggaran = pemain[i].getJumlahPelanggaran() + "";
            String kk = pemain[i].getJumlahKartuKuning() + "";
            String km = pemain[i].getJumlahKartuMerah() + "";
            System.out.printf("%s| %s|%s|%s|%s|%s\n", Text.center(nomor, 6), Text.leftPad(pemain[i].getNamaPemain(), 9), 
                                Text.center(gol, 5), Text.center(pelanggaran, 13), Text.center(kk, 14), Text.center(km, 12));
        }
    }

    public void showPemain(Pair<Integer, String> pair){
        int nomorPemain = pair.getKey();
        String namaPemain = pair.getValue();
        if (arrNomorPemain.contains(nomorPemain)){
            int i = arrNomorPemain.indexOf(nomorPemain);
            pemain[i].show();
        }
        else if (arrNamaPemain.contains(namaPemain)){
            int i = arrNamaPemain.indexOf(namaPemain);
            pemain[i].show();           
        }
        else{
            if (nomorPemain == 0){
                System.out.printf("ERROR: Pemain dengan nama %s bukan anggota dari Tim %s!\n", namaPemain, namaTim);
            }
            else { System.out.printf("ERROR: Pemain dengan nomor %d bukan anggota dari Tim %s!\n", nomorPemain, namaTim);
            }
        }
    }

    //SETTER & GETTER
    public void setNamaTim(String namaTim) { this.namaTim = namaTim; }

    public String getNamaTim() { return namaTim; }

    public void setJumlahGol(int jumlahGol) { this.jumlahGol = jumlahGol; }

    public int getJumlahGol() { return jumlahGol; }

    public int getPeringkat() { return peringkat; }

    public void setPeringkat(int peringkat) { this.peringkat = peringkat; }

    public int getJumlahMenang() { return jumlahMenang; }

    public void setJumlahMenang(int jumlahMenang) { this.jumlahMenang = jumlahMenang; }

    public int getJumlahKalah() { return jumlahKalah; }

    public void setJumlahKalah(int jumlahKalah) { this.jumlahKalah = jumlahKalah; }

    public int getJumlahSeri() { return jumlahSeri; }

    public void setJumlahSeri(int jumlahSeri) { this.jumlahSeri = jumlahSeri; }

    public int getJumlahPoin() { return jumlahPoin; }

    public void setJumlahPoin(int jumlahPoin) { this.jumlahPoin = jumlahPoin; }

    public int getJumlahKebobolan() { return jumlahKebobolan; }

    public void setJumlahKebobolan(int jumlahKebobolan) { this.jumlahKebobolan = jumlahKebobolan; }

    public Pemain[] getPemain() { return pemain; }

    public void setPemain(Pemain[] pemain) { this.pemain = pemain; }

    public ArrayList<String> getArrNamaPemain() { return arrNamaPemain; }

    public void setArrNamaPemain(ArrayList<String> arrNamaPemain) { this.arrNamaPemain = arrNamaPemain; }

    public ArrayList<Integer> getArrNomorPemain() { return arrNomorPemain; }

    public void setArrNomorPemain(ArrayList<Integer> arrNomorPemain) { this.arrNomorPemain = arrNomorPemain; }
}
