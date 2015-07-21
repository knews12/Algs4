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
        markedVertices.put(this.source, null);
        dfs(this.source);
    }

    public boolean hasPathTo(T vertex) { return markedVertices.containsKey(vertex); }

    public Iterable<T> pathTo(T vertex)
    {
        Stack<T> path = new Stack<>();

        if (!hasPathTo(vertex)) return path;

        path.push(vertex);
        T next = markedVertices.get(vertex);

        while(next != source)
        {
            path.push(next);
            next = markedVertices.get(next);
        }

        path.push(source);

        return path;
    }

    private void dfs(T v)
    {
        for (T w : graph.adjacentVertices(v))
        {
            markedVertices.put(w, v);
            dfs(w);
        }
    }
}
