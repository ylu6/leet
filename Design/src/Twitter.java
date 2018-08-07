import java.util.*;

/**
 * Created by yeqing on 7/6/2017.
 */
public class Twitter {
    /** Initialize your data structure here. */
    int timestamp;
    Map<Integer, User> userMap;

    // Tweet user
    class User {
        public int id;
        public Set<Integer> followed; // Tweet users this user is following
        public Tweet tweetHead;
        public User(int id) {
            this.id = id;
            followed = new HashSet<Integer>();
            followed.add(id); // user always follow himself
            tweetHead = null;
        }
        public void post(int tweetId) {
            Tweet t = new Tweet(tweetId);
            t.next = tweetHead;
            tweetHead = t;
        }
        public void follow(int tweetId) {
            followed.add(tweetId);
        }
        public void unfollow(int tweetId) {
            followed.remove(tweetId);
        }
    }
    class Tweet {
        public int id;
        public int time;
        public Tweet next;
        public Tweet(int tweetId) {
            id = tweetId;
            time = timestamp++; // every time a new tweet was created, increase timestamp
            next = null;
        }
    }

    public Twitter() {
        timestamp = 0;
        userMap = new HashMap<Integer, User>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId))
            userMap.put(userId, new User(userId));
        userMap.get(userId).post(tweetId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    // get 10 largest values from a list of LinkedList
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new ArrayList<Integer>();
        if (!userMap.containsKey(userId)) return newsFeed;
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a,b)->b.time-a.time); // maxPQ
        Tweet latest;
        for (int id : userMap.get(userId).followed) {
            latest = userMap.get(id).tweetHead;
            if (latest != null) pq.add(latest);
        }


        if (pq.size() == 0) return newsFeed;

        for (int i = 0; i < 10 && pq.size() > 0; i++) {
            latest = pq.poll();
            newsFeed.add(latest.id);
            if (latest.next != null) pq.add(latest.next);
        }
        return newsFeed;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followeeId))
            userMap.put(followeeId, new User(followeeId));
        if(!userMap.containsKey(followerId))
            userMap.put(followerId, new User(followerId));

        userMap.get(followerId).follow(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followeeId) || followeeId == followerId)
            return;
        userMap.get(followerId).unfollow(followeeId);
    }

    public static void main(String[] args) {
        Twitter t = new Twitter();
        t.postTweet(1,5);
        System.out.println(t.getNewsFeed(1).toString());
        t.follow(1,2);
    }
}

