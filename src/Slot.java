import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.LWJGLException;

public class Slot {

    static Setobj setobj = new Setobj();
    private volatile int number[];

    public Slot() {
        this.number = new int[3];  // 例えば、3つのスロットの数字を格納するために初期化
    }

    public void setDisplaynumber(int[] number) {
        this.number = number;
    }


    public void slot_main() {
        new Thread(() -> {
            try {
                // ディスプレイ設定
                int width = 800;
                int height = 600;
                Display.setDisplayMode(new DisplayMode(width, height)); // 画面サイズ
                Display.create(); // Display作成
                setobj.setobj_OpenGL(); // openGL初期化
                setobj.setobj_load();
            } catch (LWJGLException e) {
                System.err.println("ディスプレイエラー");
                e.printStackTrace();
                return;
            }

            Setobj setobj = new Setobj();
            setobj.setobj_load();
            Slotmove slotmove_left = new Slotmove(setobj);
            Slotmove slotmove_senter = new Slotmove(setobj);
            Slotmove slotmove_right = new Slotmove(setobj);
            // 描画ループ
            while (!Display.isCloseRequested()) {// ウインドウが閉じたらループ終了
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);// 画面クリア
                slotmove_left.update(number[0],0.2f);
                slotmove_senter.update(number[1],0);
                slotmove_right.update(number[2],-0.2f);
                Display.update();
                Display.sync(60);
            }
            Display.destroy(); // Displayの破棄
        }).start();
    }
}
