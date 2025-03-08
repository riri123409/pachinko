
/*
 * オブジェクトの位置設定クラス
 * スロットの目のオブジェクトを読み込む
 * ディスプレイの初期化
 */
import org.lwjgl.opengl.GL11;
import java.io.File;

class ObjTransform {
    float xPos;
    float yPos;
    float angles;
    float alpha;
    int index; // どのモデルを使うか

    // コンストラクタ
    public ObjTransform(float xPos, float yPos, float angles, float alpha, int index) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.angles = angles;
        this.alpha = alpha;
        this.index = index;
    }
}

public class Setobj {
    private static final int MODEL_COUNT = 9; // 9つのモデル
    private static OBJLoader loader;
    private static Obj[] models = new Obj[MODEL_COUNT];
    private static int[] textureID = new int[MODEL_COUNT];

    public void setobj_load() {
        loader = new OBJLoader();

        try {
            // 9個のOBJモデルをロード
            for (int i = 0; i < MODEL_COUNT; i++) {
                models[i] = loader.loadModel(new File("obj/" + (i + 1) + ".obj"));
                textureID[i] = loader.loadTexture("/resource/" + (i + 1) + ".png");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Obj[] getModels() {
        return models.clone(); // 新しい配列のコピーを返す
    }

    public int[] getTextureID() {
        return textureID.clone(); // 新しい配列のコピーを返す
    }

    // openGL初期化
    public void setobj_OpenGL() {
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

    }

    public void setobj_setobj(float xPos, float yPos, float angles, int i, Setobj setobj) {
        // GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);// 画面クリア
        GL11.glLoadIdentity();// 行列リセット
        GL11.glPushMatrix();// 現在の変換行列を保存
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 0.5f);
        GL11.glTranslatef(xPos, yPos, 0); // 3×3配置
        GL11.glRotatef(angles, 0.0f, 0.0f, 0.0f);// GL11.glRotatef(angle, x, y, z)
        loader.render(setobj.getModels()[i], setobj.getTextureID()[i]);
        GL11.glPopMatrix();// 行列の復元
    }
}
