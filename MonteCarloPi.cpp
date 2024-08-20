#include <iostream>
#include <cmath>
#include <cstdlib>
#include <chrono>

int main() {
    const long totalPoints = 10000000L;
    long insideCirclePoints = 0;

    auto inicio = std::chrono::high_resolution_clock::now();

    for (long i = 0; i < totalPoints; ++i) {
        double x = static_cast<double>(rand()) / RAND_MAX;
        double y = static_cast<double>(rand()) / RAND_MAX;

        if (std::pow(x - 0.5, 2) + std::pow(y - 0.5, 2) <= std::pow(0.5, 2)) {
            insideCirclePoints++;
        }
    }

    auto resultado = std::chrono::high_resolution_clock::now() - inicio;
    auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(resultado).count();

    double piEstimate = 4.0 * static_cast<double>(insideCirclePoints) / totalPoints;
    std::cout << "Estimativa de PI (serial): " << piEstimate << std::endl;
    std::cout << "Tempo de execucao: " << duration << "ms" << std::endl;

    return 0;
}
