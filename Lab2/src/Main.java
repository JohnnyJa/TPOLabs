import Fox.FoxCalculator;
import Helper.MatrixEntity;
import Helper.RandomMatrixGenerator;
import Parallel.ParallelCalculator;
import Sequential.SequentialCalculator;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        RandomMatrixGenerator randomMatrixGenerator = new RandomMatrixGenerator();

        int SIZE = 8;
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

        System.out.println("Matrix 3:");
        matrixEntity3.print2D();
        System.out.println("####");

        System.out.println("Matrix 4:");
        matrixEntity4.print2D();
        System.out.println("####");
//
//        SequentialCalculator sequentialCalculator = new SequentialCalculator();
//        MatrixEntity res = new MatrixEntity(sequentialCalculator.multiplyMatrix(matrixEntity3, matrixEntity4).getMatrix());
//        System.out.println("sequential result:");
//        res.print2D();
//
//        ParallelCalculator parallelCalculator = new ParallelCalculator();
//        System.out.println("parallel result:");
//        MatrixEntity res2 = new MatrixEntity(parallelCalculator.multiplyMatrix(matrixEntity3, matrixEntity4, 4).getMatrix());
//        res2.print2D();

        FoxCalculator foxCalculator = new FoxCalculator(matrixEntity3, matrixEntity4, 4);
        System.out.println("fox result:");
        MatrixEntity res3 = new MatrixEntity(foxCalculator.multiplyMatrix().getMatrix());
        res3.print2D();
    }
}