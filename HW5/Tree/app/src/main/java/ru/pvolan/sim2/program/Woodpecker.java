package ru.pvolan.sim2.program;

public class Woodpecker {
    private int bugsEatenCount; //Кол­во съеденных дятлом жучков
    private TreeNode currentPosition; //Текущее местонахождение дятла

    public Woodpecker() {
        this.bugsEatenCount = 0; //default
    }

    public int getX() {
        return currentPosition.getX();
    }

    public int getY() {
        return currentPosition.getY();
    }

    public void eatABug(TreeNode node) {

        if (node.getBugsCount() != 0) {
            node.delBug(node);
            bugsEatenCount += 1;
        }
    }

    public void setCurrentPosition(TreeNode node) {
        this.currentPosition = node;
    }

    public TreeNode getCurrentPosition() {
        return currentPosition;
    }

    public int getBugsEatenCount() {
        return bugsEatenCount;
    }
}

