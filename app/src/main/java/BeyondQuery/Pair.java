package BeyondQuery;

public class Pair implements Comparable<Pair> {
	
    public final int index;
    public final double value;

    public Pair(int index, double weightOfItems) {
        this.index = index;
        this.value = weightOfItems;
    }

    @Override
    public int compareTo(Pair other) {
        //multiplied to -1 as the author need descending sort order
        return -1 * Double.valueOf(this.value).compareTo(other.value);
    }
}
