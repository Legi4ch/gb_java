package HomeWork3;

import java.util.Random;
import java.util.Scanner;

public class FruitsGame {
    public static final String [] WORDS =  {"apple", "orange", "lemon",
            "banana", "apricot", "avocado",
            "broccoli", "carrot", "cherry",
            "garlic", "grape", "melon",
            "leak", "kiwi", "mango",
            "mushroom", "nut", "olive",
            "pea", "peanut", "pear",
            "pepper", "pineapple", "pumpkin", "potato"};

    public static void main(String[] args) {
        System.out.println("Компьютер загадал слово. Попробуйте отгадать.");
        String sourceWord = randomWord(); //загадываем слово
        String userWord = new String(); //тут храним ввод юзера
        //System.out.println(sourceWord);
        do {
            userWord = userInput();
            checkWord(sourceWord,userWord);
        }
        while (!userWord.equals(sourceWord));

    }

    //случайное слово
    public static String randomWord() {
        Random rand = new Random();
        int index = rand.nextInt(WORDS.length);
        return WORDS[index];
    }

    //запрос ввода слова с клавиатуры
    public static String userInput() {
        System.out.println("Введите слово:");
        Scanner scanner = new Scanner(System.in);
        String ui = scanner.nextLine();
        return ui;
    }

    //сравнение по буквам и вывод
    public static void checkWord(String sourceWord,String userWord) {
        if (!sourceWord.equals(userWord)) {
            String result = new String();
            int max = (sourceWord.length() >= userWord.length()) ? userWord.length() : sourceWord.length();
            for (int i = 0; i < max; i++) {
                if (i < userWord.length()) {
                    if (sourceWord.charAt(i) == userWord.charAt(i)) {
                        result += sourceWord.charAt(i);
                    } else {
                        result += "#";
                    }
                } else { //если введенное слово длиннее исходника, завершаем перебор
                    break;
                }
            }
            System.out.println(rPad(result,15));
        } else {
            System.out.println("Вы угадали слово: " + userWord);
        }
    }

    //добивка строки до 15 символов
    public static String rPad(String str, int pad) {
        if (str.length() < 15) {
            for (int i = str.length(); i <= pad ; i++) {
                str += "#";
            }
        }
        return str;
    }
}
