public class Slotnumber {

    private int[] number;

    public int[] hit() 
    {
        Set_randam rand = new Set_randam();
        this.number = new int[3];
        this.number[0] = rand.set_randam(9);
        this.number[1] = this.number[0];
        this.number[2] = this.number[0];
        return number;
    }

    public int[] reach() 
    {
        Set_randam rand = new Set_randam();
        this.number = new int[3];
        this.number[0] = rand.set_randam(9);
        while(true)
        {
            this.number[1] = rand.set_randam(9);
            if (this.number[1] != this.number[0]) {
                break;
            }
        }
        this.number[2] = this.number[0];
        return number;
    }

    public int[] miss()
    {
        Set_randam rand = new Set_randam();
        this.number = new int[3];
        this.number[0] = rand.set_randam(9);
        while(true)
        {
            this.number[1] = rand.set_randam(9);
            if (this.number[1] != this.number[0]) {
                break;
            }
        }
        while(true)
        {
            this.number[2] = rand.set_randam(9);
            if (this.number[2] != this.number[0]) {
                break;
            }
        }
        return number;
    }

}