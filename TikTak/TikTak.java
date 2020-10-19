package TikTak;
/**
 * Надо изменить подсчет по колонкам/ктрокам и диагоналям
 * сейчас там считается просто кол-во символов противника, а нодо учеть что они идут подряд
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class TikTak {
    //game setting
    static char[][] gameMatrix;
    static String [] matrixDiags;
    static final int SIZE = 5;
    static final char DOT_EMPTY = '•';
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final int WIN_DOTS_COUNT = 3;
    static final int MAX_DIAGS_COUNT = 10; //кол-во диагоналей, которые будем обсчитывать


    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();




    public static void main (String[] args){
        playGame();
   }

    static void playGame() {
        initGameMatrix();
        printGameMatrix();

        while (true) {
            playerMove();
            if (isGameOver(DOT_X)) {
                System.out.println("Игра завершена!");
                break;
            }

            pcMove();
            if (isGameOver(DOT_O)) {
                System.out.println("Игра завершена!");
                break;
            }
        }
    }

    //создаем и заполняем матрицу
    static void initGameMatrix() {
        gameMatrix = new char[SIZE][SIZE];
        matrixDiags = new String [MAX_DIAGS_COUNT];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++) {
                gameMatrix[i][j] = DOT_EMPTY;
            }
            }
        }

    //печать игрового поля
    static void printGameMatrix() {

        printMatrinxHeader();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i+1) + "  ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(gameMatrix[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //печать заголовков игрового поля
    private static void printMatrinxHeader() {
        System.out.print(DOT_EMPTY + "  ");
        for (int i = 1; i <= SIZE; i++) {
            System.out.print(i + "  ");
        }
        System.out.println();
    }

    //ход игрока
    static void playerMove(){
        int x,y;
        do {
            System.out.println("Ходите. Пробел разделяет координаты");
            y = scanner.nextInt() - 1;
            x = scanner.nextInt() - 1;
        } while(!isPointValid(x,y));
        gameMatrix[y][x] = DOT_X;
    }

    //ход компа

    /**
     * В принципе решение рабочее. Для сильного сопротивления человеку, нужно доработать функции ходов
     * на сетке 3*3 играет хорошо, на сетках больше сказывается слабый Функционал этих методов
     * Но доработать не сложно в принципе
     */

    static void pcMove() {
        int x = -1;
        int y = -1;
        boolean isSolution = false;  //флаг найденного решения, если его нет, случайный выбор


        int[] left = checkDiag('L');
        int[] right = checkDiag('R');

        //проверяем строки и столбцы на игровом поле
        for (int i = 0; i < SIZE; i++) {
            //строки
            int[] line = checkLine(i);
            int[] col = checkCol(i);
            if (line[0] == 1) {
                y = i;
                x = line[1];
                isSolution = true;
                break;
            }
            //столбцы
            else if (col[0] == 1) {
                x = i;
                y = col[1];
                isSolution = true;
                break;
            }
        }
        //правая диагональ
        if (right[0] == 1) {
            x = right[2];
            y = right[1];
            isSolution = true;
        }
        //правая диагональ
        else if (left[0] == 1) {
            x = left[2];
            y = left[1];
            isSolution = true;
         }

        if (!isSolution) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isPointValid(x,y));
        }
         gameMatrix[y][x] = DOT_O;
        System.out.println("Ход компьютера :" + (y + 1) + " " + (x + 1) + " Ждем вас..");

    }

    /**
     * результатом метода является массив из трех чисел
     * нулевое число это буелевый ответ да/нет
     * а 1 и 2 координаты в матрице для хода
     */
    static int[] checkDiag(char d) {
        int[] result = {0,0,0}; // да/нет, i,j для DOT_O
        int human_symbols = 0; //счетчик ходов человека
        int x = -1, y = -1; //координаты за пределами поля

        if (d == 'L') {
            int i = 0;
            while (i < SIZE) {
                if (gameMatrix[i][i] == DOT_X) {
                    human_symbols++;
                }
                else if (gameMatrix[i][i] == DOT_O) {
                    human_symbols--;
                }
                else if (gameMatrix[i][i] == DOT_EMPTY) {
                    x = i;
                    y = i;
                }
                ++i;
            }
        } else {
            int i = 0;
            while (i < SIZE) {
                if (gameMatrix[i][SIZE - i - 1] == DOT_X) {
                    human_symbols++;
                } else if (gameMatrix[i][SIZE - i - 1] == DOT_O) {
                    human_symbols--;
                } else if (gameMatrix[i][SIZE - i - 1] == DOT_EMPTY) {
                    x = i;
                    y = SIZE - i - 1;
                }
                ++i;
            }
        }
        if (human_symbols == WIN_DOTS_COUNT - 1 && x >= 0 && y >= 0 && isPointValid(x,y)) {
            result[0] = 1;
            result[1] = x;
            result[2] = y;
        }
        return result;
    }

    /**
     * результатом метода является массив из двух чисел
     * нулевое число это буелевый ответ да/нет
     * первое координата в строке для хода
     */
    static int[] checkLine(int i) {
        int[] result = {0,0}; // да/нет, координата для DOT_O
        int point = -1; //координата по умолчанию
        int human_symbol = 0; //счетчик ходов человека
        int pc_symbol = 0; //счетчик ходов компа
        for (int j = 0; j < SIZE; j++) {
            if (gameMatrix[i][j] == DOT_X) {
                human_symbol++;
                pc_symbol = 0;
            }
            else if (gameMatrix[i][j] == DOT_O) {
                pc_symbol++;
                human_symbol = 0;
            }
            else {
                point = j;
                break;
            }
        }
/*        if (pc_symbol == WIN_DOTS_COUNT-1 && point >= 0 && isPointValid(i,point)) {
            result[0] = 1;
            result[1] = point;
            return result;
        }
        else */if (human_symbol == WIN_DOTS_COUNT -1 && point >= 0 && isPointValid(i,point)) {
            result[0] = 1;
            result[1] = point;
        }
        return result;
    }

    /**
     * результатом метода является массив из двух чисел
     * нулевое число это буелевый ответ да/нет
     * первое координата в столбце для хода
     */
    static int[] checkCol(int i) {
        int[] result = {0,0}; // да/нет, координата для DOT_O
        int point = -1; //координата по умолчанию
        int human_symbol = 0; //счетчик ходов человека
        int pc_symbol = 0; //счетчик ходов компа
        for (int j = 0; j < SIZE; j++) {
            if (gameMatrix[j][i] == DOT_X) {
                human_symbol++;
                pc_symbol = 0;
            }
            else if (gameMatrix[j][i] == DOT_O) {
                pc_symbol++;
                human_symbol = 0;
            }
            else {
                point = j;
                break;
            }
        }
        if (pc_symbol == WIN_DOTS_COUNT -1 && point >= 0  && isPointValid(i,point)) {
            result[0] = 1;
            result[1] = point;
            return result;
        }
        else if (human_symbol == WIN_DOTS_COUNT -1 && point >= 0  && isPointValid(i,point)) {
            result[0] = 1;
            result[1] = point;
        }
        return result;
    }


    //проверка валидности координат для хода
    static boolean isPointValid(int x,int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            //System.out.printf("Ячейка с координатами [%d][%d] не существует на поле\n",x + 1,y + 1);
            return false;
        }
        if (gameMatrix[y][x] != DOT_EMPTY) {
            //System.out.printf("Ячейка с координатами [%d][%d] уже занята\n",x + 1,y + 1);
            return false;
        }
        return true;
    }

    //проверка окончания игры
    static boolean isGameOver (char playerSymbol) {
        boolean result = false;
        printGameMatrix();
        if (checkWin(playerSymbol)) {
            if (playerSymbol == DOT_X) {
                System.out.println("Вы выиграли!!!");
            }
            else if (playerSymbol == DOT_O) {
                System.out.println("Комьютер выиграл!!!");
            }
            else {
                System.out.printf("Ничья!!!");
            }
            result = true;
        }
        else if (isMatrixDone()) {
            System.out.println("Ничья");
            result = true;
        }
        if (result) {
            repeatGame();
        }
        return result;
    }

    //запрос на повтор игры
    public static int repeatGame() {
        System.out.println("Играем еще? 1 - Да / 0 - Нет");
        switch (scanner.nextInt()) {
            case (0):
                System.out.println("Бывай!");
                System.exit(1);
                break;
            case (1):
                playGame();
                break;
        }
        return scanner.nextInt();
    }

    //проверка заполнения матрицы игры
    static boolean isMatrixDone() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (gameMatrix[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    //проверка победы
    static boolean checkWin(char playerSymbol) {
        boolean result = false;
        if (winOnDiagNew(playerSymbol) || winOnLines(playerSymbol) || winOnCols(playerSymbol)) {
            result = true;
        }
        return result;
  }

    //проверка победы на диагоналях 3*3
    static boolean winOnDiag(char playerSymbol) {
        fillDiags();
        System.out.println(Arrays.toString(matrixDiags));
        boolean result = false;
        int left = 0;
        int right = 0;

        //проверяем диагонали и суммируем каждую
        for (int i = 0; i < SIZE; i++) {
            if (gameMatrix[i][i] == playerSymbol) {
                left++;
            }

            if (gameMatrix[i][SIZE -i - 1] == playerSymbol) {
                right++;
            }
        }
        //на какой-то диагонали все заполнено
        if (right == WIN_DOTS_COUNT || left == WIN_DOTS_COUNT) {
            result = true;
        }
        return result;
    }

    //проверка победы на строках любой длины
    static boolean winOnLines(char playerSymbol) {
        for (int i = 0; i < SIZE; i++) {
            int counter = 0;
            for (int j = 0; j < SIZE; j++) {
                if (gameMatrix[i][j] == playerSymbol) {
                    counter++;
                    if (counter == WIN_DOTS_COUNT) {
                        return true; //собралась последовательность
                    }
                }
                else  {
                    counter = 0; //обнуляем последовательность, если встретился другой символ
                }
            }
        }
        return false;
    }

    //проверка победы на столбцах любой длины
    static boolean winOnCols(char playerSymbol) {
        for (int i = 0; i < SIZE; i++) {
            int counter = 0;
            for (int j = 0; j < SIZE; j++) {
                if (gameMatrix[j][i] == playerSymbol) {
                    counter++;
                    if (counter == WIN_DOTS_COUNT) {
                        return true; //собралась последовательность
                    }
                }
                else  {
                    counter = 0; //обнуляем последовательность, если встретился другой символ
                }
            }
        }
        return false;
    }

    //проверка победы на диагоналях
    static boolean winOnDiagNew(char playerSymbol) {
        fillDiags();
        for (int i = 0; i < MAX_DIAGS_COUNT; i++) {
            //только диагонали где может поместиться нужное кол-во выигрышных символов
            if (matrixDiags[i].length() >= WIN_DOTS_COUNT) {
                int counter = 0;
                for (int j = 0; j < matrixDiags[i].length(); j++) {
                    if (matrixDiags[i].charAt(j) == playerSymbol) {
                        counter++;
                        if (counter == WIN_DOTS_COUNT) {
                            return true;
                        }
                    }
                    else {
                        counter = 0;
                    }
                }
            }
        }
    return false;
    }

    //очистка массива диагоналей перед каждой проверкой
    public static void clearDiags(){
        for (int i = 0; i < matrixDiags.length; i++) {
            matrixDiags[i] = "";
        }
    }

    //заполнение массива диагоналей
    //знаю, что хардкод и работает только до 5*5, но не хватило времени додуматься до решения лучше
    public static void fillDiags() {
        clearDiags();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                //левые диагонали
                if (i==j) {
                    matrixDiags[0] +=  gameMatrix[i][j];
                }
                if (i==j+1) {
                    matrixDiags[1] += gameMatrix[i][j];
                }
                if (i==j+2) {
                    matrixDiags[2] +=  gameMatrix[i][j];
                }
                if (i == j-1) {
                    matrixDiags[3] +=  gameMatrix[i][j];
                }
                if (i == j-2) {
                    matrixDiags[4] +=  gameMatrix[i][j];
                }

                //правые дмагонали
                if (i == SIZE - j -1){
                    matrixDiags[5] +=  gameMatrix[i][j];
                }
                if (i == SIZE - j -2){
                    matrixDiags[6] +=  gameMatrix[i][j];
                }
                if (i == SIZE - j -3){
                    matrixDiags[7] +=  gameMatrix[i][j];
                }
                if (SIZE - i == j ){
                    matrixDiags[8] +=  gameMatrix[i][j];
                }
                if (SIZE - i == j - 1 ){
                    matrixDiags[9] +=  gameMatrix[i][j];
                }
            }
        }
    }
}


