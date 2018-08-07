public class PopulatingNextRightPointersInEachNode {
    public void connect(TreeLinkNode root) {
        if(root == null || root.left == null) return;
        TreeLinkNode levelHeader = root;
        while(levelHeader != null && levelHeader.left != null) {
            for(TreeLinkNode cur = levelHeader; cur != null; cur = cur.next) {
                cur.left.next = cur.right;
                if(cur.next != null) cur.right.next = cur.next.left;
            }
            levelHeader = levelHeader.left;
        }
    }
}

 class TreeLinkNode {
    int val;
    TreeLinkNode left, right, next;
    TreeLinkNode(int x) { val = x; }
  }
