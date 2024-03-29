package collector.gui;

import collector.pojo.AuthorityCredential;
import collector.pojo.OperatorCredential;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class PrintReportUserEnd extends javax.swing.JFrame {

    OperatorCredential operator;
    AuthorityCredential authority;

    String name, CompId, subject, matter;

    public PrintReportUserEnd() {
        initComponents();
        super.setLocationRelativeTo(null);
        setTitle("JanSeva Report Print UserEnd");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/man.png"));
        setIconImage(icon1.getImage());
        setVisible(true);

    }

    public PrintReportUserEnd(String name, String complaintId, String subject, String matter, AuthorityCredential authority, OperatorCredential operator) {
        this();
        this.authority = authority;
        this.operator = operator;
        this.name = name;
        this.subject = subject;
        this.matter = matter;
        System.out.println(name + "" + subject);
        lblName.setText(name);
        lblCompId.setText(complaintId);
        lblSubject.setText(subject);
        jtxtAreaMatter.setText(matter);
        lblText.setText(operator.getOperatorName());
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String str = sdf.format(today);
        lblDate.setText(str);
        lblOfficeName.setText(authority.getDepartmentName());
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
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        btnPrint2 = new javax.swing.JButton();
        btnBack2 = new javax.swing.JButton();
        JPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLablename = new javax.swing.JLabel();
        jLablename1 = new javax.swing.JLabel();
        jLablename2 = new javax.swing.JLabel();
        jLablename3 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblCompId = new javax.swing.JLabel();
        lblSubject = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtAreaMatter = new javax.swing.JTextArea();
        jLablename4 = new javax.swing.JLabel();
        lblOfficeName = new javax.swing.JLabel();
        lblOfficeName1 = new javax.swing.JLabel();
        lblText = new javax.swing.JLabel();
        lblDate = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 607, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        btnPrint2.setBackground(new java.awt.Color(255, 255, 255));
        btnPrint2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print.png"))); // NOI18N
        btnPrint2.setText("Print");
        btnPrint2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });

        btnBack2.setBackground(new java.awt.Color(255, 255, 255));
        btnBack2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back1.png"))); // NOI18N
        btnBack2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(275, 275, 275)
                .addComponent(btnPrint2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBack2)
                    .addComponent(btnPrint2))
                .addContainerGap())
        );

        JPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Jan Seva");

        jLablename.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLablename.setText("Name");

        jLablename1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLablename1.setText("Complaint Id");

        jLablename2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLablename2.setText("Subject");

        jLablename3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLablename3.setText("Matter");

        lblName.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblName.setText("Name");

        lblCompId.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblCompId.setText("id");

        lblSubject.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblSubject.setText("sub");

        jtxtAreaMatter.setEditable(false);
        jtxtAreaMatter.setColumns(20);
        jtxtAreaMatter.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jtxtAreaMatter.setRows(5);
        jScrollPane1.setViewportView(jtxtAreaMatter);

        jLablename4.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLablename4.setText("Registered at");

        lblOfficeName.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblOfficeName.setText("office");

        lblOfficeName1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblOfficeName1.setText("By");

        lblText.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        lblText.setText("Name");

        lblDate.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        lblDate.setText("Date");

        javax.swing.GroupLayout JPanel1Layout = new javax.swing.GroupLayout(JPanel1);
        JPanel1.setLayout(JPanel1Layout);
        JPanel1Layout.setHorizontalGroup(
            JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLablename4)
                    .addComponent(jLablename3)
                    .addComponent(jLablename2)
                    .addComponent(jLablename)
                    .addComponent(jLablename1))
                .addGap(18, 18, 18)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanel1Layout.createSequentialGroup()
                        .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(JPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel4))
                            .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblCompId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblSubject, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(28, 28, 28)
                        .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(JPanel1Layout.createSequentialGroup()
                        .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JPanel1Layout.createSequentialGroup()
                                .addComponent(lblOfficeName, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblOfficeName1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblText, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 44, Short.MAX_VALUE)))
                .addContainerGap())
        );
        JPanel1Layout.setVerticalGroup(
            JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel1Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(27, 27, 27)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLablename)
                    .addComponent(lblName)
                    .addComponent(lblDate))
                .addGap(18, 18, 18)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLablename1)
                    .addComponent(lblCompId))
                .addGap(18, 18, 18)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLablename2)
                    .addComponent(lblSubject))
                .addGap(18, 18, 18)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLablename3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(JPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLablename4)
                    .addComponent(lblOfficeName)
                    .addComponent(lblOfficeName1)
                    .addComponent(lblText))
                .addGap(66, 66, 66))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(JPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
        Toolkit tkp = JPanel1.getToolkit();
        PrintJob pjp = tkp.getPrintJob(this, null, null);
        Graphics g = pjp.getGraphics();
        JPanel1.print(g);
        g.dispose();
        pjp.end();
    }//GEN-LAST:event_btnPrintActionPerformed

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
            java.util.logging.Logger.getLogger(PrintReportUserEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrintReportUserEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrintReportUserEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrintReportUserEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrintReportUserEnd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel1;
    private javax.swing.JButton btnBack2;
    private javax.swing.JButton btnPrint2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLablename;
    private javax.swing.JLabel jLablename1;
    private javax.swing.JLabel jLablename2;
    private javax.swing.JLabel jLablename3;
    private javax.swing.JLabel jLablename4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtxtAreaMatter;
    private javax.swing.JLabel lblCompId;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblOfficeName;
    private javax.swing.JLabel lblOfficeName1;
    private javax.swing.JLabel lblSubject;
    private javax.swing.JLabel lblText;
    // End of variables declaration//GEN-END:variables
}
