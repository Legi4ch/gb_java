package Leetcode;

public class Main {
    public static void main(String[] args) throws MatrixInitErrorException {

        try {
            Reverse matrix = new Reverse(9,9);
            //System.out.println(matrix.toString());
            matrix.fillCCW();
            System.out.println(matrix.toString());


        }
        catch (MatrixInitErrorException e) {
            System.out.println(e.getMessage());
        }

    }
}
