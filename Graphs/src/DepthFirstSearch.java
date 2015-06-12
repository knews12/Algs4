import java.util.*;

/**
 * Created by Kyle on 6/9/2015.
 */
public class DepthFirstSearch<T>
{
    private Graph<T> graph;
    private T source;
    private Map<T, T> markedVertices;
    private int count;

    public DepthFirstSearch(Graph<T> graph, T source)
    {
        this.graph = graph;
        this.source = source;
        markedVertices = new HashMap<>();
        count = -1; // ignore the source
        dfs(source);
    }

    /*
    Is source connected to vertex
     */
    public boolean marked(T vertex) { return markedVertices.containsKey(vertex);}

    public int count() { return count; }

    private void dfs(T v)
    {
        // mark v
        markedVertices.put(v, null);
        count++;

        // for each vertex check if it was marked
        for (T w : graph.adjacentVertices(v))
        {
            // if not marked then search that vertex
            if (!marked(w))
            {
                dfs(w);
                markedVertices.put(w, v);
            }
        }
    }
}
