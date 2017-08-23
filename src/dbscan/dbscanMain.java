package dbscan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class dbscanMain {

	
	public static boolean printOn = true;

	public static void main(String[] args) throws IOException{
		
		ptrList pList = new ptrList();
		
		readFile(args[0], pList);
	
		DBSCAN.run(pList.getList(), GlobalVar.minPts, GlobalVar.ep);
		
	    if(dbscanMain.printOn==true){
	    	System.out.println(pList);
	    }
	    
	    System.out.println("DBSCAN(without approximation) finished!");
	}
	
	public static void readFile(String name, ptrList pList) throws IOException{
	    FileReader in=null;
		try {
			in = new FileReader(name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    BufferedReader br = new BufferedReader(in);

	    String s;
	    int index = 1;
	    while ((s=br.readLine()) != null) {
	        StringTokenizer st = new StringTokenizer(s,"\t");

			point newP = new point(index++);
			String temp;
			
			while(st.hasMoreTokens()){
				temp = st.nextToken();
				newP.setX(Double.parseDouble(temp));
				
				if(st.hasMoreElements()){
					temp = st.nextToken();
					newP.setY(Double.parseDouble(temp));
				}
			}
			pList.getList().add(newP);
	    }
	    
	    try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    if(dbscanMain.printOn==true){
	    	System.out.println(pList);
	    }
	}
}
