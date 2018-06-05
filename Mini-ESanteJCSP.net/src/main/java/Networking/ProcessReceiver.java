/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking;

import com.xpertsoft.mini.esante.gui.FichePrescription;
import com.xpertsoft.mini.esante.gui.menu;
import javax.swing.JOptionPane;
import org.jcsp.lang.Alternative;
import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;


/**
 *
 * @author Soufiane
 */
public class ProcessReceiver implements CSProcess {

    private AltingChannelInput in;
    private AltingChannelInput config;
    
    private menu m;
    private boolean recive;

    public ProcessReceiver(AltingChannelInput _in, AltingChannelInput _config, menu _menu) {
        this.in = _in;
        this.m = _menu;
        this.config = _config;
        recive = false;
    }

    @Override
    public void run() {
        while (true) {
            AltingChannelInput[] chans = {config, in};
            Alternative alt = new Alternative(chans);
            switch (alt.priSelect()) {
                case 0:
                    ProcessReceiver.this.recive = (boolean) config.read();

                    break;
                case 1:
                    if (ProcessReceiver.this.recive) {
                    MessageClient message = (MessageClient) in.read();
                    switch (message.getTypeRequest()) {
                        case MessageClient.PRESCRIPTION:
                            int result = JOptionPane.showConfirmDialog(m, "Une Prescription vien d'etre arrivee voulez vous le voir", "Accuse de reception", JOptionPane.YES_NO_OPTION);
                            if (JOptionPane.YES_OPTION == result) {
                                FichePrescription pf = new FichePrescription(m, false, "Recever", message.getEntet(), message.getDetail());
                                pf.setVisible(true);
                            }
                            break;
                        case MessageClient.SOLICITATION:

                            int result2 = JOptionPane.showConfirmDialog(m, message.getSolicitation(), "Solisitation", JOptionPane.YES_NO_OPTION);

                            if (result2 == JOptionPane.YES_OPTION) {

                            }
                            break;
                    }
                    }
                    break;
            }

        }
    }

}
