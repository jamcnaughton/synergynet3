/*
 * Copyright (c) 2009 University of Durham, England
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'SynergySpace' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package multiplicity3.config.position;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PositionConfigPanel extends JPanel {

	private static final long serialVersionUID = 1959347964564852506L;
	private PositionConfigPrefsItem prefsItem;

    private JCheckBox cbEnableNormalPositionMode = new JCheckBox();
    private JCheckBox cbEnableDeveloperMode = new JCheckBox();
	private JLabel jLabelTableHeight = new JLabel();
	private JLabel jLabelTablePosition = new JLabel();
	private JLabel jLabelTableOrientation = new JLabel();	
	private JLabel jLabelX = new JLabel();
	private JLabel jLabelY = new JLabel();
	private JTextField jTextFieldPositionX = new JTextField();
	private JTextField jTextFieldPositionY = new JTextField();
	private JTextField jTextFieldTableAngle = new JTextField();
	private JTextField jTextFieldTableHeight = new JTextField();
	private JLabel jLabelTableDistances = new JLabel();
	private JLabel jLabelXDistance = new JLabel();
	private JLabel jLabelYDistance = new JLabel();
	private JTextField jTextFieldDistanceX = new JTextField();
	private JTextField jTextFieldDistanceY = new JTextField();
    private JCheckBox horizontal = new JCheckBox();
    private JCheckBox vertical = new JCheckBox();
	private JLabel jLabelTableLimits = new JLabel();
	private JLabel jLabelXLimit = new JLabel();
	private JLabel jLabelYLimit = new JLabel();
	private JTextField jTextFieldLimitX = new JTextField();
	private JTextField jTextFieldLimitY = new JTextField();
	private JLabel jLabelPlacement = new JLabel();



    public PositionConfigPanel(PositionConfigPrefsItem positionConfigPrefsItem) {
    	this.prefsItem = positionConfigPrefsItem;
        initComponents();
    }

    private void initComponents() {

	    setLayout(null);    
	    
	    jLabelTableOrientation.setText("Orientation (degrees) = ");

	    jTextFieldTableAngle.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				prefsItem.setAngle(getFloatFromTextField(jTextFieldTableAngle, prefsItem.getAngle()));
			}
		});
	    
	    jLabelTableHeight.setText("Table Height (m) =");

	    jTextFieldTableHeight.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				prefsItem.setTableHeight(getFloatFromTextField(jTextFieldTableHeight, prefsItem.getTableHeight()));
			}
		});
	    
        cbEnableNormalPositionMode.setText("Enable User Defined Table Positioning");
        cbEnableNormalPositionMode.setSelected(!prefsItem.getDeveloperMode());
        cbEnableNormalPositionMode.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        cbEnableNormalPositionMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if (cbEnableNormalPositionMode.isSelected()){
                	cbEnableDeveloperMode.setSelected(!cbEnableNormalPositionMode.isSelected());
                	enableDeveloperMode(evt);
            	}else{
            		cbEnableNormalPositionMode.setSelected(true);
            	}
            }
        });

	    jLabelTablePosition.setText("Position (m):");

	    jLabelX.setText(" X = ");

	    jTextFieldPositionX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				prefsItem.setXPos(getFloatFromTextField(jTextFieldPositionX, prefsItem.getXPos()));
			}
		});

	    jLabelY.setText(" Y = ");

	    jTextFieldPositionY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				prefsItem.setYPos(getFloatFromTextField(jTextFieldPositionY, prefsItem.getYPos()));
			}
		});

        cbEnableDeveloperMode.setText("Enable Developer Mode");
        cbEnableDeveloperMode.setSelected(prefsItem.getDeveloperMode());
        cbEnableDeveloperMode.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        cbEnableDeveloperMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if (cbEnableDeveloperMode.isSelected()){
	            	cbEnableNormalPositionMode.setSelected(!cbEnableNormalPositionMode.isSelected());
	                enableDeveloperMode(evt);
            	}else{
            		cbEnableDeveloperMode.setSelected(true);
            	}
            }
        });

        jLabelTableDistances.setText("Distances between displays (m):");

        jLabelXDistance.setText(" X = ");

        jTextFieldDistanceX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				prefsItem.setXDistance(getFloatFromTextField(jTextFieldDistanceX, prefsItem.getGridDistanceX()));
			}
		});

	    jLabelYDistance.setText(" Y = ");

	    jTextFieldDistanceY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				prefsItem.setYDistance(getFloatFromTextField(jTextFieldDistanceY, prefsItem.getGridDistanceY()));
			}
		});

	    jLabelTableLimits.setText("Grid Limit (0 = no limit):");

	    jLabelXLimit.setText(" X = ");

	    jTextFieldLimitX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				prefsItem.setXLimit(getIntegerFromTextField(jTextFieldLimitX, prefsItem.getGridLimitX()));
			}
		});

	    jLabelYLimit.setText(" Y = ");

	    jTextFieldLimitY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				prefsItem.setYLimit(getIntegerFromTextField(jTextFieldLimitY, prefsItem.getGridLimitY()));
			}
		});

	    jLabelPlacement.setText("Placement:");

	    horizontal.setText("Horizontal");
	    horizontal.setSelected(prefsItem.getHorizontalPlacement());
	    horizontal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if (horizontal.isSelected()){
	            	vertical.setSelected(!horizontal.isSelected());
	            	enableHorizontalPlacement(evt);
            	}else{
            		horizontal.setSelected(true);
            	}
            }
        });

	    vertical.setText("Vertical");
	    vertical.setSelected(!prefsItem.getHorizontalPlacement());

	    vertical.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	if (horizontal.isSelected()){
	            	horizontal.setSelected(!vertical.isSelected());
	            	enableHorizontalPlacement(evt);
            	}else{
            		vertical.setSelected(false);
            	}
            }
        });
	    
	    jLabelTableOrientation.setBounds(new Rectangle(30, 30, 175, 24));
	    jTextFieldTableAngle.setBounds(new Rectangle(205, 30, 50, 24));
	    jLabelTableHeight.setBounds(new Rectangle(290, 30, 150, 24));
	    jTextFieldTableHeight.setBounds(new Rectangle(440, 30, 50, 24));

        cbEnableNormalPositionMode.setBounds(new Rectangle(30, 90, 300, 24));
        jLabelTablePosition.setBounds(new Rectangle(70, 120, 133, 24));
	    jLabelX.setBounds(new Rectangle(290, 120, 390, 24));	   
	    jTextFieldPositionX.setBounds(new Rectangle(325, 120, 57, 24));
	    jLabelY.setBounds(new Rectangle(440, 120, 39, 24));
	    jTextFieldPositionY.setBounds(new Rectangle(475, 120, 57, 24));
	    
        cbEnableDeveloperMode.setBounds(new Rectangle(30, 170, 300, 24));
	    jLabelTableDistances.setBounds(new Rectangle(70, 200, 210, 24));
	    jLabelXDistance.setBounds(new Rectangle(290, 200, 39, 24));
	    jTextFieldDistanceX.setBounds(new Rectangle(325, 200, 57, 24));
	    jLabelYDistance.setBounds(new Rectangle(440, 200, 39, 24));
	    jTextFieldDistanceY.setBounds(new Rectangle(475, 200, 57, 24));
	    jLabelTableLimits.setBounds(new Rectangle(70, 230, 200, 24));
	    jLabelXLimit.setBounds(new Rectangle(290, 230, 39, 24));
	    jTextFieldLimitX.setBounds(new Rectangle(325, 230, 57, 24));
	    jLabelYLimit.setBounds(new Rectangle(440, 230, 39, 24));
	    jTextFieldLimitY.setBounds(new Rectangle(475, 230, 57, 24));
	    jLabelPlacement.setBounds(new Rectangle(70, 260, 210, 24));
	    horizontal.setBounds(new Rectangle(290, 260, 150, 24));
	    vertical.setBounds(new Rectangle(440, 260, 155, 24));

	    add(jLabelTableOrientation, null);
	    add(jTextFieldTableAngle, null);
	    add(jLabelTableHeight, null);
	    add(jTextFieldTableHeight, null);
	    add(cbEnableNormalPositionMode, null);
	    add(jLabelTablePosition, null);
	    add(jLabelX, null);
	    add(jTextFieldPositionX, null);
	    add(jLabelY, null);
	    add(jTextFieldPositionY, null);
	    add(cbEnableDeveloperMode, null);
	    add(jLabelTableDistances, null);
	    add(jLabelXDistance, null);
	    add(jTextFieldDistanceX, null);
	    add(jLabelYDistance, null);
	    add(jTextFieldDistanceY, null);
	    add(jLabelTableLimits, null);
	    add(jLabelXLimit, null);
	    add(jTextFieldLimitX, null);
	    add(jLabelYLimit, null);
	    add(jTextFieldLimitY, null);
	    add(jLabelPlacement, null);
	    add(horizontal,null);
	    add(vertical,null);
	    loadPreferences();
    }

	private int getIntegerFromTextField(JTextField tf, int previousValue) {
		if(tf.getText().length() > 0) {
			try {
				int num = Integer.parseInt(tf.getText());
				tf.setForeground(Color.black);
				return num;
			}catch(NumberFormatException ex) {
				tf.setForeground(Color.red);
			}    
		}
		return previousValue;
	}
	
	private Float getFloatFromTextField(JTextField tf, float previousValue) {
		if(tf.getText().length() > 0) {
			try {
				float num = Float.parseFloat(tf.getText());
				tf.setForeground(Color.black);
				return num;
			}catch(NumberFormatException ex) {
				tf.setForeground(Color.red);
			}    
		}
		return previousValue;
	}
	private void loadPreferences(){
		jTextFieldTableAngle.setText("" + prefsItem.getAngle());
		jTextFieldTableHeight.setText("" + prefsItem.getTableHeight());
		jTextFieldPositionX.setText("" + prefsItem.getXPos());
		jTextFieldPositionY.setText("" + prefsItem.getYPos());
		jTextFieldDistanceX.setText("" + prefsItem.getGridDistanceX());
		jTextFieldDistanceY.setText("" + prefsItem.getGridDistanceY());
		jTextFieldLimitX.setText("" + prefsItem.getGridLimitX());
		jTextFieldLimitY.setText("" + prefsItem.getGridLimitY());
	}

	private void enableDeveloperMode(ActionEvent evt) {
		prefsItem.setDeveloperMode(cbEnableDeveloperMode.isSelected());
	}

	private void enableHorizontalPlacement(ActionEvent evt) {
		prefsItem.setHorizontalPlacement(horizontal.isSelected());
	}
}
