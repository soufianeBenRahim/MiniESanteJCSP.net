package com.xpertsoft.mini.esante.gui;

    //////////////////////////////////////////////////////////////////////
    //                                                                  //
    //  JCSP ("CSP for Java") Libraries                                 //
    //  Copyright (C) 1996-2008 Peter Welch and Paul Austin.            //
    //                2001-2004 Quickstone Technologies Limited.        //
    //                                                                  //
    //  This library is free software; you can redistribute it and/or   //
    //  modify it under the terms of the GNU Lesser General Public      //
    //  License as published by the Free Software Foundation; either    //
    //  version 2.1 of the License, or (at your option) any later       //
    //  version.                                                        //
    //                                                                  //
    //  This library is distributed in the hope that it will be         //
    //  useful, but WITHOUT ANY WARRANTY; without even the implied      //
    //  warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR         //
    //  PURPOSE. See the GNU Lesser General Public License for more     //
    //  details.                                                        //
    //                                                                  //
    //  You should have received a copy of the GNU Lesser General       //
    //  Public License along with this library; if not, write to the    //
    //  Free Software Foundation, Inc., 59 Temple Place, Suite 330,     //
    //  Boston, MA 02111-1307, USA.                                     //
    //                                                                  //
    //  Author contact: P.H.Welch@kent.ac.uk                             //
    //                                                                  //
    //                                                                  //
    //////////////////////////////////////////////////////////////////////


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @author Quickstone Technologies Limited
 */

public class ConnectDialog extends JDialog {
  private JTextField cnsTextField = new JTextField("",30);
  private JTextField channelTextField = new JTextField("JCSPChatChannel", 30);
  private JTextField usernameTextField = new JTextField(30);
  private JTextField PasswordTextField = new JTextField("",30);
  private JButton connectButton = new JButton ("Connect");
  private String cnsName;
  private String channelName;
  private String username;
  private String Password;

  public ConnectDialog() {
    this.setModal(true);
    Container cp = this.getContentPane();
    Box vbox1 = Box.createVerticalBox();
    cp.add(vbox1);
    //not in 1.3 vbox1.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0,0,1,1, Color.darkGray),BorderFactory.createMatteBorder(1,1,0,0, Color.white)),BorderFactory.createEmptyBorder(4,4,4,4)));
    Box hbox1 = Box.createHorizontalBox();
    vbox1.add(hbox1);
    hbox1.add(new JLabel("Server address:"));
    hbox1.add(Box.createHorizontalStrut(2));
    hbox1.add(cnsTextField);
    Box hbox2 = Box.createHorizontalBox();
    vbox1.add(Box.createVerticalStrut(4));
    vbox1.add(hbox2);
    hbox2.add(new JLabel("Channel name:"));
    hbox2.add(Box.createHorizontalStrut(2));
    hbox2.add(channelTextField);
    vbox1.add(Box.createVerticalStrut(4));
    Box hbox3 = Box.createHorizontalBox();
    vbox1.add(hbox3);
    hbox3.add(new JLabel("Username:"));
    hbox3.add(Box.createHorizontalStrut(2));
    hbox3.add(usernameTextField);
    
    Box hbox4 = Box.createHorizontalBox();
    vbox1.add(hbox4);
    hbox4.add(new JLabel("Password:"));
    hbox4.add(Box.createHorizontalStrut(2));
    hbox4.add(PasswordTextField);
    
    vbox1.add(Box.createVerticalStrut(4));
    vbox1.add(connectButton);
    connectButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
         cnsName = cnsTextField.getText();
         username = usernameTextField.getText();
         channelName = channelTextField.getText();
         Password=PasswordTextField.getText();
         ConnectDialog.this.setVisible(false);
      }
    });

    this.pack();
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension td = this.getSize();
    this.setBounds((d.width - td.width)/2,(d.height - td.height)/2,td.width,td.height);
    this.setVisible(true);
  }
  protected void processWindowEvent(WindowEvent e) {
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }
  public String getChannelName() {
    return channelName;
  }
  public String getCNSName() {
    return cnsName;
  }
  public String getUsername() {
    return username;
  }
}
