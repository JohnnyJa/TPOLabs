package test;

import Fox.FoxCalculator;
import Helper.MatrixEntity;
import Helper.RandomMatrixGenerator;
import Parallel.ParallelCalculator;

public class Task4Test {
    public static void main(String[] args) {
        RandomMatrixGenerator randomMatrixGenerator = new RandomMatrixGenerator();

        int MATRIX_SIZE = 1500;
        int THREADS_COUNT = 8;

        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();


        MatrixEntity matrixEntity = new MatrixEntity(
                randomMatrixGenerator
                        .generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE)
                        .getMatrix());

        MatrixEntity matrixEntity2 = new MatrixEntity(
                randomMatrixGenerator
                        .generateRandomMatrix(MATRIX_SIZE, MATRIX_SIZE)
                        .getMatrix());

        ParallelCalculator parallelCalculator = new ParallelCalculator();
        FoxCalculator foxCalculator = new FoxCalculator(matrixEntity, matrixEntity2, THREADS_COUNT);

        // Parallel test
        startTime = System.currentTimeMillis();
        MatrixEntity parRes = new MatrixEntity(parallelCalculator.multiplyMatrix(matrixEntity, matrixEntity2, THREADS_COUNT).getMatrix());
        endTime = System.currentTimeMillis();
        System.out.println("Parallel: " + (endTime - startTime) + " ms " + "with " + THREADS_COUNT + " threads" );

        // Fox test
        startTime = System.currentTimeMillis();
        MatrixEntity foxRes = new MatrixEntity(foxCalculator.multiplyMatrix().getMatrix());
        endTime = System.currentTimeMillis();
        System.out.println("Fox: " + (endTime - startTime) + " ms " + "with " + THREADS_COUNT + " threads" );

        // Check results
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (parRes.get(i, j) != foxRes.get(i, j)) {
                    System.out.println("Error");
                    return;
                }
            }
        }
    }
}
