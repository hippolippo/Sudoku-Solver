import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Board {
    String board_data = "";
    int first_free = 0;

    private final Character[] choices = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public Board(String filepath) throws FileNotFoundException {
        File file = new File(filepath);
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            board_data += data;
        }
        reader.close();
        first_free = board_data.indexOf('0');
    }

    public Board() {
        board_data = "000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        first_free = 0;
    }

    public int[] get_first_empty_tile(){
        //System.out.println("First Empty "+ first_free);
        return new int[]{first_free / 9 + 1, first_free % 9 + 1};
    }

    public Board(Board copy){
        board_data = copy.board_data;
        first_free = copy.first_free;
    }

    public Character get_tile(int row, int col){
        return board_data.charAt((row-1) * 9 + col - 1);
    }

    public Board set_tile(int row, int col, Character val){
        Board copy = new Board(this);
        copy.board_data = copy.board_data.substring(0, (row-1) * 9 + col - 1) + val + copy.board_data.substring((row-1) * 9 + col);
        //System.out.println("W " + val + " to " + row + " " + col + '\n' + copy.toString() + '\n');
        copy.first_free = copy.board_data.indexOf('0');
        return copy;
    }

    public boolean full(){
        return first_free == -1;
    }

    public static int[] quadrant_of(int row, int col){
        return new int[]{(row - 1)/3, (col - 1)/3};
    }

    public boolean allowed_at(int row, int col, Character val){
        for(int c = 1; c <= 9; c++){
            if(get_tile(row, c) == val)
                return false;
        }
        for(int r = 1; r <= 9; r++){
            if(get_tile(r, col) == val)
                return false;
        }
        int[] quadrant = quadrant_of(row, col);
        for(int s = 0; s < 9; s++){
            if(get_tile(quadrant[0] * 3 + 1 + s % 3, quadrant[1] * 3 + 1 + s / 3) == val)
                return false;
        }
        return true;
    }

    public boolean is_free(int row, int col){
        return get_tile(row, col) == '0';
    }

    public LinkedList<Character> available_at(int row, int col){
        LinkedList<Character> available = new LinkedList<>();
        for (Character val:
             choices) {
            if(allowed_at(row, col, val))
                available.add(val);
        }
        return available;
    }

    @Override
    public String toString() {
        String out = "";
        for(int i = 0; i < 9; i++){
            out += board_data.substring(i*9, i*9+9) + '\n';
        }
        return out;
    }
}