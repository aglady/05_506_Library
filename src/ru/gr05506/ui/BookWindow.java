package ru.gr05506.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class BookWindow extends JFrame implements BookView{
    private JTextField tfAuthor = new JTextField();
    private JTextField tfTitle = new JTextField();
    private JTextField tfYear = new JTextField();
    private JTextField tfDop = new JTextField();
    private JButton btnAdd = new JButton();
    private JTextArea txtArea = new JTextArea();
    private JPanel panel = new JPanel();
    private JLabel labelStatus = new JLabel();

    private final BookController controller;
    public BookWindow(BookController controller){
        this.controller = controller;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600,400));

        JLabel lblAuthor = new JLabel("Автор:");
        JLabel lblTitle = new JLabel("Название:");
        JLabel lblYear = new JLabel("Год издания:");
        JLabel lblAdditional = new JLabel("Допинформация:");

        btnAdd.setText("Добавить");
        btnAdd.addActionListener(e -> {
            controller.addBookClicked();
        });
        tfAuthor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.inputChanged();
            }
        });
        tfTitle.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.inputChanged();
            }
        });
        tfYear.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.inputChanged();
            }
        });
        GroupLayout gl = new GroupLayout(panel);
        panel.setLayout(gl);

        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(
                        gl.createParallelGroup()
                                .addComponent(lblAuthor)
                                .addComponent(lblTitle)
                                .addComponent(lblYear)
                                .addComponent(lblAdditional)
                )
                .addGap(8)
                .addGroup(gl.createParallelGroup()
                        .addComponent(tfAuthor)
                        .addComponent(tfTitle)
                        .addComponent(tfYear)
                        .addComponent(tfDop)
                )
                .addGap(8)
                .addComponent(btnAdd)
                .addGap(8)
        );

        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(8)
                .addGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(gl.createSequentialGroup()
                                .addComponent(lblAuthor)
                                .addGap(10)
                                .addComponent(lblTitle)
                                .addGap(10)
                                .addComponent(lblYear)
                                .addGap(10)
                                .addComponent(lblAdditional))
                        .addGroup(gl.createSequentialGroup()
                                .addComponent(tfAuthor)
                                .addGap(8)
                                .addComponent(tfTitle)
                                .addGap(8)
                                .addComponent(tfYear)
                                .addGap(8)
                                .addComponent(tfDop)
                        )
                        .addComponent(btnAdd)
                )
                .addGap(8)
        );
        txtArea.setEditable(false);
        add(panel,BorderLayout.NORTH);
        add(new JScrollPane(txtArea),BorderLayout.CENTER);
        add(labelStatus,BorderLayout.SOUTH);
    }

    @Override
    public String getAuthor() {
        return tfAuthor.getText();
    }

    @Override
    public String getBookTitle() {
        return tfTitle.getText();
    }

    @Override
    public String getYear() {
        return tfYear.getText();
    }

    @Override
    public String getInfo() {
        return tfDop.getText();
    }

    @Override
    public void clearInput() {
        tfAuthor.setText("");
        tfTitle.setText("");
        tfYear.setText("");
        tfDop.setText("");
    }

    @Override
    public void showBooks(List<Book> books) {
        txtArea.setText("");
        var i = 1;
        for(var book: books) {
            txtArea.append(i++ + ". " + book.author());
            txtArea.append(" | " + book.title());
            txtArea.append(" | " + book.year());
            if (book.info() != null && !book.info().isBlank())
                txtArea.append(" | " + book.info());
            txtArea.append(System.lineSeparator());
        }
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void render(BookFormState state) {
        btnAdd.setEnabled(state.addEnabled());
        labelStatus.setText(state.status());
        if (state.error() != null){
            showError(state.error());
        }
    }
}
