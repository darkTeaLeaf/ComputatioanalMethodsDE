package sample;

class IVP extends Grid{
    private double C;

    void computeConst(){
        this.C = (y0 + 1 - 2*x0)*Math.pow(Math.E, 2*x0); // C = (1 - 2x + y)e^(2x)

    }

    double getConst(){
        return C;
    }
}
