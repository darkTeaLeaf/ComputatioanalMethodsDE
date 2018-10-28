package sample;

public class LocalError {
    double[] x;
    private double[] e;

    public void calculateE(double[] method, double[] exactSolution, double[] x) {
        this.x = x;
        this.e = new double[x.length];

        for (int i = 0; i < e.length; i++) {
            e[i] = Math.abs(exactSolution[i] - method[i]);
        }
    }

    public double[] getE(){
        return e;
    }

    public double maxError(){
        double max = 0;
        for (double anE : e) {
            if (max < anE) {
                max = anE;
            }
        }
        return max;
    }
}
