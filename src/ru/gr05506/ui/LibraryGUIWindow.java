package ru.gr05506.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryGUIWindow extends JFrame implements LibraryView{
    private JTextField tfAuthor = new JTextField();
    private JTextField tfTitle = new JTextField();
    private JTextField tfYear = new JTextField();
    private JTextField tfDop = new JTextField();
    private JButton btnAdd = new JButton();
    private JTextArea txtArea = new JTextArea();
    private JPanel panel = new JPanel();
    public LibraryGUIWindow(LibraryController controller){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600,400));

        btnAdd.setText("Добавить");
        btnAdd.addActionListener(e -> {
            controller.addBookClicked();
        });
        GroupLayout gl = new GroupLayout(panel);
        panel.setLayout(gl);

        gl.setHorizontalGroup(gl.createSequentialGroup()
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
                .addGroup(gl.createParallelGroup()
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

        add(panel,BorderLayout.NORTH);
        add(new JScrollPane(txtArea),BorderLayout.CENTER);
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
        for (var el : books){
            txtArea.append(el.author()+" | "+el.title()+" | " + el.year() + " | " + el.info() + '\n');
        }
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(this,message);
    }
}
