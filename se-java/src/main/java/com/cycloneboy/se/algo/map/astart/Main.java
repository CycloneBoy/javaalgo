package com.cycloneboy.se.algo.map.astart;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Created by CycloneBoy on 2017/10/13.
 */
public class Main extends JPanel{
    private final static int DEFAULT_WIDTH=480;
    private final static int DEFAULT_HEIGHT=480;
    private final static int CS=32;
    final static  private  long serialVersionUID = 1L;
    //private JFrame frame;
    private List path;
    //private static final int CS=32;
    public static final int []HIT={1};   //the area of which movement is forbidden
    private static final int DEFAULT_ROW=15;   //the default rows and collides of the Map
    private static final int DEFAULT_COL=15;
    private Image backgroundImage;
    private Image wallImage;

    //private Graphics graphics;
    private static Image image=null;
    private static Point START_POS=new Point(1,1);//the beginning points of the finding
    private static Point OBJECT_POS = new Point(10, 13);//the destination points
    // private CreateMap map;
    private PathFinding astar;
    static final int [][]MAP=
            {
                    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
                    { 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1 },
                    { 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
                    { 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1 },
                    { 1, 0, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1 },
                    { 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1 },
                    { 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                    { 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
                    { 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1 },
                    { 1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 1 },
                    { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                    { 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1 },
                    { 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 0, 1, 1 },
                    { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1 },
                    { 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1 }
            };

    public Main(Image image)
    {
        this.image=image;
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setFocusable(true);
        //screen = new Image(WIDTH, HEIGHT).getImage();
        //screen=createImage();
        //this.set
        // graphics = image.getGraphics();
        backgroundImage=new ImageIcon("fghyuj.png").getImage();
        ImageIcon imageicon=new ImageIcon("fghyuj.png");
        wallImage=new ImageIcon("artwer.png").getImage();
        ImageIcon imageicon1=new ImageIcon("artwer.png");
        //map=new CreateMap();
        astar=new PathFinding(MAP,HIT);
        path=astar.searchPath(START_POS, OBJECT_POS);
    }
    // public void update(Graphics g)
// {
//  paint(g);
// }
    protected void paintComponent(Graphics g){
        // draw the map
        // map.paintComponent(graphics);
        g.setColor(Color.RED);
        g.fillRect(START_POS.x * CS, START_POS.y * CS, CS, CS);
        System.out.println("asc");
        g.setColor(Color.BLUE);
        g.fillRect(OBJECT_POS.x * CS, OBJECT_POS.y * CS, CS, CS);
        for(int i=0;i<DEFAULT_ROW;i++)
        {
            for(int j=0;j<DEFAULT_COL;j++)
            {
                switch(MAP[i][j]){
                    case 0:
                        //g.drawImage(backgroundImage,j*CS, i*CS, this);
                        g.drawImage(backgroundImage, j*CS, i*CS, null);
                        break;
                    case 1:
                        g.drawImage(wallImage,j*CS, i*CS, null);
                        break;
                }

            }
        }
        // draw the path
        if (path != null){
            g.setColor(Color.YELLOW);
            //visit the position，describe the path
            for (int i = 0; i < path.size(); i++){
                Node node = (Node) path.get(i);
                Point pos = node._Pos;
                // draw the frame
                g.fillRect(pos.x * CS + 7, pos.y * CS + 7, CS - 14,
                        CS - 14);
            }
        }
        //  g.drawImage(image, 0, 0, this);
    }

}
