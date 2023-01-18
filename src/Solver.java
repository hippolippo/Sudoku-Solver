import java.util.Collections;
import java.util.LinkedList;

public class Solver {
    public static int count = 0;
    public static int solutionCount = 0;

    private static Board UniqueHelper(Board board) {
        if(solutionCount > 1)
            return null;
        if(board.full())
            return board;
        int[] free = board.get_first_empty_tile();
        LinkedList<Character> choices = board.available_at(free[0], free[1]);
        if(choices.size() == 0)
            return null;
        for (Character val:
                choices) {
            Board solution = UniqueHelper(board.set_tile(free[0], free[1], val));
            if(solution != null)
                solutionCount++;
            if(solutionCount > 1)
                return null;
        }
        return null;
    }

    public static boolean Unique(Board board){
        if(board.full())
            return true;
        solutionCount = 0;
        UniqueHelper(board);
        System.out.println(solutionCount);
        return solutionCount == 1;
    }
    public static Board Solve(Board board){
        if(board.full())
            return board;
        int[] free = board.get_first_empty_tile();
        LinkedList<Character> choices = board.available_at(free[0], free[1]);
        if(choices.size() == 0)
            return null;
        for (Character val:
             choices) {
            Board solution = Solve(board.set_tile(free[0], free[1], val));
            if(solution != null)
                return solution;
        }
        return null;
    }

    public static Board SolveRandom(Board board){
        if(board.full())
            return board;
        int[] free = board.get_first_empty_tile();
        LinkedList<Character> choices = board.available_at(free[0], free[1]);
        Collections.shuffle(choices);
        if(choices.size() == 0)
            return null;
        for (Character val:
                choices) {
            Board solution = SolveRandom(board.set_tile(free[0], free[1], val));
            if(solution != null)
                return solution;
        }
        return null;
    }
}
