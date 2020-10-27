package Leetcode;

public class Main {

    public static void main(String[] args) throws MatrixInitErrorException {
        long time = System.nanoTime();
        try {
            Reverse matrix = new Reverse(10,10);
            System.out.println(matrix.toString());
            matrix.fillCCW();
            System.out.println(matrix.toString());
        } catch (MatrixInitErrorException e) {
            System.out.println(e.getMessage());
        }
        time = System.nanoTime() - time;
        System.out.printf("Elapsed %,9.3f ms\n", time/1_000_000.0); 
    }



}
