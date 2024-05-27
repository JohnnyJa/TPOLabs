package Parallel;

import Helper.MatrixEntity;

import java.util.ArrayList;

public class ParallelCalculator {
    /**
     * Метод для множення матриць за допомогою стрічкового алгоритму
     * @param matrixEntity1 перша матриця
     * @param matrixEntity2 друга матриця
     * @param threadsCount кількість потоків
     * @return результат множення матриць у вигляді MatrixEntity
     */
    public MatrixEntity multiplyMatrix(MatrixEntity matrixEntity1, MatrixEntity matrixEntity2, int threadsCount) {

        // Перевірка того чи можна множити матриці між собою (кількість стовпців першої матриці
        // має бути рівною кількості рядків другої матриці)
        if (matrixEntity1.getColumnsSize() != matrixEntity2.getRowsSize()) {
            throw new IllegalArgumentException("matrices cannot be multiplied because the " +
                    "number of columns of matrix A is not equal to the number of rows of matrix B.");
        }

        int height = matrixEntity1.getRowsSize();
        int width = matrixEntity2.getColumnsSize();
        MatrixEntity resultMatrix = new MatrixEntity(height, width);


        // Встановлюємо кількість рядків які будуть обровблятися в одному потоці
        int rowsPerThread = height / threadsCount;
        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0; i < threadsCount; i++) {
            int from = i * rowsPerThread;
            int to;
            // Встановлюємо межі роботи для кожного потоку
            if (i == threadsCount - 1) {
                to = height;
            } else {
                to = (i + 1) * rowsPerThread;
            }
            threads.add(new Thread(() -> { // Створюємо поток який буде рахувати результат у встановленому діапазоні
                for (int row = from; row < to; row++) { // Ітеруємось по призначених рядках у межах встановленого діапазону
                    for (int col = 0; col < width; col++) {
                        for (int k = 0; k < matrixEntity2.getRowsSize(); k++) { // Множимо елементи матриць
                            resultMatrix.set(row, col, resultMatrix.get(row, col)
                                    + matrixEntity1.get(row, k) * matrixEntity2.get(k, col));
                        }
                    }
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }

        try { // Чекаємо поки всі потоки закінчать свою роботу
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resultMatrix;
    }
}
