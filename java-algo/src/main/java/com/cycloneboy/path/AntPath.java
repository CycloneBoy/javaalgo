/**
 * 文件名    :AntPath.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月14日
 * 作者        :CycloneBoy
 */
package com.cycloneboy.path;

import java.util.Random;
import java.util.Vector;

/**
 * 蚂蚁个体
 * @author CycloneBoy
 *
 */
public class AntPath {
	private int nPoint;//n表示问题的规模（n×n个节点）
	private int[][] graph;//路径地图，n×n的矩阵
	private double[][] distanceGraph;//图中任意两点间的距离矩阵
	private int[][] distance; //距离矩阵
	private Vector<Integer> obstacle; //障碍物点对应的位置
	private int startPoint;//路径起点
	private int stopPoint; //路径终点
	
	private float alpha; //表征信息素重要程度的参数
	private float beta;  //表征启发式因子重要程度的参数
	private float rho ; //信息素蒸发系数
	private float q ;  //信息素增加强度系数
	private double[][] pheromone; //信息素矩阵
	private Vector<Integer> tabu; //禁忌表
	private Vector<Integer> allowedGrids; //允许搜索的城市
	
	private float[][] delta; //信息素变化矩阵
	

    private int tourLength; //路径长度
    private int gridNum; //城市数量，这里代表有多少个栅格 ,cityNum = nPoint*nPoint 
	    
	private int firstGrid; //起始栅格
	private int currentGrid; //当前栅格
	
	private int preRandom = 0;//保存前面一个随机数
	
	
	public AntPath(){
	     gridNum = 100;
	     tourLength = 0;
	  }
	  
	/**
	 * Constructor of Ant
	 * @param num 栅格的数量
	 */
	   public AntPath(int num){
		 gridNum = num;
	     tourLength = 0;
	   }
	   
 
	  /**
	   	* 路径规划
	    * 描述：第一步初始化
	    * 	     设t=0，初始化bestLength为一个非常大的数（正无穷），bestTour为空。初始化所有的蚂蚁的Delt
	    *     矩阵所有元素初始化为0，Tabu表清空，Allowed表中加入所有的城市节点。随机选择它们的起始位置（也可
	    *     以人工指定）。在Tabu中加入起始节点，Allowed中去掉该起始节点。
	    * 初始化蚂蚁，随机选择起始位置
	    * @param distance 距离矩阵
	    * @param a alpha  
	    * @param b beta
	    * @param rho 信息素挥发系数
	    * @param q   信息素增加强度系数
	    * @param n   表示问题的规模（n×n个节点）的地图
	    * @param graph  地图
	    * @param startPoint 路径起点
	    * @param stopPoint 路径终点
	    */
	  public void init(int[][] distance, float a, float b,float rho,float q,
			  			int n,int[][] graph,int startPoint,int stopPoint){
		  int nn = n * n;
		  this.alpha = a;  //表征信息素重要程度的参数
		  this.beta = b;   //表征启发式因子重要程度的参数
		  this.rho = rho;  //信息素挥发系数
	      this.q = q ;     //信息素增强系数
	      this.nPoint = n ;//表示问题的规模（n×n个节点）
	      this.graph = graph;//初始化地图
	      this.distance = distance;
	      //this.distanceGraph = calculateDistanceOfGraph(n);//计算图中任意两点的距离矩阵
	      this.allowedGrids = new Vector<Integer>();
	      this.tabu = new Vector<Integer>();
	      
	      this.obstacle = new Vector<Integer>();
	      //初始化障碍物数组
	      for (int i = 0; i < n; i++) {
	    	  for (int j = 0; j < n; j++) {
				if (graph[i][j] == 1) {
					obstacle.add(Integer.valueOf(j + n*i));//添加障碍物的位置
				}
	    	  }
		  }

	      this.startPoint = startPoint;//初始化路径起点
	      this.stopPoint = stopPoint; //初始化路径终点
	      this.gridNum = nn; //多少个城市，就是有多少个栅格
	      
	      	//初始化信息素矩阵    Tau=ones(n*n,n*n);%Tau为信息素矩阵
	        pheromone= new double [nn][nn];  
	        for(int i=0;i<nn;i++)  
	        {  
	             for(int j=0;j<nn;j++){  
	                 pheromone[i][j] = 1.0;  //初始化为1.0,设置所有位置的信息素相等
	             }  
	        }  
	      

	      //初始化信息素变化矩阵
	      delta = new float[nn][nn];
	      for (int i = 0; i < nn; i++) {
	    	  Integer integer = new Integer(i);
	      		allowedGrids.add(integer);	//可以访问的栅格加入到数组中
	      		for (int j = 0; j < nn; j++) {
	      			delta[i][j] = 0.f;  //初始化信息
	      		}
	      }
   
	      //Random random = new Random(System.currentTimeMillis());
	      //初始化蚂蚁行走的起始点
	      firstGrid = startPoint;//当前起始节点   random.nextInt(nn);//第一个访问的城市
	      //从允许访问的栅格列表中去除起始点
	      for (Integer i:allowedGrids) {
	    	  if (i.intValue() == firstGrid) {
	    		  allowedGrids.remove(i);
	    		  break;
	    	  }
	      }

	     //在禁忌表中添加已经访问的栅格
	     tabu.add(Integer.valueOf(firstGrid)); //禁忌表中添加起始点
	     currentGrid = firstGrid; //更新当前访问的栅格
	   }
	  
	  /**
	   * 描述:第二步，为每只蚂蚁选择下一个节点
	   * 寻找当前节点的所有可以行走的邻居节点(最多八个节点可以访问)
	   * @param pot 当前节点 
	   * @param n  地图规模
	   * @return 可以行走的邻居节点
	   * @author CycloneBoy
	   *  版本 : V1.0
	   *  说明 : 更改与
	   */
	  public int[] neighborPosition(int pot,int n){
		  int[] potseq = new int[8];//最多八个节点都可以访问
		  double x = pot%n + 0.5;
		  double y = (pot/n) + 0.5;
		  //System.out.println("pos : "+ pot + " x = " + x + " ,y = " + y);
		  //double y = n + 0.5 - Math.ceil(pot/n);
		  double newx,newy;
		  if (x == -0.5) {
			x = n - 0.5; 
		  }
		  
		  for (int i = 0; i < potseq.length; i++) {
			  potseq[i] = -1;   //初始化都不能走
		  }
		  
		  //从左上角按顺时针探寻一圈可以访问的节点
		  newx = x - 1; //左上角
		  newy = y - 1;
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[0] = (int) (n*(newy - 0.5)+ newx -0.5);
		  }
		  //System.out.println("potseq[0] : "+ potseq[0] + " newx = " + newx + " ,newy = " + newy);
		  
		  newx = x ;  //正上方
		  newy = y - 1;
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[1] = (int) (n*(newy - 0.5)+ newx -0.5);
		  }
		  //System.out.println("potseq[1] : "+ potseq[1] + " newx = " + newx + " ,newy = " + newy);
			 
		  newx = x + 1; //右上角
		  newy = y - 1; 
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[2] = (int) (n*(newy - 0.5)+ newx -0.5);
		  }
		  //System.out.println("potseq[2] : "+ potseq[2] + " newx = " + newx + " ,newy = " + newy);
			 
		  newx = x + 1; //右边
		  newy = y ; 
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[3] = (int) (n*(newy - 0.5)+ newx -0.5);
		  }
		  //System.out.println("potseq[3] : "+ potseq[3] + " newx = " + newx + " ,newy = " + newy);
			 
		  newx = x + 1; //右下角
		  newy = y + 1;
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[4] = (int) (n*(newy - 0.5)+ newx -0.5);
		  }
		  //System.out.println("potseq[4] : "+ potseq[4] + " newx = " + newx + " ,newy = " + newy);
			 
		  newx = x ;   //正下方
		  newy = y + 1;
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[5] = (int) (n*(newy - 0.5)+ newx -0.5);
		  }
		  //System.out.println("potseq[5] : "+ potseq[5] + " newx = " + newx + " ,newy = " + newy);
			 
		  newx = x - 1; //右下角
		  newy = y + 1;
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[6] = (int) (n*(newy - 0.5)+ newx -0.5);
		  }
		  //System.out.println("potseq[6] : "+ potseq[6] + " newx = " + newx + " ,newy = " + newy);
			 
		  newx = x - 1; //左边
		  newy = y ;
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[7] = (int) (n*(newy - 0.5)+ newx -0.5);
		  }
		  //System.out.println("potseq[7] : "+ potseq[7] + " newx = " + newx + " ,newy = " + newy);
			 	
		  return potseq;
	  }
	  
	  /**
	   * 描述:第二步，为每只蚂蚁选择下一个节点
	   * 	为每只蚂蚁选择下一个节点，该节点只能从Allowed中以某种概率（公式1）搜索到，每搜到一个，
	   *    就将该节点加入到Tabu中，并且从Allowed中删除该节点。该过程重复n-1次，直到所有的城市
	   *    都遍历过一次。遍历完所有节点后，将起始节点加入到Tabu中。此时Tabu表元素数量为n+1（n为
	   *    城市数量），Allowed元素数量为0。接下来按照（公式2）计算每个蚂蚁的Delta矩阵值。最后
	   *    计算最佳路径，比较每个蚂蚁的路径成本，然后和bestLength比较，若它的路径成本比bestLength
	   *    小，则将该值赋予bestLength，并且将其Tabu赋予BestTour。
	   * 选择下一个城市，并更新信息素
	   * @param pheromone 信息素矩阵
	   */
	  public boolean  selectNextCity(float[][] pheromone){
		 boolean findDestFlag = false;	//是否找到一条从起始点到终点的路径标志
		 int[] nextPosition = new int[8];
		 Vector<Integer> nextChoose = new Vector<Integer>();
		 
		 //获取当前节点的可以访问的邻居节点
		 nextPosition = neighborPosition(currentGrid,nPoint); //获取当前节点的所有相邻节点
//		 System.out.println("-->打印当前节点可以访问的邻居节点:");
//		 for (int i = 0; i < nextPosition.length; i++) {
//			System.out.print(" " + nextPosition[i]);
//		 }
//		 System.out.println("\n-->打印当前节点可以访问的邻居节点,完毕");
		 
		 //判断相邻节点中是否有障碍物节点，有则去除
		 for (int i = 0; i < nextPosition.length; i++) { //判断相邻节点中是否有障碍物节点，有则去除
			 if (nextPosition[i] != -1) { //处理可以访问节点
				 if (obstacle.contains(Integer.valueOf(nextPosition[i]))) {//判断下一个要访问的栅格是否是障碍物点，是，则去除这个栅格
					 nextPosition[i] = -1;//是障碍物，去除掉这个节点
					 //System.out.println("nextPosition[" + i + "] = " + nextPosition[i] +" 是障碍物,去除掉！" );
				 } 
				//判断下一个要访问的栅格是否是终点，是，则找到一条路径，置标志位为真
					if (nextPosition[i] == stopPoint) {
						findDestFlag = true;//找到目的地
						 //在禁忌表中添加终点栅格
					     tabu.add(Integer.valueOf(stopPoint));
					     //将当前城市改为选择的城市
					     currentGrid = stopPoint;
					     System.out.print("注意-->蚂蚁找到终点, ");
					     break;//跳出循环
					}
					
					//判断该栅格是否 已经行走过
					if (tabu.contains(nextPosition[i])) {
						//nextPosition[i] = -1; //如果这个栅格已经行走过则剔除掉这个
					}
					
					//将下一个可以访问的栅格加入到另外一个数组中保存
					if (nextPosition[i] != -1) {
						nextChoose.add(Integer.valueOf(nextPosition[i]));
					}
			 	}
			 
			
		 }
		 
		
		 //System.out.println("------>除去掉障碍物栅格后，下一个可以行走的栅格: ");
//		 for (int i = 0; i < nextChoose.size(); i++) {
//			 System.out.print(" " + nextChoose.get(i).intValue());
//		 }
		 
//		 //如果走到死胡同中去了则
//		 if (nextChoose.size() ==0) {
//			 System.out.println("------>没有可以行走的栅格，走到胡同中去了 ");
//			 nextChoose.add(Integer.valueOf(tabu.get(tabu.size()-1)));//往回走一步
//		 }
		 
		 
		 //System.out.println("\n------->按轮盘赌原则选取下一个栅格 ");
		 //判断是否找到一条路径，没有找到继续寻找下一个栅格
		 if (findDestFlag == false && nextChoose.size() !=0) { //没有找到目的地，继续搜索
			 //待选节点的概率分布数组
			 float[] p = new float[nextChoose.size()];
		     
			 //计算待选栅格的概率分布
			 float sum = 0.0f;
		     //计算分母部分
		     for (int i = 0 ; i < nextChoose.size();i++) { //下面计算待选节点的概率分布
		    	 //计算每个节点的信息素求出前往每个节点的概率，分母部分
		    	 sum += Math.pow(pheromone[currentGrid][nextChoose.get(i).intValue()], alpha)
		    			 *Math.pow(1.0/distance[stopPoint][nextChoose.get(i).intValue()], beta); // P(k)=(Tau(to_visit,J(k))^Alpha)*(Eta(J(k),dest)^Beta);
		     }
		     //计算概率矩阵 , 计算待选节点的概率分布
		     for (int i = 0; i < nextChoose.size(); i++) {
		    	 p[i] = (float) (Math.pow(pheromone[currentGrid][nextChoose.get(i).intValue()], alpha)
		        		  *Math.pow(1.0/distance[stopPoint][nextChoose.get(i).intValue()], beta))/sum; //Eta为启发因子，这里设为距离的倒数
		    	 
		    	 //System.out.print(" p["+i+"]=" + p[i]);
		     }
		     
		     //按轮盘赌原则选取下一个栅格
		     Random random = new Random(System.currentTimeMillis());
		     int r = (random.nextInt(100 - 1) + 1 + preRandom) % 100;
		     preRandom = r; //保持前一个生成的随机数，利用它来让产生的随机数不与前一个数重复
		     double sleectP = r * 1.0 /100;
		     //System.out.print("  随机数: " +sleectP);
		     int selectGrid = 0;
		     float sum1 = 0.f;
		     for (int i = 0; i < nextChoose.size(); i++) {
		    	 sum1 += p[i];
		    	 //System.out.print("  sum1: " + sum1);
		    	 if (sum1 >= sleectP) {
		    		 selectGrid = nextChoose.get(i).intValue();	//选择第i个栅格作为下一个要访问的栅格
		    		 //System.out.print(" ->按轮盘赌原则选取下一个栅格为: "+ selectGrid);
		         break;
		    	 }
		     }
		     
		     
		     
		     
		     //从允许选择的栅格中去除select grid
		     for (Integer i:allowedGrids) {
		    	 if (i.intValue() == selectGrid) {
		    		 allowedGrids.remove(i);
		    		 break;
		    	 }
		     }
		     
		     //在禁忌表中添加select grid
		     //System.out.println("--->当前禁忌表中的已经行走的路径: ");
		     tabu.add(Integer.valueOf(selectGrid));
//		     for (int i = 0; i < tabu.size(); i++) {
//		    	 System.out.print(" " + tabu.get(i).intValue());
//			 }
		     
		     //将当前城市改为选择的城市
		     currentGrid = selectGrid;
		     //System.out.println("--->更新当前所在位置栅格: " + currentGrid);
		 }
	     
	     return findDestFlag;
   }
	  


	/**
	    * 计算路径长度
	    * 获取禁忌表中的路径长度
	    * @return 路径长度
	    */
	 private int calculateTourLength(){
		 int len = 0;
		 //计算最短路径长度
		 if (this.tabu.get(this.tabu.size()-1) != stopPoint) { //没有找到终点
			 len = Integer.MAX_VALUE;
		} else {
			for (int i = 0; i < this.tabu.size() - 1; i++) {
			       len += distance[this.tabu.get(i).intValue()][this.tabu.get(i+1).intValue()];
			     }
		}

	     return len;
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
	 	
	   public Vector<Integer> getAllowedCities() {
		   return allowedGrids;
	   }
	 
	   public void setAllowedCities(Vector<Integer> allowedCities) {
	     this.allowedGrids = allowedCities;
	   }
	
	   public int getTourLength() {
	     tourLength = calculateTourLength();
	     return tourLength;
	   }
	   public void setTourLength(int tourLength) {
	     this.tourLength = tourLength;
	  }
	  public int getCityNum() {
	     return gridNum;
	   }
	  public void setCityNum(int cityNum) {
	     this.gridNum = cityNum;
	   }
	
	  public Vector<Integer> getTabu() {
		  return tabu;
	  }

	   public void setTabu(Vector<Integer> tabu) {
	     this.tabu = tabu;
	  }
	 
	  public float[][] getDelta() {
	     return delta;
	  }

	   public void setDelta(float[][] delta) {
		   this.delta = delta;
	   }
 
	   public int getFirstCity() {
		   return firstGrid;
	   }
	 
	   public void setFirstCity(int firstCity) {
		   this.firstGrid = firstCity;
	   }

	/**
	 * @return the graph
	 */
	public int[][] getGraph() {
		return graph;
	}

	/**
	 * @param graph the graph to set
	 */
	public void setGraph(int[][] graph) {
		this.graph = graph;
	}

	/**
	 * @return the startPoint
	 */
	public int getStartPoint() {
		return startPoint;
	}

	/**
	 * @param startPoint the startPoint to set
	 */
	public void setStartPoint(int startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * @return the stopPoint
	 */
	public int getStopPoint() {
		return stopPoint;
	}

	/**
	 * @param stopPoint the stopPoint to set
	 */
	public void setStopPoint(int stopPoint) {
		this.stopPoint = stopPoint;
	}

	/**
	 * @return the rho
	 */
	public float getRho() {
		return rho;
	}

	/**
	 * @param rho the rho to set
	 */
	public void setRho(float rho) {
		this.rho = rho;
	}

	/**
	 * @return the q
	 */
	public float getQ() {
		return q;
	}

	/**
	 * @param q the q to set
	 */
	public void setQ(float q) {
		this.q = q;
	}

	/**
	 * @return the distanceGraph
	 */
	public double[][] getDistanceGraph() {
		return distanceGraph;
	}

	/**
	 * @param distanceGraph the distanceGraph to set
	 */
	public void setDistanceGraph(double[][] distanceGraph) {
		this.distanceGraph = distanceGraph;
	}
	  /**
	 * @return the currentGrid
	 */
	public int getCurrentGrid() {
		return currentGrid;
	}

	/**
	 * @param currentGrid the currentGrid to set
	 */
	public void setCurrentGrid(int currentGrid) {
		this.currentGrid = currentGrid;
	}

}
