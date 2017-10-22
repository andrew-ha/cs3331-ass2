import java.util.Random;

public class QueueNode implements Comparable<QueueNode> {
    private String name;
    private float val;

    public QueueNode(String name, float val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }

    @Override
    public int compareTo(QueueNode comparedNode) {
        if (val < comparedNode.val) {
            return -1;
        }
        else if (val > comparedNode.val) {
            return 1;
        }

        // If two values are equal assign them at random
        // picks a random int between -10 and 10 (upper bound is exclusive)
        else {
            Random randomGenerator = new Random();
            System.out.println(randomGenerator.nextBoolean());
            if (randomGenerator.nextBoolean()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
