import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Graph {
    private Map<States.E_Countries, List<airplane>> graph;

    public Graph() {
        this.graph = new HashMap<>();
    }

    public void addEdge(airplane airplane) {
        graph.computeIfAbsent(airplane.departure_(), k -> new ArrayList<>()).add(airplane);
    }

    public List<airplane> getNeighbors(States.E_Countries node) {
        return graph.getOrDefault(node, Collections.emptyList());
    }

    public Set<States.E_Countries> getAllNodes() {
        return graph.keySet();
    }
}
