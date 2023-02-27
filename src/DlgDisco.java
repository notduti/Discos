import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DlgDisco extends JDialog implements ActionListener {

    private Disco disco = null;
    private JTextField txtId = null;
    private JTextField txtName = null;
    private JTextField txtCapacity = null;

    public DlgDisco(Disco disco) {

        this.disco = disco;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setTitle("Disco " + this.disco.getName());

        initUI();
        populate();

        setVisible(true);
    }

    private void initUI() {

        JPanel pnlNorth = new JPanel(new GridLayout(3, 1));

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

        JPanel pnlCapacity = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCapacity = new JLabel("Capacity:");
        this.txtCapacity = new JTextField(20);
        pnlCapacity.add(lblCapacity);
        pnlCapacity.add(this.txtCapacity);
        pnlNorth.add(pnlCapacity);

        this.add(pnlNorth, BorderLayout.NORTH);
    }

    private void populate() {

        this.txtId.setText(this.disco.getId() + "");
        this.txtName.setText(this.disco.getName());
        this.txtCapacity.setText(this.disco.getCapacity() + "");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
