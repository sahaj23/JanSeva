package collector.gui;

import collector.dao.MailStatusDAO;
import collector.pojo.AuthorityCredential;
import collector.pojo.OperatorCredential;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class AllMailAuthorityEnd extends javax.swing.JFrame {
    
    private OperatorCredential solver;
    private AuthorityCredential departmentCredential;
    JLabel[] complaintId, complaintSubject;
    JButton[] view;
    int totalMails, start, end;
    Message[] messages;
    
    public AllMailAuthorityEnd() {
        initComponents();
        super.setLocationRelativeTo(null);
        setTitle("JanSeva Authority End All Mail");
//      add icon
      ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/man.png"));
      setIconImage(icon1.getImage());
        setVisible(true);
    }

    public AllMailAuthorityEnd(OperatorCredential solver, AuthorityCredential departmentCredentials) throws MessagingException, SQLException {
        this();
        this.solver = solver;
        this.departmentCredential = departmentCredentials;
        lblUser.setText("Welcome " + solver.getOperatorName() + ", Office Of: " + departmentCredentials.getDepartmentName());
        getMessageCount(departmentCredential.getConfidentialEmail(), departmentCredential.getPassword());
        end = totalMails;
        if (end - 9 >= 1) {
            start = end - 9;
        } else {
            start = 1;
        }
        complaintId = new JLabel[10];
        complaintSubject = new JLabel[10];
        view = new JButton[10];
        assign();
        if(totalMails <= 0) {
            noMessageFound();
        }
        recieveEmail(departmentCredential.getConfidentialEmail(), departmentCredential.getPassword(), "new");
        draw();
        btnBack.setVisible(false);
    }
    
    public void assign() {
        complaintId[0] = lblId1;
        complaintId[1] = lblId2;
        complaintId[2] = lblId3;
        complaintId[3] = lblId4;
        complaintId[4] = lblId5;
        complaintId[5] = lblId6;
        complaintId[6] = lblId7;
        complaintId[7] = lblId8;
        complaintId[8] = lblId9;
        complaintId[9] = lblId10;

        complaintSubject[0] = lblSub1;
        complaintSubject[1] = lblSub2;
        complaintSubject[2] = lblSub3;
        complaintSubject[3] = lblSub4;
        complaintSubject[4] = lblSub5;
        complaintSubject[5] = lblSub6;
        complaintSubject[6] = lblSub7;
        complaintSubject[7] = lblSub8;
        complaintSubject[8] = lblSub9;
        complaintSubject[9] = lblSub10;

        view[0] = btnView1;
        view[1] = btnView2;
        view[2] = btnView3;
        view[3] = btnView4;
        view[4] = btnView5;
        view[5] = btnView6;
        view[6] = btnView7;
        view[7] = btnView8;
        view[8] = btnView9;
        view[9] = btnView10;
    }
    
    public void draw() throws MessagingException, SQLException {
        if (messages.length >= 10) {
            for (int i = 0; i < 10; i++) {
                Message m = messages[i];
                String header = m.getSubject();
                String id = header.split("~")[0];
                int cid = Integer.parseInt(id);
                if (MailStatusDAO.checkEntry(cid)) {
                    int status = MailStatusDAO.getAuthorityStatus(cid,departmentCredential.getDepartmentName());
                    if (status == 1) {
                        complaintId[i].setBorder(BorderFactory.createLineBorder(Color.black));
                        complaintSubject[i].setBorder(BorderFactory.createLineBorder(Color.black));
                    } else if (status == 2) {
                        complaintId[i].setBorder(BorderFactory.createLineBorder(Color.red));
                        complaintSubject[i].setBorder(BorderFactory.createLineBorder(Color.red));
                    }
                } 
                complaintId[i].setText(id);
                complaintSubject[i].setText(header.split("~")[1]);
            }
        } else {
            int i;
            for (i = 0; i < messages.length; i++) {
                Message m = messages[i];
                String header = m.getSubject();
                String[] arr = header.split("~");
                String id = arr[0];
                int cid = Integer.parseInt(id);
                System.out.println(departmentCredential.getDepartmentName());
                int status = MailStatusDAO.getAuthorityStatus(cid,departmentCredential.getDepartmentName());
                if (status == 1) {
                    complaintId[i].setBorder(BorderFactory.createLineBorder(Color.black));
                } else if (status == 2) {
                    complaintId[i].setBorder(BorderFactory.createLineBorder(Color.red));
                }
                complaintId[i].setText(id);
                complaintSubject[i].setText(arr[1]);
            }
            for (; i < 10; i++) {
                complaintId[i].setVisible(false);
                complaintSubject[i].setVisible(false);
                view[i].setVisible(false);
            }
        }
    }
    
    public void clearBorder() {
        for (JLabel complaint : complaintId) {
            complaint.setBorder(null);
        }
        for (JLabel subject : complaintSubject) {
            subject.setBorder(null);
        }
    }
    
    public void visibleAll() {
        for (JLabel complaint : complaintId) {
            complaint.setVisible(true);
        }
        for (JLabel subject : complaintSubject) {
            subject.setVisible(true);
        }
        for (JButton view : view) {
            view.setVisible(true);
        }
    }
    
    public void recieveEmail(String username, String password, String direction) {

        try {
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            Session session = Session.getDefaultInstance(props);
            Store emailstore = session.getStore();
            emailstore.connect("imap.gmail.com", username, password);
            Folder emailfolder = emailstore.getFolder("INBOX");
            emailfolder.open(Folder.READ_ONLY);
            if (direction.equalsIgnoreCase("next")) {
                start = end + 1;
                end = start + 9;
                if (start <= totalMails) {
                    if (end <= totalMails) {
                        messages = emailfolder.getMessages(start, end);
                    } else {
                        messages = emailfolder.getMessages(start, totalMails);
                        end = totalMails;
                    }
                }
            } else if (direction.equalsIgnoreCase("back")) {
                end = start - 1;
                start = end - 9;
                if (end >= 1) {
                    if (start >= 1) {
                        messages = emailfolder.getMessages(start, end);
                    } else {
                        messages = emailfolder.getMessages(1, end);
                        start = 1;
                    }
                }
            } else if (direction.equalsIgnoreCase("new")) {
                messages = emailfolder.getMessages(start, end);
            }
            Collections.reverse(Arrays.asList(messages));
            
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void getMessageCount(String username, String password) {
        try {
            Properties props = new Properties();
            props.setProperty("mail.store.protocol", "imaps");
            Session session = Session.getDefaultInstance(props);
            Store emailstore = session.getStore();
            emailstore.connect("imap.gmail.com", username, password);
            Folder emailfolder = emailstore.getFolder("INBOX");
            emailfolder.open(Folder.READ_ONLY);
            totalMails = emailfolder.getMessageCount();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void noMessageFound() {
        for (JLabel complaint : complaintId) {
            complaint.setVisible(false);
        }
        for (JLabel subject : complaintSubject) {
            subject.setVisible(false);
        }
        for (JButton view : view) {
            view.setVisible(false);
        }
        this.add(new JLabel("NO COMPLAINTS FOUND AT CURRENT MOMENT.\nPLEASE CHECK AGAIN LATER"));
        return;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lblId1 = new javax.swing.JLabel();
        lblSub1 = new javax.swing.JLabel();
        btnView1 = new javax.swing.JButton();
        lblId2 = new javax.swing.JLabel();
        lblSub2 = new javax.swing.JLabel();
        btnView2 = new javax.swing.JButton();
        lblId3 = new javax.swing.JLabel();
        lblSub3 = new javax.swing.JLabel();
        btnView3 = new javax.swing.JButton();
        lblId4 = new javax.swing.JLabel();
        lblSub4 = new javax.swing.JLabel();
        btnView4 = new javax.swing.JButton();
        lblId5 = new javax.swing.JLabel();
        lblSub5 = new javax.swing.JLabel();
        btnView5 = new javax.swing.JButton();
        lblId6 = new javax.swing.JLabel();
        lblSub6 = new javax.swing.JLabel();
        btnView6 = new javax.swing.JButton();
        lblId7 = new javax.swing.JLabel();
        lblSub7 = new javax.swing.JLabel();
        btnView7 = new javax.swing.JButton();
        lblId8 = new javax.swing.JLabel();
        lblSub8 = new javax.swing.JLabel();
        btnView8 = new javax.swing.JButton();
        lblId9 = new javax.swing.JLabel();
        lblSub9 = new javax.swing.JLabel();
        btnView9 = new javax.swing.JButton();
        lblId10 = new javax.swing.JLabel();
        lblSub10 = new javax.swing.JLabel();
        btnView10 = new javax.swing.JButton();
        btnForward = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblUser = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblLogout = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("Jan Seva");

        lblId1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId1.setForeground(new java.awt.Color(255, 0, 0));
        lblId1.setText("jLabel1");

        lblSub1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSub1.setText("jLabel2");

        btnView1.setBackground(new java.awt.Color(255, 255, 255));
        btnView1.setText("View");
        btnView1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView1ActionPerformed(evt);
            }
        });

        lblId2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId2.setForeground(new java.awt.Color(255, 0, 0));
        lblId2.setText("jLabel1");

        lblSub2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSub2.setText("jLabel2");

        btnView2.setBackground(new java.awt.Color(255, 255, 255));
        btnView2.setText("View");
        btnView2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView2ActionPerformed(evt);
            }
        });

        lblId3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId3.setForeground(new java.awt.Color(255, 0, 0));
        lblId3.setText("jLabel1");

        lblSub3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSub3.setText("jLabel2");

        btnView3.setBackground(new java.awt.Color(255, 255, 255));
        btnView3.setText("View");
        btnView3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView3ActionPerformed(evt);
            }
        });

        lblId4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId4.setForeground(new java.awt.Color(255, 0, 0));
        lblId4.setText("jLabel1");

        lblSub4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSub4.setText("jLabel2");

        btnView4.setBackground(new java.awt.Color(255, 255, 255));
        btnView4.setText("View");
        btnView4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView4ActionPerformed(evt);
            }
        });

        lblId5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId5.setForeground(new java.awt.Color(255, 0, 0));
        lblId5.setText("jLabel1");

        lblSub5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSub5.setText("jLabel2");

        btnView5.setBackground(new java.awt.Color(255, 255, 255));
        btnView5.setText("View");
        btnView5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView5ActionPerformed(evt);
            }
        });

        lblId6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId6.setForeground(new java.awt.Color(255, 0, 0));
        lblId6.setText("jLabel1");

        lblSub6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSub6.setText("jLabel2");

        btnView6.setBackground(new java.awt.Color(255, 255, 255));
        btnView6.setText("View");
        btnView6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView6ActionPerformed(evt);
            }
        });

        lblId7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId7.setForeground(new java.awt.Color(255, 0, 0));
        lblId7.setText("jLabel1");

        lblSub7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSub7.setText("jLabel2");

        btnView7.setBackground(new java.awt.Color(255, 255, 255));
        btnView7.setText("View");
        btnView7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView7ActionPerformed(evt);
            }
        });

        lblId8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId8.setForeground(new java.awt.Color(255, 0, 0));
        lblId8.setText("jLabel1");

        lblSub8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSub8.setText("jLabel2");

        btnView8.setBackground(new java.awt.Color(255, 255, 255));
        btnView8.setText("View");
        btnView8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView8ActionPerformed(evt);
            }
        });

        lblId9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId9.setForeground(new java.awt.Color(255, 0, 0));
        lblId9.setText("jLabel1");

        lblSub9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSub9.setText("jLabel2");

        btnView9.setBackground(new java.awt.Color(255, 255, 255));
        btnView9.setText("View");
        btnView9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView9ActionPerformed(evt);
            }
        });

        lblId10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblId10.setForeground(new java.awt.Color(255, 0, 0));
        lblId10.setText("jLabel1");

        lblSub10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblSub10.setText("jLabel2");

        btnView10.setBackground(new java.awt.Color(255, 255, 255));
        btnView10.setText("View");
        btnView10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnView10ActionPerformed(evt);
            }
        });

        btnForward.setBackground(new java.awt.Color(255, 255, 255));
        btnForward.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/forward1.png"))); // NOI18N
        btnForward.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnForwardActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back1.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        lblUser.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUser.setText("jLabel1");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel5.setText("© JanSeva");

        lblLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout1.png"))); // NOI18N
        lblLogout.setText("Logout");
        lblLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLogoutMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblId10, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSub10, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblId9, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSub9, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblId8, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSub8, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblId7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSub7, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblId6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSub6, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblId5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSub5, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblId4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSub4, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblId3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSub3, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblId2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSub2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lblId1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(lblSub1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnView1)
                                    .addComponent(btnView2)
                                    .addComponent(btnView3)
                                    .addComponent(btnView4)
                                    .addComponent(btnView5)
                                    .addComponent(btnView6)
                                    .addComponent(btnView7)
                                    .addComponent(btnView8)
                                    .addComponent(btnView9)
                                    .addComponent(btnView10)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblLogout)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnForward, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 41, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBack))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblUser)
                                    .addComponent(jLabel5))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId1)
                            .addComponent(lblSub1)
                            .addComponent(btnView1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId2)
                            .addComponent(lblSub2)
                            .addComponent(btnView2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId3)
                            .addComponent(lblSub3)
                            .addComponent(btnView3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId4)
                            .addComponent(lblSub4)
                            .addComponent(btnView4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId5)
                            .addComponent(lblSub5)
                            .addComponent(btnView5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId6)
                            .addComponent(lblSub6)
                            .addComponent(btnView6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId7)
                            .addComponent(lblSub7)
                            .addComponent(btnView7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId8)
                            .addComponent(lblSub8)
                            .addComponent(btnView8))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId9)
                            .addComponent(lblSub9)
                            .addComponent(btnView9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblId10)
                            .addComponent(lblSub10)
                            .addComponent(btnView10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnForward, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblLogout, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnForwardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnForwardActionPerformed
        btnBack.setVisible(true);
        clearBorder();
        visibleAll();
        recieveEmail(departmentCredential.getConfidentialEmail(), departmentCredential.getPassword(), "back");
        try {
            draw();
        } catch (MessagingException ex) {
            Logger.getLogger(AllMailCollectorEnd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AllMailCollectorEnd.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (start <= 1) {
            btnForward.setVisible(false);
        }
    }//GEN-LAST:event_btnForwardActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        clearBorder();
        visibleAll();

        recieveEmail(departmentCredential.getConfidentialEmail(), departmentCredential.getPassword(), "next");
        try {
            draw();
        } catch (MessagingException ex) {
            Logger.getLogger(AllMailCollectorEnd.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AllMailCollectorEnd.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (end >= totalMails) {
            btnBack.setVisible(false);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnView1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView1ActionPerformed
        try {
            MailStatusDAO.setAuthorityStatus(Integer.parseInt(complaintId[0].getText().trim()),departmentCredential.getDepartmentName(),1);
            new AuthorityEndComplaintWindow(messages[0], this, solver, departmentCredential).setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Some Error Occured In Fetching Data\nPlease Try Again Later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnView1ActionPerformed

    private void btnView2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView2ActionPerformed
        try {
            MailStatusDAO.setAuthorityStatus(Integer.parseInt(complaintId[1].getText().trim()),departmentCredential.getDepartmentName(),1);
            new AuthorityEndComplaintWindow(messages[1], this, solver, departmentCredential).setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Some Error Occured In Fetching Data\nPlease Try Again Later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnView2ActionPerformed

    private void btnView3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView3ActionPerformed
        try {
            MailStatusDAO.setAuthorityStatus(Integer.parseInt(complaintId[2].getText().trim()),departmentCredential.getDepartmentName(),1);
            new AuthorityEndComplaintWindow(messages[2], this, solver, departmentCredential).setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Some Error Occured In Fetching Data\nPlease Try Again Later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnView3ActionPerformed

    private void btnView4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView4ActionPerformed
        try {
            MailStatusDAO.setAuthorityStatus(Integer.parseInt(complaintId[3].getText().trim()),departmentCredential.getDepartmentName(),1);
            new AuthorityEndComplaintWindow(messages[3], this, solver, departmentCredential).setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Some Error Occured In Fetching Data\nPlease Try Again Later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnView4ActionPerformed

    private void btnView5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView5ActionPerformed
        try {
            MailStatusDAO.setAuthorityStatus(Integer.parseInt(complaintId[4].getText().trim()),departmentCredential.getDepartmentName(),1);
            new AuthorityEndComplaintWindow(messages[4], this, solver, departmentCredential).setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Some Error Occured In Fetching Data\nPlease Try Again Later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnView5ActionPerformed

    private void btnView6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView6ActionPerformed
        try {
            MailStatusDAO.setAuthorityStatus(Integer.parseInt(complaintId[5].getText().trim()),departmentCredential.getDepartmentName(),1);
            new AuthorityEndComplaintWindow(messages[5], this, solver, departmentCredential).setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Some Error Occured In Fetching Data\nPlease Try Again Later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnView6ActionPerformed

    private void btnView7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView7ActionPerformed
        try {
            MailStatusDAO.setAuthorityStatus(Integer.parseInt(complaintId[6].getText().trim()),departmentCredential.getDepartmentName(),1);
            new AuthorityEndComplaintWindow(messages[6], this, solver, departmentCredential).setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Some Error Occured In Fetching Data\nPlease Try Again Later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnView7ActionPerformed

    private void btnView8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView8ActionPerformed
        try {
            MailStatusDAO.setAuthorityStatus(Integer.parseInt(complaintId[7].getText().trim()),departmentCredential.getDepartmentName(),1);
            new AuthorityEndComplaintWindow(messages[7], this, solver, departmentCredential).setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Some Error Occured In Fetching Data\nPlease Try Again Later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnView8ActionPerformed

    private void btnView9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView9ActionPerformed
        try {
            MailStatusDAO.setAuthorityStatus(Integer.parseInt(complaintId[8].getText().trim()),departmentCredential.getDepartmentName(),1);
            new AuthorityEndComplaintWindow(messages[8], this, solver, departmentCredential).setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Some Error Occured In Fetching Data\nPlease Try Again Later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnView9ActionPerformed

    private void btnView10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnView10ActionPerformed
        try {
            MailStatusDAO.setAuthorityStatus(Integer.parseInt(complaintId[9].getText().trim()),departmentCredential.getDepartmentName(),1);
            new AuthorityEndComplaintWindow(messages[9], this, solver, departmentCredential).setVisible(true);
            this.setVisible(false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Some Error Occured In Fetching Data\nPlease Try Again Later.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnView10ActionPerformed

    private void lblLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseClicked
        this.dispose();
        WelcomeWindowAuthority login = new WelcomeWindowAuthority();
        login.setVisible(true);
    }//GEN-LAST:event_lblLogoutMouseClicked

    private void lblLogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseEntered
        lblLogout.setForeground(Color.red);
        Font f = new Font("Tahoma", Font.BOLD, 11);
        lblLogout.setFont(f);
    }//GEN-LAST:event_lblLogoutMouseEntered

    private void lblLogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogoutMouseExited
        lblLogout.setForeground(new Color(0, 0, 0));
        Font f = new Font("Tahoma", Font.PLAIN, 11);
        lblLogout.setFont(f);
    }//GEN-LAST:event_lblLogoutMouseExited

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
            java.util.logging.Logger.getLogger(AllMailAuthorityEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllMailAuthorityEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllMailAuthorityEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllMailAuthorityEnd.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllMailAuthorityEnd().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnForward;
    private javax.swing.JButton btnView1;
    private javax.swing.JButton btnView10;
    private javax.swing.JButton btnView2;
    private javax.swing.JButton btnView3;
    private javax.swing.JButton btnView4;
    private javax.swing.JButton btnView5;
    private javax.swing.JButton btnView6;
    private javax.swing.JButton btnView7;
    private javax.swing.JButton btnView8;
    private javax.swing.JButton btnView9;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblId1;
    private javax.swing.JLabel lblId10;
    private javax.swing.JLabel lblId2;
    private javax.swing.JLabel lblId3;
    private javax.swing.JLabel lblId4;
    private javax.swing.JLabel lblId5;
    private javax.swing.JLabel lblId6;
    private javax.swing.JLabel lblId7;
    private javax.swing.JLabel lblId8;
    private javax.swing.JLabel lblId9;
    private javax.swing.JLabel lblLogout;
    private javax.swing.JLabel lblSub1;
    private javax.swing.JLabel lblSub10;
    private javax.swing.JLabel lblSub2;
    private javax.swing.JLabel lblSub3;
    private javax.swing.JLabel lblSub4;
    private javax.swing.JLabel lblSub5;
    private javax.swing.JLabel lblSub6;
    private javax.swing.JLabel lblSub7;
    private javax.swing.JLabel lblSub8;
    private javax.swing.JLabel lblSub9;
    private javax.swing.JLabel lblUser;
    // End of variables declaration//GEN-END:variables
}
