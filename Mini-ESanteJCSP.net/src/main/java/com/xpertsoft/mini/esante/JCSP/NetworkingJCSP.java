package com.xpertsoft.mini.esante.JCSP;

import Networking.Contact;
import com.xpertsoft.mini.esante.Model.PrescriptionDetail;
import com.xpertsoft.mini.esante.Model.Prescriptionentet;

import com.xpertsoft.mini.esante.gui.menu;


import java.util.List;
import org.jcsp.lang.ChannelOutput;
import org.jcsp.net.NetChannelEnd;
import org.jcsp.net.NetChannelInput;
import org.jcsp.net.NetChannelLocation;
import org.jcsp.net.NetChannelOutput;
import org.jcsp.net.Node;
import org.jcsp.net.cns.CNS;
import org.jcsp.net.tcpip.TCPIPNodeFactory;
import org.jcsp.net2.tcpip.TCPIPNodeAddress;

import org.jcsp.net2.*;

import org.jcsp.net2.tcpip.*;

public class NetworkingJCSP {
 

public String IPAnnuaire="";


    menu menu;
    NetChannelOutput connectChan ;
    ChannelOutput messageOutChan;
    NetChannelInput messageInChan;
    public boolean connectToAnnuer = false;
    public NetworkingJCSP(menu _menu) {
        menu=_menu;

  
    }

    public void EndRecive(){

    }
    public void Recive() {
   
    }
    public void GetCNS() {
    try {
         Node.getInstance().init(new TCPIPNodeFactory(IPAnnuaire));
         String connectName = "JCSPChatChannel" + ".client2serverconnect"; 
         connectChan = CNS.createOne2Net(connectName);
         connectToAnnuer=true;
    }
    catch (Exception e) {
        connectToAnnuer=false;
      System.out.println("Error - could not connect to server: " + e);

    }
    }

    public void GetServiceDestant(String Psudo) {
        if(connectChan==null)    GetCNS(); 
        if(!connectToAnnuer) return;
        messageInChan = NetChannelEnd.createNet2One();
        NetChannelOutput serverOutChan = NetChannelEnd.createOne2Net(messageInChan.getChannelLocation());
        connectChan.write(new Contact(Psudo, serverOutChan));  
        Object o = messageInChan.read();
        messageOutChan = null;
        if (o instanceof NetChannelLocation) {
            messageOutChan = NetChannelEnd.createAny2Net((NetChannelLocation) o);
        }
    }

    public boolean Connect(String name, String pass) {
        if(connectChan==null)    GetCNS(); 
        if(!connectToAnnuer) return false;
                messageInChan = NetChannelEnd.createNet2One();
        NetChannelOutput serverOutChan = NetChannelEnd.createOne2Net(messageInChan.getChannelLocation());
        connectChan.write(new Contact(name, pass, serverOutChan, 1));
        Object o = messageInChan.read();
        return (boolean) o;
    }
    public boolean Deconnect(String name, String pass) {
       if(connectChan==null)    GetCNS(); 
       if(!connectToAnnuer) return false;
        if(messageInChan==null)messageInChan = NetChannelEnd.createNet2One();
        NetChannelOutput serverOutChan = NetChannelEnd.createOne2Net(messageInChan.getChannelLocation());
       connectChan.write(new Contact(name, pass, serverOutChan, 2));
       Object o = messageInChan.read();
       return (boolean) o;
    }

    public void SendPrescription(Prescriptionentet p,List<PrescriptionDetail> detail) {

    }
     
    public void SendSollicitation(String Message) {
 
    }

}
