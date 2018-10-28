package sample;

public class ExactSolution extends IVP {
    private double[] y;

    public void calculateY(int N, double X, double x0, double y0) {
        y = new double[N + 1];

        createGrid(N, X, x0, y0);
        computeConst();

        for (int i = 0; i < N + 1; i++) {
            y[i] = 2 * x[i] + getConst() * Math.pow(Math.E, -2 * x[i]) - 1; // y = 2x + Ce^(-2x) - 1
        }
    }

    public double[] getY() {
        return this.y;
    }
}
