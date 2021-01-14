package ru.pvolan.sim2.program;

public class TreeNode {

    private int bugsCount; //Кол­во жучков, оставшихся в этом узле
    private TreeNode leftSubTree; //Ссылка на левое поддерево
    private TreeNode rightSubTree; //Ссылка на правое поддерево

    private int x;
    private int y;

    public TreeNode(int x, int y){
        this.x = x;
        this.y = y;
    }

    public TreeNode() {
        this.bugsCount = 0; //default
    }


    public void setBugsCount(int count){

        this.bugsCount = count;
    }

    public int getBugsCount() {
        return bugsCount;
    }

    public void setTwoSubTree(){
        this.setLeftSubTree();
        this.setRightSubTree();
    }


    public TreeNode getLeftSubTree(){
        return leftSubTree;
    }

    public TreeNode getRightSubTree() {
        return rightSubTree;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // woodpecker ate a bug

    public void delBug(TreeNode node){
        bugsCount = node.bugsCount - 1;
    }
    public static boolean isBinary(TreeNode node){
        if (node.rightSubTree!= null || node.leftSubTree != null) return true;
        else return false;

    }

    //it has a left subtree
    public static boolean hasLeftSubTree(TreeNode node){
        if (node.leftSubTree != null) return true;
        else return false;
    }

    //it has a right subtree
    public static boolean hasRightSubTree(TreeNode node){
        if (node.rightSubTree!= null) return true;
        else return false;
    }

    public TreeNode setLeftSubTree(){

        this.leftSubTree = new TreeNode();
        this.leftSubTree.x = this.x - 100;
        this.leftSubTree.y = this.y + 100;
        return leftSubTree;
    }

    public TreeNode setRightSubTree(){

        this.rightSubTree = new TreeNode();
        this.rightSubTree.x = this.x + 100;
        this.rightSubTree.y = this.y + 100;
        return rightSubTree;
    }

    public void generateRightSide(TreeNode right, int level){
        int step = this.x;
        for (int i = 0; i < level; i++){
            step /= 2;
        }

        TreeNode rightRight = right.setRightSubTree();
        rightRight.x = right.x + step;
        TreeNode rightLeft = right.setLeftSubTree();
        rightLeft.x = right.x - step;
    }

    public void generateLeftSide(TreeNode left, int level){
        int step = this.x;
        for (int i = 0; i < level; i++){
            step /= 2;
        }
        TreeNode leftRight = left.setRightSubTree();
        leftRight.x = left.x + step;
        TreeNode leftLeft = left.setLeftSubTree();
        leftLeft.x = left.x - step;
    }
    
}
