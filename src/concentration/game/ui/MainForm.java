/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game.ui;

import com.sun.glass.ui.Screen;
import concentration.game.core.Game;
import concentration.game.startpackage.Initializer;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;

/**
 *
 * @author Suiri Otaku
 */
public class MainForm extends JFrame {

    PlayGround pg;
    ScorePanel sp;
    MenuBar mb;
    Initializer initializer;
    int op = 0;
    /**
     *
     * @param initializer
     */
    public MainForm(Initializer initializer) {
        this.initializer = initializer;
        init();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Concentration Card Game");
    }

    private void init() {

        sp = new ScorePanel();
        sp.setBounds(610, 10, 350, 600);
        sp.setCover(initializer.getCovers().get(0));
        sp.showCover();
        pg = new PlayGround(this.initializer.getCards(), new Game(), sp);
        pg.setBounds(10, 10, 600, 600);
        pg.setComp(this.initializer.getComparators());
        sp.setPlayGround(pg);
        mb = new MenuBar(pg, sp);
        mb.setCovers(initializer.getCovers());
        setLayout(new BorderLayout());
        add(mb, BorderLayout.NORTH);
        JPanel mainp = new JPanel();
        mainp.setLayout(new FlowLayout());
        mainp.add(pg);
        mainp.add(sp);
        add(mainp, BorderLayout.CENTER);
        //sp.setSize(getWidth(),getHeight());
        //pack();
        setOpacity((float) 0);
        setResizable(false);
        setSize(800, 800);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        start();
        addWindowListener(new WindowAdapter() {
            @Override
    public void windowClosing(WindowEvent windowEvent) {
                shutdown();
               
    }
        });
    }

    /**
     *
     */
    public void start() {
        Timer tr = new Timer(100,e->{
           while(op < 85)
           {
               op++;
            setOpacity((float) op/100);
               try {
                   Thread.sleep(20);
               } catch (InterruptedException ex) {
                   Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
        });
        tr.setRepeats(false);
        tr.start();
    }
    private void shutdown(){
      
    
           while(op > 0)
           {
               op--;
            setOpacity((float) op/100);
               
               try {
                   Thread.sleep(10);
               } catch (InterruptedException ex) {
                   Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
          
           System.exit(0);
        
 
    }
    
}
