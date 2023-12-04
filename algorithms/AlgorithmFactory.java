package algorithms;

import java.util.HashMap;
import java.util.Map;

public class AlgorithmFactory {
    private static final Map<Integer, IAlgorithm> algorithms = new HashMap<>();

    static {
        algorithms.put(1, new Binary()); // technically adding singleton classes??? idk
        algorithms.put(2, new RemoveMax());
        algorithms.put(3, new Random());
        algorithms.put(4, new Logic());
    }

    public static IAlgorithm getAlgorithm(int id) {
        if (!algorithms.containsKey(id)) {
            throw new IllegalArgumentException("Unknown Algorithm ID");
        }
        return algorithms.get(id);
    }

    public static Map<Integer, String> getAlgorithmList() {
        Map<Integer, String> algorithmNames = new HashMap<>();
        for (Map.Entry<Integer, IAlgorithm> entry : algorithms.entrySet()) { // make hashmap a set to iterate
            algorithmNames.put(entry.getKey(), entry.getValue().getClass().getSimpleName());
        }
        return algorithmNames;
    }

    public static IAlgorithm getAlgorithmByName(String name) {
        for (IAlgorithm algorithm : algorithms.values()) {
            if (algorithm.getClass().getSimpleName().equalsIgnoreCase(name)) {
                return algorithm;
            }
        }
        throw new IllegalArgumentException("Unknown Algorithm Name");
    }
}
