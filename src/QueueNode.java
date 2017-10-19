import java.util.concurrent.ThreadLocalRandom;

public class QueueNode implements Comparable<QueueNode> {
    private String name;
    private float val;

    public QueueNode(String name, int val) {
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
            return 1;
        }
        else if (val > comparedNode.val) {
            return -1;
        }

        // If two values are equal
        else {
            return ThreadLocalRandom.current().nextInt(-1, 2);
        }
    }
}
