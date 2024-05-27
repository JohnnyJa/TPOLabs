package Helper;

public class RandomMatrixGenerator {

    MatrixEntity matrixEntity;

    public MatrixEntity getMatrixEntity() {
        return matrixEntity;
    }

    public void setMatrixEntity(MatrixEntity matrixEntity) {
        this.matrixEntity = matrixEntity;
    }

    public MatrixEntity generateRandomMatrix(int rows, int columns) {
        matrixEntity = new MatrixEntity(rows, columns);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrixEntity.set(i, j, (int) (Math.random() * 10));
            }
        }
        return matrixEntity;
    }
}
