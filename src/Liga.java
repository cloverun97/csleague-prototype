 /**
 * Created by RehanHawari on 3/9/2017.
 */
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Random;
import javafx.util.Pair;
import java.util.Arrays;

public class Liga {
	private Random random;
    private int jumlahTim;
    //POINTER UNTUK ARRAYLIST arrNextGame
    private int idxMatch; 
    private Tim[] tim;
    //ATTIBUT DIBAWAH ADALAH SEMUA ATTRIBUT 
    //SELAMA PERTANDINGAN Tim[a] vs Tim[b] BERLANGSUNG
    private int[] golTim = {0, 0};
    private int[] kkTim = {0, 0};
    private int[] kmTim = {0, 0};
    private int[] pelanggaranTim = {0, 0};

    private Pair<String, String> pairMatchNow;
    private Pair<String, String> pair;
    private ArrayList<Pair<String, String>> arrNextGame;
    //arrNamaTim => UNTUK MENAMPUNG NAMA TIM YANG SUDAH DI-GENERATE, 
    //AGAR TIDAK ADA TIM YANG NAMANYA SAMA
    private ArrayList<String> arrNamaTim;
    //arrPencetakGol => UNTUK MENAMPUNG SEMUA {(jumlahGol, (pemain, tim)} DI LIGA 
    private ArrayList<Pair<Integer, Pair<String, String>>> arrPencetakGol; 

    String[] arrayOfTeamName = {"Gajah", "Rusa", "Belalang", "Kodok", "Kucing",
            "Tupai", "Rajawali", "Siput", "Kumbang", "Semut", "Ular", "Harimau",
            "Kuda", "Serigala", "Panda", "Kadal", "Ayam", "Bebek"};
  
  	public Liga(int jumlahTim){
  		this.jumlahTim = jumlahTim;
	    idxMatch = 0; 
	    arrNextGame = new ArrayList<Pair<String, String>>();
	    arrNamaTim = new ArrayList<String>();
	    arrPencetakGol = new ArrayList<Pair<Integer, Pair<String, String>>>();
  		random = new Random();
  		tim = new Tim[jumlahTim];
  		for (int i = 0; jumlahTim >= 4 && jumlahTim <= 10 && i < jumlahTim; i++){
  			tim[i] = new Tim();
   		}
   		prosesInit();
  	}

  	//METHOD PROSES PERINTAH INIT
    public void prosesInit(){

	    for (int i = 0;  i < jumlahTim; i++){
    		int randomNum = random.nextInt(arrayOfTeamName.length);
    		while (arrNamaTim.contains(arrayOfTeamName[randomNum])){
    			randomNum = random.nextInt(arrayOfTeamName.length);
       		}
			tim[i] = new Tim(arrayOfTeamName[randomNum]);
			arrNamaTim.add(arrayOfTeamName[randomNum]);
	 	}

   		//UNTUK KOMBINASI PERTANDINGAN ROUND ROBIN
		for (int i = 0; i < jumlahTim; i++){
			for(int j = i+1; j < jumlahTim; j++){
				pair = new Pair<String, String>(tim[i].getNamaTim(), tim[j].getNamaTim());
				arrNextGame.add(pair);
			}
		}

		//INSTANSIASI UNTUK ADD PEMAIN KE arrPencetakGol
		for (int i = 0; i < jumlahTim; i++){
			for (int j = 0; j < 5; j++){
				arrPencetakGol.add(tim[i].getAllPemain(j)); //getAllPemain return Pair<Integer, Pair<String, String>>
			}
		}

    }	

    //UPDATE GOL, KEBOBOLAN, KARTU, PELANGGARAN, POIN, KLASEMEN, & PENCETAK GOL;
    public void updateGol(String namaTim, int nomorPemain){
    	int i = getIndexTeam(namaTim);
		tim[i].setJumlahGol(tim[i].getJumlahGol() + 1);
		tim[i].updateGol(nomorPemain);
		
		int x = getTeamNum(namaTim);
		golTim[x]++;
		
		pairMatchNow = prosesNextGame();
		if (pairMatchNow.getKey().equals(namaTim)){
			updateKebobolan(pairMatchNow.getValue());
		}
		else{
			updateKebobolan(pairMatchNow.getKey());
		}
	}

	public void updateKebobolan(String namaTim){
		int i = getIndexTeam(namaTim);
		tim[i].setJumlahKebobolan(tim[i].getJumlahKebobolan() + 1);
	}

    public void updateKartuKuning(String namaTim, int nomorPemain){
    	int i = getIndexTeam(namaTim);
    	tim[i].updateKartuKuning(nomorPemain);
    	int x = getTeamNum(namaTim);
    	kkTim[x]++;
    	pelanggaranTim[x]++;
	}

    public void updateKartuMerah(String namaTim, int nomorPemain){
    	int i = getIndexTeam(namaTim);
    	tim[i].updateKartuMerah(nomorPemain);
		int x = getTeamNum(namaTim);
		kmTim[x]++;
		pelanggaranTim[x]++;
	}

	public void updatePelanggaran(String namaTim, int nomorPemain){
		int i = getIndexTeam(namaTim);
    	tim[i].updatePelanggaran(nomorPemain);
    	int x = getTeamNum(namaTim);
		pelanggaranTim[x]++;
	}

	public void updatePoin(int i, int j, int x, int y){
		if (golTim[x] > golTim[y]){
			tim[i].setJumlahPoin(tim[i].getJumlahPoin() + 3);
			tim[i].setJumlahMenang(tim[i].getJumlahMenang() + 1);
			tim[j].setJumlahKalah(tim[i].getJumlahKalah() + 1);
		}
		else if(golTim[x] < golTim[y]){
			tim[j].setJumlahPoin(tim[i].getJumlahPoin() + 3);
			tim[j].setJumlahMenang(tim[i].getJumlahMenang() + 1);
			tim[i].setJumlahKalah(tim[i].getJumlahKalah() + 1);}
		else{
			tim[i].setJumlahSeri(tim[i].getJumlahSeri() + 1);
			tim[j].setJumlahSeri(tim[j].getJumlahSeri() + 1);
			tim[i].setJumlahPoin(tim[i].getJumlahPoin() + 1);
			tim[j].setJumlahPoin(tim[j].getJumlahPoin() + 1);
		}
	}

	public void updateKlasemen() {
		
		//DESCENDING BUBBLE SORT
		for (int i = tim.length-1; i>=0; i--) {
			boolean swapped = false;

			for (int j = 0; j < i; j++) {
				if (tim[j].getJumlahPoin() <= tim[j + 1].getJumlahPoin()) {
					//PENGECEKAN APAKAH JUMLAH POIN SAMA
					//JIKA IYA MAKA BANDINGKAN SELISIH GOL
					if(tim[j].getJumlahPoin() == tim[j + 1].getJumlahPoin()){
						int x = tim[j].getJumlahGol() - tim[j].getJumlahKebobolan();
						int y = tim[j + 1].getJumlahGol() - tim[j + 1].getJumlahKebobolan();
						if (x < y){
							Tim T = tim[j];
							tim[j] = tim[j + 1];
							tim[j + 1] = T;
							swapped = true;
						}
					}
					else{
						Tim T = tim[j];
						tim[j] = tim[j + 1];
						tim[j + 1] = T;
						swapped = true;
					}
				}
			}

			if (!swapped) {break;}
		}

		//SET PERINGKAT TIM
		for (int i = 0; i < tim.length; i++){
			tim[i].setPeringkat(i + 1);
		}
	}

	public void updatePencetakGol(){

		//REMOVE ALL ELEMENT IN arrPencetakGol
		arrPencetakGol.clear();
		
		//REFILL arrPencetakGol
		for (int i = 0; i < jumlahTim; i++){
			for (int j = 0; j < 5; j++){
				//getAllPemain return Pair<Integer, Pair<String, String>>
				arrPencetakGol.add(tim[i].getAllPemain(j)); 
			}
		}
		
		//REARRANGE(|"SORTING") ARRAYLIST OF PAIR, DESCENDING INSERTION SORT
		for (int ii = 1; ii < arrPencetakGol.size(); ii++) {
			Pair<Integer, Pair<String, String>> tmp = arrPencetakGol.get(ii);
			int jj = ii;
			while (( jj > 0) && (tmp.getKey() >= arrPencetakGol.get(jj - 1).getKey())) {
				arrPencetakGol.set(jj, arrPencetakGol.get(jj - 1));
				jj--;
			}
			arrPencetakGol.set(jj, tmp);
		}

	}

	//METHOD SHOW KLASEMEN, TOP SCORER, TIM & PEMAIN
	public void showKlasemen(){
		updateKlasemen();
		System.out.print("Peringkat | Nama Tim | Jumlah Gol | Jumlah Kebobolan | Menang | Kalah | Seri | Poin\n");
		System.out.print("-----------------------------------------------------------------------------------\n");
		
		for (int i = 0; i < jumlahTim; i++){
			String peringkat = tim[i].getPeringkat() + "";
			String gol = tim[i].getJumlahGol() + "";
			String kebobolan = tim[i].getJumlahKebobolan() + "";
			String menang = tim[i].getJumlahMenang() + "";
			String kalah = tim[i].getJumlahKalah() + "";
			String seri = tim[i].getJumlahSeri() + "";
			String poin = tim[i].getJumlahPoin() + "";
			System.out.printf("%s| %s|%s|%s|%s|%s|%s|%s\n", Text.center(peringkat, 10), Text.leftPad(tim[i].getNamaTim(), 9),
								Text.center(gol, 12), Text.center(kebobolan, 18), Text.center(menang, 8),
								Text.center(kalah, 7), Text.center(seri, 6), Text.center(poin, 6));
		}

	}

	public void showPencetakGol(){
		updatePencetakGol();
		//PRINT PENCETAK GOL
		System.out.printf("Peringkat | Nama Pemain | Nama Tim | Jumlah Gol\n"); 
		System.out.printf("-----------------------------------------------\n");
		
		for(int i = 0; i < 10; i++){
			int g = arrPencetakGol.get(i).getKey();
			String gol = g + "";
			String namaPemain = arrPencetakGol.get(i).getValue().getKey();
			String namaTim = arrPencetakGol.get(i).getValue().getValue();
			int j = i + 1;
			String no = j + "";
			System.out.printf("%s| %s| %s|%s\n", Text.center(no, 10), Text.leftPad(namaPemain, 12), Text.leftPad(namaTim, 9), Text.center(gol, 11));
		}
	
	}

	public void showTim(String namaTim) {
	
		if (arrNamaTim.contains(namaTim)){
			
			for (int i = 0; i < jumlahTim; i++){
				if (tim[i].getNamaTim().equals(namaTim)){
					tim[i].showDaftarPemain();
				}
			}

		}
	
		else{
			System.out.printf("Tidak ada tim dengan nama %s.\n", namaTim);
		}
	
	}

	public void showPemain(String namaTim, Pair<Integer, String> pairPemain){
		int i = getIndexTeam(namaTim);
		tim[i].showPemain(pairPemain);
	}

	//GET RANDOM NUMBER OF PLAYER
	public int getRandomPlayerNum(String teamName){
		int i = getIndexTeam(teamName);
		int x = random.nextInt(5);
		return tim[i].getArrNomorPemain().get(x);
	}

	//GET INDEX TEAM FROM tim[] array;
	public int getIndexTeam(String namaTim){
		int ret = 0;
		for (int i = 0; i < jumlahTim; i++){
			if (tim[i].getNamaTim().equals(namaTim)){
				ret = i;
				break;
			}
		}
		return ret;
	}
	
	//RETURN NEXT/CURRENT MATCH
	public Pair<String, String> prosesNextGame(){
		String team1 = arrNextGame.get(idxMatch).getKey();
		String team2 = arrNextGame.get(idxMatch).getValue();
		Pair<String, String> pairMatch = new Pair<String, String>(team1, team2);
		return pairMatch;
	}

	public void endOfMatch(){
		Pair<String, String> pairMatch = prosesNextGame();
		System.out.printf("Statistika Pertandingan Tim %s VS Tim %s\n\n", pairMatch.getKey(), pairMatch.getValue());
		int i = getIndexTeam(pairMatch.getKey());
		int j = getIndexTeam(pairMatch.getValue());
		int x = getTeamNum(pairMatch.getKey());
		int y = getTeamNum(pairMatch.getValue());
		updatePoin(i, j, x, y); //UPDATE POIN
		printStatistic(i, pairMatch.getKey());
		printStatistic(j, pairMatch.getValue());
		idxMatch++;
		//RESET MATCH STATISTIC PARAMETER
		Arrays.fill(golTim, 0);
	    Arrays.fill(pelanggaranTim, 0);
	    Arrays.fill(kkTim, 0);
	    Arrays.fill(kmTim, 0);
	}

	public void printStatistic(int i, String namaTim){
		int x = getTeamNum(namaTim);
		System.out.printf("Tim : %s\n", tim[i].getNamaTim());
		System.out.printf("Gol : %d\n", golTim[x]);
		System.out.printf("Pelanggaran : %d\n", pelanggaranTim[x]);
		System.out.printf("Kartu Kuning : %d\n", kkTim[x]);
		System.out.printf("Kartu Merah : %d\n\n", kmTim[x]);
	}

	//METHOD UNTUK ERROR HANDLING RANDOM MELEBIHI CONSTRAIN SOAL
	public boolean isOverflow(String key, String teamName){
		boolean ret = false;
		int x = getTeamNum(teamName);
		if (key.equals("-g")){
			if (golTim[x] >= 5){ret = true;}
		}
		else if (key.equals("-kk")){
			if (kkTim[x] >= 3){ret = true;}
		}
		else if (key.equals("-km")){
			if(kmTim[x] >= 2){ret = true;}
		}
		else if (key.equals("-p")){
			if(pelanggaranTim[x] >= 5){ret = true;}
		}
		return ret;
	}	

	//GET INDEX TEAM yang sedang bertanding
	public int getTeamNum(String teamName){
		pairMatchNow = prosesNextGame();
		int ret = (teamName.equals(pairMatchNow.getKey()))? 0 : 1;
		return ret;
	}

    public String getChampion(){
    	updateKlasemen();
    	return tim[0].getNamaTim();
    }

	public int getIdxMatch() { return idxMatch; }

	public ArrayList<Pair<String, String>> getArrNextGame() { return arrNextGame; }

	public ArrayList<String> getArrNamaTim() { return arrNamaTim; }

}

