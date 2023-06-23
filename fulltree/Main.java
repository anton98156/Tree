package fulltree;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<Integer>();
        
        tree.add(4);
        tree.add(6);
        tree.add(8);
        tree.add(10);
        
        System.out.println(tree.contain(4));
        System.out.println(tree.contain(7));
    }
}
