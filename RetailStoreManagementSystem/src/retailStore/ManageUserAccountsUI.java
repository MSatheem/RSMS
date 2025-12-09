package retailStore;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ManageUserAccountsUI extends JPanel {

	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	private JTable table;
	private JTextField tfUserName;
	DefaultComboBoxModel<String> comboBoxModel;
	private JComboBox<String> comboBoxEmployee;
	@SuppressWarnings("rawtypes")
	JComboBox cbAccessLevel, cbAccessStatus;
	Users newUser;
	Employee employee;
	
	private void populateTable() {
		Users users = new Users();
		String name[] = {"Id","User Name","Access Status","Access Level"};
		model = new DefaultTableModel(users.tableArray(),name);
		table.setModel(model);
		populateComboBox();
	}
	
	private void populateComboBox() {
		newUser  = new Users();
		String data[] = new String[newUser.employeeListWithoutUserAccounts().size()];
		for(int i=0; i<data.length; i++) {
			employee = newUser.employeeListWithoutUserAccounts().get(i);
			data[i] = employee.getId() + " "+ String.valueOf(employee.getName());
 		}
		comboBoxModel = new DefaultComboBoxModel<String>(data);
		comboBoxEmployee = new JComboBox<String>(comboBoxModel);
		comboBoxEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboBoxEmployee.setModel(comboBoxModel);
		comboBoxEmployee.setSelectedIndex(-1);
		clearFields();
	}
	
	private void clearFields() {
		tfUserName.setText("");
		cbAccessLevel.setSelectedIndex(-1);
		cbAccessStatus.setSelectedIndex(-1);
		comboBoxEmployee.setSelectedIndex(-1);
	}
	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ManageUserAccountsUI() {
		setLayout(null);
		setBounds(0,0,1000,700);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 473, 819, 192);
		add(scrollPane);
		
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Users");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(442, 46, 54, 22);
		add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(90, 196, 819, 248);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_1 = new JLabel("User Name");
		lblNewLabel_1_1.setBounds(59, 31, 99, 27);
		panel.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1_2 = new JLabel("AccessLevel");
		lblNewLabel_1_2.setBounds(59, 94, 99, 27);
		panel.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		cbAccessLevel = new JComboBox();
		cbAccessLevel.setBounds(218, 97, 122, 21);
		panel.add(cbAccessLevel);
		cbAccessLevel.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
		cbAccessLevel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		tfUserName = new JTextField();
		tfUserName.setBounds(218, 34, 117, 25);
		panel.add(tfUserName);
		tfUserName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("AccessStatus");
		lblNewLabel_1.setBounds(483, 94, 99, 27);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		cbAccessStatus = new JComboBox();
		cbAccessStatus.setBounds(630, 97, 122, 21);
		panel.add(cbAccessStatus);
		cbAccessStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cbAccessStatus.setModel(new DefaultComboBoxModel(new String[] {"False", "True"}));
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creating user and saving user
				if(cbAccessStatus.getSelectedIndex() == 1) {
					newUser.setAccessStatus(true);
				} else {
					newUser.setAccessStatus(false);
				}
				newUser.setUserName(tfUserName.getText());
				newUser.setAccessLevel(cbAccessLevel.getSelectedIndex());
				newUser.setEmployeeId(newUser.employeeListWithoutUserAccounts().get(comboBoxEmployee.getSelectedIndex()).getId());
				newUser.addNewUser();
				populateTable();
			}
		});
		btnSave.setBounds(667, 175, 85, 21);
		panel.add(btnSave);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});
		btnClear.setBounds(483, 175, 85, 21);
		panel.add(btnClear);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Employee ID");
		lblNewLabel_1_1_1.setBounds(134, 122, 86, 19);
		add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		

		populateTable(); //Populate Table
		
		comboBoxEmployee.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBoxEmployee.setBounds(286, 121, 122, 21);
		add(comboBoxEmployee);
	}
}
