/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concentration.game;

import concentration.game.core.Card;
import concentration.game.startpackage.Initializer;
import concentration.game.ui.MainForm;
import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import com.sun.java.swing.plaf.motif.MotifLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import java.io.File;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 *
 * @author Suiri Otaku
 */
public class ConcentrationGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Initializer init = new Initializer(); // init cards

        try {

            UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
            //UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new MainForm(init).start();
            }
        });
    }

}
