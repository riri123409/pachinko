import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import java.io.File;

public class Loadobj {
    private static final int MODEL_COUNT = 9; // 9つのモデル
    private static OBJLoader loader;
    private static Obj[] models = new Obj[MODEL_COUNT];
    private static int[] textureID = new int[MODEL_COUNT];
    private static float[] xPos = new float[MODEL_COUNT]; // 各モデルのX座標
    private static float[] yPos = new float[MODEL_COUNT]; // 各モデルのy座標
    private static float[] angles = new float[MODEL_COUNT]; // 各モデルの回転角度

    public static void main(String[] args) {
        setupWindow();
        loader = new OBJLoader();

        try {
            // 9個のOBJモデルをロード
            for (int i = 0; i < MODEL_COUNT; i++) {
                models[i] = loader.loadModel(new File("obj/" + (i + 1) + ".obj"));
                textureID[i] = loader.loadTexture("/resource/" + (i + 1) + ".png");
                xPos[i] = (i - 4) * 0.2f; // -0.8 ~ 0.8 に配置
                angles[i] = i * 10.0f; // 各モデルの初期角度
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!Display.isCloseRequested()) {
            update();
            render();
            Display.update();
        }

        Display.destroy();
    }

    private static void setupWindow() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.setTitle("Multiple Moving OBJ - LWJGL 2");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(1);
        }

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(-1, 1, -1, 1, -1, 1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    private static void update() {
        for (int i = 0; i < MODEL_COUNT; i++) {
            // 各モデルの移動
            xPos[i] += 0.005f * (i % 3 - 1); // -0.005, 0.0, 0.005 の3パターン
            if (xPos[i] > 0.8f)
                xPos[i] = -0.8f;
            if (xPos[i] < -0.8f)
                xPos[i] = 0.8f;

            // 各モデルの回転
            angles[i] += 1.0f + (i % 3) * 0.5f; // 1.0, 1.5, 2.0 の3パターン
        }
    }

    private static void render() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glLoadIdentity();

        for (int i = 0; i < MODEL_COUNT; i++) {
            GL11.glPushMatrix();
            GL11.glTranslatef(xPos[i], (i % 3 - 1) * 0.2f, -1.0f); // 3×3配置
            GL11.glRotatef(angles[i], 0.0f, 1.0f, 0.0f);
            loader.render(models[i], textureID[i]);
            GL11.glPopMatrix();
        }
    }
}
