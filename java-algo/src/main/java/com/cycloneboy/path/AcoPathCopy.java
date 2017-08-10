/**
 * 文件名    :AcoPath.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月14日
 * 作者        :CycloneBoy
 *//*


import com.cycloneboy.cycloneboy.path;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

*/
/**
 * @author CycloneBoy
 *
 *//*

public class AcoPathCopy {
	private path.AntPath[] ants; //蚂蚁
	private int antNum; //蚂蚁数量
	private int cityNum; //城市数量
	private int MAX_GEN; //运行代数
	private float[][] pheromone; //信息素矩阵
	private int[][] distance; //距离矩阵
	//private double[][] distanceGraph;//图中任意两点间的距离矩阵
	private int[] bestLength; //最佳长度
	private int bestLengthPath;
	private double[] bestLengthAve; // 记录各代路线的平均长度
	private int[][] bestTour; //最佳路径
    private int[] bestTourPath;
	
	//三个参数
	private float alpha; 
	private float beta;
	private float rho;
  
	*/
/**
	 * 空构造函数
	 *//*

	public AcoPathCopy(){
    
	}
	
	*/
/**
	 * 构造函数: constructor of ACO
	 * @param n 城市数量
	 * @param m 蚂蚁数量
	 * @param g 运行代数
	 * @param a alpha 
	 * @param b beta
	 * @param r rho
	 * 
	 **//*

	public AcoPathCopy(int n, int m, int g, float a, float b, float r) {
	   cityNum = n;
	   antNum = m;
	   ants = new path.AntPath[antNum];
	   MAX_GEN = g;
	   alpha = a;
	   beta = b;
	   rho = r;
   }
   
   @SuppressWarnings("resource")
   */
/**
    * 初始化ACO算法类
    * @param filename 数据文件名，该文件存储所有城市节点坐标数据
    * @throws IOException
    *//*

   private void init(String filename) throws IOException{
	   //读取数据  
       int[] x;  
       int[] y;  
       String strbuff;  
       BufferedReader data = new BufferedReader(new InputStreamReader(
    		   new FileInputStream(filename)));  //获取文件数据源，tsp数据源
         
       distance = new int[cityNum][cityNum];  
       x = new int[cityNum];  
       y = new int[cityNum];  
        for (int i = 0; i < cityNum; i++) {  
             strbuff = data.readLine(); 
             String[] strcol = strbuff.split(" ");  //读取一行
             x[i] = Integer.valueOf(strcol[1]);  
             y[i] = Integer.valueOf(strcol[2]); 
             System.out.println(" i = " +i+ "    x["+i+"]= "+ x[i] + 
            		 "    y["+i+"]= " + y[i]);//打印数据调试
        }  
         //计算距离矩阵 ，针对具体问题，距离计算方法也不一样，此处用的是att48作为案例，它有48个城市，
         //距离计算方法为伪欧氏距离，最优值为10628 
         for (int i = 0; i < cityNum - 1; i++) {  
             distance[i][i] = 0;  //对角线为0
             for (int j = i + 1; j < cityNum; j++) { 
            	 double rij = Math.sqrt(((x[i] - x[j]) * (x[i] - x[j])+ (y[i] - y[j]) * (y[i] - y[j]))/10.0);
            	 int tij = (int) Math.round(rij);
            	 if (tij < rij) {
            		 distance[i][j] = tij + 1;  
            		 distance[j][i] = distance[i][j];  
            	 }else {
            		 distance[i][j] = tij;  
                     distance[j][i] = distance[i][j]; 
            	 }
             }  
         } 
         
        distance[cityNum - 1][cityNum - 1] = 0;  
        
        //初始化信息素矩阵  
        pheromone=new float[cityNum][cityNum];  
        for(int i=0;i<cityNum;i++)  
        {  
             for(int j=0;j<cityNum;j++){  
                 pheromone[i][j]=0.1f;  //初始化为0.1
             }  
        }  
        
        bestLength = new int[MAX_GEN]; 
        bestLengthPath = Integer.MAX_VALUE;
        bestTour = new int[MAX_GEN][cityNum];
        bestTourPath = new int[cityNum];
        bestLengthAve = new double[cityNum];
        //随机放置蚂蚁  
        for(int i=0;i<antNum;i++){  
             ants[i]=new path.AntPath(cityNum);
             //ants[i].init(distance, alpha, beta);  
        }  
   }
  
   */
/**
    * 进行蚁群算法计算
    * 输出具体的结果
    * 描述:如果达到最大代数MAX_GEN，算法终止，转到第（5）步；否则，重新初始化所有的蚂蚁的Delt矩阵
    * 	    所有元素初始化为0，Tabu表清空，Allowed表中加入所有的城市节点。随机选择它们的起始位置（也
    *    可以人工指定）。在Tabu中加入起始节点，Allowed中去掉该起始节点，重复执行（2），（3）,(4)步。
    * @author CycloneBoy
    * 
    *//*

   public void solve(){
     boolean flagFindDes = false; //是否找到目的地
     for (int g = 0; g < MAX_GEN; g++) {		//MAX_GEN次蚂蚁迭代
       for (int i = 0; i < antNum; i++) {		//某一代antNum只蚂蚁迭代结束
    	   for (int j = 1; j < cityNum; j++) {	//一只蚂蚁到达终点或者无路可走
    		   flagFindDes = ants[i].selectNextCity(pheromone);  //行走完一次路径
    		   if (flagFindDes == true) {
    			   break;  //跳出本次循环，找到一条路径
    		   }
    	   }
    	   //ants[i].getTabu().add(ants[i].getFirstCity()); //添加第一个出发城市
    	 
    	   if (ants[i].getTourLength() < bestLengthPath) {    //判断这一次是否是到目前为止的最短路径
    		   bestLengthPath = ants[i].getTourLength();
    		   bestLength[g] = ants[i].getTourLength();
    		   for (int k = 0; k < ants[i].getTabu().size() + 1; k++) {	  //是目前为止的最短路径，就保存这条最短路径
    			   bestTourPath[k] = ants[i].getTabu().get(k).intValue();
    			   bestTour[g][k] = bestTourPath[k];//某一次迭代的最好路径
    		   }
    		   System.out.println("----->第"+g+" 次迭代的第"+i+" 只蚂蚁找到一条最短路径，长度为:"+bestLengthPath + "   ,最好路径:" +bestTourPath);
    	   }
    	   //经过一次迭代之后，城市i与j之间信息素经过一个迭代后的增量。
    	   for (int j = 0; j < ants[i].getTabu().size(); j++) {
    		   ants[i].getDelta()[ants[i].getTabu().get(j).intValue()][ants[i].getTabu().get(j+1).intValue()] = (float) (1./ants[i].getTourLength());
    		   ants[i].getDelta()[ants[i].getTabu().get(j+1).intValue()][ants[i].getTabu().get(j).intValue()] = (float) (1./ants[i].getTourLength());
    	   }
       }//某一代所有蚂蚁迭代结束
       
       for (int i = 0; i < ants.length; i++) {
    	   bestLengthAve[g]+=bestLength[i];
       }
       bestLengthAve[g] = bestLengthAve[g]/antNum;//计算某次迭代的平均路径
       System.out.println("第"+g+" 次迭代结束,最短路径:"+bestLengthPath +" ,最短平均路径为: "+bestLengthAve[g]);
   	
       //更新信息素，第五步：更新信息素
       updatePheromone();
       
       //%第六步：更新启发因子信息
//       if (g > 10) {		//10轮之后再更新
//    	   int[] bestpath = new int[cityNum];
//    	   double bestpathLength = bestLength[g];
//    	   for (int i = 0; i < bestpath.length; i++) {
//    		   bestpath[i] =  bestTour[g][i];//这次迭代的最好路径
//    	   }
//    	   
//    	   for (int i = 1; i < bestpath.length; i++) {
//    		   
//    	   }
//       }
       
       //重新初始化蚂蚁
       for(int i=0;i<antNum;i++){  
            //ants[i].init(distance, alpha, beta);  
       }
       
       
     }
     
     //打印最佳结果，输出TSP最优解
     printOptimal();
   }
  
	  */
/**
	   * 进行蚁群算法计算
	   * 输出具体的结果
	   * 描述:如果达到最大代数MAX_GEN，算法终止，转到第（5）步；否则，重新初始化所有的蚂蚁的Delt矩阵
	   * 	    所有元素初始化为0，Tabu表清空，Allowed表中加入所有的城市节点。随机选择它们的起始位置（也
	   *     可以人工指定）。在Tabu中加入起始节点，Allowed中去掉该起始节点，重复执行（2），（3）,(4)步。
	   *     
	   * @author CycloneBoy
	   *//*

	  public void solvePath(){
		   
		  for (int g = 0; g < MAX_GEN ; g++) {		//nc_max次蚂蚁迭代 ,%停止条件之一：达到最大迭代次数
			  //第二步：将m只蚂蚁放到起点上 
			  //第三步：m只蚂蚁按概率函数选择下一个节点，完成各自的周游
			  for (int i = 0; i < antNum; i++) {		//某一代antNum只蚂蚁迭代结束
				  
			  }
		  }
	  }
   
   
   */
/**
    * 每一代蚂蚁迭代结束后，更新信息素
    * 令t = t + n，按照（公式3）更新信息素矩阵pheromone。
    * @author CycloneBoy
    *//*

   private void updatePheromone(){
	   	//信息素挥发  
         for(int i=0;i<cityNum;i++)  
             for(int j=0;j<cityNum;j++)  
                 pheromone[i][j]=pheromone[i][j]*(1-rho);  //挥发后剩下的信息素
         //信息素更新  
         for(int i=0;i<cityNum;i++){  
            for(int j=0;j<cityNum;j++){  
                 for (int k = 0; k < antNum; k++) { //antNum只蚂蚁爬过之后的，在一条边上的增量
                	 pheromone[i][j] += ants[k].getDelta()[i][j];//新增加一部分
                 } 
            }  
         }  
   }
   
   */
/**
    * 最后一步:输出最优的路径
    * 
    * @author CycloneBoy
    *//*

   private void printOptimal(){
     System.out.println("The optimal length is: " + bestLength);
     System.out.println("The optimal tour is: ");
     for (int i = 0; i < cityNum + 1; i++) {
       System.out.println(bestTour[i]);
     }
   }
   
   public path.AntPath[] getAnts() {
	   return ants;
   }

  public void setAnts(path.AntPath[] ants) {
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
 

 
   */
/**
 * @return the bestLength
 *//*

public int[] getBestLength() {
	return bestLength;
}

*/
/**
 * @param bestLength the bestLength to set
 *//*

public void setBestLength(int[] bestLength) {
	this.bestLength = bestLength;
}

*/
/**
 * @return the bestLengthPath
 *//*

public int getBestLengthPath() {
	return bestLengthPath;
}

*/
/**
 * @param bestLengthPath the bestLengthPath to set
 *//*

public void setBestLengthPath(int bestLengthPath) {
	this.bestLengthPath = bestLengthPath;
}

*/
/**
 * @return the bestTour
 *//*

public int[][] getBestTour() {
	return bestTour;
}

*/
/**
 * @param bestTour the bestTour to set
 *//*

public void setBestTour(int[][] bestTour) {
	this.bestTour = bestTour;
}

*/
/**
 * @return the bestTourPath
 *//*

public int[] getBestTourPath() {
	return bestTourPath;
}

*/
/**
 * @param bestTourPath the bestTourPath to set
 *//*

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
 

   */
/**
    * 测试48个城市的TSP问题
    * 描述: 在该java实现中我们选择使用tsplib上的数据att48，这是一个对称tsp问题，
    * 	        城市规模为48，其最优值为10628.其距离计算方法如图（2）所示：
    *      实现中，使用了两个java类，一个Ant类，一个ACO类。
    *      25次迭代后的最优解为:12280
    * @param args
    * @throws IOException 
    *//*

//   public static void main(String[] args) throws IOException {
//	   AcoPathCopy aco = new AcoPathCopy(48, 10, 30, 1.f, 5.f, 0.5f);//城市数量、蚂蚁数量、迭代测试、alpha、beta、rho
//	   aco.init("src//com//algo//path//data.txt");
//	   aco.solve();
//   }
 
}
*/
