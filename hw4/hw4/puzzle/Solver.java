package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private MinPQ<searchNode> pq;
    private List<searchNode> solution;
    private List<WorldState> answer;
    private int goalMoves;

    private class searchNode implements Comparable<searchNode> {
        private WorldState state;
        private int moves;
        private searchNode parent;

        public searchNode(WorldState state, int moves, searchNode parent) {
            this.state = state;
            this.moves = moves;
            this.parent = parent;
        }

        public boolean isGoal() {
            return state.isGoal();
        }

        public WorldState worldState() {
            return state;
        }

        @Override
        public int compareTo(searchNode o) {
            return moves + state.estimatedDistanceToGoal() - o.state.estimatedDistanceToGoal() - o.moves;
        }
    }

    private void bfsSearch(searchNode node) {
        for (WorldState w : node.worldState().neighbors()) {
            if (node.parent == null || !w.equals(node.parent.worldState())) {
                pq.insert(new searchNode(w, node.moves + 1, node));
            }
        }
    }

    public Solver(WorldState initial) {
        pq = new MinPQ<searchNode>();
        solution = new ArrayList<searchNode>();
        pq.insert(new searchNode(initial, 0, null));

        while (!pq.isEmpty()) {
            searchNode node = pq.delMin();
            if (node.isGoal()) {
                solution.add(node);
                this.answer = getAnswer(node);
                return;
            }
            bfsSearch(node);
            solution.add(node);
        }
    }

    private List<WorldState> getAnswer(searchNode goal) {
        goalMoves = goal.moves;
        List<WorldState> answer = new ArrayList<>();
        while (goal != null) {
            answer.add(goal.worldState());
            goal = goal.parent;
        }
        return reverse(answer);
    }

    private List<WorldState> reverse(List<WorldState> list) {
        List<WorldState> reversed = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            reversed.add(list.get(i));
        }
        return reversed;
    }

    public int moves() {
        return goalMoves;
    }

    public Iterable<WorldState> solution() {
        return answer;
    }
}
