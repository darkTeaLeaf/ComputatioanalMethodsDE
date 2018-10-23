package sample;

public class Euler extends Grid {
    private double[] y;

    void mainFormula(double[] y, int N, int X, double x0, double y0) {
        createGrid(N, X, x0, y0);

        for (int i = 1; i < N + 1; i++) {
            y[i] = y[i - 1] + deltaY(x[i - 1], y[i - 1]); // y[i] = y[i-1] + deltaY[i-1]
        }
    }

    public double deltaY(double x, double y) {
        return h * RHS.solution(x, y); // deltaY[i-1] = hf(x[i-1], y[i-1])
    }

    public double[] calculateY(int N, int X, double x0, double y0) {
        y = new double[N + 1];

        mainFormula(y, N, X, x0, y0);

        return y;
    }

    public double[] getY(){
        return this.y;
    }

}
