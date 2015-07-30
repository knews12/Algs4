/**
 * Created by Kyle on 7/28/2015.
 */
import java.util.List;

public class DiGraph<T> extends Graph<T>
{
    public DiGraph()
    {
        super();
    }

    @Override
    public void addEdge(T v, T w)
    {
        super.ensureVertexExists(v);
        super.ensureVertexExists(w);

        List<T> vEdges = super.vertices.get(v);
        if (!vEdges.contains(w)) {
            vEdges.add(w);
            super.edgeCount++;
        }
    }
}
