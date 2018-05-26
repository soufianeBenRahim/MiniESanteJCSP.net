package com.xpertsoft.mini.esante.JCSP;

import com.xpertsoft.mini.esante.Model.PrescriptionDetail;
import com.xpertsoft.mini.esante.Model.Prescriptionentet;

import com.xpertsoft.mini.esante.gui.menu;


import java.util.List;
import javax.swing.JOptionPane;
import org.jcsp.net.NetChannelInput;
import org.jcsp.net.NetChannelOutput;
import org.jcsp.net.Node;
import org.jcsp.net.NodeInitFailedException;
import org.jcsp.net.cns.CNS;


public class NetworkingJCSP {


    private menu menu;
    public NetworkingJCSP(menu _menu) {
        menu=_menu;
    }

    public void EndRecive(){

    }
    public void Recive() {
     try {
            Node.getInstance().init();
            NetChannelInput in = CNS.createNet2One("rx.in");
            String Message = (String)in.read();
            int result=JOptionPane.showConfirmDialog(menu, Message,"Solisitation" , JOptionPane.YES_NO_OPTION);
            
            CNS.destroyChannelEnd(in);
          } catch (NodeInitFailedException e) {
            e.printStackTrace();
          }
    }
    public String GetIPUser(String Psudo) {
        
  
       return null;
    }
    public boolean Connect(String name, String pass) {
        boolean result = false;
      
        return result;
    }
    public boolean Deconnect(String name, String pass) {
        
     
      return false;
    }

    public void SendPrescription(Prescriptionentet p,List<PrescriptionDetail> detail) {
      
    }
     
    public void SendSollicitation(String Message) {
      try {
          Node.getInstance().init();
          NetChannelOutput out = CNS.createOne2Net("rx.in");
          out.write(Message);
          CNS.destroyChannelEnd(out);
        } catch (NodeInitFailedException e) {
          e.printStackTrace();
        }
    }

}
