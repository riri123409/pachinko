/*
 * その他当たりの時
 */
public class Hit {
    public void Hit_lottery() {
        Set_randam rand = new Set_randam();
        int i = rand.set_randam(3);

        if (i < 1) {
            first_Direction();
        } else if (i < 2) {
            second_Direction();
        } else {
            third_Direction();
        }
    }

    private void first_Direction() {

    }

    private void second_Direction() {

    }

    private void third_Direction() {

    }

}
