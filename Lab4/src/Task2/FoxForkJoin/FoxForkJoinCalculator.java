package Task2.FoxForkJoin;

import Task2.Helper.MatrixEntity;

import java.util.concurrent.ForkJoinPool;

public class FoxForkJoinCalculator
{
    private MatrixEntity matrixEntity1;
    private MatrixEntity matrixEntity2;
    private MatrixEntity resultMatrix;


    public FoxForkJoinCalculator(MatrixEntity matrixEntity1, MatrixEntity matrixEntity2) {
        this.matrixEntity1 = matrixEntity1;
        this.matrixEntity2 = matrixEntity2;
        this.resultMatrix = new MatrixEntity(matrixEntity1.getRowsSize(), matrixEntity2.getColumnsSize());
    }

    public MatrixEntity multiplyMatrix(int threadsCount) {
        var pool = new ForkJoinPool(threadsCount);
        var task = new RecursiveFoxThread(matrixEntity1, matrixEntity2);
        var res = pool.invoke(task);
        return res;
    }
}
