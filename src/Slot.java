import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.LWJGLException;

public class Slot {

    static Setobj setobj = new Setobj();

    public Slot() {
        // ディスプレイ設定
        int width = 800;
        int height = 600;
        Display.setDisplayMode(new DisplayMode(width, height)); // 画面サイズ
        Display.create(); // Display作成
        setobj.setobj_OpenGL(); // openGL初期化
        setobj.setobj_load();
    }

    public void slot_main(ObjTransform[] objects) {
        try {
            Slotmove slotmove_left = new Slotmove(setobj, objects[0]);
            Slotmove slotmove_senter = new Slotmove(setobj, objects[1]);
            Slotmove slotmove_right = new Slotmove(setobj, objects[2]);
            // 描画ループ
            while (!Display.isCloseRequested()) {// ウインドウが閉じたらループ終了
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);// 画面クリア
                slotmove_left.update();
                slotmove_senter.update();
                slotmove_right.update();
                Display.update();
                Display.sync(60);
            }
            Display.destroy(); // Displayの破棄
        } catch (LWJGLException e) {
            System.err.println("ディスプレイエラー");
            e.printStackTrace();
            return;
        }
    }

    static float ypos_ = 0;
}
