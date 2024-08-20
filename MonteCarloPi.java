package trabalho_final;

public class MonteCarloPi {
	public static void main(String[] args) {
        long totalPoints = 10000000L;
        long insideCirclePoints = 0;

        long startTime = System.currentTimeMillis(); // Início do contador de tempo

        for (long i = 0; i < totalPoints; i++) {
            double x = Math.random();
            double y = Math.random();

            // Verifica se o ponto está dentro do círculo unitário
            if (Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2) <= Math.pow(0.5, 2)) {
                insideCirclePoints++;
            }
        }
        
        long endTime = System.currentTimeMillis(); // Fim do contador de tempo

        double piEstimate = 4.0 * insideCirclePoints / totalPoints;
        System.out.println("Estimativa de PI: " + piEstimate);
        System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");
    }
}
