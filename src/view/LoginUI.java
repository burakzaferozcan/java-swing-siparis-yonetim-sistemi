package view;

import business.UserController;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI extends JFrame {
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_title;
    private JPanel pnl_bottom;
    private JTextField fld_mail;
    private JButton btn_login;
    private JLabel lbl_mail;
    private JPasswordField fld_password;
    private JLabel lbl_password;
    private UserController userController;

    public LoginUI() {
        this.userController = new UserController();
        this.add(container);
        this.setTitle("Müşteri Yönetim Sistemi");
        this.setSize(400, 400);
        this.setVisible(true);
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - this.getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2;
        this.setLocation(x, y);

        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] checkList = {fld_mail, fld_password};
                if (!Helper.isEmailValid(fld_mail.getText())) {
                    Helper.showMsg("Geçerli bir e-posta adresi giriniz !");
                } else if (Helper.isFieldListEmpty(checkList)) {
                    Helper.showMsg("fill");

                } else {
                    User user = userController.findByLogin(fld_mail.getText(), fld_password.getText());
                    if (user == null) {
                        Helper.showMsg("Girdiğiniz bilgilere göre kullanıcı bulunamadı!");
                    } else {
                        dispose();
                        DashboardUI dashboardUI = new DashboardUI(user);
                    }
                }

            }
        });
    }
}
