package sample;

public class RungeKutta extends Euler{
    private double[] y;

    public double deltaY(double x, double y) {
        return (h/6)*(k1(x, y) + 2*k2(x, y) + 2*k3(x, y) + k4(x, y));
    }

    private double k1(double x, double y){
        return RHS.solution(x, y);
    }

    private double k2(double x, double y){
        return RHS.solution(x + (h/2), y + (h*(k1(x, y))/2));
    }

    private double k3(double x, double y){
        return RHS.solution(x + (h/2), y + (h*k2(x, y))/2);
    }

    private double k4(double x, double y){
        return RHS.solution(x + h, y + h*k3(x, y));
    }

    public void calculateY(int N, double X, double x0, double y0) {
        y = new double[N + 1];

        mainFormula(y, N, X, x0, y0);

    }

    public double[] getY(){
        return this.y;
    }
}
