package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int[] nodeTo;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        nodeTo = new int[maze.N() * maze.N()];
        nodeTo[0] = -1;
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        determineCycles(0);
    }

    // Helper methods go here
    private void determineCycles(int v) {
        marked[v] = true;
        announce();

        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                nodeTo[w] = v;
                determineCycles(w);
            } else if (nodeTo[v] != w) {
                //cycle exist
                edgeTo[w] = v;
                announce();
                return;
            }
        }
    }
}

