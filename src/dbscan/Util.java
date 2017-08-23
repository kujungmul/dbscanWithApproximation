package dbscan;

public class Util {
	public static double dist(point X, point Y){
		return Math.sqrt(Math.pow((X.getX()-Y.getX()), 2) + Math.pow((X.getY()-Y.getY()), 2));
	}
}
