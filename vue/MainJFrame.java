/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import modele.dao.ConnexionBDD;
import modele.dao.DaoSalarie;
import modele.dao.DaoService;
import modele.metier.Salarie;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static java.lang.String.valueOf;

/**
 * @author Yanis Dubourg & Jules Tanguy
 */
public class MainJFrame extends javax.swing.JFrame {

    int indexSalarieSelectionne = 0;
    int indexServiceSelectionne = 0;
    Salarie salarieSelectionne;
    ArrayList<String> jListAffiche;
    int TailleJListAffiche;

    ArrayList<JTextField> textFields;
    static JFrame mainFrame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
/*        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Set the System look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        try {
//            // Set System L&F
//            UIManager.setLookAndFeel(
//                    UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
//            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Set the FlatLAF look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        mainFrame = new MainJFrame();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    /**
     * Creates new form JFrameListe
     */
    public MainJFrame() {
        try {
            ConnexionBDD.startConnexion();
            initComponents();
        } catch (SQLException throwables) {
            JOptionPane.showMessageDialog(null, "Échec de la connexion au serveur", "Erreur", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }


        jListAffiche = DaoSalarie.getAllSalarieNameByService(indexServiceSelectionne);

        textFields = new ArrayList<>() {
            {
                add(jTextFieldNom);
                add(jTextFieldPrenom);
                add(jTextFieldDateNaiss);
                add(jTextFieldDateEmbauche);
                add(jTextFieldFonction);
                add(jTextFieldTauxHoraire);
                add(jTextFieldSituationFamiliale);
                add(jTextFieldNbrEnfant);
                add(jTextFieldCategorie);
                add(jTextFieldService);
            }
        };

    }

    private void updateTextField() {
        TailleJListAffiche = DaoSalarie.getAllSalarieNameByService(indexServiceSelectionne).size();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if (TailleJListAffiche > 0 && TailleJListAffiche > indexSalarieSelectionne && indexSalarieSelectionne >= 0) {
            salarieSelectionne = DaoSalarie.getAllByService(indexServiceSelectionne).get(indexSalarieSelectionne);

            jTextFieldNom.setText(salarieSelectionne.getNom());
            jTextFieldPrenom.setText(salarieSelectionne.getPrenom());
            jTextFieldDateNaiss.setText(dateFormat.format(salarieSelectionne.getDateNaiss()));
            jTextFieldDateEmbauche.setText(dateFormat.format(salarieSelectionne.getDateEmbauche()));
            jTextFieldFonction.setText(salarieSelectionne.getFonction());
            jTextFieldTauxHoraire.setText(valueOf(salarieSelectionne.getTauxHoraire()));
            jTextFieldSituationFamiliale.setText(salarieSelectionne.getSituationFamiliale());
            jTextFieldNbrEnfant.setText(valueOf(salarieSelectionne.getNbrEnfants()));
            jTextFieldCategorie.setText(salarieSelectionne.getUneCategorie().getLibelle());
            jTextFieldService.setText(salarieSelectionne.getUnService().getDesignation());

        } else {

            textFields.forEach(unJtextField -> {
                unJtextField.setText(null);
            });

        }
    }

    private void updateTextFieldDialogModifier() {
        TailleJListAffiche = DaoSalarie.getAllSalarieNameByService(indexServiceSelectionne).size();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        if (TailleJListAffiche > 0 && TailleJListAffiche > indexSalarieSelectionne && indexSalarieSelectionne >= 0) {
            salarieSelectionne = DaoSalarie.getAllByService(indexServiceSelectionne).get(indexSalarieSelectionne);

            jTextFieldModifyNom.setText(salarieSelectionne.getNom());
            jTextFieldModifyPrenom.setText(salarieSelectionne.getPrenom());
//            jTextFieldDateNaiss.setText(dateFormat.format(salarieSelectionne.getDateNaiss()));
//            jTextFieldDateEmbauche.setText(dateFormat.format(salarieSelectionne.getDateEmbauche()));
            jTextFieldModifyFonction.setText(salarieSelectionne.getFonction());
            jTextFieldModifyTauxHoraire.setText(valueOf(salarieSelectionne.getTauxHoraire()));
            jTextFieldModifySitFamiliale.setText(salarieSelectionne.getSituationFamiliale());
            jTextFieldModifyNbrEnfant.setText(valueOf(salarieSelectionne.getNbrEnfants()));
//            jTextFieldCategorie.setText(salarieSelectionne.getUneCategorie().getLibelle());
//            jTextFieldService.setText(salarieSelectionne.getUnService().getDesignation());

        } else {

            textFields.forEach(unJtextField -> {
                unJtextField.setText(null);
            });

        }
    }

    private void updateListSalarie() {

        jListAffiche = DaoSalarie.getAllSalarieNameByService(indexServiceSelectionne);
        TailleJListAffiche = jListAffiche.size();

        jListSalarie.setModel(new javax.swing.AbstractListModel<String>() {
            ArrayList<String> strings = jListAffiche;

            @Override
            public int getSize() {
                return strings.size();
            }

            @Override
            public String getElementAt(int i) {
                return strings.get(i);
            }
        });
    }

    private void modifySalarie() {
        String nomPrenomSalarie = jListSalarie.getSelectedValue();

        if (nomPrenomSalarie == null) {
            JOptionPane.showConfirmDialog(this, "Aucun salarié n'est sélectionné", "Attention",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        } else {
            updateTextFieldDialogModifier();
            jDialogModifierSalarie.setLocationRelativeTo(mainFrame);
            jDialogModifierSalarie.setVisible(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogModifierSalarie = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldModifyNom = new javax.swing.JTextField();
        jTextFieldModifyPrenom = new javax.swing.JTextField();
        jTextFieldModifyFonction = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextFieldModifyTauxHoraire = new javax.swing.JTextField();
        jTextFieldModifyNbrEnfant = new javax.swing.JTextField();
        jTextFieldModifySitFamiliale = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBoxServices = new javax.swing.JComboBox<>();
        jScrollPaneListSalarie = new javax.swing.JScrollPane();
        jListSalarie = new javax.swing.JList<>();
        jLabelListSalarie = new javax.swing.JLabel();
        jLabelListService = new javax.swing.JLabel();
        jButtonCreerNouveauSalarie = new javax.swing.JButton();
        jPanelSalarie = new javax.swing.JPanel();
        jTextFieldFonction = new javax.swing.JTextField();
        jLabelTauxHoraire = new javax.swing.JLabel();
        jTextFieldSituationFamiliale = new javax.swing.JTextField();
        jTextFieldTauxHoraire = new javax.swing.JTextField();
        jLabelSituationFamiliale = new javax.swing.JLabel();
        jTextFieldService = new javax.swing.JTextField();
        jLabelNbrEnfant = new javax.swing.JLabel();
        jTextFieldNbrEnfant = new javax.swing.JTextField();
        jLabelService = new javax.swing.JLabel();
        jButtonModifier = new javax.swing.JButton();
        jLabelCategorie = new javax.swing.JLabel();
        jButtonSupprimer = new javax.swing.JButton();
        jLabelNom = new javax.swing.JLabel();
        jTextFieldCategorie = new javax.swing.JTextField();
        jTextFieldNom = new javax.swing.JTextField();
        jLabelPrenom = new javax.swing.JLabel();
        jTextFieldPrenom = new javax.swing.JTextField();
        jLabelNomDateNaiss = new javax.swing.JLabel();
        jTextFieldDateNaiss = new javax.swing.JTextField();
        jLabelDateEmbauche = new javax.swing.JLabel();
        jTextFieldDateEmbauche = new javax.swing.JTextField();
        jLabelFonction = new javax.swing.JLabel();
        jLabelSalarie = new javax.swing.JLabel();

        jDialogModifierSalarie.setTitle("Modifier un Salarié");
        jDialogModifierSalarie.setMinimumSize(null);
        jDialogModifierSalarie.setModal(true);
        jDialogModifierSalarie.setResizable(false);
        jDialogModifierSalarie.setSize(new java.awt.Dimension(394, 440));

        jLabel1.setText("Nom");

        jLabel2.setText("Prénom");

        jLabel3.setText("Date de Naissance");

        jLabel4.setText("Date d'embauche");

        jLabel5.setText("Taux Horaire");

        jLabel6.setText("Situation familiale");

        jLabel7.setText("Nombre d'enfant");

        jLabel8.setText("Fonction");

        jLabel9.setText("Service");

        jLabel10.setText("Catégorie");

        jTextFieldModifyNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldModifyNomActionPerformed(evt);
            }
        });

        jTextFieldModifyPrenom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldModifyPrenomActionPerformed(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jButton1.setText("Appliquer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Annuler");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogModifierSalarieLayout = new javax.swing.GroupLayout(jDialogModifierSalarie.getContentPane());
        jDialogModifierSalarie.getContentPane().setLayout(jDialogModifierSalarieLayout);
        jDialogModifierSalarieLayout.setHorizontalGroup(
                jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jDialogModifierSalarieLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton1)
                                        .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel3)
                                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel10)
                                                .addComponent(jTextFieldModifyNom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel8)
                                                .addComponent(jTextFieldModifySitFamiliale, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextFieldModifyFonction, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel7)
                                        .addComponent(jTextFieldModifyNbrEnfant, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldModifyTauxHoraire, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4)
                                        .addComponent(jTextFieldModifyPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButton2))
                                .addContainerGap(207, Short.MAX_VALUE))
        );
        jDialogModifierSalarieLayout.setVerticalGroup(
                jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogModifierSalarieLayout.createSequentialGroup()
                                .addContainerGap(82, Short.MAX_VALUE)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldModifyNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldModifyPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldModifyFonction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldModifyTauxHoraire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldModifyNbrEnfant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldModifySitFamiliale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jDialogModifierSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton2))
                                .addGap(78, 78, 78))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Infoware SIRH");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jComboBoxServices.setModel(new javax.swing.DefaultComboBoxModel<>(DaoService.getDesignationList()));
        jComboBoxServices.setToolTipText("Nom du service");
        jComboBoxServices.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jComboBoxServices.setVerifyInputWhenFocusTarget(false);
        jComboBoxServices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxServicesActionPerformed(evt);
            }
        });

        jListSalarie.setModel(new javax.swing.AbstractListModel<String>() {
            ArrayList<String> strings = DaoSalarie.getAllSalarieNameByService(indexServiceSelectionne);

            @Override
            public int getSize() {
                return strings.size();
            }

            @Override
            public String getElementAt(int i) {
                return strings.get(i);
            }
        });
        jListSalarie.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jListSalarie.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jListSalarieMouseWheelMoved(evt);
            }
        });
        jListSalarie.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jListSalarieMouseClicked(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                jListSalarieMousePressed(evt);
            }
        });
        jListSalarie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jListSalarieKeyPressed(evt);
            }
        });
        jScrollPaneListSalarie.setViewportView(jListSalarie);

        jLabelListSalarie.setText("Liste des salariés");

        jLabelListService.setText("Trier par service");

        jButtonCreerNouveauSalarie.setText("Créer un nouveau Salarié");
        jButtonCreerNouveauSalarie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreerNouveauSalarieActionPerformed(evt);
            }
        });

        jPanelSalarie.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTextFieldFonction.setEditable(false);
        jTextFieldFonction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFonctionActionPerformed(evt);
            }
        });

        jLabelTauxHoraire.setText("Taux Horaire");

        jTextFieldSituationFamiliale.setEditable(false);
        jTextFieldSituationFamiliale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSituationFamilialeActionPerformed(evt);
            }
        });

        jTextFieldTauxHoraire.setEditable(false);
        jTextFieldTauxHoraire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTauxHoraireActionPerformed(evt);
            }
        });

        jLabelSituationFamiliale.setText("Situation familiale");

        jTextFieldService.setEditable(false);
        jTextFieldService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldServiceActionPerformed(evt);
            }
        });

        jLabelNbrEnfant.setText("Nombre d'enfant");

        jTextFieldNbrEnfant.setEditable(false);
        jTextFieldNbrEnfant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNbrEnfantActionPerformed(evt);
            }
        });

        jLabelService.setText("Service");

        jButtonModifier.setText("Modifier");
        jButtonModifier.setRequestFocusEnabled(false);
        jButtonModifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierActionPerformed(evt);
            }
        });

        jLabelCategorie.setText("Catégorie");

        jButtonSupprimer.setText("Supprimer le salarié");
        jButtonSupprimer.setRequestFocusEnabled(false);
        jButtonSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerActionPerformed(evt);
            }
        });

        jLabelNom.setText("Nom");

        jTextFieldCategorie.setEditable(false);
        jTextFieldCategorie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCategorieActionPerformed(evt);
            }
        });

        jTextFieldNom.setEditable(false);
        jTextFieldNom.setAutoscrolls(false);
        jTextFieldNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomActionPerformed(evt);
            }
        });

        jLabelPrenom.setText("Prénom");

        jTextFieldPrenom.setEditable(false);

        jLabelNomDateNaiss.setText("Date de Naissance");

        jTextFieldDateNaiss.setEditable(false);

        jLabelDateEmbauche.setText("Date d'embauche");

        jTextFieldDateEmbauche.setEditable(false);
        jTextFieldDateEmbauche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDateEmbaucheActionPerformed(evt);
            }
        });

        jLabelFonction.setText("Fonction");

        jLabelSalarie.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabelSalarie.setText("Salarié");

        javax.swing.GroupLayout jPanelSalarieLayout = new javax.swing.GroupLayout(jPanelSalarie);
        jPanelSalarie.setLayout(jPanelSalarieLayout);
        jPanelSalarieLayout.setHorizontalGroup(
                jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelSalarieLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelNomDateNaiss)
                                        .addComponent(jTextFieldDateNaiss, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelFonction)
                                        .addComponent(jLabelSituationFamiliale)
                                        .addComponent(jLabelCategorie)
                                        .addComponent(jTextFieldCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldSituationFamiliale, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldFonction, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelNom))
                                .addGap(26, 26, 26)
                                .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelPrenom)
                                        .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabelNbrEnfant)
                                                .addComponent(jTextFieldService, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                                .addComponent(jLabelTauxHoraire)
                                                .addComponent(jLabelDateEmbauche)
                                                .addComponent(jTextFieldDateEmbauche)
                                                .addComponent(jTextFieldTauxHoraire)
                                                .addComponent(jTextFieldNbrEnfant)
                                                .addComponent(jLabelService)))
                                .addGap(32, 32, 32))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSalarieLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSalarieLayout.createSequentialGroup()
                                                .addComponent(jLabelSalarie)
                                                .addGap(158, 158, 158))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSalarieLayout.createSequentialGroup()
                                                .addComponent(jButtonModifier)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButtonSupprimer)
                                                .addGap(58, 58, 58))))
        );
        jPanelSalarieLayout.setVerticalGroup(
                jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelSalarieLayout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabelSalarie)
                                .addGap(26, 26, 26)
                                .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelPrenom)
                                        .addComponent(jLabelNom))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldPrenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanelSalarieLayout.createSequentialGroup()
                                                .addComponent(jLabelNomDateNaiss)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldDateNaiss, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelSalarieLayout.createSequentialGroup()
                                                .addComponent(jLabelDateEmbauche)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldDateEmbauche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanelSalarieLayout.createSequentialGroup()
                                                .addComponent(jLabelTauxHoraire)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldTauxHoraire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanelSalarieLayout.createSequentialGroup()
                                                .addComponent(jLabelFonction)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldFonction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelNbrEnfant)
                                        .addComponent(jLabelSituationFamiliale))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldSituationFamiliale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldNbrEnfant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabelService)
                                        .addComponent(jLabelCategorie))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldCategorie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26)
                                .addGroup(jPanelSalarieLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonModifier)
                                        .addComponent(jButtonSupprimer))
                                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabelListService)
                                                        .addComponent(jComboBoxServices, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButtonCreerNouveauSalarie, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPaneListSalarie, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabelListSalarie))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                                                .addComponent(jPanelSalarie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(57, 57, 57))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabelListService)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxServices, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelListSalarie)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPaneListSalarie, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCreerNouveauSalarie)
                                .addGap(37, 37, 37))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jPanelSalarie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButtonModifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierActionPerformed
        String nomPrenomSalarie = jListSalarie.getSelectedValue();

        if (nomPrenomSalarie == null) {
            JOptionPane.showConfirmDialog(this, "Aucun salarié n'est sélectionné", "Attention", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showConfirmDialog(this, "Feature pas encore terminée, désolé :(", "Attention", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
/*            updateTextFieldDialogModifier();
            jDialogModifierSalarie.setLocationRelativeTo(mainFrame);
            jDialogModifierSalarie.setVisible(true);*/
        }

    }//GEN-LAST:event_jButtonModifierActionPerformed

    private void jButtonSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerActionPerformed
        String nomPrenomSalarie = jListSalarie.getSelectedValue();

        if (nomPrenomSalarie == null) {

            JOptionPane.showConfirmDialog(this, "Aucun salarié n'est sélectionné", "Attention", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);

        } else {

            int confirmation = JOptionPane.showConfirmDialog(this,
                    "Voulez-vous vraiment supprimer le salarié " + salarieSelectionne.getNom() + " " + salarieSelectionne.getPrenom() + " ?",
                    "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (confirmation == 0) {

                DaoSalarie.deleteSalarie(salarieSelectionne);
                updateListSalarie();
                updateTextField();

            }

        }


    }//GEN-LAST:event_jButtonSupprimerActionPerformed

    private void jTextFieldNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomActionPerformed

    private void jTextFieldSituationFamilialeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSituationFamilialeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSituationFamilialeActionPerformed

    private void jTextFieldServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldServiceActionPerformed

    private void jTextFieldNbrEnfantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNbrEnfantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNbrEnfantActionPerformed

    private void jTextFieldCategorieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCategorieActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCategorieActionPerformed

    private void jTextFieldDateEmbaucheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDateEmbaucheActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDateEmbaucheActionPerformed

    private void jTextFieldFonctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFonctionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFonctionActionPerformed

    private void jTextFieldTauxHoraireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTauxHoraireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTauxHoraireActionPerformed

    private void jListSalarieMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListSalarieMouseClicked


    }//GEN-LAST:event_jListSalarieMouseClicked

    private void jListSalarieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jListSalarieKeyPressed
        TailleJListAffiche = DaoSalarie.getAllSalarieNameByService(indexServiceSelectionne).size();
        String keyPressed = java.awt.event.KeyEvent.getKeyText(evt.getKeyCode());

        if (keyPressed.equals("Up") && indexSalarieSelectionne > 0) {
            indexSalarieSelectionne--;
        }

        if (keyPressed.equals("Down") && indexSalarieSelectionne < TailleJListAffiche - 1) {
            indexSalarieSelectionne++;
        }

        updateTextField();
    }//GEN-LAST:event_jListSalarieKeyPressed

    private void jListSalarieMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jListSalarieMouseWheelMoved
        TailleJListAffiche = DaoSalarie.getAllSalarieNameByService(indexServiceSelectionne).size();
        if (evt.getWheelRotation() == -1 && indexSalarieSelectionne > 0) {
            jListSalarie.setSelectedIndex(--indexSalarieSelectionne);
        }
        if (evt.getWheelRotation() == 1 && indexSalarieSelectionne < TailleJListAffiche - 1) {
            jListSalarie.setSelectedIndex(++indexSalarieSelectionne);
        }

        updateTextField();
    }//GEN-LAST:event_jListSalarieMouseWheelMoved

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        ConnexionBDD.closeConnexion();
    }//GEN-LAST:event_formWindowClosing

    private void jComboBoxServicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxServicesActionPerformed

        var tempComboBoxIndex = new JComboBox();
        tempComboBoxIndex = (JComboBox) evt.getSource();
        indexServiceSelectionne = tempComboBoxIndex.getSelectedIndex();

        textFields.forEach(unJtextField -> {
            unJtextField.setText(null);
        });

        updateListSalarie();

    }//GEN-LAST:event_jComboBoxServicesActionPerformed

    private void jButtonCreerNouveauSalarieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreerNouveauSalarieActionPerformed
        JOptionPane.showConfirmDialog(this, "Feature pas encore terminée, désolé :(", "Attention", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButtonCreerNouveauSalarieActionPerformed

    private void jTextFieldModifyPrenomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldModifyPrenomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldModifyPrenomActionPerformed

    private void jTextFieldModifyNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldModifyNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldModifyNomActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jListSalarieMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jListSalarieMousePressed
        indexSalarieSelectionne = jListSalarie.getSelectedIndex();
        updateTextField();
    }//GEN-LAST:event_jListSalarieMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCreerNouveauSalarie;
    private javax.swing.JButton jButtonModifier;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JComboBox<String> jComboBoxServices;
    private javax.swing.JDialog jDialogModifierSalarie;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelCategorie;
    private javax.swing.JLabel jLabelDateEmbauche;
    private javax.swing.JLabel jLabelFonction;
    private javax.swing.JLabel jLabelListSalarie;
    private javax.swing.JLabel jLabelListService;
    private javax.swing.JLabel jLabelNbrEnfant;
    private javax.swing.JLabel jLabelNom;
    private javax.swing.JLabel jLabelNomDateNaiss;
    private javax.swing.JLabel jLabelPrenom;
    private javax.swing.JLabel jLabelSalarie;
    private javax.swing.JLabel jLabelService;
    private javax.swing.JLabel jLabelSituationFamiliale;
    private javax.swing.JLabel jLabelTauxHoraire;
    private javax.swing.JList<String> jListSalarie;
    private javax.swing.JPanel jPanelSalarie;
    private javax.swing.JScrollPane jScrollPaneListSalarie;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextFieldCategorie;
    private javax.swing.JTextField jTextFieldDateEmbauche;
    private javax.swing.JTextField jTextFieldDateNaiss;
    private javax.swing.JTextField jTextFieldFonction;
    private javax.swing.JTextField jTextFieldModifyFonction;
    private javax.swing.JTextField jTextFieldModifyNbrEnfant;
    private javax.swing.JTextField jTextFieldModifyNom;
    private javax.swing.JTextField jTextFieldModifyPrenom;
    private javax.swing.JTextField jTextFieldModifySitFamiliale;
    private javax.swing.JTextField jTextFieldModifyTauxHoraire;
    private javax.swing.JTextField jTextFieldNbrEnfant;
    private javax.swing.JTextField jTextFieldNom;
    private javax.swing.JTextField jTextFieldPrenom;
    private javax.swing.JTextField jTextFieldService;
    private javax.swing.JTextField jTextFieldSituationFamiliale;
    private javax.swing.JTextField jTextFieldTauxHoraire;
    // End of variables declaration//GEN-END:variables
}
