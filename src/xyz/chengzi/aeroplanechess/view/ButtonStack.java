package xyz.chengzi.aeroplanechess.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ButtonStack extends JComponent implements ItemListener {
    private JRadioButton Yes;
    private JRadioButton No;
    private JComboBox<Integer> diceComboBox;
    private int Stack ;

    public int getStack() {
        return Stack;
    }

    public ButtonStack() {
        JFrame jf = new JFrame("是否重叠？");
        jf.setSize(200, 200);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        // Create 2 radio buttons
        JRadioButton radioBtn01 = new JRadioButton("是");
        JRadioButton radioBtn02 = new JRadioButton("否");

        // Create a button group and add two radio buttons to the group
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(radioBtn01);
        btnGroup.add(radioBtn02);

        // Set the first radio button to be selected
        radioBtn01.setSelected(false);
        radioBtn02.setSelected(false);

        panel.add(radioBtn01);
        panel.add(radioBtn02);

        JButton button = new JButton("确定");
        panel.add(button);

        jf.add(panel);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setVisible(true);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String info ="";
                // Get all the components on the panel through the panel property name
                System.out.println(info);
                for(Component c:panel.getComponents()){
                    if(c instanceof JRadioButton){
                        if(((JRadioButton) c).isSelected()){
                            info += ((JRadioButton)c).getText();
                        }
                    }
                }
                System.out.println(info);
                if(info.equals("是")){
                    Stack = 0;
                }else{
                    Stack = 1;
                }
                JOptionPane.showMessageDialog(null, "你选择了"+info);
            }
        });

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        System.out.println("Stack is :"+Stack);
    }
}
