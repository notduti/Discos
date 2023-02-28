import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DlgDisco extends JDialog implements ActionListener {

    private Disco disco = null;
    private JTextField txtId = null;
    private JTextField txtName = null;
    private JTextField txtCapacity = null;
    private JButton btnUpdate = null;

    public DlgDisco(Disco disco) {

        this.disco = disco;
        System.out.println(this.disco.toString());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setTitle("Disco " + this.disco.getName());

        initUI();
        populate();

        setVisible(true);
    }

    private void initUI() {

        JPanel pnlCenter = new JPanel(new GridLayout(3, 1));

        JPanel pnlId = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblId = new JLabel("Id:");
        this.txtId = new JTextField(20);
        this.txtId.setEnabled(false);
        pnlId.add(lblId);
        pnlId.add(this.txtId);
        pnlCenter.add(pnlId);

        JPanel pnlName = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblName = new JLabel("Name:");
        this.txtName = new JTextField(20);
        pnlName.add(lblName);
        pnlName.add(this.txtName);
        pnlCenter.add(pnlName);

        JPanel pnlCapacity = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCapacity = new JLabel("Capacity:");
        this.txtCapacity = new JTextField(20);
        pnlCapacity.add(lblCapacity);
        pnlCapacity.add(this.txtCapacity);
        pnlCenter.add(pnlCapacity);

        this.add(pnlCenter, BorderLayout.CENTER);

        JPanel pnlSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.btnUpdate = new JButton("Update");
        pnlSouth.add(this.btnUpdate);
        this.add(pnlSouth, BorderLayout.SOUTH);
    }

    private void populate() {

        this.txtId.setText(this.disco.getId() + "");
        this.txtName.setText(this.disco.getName());
        this.txtCapacity.setText(this.disco.getCapacity() + "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btnUpdate) {

            try {
                DiscoDAO.update(this.disco);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
