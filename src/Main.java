import javax.swing.*;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // フレーム（ウィンドウ）を作成
        JFrame frame = new JFrame("Swing ボタンの例");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // パネルを作成（レイアウトを使用）
        JPanel panel = new JPanel();

        // ボタンを作成
        JButton button = new JButton("クリック");
        button.setPreferredSize(new Dimension(80, 30)); // ボタンサイズを指定

        // ボタンをクリックしたときのアクションを設定
        button.addActionListener(e -> onButtonClick(frame));

        // **ボタンをパネルに追加**
        panel.add(button);

        // **パネルをフレームに追加**
        frame.add(panel, BorderLayout.CENTER);

        // フレームを表示
        frame.setVisible(true);
    }

    public static void onButtonClick(JFrame frame) {
        // JOptionPane.showMessageDialog(frame, "ボタンがクリックされました！");
        Lottery lot = new Lottery();
        lot.lottery();
    }
}
