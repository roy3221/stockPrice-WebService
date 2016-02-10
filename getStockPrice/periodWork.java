package GetStockPrice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
//import java.net.MalformedURLException;
//import java.rmi.RemoteException;
//import java.util.Timer;
import java.util.TimerTask;

import javax.xml.rpc.ServiceException;

public class periodWork {
	ExtractInfo info= new ExtractInfo();
	OutResult Out= new OutResult();
	String[] result;
	boolean flag=false;
	
	@SuppressWarnings({ })
	public TimerTask period(){
		TimerTask Task= new TimerTask(){
			public void run() {
			try {
				result = info.GetData();
			} catch (MalformedURLException | RemoteException
					| ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String n="\r\n";
//write the data to file each time get it.//
			try {
				result = info.GetData();
				Out.writeToFile("System Time: "+Out.getTime());
				Out.writeToFile("  EST Time: "+Out.getESTTime());
				Out.writeToFile(n);
			    Out.writeToFile(result);
			    Out.writeToFile(n);
			} catch (IOException | ServiceException e) {
				
				e.printStackTrace();
			}
System.out.println(Out.k+1+" qoutation(s) recieved.");
				info.newList();
					for(int i=0; i<30;i++){
						info.listOfLists.get(i).add(info.Price[i]);
						System.out.println(info.listOfLists.get(i));
					}
					Out.k++;
	}
		};
		return Task;
	}
	
	public  TimerTask end(){
		TimerTask endTask= new TimerTask(){
			public void run() {
				//flag=true;
				double maxSD=0;
				String n="\r\n";
				for(int i=0; i<30;i++){
					Out.size[i]=info.listOfLists.get(i).size();
				}
				CalculateSD cal= new CalculateSD(info.listOfLists,Out.size);
				double [] SD= cal.calculate();
				for(int i=0; i<30;i++){
					System.out.println("The Stand deviation of "+info.Symbol[i]+" is "+SD[i]);
					try {
					Out.writeToFile("The Stand deviation of "+info.Symbol[i]+" is "+SD[i]);
				    Out.writeToFile(n);
					}catch (IOException e) {
						
						e.printStackTrace();
					}
				}
				int maxindex=cal.MaxSD();
				maxSD=cal.max;
				System.out.println("The stock that has max Stand deviation is "+info.Symbol[maxindex]+" ,and its value is "+maxSD);
				try {
					result = info.GetData();
					Out.writeToFile("System Time: "+Out.getTime());
					Out.writeToFile("  EST Time: "+Out.getESTTime());
					Out.writeToFile(n);
				    Out.writeToFile("The stock that has max Stand Deviation is "+info.Symbol[maxindex]+" ,whose Stand Deviation= "+maxSD);
				    Out.writeToFile(n);
				} catch (ServiceException | IOException e) {
					
					e.printStackTrace();
				}
				Out.k=0;
				info.listOfLists.clear();
				System.out.println("I am done");
				System.exit(0);
				return;
				
			}
		};
		return endTask;

		
	}
	

}

