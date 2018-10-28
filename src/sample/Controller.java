package sample;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class Controller {
    private Euler eulerComputation;
    private ImprovedEuler improvedComputation;
    private RungeKutta rungeKuttaComputation;
    private ExactSolution exactComputation;
    private LocalError localError;
    private TotalError totalError;

    @FXML
    public TextField x0;
    @FXML
    public TextField y0;
    @FXML
    public TextField X;
    @FXML
    public TextField N;
    @FXML
    public LineChart<Number, Number> locErrorChart;
    @FXML
    public NumberAxis localE;
    @FXML
    NumberAxis x;
    @FXML
    NumberAxis y;
    @FXML
    LineChart<Number, Number> lineChart;
    @FXML
    Button computeButton;
    @FXML
    public LineChart<Number, Number> totalErrorChart;
    @FXML
    public Button computeButtonTab2;
    @FXML
    public TextField n0;
    @FXML
    public TextField NTab2;
    @FXML
    public void initialize() {
        eulerComputation = new Euler();
        improvedComputation = new ImprovedEuler();
        rungeKuttaComputation = new RungeKutta();
        exactComputation = new ExactSolution();
        localError = new LocalError();

        computeButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            getLineChart(Integer.parseInt(N.getText()), Double.parseDouble(X.getText()),
                    Double.parseDouble(x0.getText()), Double.parseDouble(y0.getText()));
            getLocalErrorChart();
            totalError = new TotalError(Double.parseDouble(x0.getText()), Double.parseDouble(y0.getText()),
                    Double.parseDouble(X.getText()));
        });

        computeButtonTab2.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
                getTotalErrorChart(Integer.parseInt(n0.getText()),Integer.parseInt(NTab2.getText())));

    }

    private void getLineChart(int N, double X, double x0, double y0){
        lineChart.getData().clear();

        eulerComputation.calculateY(N, X, x0, y0);
        improvedComputation.calculateY(N, X, x0, y0);
        rungeKuttaComputation.calculateY(N, X, x0, y0);
        exactComputation.calculateY(N, X, x0, y0);

        XYChart.Series eulerGraph = new XYChart.Series();
        XYChart.Series improvedGraph = new XYChart.Series();
        XYChart.Series rungeKuttaGraph = new XYChart.Series();
        XYChart.Series exactGraph = new XYChart.Series();

        eulerGraph.setName("Euler");
        improvedGraph.setName("Improved Euler");
        rungeKuttaGraph.setName("Runge-Kutta");
        exactGraph.setName("Exact");

        for (int i = 0; i < improvedComputation.x.length; i++) {
            eulerGraph.getData().add(new XYChart.Data<>(eulerComputation.x[i], eulerComputation.getY()[i]));
            improvedGraph.getData().add(new XYChart.Data<>(improvedComputation.x[i], improvedComputation.getY()[i]));
            rungeKuttaGraph.getData().add(new XYChart.Data<>(rungeKuttaComputation.x[i], rungeKuttaComputation.getY()[i]));
            exactGraph.getData().add(new XYChart.Data<>(exactComputation.x[i], exactComputation.getY()[i]));
        }

        lineChart.getData().add(eulerGraph);
        lineChart.getData().add(improvedGraph);
        lineChart.getData().add(rungeKuttaGraph);
        lineChart.getData().add(exactGraph);
    }

    private void getLocalErrorChart(){
        locErrorChart.getData().clear();
        localError.calculateE(eulerComputation.getY(),exactComputation.getY(),exactComputation.x);
        double[] eulerError = localError.getE();

        localError.calculateE(improvedComputation.getY(),exactComputation.getY(),exactComputation.x);
        double[] improvedError = localError.getE();

        localError.calculateE(rungeKuttaComputation.getY(),exactComputation.getY(),exactComputation.x);
        double[] rungeKuttaError = localError.getE();

        XYChart.Series eulerGraph = new XYChart.Series();
        XYChart.Series improvedGraph = new XYChart.Series();
        XYChart.Series rungeKuttaGraph = new XYChart.Series();

        eulerGraph.setName("Euler");
        improvedGraph.setName("Improved Euler");
        rungeKuttaGraph.setName("Runge-Kutta");

        for (int i = 0; i < localError.x.length; i++) {
            eulerGraph.getData().add(new XYChart.Data<>(localError.x[i], eulerError[i]));
            improvedGraph.getData().add(new XYChart.Data<>(localError.x[i], improvedError[i]));
            rungeKuttaGraph.getData().add(new XYChart.Data<>(localError.x[i], rungeKuttaError[i]));
        }

        locErrorChart.getData().add(eulerGraph);
        locErrorChart.getData().add(improvedGraph);
        locErrorChart.getData().add(rungeKuttaGraph);
    }

    private void getTotalErrorChart(int n0, int N){
        totalErrorChart.getData().clear();
        totalError.calculateE(n0, N,  0);
        double[] eulerError = totalError.getE();

        totalError.calculateE(n0, N, 1);
        double[] improvedError = totalError.getE();

        totalError.calculateE(n0, N, 2);
        double[] rungeKuttaError = totalError.getE();

        XYChart.Series eulerGraph = new XYChart.Series();
        XYChart.Series improvedGraph = new XYChart.Series();
        XYChart.Series rungeKuttaGraph = new XYChart.Series();

        eulerGraph.setName("Euler");
        improvedGraph.setName("Improved Euler");
        rungeKuttaGraph.setName("Runge-Kutta");

        for (int i = 0; i < totalError.n.length; i++) {
            eulerGraph.getData().add(new XYChart.Data<>(totalError.n[i], eulerError[i]));
            improvedGraph.getData().add(new XYChart.Data<>(totalError.n[i], improvedError[i]));
            rungeKuttaGraph.getData().add(new XYChart.Data<>(totalError.n[i], rungeKuttaError[i]));
        }

        totalErrorChart.getData().add(eulerGraph);
        totalErrorChart.getData().add(improvedGraph);
        totalErrorChart.getData().add(rungeKuttaGraph);
    }
}
