import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.JPanel;

public class Panel extends JPanel {
    final int maxCol = 30;
    final int maxRow = 20;
    final int nodeSize = 70;
    final int screenWidth = 1050;
    final int screenHeight = 700;

    //NODE
    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    ArrayList<Node>openList = new ArrayList<>();
    ArrayList<Node>checkedList = new ArrayList<>();


    boolean goalReached = false;

    // flag for preventing an infinite loop while searching nodes
    int step = 0;

    public Panel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);



        int col = 0;
        int row = 0;

        while(col < maxCol && row < maxRow) {
            this.node[col][row] = new Node(col, row);
            this.add(this.node[col][row]);
            ++col;
            if (col == maxCol) {
                col = 0;
                ++row;
            }
        }

        this.setStartNode(0, 0);

        this.setGoalNode(29, 5);

        this.setSolidNode(10, 2);
        this.setSolidNode(10, 3);
        this.setSolidNode(10, 4);
        this.setSolidNode(10, 5);
        this.setSolidNode(10, 6);
        this.setSolidNode(6, 2);
        this.setSolidNode(6, 1);
        this.setSolidNode(7, 2);
        this.setSolidNode(8, 2);
        this.setSolidNode(9, 2);
        this.setSolidNode(10, 7);
        this.setSolidNode(11, 7);
        this.setSolidNode(12, 7);

        this.setCostOnNodes();
    }

    private void setStartNode(int col, int row) {
        this.node[col][row].setAsStart();
        this.startNode = this.node[col][row];
        this.currentNode = this.startNode;
    }

    private void setGoalNode(int col, int row) {
        this.node[col][row].setAsGoal();
        this.goalNode = this.node[col][row];
    }

    private void setSolidNode(int col, int row) {
        this.node[col][row].setAsSolid();
    }

    private void getCost(Node node) {
        int xDistance = Math.abs(node.col - this.startNode.col);
        int yDistcance = Math.abs(node.row - this.startNode.row);
        node.gCost = xDistance + yDistcance;
        xDistance = Math.abs(node.col - this.goalNode.col);
        yDistcance = Math.abs(node.row - this.goalNode.row);
        node.hCost = xDistance + yDistcance;
        node.fCost = node.hCost + node.gCost;
        if (node != this.startNode && node != this.goalNode) {
            node.setText("<html>F: " + node.fCost + "<br>G: " + node.gCost + "</html>");
        }

    }

    private void setCostOnNodes() {
        int col = 0;
        int row = 0;

        while(col < maxCol && row < maxRow) {
            this.getCost(this.node[col][row]);
            ++col;
            if (col == maxCol) {
                col = 0;
                ++row;
            }
        }

    }


    public void search(){

        if(!goalReached && step < 300){
             int col = currentNode.col;
             int row = currentNode.row;

             currentNode.setAsChecked();
             checkedList.add(currentNode);
             openList.remove(currentNode);

              // all these if statements are for avoiding nullPointerException when the node
            //is in one extreme corner

             if(row - 1 >= 0){

                 //OPen the up node
                 openNode(node[col][row - 1]);
             }

            if(col -1 >= 0){
                //open the left node
                openNode(node[col - 1][row]);
            }

             if(row + 1 < maxRow){
                 //open the down node
                 openNode(node[col][row + 1]);
             }

             if(col + 1 < maxCol){
                 //open the right node
                 openNode(node[col + 1][row]);
             }

             // FIND THE BEST NODES
            int bestNodeIndex = 0;
             int bestNodeFCost = 999;

             for(int i = 0; i < openList.size(); i++){

                 //check if the node's fCost is better
                 if(openList.get(i).fCost < bestNodeFCost){
                     bestNodeIndex = i;
                     bestNodeFCost = openList.get(i).fCost;
                     
                 }
                 //if fCost is equal we use gCost
                 else if(openList.get(i).fCost == bestNodeFCost){
                     if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                         bestNodeIndex = i;
                     }
                 }
             }

            // after the loop we get the best node wich is our step
            currentNode = openList.get(bestNodeIndex);

             if(currentNode == goalNode){
                 goalReached = true;
             }
        }
        step++;
    }


    //automated search
    public void autoSearch(){

        while(!goalReached){
            int col = currentNode.col;
            int row = currentNode.row;

            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);

            // all these if statements are for avoiding nullPointerException when the node
            //is in one extreme corner

            if(row - 1 >= 0){

                //OPen the up node
                openNode(node[col][row - 1]);
            }

            if(col -1 >= 0){
                //open the left node
                openNode(node[col - 1][row]);
            }

            if(row + 1 < maxRow){
                //open the down node
                openNode(node[col][row + 1]);
            }

            if(col + 1 < maxCol){
                //open the right node
                openNode(node[col + 1][row]);
            }

            // FIND THE BEST NODES
            int bestNodeIndex = 0;
            int bestNodeFCost = 999;

            for(int i = 0; i < openList.size(); i++){

                //check if the node's fCost is better
                if(openList.get(i).fCost < bestNodeFCost){
                    bestNodeIndex = i;
                    bestNodeFCost = openList.get(i).fCost;

                }
                //if fCost is equal we use gCost
                else if(openList.get(i).fCost == bestNodeFCost){
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost){
                        bestNodeIndex = i;
                    }
                }
            }

            // after the loop we get the best node wich is our step
            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode){
                goalReached = true;
                trackThePath();
            }
        }
        step++;
    }



    private void openNode(Node node){

        if(!node.open && !node.checked && !node.solid){
            
            //if the node is not open, yet we added to the open list
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);

        }
    }

    private void trackThePath(){
        //back track and mark the best path

        //start from the end
        Node current = goalNode;

        while(current != startNode){

            current = current.parent;
            if(current != startNode){
                current.setAsPath();
            }


        }


    }

}
