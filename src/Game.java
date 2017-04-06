/**
 * Created by RehanHawari on 3/9/2017.
 */
import java.util.Scanner;
import java.util.Random;
import javafx.util.Pair;
import java.util.Arrays;
import java.util.ArrayList;

public class Game {
    private Random random;
    private Liga liga;
    private int jumlahTim;
    private boolean finished;
    private Pair<String, String> pair;
    private String[] subCommand  = {"-g", "-kk", "-km", "-p"};

    public Game(){
        finished = false;
        random = new Random();
        }


    public Game(int x){
        finished = false;
        random = new Random();
        liga = new Liga(x);
    }

    /*
        METHOD MEMPROSES NEXTGAME RANDOM YANG
        TETAP MEMANGGIL NEXTGAME MANUAL 
    */
    public void prosesNextGameRandom(){
        prosesGameRandom();
        if (liga.getIdxMatch() >= liga.getArrNextGame().size()){
            printEndOfGame();
        }
    }

    //MEMPROSES GAME RANDOM
    public void prosesGameRandom(){
        //RANDOM SEBANYAK 13x
        int cnt = random.nextInt(13);

        while (cnt > 0){
            //RANDOM KEY/PERINTAH, NAMATIM, NOMOR PEMAIN 
            String key = subCommand[random.nextInt(subCommand.length)];

            Pair<String, String> pair = liga.prosesNextGame();
            int i = random.nextInt(2);
            String namaTim = (i == 0) ? pair.getKey() : pair.getValue();
            int nomorPemain = liga.getRandomPlayerNum(namaTim);
            
            /*
                SELAMA HASIL RANDOM LEBIH DARI CONSTRAIN SOAL
                KEY AKAN TERUS DI-RANDOM
            */
            while(liga.isOverflow(key, namaTim)){
                key = subCommand[random.nextInt(subCommand.length)];
            }

            prosesCommand(key, namaTim, nomorPemain);
            cnt--;
        
        }

        liga.endOfMatch();
    }

    //METHOD PROSES NEXTGAME MANUAL
    public void prosesNextGameManual(String key, String namaTim, int nomorPemain){
        prosesCommand(key, namaTim, nomorPemain);
    }

    //METHOD UNTUK EKSEKUSI PERINTAH
    public void prosesCommand(String key, String namaTim, int nomorPemain){
        if (key.equals("-g")){
            liga.updateGol(namaTim, nomorPemain);
        }
        else if(key.equals("-kk")){
            liga.updateKartuKuning(namaTim, nomorPemain);
        }
        else if(key.equals("-km")){
            liga.updateKartuMerah(namaTim, nomorPemain);
        }
        else if(key.equals("-p")){
            liga.updatePelanggaran(namaTim, nomorPemain);
        }
    }

    //PROSES NEXTGAME ALL, MEMANGGIL METHOD NEXTGAME RANDOM
    public void prosesNextGameAll(){
        while(liga.getIdxMatch() < liga.getArrNextGame().size()){
            prosesGameRandom();
        }
        printEndOfGame();
    }

    //ALL METHOD SHOW
    public void prosesShowKlasemen(){
        liga.showKlasemen();
    }

    public void prosesShowCetakGol(){
        liga.showPencetakGol();
    }

    public void prosesShowTim(String namaTim){
        liga.showTim(namaTim);
    }

    public void prosesShowPemain(String namaTim, int nomorPemain, String namaPemain){
        Pair<Integer, String> pairPemain = new Pair<Integer, String>(nomorPemain, namaPemain);
        liga.showPemain(namaTim, pairPemain);
    }

    public Pair<String, String> prosesShowNextGame(){
        pair = liga.prosesNextGame();
        System.out.printf("%s vs %s\n", pair.getKey(), pair.getValue());
        return pair;
    }

    public void printEndOfGame(){
        setFinished(true);
        System.out.printf("CS LEAGUE MUSIM INI TELAH USAI!\n\n");
        System.out.printf("CHAMPION : Tim %s\n\n", liga.getChampion());
        System.out.printf("KLASEMEN AKHIR:\n\n");
        prosesShowKlasemen();
        System.out.println();
        System.out.printf("TOP SCORE:\n\n");
        prosesShowCetakGol();
        System.out.printf("GOODBYE!");
    }

    //SETTER GETTER
    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean bool) {
        finished = bool;
    }

    public ArrayList<String> getSubCommand(){
        ArrayList<String> arr = new ArrayList<String>(Arrays.asList(subCommand));
        return arr;
    }

    public Liga getLiga(){
        return liga;
    }

}
