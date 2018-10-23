package sample;

public class ImprovedEuler extends Euler{
    private double[] y;

    public double deltaY(double x, double y){
        return h*RHS.solution(x+(h/2), y + (h/2)*RHS.solution(x, y));
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
