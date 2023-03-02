import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class DlgOwner extends JDialog implements ActionListener {

    private Owner owner = null;
    private JButton btnAdd = null;
    private JButton btnDelete = null;
    private JButton btnUpdate = null;
    private JButton btnDetail = null;

    private JTextField txtId = null;
    private JTextField txtName = null;
    private JTextField txtSurname = null;
    private JTextField txtPlace = null;
    private JTextField txtDate = null;

    private JTable tblDiscos = null;


    public DlgOwner(Owner owner) {

        this.owner = owner;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setTitle("Owner " + owner.getName());

        initUI();
        populate();

        setVisible(true);
    }

    private void populate() {

        DefaultTableModel model = new DefaultTableModel();

        String[] cols = {"iddisco", "id", "name", "capacity"};

        for(String col: cols)
            model.addColumn(col);

        ArrayList<Disco> discos = owner.getDiscos();
        if(discos != null) {

            for(Disco disco: discos)
               model.addRow(disco.toRow());
            this.tblDiscos.setModel(model);
        }

        this.txtId.setText(owner.getId() + "");
        this.txtName.setText(owner.getName());
        this.txtSurname.setText(owner.getSurname());
        this.txtPlace.setText(owner.getPlace());
        this.txtDate.setText(owner.getDate());
    }

    private void initUI() {

        JPanel pnlNorth = new JPanel(new GridLayout(7, 1));

        JPanel pnlId = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblId = new JLabel("Id:");
        this.txtId = new JTextField(20);
        this.txtId.setEnabled(false);
        pnlId.add(lblId);
        pnlId.add(this.txtId);
        pnlNorth.add(pnlId);

        JPanel pnlName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblName = new JLabel("Name:");
        this.txtName = new JTextField(20);
        pnlName.add(lblName);
        pnlName.add(this.txtName);
        pnlNorth.add(pnlName);

        JPanel pnlSurname = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblSurname = new JLabel("Surname:");
        this.txtSurname = new JTextField(20);
        pnlSurname.add(lblSurname);
        pnlSurname.add(this.txtSurname);
        pnlNorth.add(pnlSurname);

        JPanel pnlPlace = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblPlace = new JLabel("Place:");
        this.txtPlace = new JTextField(20);
        pnlPlace.add(lblPlace);
        pnlPlace.add(this.txtPlace);
        pnlNorth.add(pnlPlace);

        JPanel pnlDate = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblDate = new JLabel("Date:");
        this.txtDate = new JTextField(20);
        pnlDate.add(lblDate);
        pnlDate.add(this.txtDate);
        pnlNorth.add(pnlDate);

        JPanel pnlUpdate = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.btnUpdate = new JButton("UPDATE");
        pnlUpdate.add(this.btnUpdate);
        pnlNorth.add(pnlUpdate);

        this.add(pnlNorth, BorderLayout.NORTH);


        this.tblDiscos = new JTable();
        JScrollPane pnlCenter = new JScrollPane(this.tblDiscos);
        this.add(pnlCenter, BorderLayout.CENTER);


        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.btnAdd = new JButton("Add...");
        this.btnDelete = new JButton("Delete");
        this.btnDetail = new JButton("Detail...");
        pnlSouth.add(btnAdd);
        pnlSouth.add(btnDetail);
        pnlSouth.add(btnDelete);

        this.add(pnlSouth, BorderLayout.SOUTH);

        this.btnAdd.addActionListener(this);
        this.btnDelete.addActionListener(this);
        this.btnDetail.addActionListener(this);
        this.btnUpdate.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnUpdate) updateOwner();

        if(e.getSource() == btnAdd) addDisco();

        if(e.getSource() == btnDelete) deleteDisco();

        if(e.getSource() == btnDetail) detailDisco();

    }

    private void updateOwner() {

        this.owner.setName(this.txtName.getText());
        this.owner.setSurname(this.txtSurname.getText());
        this.owner.setPlace(this.txtPlace.getText());
        this.owner.setDate(this.txtDate.getText());

        try {
            OwnerDAO.update(this.owner);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteDisco() {

        int selected = this.tblDiscos.getSelectedRow();
        if(selected == -1) return;

        Disco disco = owner.getDiscos().get(selected);

        try {

            DiscoDAO.delete(disco.getId());
            this.owner.getDiscos().remove(disco);
            populate();
        } catch (
        SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void detailDisco() {

        int selected = this.tblDiscos.getSelectedRow();
        if(selected == -1) return;

        new DlgDisco(this.owner.getDiscos().get(selected));
        populate();
    }


    private void addDisco() {

        Disco disco = new Disco();
        int iddisco;

        try {
            iddisco = DiscoDAO.insert(disco);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        disco.setIddisco(iddisco);
        disco.setId(this.owner.getId());
        new DlgDisco(disco);

        try {
            DiscoDAO.update(disco);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.owner.addDisco(disco);

        populate();
    }
}
