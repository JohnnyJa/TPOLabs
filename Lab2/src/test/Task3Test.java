package test;

import Fox.FoxCalculator;
import Helper.MatrixEntity;
import Helper.RandomMatrixGenerator;
import Parallel.ParallelCalculator;
import Sequential.SequentialCalculator;

public class Task3Test {
    public static void main(String[] args) {
        RandomMatrixGenerator randomMatrixGenerator = new RandomMatrixGenerator();

        int MATRIX_SIZE = 2000;
        int THREADS_COUNT = 4;

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

        SequentialCalculator sequentialCalculator = new SequentialCalculator();
        ParallelCalculator parallelCalculator = new ParallelCalculator();
        FoxCalculator foxCalculator = new FoxCalculator(matrixEntity, matrixEntity2, THREADS_COUNT);


        // Sequential test
        startTime = System.currentTimeMillis();
        MatrixEntity seqRes = new MatrixEntity(sequentialCalculator.multiplyMatrix(matrixEntity, matrixEntity2).getMatrix());
        endTime = System.currentTimeMillis();
        System.out.println("Sequential: " + (endTime - startTime) + " ms " + "for " + MATRIX_SIZE + " matrix size");

        // Parallel test
        startTime = System.currentTimeMillis();
        MatrixEntity parRes = new MatrixEntity(parallelCalculator.multiplyMatrix(matrixEntity, matrixEntity2, THREADS_COUNT).getMatrix());
        endTime = System.currentTimeMillis();
        System.out.println("Parallel: " + (endTime - startTime) + " ms " + "for " + MATRIX_SIZE + " matrix size");

        // Fox test
        startTime = System.currentTimeMillis();
        MatrixEntity foxRes = new MatrixEntity(foxCalculator.multiplyMatrix().getMatrix());
        endTime = System.currentTimeMillis();
        System.out.println("Fox: " + (endTime - startTime) + " ms " + "for " + MATRIX_SIZE + " matrix size");

        // Check results
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (seqRes.get(i, j) != parRes.get(i, j) || seqRes.get(i, j) != foxRes.get(i, j)) {
                    System.out.println("Error");
                    return;
                }
            }
        }


    }
}
