package com.odl.userinterface;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.odl.bean.ObservationData;
import com.odl.bean.User;
import com.odl.facade.ODLDataFacade;

/**
 * The Class PhysicianViewObservationOfAPatient.
 *
 * @author pejakalabhargava
 * @author amitkatti
 *
 */
public class PhysicianViewObservationOfAPatient extends javax.swing.JFrame {

	/** The user. */
	User user;
    
    /** The parent frame. */
    PhysicianLandingScreen parentFrame;
    
    /** The data facade. */
    ODLDataFacade dataFacade = new ODLDataFacade();
    
    /** The patient list. */
    Map<String,String>  patientList = new HashMap<String,String>();
    
    /** The obs list. */
    Map<String,String>  obsList = new HashMap<String,String>();

    /**
     * Instantiates a new physician view observation of a patient.
     *
     * @param user the user
     * @param landingFrameForUser the landing frame for user
     */
    public PhysicianViewObservationOfAPatient(User user, PhysicianLandingScreen landingFrameForUser) {
	    this.user = user;
	    parentFrame = landingFrameForUser;
	    patientList = dataFacade.populateAllPatients();
	    dataFacade.getAttributesAndObsTypes();
	    obsList = dataFacade.getAllObservationTypes();
		initComponents();
	}

    private void initComponents() {

    	buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jButton2 = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Viewing Observation Type");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 33, 0));
        jLabel1.setText("View Patient Observations");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Select a Patient:");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("View Observation");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("View By Patient");
        jRadioButton1.setSelected(true);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        jRadioButton1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton1ItemStateChanged(evt);
            }
        });
      
        
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("View By Observation Type");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jRadioButton2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton2ItemStateChanged(evt);
            }
        });
      
    
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(28, 28, 28)
                                .addComponent(jRadioButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(jButton2)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        this.setLocationRelativeTo(null);
        populateComboBox();
    }
    /**
     * Populate combo box.
     */
    private void populateComboBox() {
    	jComboBox1.removeAllItems();
        for (String value : patientList.keySet()) {
    		jComboBox1.addItem(value);
    	}	
	}
    
    /**
     * Populate jcombo b ox with obs type.
     */
    private void populateJcomboBOxWithObsType() {
    	jComboBox1.removeAllItems();
    	for (String value : obsList.values()) {
    		jComboBox1.addItem(value);
    	}	
	}
    
    

	/**
	 * J button1 action performed.
	 *
	 * @param evt the evt
	 */
	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
	    this.setVisible(false);
	    if(parentFrame != null) {parentFrame.setVisible(true);}
    }                                        
	
	/**
	 * J button2 action performed.
	 *
	 * @param evt the evt
	 */
	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		jTextArea1.setText("");
		jButton2.setEnabled(false);
		jComboBox1.setEnabled(false);
		if(jRadioButton1.isSelected()) {
		String patientName = (String) jComboBox1.getSelectedItem();
		String patientId = patientList.get(patientName);
		User user = new User();
		user.setUserID(patientId);
		dataFacade.getObservationDataForUser(user);
		for (Iterator<ObservationData> iterator = user.getObservationData().iterator(); iterator.hasNext();) {
			ObservationData type = (ObservationData) iterator.next();
			jTextArea1.append(type.toString());
			jTextArea1.append("\n");
			jTextArea1.append("----------------------------------------------------\n");
		}
		if(user.getObservationData() == null || user.getObservationData().isEmpty()){
			jTextArea1.append("There are no Observations for  this patient.");
		}
		} else {
			if(jRadioButton2.isSelected()) {
				String obsName = (String) jComboBox1.getSelectedItem();
				int obsId = Integer.parseInt(getKeyByValue(obsName));
				User user1 = new User();
				user1.setUserID("dummy");
				java.util.Set<String> name = dataFacade.getpatientDataForObsType(user1,obsId);
				jTextArea1.setText("There are/is " + name.size() + " patient(s) with this observation\n");
				
				for (Iterator iterator = name.iterator(); iterator.hasNext();) {
					String string = (String) iterator.next();
					string = getKeyByValuePatient(string);
					jTextArea1.append(string);
					jTextArea1.append("\n");
				}
				jTextArea1.append("\n");
				jTextArea1.append("\n");
				jTextArea1.append("------------------------------------------------");
				jTextArea1.append("\nThe observation set is as below:\n");
			
				for (Iterator<ObservationData> iterator = user1.getObservationData().iterator(); iterator.hasNext();) {
					ObservationData type = (ObservationData) iterator.next();
					jTextArea1.append(type.toString());
					jTextArea1.append("\n");
					jTextArea1.append("----------------------------------------------------\n");
				}
				if(user1.getObservationData() == null || user1.getObservationData().isEmpty()){
					jTextArea1.append("There are no Observations for  this patient.");
				}
				}
		}
		jButton2.setEnabled(true);
		jComboBox1.setEnabled(true);
		jTextArea1.setCaretPosition(0);
	}

	 /**
 	 * Gets the key by value.
 	 *
 	 * @param value the value
 	 * @return the key by value
 	 */
 	private  String getKeyByValue(String value) {
	        for (Entry<String, String> entry : obsList.entrySet()) {
	            if (value.equals(entry.getValue())) {
	                return entry.getKey();
	            }
	        }
	        return null;
	    }

	 /**
 	 * Gets the key by value patient.
 	 *
 	 * @param value the value
 	 * @return the key by value patient
 	 */
 	private  String getKeyByValuePatient(String value) {
	        for (Entry<String, String> entry : patientList.entrySet()) {
	            if (value.equals(entry.getValue())) {
	                return entry.getKey();
	            }
	        }
	        return null;
	    }

 /**
  * J combo box1 action performed.
  *
  * @param evt the evt
  */
 private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    /**
     * J combo box1 item state changed.
     *
     * @param evt the evt
     */
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {                                            
      	jTextArea1.setText("");
     }                                           

    /**
     * J radio button2 action performed.
     *
     * @param evt the evt
     */
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                              
    jLabel2.setText("Select an Obs Type:");
     populateJcomboBOxWithObsType();
    }                                             

    /**
     * J radio button1 action performed.
     *
     * @param evt the evt
     */
    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	 jLabel2.setText("Select a patient:");
         	populateComboBox();
          }                                             

    /**
     * J radio button1 item state changed.
     *
     * @param evt the evt
     */
    private void jRadioButton1ItemStateChanged(java.awt.event.ItemEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    /**
     * J radio button2 item state changed.
     *
     * @param evt the evt
     */
    private void jRadioButton2ItemStateChanged(java.awt.event.ItemEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    /**
     * The main method.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PhysicianViewObservationOfAPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhysicianViewObservationOfAPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhysicianViewObservationOfAPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhysicianViewObservationOfAPatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PhysicianViewObservationOfAPatient(null,null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify       
    /** The button group1. */
    private javax.swing.ButtonGroup buttonGroup1;
    
    /** The j button1. */
    private javax.swing.JButton jButton1;
    
    /** The j button2. */
    private javax.swing.JButton jButton2;
    
    /** The j combo box1. */
    private javax.swing.JComboBox jComboBox1;
    
    /** The j label1. */
    private javax.swing.JLabel jLabel1;
    
    /** The j label2. */
    private javax.swing.JLabel jLabel2;
    
    /** The j radio button1. */
    private javax.swing.JRadioButton jRadioButton1;
    
    /** The j radio button2. */
    private javax.swing.JRadioButton jRadioButton2;
    
    /** The j scroll pane2. */
    private javax.swing.JScrollPane jScrollPane2;
    
    /** The j text area1. */
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration                   
}
