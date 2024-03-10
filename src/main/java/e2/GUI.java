package e2;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import java.io.Serial;
import java.util.*;
import java.util.Map.Entry;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    
    @Serial
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
    private final Logics logics;
    
    public GUI(int size) {
        Random random = new Random();
        this.logics = new LogicsImpl(size, (p) -> random.nextBoolean());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Pair<Integer,Integer> pos = buttons.get(bt);
            boolean aMineWasFound = logics.hasMine(pos); // call the logic here to tell it that cell at 'pos' has been selected
            if (aMineWasFound) {
                quitGame();
                JOptionPane.showMessageDialog(this, "You lost!!");
            } else {
                drawBoard();            	
            }
            boolean isThereVictory = false; // call the logic here to ask if there is victory
            if (isThereVictory){
                quitGame();
                JOptionPane.showMessageDialog(this, "You won!!");
                System.exit(0);
            }
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                if (bt.isEnabled()){
                    final Pair<Integer,Integer> pos = buttons.get(bt);
                    logics.flag(pos);
                }
                drawBoard(); 
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb,new Pair<>(i,j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }
    
    private void quitGame() {
        this.drawBoard();
    	for (var entry: this.buttons.entrySet()) {
            // if this button is a mine, draw it "*"
            if(this.logics.hasMine(entry.getValue())) {
                entry.getKey().setText("*");
            }
            entry.getKey().setEnabled(false);
            // disable the button
    	}
    }

    private void drawBoard() {
        for (var entry: this.buttons.entrySet()) {
            // if this button is a cell with counter, put the number
            // if this button has a flag, put the flag
            if(this.logics.isFlagged(entry.getValue())) {
                entry.getKey().setText("F");
            }

        }
    }
    
}
