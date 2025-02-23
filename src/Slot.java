import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.LWJGLException;

import java.io.File;
import java.io.FileNotFoundException;

public class Slot {

    // private static void initOpenGL() {
    // GL11.glMatrixMode(GL11.GL_PROJECTION);
    // GL11.glLoadIdentity();
    // GLU.gluPerspective(45.0f, (float) 800 / (float) 600, 0.1f, 100.0f);
    // GL11.glMatrixMode(GL11.GL_MODELVIEW);
    // GL11.glEnable(GL11.GL_DEPTH_TEST);
    // }

    public static void main(String[] args) {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600)); // 画面サイズ
            Display.create(); // Displayを作成
            GL11.glEnable(GL11.GL_TEXTURE_2D); // テクスチャを有効にする

            // OpenGLの設定
            GL11.glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // 背景色
            GL11.glEnable(GL11.GL_DEPTH_TEST); // 深度テスト
        } catch (LWJGLException e) {
            System.err.println("ディスプレイエラー");
            e.printStackTrace();
        }

        try {
            // ① OBJLoader インスタンス作成
            OBJLoader loader = new OBJLoader();
            // ② model.obj をロード
            Obj model = loader.loadModel(new File("obj//test.obj"));
            loader.render(model);
            int displayList = loader.createDisplayList(model);

            while (!Display.isCloseRequested()) {
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // 画面クリア

                GL11.glCallList(displayList); // ディスプレイリストを呼び出す

                Display.update(); // 画面の更新
                Display.sync(60); // 60fpsに同期
            }
            Display.destroy();

        } catch (FileNotFoundException e) {
            System.err.println("エラー: OBJファイルが見つかりません。");
            e.printStackTrace();
        }

        // // ③ 成功メッセージ
        // System.out.println("OBJファイルの読み込み完了！");
        // System.out.println("頂点数: " + model.getVertices().size());
        // System.out.println("テクスチャ座標数: " + model.getTextureCoordinates().size());
        // System.out.println("法線数: " + model.getNormals().size());
        // System.out.println("面数: " + model.getFaces().size());
    }
}
