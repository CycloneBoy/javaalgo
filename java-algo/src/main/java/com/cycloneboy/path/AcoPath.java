/**
 * 文件名    :AcoPath.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月14日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.path;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * @author CycloneBoy
 *
 */
public class AcoPath {
	private AntPath[] ants; //蚂蚁
	private int antNum;  //蚂蚁数量
	private int nPoint;	 //地图边长，每行多少个点
	private int gridNum;//栅格的数量
	private int cityNum; //城市数量
	private int MAX_GEN; //运行代数
	private float[][] pheromone; //信息素矩阵
	private int[][] distance; //距离矩阵
	private double[][] distanceGraph;//图中任意两点间的距离矩阵
	private int[][] graph;//路径地图，n×n的矩阵
	
	private int[] bestLength; //最佳长度
	private int bestLengthPath;
	private double[] bestLengthAve; // 记录各代路线的平均长度
	private int[][] bestTour; //最佳路径
	//private int[][][] allOptionalPath; //最佳路径
    private int[] bestTourPath;
	
	//四个参数
	private float alpha; 
	private float beta;
	private float rho;
	private float q ;  //信息素增加强度系数
	
	
	private int startPoint;//路径起点
	private int stopPoint; //路径终点
	
	/**
	 * 空构造函数
	 */
	public AcoPath(){
		
	}
	
	/**
	 * 构造函数: constructor of ACO
	 * @param n 每行栅格数量
	 * @param m 蚂蚁数量
	 * @param g 运行代数
	 * @param a alpha 
	 * @param b beta
	 * @param r rho
	 * @param q q
	 **/
	public AcoPath(int n, int m, int g, float a, float b, float r,float q ,int start,int stop) {
		this.nPoint = n;
	   antNum = m;
	   ants = new AntPath[antNum];
	   MAX_GEN = g;
	   alpha = a;
	   beta = b;
	   rho = r;
	   this.q = q;
	   this.gridNum =  nPoint * nPoint; //n X n个栅格
	   this.cityNum = gridNum;
	   this.startPoint = start;
	   this.stopPoint = stop ;
	   System.out.println("测试-> 算法初始化:");
	   System.out.println(" 每行栅格数: "+ nPoint +" 蚂蚁数量: "+ antNum + " 迭代次数: " + MAX_GEN);
	   System.out.println(" alpha: " + alpha + " beta: " + beta + " rho: "+ rho + " q: " + q);
	   
   }
   
   @SuppressWarnings("resource")
   /**  
    * 初始化ACO算法类
    * @param filename 数据文件名，该文件存储所有城市节点坐标数据
    * @throws IOException
    */
   private void init(String filename) throws IOException{
	   //读取数据  
       String strbuff;  
       BufferedReader data = new BufferedReader(new InputStreamReader(
    		   new FileInputStream(filename)));  //获取文件数据源，tsp数据源
        
       strbuff = data.readLine(); 
       String[] strcol = strbuff.split(" ");  //读取一行
       this.nPoint = Integer.valueOf(strcol[0]);//每行多少个点
       System.out.println("测试-> 初始化地图,每行: " + nPoint +" 个点");
       
       graph = new int[nPoint][nPoint];
       
       //读取地图
       for (int i = 0; i < nPoint; i++) {
    	   strbuff = data.readLine(); 
           strcol = strbuff.split(" ");  //读取一行
           for (int j = 0; j < strcol.length; j++) {
        	   graph[i][j] = Integer.valueOf(strcol[j]);
           }
       }
       
       //打印数据调试
       for (int i = 0; i < graph.length; i++) { 
    	   for (int j = 0; j < graph[i].length; j++) {
    		   System.out.print(graph[i][j] + " ");
    	   }
    	   System.out.println();
       }
       System.out.println("测试-> 地图读取完毕");
      	
       
       //初始化地图中任意两点的距离
       //distanceGraph = calculateDistanceOfGraph(nPoint); //new double[nPoint][nPoint];
       distance = calculateDistanceOfGraph(nPoint, nPoint);
       //打印调试
       //打印数据调试
       System.out.println("\n\n测试开始-> 打印地图中任意两点间的距离");
       for (int i = 0; i < distance.length; i++) { 
    	   for (int j = 0; j < distance[i].length; j++) {
    		   //System.out.print(distanceGraph[i][j] + " ");
    		   //System.out.print(distance[i][j] + " ");
    	   }
    	   //System.out.println();
       }
       System.out.println("测试完毕-> 打印地图中任意两点间的距离,完毕");
       
       
        //初始化信息素矩阵  
        pheromone = new float[gridNum][gridNum];  
        for(int i=0;i<gridNum;i++)  
        {  
             for(int j=0;j<gridNum;j++){  
                 pheromone[i][j]=1f;  //初始化为0.1
             }  
        }  
        
        bestLength = new int[MAX_GEN]; //每代蚂蚁迭代后的最佳长度
        bestLengthPath = Integer.MAX_VALUE; //初始化最短距离为无穷大
        bestLengthAve = new double[MAX_GEN]; //每代蚂蚁迭代后的平均最佳长度
        
        bestTour = new int[MAX_GEN][gridNum+1]; //存储并记录路径的生成
        bestTourPath = new int[gridNum+1];
        //allOptionalPath = new int[MAX_GEN][antNum][gridNum+1];
        
        //初始化迭代路径和长度缓冲区
        for (int i = 0; i < MAX_GEN; i++) {
        	bestLength[i] = 0;
        	bestLengthAve[i]=0;
        	for (int j = 0; j < gridNum+1; j++) {
        		bestTour[i][j] = 0;
			}
		}
        
        for (int i = 0; i < gridNum+1; i++) {
        	bestTourPath[i] = 0;
		}
        
        
        //开始放置antNum只蚂蚁  
        System.out.println("测试开始-> 开始放置 "+ antNum + " 只蚂蚁");
        for(int i=0;i<antNum;i++){  
             ants[i]=new AntPath(gridNum);  
             ants[i].init(distance, alpha, beta,rho,q,nPoint,graph,
            		 startPoint,stopPoint);  
        }  
        System.out.println("测试完毕-> 放置antNum只蚂蚁,完毕");
   }
  
   /**
	   * 计算距离矩阵 ，针对具体问题，距离计算方法也不一样
	   * 计算图中任意两点的距离
	   * D=caldistmat(n);     %D表示完全图的赋权邻接矩阵，图中任意两点的距离
	   * @param n 图的规模大小 
	   * @return 图中任意两点的距离矩阵
	   */
	 public  double[][]  calculateDistanceOfGraph(int n){
		 int total = n * n;
		 double [][] distance = new double[total][total];
		 int first,second; //当前坐标点的标号 0,1,...99
		 
		 for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				first = i+n*j;
				
				for (int k1 = 0; k1 < n; k1++) {
					for (int k2 = 0; k2 < n; k2++) {
						second = k1+n*k2;
						distance[first][second] = Math.sqrt((i - k1)*( i- k1) + (j - k2)*(j - k2));
						if(i==k1 & j==k2){
							distance[first][second] = 0.0001; //当前位置到当前位置的距离为0
						}
		                   
					}
				}
			}
		}
		 return distance;
	 }
   
	 /**
	   * 计算距离矩阵 ，针对具体问题，距离计算方法也不一样
	   * 计算图中任意两点的距离
	   * D=caldistmat(n);     %D表示完全图的赋权邻接矩阵，图中任意两点的距离
	   * @param n 图的规模大小 
	   * @return 图中任意两点的距离矩阵
	   */
	 public  int[][]  calculateDistanceOfGraph(int n,int m ){
		 int total = n * n;
		 int [][] distance = new int[total][total];
		 int first,second; //当前坐标点的标号 0,1,...99
		 
		 for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				first = i+n*j;
				
				for (int k1 = 0; k1 < n; k1++) {
					for (int k2 = 0; k2 < n; k2++) {
						second = k1+n*k2;
						double rij = Math.sqrt((i - k1)*( i- k1) + (j - k2)*(j - k2));
						int tij = (int) Math.round(rij);
		            	 if (tij < rij) {
		            		 distance[first][second] = tij + 1;  
		            		 //distance[j][i] = distance[i][j];  
		            	 }else {
		            		 distance[first][second] = tij;  
		                     //distance[j][i] = distance[i][j]; 
		            	 }
		            	 
						 if(i==k1 & j==k2){
							distance[first][second] = 0; //当前位置到当前位置的距离为0
						 }
					}
				}
			}
		}
		 return distance;
	 }
	 
   /**
    * 进行蚁群算法计算
    * 输出具体的结果
    * 描述:如果达到最大代数MAX_GEN，算法终止，转到第（5）步；否则，重新初始化所有的蚂蚁的Delt矩阵
    * 	    所有元素初始化为0，Tabu表清空，Allowed表中加入所有的城市节点。随机选择它们的起始位置（也
    *    可以人工指定）。在Tabu中加入起始节点，Allowed中去掉该起始节点，重复执行（2），（3）,(4)步。
    * @author CycloneBoy
    * 
    */
   public void solve(){
     boolean flagFindDes = false; //是否找到目的地
     float[] bestLengthTemp = new  float[antNum];//每一只蚂蚁的最短路径
     int[] bestLengthSix = new int[6];
     bestLengthSix[0] = 0;
     int preRandom=0;
     for (int g = 0; g < MAX_GEN; g++) {		//MAX_GEN次蚂蚁迭代
       for (int i = 0; i < antNum; i++) {		//某一代antNum只蚂蚁迭代结束
    	   
//    	   System.out.println("----->第 " + g+ "代的第 "+ i + "只蚂蚁开始寻找路径。" + 
//    			   			" 起点: " + ants[i].getTabu().get(0));
    	   for (int j = 1; j < gridNum; j++) {	//一只蚂蚁到达终点或者无路可走
//    		   System.out.println("--->第 "+ i + " 只蚂蚁进行第 " + j + 
//    				   "次选择下一个行走的栅格,当前所在栅格为:" + ants[i].getCurrentGrid());
    		   flagFindDes = ants[i].selectNextCity(pheromone);  //行走完一次路径
    		   
    		   if (flagFindDes == true) {
    			   flagFindDes = false;
    			   
    			   break;  //跳出本次循环，找到一条路径
    		   }
    	   }
    	   System.out.print("----->第 " + g+ "代的第 "+ i + "只蚂蚁寻找路径完毕");
    	   for (int k = 0; k < ants[i].getTabu().size() ; k++) {	  //是目前为止的最短路径，就保存这条最短路径
    		  // allOptionalPath[g][i][k] = ants[i].getTabu().get(k).intValue();
    		   System.out.print(" " + ants[i].getTabu().get(k).intValue());
		   }
    	   //allOptionalPath[g][i][gridNum] = ants[i].getTabu().size();//记录有多少个点
		   
    	   System.out.println();
    	   
    	   
    	   //第四步：记录本次迭代最佳路线
    	   bestLengthTemp[i] = ants[i].getTourLength();
    	   if (ants[i].getTourLength() < bestLengthPath) {    //判断这一次是否是到目前为止的最短路径
    		   bestLengthPath = ants[i].getTourLength();
    		   
    		   //bestLength[g] = ants[i].getTourLength();//本次迭代中的最佳路径
    		   for (int k = 0; k < ants[i].getTabu().size() ; k++) {	  //是目前为止的最短路径，就保存这条最短路径
    			   bestTourPath[k] = ants[i].getTabu().get(k).intValue();
    			   //bestTour[g][k] = bestTourPath[k];//某一次迭代的最好路径
    		   }
    		   bestTourPath[gridNum] = ants[i].getTabu().size();//记录这一条路径的长度
    		   //bestTour[g][gridNum] = ants[i].getTabu().size();//记录这一条路径的长度
    		   System.out.println("\n----->第"+g+" 次迭代的第"+i+" 只蚂蚁找到一条最短路径，长度为:"+
    				   bestLengthPath + " ,总共有 "+bestTourPath[gridNum]+" 个点的路径为:" );
    		   for (int j = 0; j < bestTourPath[gridNum]; j++) {
    			   System.out.print(" " + bestTourPath[j]);
    		   }
    		   System.out.println("\n");
    	   }
    	   
    	   
//    	   //经过一次迭代之后，栅格i与j之间信息素经过一个迭代后的增量。
//    	   for (int j = 0; j < ants[i].getTabu().size() -1; j++) {
//    		   ants[i].getDelta()[ants[i].getTabu().get(j).intValue()][ants[i].getTabu().get(j+1).intValue()] = (float) (1./ants[i].getTourLength());
//    		   ants[i].getDelta()[ants[i].getTabu().get(j+1).intValue()][ants[i].getTabu().get(j).intValue()] = (float) (1./ants[i].getTourLength());
//    	   }
       }//某一代所有蚂蚁迭代结束
      
       
       //记录某一次迭代后的最优路径长度和最优路径
       bestLength[g] = bestLengthPath;
       for (int j = 0; j < bestTourPath[gridNum]; j++) {
    	   bestTour[g][j] = bestTourPath[j];//某一次迭代的最好路径
       }
       bestTour[g][gridNum] = bestTourPath[gridNum];//记录这一条路径的长度
       
       for (int i = 0; i < ants.length; i++) {
    	   bestLengthAve[g]+=bestLengthTemp[i];
       }
       bestLengthAve[g] = bestLengthAve[g]/antNum;//计算某次迭代的平均路径
       System.out.println("第"+g+" 次迭代结束,最短路径:"+bestLengthPath +" ,最短平均路径为: "+bestLengthAve[g]);
   	
       
       //进行算法优化
       //如果5代最好的结果相等，则提前停止
       for (int i = 1; i < bestLengthSix.length; i++) {
    	   bestLengthSix[i] = bestLengthSix[i-1];
       }
       bestLengthSix[0] = bestLengthPath;
       
       //计算五代的总和
       int sumBestLengthSix = 0;
       for (int i = 1; i < bestLengthSix.length; i++) {
    	   sumBestLengthSix += bestLengthSix[i];
       }
       
       if (g > 10) {		//10轮之后再更新

           	//在误差允许范围内
           if ( Math.abs(bestLengthSix[0]  - sumBestLengthSix*1.0/5.0) < 0.0001) {
        	   System.out.println("5代最好的结果相等，则提前停止迭代");
        	   break;//结束迭代，跳出总循环
           }
       }
       
       	//如果五代的最佳路径相等，则更新信息素蒸发系数
       if ( Math.abs(bestLengthSix[0]  - sumBestLengthSix*1.0/5.0) < 0.0001) {
    	   
    	   Random random = new Random(System.currentTimeMillis());
		   int r = (random.nextInt(100 - 1) + 1 + preRandom) % 100;
		   preRandom = r; //保持前一个生成的随机数，利用它来让产生的随机数不与前一个数重复
		   double sleectP = r * 1.0 /100;
    	   if (sleectP <= 0.4) {
    		   setRho((float) (1.05 * getRho()));//更新信息素蒸发系数
    	   } else {
    		   setRho(0.4f);
    	   }
    	   System.out.println("如果五代的最佳路径相等，则更新信息素蒸发系数");
       }
       
       
       //更新信息素，第五步：更新信息素
       updatePheromone(bestLengthTemp);
       
       
       
       //%第六步：更新启发因子信息
       if (g > 16) {		//10轮之后再更新
    	   int[] bestpath = new int[gridNum];
    	   //各代最佳路线的长度
    	   double bestpathLength = bestLength[g];	//各代最佳路线的长度
    	   	//各代最佳路线
    	   for (int i = 0; i < bestpath.length; i++) {
    		   bestpath[i] =  bestTour[g][i];//这次迭代的最好路径 //各代最佳路线
    	   }
    	   
    	   for (int i = 1; i < bestpath.length; i++) {
    		   distance[bestpath[i]][stopPoint] = bestLengthPath - 
    				   			distance[bestpath[i-1]][bestpath[i]];
    		   bestpathLength = distance[bestpath[i]][stopPoint]; //更新距离长度
    		   //更新启发因子信息
 
    	   }
       }
       
       //重新初始化蚂蚁，放置蚂蚁到起点
       for(int i=0;i<antNum;i++){  
    	   ants[i].init(distance, alpha, beta,rho,q,nPoint,graph,
          		 startPoint,stopPoint);
       }
       
       
     }
     
     //打印最佳结果，输出TSP最优解
     printOptimal();
   }
  
   
   /**
    * 每一代蚂蚁迭代结束后，更新信息素
    * 令t = t + n，按照（公式3）更新信息素矩阵pheromone。
    * @author CycloneBoy
    */
   private void updatePheromone(float[] bestLengthTemp){
	   
	   //更新信息素
	   for (int i = 0; i < antNum; i++) {
		   for (int j = 0; j < ants[i].getTabu().size() - 1; j++) {
			   
			   //找到最短路径的蚂蚁才更新
			   if ( ants[i].getTabu().size() < 90) {
				   
				   //更新信息素增量
				   ants[i].getDelta()[ants[i].getTabu().get(j)][ants[i].getTabu().get(j+1)] =
						   ants[i].getDelta()[ants[i].getTabu().get(j)][ants[i].getTabu().get(j+1)] +
						   q/bestLengthTemp[i];
				   
//				   pheromone[ants[i].getTabu().get(j)][ants[i].getTabu().get(j+1)] = 
//						   pheromone[ants[i].getTabu().get(j)][ants[i].getTabu().get(j+1)] +
//						   q/bestLengthTemp[i];
		
			 }
		 }
		   
	   }
	   
	   	//信息素挥发  
         for(int i=0;i<gridNum;i++)  
             for(int j=0;j<gridNum;j++)  
                 pheromone[i][j]=pheromone[i][j]*(1-rho);  //挥发后剩下的信息素
         //信息素更新  
         for(int i=0;i<gridNum;i++){  
            for(int j=0;j<gridNum;j++){  
                 for (int k = 0; k < antNum; k++) { //antNum只蚂蚁爬过之后的，在一条边上的增量
                	 pheromone[i][j] += ants[k].getDelta()[i][j];//新增加一部分
                 } 
            }  
         }  
   }
   
   /**
    * 最后一步:输出最优的路径
    * 
    * @author CycloneBoy
    */
   private void printOptimal(){
	 int pos=0 ;
	 int shortTestLength = bestLength[0];
	 
//	 for (int i = 0; i < MAX_GEN; i++) {
//		 
//		if (shortTestLength > bestLength[i]) {
//			shortTestLength = bestLength[i];
//			pos = i;
//		 }
//		 System.out.print("The optimal length is: " + bestLength[i]);
//	     System.out.println("  ,The optimal tour total grid  is: " + bestTour[i][gridNum]+ 
//	    		 " , the optimal tour is :");
//	     System.out.print("           ");
//	     for (int j = 0; j < bestTour[i][gridNum] ; j++) {
//	    	 System.out.print(" " + bestTour[i][j]);
//	     }
//	     System.out.println();
//	 }
//	
	 
	 System.out.print("The optimal length is: " + bestLengthPath);
     System.out.println("  ,The optimal tour total grid  is: " + bestTourPath[gridNum]+ 
    		 " , the optimal tour is :");
     System.out.print("           ");
     for (int j = 0; j < bestTourPath[gridNum] ; j++) {
    	 System.out.print(" " + bestTourPath[j]);
     }

   }
   
   public AntPath[] getAnts() {
	   return ants;
   }

  public void setAnts(AntPath[] ants) {
     this.ants = ants;
  }
 
   public int getAntNum() {
	   return antNum;
  }
 
   public void setAntNum(int m) {
	   this.antNum = m;
   }
 
   public int getCityNum() {
	   return cityNum;
   }
 
  public void setCityNum(int cityNum) {
	  this.cityNum = cityNum;
   }

   public int getMAX_GEN() {
	   return MAX_GEN;
   }
 
   public void setMAX_GEN(int mAX_GEN) {
     MAX_GEN = mAX_GEN;
  }

   public float[][] getPheromone() {
	   return pheromone;
   }

   public void setPheromone(float[][] pheromone) {
	   this.pheromone = pheromone;
   }

   public int[][] getDistance() {
	   return distance;
   }

   public void setDistance(int[][] distance) {
	   this.distance = distance;
  }
 

 
   /**
 * @return the bestLength
 */
public int[] getBestLength() {
	return bestLength;
}

/**
 * @param bestLength the bestLength to set
 */
public void setBestLength(int[] bestLength) {
	this.bestLength = bestLength;
}

/**
 * @return the bestLengthPath
 */
public int getBestLengthPath() {
	return bestLengthPath;
}

/**
 * @param bestLengthPath the bestLengthPath to set
 */
public void setBestLengthPath(int bestLengthPath) {
	this.bestLengthPath = bestLengthPath;
}

/**
 * @return the bestTour
 */
public int[][] getBestTour() {
	return bestTour;
}

/**
 * @param bestTour the bestTour to set
 */
public void setBestTour(int[][] bestTour) {
	this.bestTour = bestTour;
}

/**
 * @return the bestTourPath
 */
public int[] getBestTourPath() {
	return bestTourPath;
}

/**
 * @param bestTourPath the bestTourPath to set
 */
public void setBestTourPath(int[] bestTourPath) {
	this.bestTourPath = bestTourPath;
}

public float getAlpha() {
	   return alpha;
   }
 
   public void setAlpha(float alpha) {
     this.alpha = alpha;
   }
 
   public float getBeta() {
     return beta;
   }
 
   public void setBeta(float beta) {
     this.beta = beta;
   }

   public float getRho() {
     return rho;
   }

   public void setRho(float rho) {
	   this.rho = rho;
   }
 

   /**
    * 测试48个城市的TSP问题
    * 描述: 在该java实现中我们选择使用tsplib上的数据att48，这是一个对称tsp问题，
    * 	        城市规模为48，其最优值为10628.其距离计算方法如图（2）所示：
    *      实现中，使用了两个java类，一个Ant类，一个ACO类。
    *      25次迭代后的最优解为:12280
    * @param args
    * @throws IOException 
    */
   public static void main(String[] args) throws IOException {
	   AcoPath aco = new AcoPath(10, 40, 60, 1.f, 6.f, 0.1f,14.f,0,99);//每行栅格数量、蚂蚁数量、迭代测试、alpha、beta、rho、q
	   aco.init("src//com//algo//path//graph.txt");
	   aco.solve();
   }
 
}
