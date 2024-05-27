package Task2.FoxForkJoin;

import Task2.Helper.MatrixEntity;
import Task2.Sequential.SequentialCalculator;

import java.util.HashMap;
import java.util.concurrent.RecursiveTask;

public class RecursiveFoxThread extends RecursiveTask<MatrixEntity> {


    private static final int THRESHOLD = 64;
    private final MatrixEntity matrixA;
    private final MatrixEntity matrixB;

    public RecursiveFoxThread(MatrixEntity matrixA, MatrixEntity matrixB) {
        this.matrixA = matrixA;
        this.matrixB = matrixB;
    }

    @Override
    protected MatrixEntity compute() {
        int size = matrixB.getColumnsSize();

        if (size <= THRESHOLD) {
            return new SequentialCalculator().multiplyMatrix(matrixA, matrixB);
        } else {
            int newSize = size / 2;

            MatrixEntity[][] subMatricesA = matrixA.split();
            MatrixEntity[][] subMatricesB = matrixB.split();

            MatrixEntity[][] subMatricesC = new MatrixEntity[2][2];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    RecursiveFoxThread task1 = new RecursiveFoxThread(subMatricesA[i][0], subMatricesB[0][j]);
                    RecursiveFoxThread task2 = new RecursiveFoxThread(subMatricesA[i][1], subMatricesB[1][j]);

                    task1.fork();
                    MatrixEntity part2 = task2.compute();
                    MatrixEntity part1 = task1.join();

                    subMatricesC[i][j] = part1.add(part2);
                }
            }

            return MatrixEntity.join(subMatricesC);
        }
    }
}
