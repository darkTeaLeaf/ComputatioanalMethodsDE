package sample;

class IVP extends Grid{
    private double C;

    double computeConst(double x0, double y0){
        this.C = (1 - 2*x0 - y0)*Math.pow(Math.E, 2*x0); // C = (1 - 2x - y)e^(2x)

        return C;
    }

    double getConst(){
        return C;
    }
}
