
import java.util.Random;
import java.util.Scanner;

public class HomeWorkLessonFour {
    public static final int LINE_LIMIT = 15;
    //переменные
    public static int SIZE = 5;
    public static int DOTS_TO_WIN = 4;


    // константы
    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    //создание игрового поля
    public static char[][] map;
    public static Scanner scanner = new Scanner(System.in);
    public static Random random = new Random();

    //игровой цикл
    public static void main(String[] args) {
        //инициализация игрового поля
        initMap();
        //вывод пустого поля
        printMap();
        //ход игрока
        while (true) {
            humanTurn();

            //вывод игрового поля
            printMap();

            //проверка победы
            if (checkWin(DOT_X )) {
                System.out.println("Человек победил!!!");
                break;
            }
            // проверка полностью заполненного поля
            if (isMapFull()) {
                System.out.println("Ничья!!!");
                break;
            }
            //ход ии
            aiTurn();

            //вывод игрового поля
            printMap();
            //проверка победителя
             if (checkWin( DOT_O)) {
                 System.out.println("ИИ победил!!!");
                 break;
             }
            //проверка заполенности карты
            if (isMapFull()) {
                System.out.println("Ничья!!!");
                break;
            }
        }
            //игра завершена
            System.out.println("Игра завершена!!!");
        scanner.close();
    }

    private static boolean isMapFull() {
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                if (map[y][x] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static boolean checkWin(char symb) {
        for (int col=0; col<SIZE-DOTS_TO_WIN+1; col++) {
            for (int row=0; row<SIZE-DOTS_TO_WIN+1; row++) {
                if (checkDiagonal(symb, col, row) || checkLanes(symb, col, row)) return true;
            }
        }
        return false;
    }

     public static boolean checkDiagonal(char symb, int offsetX, int offsetY) {
        boolean toright, toleft;
        toright = true;
        toleft = true;
        for (int i=0; i<DOTS_TO_WIN; i++) {
            toright &= (map[i+offsetX][i+offsetY] == symb);
            toleft &= (map[DOTS_TO_WIN-i-1+offsetX][i+offsetY] == symb);
        }

        if (toright || toleft) return true;

        return false;
    }
   public static boolean checkLanes(char symb, int offsetX, int offsetY) {
        boolean cols, rows;
        for (int col=offsetX; col<DOTS_TO_WIN+offsetX; col++) {
            cols = true;
            rows = true;
            for (int row=offsetY; row<DOTS_TO_WIN+offsetY; row++) {
                cols &= (map[col][row] == symb);
                rows &= (map[row][col] == symb);
            }
            if (cols || rows) return true;
        }
        return false;
    }


    public static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));

        System.out.println("AI made turn" + (x + 1) + " " + (y + 1));

        map[y][x] = DOT_O;
    }




    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате х у ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x,y ));

        map[y][x] = DOT_X;

    }


    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE) return false;
        if (y < 0 || y >= SIZE) return false;
        if (map[y][x] != DOT_EMPTY) return false;
        return true;
    }

    private static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }


    private static void printMap() {
        for (int i = 0; i <LINE_LIMIT; i++) {
            System.out.println();
        }

        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
            for (int y = 0; y < SIZE; y++) {
                System.out.print((y+1)+ " ");
                for (int x = 0; x < SIZE; x++) {
                    System.out.print(map[y][x] + " ");
                }

            System.out.println();
        }
        System.out.println();
    }

}
