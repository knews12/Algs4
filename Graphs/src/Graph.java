/**
 * Created by Kyle on 6/9/2015.
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Graph<T>
{
    protected Map<T, List<T>> vertices;
    protected int edgeCount;

    public Graph()
    {
        vertices = new HashMap();
        edgeCount = 0;
    }

    public int numberOfVertices()
    {
        return vertices.size();
    }

    public int numberOfEdges()
    {
        return edgeCount;
    }

    public void addEdge(T v, T w)
    {
        ensureVertexExists(v);
        ensureVertexExists(w);

        List<T> vEdges = vertices.get(v);
        List<T> wEdges = vertices.get(w);
        if (!vEdges.contains(w))
        {
            vEdges.add(w);
            wEdges.add(v);
            edgeCount++;
        }
    }

    public Iterable<T> adjacentVertices(T v)
    {
        if (vertices.containsKey(v)) {
            return vertices.get(v);
        }
        else return new ArrayList<>();
    }

    protected void ensureVertexExists(T v) {
        if (!vertices.containsKey(v)) {
            vertices.put(v, new ArrayList());
        }
    }

    public static void main(String[] args)
    {
        Graph<Integer> g = new Graph();
        g.addEdge(0, 1);
        g.addEdge(1, 2);

        System.out.println("Number of edges: " + g.numberOfEdges());

        DepthFirstPaths<Integer> dfp = new DepthFirstPaths<>(g, 0);
        Iterable<Integer> path = dfp.pathTo(2);
        for (Integer i: path) {
            System.out.print(i + " -> ");
        }
    }
}
