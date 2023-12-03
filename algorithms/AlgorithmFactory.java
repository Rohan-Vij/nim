package algorithms;

import java.util.HashMap;
import java.util.Map;

public class AlgorithmFactory {
    private static final Map<Integer, IAlgorithm> algorithms = new HashMap<>();

    static {
        algorithms.put(1, new Binary()); // technically adding singleton classes??? idk
        algorithms.put(2, new RemoveMax());
        algorithms.put(3, new Random());
    }

    public static IAlgorithm getAlgorithm(int id) {
        if (!algorithms.containsKey(id)) {
            throw new IllegalArgumentException("Unknown Algorithm ID");
        }
        return algorithms.get(id);
    }

    public static Map<Integer, String> getAlgorithmList() {
        Map<Integer, String> algorithmNames = new HashMap<>();
        for (Map.Entry<Integer, IAlgorithm> entry : algorithms.entrySet()) {
            algorithmNames.put(entry.getKey(), entry.getValue().getClass().getSimpleName());
        }
        return algorithmNames;
    }
}
