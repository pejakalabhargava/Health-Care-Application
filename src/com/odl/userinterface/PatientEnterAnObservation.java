package com.odl.userinterface;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import com.odl.bean.User;
import com.odl.facade.ODLDataFacade;

/**
 * The Class PatientEnterAnObservation.
 * 
 * @author amitkatti 
 * @author pejakalabhargava
 */
public class PatientEnterAnObservation extends javax.swing.JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4720475011456692948L;
	
	/** The user. */
	User user;
	
	/** The parent frame. */
	PatientLandingScreen parentFrame;
	
	/** The data facade. */
	ODLDataFacade dataFacade = new ODLDataFacade();
	
	/** The obs attribute. */
	Map<String,Map<String,String>> obsAttribute = new HashMap<String,Map<String,String>>();
    
    /**
     * Creates new form EnterAnObservation.
     *
     * @param usr the usr
     * @param parent the parent
     */
    public PatientEnterAnObservation(User usr, PatientLandingScreen parent) {
    	user = usr;
    	parentFrame = parent;
    	dataFacade.getObservationTypeForUser(user);
    	initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
    	this.setTitle("Observeration Data Entry.");
    	jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
      
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Select Observation Type:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Enter Value for Attribute 1:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Enter Value for Attribute 2:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Enter Value for Attribute 3:");

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField2FocusGained(evt);
            }
        });

        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField3FocusGained(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Date  & Time Of Observation:");

        jComboBox1.setMaximumRowCount(20);
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.setFocusable(false);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Enter Observation Data:");

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTheObservationData(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.setFocusPainted(false);
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonClicked(evt);
            }
        });

        jSpinner1.setModel(new javax.swing.SpinnerDateModel());
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1)
                            .addComponent(jTextField2)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE))))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jButton2))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

       
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 710, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(24, Short.MAX_VALUE)))
        );
        
        setUpObsType();
        pack();
        this.setLocationRelativeTo(null);
    }
    
    /**
     * Sets the up obs type.
     */
    private void setUpObsType() {
		if (user.getObservationTypesForUser() == null) {
			dataFacade.getObservationTypeForUser(user);
		}
    	for(Entry<String, String> entry : user.getObservationTypesForUser().entrySet()) {
    		String key = entry.getKey();
    		jComboBox1.addItem(key);
    	}
    }

	/**
	 * Save the observation data.
	 *
	 * @param evt the evt
	 */
	private void saveTheObservationData(java.awt.event.ActionEvent evt) {                                         
     String type = (String) jComboBox1.getSelectedItem();
     String obsId = user.getObservationTypesForUser().get(type);
     Map<String,String> attributeLists = obsAttribute.get(type);
     Map<String,String> valueForAttributes = new HashMap();
     String[] key = attributeLists.keySet().toArray(new String[attributeLists.keySet().size()]);
     if (key != null && key.length>0) {
    	 String value = jTextField1.getText();
    	 valueForAttributes.put(attributeLists.get(key[0]), value);
     }
	 if (key != null && key.length>1) {
		 String value = jTextField2.getText();
    	 valueForAttributes.put(attributeLists.get(key[1]), value);
     }
	 if (key != null && key.length>2) {
		 String value = jTextField3.getText();
    	 valueForAttributes.put(attributeLists.get(key[2]), value);
     }
	 Date date = (Date) jSpinner1.getValue();
	 System.out.println(date);
	 System.out.println(valueForAttributes.toString());
	 System.out.println(obsId);
	 boolean status = dataFacade.saveObservationData(user.getUserID(),obsId,valueForAttributes,date);
	 user.setObservationData(null);
	 if (status == true) {
		 parentFrame.viewObservation =null;
		 JOptionPane.showMessageDialog(null,"Data Inserted Successfully.");
		 dataFacade.setObservationTypeAlertsForUser(user);
		 dataFacade.checkForActiveAlerts(user);
 	    if(user.isAlertExits()) {
 	    	parentFrame.getjButton4().setText("");
 	    	parentFrame.getjButton4().setText("View My Alerts*("+ user.getAlertCount()+")");
 	    }
	} else {
	 JOptionPane.showMessageDialog(null,"Error in Inserting Data");
	}
	}                                        

    /**
     * J combo box1 action performed.
     *
     * @param evt the evt
     */
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {                                           
    	 javax.swing.JComboBox cb = (javax.swing.JComboBox)evt.getSource();
         String name = (String)cb.getSelectedItem();
         System.out.println(name);
         jLabel2.setVisible(false);
         jLabel3.setVisible(false);
         jLabel4.setVisible(false);
         jTextField1.setVisible(false);
         jTextField2.setVisible(false);
         jTextField3.setVisible(false);
         
         if(obsAttribute.get(name) != null) {
        	  String[] key = obsAttribute.get(name).keySet().toArray(new String[obsAttribute.get(name).keySet().size()]);
             if (key != null && key.length>0) {
        		 jLabel2.setText("Enter " + key[0]);
        		 jTextField1.setText(key[0]);
        		 jLabel2.setVisible(true);
        		 jTextField1.setVisible(true);
        	 }
        	 if (key != null && key.length>1) {
        		 jLabel3.setText("Enter " + key[1]);
        		 jTextField2.setText(key[1]);
        		 jLabel3.setVisible(true);
        		 jTextField2.setVisible(true);
        	 }
        	 if (key != null && key.length>2) {
        		 jLabel4.setText("Enter " + key[2]);
        		 jTextField3.setText(key[2]);
        		 jLabel4.setVisible(true);
        		 jTextField3.setVisible(true);
        	 }
        	 
         } else 
         {
         Map<String,String> attributeList = dataFacade.getAttributeListForType(user.getObservationTypesForUser().get(name));
         if (attributeList != null && !attributeList.isEmpty()) {
        	 obsAttribute.put(name,attributeList);
             String[] key = attributeList.keySet().toArray(new String[attributeList.keySet().size()]);
             if (key != null && key.length>0) {
        		 jLabel2.setText("Enter " + key[0]);
        		 jTextField1.setText(key[0]);
        		 jLabel2.setVisible(true);
        		 jTextField1.setVisible(true);
        	 }
        	 if (key != null && key.length>1) {
        		 jLabel3.setText("Enter " + key[1]);
        		 jTextField2.setText(key[1]);
        		 jLabel3.setVisible(true);
        		 jTextField2.setVisible(true);
        	 }
        	 if (key != null && key.length>2) {
        		 jLabel4.setText("Enter " + key[2]);
        		 jTextField3.setText(key[2]);
        		 jLabel4.setVisible(true);
        		 jTextField3.setVisible(true);
        	 }
	         }
         }
    }                                          

  
    /**
     * Back button clicked.
     *
     * @param evt the evt
     */
    private void backButtonClicked(java.awt.event.ActionEvent evt) {                                         
     this.setVisible(false);
     parentFrame.setVisible(true);
    }                                        

    
    /**
     * J text field1 focus gained.
     *
     * @param evt the evt
     */
    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {                                        
         javax.swing.JTextField tf = (javax.swing.JTextField)evt.getSource();
         tf.setText("");
    }                                       

    /**
     * J text field2 focus gained.
     *
     * @param evt the evt
     */
    private void jTextField2FocusGained(java.awt.event.FocusEvent evt) {                                        
   	 javax.swing.JTextField tf = (javax.swing.JTextField)evt.getSource();
        tf.setText("");
    }                                       

    /**
     * J text field3 focus gained.
     *
     * @param evt the evt
     */
    private void jTextField3FocusGained(java.awt.event.FocusEvent evt) {                                        
    	 javax.swing.JTextField tf = (javax.swing.JTextField)evt.getSource();
         tf.setText("");
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
            java.util.logging.Logger.getLogger(PatientEnterAnObservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PatientEnterAnObservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PatientEnterAnObservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PatientEnterAnObservation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PatientEnterAnObservation(null,null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    /** The j button1. */
    private javax.swing.JButton jButton1;
    
    /** The j button2. */
    private javax.swing.JButton jButton2;
    
    /** The j combo box1. */
    private javax.swing.JComboBox<String> jComboBox1;
    
    /** The j label1. */
    private javax.swing.JLabel jLabel1;
    
    /** The j label2. */
    private javax.swing.JLabel jLabel2;
    
    /** The j label3. */
    private javax.swing.JLabel jLabel3;
    
    /** The j label4. */
    private javax.swing.JLabel jLabel4;
    
    /** The j label5. */
    private javax.swing.JLabel jLabel5;
    
    /** The j label7. */
    private javax.swing.JLabel jLabel7;
    
    /** The j panel1. */
    private javax.swing.JPanel jPanel1;
    
    /** The j text field1. */
    private javax.swing.JTextField jTextField1;
    
    /** The j text field2. */
    private javax.swing.JTextField jTextField2;
    
    /** The j text field3. */
    private javax.swing.JTextField jTextField3;
    
    /** The j spinner1. */
    private javax.swing.JSpinner jSpinner1;
  
    // End of variables declaration                   
}
