public class TeemoAttacking {
    // use int prv to keep track of time of previous attack
    // if cur_time - prv >= duration, it was poisoned for the whole duration
    // otherwise, previous attack only poisoned for cur_time - prv_time
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if(timeSeries == null || timeSeries.length == 0) return 0;
        int time = 0, prv = timeSeries[0];

        for(int t : timeSeries) {
            if(prv + duration < t) time += duration;
            else time += t - prv;
            prv = t;
        }
        time += duration;
        return time;
    }
}
