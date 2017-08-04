package util;

import java.util.Random;

public class SortDemo {
	
	    public static int[] arr4fastSort=null;
	    public static int[] arr4bubbleSort=null;
	    
		public static void main(String[] args) {
			/**
			 * [(30),1,7,40,32,45,2,56,12,23,9,7,60]
			 * 小与等于30的个数为：7
			 * 大于30的个数为：5
			 * 所以30的最终位置应角标应为7
			 * [2,1,7,12,23,*45,56,（30）,40,32,*9,7,60]
			 */
			Random r = new Random();
			int len=100000;
			arr4fastSort=new int[len];
			arr4bubbleSort=new int[len];
			for(int i=0;i<len;i++){
				  int num=r.nextInt(1000)+1;
				  arr4fastSort[i]=num;
				  arr4bubbleSort[i]=num;
			}
			
			
			long t = System.currentTimeMillis();
			bubbleSort();
			System.out.println("冒泡排序耗时"+(System.currentTimeMillis()-t)+"毫秒！");
			
			t = System.currentTimeMillis();
			fastSort(0, arr4fastSort.length-1);
			System.out.println("快速排序耗时"+(System.currentTimeMillis()-t)+"毫秒！");
			
			/*printArr(arr4bubbleSort);
			printArr(arr4fastSort);*/
			checkSame();
			
		}
		
		
		public static void fastSort(int fromIndex,int toIndex){
				  if(fromIndex>=toIndex){
					  	return;
				  }
				  
				  int rulerIndex=fromIndex;
				  int rulerNum=arr4fastSort[rulerIndex];
				  
				  int finalIndexOfRulerNum=0;
				  int countOfleRulerNum=0;//小与等于标尺数字的数字个数
				  for(int i=fromIndex+1;i<=toIndex;i++){
						  int currNum=arr4fastSort[i];
						  if(currNum<=rulerNum){//当前的数字小与等于比标尺数字
							  countOfleRulerNum++;
						  }
				  }
				  finalIndexOfRulerNum=countOfleRulerNum+fromIndex;
				  
				  //交换rulerIndex和finalIndexOfRulerNum两角标位置处的数字
				  arr4fastSort[rulerIndex]=arr4fastSort[finalIndexOfRulerNum];
				  arr4fastSort[finalIndexOfRulerNum]=rulerNum;
				  
				  /*遍历fromIndex————finalIndexOfRulerNum-1之间的数字，
				   * 每发现一个大于rulerNum的数字，则再查看finalIndexOfRulerNum+1————toIndex之间的数字，
				   * 则必有一个小与等于rulerNum的数字
				   * [1,2,7,12,23,*45,56,（30）,40,32,*9,7,60]*/
				  
				  /*int leftIndex=fromIndex;*/
				  int rightIndex=finalIndexOfRulerNum+1;
				  for(int i=fromIndex;i<finalIndexOfRulerNum;i++){
					     if(arr4fastSort[i]>rulerNum){
					    	 	for(int j=rightIndex;j<=toIndex;j++){
						    	 		 rightIndex++;
						    	 		 if(arr4fastSort[j]<=rulerNum){
							    	 			 int tempNum=arr4fastSort[i];
							    	 			 arr4fastSort[i]=arr4fastSort[j];
							    	 			 arr4fastSort[j]=tempNum;
							    	 			 break;
						    	 		 }
					    	 	}
					     }
				  }
				  fastSort(fromIndex, finalIndexOfRulerNum-1);
				  fastSort(finalIndexOfRulerNum+1, toIndex);
		}
		
		
		public static void bubbleSort(){
			        int len=arr4bubbleSort.length;
			        for (int i = 0; i < len; i++){    //最多做n-1趟排序
				           for(int j = 0 ;j < len - i - 1; j++){    //对当前无序区间score[0......length-i-1]进行排序(j的范围很关键，这个范围是在逐步缩小的)
					               if(arr4bubbleSort[j] < arr4bubbleSort[j + 1]){    //把小的值交换到后面
						                   int temp = arr4bubbleSort[j];
						                   arr4bubbleSort[j] = arr4bubbleSort[j + 1];
						                   arr4bubbleSort[j + 1] = temp;
					               }
				            }            
			        }
		}
		
		
		public static void checkSame(){
			  if(arr4bubbleSort.length!=arr4fastSort.length){
				  System.out.println("不相同！");
				  return;
			  }
			  int len=arr4bubbleSort.length;
			
			  for(int i=0;i<len;i++){
				    	if(arr4bubbleSort[i]!=arr4fastSort[len-1-i]){
				    		  System.out.println("不相同！");
				    		  return;
				    	}
			  }
			  System.out.println("完全一样！");
		}
		
		
		public static void printArr(int[] arr){
				 StringBuilder sb = new StringBuilder("[");
				 int len=arr.length;
				 for(int i=0;i<len;i++){
					   sb.append(arr[i]);
					   if(i<(len-1)){
						   sb.append(",");
					   }
				 }
				 sb.append("]");
				 System.out.println(sb.toString());
		}
}
