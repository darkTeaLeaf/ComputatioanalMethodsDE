package sample;

public class ExactSolution extends IVP {
    private double[] y;

    public double[] calculateY(int N, int X, double x0, double y0) {
        y = new double[N + 1];

        createGrid(N, X, x0, y0);
        computeConst(x0, y0);

        for (int i = 0; i < N + 1; i++) {
            y[i] = 2 * x[i] + getConst() * Math.pow(Math.E, -2 * x[i]) - 1; // y = 2x + Ce^(-2x) - 1
        }

        return y;
    }

    public double[] getY() {
        return this.y;
    }
}
