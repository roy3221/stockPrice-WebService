package GetStockPrice;

import java.util.ArrayList;
import java.util.List;

public class CalculateSD {
	
	List<List<String>> SDlistOfLists = new ArrayList<List<String>>();
	int[] size= new int[30];
	double [] sum= new double[30];
	double [] mean= new double[30];
	double [] variance= new double[30];
	double [] SD= new double[30];
	double max= 0;
	public CalculateSD(List<List<String>> SDlistOfLists, int[] size){
		this.SDlistOfLists=SDlistOfLists;
		this.size=size;
	}
	
	public double[] calculate(){
		for(int i=0;i<30;i++){
			for(int j=0;j<size[i];j++){
				sum[i]= sum[i]+Double.parseDouble(SDlistOfLists.get(i).get(j));
			}
			mean[i]=sum[i]/size[i];
			
		}
		for(int i=0;i<30;i++){
			for(int j=0;j<size[i];j++){
				variance[i]= (variance[i]+Math.pow((Double.parseDouble(SDlistOfLists.get(i).get(j))-mean[i]),2))/size[i];
			}
			SD[i]=Math.sqrt(variance[i]);
		}
		return SD;
	}
	
	public int MaxSD(){
		int index=0;
		for(int i=0;i<30;i++){
			if(SD[i]>max){
				max=SD[i];
				index = i;
			}
		}
		return index;
	}
}
