import org.jenetics.*;
import org.jenetics.engine.Engine;
import org.jenetics.engine.EvolutionStatistics;
import org.jenetics.engine.codecs;
import org.jenetics.util.DoubleRange;
import static org.jenetics.engine.EvolutionResult.toBestPhenotype;
import static org.jenetics.engine.limit.bySteadyFitness;

/**
 * Created by nfrik on 8/21/16.
 */
public class RastriginFunction {
    private static final double A = 10;
    private static final double S = 100;
    private static final double R = 50.12;
    private static final int N = 2;

    private static double fitness(final double[] x) {
        double value = A * N;
        for (int i = 0; i < N; ++i) {
            value += x[i] * x[i] - A * Math.cos(2.0 * Math.PI * x[i]);
        }

        return value;
    }

    private static double fitness2(final double[] x) {
        double value = 1;
        for (int i = 0; i < N; ++i) {
            value *= Math.sin(x[i]) * Math.sin(x[i]) * Math.exp(x[i]/S);
        }

        return value;
    }

    public static void main(final String[] args) {
        final Engine<DoubleGene, Double> engine = Engine.builder(
                RastriginFunction::fitness2,
                codecs.ofVector(DoubleRange.of(-R, R), N))
                .populationSize(100)
                .optimize(Optimize.MAXIMUM)
                .alterers(
                        new Mutator<>(0.03),
                        new MeanAlterer<>(0.6))
                .build();

        final EvolutionStatistics<Double, ?>
                statistics = EvolutionStatistics.ofNumber();

        final Phenotype<DoubleGene, Double> best = engine.stream()
                .limit(bySteadyFitness(7))
                .peek(statistics)
                .collect(toBestPhenotype());

        System.out.println(statistics);
        System.out.println(best);

    }
}
