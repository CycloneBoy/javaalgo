package com.cycloneboy.path;

import java.util.Vector;

/**
 * 文件名    :AntPath.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月14日
 * 作者        :CycloneBoy
 */

import java.util.Random;
/**
 * 蚂蚁个体
 * @author CycloneBoy
 *
 */
public class AntPathCopy {
	private int[][] graph;//路径地图，n×n的矩阵
	private Vector<Integer> obstacle; //障碍物点对应的位置
	private int startPoint;//路径起点
	private int stopPoint;//路径终点
	
	private int nc_max ;//NC_max 最大迭代次数
	private int antNum; //蚂蚁数量
	
	private float alpha; //表征信息素重要程度的参数
	private float beta;  //表征启发式因子重要程度的参数
	private float rho ; //信息素蒸发系数
	private float q ;  //信息素增加强度系数
	private int nPoint;//n表示问题的规模（n×n个节点）
	private double[][] distanceGraph;//图中任意两点间的距离矩阵
	private double[][] pheromone; //信息素矩阵
	private int[][] tabuDistance;//存储并记录路径的生成
	private int[][] R_best ; //记录各代最佳路线
	private double[] L_best; // 记录各代最佳路线的长度
	private double[] L_ave; // 记录各代路线的平均长度
	private int isVisited;//是否开始爬行
	
	private Vector<Integer> tabu; //禁忌表
	private Vector<Integer> allowedCities; //允许搜索的城市
	private float[][] delta; //信息数变化矩阵
	private int[][] distance; //距离矩阵
	   
	
	
    private int tourLength; //路径长度
    private int cityNum; //城市数量
	    
	private int firstCity; //起始城市
	private int currentCity; //当前城市
	
	
	public AntPathCopy(){
	     cityNum = 30;
	     tourLength = 0;
	    
	  }
	  
  /**
   * Constructor of Ant
   * @param num 蚂蚁数量
 */
	   public AntPathCopy(int num){
		   cityNum = num;
	     tourLength = 0;
	     
	   }
	   
	   /**
	    * 描述：第一步初始化
	    * 	     设t=0，初始化bestLength为一个非常大的数（正无穷），bestTour为空。初始化所有的蚂蚁的Delt
	    *     矩阵所有元素初始化为0，Tabu表清空，Allowed表中加入所有的城市节点。随机选择它们的起始位置（也可
	    *     以人工指定）。在Tabu中加入起始节点，Allowed中去掉该起始节点。
	    * 初始化蚂蚁，随机选择起始位置
	    * @param distance 距离矩阵
	    * @param a alpha  
	    * @param b beta
	    * 
	    */
	  public void init(int[][] distance, float a, float b){
		  alpha = a;
		  beta = b;
	      allowedCities = new Vector<Integer>();
	      tabu = new Vector<Integer>();
	      this.distance = distance;
	      delta = new float[cityNum][cityNum];
	      for (int i = 0; i < cityNum; i++) {
	    	  Integer integer = new Integer(i);
	      		allowedCities.add(integer);
	      		for (int j = 0; j < cityNum; j++) {
	      			delta[i][j] = 0.f;
	      		}
	      }
    
	      Random random = new Random(System.currentTimeMillis());
	      firstCity = random.nextInt(cityNum);//第一个访问的城市
	      for (Integer i:allowedCities) {
	    	  if (i.intValue() == firstCity) {
	    		  allowedCities.remove(i);
	    		  break;
	    	  }
	      }
     
	     tabu.add(Integer.valueOf(firstCity)); //禁忌表中添加起始点
	     currentCity = firstCity;
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
	    * @param n  表示问题的规模（n×n个节点）的地图
	    * @param graph  地图
	    * @param startPoint 路径起点
	    * @param stopPoint 路径终点
	    * @param nAnt 蚂蚁数量
	    * @param ncMax 迭代次数
	    */
	  public void init(int[][] distance, float a, float b,float rho,float q,int n,
			  int[][] graph,int startPoint,int stopPoint,int nAnt,int ncMax){
		  int nn = n *n;
		  alpha = a;
		  beta = b;
	      allowedCities = new Vector<Integer>();
	      tabu = new Vector<Integer>();
	      this.distance = distance;
	      this.graph = graph;//初始化地图
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
	      this.antNum = nAnt;//蚂蚁数量
	      this.nc_max = ncMax;//迭代次数
	      this.rho = rho; //信息素挥发系数
	      this.q = q ;//信息素增强系数
	      this.nPoint = n ;//表示问题的规模（n×n个节点）
	      this.distanceGraph = caldistmat(n);//计算图中任意两点的距离矩阵
	      this.tabuDistance = new int[antNum][nn]; //存储并记录路径的生成
	      this.isVisited = 0;//蚂蚁还没有被访问
	      this.cityNum = nn; //多少个城市，就是有多少个栅格
	      	//初始化信息素矩阵    Tau=ones(n*n,n*n);%Tau为信息素矩阵
	        pheromone= new double [nn][nn];  
	        for(int i=0;i<nn;i++)  
	        {  
	             for(int j=0;j<nn;j++){  
	                 pheromone[i][j] = 1.0;  //初始化为1.0,设置所有位置的信息素相等
	             }  
	        }  
	      
	      this.R_best = new int[nc_max][nn];//记录各代最佳路线
	      this.L_best = new double[nc_max]; //记录各代最佳路线的长度
	      this.L_ave = new double[nc_max];  //记录各代路线的平均长度
	      
		 
		  this.isVisited = 1;//已经访问
		  
	      delta = new float[nn][nn];
	      for (int i = 0; i < nn; i++) {
	    	  Integer integer = new Integer(i);
	      		allowedCities.add(integer);
	      		for (int j = 0; j < nn; j++) {
	      			delta[i][j] = 0.f;
	      		}
	      }
   
	      //Random random = new Random(System.currentTimeMillis());
	      firstCity = startPoint;//当前起始节点   random.nextInt(nn);//第一个访问的城市
	      for (Integer i:allowedCities) {
	    	  if (i.intValue() == firstCity) {
	    		  allowedCities.remove(i);
	    		  break;
	    	  }
	      }
    
	     tabu.add(Integer.valueOf(firstCity)); //禁忌表中添加起始点
	     currentCity = firstCity;
	   }
	  
	  /**
	   * 描述:第二步，为每只蚂蚁选择下一个节点
	   * 寻找当前节点的所有可以行走的邻居节点(最多八个节点可以访问)
	   * @param pot 当前节点 
	   * @param n  地图规模
	   * @return 可以行走的邻居节点
	   * @author CycloneBoy
	   * 
	   */
	  public int[] neighborPosition(int pot,int n){
		  int[] potseq = new int[8];//最多八个节点都可以访问
		  double x = pot%n - 0.5;
		  double y = n + 0.5 - Math.ceil(pot/n);
		  double newx,newy;
		  if (x == -0.5) {
			x = n - 0.5; 
		  }
		  
		  for (int i = 0; i < potseq.length; i++) {
			  potseq[i] = -1;   //初始化都不能走
		  }
		  
		  newx = x - 1; //左边
		  newy = y;
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[0] = (int) (n*(n-0.5-y)+x+0.5);
		  }
		  
		  newx = x + 1;//右边
		  newy = y;
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[1] = (int) (n*(n-0.5-y)+x+0.5);
		  }
		  
		  newx = x;
		  newy = y - 1; //下边
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[2] = (int) (n*(n-0.5-y)+x+0.5);
		  }
		  
		  newx = x;
		  newy = y + 1; //上边
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[3] = (int) (n*(n-0.5-y)+x+0.5);
		  }
		  
		  newx = x - 1; //左下角
		  newy = y - 1;
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[4] = (int) (n*(n-0.5-y)+x+0.5);
		  }
		  
		  newx = x - 1;//左上角
		  newy = y + 1;
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[5] = (int) (n*(n-0.5-y)+x+0.5);
		  }
		  
		  newx = x + 1; //右下角
		  newy = y - 1;
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[6] = (int) (n*(n-0.5-y)+x+0.5);
		  }
		  
		  newx = x + 1; //右上角
		  newy = y + 1;
		  if(newx>=0.5 & newx<=9.5 & newy>=0.5 & newy <=9.5){
		  		potseq[7] = (int) (n*(n-0.5-y)+x+0.5);
		  }
		  	
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
		 boolean findDestFlag = false;
		 int[] nextPosition = new int[8];
		 Vector<Integer> nextChoose = new Vector<Integer>();
		 nextPosition = neighborPosition(currentCity,nPoint); //获取当前节点的所有相邻节点
		 for (int i = 0; i < nextPosition.length; i++) { //判断相邻节点中是否有障碍物节点，有则去除
			if ( nextPosition[i] != -1 && obstacle.contains(Integer.valueOf(nextPosition[i]))) {
				nextPosition[i] = -1;//是障碍物，去除掉这个节点
			}
			
			if (nextPosition[i] == stopPoint) {
				findDestFlag = true;//找到目的地
				 //在禁忌表中添加select city
			     tabu.add(Integer.valueOf(stopPoint));
			     //将当前城市改为选择的城市
			     currentCity = stopPoint;
			}
			
			if (nextPosition[i] != -1) {
				nextChoose.add(Integer.valueOf(nextPosition[i]));
			}
		 }
		 
		 if (findDestFlag == false) { //没有找到目的地，继续搜索
			 float[] p = new float[nextChoose.size()];
		     float sum = 0.0f;
		     //计算分母部分
		     for (int i = 0 ; i < nextChoose.size();i++) { //下面计算待选节点的概率分布
		    	 //计算每个节点的信息素求出前往每个节点的概率，分母部分
		    	 sum += Math.pow(pheromone[currentCity][nextChoose.get(i)], alpha)
		    			 *Math.pow(1.0/distance[currentCity][nextChoose.get(i)], beta);
		     }
		     //计算概率矩阵 , 计算待选节点的概率分布
		     for (int i = 0; i < nextChoose.size(); i++) {
		    	 p[i] = (float) (Math.pow(pheromone[currentCity][nextChoose.get(i)], alpha)
		        		  *Math.pow(1.0/distanceGraph[currentCity][nextChoose.get(i)], beta))/sum; //Eta为启发因子，这里设为距离的倒数 
		     }
		     
		     //轮盘赌选择下一个城市
		     Random random = new Random(System.currentTimeMillis());
		     float sleectP = random.nextFloat();
		     int selectCity = 0;
		     float sum1 = 0.f;
		     for (int i = 0; i < nextChoose.size(); i++) {
		    	 sum1 += p[i];
		    	 if (sum1 >= sleectP) {
		    		 selectCity = nextChoose.get(i);	//选择第i个城市作为下一个要访问的城市
		         break;
		    	 }
		     }
		     
		     //从允许选择的城市中去除select city
		     for (Integer i:allowedCities) {
		    	 if (i.intValue() == selectCity) {
		    		 allowedCities.remove(i);
		    		 break;
		    	 }
		     }
		     
		     //在禁忌表中添加select city
		     tabu.add(Integer.valueOf(selectCity));
		     //将当前城市改为选择的城市
		     currentCity = selectCity;
		     
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
	     for (int i = 0; i < this.tabu.size(); i++) {
	       len += distanceGraph[this.tabu.get(i).intValue()][this.tabu.get(i+1).intValue()];
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
	 public  double[][]  caldistmat(int n){
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
		   return allowedCities;
	   }
	 
	   public void setAllowedCities(Vector<Integer> allowedCities) {
	     this.allowedCities = allowedCities;
	   }
	
	   public int getTourLength() {
	     tourLength = calculateTourLength();
	     return tourLength;
	   }
	   public void setTourLength(int tourLength) {
	     this.tourLength = tourLength;
	  }
	  public int getCityNum() {
	     return cityNum;
	   }
	  public void setCityNum(int cityNum) {
	     this.cityNum = cityNum;
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
		   return firstCity;
	   }
	 
	   public void setFirstCity(int firstCity) {
		   this.firstCity = firstCity;
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
	   
	   
}
