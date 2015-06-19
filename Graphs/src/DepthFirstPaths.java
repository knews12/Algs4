import java.util.*;

/**
 * Created by Kyle on 6/10/2015.
 */
public class DepthFirstPaths<T>
{
    private Graph<T> graph;
    private T source;
    private Map<T, T> markedVertices;

    public DepthFirstPaths(Graph<T> graph, T source)
    {
        this.graph = graph;
        this.source = source;
        markedVertices = new HashMap<>();
    }

    public boolean hasPathTo(T vertex) { return markedVertices.containsKey(vertex); }

    public Iterable<T> pathTo(T vertex)
    {

    }

    private void dfs(T v)
    {
        markedVertices.put(v, null);

        for (T w : graph.adjacentVertices(v))
        {
            markedVertices.put(w, v);
            dfs(w);
        }
    }
}
