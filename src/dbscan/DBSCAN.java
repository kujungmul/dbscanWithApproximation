package dbscan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;


public class DBSCAN {
	
	
	
	public static void run(ArrayList<point> pList, double minPts, double eps){
		
		int C = 1;
		
		for(point P : pList){
						
			if(P.isVisited()==true){
				continue;
			}
			
			P.setVisited(true);
						
			//NeighborPts = regionQuery(P, eps)
			Queue<point> neighborPts = regionQuery(P, eps, pList);
			
			if(neighborPts.size() < minPts){
				P.setStates(GlobalVar.NOISE);
			}
			else{
				expandCluster(P, neighborPts, C, eps, minPts, pList);
				C++;
			}	
		}
	}

	private static void expandCluster(point p, Queue<point> neighborPts, int c, double eps, double minPts, ArrayList<point> pList) {
		
		p.setCluster_id(c);
		Queue<point> neighbor_bar;
		
		while(!neighborPts.isEmpty())
		{
			point p_bar = neighborPts.poll();
			if(p_bar.isVisited()==false){
				p_bar.setVisited(true);
				neighbor_bar = regionQuery(p_bar, eps, pList);
				if(neighbor_bar.size() >= minPts){
					neighborPts.addAll(neighbor_bar);
				}
			}
			if (p_bar.getCluster_id()==-1){
				p_bar.setCluster_id(c);
			}
		}
	}

	private static Queue<point> regionQuery(point p, double eps, ArrayList<point> pList) {
		
		Queue<point> ss = new LinkedList<point>();
		
		int currentIdx = p.getIdx();
		
		for(point Q : pList){
			if(Util.dist(p, Q) < eps){
				ss.offer(Q);
			}
		}
		
		return ss;
	}
}
