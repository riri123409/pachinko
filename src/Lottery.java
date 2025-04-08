/*
 * 抽選
 */
public class Lottery {
    public void lottery(Slot slot) {
        Set_randam randam = new Set_randam();
        int i = randam.set_randam(65536);
        int[] number;
        System.out.printf("i:%d \n", i);

        // 青 3%
        // 緑 4％
        // 赤 91%
        // 虹 100%
        // その他
        if (i < 205) {
            Slotnumber slotnumber = new Slotnumber();
            number = slotnumber.hit();
            System.out.printf("%d %d %d",number[0]+1,number[1]+1,number[2]+1);
        } else if (i < 3047) {
            Slotnumber slotnumber = new Slotnumber();
            number = slotnumber.reach();
            System.out.printf("%d %d %d",number[0]+1,number[1]+1,number[2]+1);
            if (i < 1638) {// 青
            } else if (i < 2949) {// 緑
            } else if (i < 3008) {// 赤
            } else {// 虹
            }
        }else {
            Slotnumber slotnumber = new Slotnumber();
            number = slotnumber.miss();
            System.out.printf("%d %d %d",number[0]+1,number[1]+1,number[2]+1);
        }

        ObjTransform[] objects = {
            new ObjTransform(0.2f, 0, 0, 0, number[0]),
            new ObjTransform(0f, 0, 0, 0, number[1]),
            new ObjTransform(-0.2f, 0, 0, 0, number[2])
        };

    }

}
