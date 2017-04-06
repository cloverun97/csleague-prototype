import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main{
	private static Game game;

	public static void main(String[] args) {
		
		//INSTANSIASI GAME
		game = new Game();
        Scanner scan = new Scanner(System.in);
        boolean belumInit = true;

        System.out.print("WELCOME\n");
        while (!game.isFinished()) {

			System.out.print("[LIGAF12]=> ");
			//SCAN PER BARIS
            String strLine = scan.nextLine();
            //SCANNER KATA DALAM BARIS 
            Scanner sc2 = new Scanner(strLine);

        	if(belumInit) {
				String command = sc2.next();
    			while (sc2.hasNext()){
					if (command.equals("init")){
						String jumlahTim = sc2.next();
						//ERROR HANDLING JIKA X BUKAN INTEGER
						try {
							int x = Integer.parseInt(jumlahTim);
							if (x < 4 || x > 10) {System.out.println("ERROR: "+
								"Jumlah tim minimal 4 tim dan maksimal 10 tim!"); }
							else {
								game = new Game(x);
								System.out.println("Init berhasil!");
								belumInit = false;
							}
						} catch (NumberFormatException nfe) {
							System.out.println("ERROR: Jumlah tim harus Integer.");
						}
					}
					//ERROR HANDLING JIKA PERINTAH BUKAN "init"
					else { 
						System.out.println("ERROR: Game belum di-init, "+
						"silakan init terlebih dahulu dengan perintah: init [jumlahTim]"); break;}
				}
        	}    	

        	//SUDAH DI-INIT
    		else { 
    			/*
					boolean isNextGameManual adalah untuk penanda apakah
					perintahnya "nextgame [command] [nama tim] [nomor pemain]"  
    			*/
    			boolean isNextGameManual = false;
    			if (strLine.equals("nextgame")) {game.prosesNextGameRandom();}
	    		
	    		else{
	            	String command = sc2.next();
	        		//WHILE KATA DALAM BARIS
		            while(sc2.hasNext()) {
		            	if (command.equals("init")) {
		            		System.out.println("ERROR: Game sudah di-init, init gagal!");
		            		break;
		            	}
		            	else if (command.equals("nextgame")){
		            		String key = sc2.next();
		            		if (key.equals("-all")){
		            			game.prosesNextGameAll();
		            		}
		            		else {
		            			//MANUAL INPUT HANDLING
								String namaTim = sc2.next();
								String noPemain = sc2.next();
								int nomorPemain = Integer.parseInt(noPemain);
								//ERROR HANDLING: NAMA TIM TIDAK ADA PADA GAME
								try{
									game.prosesNextGameManual(key, namaTim, nomorPemain);
			            			isNextGameManual = true;
								} catch(ArrayIndexOutOfBoundsException aioobe){
									System.out.printf("ERROR: Tim %s tidak sedang bertanding\n", namaTim);
									break;
								}
	            			}
	            		}

	            		//HANDLING PERINTAH SHOW
	            		else if (command.equals("show")){
	            			String str = sc2.next();
	            			if (str.equals("klasemen")) { game.prosesShowKlasemen(); }
	            			else if (str.equals("pencetakGol")) { game.prosesShowCetakGol(); }
	            			else if (str.equals("nextgame")) { game.prosesShowNextGame(); }
	            			else if (str.equals("tim")) { String namaTim = sc2.next(); game.prosesShowTim(namaTim); }
	            			else if (str.equals("pemain")) {
	            				//ERROR HANDLING PERINTAH SHOW TIDAK LENGKAP
	            				try{
		            				String namaTim = sc2.next();
		            				String param = sc2.next();
		            				if (game.getLiga().getArrNamaTim().contains(namaTim)){
		            					//ERROR HANDLING MENCARI PEMAIN DENGAN NAMA/NOMOR
			            				 try {
		     								int x = Integer.parseInt(param);
									        game.prosesShowPemain(namaTim, x, "");
									    } catch (NumberFormatException nfe) {
									        game.prosesShowPemain(namaTim, 0, param);
									    }
									}
									else {System.out.printf("ERROR: Tidak ada tim dengan nama %s\n", namaTim);}
								} catch (NoSuchElementException nse){
	            					System.out.print("ERROR: Perintah tidak lengkap, format perintah: show pemain [nama tim] [nomor/nama pemain]\n");
								}
	            			}
	            			//ERROR HANDLING PERINTAH SHOW TIDAK SESUAI FORMAT
	            			else {System.out.printf("ERROR: Tidak ada perintah Show %s!\n", str);}
	            		}
	            		//ERROR HANDLING PERINTAH TIDAK SESUAI FORMAT
	            		else{System.out.printf("ERROR: Tidak ada perintah %s\n", command); break;}
	            	}
	            	//PRINT STATISTIC PERTANDINGAN MANUAL
	            	if (isNextGameManual){game.getLiga().endOfMatch();}
    			}
        	}
		}
	}
}
