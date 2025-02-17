import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // フレーム（ウィンドウ）を作成
        JFrame frame = new JFrame("Swing ボタンの例");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // ボタンを作成
        JButton button = new JButton("クリック");

        // ボタンをクリックしたときのアクションを設定
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "ボタンがクリックされました！"));

        // フレームにボタンを追加
        frame.getContentPane().add(button);

        // フレームを表示
        frame.setVisible(true);
    }
}
