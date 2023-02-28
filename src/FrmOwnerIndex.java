import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class FrmOwnerIndex extends JFrame implements ActionListener {

    JTable tblOwners = null;
    ArrayList<Owner> owners = null;
    JButton btnAdd = null;
    JButton btnDetail = null;
    JButton btnDelete = null;

    private FrmOwnerIndex(ArrayList<Owner> owners) {

        this.owners = owners;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setTitle("Owner list");

        initUI();
        populate();

        setVisible(true);
    }

    private void initUI() {

        this.tblOwners = new JTable();
        JScrollPane pnlCenter = new JScrollPane(this.tblOwners);

        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        this.btnAdd = new JButton("Add...");
        this.btnDetail = new JButton("Detail...");
        this.btnDelete = new JButton("Delete");

        pnlSouth.add(btnAdd);
        pnlSouth.add(btnDetail);
        pnlSouth.add(btnDelete);

        this.btnAdd.addActionListener(this);
        this.btnDelete.addActionListener(this);
        this.btnDetail.addActionListener(this);

        this.add(pnlCenter, BorderLayout.CENTER);
        this.add(pnlSouth, BorderLayout.SOUTH);
    }

    private void populate() {

        DefaultTableModel model = new DefaultTableModel();

        String[] cols = {"Id", "Name", "Surname", "Residence", "Date of Birth"};
        for(String col:cols)
            model.addColumn(col);

        for(Owner owner: this.owners)
            model.addRow(owner.toRow());


        this.tblOwners.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == this.btnDetail) detailOwner();
        if(e.getSource() == this.btnAdd) addOwner();
        if(e.getSource() == this.btnDelete) deleteOwner();
    }

    public void deleteOwner() {

        int selected = this.tblOwners.getSelectedRow();
        if(selected == -1) return;
        Owner owner = this.owners.get(selected);

        try {
            OwnerDAO.delete(owner.getId());
            this.owners.remove(owner);
            populate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void detailOwner() {

        int selected = this.tblOwners.getSelectedRow();
        if(selected == -1) return;

        new DlgOwner(this.owners.get(selected));
        populate();
    }

    public void addOwner() {

        Owner newowner = new Owner();
        int id;
        try {
            id = OwnerDAO.insert(newowner);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        newowner.setId(id);
        //System.out.println(newowner.toString());
        new DlgOwner(newowner);
        this.owners.add(newowner);
        try {
            OwnerDAO.update(newowner);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        populate();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {
                new FrmOwnerIndex(OwnerDAO.readAll());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
