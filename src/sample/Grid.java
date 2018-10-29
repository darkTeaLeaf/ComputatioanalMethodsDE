package sample;

abstract class Grid {
    double h;
    double x0;
    double y0;

    double[] x;

    void createGrid(int N, double X, double x0, double y0){
        this.h = (X-x0)/N;
        this.x0 = x0;
        this.y0 = y0;

        x = new double[N + 1];

        for (int i = 0; i < N + 1; i++) {
            x[i] = x0 + h*i;
        }
    }
}
