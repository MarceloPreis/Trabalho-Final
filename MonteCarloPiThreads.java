package trabalho_final;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Random;

public class MonteCarloPiThreads {

    private static final AtomicLong insideCirclePoints = new AtomicLong(0);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        long totalPoints = 10000000L;
        int threadCount = 24; // Número de threads

        // Cria um ExecutorService com um número fixo de threads
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        long startTime = System.currentTimeMillis(); // Início do contador de tempo

        // Submete tarefas ao executor
        Future<?>[] futures = new Future[threadCount];
        for (int i = 0; i < threadCount; i++) {
            futures[i] = executor.submit(() -> {
                Random random = new Random();
                long localInsideCirclePoints = 0;

                for (long j = 0; j < totalPoints / threadCount; j++) {
                    double x = random.nextDouble();
                    double y = random.nextDouble();

                    if (Math.pow(x - 0.5, 2) + Math.pow(y - 0.5, 2) <= Math.pow(0.5, 2)) {
                        localInsideCirclePoints++;
                    }
                }

                // Atualiza o contador global de forma thread-safe
                insideCirclePoints.addAndGet(localInsideCirclePoints);
            });
        }

        // Aguarda todas as tarefas terminarem
        for (Future<?> future : futures) {
            future.get(); // Bloqueia até que a tarefa termine
        }

        long endTime = System.currentTimeMillis(); // Fim do contador de tempo
        executor.shutdown();

        double piEstimate = 4.0 * insideCirclePoints.get() / totalPoints;
        System.out.println("Estimativa de PI com ExecutorService: " + piEstimate);
        System.out.println("Tempo de execução: " + (endTime - startTime) + "ms");
    }
}
