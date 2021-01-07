package ru.pvolan.sim2.program;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;


import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageSwitcher;

import androidx.core.content.ContextCompat;

import ru.pvolan.sim2.MainActivity;
import ru.pvolan.sim2.ProgramBase;
import ru.pvolan.sim2.R;

import static android.os.Build.VERSION_CODES.R;

public class Program extends ProgramBase {

    //You can add your fields and methods to this class
    //You can also remove some methods listed below, if you don't need them (inheritance magic is used here)

    TreeNode root; //Дерево
    Woodpecker woodpecker; //Дятел

    private Paint woodpeckerPaint;
    private Paint treeNodePaint;
    private Paint bugPaint;
    private Paint countTextPaint;

    private Context context;



    public void init() {
        //Perform initialization here
        super.init();

        root = new TreeNode(500, 100);
        root.setBugsCount(8);
        root.setTwoSubTree();

        TreeNode left = root.getLeftSubTree();
        left.setBugsCount(4);
        left.generateLeftSide(left.setLeftSubTree(), 3);

        TreeNode leftLeft = left.getLeftSubTree();
        leftLeft.setBugsCount(8);

        TreeNode right = root.getRightSubTree();
        right.setBugsCount(6);
        TreeNode rightLeft = right.setLeftSubTree();
        rightLeft.setBugsCount(3);

        TreeNode rightRight = right.setRightSubTree();
        right.generateRightSide(rightRight, 3);
        rightRight.setBugsCount(4);

        TreeNode rightRightRight = rightRight.setRightSubTree();
        rightRightRight.setBugsCount(5);

        rightRight.generateLeftSide(rightRightRight, 3);

        TreeNode rightLeftLeft = rightLeft.setLeftSubTree();
        rightLeft.generateLeftSide(rightLeftLeft, 3);
        rightLeftLeft.setBugsCount(9);


        woodpecker = new Woodpecker();
        woodpecker.setCurrentPosition(root);

        woodpeckerPaint = new Paint();
        woodpeckerPaint.setAntiAlias(true);
        woodpeckerPaint.setColor(Color.rgb(255, 0, 0));
        woodpeckerPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        woodpeckerPaint.setFilterBitmap(true);


        treeNodePaint = new Paint();
        treeNodePaint.setAntiAlias(true);
        treeNodePaint.setColor(Color.rgb(101, 0, 0));
        treeNodePaint.setStyle(Paint.Style.FILL_AND_STROKE);


        bugPaint = new Paint();
        bugPaint.setAntiAlias(true);
        bugPaint.setColor(Color.rgb(0,0,255));
        bugPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        bugPaint.setTextSize(72);

        countTextPaint = new Paint();
        countTextPaint.setAntiAlias(true);
        countTextPaint.setColor(Color.rgb(0,0,0));
        countTextPaint.setStyle(Paint.Style.FILL);
        countTextPaint.setTextSize(62);
    }

    public void onDraw(Canvas canvas) {
        /**
         * Draw here.
         * Use canvas to draw on.
         * This method is called automatically after redraw() method has been called. But note that exact time WHEN this method is called is quite indefinite.
         *
         *
         * See https://developer.android.com/reference/android/graphics/Canvas?hl=en to know how to use Canvas class
         * For the first time, take a look into drawLine(), drawRect(), drawText() methods
         *
         * Note the getWidth() and getHeight() methods.
         * Please note that width and height may change over time
         */

        showCountAteBugs(canvas, woodpecker, countTextPaint);


        canvas.drawCircle(root.getX(), root.getY(), 50, treeNodePaint);
        drawBugs(canvas, root, treeNodePaint);

        drawTree(canvas, root, treeNodePaint, bugPaint);

        drawWoodpecker(canvas, woodpeckerPaint, woodpecker.getX()+50, woodpecker.getY(), 100);

    }


    public void onCommandRun(String command) {
        //This method is called when button "Run" is clicked.
        //Command is passed as a parameter

        //Use redraw() method when you need to update your output.
        redraw();

        //Use redraw() method when you want to show a floating popup with a short string message
        showToast("Command " + command);
    }


    public void onButtonAClick() {
        //This method is called when button "A" is clicked.
        //Same rules as for onCommandRun() method are applied
        woodpecker.eatABug(woodpecker.getCurrentPosition());
        redraw();
    }


    public void onButtonBClick() {
        //This method is called when button "B" is clicked.
        //Same rules as for onCommandRun() method are applied
        woodpecker.eatABug(woodpecker.getCurrentPosition());
        redraw();
    }


    public void onButtonUpClick() {
        //This method is called when button "Up" is clicked.
        //Same rules as for onCommandRun() method are applied
        woodpecker.setCurrentPosition(root);
        redraw();
    }


    public void onButtonDownClick() {
        //This method is called when button "Down" is clicked.
        //Same rules as for onCommandRun() method are applied
        woodpecker.setCurrentPosition(root);
        redraw();
    }


    public void onButtonLeftClick() {
        //This method is called when button "Left" is clicked.
        //Same rules as for onCommandRun() method are applied
        TreeNode wpPosition = woodpecker.getCurrentPosition();
        if (wpPosition.getLeftSubTree() != null) {
            woodpecker.setCurrentPosition(wpPosition.getLeftSubTree());
            redraw();
        } else {
            showToast("Нажмите вверх, чтобы перелететь к корню дерева.");
        }
    }


    public void onButtonRightClick() {
        //This method is called when button "Right" is clicked.
        //Same rules as for onCommandRun() method are applied
        TreeNode wpPosition = woodpecker.getCurrentPosition();
        if (wpPosition.getRightSubTree() != null) {
            woodpecker.setCurrentPosition(wpPosition.getRightSubTree());
            redraw();
        }else {
            showToast("Нажмите вверх, чтобы перелететь к корню дерева.");
        }
    }


    public void drawWoodpecker(Canvas canvas, Paint paint, int x, int y, int width) {
        int halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(x + halfWidth, y - halfWidth); // Top
        path.lineTo(x - halfWidth, y); // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
        path.lineTo(x + halfWidth, y - halfWidth); // Back to Top
        path.close();
        canvas.drawPath(path, paint);
    }

    public void drawTree(Canvas canvas, TreeNode root, Paint treePaint, Paint bugsPaint){

        if (TreeNode.isBinary(root)) {

            treePaint.setColor(Color.rgb(91, 0, 0));
            canvas.drawCircle(root.getX(), root.getY(), 40, treePaint);
            drawBugs(canvas, root, bugsPaint);

            Paint linePaint = new Paint();
            linePaint.setAntiAlias(true);
            linePaint.setColor(Color.rgb(0,0,0));
            linePaint.setStyle(Paint.Style.FILL);

            if (TreeNode.hasLeftSubTree(root)) {

                TreeNode left = root.getLeftSubTree();
                canvas.drawCircle(left.getX(), left.getY(), 40, treePaint);
                drawBugs(canvas, root, bugsPaint);
                drawTree(canvas, left, treePaint, bugsPaint);
                canvas.drawLine(root.getX(), root.getY(), left.getX(), left.getY(), linePaint);
            }
            if (TreeNode.hasRightSubTree(root)) {
                TreeNode right = root.getRightSubTree();
                canvas.drawCircle(right.getX(), right.getY(), 40, treePaint);
                drawBugs(canvas, root, bugsPaint);
                drawTree(canvas, right, treePaint, bugsPaint);

                canvas.drawLine(root.getX(), root.getY(), right.getX(), right.getY(), linePaint);
            }
        } else {
            treePaint.setColor(Color.rgb(0, 150, 0));
            canvas.drawCircle(root.getX(), root.getY(), 40, treePaint);
            drawBugs(canvas, root, bugsPaint);
        }
    }

    public void drawBugs(Canvas canvas, TreeNode node, Paint paint){
        if (node.getBugsCount() > 0) {
            int paddingX = -35;
            canvas.drawText(Integer.toString(node.getBugsCount()), node.getX()+paddingX, node.getY()+20, paint);
        }
    }

    public void showCountAteBugs(Canvas canvas, Woodpecker woodpecker, Paint paint){
        String ateBugsCount = "Cчет: " + Integer.toString(woodpecker.getBugsEatenCount());
        canvas.drawText(ateBugsCount, 100, 50, paint);
    }
}
