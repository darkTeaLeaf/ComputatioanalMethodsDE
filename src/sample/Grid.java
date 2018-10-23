package sample;

abstract class Grid {
    int X;
    double h;
    int N;
    double x0;
    double y0;

    double[] x;
    double[] y;

    void createGrid(int N, int X, double x0, double y0){
        this.X = X;
        this.h = (double) X/N;
        this.x0 = x0;
        this.y0 = y0;
        this.N = N;

        x = new double[N + 1];
        y = new double[N + 1];

        for (int i = 0; i < N + 1; i++) {
            x[i] = x0 + h*i;
        }
    }
}
