package Controller;

import Main.Notifiable;
import Model.Board;
import Model.Node;

import java.util.PriorityQueue;
import java.util.Random;

public class Controller {

    private Notifiable main;
    private Board board;
    private Thread pathFinder;

    public Controller(Notifiable main, Board board) {
        this.main = main;
        this.board = board;
    }

    public void prepare() {
        pathFinder = new Thread(this::executeSearch);
        pathFinder.start();
    }

    private void executeSearch() {
        Node solution = brandAndBound();
    }

    private Node brandAndBound() {
        PriorityQueue<Node> queue = new PriorityQueue<>();


        return null;
    }

    public void shuffle() {
        int[][] matrix = board.getMatrix();
        Random random = new Random();

        // Fisher-Yates algorithm
        for (int i = matrix.length - 1; i > 0; i--) {
            for (int j = matrix[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);
                int temp = matrix[i][j];
                matrix[i][j] = matrix[m][n];
                matrix[m][n] = temp;
            }
        }

        main.notify("shuffleFinished", null);
    }



}
