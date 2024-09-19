public class    GaussSeidelMethod {

    public static void main(String[] args) {
        // Example system of linear equations coefficients
        double[][] coefficients = {
                {27,6,-1},
                {6,15,2},
                {1,1,54},
                
        };

        // Right-hand side constants
        double[] constants = {85,72,110};

        // Initial guess
        double[] initialGuess = {0, 0, 0, 0};

        // Maximum number of iterations 
        int maxIterations = 100;

        // Tolerance for convergence
        double tolerance = 1e-10;

        // Solve the system using Gauss-Seidel method
        double[] solution = gaussSeidel(coefficients, constants, initialGuess, maxIterations, tolerance);

        // Print the solution
        System.out.println("Solution: ");
        for (int i = 0; i < solution.length; i++) {
            System.out.printf("x%d = %.6f%n", i + 1, solution[i]);
        }
    }

    public static double[] gaussSeidel(double[][] coefficients, double[] constants, double[] initialGuess, int maxIterations, double tolerance) {
        int n = coefficients.length;
        double[] currentSolution = initialGuess.clone();

        for (int k = 0; k < maxIterations; k++) {
            double[] nextSolution = new double[n];

            for (int i = 0; i < n; i++) {
                double sum = 0.0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += coefficients[i][j] * currentSolution[j];
                    }
                }
                nextSolution[i] = (constants[i] - sum) / coefficients[i][i];
            }

            // Check for convergence
            boolean converged = true;
            for (int i = 0; i < n; i++) {
                if (Math.abs(nextSolution[i] - currentSolution[i]) > tolerance) {
                    converged = false;
                    break;
                }
            }

            if (converged) {
                return nextSolution;
            }

            currentSolution = nextSolution;
        }

        throw new RuntimeException("Gauss-Seidel method did not converge within the specified number of iterations.");
    }
}