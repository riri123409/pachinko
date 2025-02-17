public class Lottery {
    public void lottery() {
        Set_randam randam = new Set_randam();
        int i = randam.set_randam(65536);

        // 青 3%
        // 緑 4％
        // 赤 91%
        // 虹 100%
        // その他
        if (i < 205) {

        } else if (i < 1638) {// 青

        } else if (i < 2949) {// 緑

        } else if (i < 3008) {// 赤

        } else if (i < 3047) {// 虹

        } else {

        }

    }

}
