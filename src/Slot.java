import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Slot {

    private static void initOpenGL() {
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        // GLU.gluPerspective(45.0f, (float) 800 / (float) 600, 0.1f, 100.0f);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        // 深度テスト（Zバッファ）を有効化(奥行)
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        // テクスチャを有効化
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        // 背景色の設定
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

        // カリング（裏面を描画しない）
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);

        // GL11.glLoadIdentity(); // 視点のリセット
        // GLU.gluLookAt(0.0f, 0.0f, -5.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f); //
        // 視点の設定
    }

    public static int loadTexture(String filePath) {
        try {
            // 画像を読み込む
            InputStream in = TextureLoader.class.getResourceAsStream(filePath);
            if (in == null) {
                throw new IOException("Texture file not found: " + filePath);
            }

            Texture texture = TextureLoader.getTexture("PNG", in);

            int textureID = texture.getTextureID();

            // テクスチャのパラメータ設定
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
            GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

            return textureID;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load texture: " + filePath);
        }
    }

    // 四角形にテクスチャを適用して描画(テクスチャのテスト)
    public static void renderTexture(int textureID) {
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);

        // 四角形を描画
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glTexCoord2f(0, 1);// テクスチャ0.0
            GL11.glVertex2f(-1.0f, -1.0f);// 左下
            GL11.glTexCoord2f(1, 1);// x 1 y 0
            GL11.glVertex2f(1.0f, -1.0f);// 右下
            GL11.glTexCoord2f(1, 0);// x 1 y 1
            GL11.glVertex2f(1.0f, 1.0f);// 右上
            GL11.glTexCoord2f(0, 0);
            GL11.glVertex2f(-1.0f, 1.0f);// 左上
        }
        GL11.glEnd();

        GL11.glDisable(GL11.GL_TEXTURE_2D);
    }

    public static void main(String[] args) {
        try {
            // ディスプレイ設定
            Display.setDisplayMode(new DisplayMode(600, 600)); // 画面サイズ
            Display.create(); // Display作成
            initOpenGL(); // openGL初期化

        } catch (LWJGLException e) {
            System.err.println("ディスプレイエラー");
            e.printStackTrace();
            return;
        }

        try {
            // テクスチャの読み込み
            int textureID = loadTexture("/resource/3.png"); // 画像ファイルのパスを修正
            // OBJLoaderインスタンス作成
            OBJLoader loader = new OBJLoader();
            // OBJファイルの読み込み
            Obj model = loader.loadModel(new File("obj/3.obj")); // パス修正

            // モデルとテクスチャを描画
            loader.render(model, textureID);

            // ディスプレイリスト作成
            int displayList = loader.createDisplayList(model, textureID);

            // 描画ループ
            while (!Display.isCloseRequested()) {// ウインドウが閉じたらループ終了
                GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // 画面クリア
                GL11.glCallList(displayList); // ディスプレイリストを呼び出す
                // renderTexture(textureID);
                Display.update(); // 画面更新
                Display.sync(60); // 60fpsに同期
            }

            Display.destroy(); // Displayの破棄

        } catch (FileNotFoundException e) {
            System.err.println("エラー: OBJファイルが見つかりません。");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("エラー: テクスチャの読み込みに失敗しました。");
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
