import java.util.BitSet;
import java.util.Random;

public interface ExpiredJobId {
    void expire(long jobId);
    boolean isExpired(long jobId);
}

class ExpiredJobIdImpl implements ExpiredJobId{
    private final Long numOfId = (long) 4e9;
    BitSetLong bitset = new BitSetLong(numOfId);

    @Override
    public void expire(long jobId) {
        bitset.set(jobId);
    }

    @Override
    public boolean isExpired(long jobId) {
        return bitset.get(jobId);
    }

    public static void main(String[] args) {
        ExpiredJobId sol = new ExpiredJobIdImpl();
        for(long i = 0L; i < (long)4e9; i++) {
            sol.expire(i);
        }
    }
}


