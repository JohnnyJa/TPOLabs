package Helper;

import java.util.Arrays;

public class MatrixEntity {
    private final int[][] matrix;

    /**
     * Конструктор класу MatrixEntity, до конструктора передається матриця, яку необхідно зберегти
     *
     * @param matrix матриця у вигляді двомірного масиву, яку необхідно зберегти
     */
    public MatrixEntity(int[][] matrix) {
        this.matrix = matrix;
    }


    /**
     * Конструктор класу MatrixEntity, до конструктора передаються розміри матриці, яку необхідно зберегти
     *
     * @param rowsSize    кількість рядків матриці
     * @param columnsSize кількість стовпців матриці
     */
    public MatrixEntity(int rowsSize, int columnsSize) {
        matrix = new int[rowsSize][columnsSize];
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int getRowsSize() {
        return matrix.length;
    }

    public int getColumnsSize() {
        return matrix[0].length;
    }

    /**
     * Метод, який повертає значення елемента матриці за індексами
     *
     * @param i індекс рядка
     * @param j індекс стовпця
     * @return значення елемента матриці за індексами
     */
    public int get(int i, int j) {
        return matrix[i][j];
    }

    /**
     * Метод, який задає значення елемента матриці за індексами
     *
     * @param i     індекс рядка
     * @param j     індекс стовпця
     * @param value значення елемента матриці за індексами
     */
    public void set(int i, int j, int value) {
        matrix[i][j] = value;
    }


    /**
     * Метод, який виводить матрицю в консоль
     */
    public void print2D() {
        Arrays.stream(matrix).map(Arrays::toString).forEach(System.out::println);
    }
}
