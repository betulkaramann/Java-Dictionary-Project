//Owner Betul Karaman

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static HashMap<String, String> turkishToEnglish = new HashMap<>();
    public static HashMap<String, String> englishToTurkish = new HashMap<>();
    static String[] mainMenu = {"Kullanmak istediğiniz sözlük için aşağıdaki seçeneklerden devam ediniz:", "1- Türkçe - İngilizce", "2- İngilizce - Türkçe", "3- Yeni kelime ekle", "0- Çıkış", "Seçiminiz: "};

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
                System.out.println(language + " Karşılığı: " + key);
            } else {
                System.out.println("Bu kelimenin " + language + " karşılığı bulunamadı.");
            }
        }
    }

    public static void addString(Scanner scanner) {
        System.out.println("#### Yeni Kelime Ekle ####");
        System.out.println("Eklemek istediğiniz kelime türünü seçin:");
        System.out.println("1- Türkçe");
        System.out.println("2- İngilizce");
        int secim = scanner.nextInt();
        scanner.nextLine();
        if (secim == 1) {
            System.out.print("Eklemek istediğiniz Türkçe kelime: ");
            String turkish = scanner.nextLine();
            System.out.print("Türkçe kelimenin İngilizce karşılığı: ");
            String english = scanner.nextLine();
            turkishToEnglish.put(turkish, english);
            englishToTurkish.put(english, turkish);
            updateDictionary("tureng.txt", turkishToEnglish);
            System.out.println(turkish + " Sözlüğe Eklendi!");
        } else if (secim == 2) {
            System.out.print("Eklemek istediğiniz İngilizce kelime: ");
            String english = scanner.nextLine();
            System.out.print("İngilizce kelimenin Türkçe karşılığı: ");
            String turkish = scanner.nextLine();
            turkishToEnglish.put(turkish, english);
            englishToTurkish.put(english, turkish);
            updateDictionary("engtur.txt", englishToTurkish);
            System.out.println(english + " Sözlüğe Eklendi!");

        } else {
            System.out.println("Geçersiz seçim yaptınız. Herhangi bir ekleme işlemi yapılmadı.");
        }
    }
    public static void updateDictionary(String fileName, HashMap<String, String> dictionary) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            for (String key : dictionary.keySet()) {
                String value = dictionary.get(key);
                fileWriter.write(key + "/" + value + "\n");
            }
            System.out.println("Sözlük dosyası güncellendi.");
        } catch (IOException e) {
            System.err.println("Dosya yazma hatası: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        readData("tureng.txt", turkishToEnglish);
        readData("engtur.txt", englishToTurkish);

        boolean flag = false;
        System.out.println("Merhaba, sözlük uygulamasına hoşgeldiniz...");
        for (String menu : mainMenu) {
            System.out.println(menu);
        }
        Scanner scanner = new Scanner(System.in);

        while (!flag) {
            int choose = scanner.nextInt();
            if (choose >= 1 && choose <= 3) {
                switch (choose) {
                    case 1:
                        Dictionary(turkishToEnglish, scanner, "#### Türkçe-İngilizce Sözlük ####", "İngilizce");
                        break;
                    case 2:
                        Dictionary(englishToTurkish, scanner, "#### İngilizce-Türkçe Sözlük ####", "Türkçe");
                        break;
                    case 3:
                        addString(scanner);
                        break;
                }
            } else if (choose == 0) {
                for (String menu : mainMenu) {
                    System.out.println(menu);
                }
            } else {
                System.out.println("Geçersiz seçim yaptınız...Tekrar deneyiniz\n");
            }
        }
    }
}