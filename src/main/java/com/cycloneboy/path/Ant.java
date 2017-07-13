/**
 * 文件名    :Ant.java
 * 项目名称:GraphAglo
 * 描述信息:
 * 版本信息: V1.0
 * 创建日期:2017年4月13日
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
public class Ant implements Cloneable{
	private Vector<Integer> tabu; //禁忌表
	private Vector<Integer> allowedCities; //允许搜索的城市
	private float[][] delta; //信息数变化矩阵
	private int[][] distance; //距离矩阵
	   
	private float alpha; 
	private float beta;
	   
    private int tourLength; //路径长度
    private int cityNum; //城市数量
	    
	private int firstCity; //起始城市
	private int currentCity; //当前城市
	   
	public Ant(){
	     cityNum = 30;
	     tourLength = 0;
	    
	  }
	  
  /**
   * Constructor of Ant
   * @param num 蚂蚁数量
 */
	   public Ant(int num){
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
	  public void selectNextCity(float[][] pheromone){
	     float[] p = new float[cityNum];
	     float sum = 0.0f;
	     //计算分母部分
	     for (Integer i:allowedCities) {
	    	 //计算每个节点的信息素求出前往每个节点的概率，分母部分
	    	 sum += Math.pow(pheromone[currentCity][i.intValue()], alpha)
	    			 *Math.pow(1.0/distance[currentCity][i.intValue()], beta);
	     }
	     //计算概率矩阵
	     for (int i = 0; i < cityNum; i++) {
	       boolean flag = false;
	       for (Integer j:allowedCities) {
	         
	         if (i == j.intValue()) {
	        	 p[i] = (float) (Math.pow(pheromone[currentCity][i], alpha)
	        		  *Math.pow(1.0/distance[currentCity][i], beta))/sum;
	        	 flag = true;
	        	 break;
	         }
	       }
	       
	       if (flag == false) {
	         p[i] = 0.f;
	       }
	     }
	     
	     //轮盘赌选择下一个城市
	     Random random = new Random(System.currentTimeMillis());
	     float sleectP = random.nextFloat();
	     int selectCity = 0;
	     float sum1 = 0.f;
	     for (int i = 0; i < cityNum; i++) {
	    	 sum1 += p[i];
	    	 if (sum1 >= sleectP) {
	    		 selectCity = i;	//选择第i个城市作为下一个要访问的城市
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
	  
	  /**
	    * 计算路径长度
	    * 获取禁忌表中的路径长度
	    * @return 路径长度
	    */
	 private int calculateTourLength(){
		 int len = 0;
	     for (int i = 0; i < cityNum; i++) {
	       len += distance[this.tabu.get(i).intValue()][this.tabu.get(i+1).intValue()];
	     }
	     return len;
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

}
