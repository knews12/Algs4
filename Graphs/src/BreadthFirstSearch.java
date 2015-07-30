/**
 * Created by Kyle on 7/28/2015.
 */
import java.util.Queue;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class BreadthFirstSearch<T>
{
    private Graph<T> graph;
    private T source;
    private int count;
    private Map<T, T> markedVertices;

    public BreadthFirstSearch(Graph graph, T source)
    {
        this.graph = graph;
        this.source = source;
        markedVertices = new HashMap<>();
        count = -1;
        bfs(this.source);
    }

    private void bfs(T v)
    {
        if (!markedVertices.containsKey(v)) {
            markedVertices.put(v, null);
        }

        Queue<T> visitedVertices = new LinkedList<>();

        for (T w:graph.adjacentVertices(v) )
        {
            markedVertices.put(w, v);
            visitedVertices.add(w);
        }

        while(visitedVertices.peek() != null)
        {
            bfs(visitedVertices.poll());
        }
    }
}
