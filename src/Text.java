/*
    KELAS INI ADALAH CLASS UTILITY UNTUK ALIGN DARI TEXT
    TERDAPAT METHOD leftPad(RATA KIRI) & center(TENGAH) 
*/
public class Text{

    //VARIABEL iMinLength ADALALAH PANJANG MAKSIMAL DARI BARIS PADA KOLOM    
    public static String center(String str, int iMinLength) {

        if (str.length() < iMinLength) {
            int length = str.length();
            int left = (iMinLength - str.length()) / 2;
            int right = iMinLength - str.length() - left;
            String tmp = str;
    
            for (int i = 0; i < left; i++) {
                tmp = " " + tmp;
            }
    
            for (int i = 0; i < right; i++) {
                tmp = tmp + " ";
            }
    
            str = tmp;
        }
    
        return str;
    }

    public static String leftPad(String sValue, int iMinLength) {
        String tmp = "" + sValue;
    
        while (tmp.length() < iMinLength) {
            tmp = tmp + " ";
        }
    
        return tmp;
    }

}
