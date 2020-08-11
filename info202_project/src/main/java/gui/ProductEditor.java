/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.ProductJdbcDAO;
import domain.Product;
import helpers.SimpleListModel;
import java.math.BigDecimal;
import java.util.Collection;
import javax.swing.JOptionPane;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.exception.ConstraintsViolatedException;

/**
 *
 * @author geaja121
 */
public class ProductEditor extends javax.swing.JDialog {

	ProductJdbcDAO pDAO = new ProductJdbcDAO();
	SimpleListModel productsModel = new SimpleListModel();

	/**
	 * Creates new form ProductEditor
	 */
	public ProductEditor(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		cmboCategory.setEditable(true);
		Collection<String> categories = pDAO.getCategories();
		productsModel.updateItems(categories);
		cmboCategory.setModel(productsModel);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jLabel1 = new javax.swing.JLabel();
      txtId = new javax.swing.JTextField();
      jLabel2 = new javax.swing.JLabel();
      txtName = new javax.swing.JTextField();
      jLabel3 = new javax.swing.JLabel();
      jScrollPane1 = new javax.swing.JScrollPane();
      txtDescription = new javax.swing.JTextArea();
      jLabel4 = new javax.swing.JLabel();
      jLabel5 = new javax.swing.JLabel();
      jLabel6 = new javax.swing.JLabel();
      txtPrice = new javax.swing.JTextField();
      txtQuantity = new javax.swing.JTextField();
      btnSave = new javax.swing.JButton();
      btnExit = new javax.swing.JButton();
      cmboCategory = new javax.swing.JComboBox<>();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

      jLabel1.setText("ID:");
      jLabel1.setName("jLabel1"); // NOI18N

      txtId.setName("txtId"); // NOI18N
      txtId.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtIdActionPerformed(evt);
         }
      });

      jLabel2.setText("Name:");
      jLabel2.setName("jLabel2"); // NOI18N

      txtName.setName("txtName"); // NOI18N
      txtName.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            txtNameActionPerformed(evt);
         }
      });

      jLabel3.setText("Description:");
      jLabel3.setName("jLabel3"); // NOI18N

      jScrollPane1.setName("jScrollPane1"); // NOI18N

      txtDescription.setColumns(20);
      txtDescription.setRows(5);
      txtDescription.setName("txtDescription"); // NOI18N
      jScrollPane1.setViewportView(txtDescription);

      jLabel4.setText("Category:");
      jLabel4.setName("jLabel4"); // NOI18N

      jLabel5.setText("Price:");
      jLabel5.setName("jLabel5"); // NOI18N

      jLabel6.setText("Quantity in Stock:");
      jLabel6.setName("jLabel6"); // NOI18N

      txtPrice.setName("txtPrice"); // NOI18N

      txtQuantity.setName("txtQuantity"); // NOI18N

      btnSave.setText("Save");
      btnSave.setName("btnSave"); // NOI18N
      btnSave.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnSaveActionPerformed(evt);
         }
      });

      btnExit.setText("Cancel");
      btnExit.setName("btnExit"); // NOI18N
      btnExit.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnExitActionPerformed(evt);
         }
      });

      cmboCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
      cmboCategory.setName("cmboCategory"); // NOI18N
      cmboCategory.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            cmboCategoryActionPerformed(evt);
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
                  .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(txtPrice)
                     .addComponent(txtName)
                     .addComponent(txtQuantity)
                     .addComponent(jScrollPane1)
                     .addComponent(txtId)
                     .addComponent(cmboCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel1)
               .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel2)
               .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jLabel3)
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
            .addGap(10, 10, 10)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel4)
               .addComponent(cmboCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel5)
               .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jLabel6)
               .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(7, 7, 7)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(btnSave)
               .addComponent(btnExit))
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
		// TODO add your handling code here:
   }//GEN-LAST:event_txtIdActionPerformed

   private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
		// TODO add your handling code here:
		String name;
		String id;
		String description;
		String category;
		BigDecimal price;
		BigDecimal quantity;

		try {
			id = txtId.getText();
			name = txtName.getText();
			description = txtDescription.getText();
			category = (String) cmboCategory.getSelectedItem();
			price = new BigDecimal(txtPrice.getText());
			quantity = new BigDecimal(txtQuantity.getText());

			Product p = new Product();
			p.setProductID(id);
			p.setName(name);
			p.setDescription(description);
			p.setCategory(category);
			p.setListPrice(price);
			p.setQuantityInStock(quantity);

			new Validator().assertValid(p);
			pDAO.saveProduct(p);
			System.out.println(p);
			dispose();

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
					  "You have entered a price or quantity that is not a valid number.",
					  "Input Error", JOptionPane.ERROR_MESSAGE);
		} catch (ConstraintsViolatedException ex) {

			// get the violated constraints from the exception
			ConstraintViolation[] violations = ex.getConstraintViolations();

			// create a nice error message for the user
			String msg = "Please fix the following input problems:";
			for (ConstraintViolation cv : violations) {
				msg += "\n  - " + cv.getMessage();
			}

			// display the message to the user
			JOptionPane.showMessageDialog(this, msg, "Input Error", JOptionPane.ERROR_MESSAGE);
		}


   }//GEN-LAST:event_btnSaveActionPerformed

   private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_txtNameActionPerformed

   private void cmboCategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboCategoryActionPerformed
      // TODO add your handling code here:
   }//GEN-LAST:event_cmboCategoryActionPerformed

   private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
      // TODO add your handling code here:
		dispose();
   }//GEN-LAST:event_btnExitActionPerformed

	/**
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
			java.util.logging.Logger.getLogger(ProductEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ProductEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ProductEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ProductEditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				ProductEditor dialog = new ProductEditor(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnExit;
   private javax.swing.JButton btnSave;
   private javax.swing.JComboBox<String> cmboCategory;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel jLabel4;
   private javax.swing.JLabel jLabel5;
   private javax.swing.JLabel jLabel6;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JTextArea txtDescription;
   private javax.swing.JTextField txtId;
   private javax.swing.JTextField txtName;
   private javax.swing.JTextField txtPrice;
   private javax.swing.JTextField txtQuantity;
   // End of variables declaration//GEN-END:variables
}
