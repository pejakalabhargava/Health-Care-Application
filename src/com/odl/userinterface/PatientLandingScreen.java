package com.odl.userinterface;

import javax.swing.JOptionPane;

import com.odl.bean.User;
import com.odl.facade.ODLDataFacade;

 /**
  * The Class PatientLandingScreen.
  *
  *  @author amitkatti 
  * @author pejakalabhargava
  */
public class PatientLandingScreen extends javax.swing.JFrame {

	/** The user. */
	User user;
	
	/** The view observation. */
	PatientViewObservation viewObservation;
	
	/** The add new obs type. */
	GenericAddANewObservationType addNewObsType;
	
	/** The enter obs. */
	PatientEnterAnObservation enterObs;
	
	/** The view alert. */
	PatientViewAlerts viewAlert;
	
	/** The find friends. */
	PatientFindNewHealthFriend findFriends;
	
	/** The find friends at risk. */
	PatientViewHealthFriendsAtRisk findFriendsAtRisk;
	
	/** The message. */
	PatientViewMessage message;
	
	/** The friends. */
	PatientViewHealthFriends friends;
	
	/** The data. */
	ODLDataFacade data = new ODLDataFacade();
    
	/**
	 * Creates new form NewJFrame2.
	 *
	 * @param user the user
	 */
    public PatientLandingScreen(User user) {
	   this.user = user;
	  data.setObservationTypeAlertsForUserViaStoredProc(user);
	   data.checkForActiveAlerts(user);
	    initComponents();
	  }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        

        jRadioButton1.setText("jRadioButton1");

        jRadioButton2.setText("jRadioButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ODL Application");
        setBackground(new java.awt.Color(117, 89, 89));
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 45, 0));
        if (user != null) {
            jLabel1.setText("WELCOME TO ODL " + user.getName());
         } else {
            jLabel1.setText("WELCOME TO ODL ");
         }
        
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Enter Observations");
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("View Observations");
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("Add a New Observation Type");
        jButton3.setFocusPainted(false);
        jButton3.setFocusable(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setFocusPainted(false);
        jButton4.setFocusable(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        if(user != null && user.isAlertExits()) {
        	jButton4.setText("View My Alerts*("+ user.getAlertCount()+")");
        } else {
        	jButton4.setText("View My Alerts");
            	
        }
    
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("odl.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setMaximumSize(new java.awt.Dimension(444, 14));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Find A New Health Friend");
        jButton5.setFocusPainted(false);
        jButton5.setFocusable(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("View Health Friends");
        jButton6.setFocusPainted(false);
        jButton6.setFocusable(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setText("View Health Friends At Risk");
        jButton7.setFocusPainted(false);
        jButton7.setFocusable(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setText("LogOut");
        jButton8.setFocusPainted(false);
        jButton8.setFocusable(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton9.setText("View Message");
        jButton9.setFocusPainted(false);
        jButton9.setFocusable(false);
      
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 28, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pack();
        this.setLocationRelativeTo(null);
    }// </editor-fold>                        

    /**
     * J button1 action performed.
     *
     * @param evt the evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if (enterObs== null) {
    		enterObs = new PatientEnterAnObservation(user,this);
       	}
       	this.setVisible(false);
       	enterObs.setVisible(true);
    
    }                                        

    /**
     * J button5 action performed.
     *
     * @param evt the evt
     */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if (findFriends== null) {
    		findFriends = new PatientFindNewHealthFriend(user,this);
       	}
    	if(findFriends.getFriendsPossible() == null || findFriends.getFriendsPossible().isEmpty()) {
    		JOptionPane.showMessageDialog(null,"No New Helath Friends Found in your class.");
    	} else {
	       	this.setVisible(false);
	       	findFriends.setVisible(true);
	    }
    }                                        

    /**
     * J button6 action performed.
     *
     * @param evt the evt
     */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if (friends== null) {
    		friends = new PatientViewHealthFriends(user,this);
       	}
     	friends.setVisible(true);
      }                                        

    /**
     * J button7 action performed.
     *
     * @param evt the evt
     */
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if (findFriendsAtRisk== null) {
    		findFriendsAtRisk = new PatientViewHealthFriendsAtRisk(user,this);
       	}
    	if(findFriendsAtRisk.getHealthFriendsAtRisk() == null || findFriendsAtRisk.getHealthFriendsAtRisk().isEmpty()) {
    		JOptionPane.showMessageDialog(null,"No  Helath Friends at Risk Found.");
    	} else {
	       	this.setVisible(false);
	       	findFriendsAtRisk.setVisible(true);
	    }
    }                                        

    /**
     * J button2 action performed.
     *
     * @param evt the evt
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	  if (viewObservation== null) {
    	       	 viewObservation = new PatientViewObservation(user,this);
    	       	}
    	       	this.setVisible(false);
    	       	viewObservation.setVisible(true);
        }                                        

    /**
     * J button3 action performed.
     *
     * @param evt the evt
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if (addNewObsType== null) {
    		addNewObsType = new GenericAddANewObservationType(user,this);
    	}
    	this.setVisible(false);
    	addNewObsType.setVisible(true);
       }                                        

    /**
     * J button4 action performed.
     *
     * @param evt the evt
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	if (viewAlert== null) {
    		viewAlert = new PatientViewAlerts(user,this);
    	}
    	this.setVisible(false);
    	viewAlert.setVisible(true);
      }                                        

    /**
     * J button9 action performed.
     *
     * @param evt the evt
     */
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    	message = new PatientViewMessage(user,this);
    	this.setVisible(false);
    	message.setVisible(true);
    }                                        

    /**
     * J button8 action performed.
     *
     * @param evt the evt
     */
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {                                         
    user = null;
    new WelcomeScreen().setVisible(true);
    dispose();
    }                                        
  
  /**
   * The main method.
   *
   * @param args the command line arguments
   */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PatientLandingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientLandingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientLandingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientLandingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientLandingScreen(null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    /** The j button1. */
    private javax.swing.JButton jButton1;
    
    /** The j button2. */
    private javax.swing.JButton jButton2;
    
    /** The j button3. */
    private javax.swing.JButton jButton3;
    
    /** The j button4. */
    private javax.swing.JButton jButton4;
    
    /** The j button5. */
    private javax.swing.JButton jButton5;
    
    /** The j button6. */
    private javax.swing.JButton jButton6;
    
    /** The j button7. */
    private javax.swing.JButton jButton7;
    
    /** The j button8. */
    private javax.swing.JButton jButton8;
    
    /** The j button9. */
    private javax.swing.JButton jButton9;
    
    /** The j label1. */
    private javax.swing.JLabel jLabel1;
    
    /** The j label2. */
    private javax.swing.JLabel jLabel2;
    
    /** The j radio button1. */
    private javax.swing.JRadioButton jRadioButton1;
    
    /** The j radio button2. */
    private javax.swing.JRadioButton jRadioButton2;
    // End of variables declaration     
    /**
     * Gets the j button4.
     *
     * @return the jButton4
     */
  	public javax.swing.JButton getjButton4() {
  		return jButton4;
  	}

  	/**
	   * Sets the j button4.
	   *
	   * @param jButton4 the jButton4 to set
	   */
  	public void setjButton4(javax.swing.JButton jButton4) {
  		this.jButton4 = jButton4;
  	}
}
