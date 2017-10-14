package com.cycloneboy.se.algo.map;


/**
 * 邻接矩阵实现的无向图
 * Created by CycloneBoy on 2017/10/13.
 */
public class MatrixUDG {

    private int mEdgNum ;       //边的数量
    private char[] mVexs;       //顶点集合
    private int[][] mMatrix;    // 领接矩阵
    private static final int INF = Integer.MAX_VALUE; // 最大值

    /**
     * 创建图(用已提供的矩阵)
     * @param vexs      -- 顶点数组
     * @param matrix    -- 矩阵(数据)
     */
    public MatrixUDG(char[] vexs,int[][] matrix){

        // 初始化"顶点数"和"边数"
        int vlen= vexs.length;

        // 初始化"顶点"
        mVexs = new char[vlen];
        for(int i = 0; i < mVexs.length;i++){
            mVexs[i] = vexs[i];
        }

        // 初始化"边"
        mMatrix = new int[vlen][vlen];
        for(int i =0 ;i < vlen;i++){
            for(int j = 0;j < vlen;j++){
                mMatrix[i][j] = matrix[i][j];
            }
        }

        // 统计"边"的数目
        mEdgNum = 0;
        for(int i = 0;i<vlen;i++){
            for(int j=0; j < vlen;j++){
                if(mMatrix[i][j] !=INF){
                    mEdgNum++;
                }
            }
        }
    }

    /**
     * 返回顶点v的第一个领接顶点的索引，失败则返回-1
     * @param v
     * @return
     */
    private int firstVertex(int v){
        if(v < 0 || v >(mVexs.length - 1)){
            return -1;
        }

        for(int i = 0; i < mVexs.length; i++){
            if(mMatrix[v][i]!= 0 && mMatrix[v][i] != INF){
                return  i;
            }
        }

        return -1;
    }

    /**
     * 返回顶点v相对于w的下一个领接顶点的索引，失败则返回-1
     * @param v
     * @param w
     * @return
     */
    private int nextVertex(int v,int w){

        if(v< 0 || v >(mVexs.length -1) || w < 0 || w > (mVexs.length -1)){
            return  -1;
        }

        for(int i = w+1;i<mVexs.length;i++){
            if(mMatrix[v][i]!= 0 && mMatrix[v][i] != INF){
                return  i;
            }
        }
        return -1;
    }

    /**
     * 深度优先搜索遍历图的递归实现
     */
    private void DFS(int i,boolean[] visited){

        visited[i] = true;
        System.out.printf("%c ",mVexs[i]);
        // 遍历该顶点的所哟领接顶点，若没有访问过，那么继续往下走
        for(int w = firstVertex(i);w >=0; w = nextVertex(i,w)){
            if(!visited[w]){
                DFS(w,visited);
            }
        }
    }

    /**
     * 深度优先搜索遍历图
     */
    public void DFS(){
        boolean[] visited = new boolean[mVexs.length]; // 顶点访问标记

        // 初始化所有顶点都没有被访问
        for(int i = 0; i < mVexs.length;i++){
            visited[i] = false;
        }

        System.out.println("DFS: ");
        for(int i = 0; i < mVexs.length;i++){
            if(!visited[i]){
                DFS(i,visited);
            }
        }
        System.out.println();
    }

    /**
     * 广度优先搜索（类似于树的层次遍历)
     */
    public void BFS(){
        int head = 0;
        int rear = 0;
        int[] queue = new int[mVexs.length];    //辅助队列
        boolean[] visited = new boolean[mVexs.length]; //顶点访问标记

        for(int i = 0; i < mVexs.length;i++){
            visited[i] = false;
        }

        System.out.println("BFS: ");
        for(int i = 0; i < mVexs.length;i++){
            if(!visited[i]){
                visited[i] = true;
                System.out.printf("%c ",mVexs[i]);
                queue[rear++] = i; //入队列
            }

            while(head != rear){
                int j = queue[head++]; // 出队列
                for(int k = firstVertex(j); k >=0; k =nextVertex(j,k)){ //k是为访问的邻接顶点
                    if(!visited[k]){
                        visited[k] = true;
                        System.out.printf("%c ",mVexs[k]);
                        queue[rear++] = k;
                    }
                }
            }
        }
        System.out.printf("\n");
    }

    /**
     * Dijkstra最短路径。
     * 即，统计图中"顶点vs"到其它各个顶点的最短路径。
     * @param vs    -- 起始顶点(start vertex)。即计算"顶点vs"到其它顶点的最短路径。
     * @param prev  -- 前驱顶点数组。即，prev[i]的值是"顶点vs"到"顶点i"的最短路径所经历的全部顶点中，位于"顶点i"之前的那个顶点。
     * @param dist  -- 长度数组。即，dist[i]是"顶点vs"到"顶点i"的最短路径的长度。
     */
    public void dijkstra(int vs,int[] prev,int[] dist){
        // flag[i]=true表示"顶点vs"到"顶点i"的最短路径已成功获得
        boolean[] flag = new boolean[mVexs.length];

        // 初始化
        for(int i = 0; i< mVexs.length;i++){
            flag[i] = false;            //顶点i的最短路径还没获取到
            prev[i] = 0;                //顶点i前驱顶点为0
            dist[i] = mMatrix[vs][i];   // 顶点i的最短路径为"顶点vs"到"顶点i"的权
        }

        // 对"顶点vs"自身进行初始化
        flag[vs] = true;
        dist[vs] = 0;

        // 遍历mVexs.length-1 次;每次找到一个顶点的最短路径
        int k = 0;
        for(int i = 1; i < mVexs.length; i++){
            // 寻找当前最小的路径
            // 即，在未获取最短路径的顶点中，找到离vs最近的顶点（k）。
            int min = INF;
            for(int j = 0 ; j < mVexs.length;j++){
                if(flag[j] == false && dist[j] < min){
                    min = dist[j];
                    k = j;
                }
            }

            // 标记"顶点k"为已经获取到最短路径
            flag[k] = true;

            // 修正当前最短路径和前驱结点
            // 即，当已经获得"顶点k的最短路径"之后，更新"未获取最短路径的顶点的最短路径和前驱顶点".
            for(int j = 0; j < mVexs.length;j++){
                int temp = (mMatrix[k][j] == INF) ? INF :(min + mMatrix[k][j]);
                if(flag[j] == false && (temp < dist[j])){
                    dist[j] = temp;
                    prev[j] = k;
                }
            }
        }

        // 打印dijkstra最短路径的结果
        System.out.printf("dijkstra(%c):\n",mVexs[vs] );
        for(int i=0; i < mVexs.length;i++){
            System.out.printf(" shortest(%c,%c)=%d\n",mVexs[vs],mVexs[i],dist[i]);
        }
    }

    public static void main(String[] args) {
        char[] vexs = {'A','B','C','D','E','F','G'};
        int[][] matrix = {
                     /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
          /*A*/ {   0,  12, INF, INF, INF,  16,  14},
          /*B*/ {  12,   0,  10, INF, INF,   7, INF},
          /*C*/ { INF,  10,   0,   3,   5,   6, INF},
          /*D*/ { INF, INF,   3,   0,   4, INF, INF},
          /*E*/ { INF, INF,   5,   4,   0,   2,   8},
          /*F*/ {  16,   7,   6, INF,   2,   0,   9},
          /*G*/ {  14, INF, INF, INF,   8,   9,   0}
        };

        MatrixUDG pG = new MatrixUDG(vexs,matrix);

        pG.DFS();
        pG.BFS();
        System.out.println("\n\n");

        int[] prev = new int[pG.mVexs.length];
        int[] dist = new int[pG.mVexs.length];

        // dijkstra 算法获取"第4个结点"到其他各个顶点的最短距离
        pG.dijkstra(3,prev,dist);

       /*
       for(int i = 0 ; i < prev.length;i++){
           System.out.printf("prev[%d]=%d\n",i,prev[i]);
       }

        for(int i = 0 ; i < dist.length;i++){
            System.out.printf("dist[%d]=%d\n",i,dist[i]);
        }
        */
    }
}
