package HomeWork3;

import java.util.Random;
import java.util.Scanner;

public class RandomIntGame {
    public static final int ATTEMPTS = 3; //кол-во попыток на игру
    public static final int MIN = 0; //минимум числа
    public static final int MAX = 10; //максимум числа

    public static void main(String[] args) {
        int exit = 1; //флаг выхода
        System.out.println("Компьютер загадал число от 0 до 9. Попробуйте его отгадать!");

        while (exit == 1) {
            int puzzle = rand();//загадываем число
            for (int i = 0; i < ATTEMPTS; i++) {
                //получаем ввод
                int input =  userInput();

                if (input > puzzle) {
                    System.out.println("Загаданное число меньше.");
                }
                else if (input < puzzle) {
                    System.out.println("Загаданное число больше.");
                }
                else {
                    System.out.println("Вы угадали.");
                    break;
                }

                if (i == ATTEMPTS-1) {
                    System.out.printf("Вы пытались, но не вышло. Число было %d \n", puzzle);
                    break;
                }
            }
             exit = repeatGame();
        }
   }

    //генератор числа
    public static int rand() {
        Random rand = new Random();
        int result = rand.nextInt(MAX);
        return result;
    }

    //запрос ввода числа с клавиатуры с проверкой диапазона ввода
    public static int userInput() {
        Scanner scanner = new Scanner(System.in);
        int ui;
        do {
            System.out.printf("Введите число от %d до %d\n", MIN,MAX-1);
            ui = scanner.nextInt();
        }
        while ((ui > MAX) || (ui < MIN));

        return ui;
    }

    //запрос на повтор игры
    public static int repeatGame() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Играем еще? 1 - Да / 0 - Нет");
        int ui = scanner.nextInt();
        return ui;
    }

}
