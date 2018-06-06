package Networking;

import com.xpertsoft.mini.esante.gui.FichePrescription;
import com.xpertsoft.mini.esante.gui.menu;
import javax.swing.JOptionPane;
import org.jcsp.lang.Alternative;
import org.jcsp.lang.AltingChannelInput;
import org.jcsp.lang.CSProcess;
import org.jcsp.net2.*;
import org.jcsp.net2.cns.*;
import org.jcsp.net2.tcpip.*;

public class NetworkingJCSP implements CSProcess {

    private static String IPAnnuaire = "";
    menu menu;



    private static NetAltingChannelInput inFromRemout;
    private static NetAltingChannelInput inFromAnnuaire;
    private static AltingChannelInput configReciv;
    private static AltingChannelInput inFromLocal;
    private static AltingChannelInput configIPAnnuaire;
    private static NetChannelOutput Channel2Remout; 
    private static NetChannelOutput Chan2Annurair;
    private menu m;
    private boolean recive;

    public NetworkingJCSP(NetAltingChannelInput _inFromAnnuaire, NetAltingChannelInput _inFromOut, AltingChannelInput _configIPAnnuaire, AltingChannelInput _configReciv, AltingChannelInput _inFromLocal, menu _menu) {

        this.m = _menu;
        configReciv = _configReciv;
        inFromRemout = _inFromOut;
        inFromLocal = _inFromLocal;
        configIPAnnuaire = _configIPAnnuaire;
        inFromAnnuaire = _inFromAnnuaire;
        recive = false;
    }
    public final static int FROMCONFIGIPANNUAIR = 0;
    public final static int FROMCONFIGRECIV = 1;
    public final static int FROMREMOUT = 2;
    public final static int FROMLOCAL = 3;
    public final static int FROMANNUAR = 4;

    @Override
    public void run() {
        while (true) {
            AltingChannelInput[] chans = {configIPAnnuaire, configReciv, inFromRemout, inFromLocal,inFromAnnuaire};

            Alternative alt = new Alternative(chans);
            switch (alt.priSelect()) {
                // pour initiliser le CNS
                case FROMCONFIGIPANNUAIR:
                    IPAnnuaire = configIPAnnuaire.read().toString();
                    GetCNS();
                    break;
                // pour arriter ou demmare le service de reciption
                case FROMCONFIGRECIV:
                    recive = (boolean) configReciv.read();
                    m.ChangeStatRecive(recive);
                    break;
                // pour les solicitation ou prescription qui vients des autre client 
                case FROMREMOUT:
                    if (recive) {
                        MessageClient message = (MessageClient) inFromRemout.read();
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
                // pour les demmande qui vien de l'interface etulisateur
                case FROMLOCAL:
                    Object o = inFromLocal.read();
                    if (o instanceof Contact) {
                        Chan2Annurair.write((Contact) o);
                    } else if (o instanceof MessageClient) {
                        Channel2Remout.write((MessageClient) o);
                    }else if(o instanceof  TCPIPNodeAddress){
                        NodeID remoteID =
                        LinkFactory.getLink((TCPIPNodeAddress) o).getRemoteNodeID();
                        Channel2Remout = NetChannel.one2net(remoteID, 45);
                    }
                    break;
                // pour le feedback de l'annuaire
                case FROMANNUAR:
                    Object o2 = inFromAnnuaire.read();
                    if (o2 instanceof Contact) {
                    NodeID remoteID =
                        LinkFactory.getLink(((Contact) o2).getNodAdress()).getRemoteNodeID();
                        Channel2Remout = NetChannel.one2net(remoteID, 45);
                    } else {
                        switch((int)o2){
                            case 0:
                                 m.ChangeStatAnnuaireConnection(true);
                                break;
                            case 1:
                                 m.ChangeStatAnnuaireConnection(false);
                                break;
                            case 2:
                                m.ChangeStatAnnuaireDeconnection(true);
                                break;
                                
                            case 3:
                                m.ChangeStatAnnuaireDeconnection(false);
                                break;
                        }
                       
                    }
                    break;
            }

        }
    }

    private static void GetCNS() {
        try {

            TCPIPNodeAddress nsAddr = new TCPIPNodeAddress(IPAnnuaire, 7890);
            CNS.initialise(nsAddr);
            String connectName = "JCSPChatChannel" + ".client2serverconnect";
            // Must use CNS to create channels
            Chan2Annurair = CNS.one2net(connectName);

        } catch (Exception e) {
            System.out.println("Error - could not connect to server: " + e);
        }
    }

}
