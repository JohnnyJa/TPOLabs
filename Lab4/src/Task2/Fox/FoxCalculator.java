package Task2.Fox;


import Task2.Helper.MatrixEntity;

public class FoxCalculator {
    private MatrixEntity matrixEntity1;
    private MatrixEntity matrixEntity2;
    private int threadsCount;
    private MatrixEntity resultMatrix;


    public FoxCalculator(MatrixEntity matrixEntity1, MatrixEntity matrixEntity2, int threadsCount) {
        this.matrixEntity1 = matrixEntity1;
        this.matrixEntity2 = matrixEntity2;
        this.resultMatrix = new MatrixEntity(matrixEntity1.getRowsSize(), matrixEntity2.getColumnsSize());

        if (threadsCount > matrixEntity1.getRowsSize() * matrixEntity2.getColumnsSize() / 4) {
            this.threadsCount = matrixEntity1.getRowsSize() * matrixEntity2.getColumnsSize() / 4;
        } else this.threadsCount = Math.max(threadsCount, 1);
    }

    public MatrixEntity multiplyMatrix() {
        int step = (int) Math.ceil(1.0 * matrixEntity1.getRowsSize() / (int) Math.sqrt(threadsCount));

        FoxCalculatorThread[] threads = new FoxCalculatorThread[threadsCount];
        int idx = 0;

        for (int i = 0; i < matrixEntity1.getRowsSize(); i += step) {
            for (int j = 0; j < matrixEntity2.getColumnsSize(); j += step) {
                threads[idx] = new FoxCalculatorThread(matrixEntity1, matrixEntity2, i, j, step, resultMatrix);
                idx++;
            }
        }

        for (int i = 0; i < idx; i++) {
            threads[i].start();
        }

        for (int i = 0; i < idx; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return resultMatrix;
    }
}
