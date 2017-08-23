package dbscan;

import java.util.ArrayList;

class point{
	private int idx;
	private double x;
	private double y;
	private int cluster_id;
	private boolean visited;
	private int states;
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getStates() {
		return states;
	}

	public void setStates(int states) {
		this.states = states;
	}

	public int getCluster_id() {
		return cluster_id;
	}

	public void setCluster_id(int cluster_id) {
		this.cluster_id = cluster_id;
	}

	public point(int idx){
		this.idx = idx;
		this.cluster_id = -1;
		this.visited = false;
		this.states = 0;
	}
	
	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public point(double x, double y){
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "point [idx=" + idx + ", x=" + x + ", y=" + y + ", cluster_id=" + cluster_id + ", visited=" + visited
				+ ", states=" + states + "]" + "\n";
	}

}

public class ptrList {
	private ArrayList<point> list;
	public ptrList(){
		this.list = new ArrayList<point>();
	}
	public ArrayList<point> getList() {
		return list;
	}
	public void setList(ArrayList<point> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "ptrList [list=" + list + "]";
	}
}
