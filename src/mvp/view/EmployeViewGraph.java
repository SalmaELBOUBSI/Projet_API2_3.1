package mvp.view;

import Entreprise.Bureau;
import Entreprise.Employe;
import mvp.presenter.EmployePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmployeViewGraph extends JFrame implements ActionListener,EmployeViewInterface {

    private EmployePresenter presenter;
    private JList<Employe> jlist;
    private JButton addButton;
    private JButton removeButton;

    private JTextField mailField;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField bureauField;

    private DefaultListModel<Employe> dle = new DefaultListModel<>();

    public EmployeViewGraph(){
        super("Gestion des employés ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jlist = new JList<>();
        jlist.setModel(dle);
        JScrollPane scrollPane = new JScrollPane(jlist);

        addButton = new JButton("AJOUTER");
        removeButton = new JButton("SUPPRIMER");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);


        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridLayout(7, 2));

        fieldPanel.add(new JLabel("Mail :"));
        mailField = new JTextField();
        fieldPanel.add(mailField);
        fieldPanel.add(new JLabel("Nom :"));
        nomField = new JTextField();
        fieldPanel.add(nomField);
        fieldPanel.add(new JLabel("Prénom :"));
        prenomField = new JTextField();
        fieldPanel.add(prenomField);
        fieldPanel.add(new JLabel("Bureau ID :"));
        bureauField = new JTextField();
        fieldPanel.add(bureauField);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);
        contentPane.add(fieldPanel, BorderLayout.NORTH);

        addButton.addActionListener(this);
        removeButton.addActionListener(this);

        setSize(new Dimension(500, 300));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {

            String mail = mailField.getText();
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            int bureau_id = Integer.parseInt(bureauField.getText());
            presenter.addEmploye(new Employe(0,mail, nom, prenom,new Bureau(bureau_id)));
        } else if (e.getSource() == removeButton) {
            int row = jlist.getSelectedIndex();
            if (row >= 0) {
                DefaultListModel<Employe> model = (DefaultListModel<Employe>) jlist.getModel();
                Employe employe = model.get(row);
                presenter.removeEmploye(employe);
            }
        }

    }

    @Override
    public void setPresenter(EmployePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Employe> employes) {
        dle.clear();
        for(Employe emp :employes) {
            dle.addElement(emp);
        }
    }

    @Override
    public void affMsg(String msg) {
        JOptionPane.showInputDialog(null, "information :", msg);
    }

    @Override
    public Employe selectionner(List<Employe> lemp) {
        return null;
    }

    @Override
    public void affList(List infos) {

    }
}
