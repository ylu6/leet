import java.util.*;

public class SimilarUsers {
    String findSimilarUser(List<List<String>> userContentLikes, String user){
        Set<String> siteSet = new HashSet<>(); // list of site this 'user' likes
        Map<String, Set<String>> map = new HashMap<>(); // {site: list of users who like this site}
        for(List<String> list : userContentLikes) {
            String u = list.get(1);
            String site = list.get(0);
            map.putIfAbsent(site, new HashSet<>());
            map.get(site).add(u);
            if(user.equals(u)) siteSet.add(site);
        }

        Map<String, Integer> score = new HashMap<>();
        int maxScore = 0;
        String similar = "";
        for(String site : siteSet) {
            for(String u : map.get(site)) {
                if(!u.equals(user)) {
                    score.put(u, score.getOrDefault(u, 0) + 1);
                    if(score.get(u) > maxScore) {
                        maxScore = score.get(u);
                        similar = u;
                    }
                }
            }
        }
        return similar;
    }

    public static void main(String[] args) {
        List<List<String>> userContentLikes = new ArrayList<>();
        String[][] arrs = {
                {"http://yahoo.com", "user3", "201999"},
                {"http://google.com/maps", "user2", "201004"},
                {"http://google.com/maps", "user1", "201015"},
                {"http://google.com", "user4", "201004"},
                {"http://google.com", "user2", "201012"},
                {"http://google.com/maps", "user2", "201008"},
                {"http://google.com/maps", "user2", "201013"},
                {"http://google.com/maps", "user2", "201030"},
                {"http://altavista.net/q?f=12344", "user3", "100002"},
                {"http://google.com/maps", "user3", "201015"},
                {"http://yahoo.com", "user2", "201035"},
                {"http://altavista.net/q?f=12344", "user1", "99999"},
                {"http://altavista.net/q?f=12344", "user1", "100004"},
                {"http://geocities.com", "user1", "100007"},
                {"http://geocities.com", "user3", "100009"}
        };
        for(String[] arr : arrs) {
            userContentLikes.add(Arrays.asList(arr));
        }

        SimilarUsers sol = new SimilarUsers();
        System.out.println(sol.findSimilarUser(userContentLikes, "user1"));
        System.out.println(sol.findSimilarUser(userContentLikes, "user2"));
        System.out.println(sol.findSimilarUser(userContentLikes, "user3"));
        System.out.println(sol.findSimilarUser(userContentLikes, "user4"));
    }
}

/*
We are building a social network where users can like and share content from the web.. 留学申请论坛-一亩三分地

Given a list of users and content that each user liked, find the user most similar to User X. "Most similar to X" means the person who liked the greatest number of unique pieces of content that User X also liked.

For example, User1 liked 3 distinct pages. User3 liked all 3 of those pages, more than anyone else, making them the most similar user to User1.

Sample input:

# Content, user ID, timestamp
user_content_likes = [
    ["http://yahoo.com", "user3", "201999"],
    ["http://google.com/maps", "user2", "201004"],
    ["http://google.com/maps", "user1", "201015"],
    ["http://google.com", "user4", "201004"],
    ["http://google.com", "user2", "201012"],
    ["http://google.com/maps", "user2", "201008"],
    ["http://google.com/maps", "user2", "201013"],
    ["http://google.com/maps", "user2", "201030"],
    ["http://altavista.net/q?f=12344", "user3", "100002"],
    ["http://google.com/maps", "user3", "201015"],
    ["http://yahoo.com", "user2", "201035"],
    ["http://altavista.net/q?f=12344", "user1", "99999"],
    ["http://altavista.net/q?f=12344", "user1", "100004"],
    ["http://geocities.com", "user1", "100007"],
    ["http://geocities.com", "user3", "100009"]]

Expected output:. more info on 1point3acres


find_similar_user(user_content_likes, "user1") => user3
find_similar_user(user_content_likes, "user2") => user3
find_similar_user(user_content_likes, "user3") => user1. Waral 博客有更多文章,
find_similar_user(user_content_likes, "user4") => user2

Expected output (camelCase):

findSimilarUser(userContentLikes, "user1") => user3
findSimilarUser(userContentLikes, "user2") => user3
findSimilarUser(userContentLikes, "user3") => user1
findSimilarUser(userContentLikes, "user4") => user2
 */
