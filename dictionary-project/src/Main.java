import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
    public static HashMap<String, String> turkishToEnglish = new HashMap<>();
    public static HashMap<String, String> englishToTurkish = new HashMap<>();
    static String[] mainMenu = {"Kullanmak istediğiniz sözlük için aşağıdaki seçeneklerden devam ediniz:" ,"1- Türkçe - İngilizce", "2- İngilizce - Türkçe", "3- Yeni kelime ekle", "0- Çıkış", "Seçiminiz: "};

    public static void readData(String fileName, HashMap<String, String> dict) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("/");
                if (parts.length == 2) {
                    dict.put(parts[0], parts[1]);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Txt dosyası bulunamadı: " + e.getMessage());
        }
    }

    public static void Dictionary(HashMap<String, String> dictionary, Scanner scanner, String title, String language) {
        System.out.println(title);
        while (true) {
            System.out.print("Kelime : ");
            String value = scanner.nextLine();
            if (value.equals("0")) {
                for (String menu : mainMenu) {
                    System.out.println(menu);
                }
                break;
            }
            String key = dictionary.get(value);
            if (key != null) {
                System.out.println(language +" Karşılığı: " + key);
            } else {
                System.out.println("Bu kelimenin "+ language +" karşılığı bulunamadı.");
            }
        }

    }
    public static void main(String[] args) {
        readData("tureng.txt", turkishToEnglish);
        readData("engtur.txt", englishToTurkish);

        boolean flag = false;
        System.out.println("Merhaba, sözlük uygulamasına hoşgeldiniz...");
        for (int i = 0; i < mainMenu.length; i++) {
            System.out.println(mainMenu[i]);
        }
        Scanner scanner = new Scanner(System.in);

        while(!flag){
            int choose = scanner.nextInt();
            if(choose >=1 && choose <= 3){
                switch (choose) {
                    case 1:
                        Dictionary(turkishToEnglish, scanner,"#### Türkçe-İngilizce Sözlük ####", "İngilizce");
                        break;
                    case 2:
                        Dictionary(englishToTurkish, scanner,"#### İngilizce-Türkçe Sözlük ####","Türkçe");
                        break;
                    case 3:
                        //addString(turkishToEnglishDictionary, scanner);
                        break;
                }
            } else if (choose == 0) {
                for (int i = 0; i < mainMenu.length; i++) {
                    System.out.println(mainMenu[i]);
                }
            } else {
                System.out.println("Geçersiz seçim yaptınız...Tekrar deneyiniz\n");
            }
        }


    }
}