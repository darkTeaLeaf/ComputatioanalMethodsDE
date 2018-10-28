package sample;

public class TotalError {
    private double x0;
    private double y0;
    private double X;
    int[] n;
    private double[] e;

    TotalError(double x0, double y0, double X) {
        this.x0 = x0;
        this.y0 = y0;
        this.X = X;
    }

    public void calculateE(int n0, int N, int methodNum) {
        e = new double[N - n0];
        n = new int[N - n0];

        for (int i = 0; i < n.length; i++) {
            n[i] = n0 + i;
        }

        LocalError localError = new LocalError();
        ExactSolution exactSolution = new ExactSolution();

        for (int i = 0; i < n.length; i++) {
            if (methodNum == 0) {
                Euler method = new Euler();
                method.calculateY(n[i], X, x0, y0);
                exactSolution.calculateY(n[i], X, x0, y0);

                localError.calculateE(method.getY(), exactSolution.getY(), method.x);
                e[i] = localError.maxError();
            }

            if (methodNum == 1) {
                ImprovedEuler method = new ImprovedEuler();
                method.calculateY(n[i], X, x0, y0);
                exactSolution.calculateY(n[i], X, x0, y0);

                localError.calculateE(method.getY(), exactSolution.getY(), method.x);
                e[i] = localError.maxError();
            }

            if (methodNum == 2) {
                RungeKutta method = new RungeKutta();
                method.calculateY(n[i], X, x0, y0);
                exactSolution.calculateY(n[i], X, x0, y0);

                localError.calculateE(method.getY(), exactSolution.getY(), method.x);
                e[i] = localError.maxError();
            }
        }
    }

    public double[] getE(){
        return e;
    }

}
