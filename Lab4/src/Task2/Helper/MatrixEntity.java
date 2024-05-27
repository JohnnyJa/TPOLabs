package Task2.Helper;

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

    public MatrixEntity[][] split() {
        int midRow = getRowsSize() / 2;
        int midCol = getColumnsSize() / 2;

        MatrixEntity[][] result = new MatrixEntity[2][2];
        result[0][0] = getSubMatrix(0, midRow, 0, midCol);
        result[0][1] = getSubMatrix(0, midRow, midCol, getColumnsSize());
        result[1][0] = getSubMatrix(midRow, getRowsSize(), 0, midCol);
        result[1][1] = getSubMatrix(midRow, getRowsSize(), midCol, getColumnsSize());

        return result;
    }

    private MatrixEntity getSubMatrix(int startRow, int endRow, int startCol, int endCol) {
        int[][] subMatrix = new int[endRow - startRow][endCol - startCol];
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                subMatrix[i - startRow][j - startCol] = matrix[i][j];
            }
        }
        return new MatrixEntity(subMatrix);
    }

    public MatrixEntity add(MatrixEntity other) {
        if (getRowsSize() != other.getRowsSize() || getColumnsSize() != other.getColumnsSize()) {
            throw new IllegalArgumentException("Matrices must have the same dimensions for addition");
        }

        int[][] resultMatrix = new int[getRowsSize()][getColumnsSize()];

        for (int i = 0; i < getRowsSize(); i++) {
            for (int j = 0; j < getColumnsSize(); j++) {
                resultMatrix[i][j] = this.matrix[i][j] + other.matrix[i][j];
            }
        }

        return new MatrixEntity(resultMatrix);
    }

    public static MatrixEntity join(MatrixEntity[][] subMatrices) {
        int newSize = subMatrices[0][0].getRowsSize() * 2;
        MatrixEntity result = new MatrixEntity(newSize, newSize);

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                MatrixEntity subMatrix = subMatrices[i][j];
                for (int row = 0; row < subMatrix.getRowsSize(); row++) {
                    for (int col = 0; col < subMatrix.getColumnsSize(); col++) {
                        result.set(i * subMatrix.getRowsSize() + row, j * subMatrix.getColumnsSize() + col, subMatrix.get(row, col));
                    }
                }
            }
        }

        return result;
    }
}
