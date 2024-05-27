package Task2;

import Task2.Fox.FoxCalculator;
import Task2.FoxForkJoin.FoxForkJoinCalculator;
import Task2.Helper.MatrixEntity;
import Task2.Helper.RandomMatrixGenerator;

public class Main {
    public static void main(String[] args) {
        RandomMatrixGenerator randomMatrixGenerator = new RandomMatrixGenerator();

        int SIZE = 2048;
        MatrixEntity matrixEntity = new MatrixEntity(new int[][]{
                {1, 5},
                {2, 3},
                {1, 7}
        });

        MatrixEntity matrixEntity2 = new MatrixEntity(new int[][]{
                {1, 2, 3, 7},
                {5, 2, 8, 1}
        });

        MatrixEntity matrixEntity3 = new MatrixEntity(
                randomMatrixGenerator
                        .generateRandomMatrix(SIZE, SIZE)
                        .getMatrix());

        MatrixEntity matrixEntity4 = new MatrixEntity(
                randomMatrixGenerator
                        .generateRandomMatrix(SIZE, SIZE)
                        .getMatrix());

//        System.out.println("Matrix 3:");
//        matrixEntity3.print2D();
//        System.out.println("####");
//
//        System.out.println("Matrix 4:");
//        matrixEntity4.print2D();
//        System.out.println("####");

        FoxCalculator foxCalculator = new FoxCalculator(matrixEntity3, matrixEntity4, 4);
        System.out.println("fox standard result:");
        var timeStart = System.currentTimeMillis();
        MatrixEntity res3 = new MatrixEntity(foxCalculator.multiplyMatrix().getMatrix());
        var timeEnd = System.currentTimeMillis();
//        res3.print2D();

        System.out.println("Time:  " + (timeEnd - timeStart));


        FoxForkJoinCalculator foxForkJoinCalculator = new FoxForkJoinCalculator(matrixEntity3, matrixEntity4);
        System.out.println("fox fork join result:");
        timeStart = System.currentTimeMillis();
        MatrixEntity res4 = new MatrixEntity(foxForkJoinCalculator.multiplyMatrix(4).getMatrix());
        timeEnd = System.currentTimeMillis();
//                res4.print2D();
        System.out.println("Time:  " + (timeEnd - timeStart));

    }
}
