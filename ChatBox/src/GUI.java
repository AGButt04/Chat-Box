import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GUI extends JFrame implements ActionListener {

    private static JTextArea chatArea;
    private static JTextField inputField;
    private static JButton sendButton, resetButton;

    public GUI() {
        super("Chat Frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 450);
        setLocationRelativeTo(null);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem openItem = new JMenuItem("Open...");
        JMenuItem saveItem = new JMenuItem("Save As...");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);

        JMenuItem welcomeItem = new JMenuItem("Welcome");
        JMenuItem helpContentsItem = new JMenuItem("Help Contents");
        helpMenu.add(welcomeItem);
        helpMenu.add(helpContentsItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);

        // Chat Area
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chatArea.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(chatArea);

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel label = new JLabel("Enter text:");
        inputField = new JTextField(20);
        sendButton = new JButton("Send");
        resetButton = new JButton("Reset");

        inputPanel.add(label);
        inputPanel.add(inputField);
        inputPanel.add(sendButton);
        inputPanel.add(resetButton);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        // Action Listeners
        sendButton.addActionListener(this);
        resetButton.addActionListener(this);

        // Enter key sends message
        inputField.addActionListener(e -> sendMessage());

        // Menu item actions (just simple messages for now)
        welcomeItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Welcome to the Chat App!"));
        helpContentsItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Type your message and press Send or Enter."));
        openItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Open feature coming soon!"));
        saveItem.addActionListener(e -> JOptionPane.showMessageDialog(this, "Save feature coming soon!"));

        setVisible(true);
    }

    private void sendMessage() {
        String text = inputField.getText().trim();
        if (!text.isEmpty()) {
            String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            chatArea.append("[" + time + "] You: " + text + "\n");
            inputField.setText("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == sendButton) {
            sendMessage();
        } else if (src == resetButton) {
            int confirm = JOptionPane.showConfirmDialog(this, "Clear chat history?", "Confirm Reset", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                chatArea.setText("");
                inputField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GUI::new);
    }
}