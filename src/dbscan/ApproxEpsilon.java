package dbscan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;

public class ApproxEpsilon {
	
	
	public static double run(ArrayList<point> arrayList){
		double appVal = 0.0;
		
		int lengthOfList = arrayList.size();
		
		int sampleCnt = (int)((double)lengthOfList * GlobalVar.samplingRatio);
		
		System.out.println(sampleCnt);
		
		Random rand = new Random();
		HashSet<Integer> randomSample = new HashSet<Integer>();
		
		// Random Sampling to extract a small portion of all the data
		while(randomSample.size() != sampleCnt){
			int rnd = rand.nextInt(lengthOfList);
			if(randomSample.contains(rnd)){
				continue;
			}
			else{
				randomSample.add(rnd);
			}
		}
		
		// extract and add sampledData
		ArrayList<point> sampledData = new ArrayList<point>();
		for(Integer k : randomSample){
			sampledData.add(arrayList.get(k));
		}
		
		
		double[] thirdDistance = new double[sampledData.size()];
		
		int idThird = 0;
		for(point k : sampledData){
			int id = 0;
			double[] tempArray = new double[sampledData.size()-1];
			for (point l : sampledData){
				if(k.getIdx() == l.getIdx()){
					continue;
				}
				tempArray[id++] = Util.dist(k, l);
			}
			Arrays.sort(tempArray);
			//TODO: it is changable. 
			thirdDistance[idThird++] = tempArray[(int)(GlobalVar.minPts * GlobalVar.samplingRatio) -1];
		}
		
		Arrays.sort(thirdDistance);

		for( int i = 0; i < thirdDistance.length/2; ++i ) 
		{ 
		  double temp = thirdDistance[i]; 
		  thirdDistance[i] = thirdDistance[thirdDistance.length - i - 1]; 
		  thirdDistance[thirdDistance.length - i - 1] = temp; 
		}
		
		for(int i = 0; i < thirdDistance.length; i++){
			System.out.println(thirdDistance[i]);
		}
		
		double[][] ar = new double[2][thirdDistance.length];
		for(int i = 0; i < thirdDistance.length ; i++){
			ar[0][i] = i+1;
			ar[1][i] = thirdDistance[i];
		}
		
		for(int i = 0; i < thirdDistance.length; i++){
			System.out.println(ar[0][i] + "\t" + ar[1][i]);
		}
		
		double minY = thirdDistance[thirdDistance.length-1];
		double maxY = thirdDistance[0];
		double minX = 1;
		double maxX = thirdDistance.length;
		
		for(int i = 0; i < ar[1].length; i++){
			
			ar[0][i] = ar[0][i] / ar[1].length;
			ar[1][i] = (ar[1][i] - minY) / (maxY - minY);
			
		}
		
		for(int i = 0; i < ar[1].length; i++){
			System.out.println(ar[0][i] + "\t" + ar[1][i]);
		}
		
		double currentX;
		double currentY;
		
		for(int i = 0; i < ar[1].length; i++){
			currentX = ar[0][i];
			currentY = ar[1][i];
			
			ar[0][i] = Math.cos(-Math.PI / 4.0f) * currentX + Math.sin(-Math.PI / 4.0f)*(currentY - 1.0f) ;
			
			currentX = ar[0][i];

			
			ar[1][i] = -Math.sin(-Math.PI / 4.0f) * currentX + Math.cos(-Math.PI / 4.0f)*(currentY - 1.0f);

		}
		
		for(int i = 0; i < ar[1].length; i++){
			System.out.println(ar[0][i] + "\t" + ar[1][i]);
		}
		
		
		double minValue = 99999.99999;
		int minValueIdx = -1;
		
		for(int i = 0; i < ar[1].length; i++){
			if(ar[1][i] < minValue){
				minValueIdx = i;
				minValue = ar[1][i];
			}
		}
		
		//System.out.println(minValue);
		System.out.println("MinValue Index = "+minValueIdx);
		
		
		return thirdDistance[minValueIdx];
	}
}
