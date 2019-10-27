package program1a;

import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.io.*;
import java.net.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField textField_port;
	private JTextField textField_IP;
	private JTextField textField_message;
	
	Socket socket = null;
	BufferedReader br;
	PrintWriter pw;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setTitle("Knock Knock Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 700);
		setBounds(400, 100, 400, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblPortNumber = new JLabel("Port Number: ");
		
		JLabel lblIpAddress = new JLabel("IP Address: ");
		
		textField_port = new JTextField();
		textField_port.setText("5520");
		textField_port.setColumns(10);
		
		textField_IP = new JTextField();
		textField_IP.setText("constance.cs.rutgers.edu");
		textField_IP.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnConnect.getText().equals("Connect")) {
					try {
						String host = textField_IP.getText();
						String port = textField_port.getText();
						int portNum = Integer.parseInt(port);
						socket = new Socket(host, portNum);
						if (socket.isConnected()) {
							btnConnect.setText("Disconnect");
						}
					}
					catch (Exception ex) {
						System.out.println( "Error: " + ex );
						socket = null; // set to null so can check if it is open
						btnConnect.setText("Connect");
					}
				} else {
					try {
						btnConnect.setText("Connect");
						br.close();
						pw.close();
						socket.close();
						socket = null;
						textArea.append("Succesfully disconnect from server\n");
					} catch (Exception ex) {
						System.out.println( "Error: " + ex );
						socket = null; // set to null so can check if it is open
					}
				}
			}
				
			});
		
	
		JLabel lblMessageToServer = new JLabel("Message to Server: ");
		
		textField_message = new JTextField();
		textField_message.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnConnect.getText().equals("Disconnect")) {
					try {
						BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
						PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
						String userInput = textField_message.getText();
						System.out.println(userInput);
						pw.println(userInput);
						textArea.append("Client: " + userInput + "\n");
						String serverResponse = br.readLine();
						System.out.println(serverResponse);
						textArea.append("Server: " + serverResponse + "\n");
						if (userInput.equals("quit")) {
							btnConnect.setText("Connect");
							br.close();
							pw.close();
							socket.close();
							socket = null;
						}
					}
					catch (Exception ex) {
						System.out.println( "Error: " + ex );
						socket = null; // set to null so can check if it is open
						btnConnect.setText("Connect");
					}
				} else {
					btnConnect.setText("Connect");
					textArea.append("Send unsuccessful, please connect to server\n");
				}
			}
		});
		
		JLabel lblClineserverCommunication = new JLabel("Client/Server Communication: ");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPortNumber)
						.addComponent(lblIpAddress))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textField_port, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
						.addComponent(textField_IP, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblMessageToServer)
					.addPreferredGap(ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
					.addComponent(btnConnect)
					.addGap(25))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(textField_message, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblClineserverCommunication)
					.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
					.addComponent(btnSend)
					.addGap(23))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPortNumber)
						.addComponent(textField_port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_IP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIpAddress))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnConnect)
							.addGap(14))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMessageToServer)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_message, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSend)
						.addComponent(lblClineserverCommunication))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
	
