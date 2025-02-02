package org.example.test.waste;

import org.example.project1.user.DAO.OrdererDAO;
import org.example.project1.user.VO.OrdererVO;

import javax.swing.*;
import java.awt.*;

public class Register extends JFrame {

    private JTextField id;
    private JPasswordField pw;
    private JPasswordField pwc;
    private JTextField name;
    private JTextField tel;
    private JTextField license;
    private JTextField loc;



    public Register() {
        // JFrame 설정
        setFrame();
        // UI 초기화
        initUI();
        // 화면 표시
        setVisible(true);
    }

    public void setFrame(){
        setTitle("회원가입");
        setSize(1200, 675);
        setLocationRelativeTo(null); // 화면 중앙 배치
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
    }


    private void initUI() {
        // 아이디
        add(new JLabel("아이디  : "));
        id = new JTextField(12);
        add(id);

        // 비밀번호
        add(new JLabel("비밀번호 : "));
        pw = new JPasswordField(12);
        add(pw);

        // 비밀번호 확인
        add(new JLabel("비번확인 : "));
        pwc = new JPasswordField(12);
        add(pwc);

        // 이름
        add(new JLabel("이름   : "));
        name = new JTextField(12);
        add(name);

        // 전화번호
        add(new JLabel("전화번호 : "));
        tel = new JTextField(12);
        add(tel);

        // 사업자 등록 번호
        add(new JLabel("사업자 등록 번호 : "));
        license = new JTextField(12);
        add(license);

        // 사업자 등록 주소지
        add(new JLabel("사업자 등록 주소지 : "));
        loc = new JTextField(12);
        add(loc);

        // 등록 버튼
        JButton registerBtn = new JButton("가입하기");
        add(registerBtn);
        registerBtn.addActionListener(e -> {
            try {
                if(validation()){
                    registerAccount();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "회원가입 중 오류가 발생했습니다.");
            }
        });

        // 취소 버튼
        JButton cancelBtn = new JButton("취소");
        add(cancelBtn);
        cancelBtn.addActionListener(e -> dispose());
    }

    private boolean validation() throws Exception {

        boolean result = false;

        OrdererDAO dao = new OrdererDAO();

        // 계정 등록시 유효성 검증
        // 공백 검증
        if (id.getText().trim().isEmpty() || pw.getText().trim().isEmpty() ||
                name.getText().trim().isEmpty() || tel.getText().trim().isEmpty() || license.getText().trim().isEmpty() || loc.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "모든 필드를 올바르게 입력하세요.");
            return false;
        }

        // 1. 아이디 검증
        if (!id.getText().matches("^[a-z0-9]{5,15}$")) {
            JOptionPane.showMessageDialog(this, "아이디 조건이 맞지 않습니다.\n영어 소문자, 숫자를 포함하여 5-15글자");
            return false;
        }

        try {
            if (dao.confirmID(id.getText())) {
                JOptionPane.showMessageDialog(this, "아이디가 중복됩니다.");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // 2. 비밀번호 검증
        if (!pw.getText().matches("^[a-z0-9]{8,15}$")) {
            JOptionPane.showMessageDialog(this, "비밀번호 조건이 맞지 않습니다.\n영어 소문자, 숫자를 포함하여 8-15글자");
            return false;
        }

        if (!pw.getText().equals(pwc.getText())) {
            JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.");
            return false;
        }

        // 3. 이름 검증
        if (!name.getText().matches("^[a-zA-Z가-힣]{2,5}$")) {
            JOptionPane.showMessageDialog(this, "이름 조건이 맞지 않습니다.\n영어, 한글 2-5글자");
            return false;
        }

        // 4. 전화번호 검증
        if (!tel.getText().matches("^010-\\d{4}-\\d{4}$")) {
            JOptionPane.showMessageDialog(this, "전화번호 조건이 맞지 않습니다.\n010-XXXX-XXXX");
            return false;
        }

        // 5. 사업자 등록 번호 검증
        if (!license.getText().matches("^\\d{3}-\\d{2}-\\d{5}$")) {
            JOptionPane.showMessageDialog(this, "사업자 등록 번호 조건이 맞지 않습니다.\nXXX-XX-XXXXX");
            return false;
        }

        // 6. 사업자 등록 주소지 검증
        if (!loc.getText().matches("^[a-zA-Z가-힣]{5,50}$")) {
            JOptionPane.showMessageDialog(this, "사업자 등록 주소지 조건이 맞지 않습니다.\n한글, 영어, 숫자를 포함하여 5-50글자");
             return false;
        }
        return true;
    }

    private void registerAccount(){
        try {
            OrdererDAO dao = new OrdererDAO();
            // 아이디 생성
            OrdererVO vo = new OrdererVO();
            vo.setId(id.getText());
            vo.setPassword(pw.getText());
            vo.setName(name.getText());
            vo.setTel(tel.getText());
            vo.setLicense(license.getText());
            vo.setLoc(loc.getText());
            dao.insert(vo);
            JOptionPane.showMessageDialog(this, "정상적으로 가입되었습니다.");
            dispose();

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "회원가입 처리 중 오류가 발생했습니다.");
        }
    }

}
