import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Sudoku{
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("Sudoku");
    JPanel textPanel = new JPanel();
    JLabel textLabel = new JLabel();
    JPanel boardPanel = new JPanel();
    JPanel buttonPanel = new JPanel();

    JButton numSelected = null;
    int mistakes = 0;

    String[] puzzle = {
            "--74916-5",
            "2---6-3-9",
            "-----7-1-",
            "-586----4",
            "--3----9-",
            "--62--187",
            "9-4-7---2",
            "67-83----",
            "81--45---"
    };

    String[] solution = {
            "387491625",
            "241568379",
            "569327418",
            "758619234",
            "123784596",
            "496253187",
            "934176852",
            "675832941",
            "812945763"
    };

    class Tile extends JButton{
        int r;
        int c;

        Tile(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    Sudoku(){
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial",Font.BOLD,30));
        textLabel.setText("Sudoku: 0");
        textLabel.setHorizontalAlignment(JLabel.CENTER);

        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(9,9));
        setupTiles();
        frame.add(boardPanel, BorderLayout.CENTER);

        setupButtons();
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setIconImage(new ImageIcon(getClass().getResource("/Sudoku.png")).getImage());

        frame.setVisible(true);
    }

    public void setupTiles(){
        for(int r=0;r<9;r++){
            for(int c=0;c<9;c++){
                Tile tile = new Tile(r,c);
                char tileChar = puzzle[r].charAt(c);
                if(tileChar != '-'){
                    tile.setFont(new Font("Arial",Font.BOLD,20));
                    tile.setBackground(Color.lightGray);
                    tile.setText(String.valueOf(tileChar));
                }
                else{
                    tile.setFont(new Font("Arial",Font.BOLD,20));
                    tile.setBackground(Color.white);
                }
                tile.setFocusable(false);
                boardPanel.add(tile);

                tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Tile tile = (Tile) e.getSource();
                        int r = tile.r;
                        int c = tile.c;

                        if(numSelected != null){
                            if(tile.getText() != ""){
                                return;
                            }
                            String numSelectedText = numSelected.getText();
                            String solutionText = String.valueOf(solution[r].charAt(c));
                            if(numSelectedText.equals(solutionText)){
                                tile.setText(numSelectedText);
                            }
                            else{
                                mistakes += 1;
                                textLabel.setText("Sudoku: " + String.valueOf(mistakes));
                            }
                        }
                    }
                });
            }
        }
    }

    public void setupButtons(){
        for(int i=0;i<9;i++){
            JButton button = new JButton();
            button.setFont(new Font("Arial",Font.PLAIN,20));
            button.setBackground(Color.white);
            button.setText(String.valueOf(i));
            button.setFocusable(false);
            buttonPanel.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();

                    if(numSelected != null){
                        numSelected.setBackground(Color.white);
                    }

                    numSelected = button;
                    button.setBackground(Color.lightGray);
                }
            });
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Sudoku s = new Sudoku();
    }
}