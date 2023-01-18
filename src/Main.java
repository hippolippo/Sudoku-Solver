import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args){
        Board board = new Board();
        try {
            board = new Board("board");
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
        }
        Board solved = Solver.Solve(board);
        /*
        Board solved = Solver.SolveRandom(board);
        Board lastBoard = null;
        Board thisBoard = new Board(solved);
        while(Solver.Unique(thisBoard)){
            lastBoard = thisBoard;
            thisBoard = thisBoard.set_tile((int)(Math.random()*9) + 1,(int)(Math.random()*9) + 1,'0');
            //System.out.println(thisBoard.toString() + '\n');
        }
        System.out.println(lastBoard);
        */
        System.out.println(solved);
    }
}
