import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Collections;

class greedy_alg {
    private Graph graph;

    public greedy_alg() {
        this.graph = new Graph();
    }

    public void addAirplane(airplane airplane) {
        graph.addEdge(airplane);
    }

     public int calculateTotalCost(java.util.List<States.E_Countries> bestPath) {
        int totalCost = 0;

        for (int i = 0; i < bestPath.size() - 1; i++) {
            States.E_Countries currentCountry = bestPath.get(i);
            States.E_Countries nextCountry = bestPath.get(i + 1);
            
            for (airplane airplane : graph.getNeighbors(currentCountry)) {
                if (airplane.destination_().equals(nextCountry)) {
                    totalCost += airplane.cost();
                    break;
                }
            }
        }
        return totalCost;
    }
    public java.util.List<States.E_Countries> calculateBestPath(States.E_Countries start, States.E_Countries end) {
        Map<States.E_Countries, Double> distance = new HashMap<>();
        Map<States.E_Countries, States.E_Countries> previous = new HashMap<>();
        PriorityQueue<States.E_Countries> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(distance::get));

        distance.put(start, 0.0);
        priorityQueue.add(start);

        while (!priorityQueue.isEmpty()) {
            States.E_Countries current = priorityQueue.poll();

            for (airplane neighbor : graph.getNeighbors(current)) {
                States.E_Countries next = neighbor.destination_();
                double newDistance = distance.get(current) + neighbor.cost();

                if (newDistance < distance.getOrDefault(next, Double.POSITIVE_INFINITY)) {
                    distance.put(next, newDistance);
                    previous.put(next, current);
                    priorityQueue.add(next);
                }
            }
        }
        // Reconstruct the path
        java.util.List<States.E_Countries> path = new ArrayList<>();
        for (States.E_Countries at = end; at != null; at = previous.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        return path;
    }
}
