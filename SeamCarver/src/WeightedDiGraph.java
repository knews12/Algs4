/**
 * Created by Kyle on 6/3/2015.
 */
public class WeightedDiGraph<T extends Comparable<T>>
{
    private int v;
    private ST<T, Bag<Edge<T>>> adj;


    WeightedDiGraph(int v)
    {
        this.v = v;
        adj = new ST();
    }

    public int V()
    {
        return v;
    }

    public void addEdge(T v, T w, double weight)
    {
        if (!adj.contains(v))
            adj.put(v, new Bag());

        Edge e = new Edge(w, weight);
        adj.get(v).add(e);
    }

    public Iterable<T> adj(T v)
    {
        if (adj.contains(v))
        {
            Bag<T> verts = new Bag();
            for (Edge e : adj.get(v))
            {
                verts.add((T)(e.getV()));
            }
            return verts;
        }
        else return new Bag();
    }

    private class Edge<T extends Comparable<T>> implements Comparable<Edge>
    {
        private double weight;
        private T v;

        public Edge(T v, double weight)
        {
            this.v = v;
            this.weight = weight;
        }

        public T getV()
        {
            return v;
        }

        public int compareTo(Edge that)
        {
            return (int)(this.weight - that.weight);
        }
    }
}
