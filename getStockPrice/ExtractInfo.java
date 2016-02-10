package GetStockPrice;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;







import NET.webserviceX.www.StockQuoteLocator;
import NET.webserviceX.www.StockQuoteSoapStub;

public class ExtractInfo {
	
	 static String result;
	 String DJsymbol[]={"MMM","AXP","AAPL","BA","CAT","CVX","CSCO","KO","DD","XOM","GE","GS","HD","INTC","IBM","JNJ","JPM","MCD","MRK","MSFT","NKE","PFE","PG"
			 ,"TRV","UNH","UTX","VZ","V","WMT","DIS"};
	 String[] Symbol = new String[30];
	 String[] Price = new String[30];
	 String[] Combine = new String[30];
	 List<List<String>> listOfLists = new ArrayList<List<String>>();
	 
	public void newList(){
		for (int i=0;i<30;i++){
			listOfLists.add(new ArrayList<String>());
		 }
	}
	 

	//get a quote from web server;//
	public void GetQuote(String symbol) throws ServiceException, MalformedURLException, RemoteException{
		StockQuoteLocator locator = new StockQuoteLocator();
		locator.setEndpointAddress("StockQuoteSoap", "http://www.webservicex.net/stockquote.asmx");
	    java.net.URL url = new java.net.URL(locator.getStockQuoteSoapAddress());
	    StockQuoteSoapStub stock = new StockQuoteSoapStub(url,locator);
	    result= stock.getQuote(symbol);
		}
	
	// get the symbol of the quote//
	public String GetStocksymbol() throws ServiceException, MalformedURLException, RemoteException{
		int indexStart=ExtractInfo.result.indexOf("<Symbol>");
	    int indexOver=ExtractInfo.result.indexOf("<Symbol>");
	    int indexofSymbolStart=indexStart;
		String name= ExtractInfo.result.substring(indexofSymbolStart,indexOver);
	    return name;
		}
	
	// get the price of the quote//
	public String GetStockprice() throws ServiceException, MalformedURLException, RemoteException{
	    String price= ExtractInfo.result.substring(47,52);
	    return price;
		}
	// keep all the Dow Jones symbols//
	public String GetDJsymbol(int i){
		String error="there is no such a Dow Jones Stock";
		String symbol= error;
		if (0<i&&i<29){
		 symbol=DJsymbol[i];
		}
		return symbol;
	}

	public String[] GetData() throws MalformedURLException, RemoteException, ServiceException {

	 	ExtractInfo stock= new ExtractInfo();	
		for(int i=0;i<30;i++){
			 stock.GetQuote(stock.DJsymbol[i]);
			 int indexOver=ExtractInfo.result.indexOf("</Symbol>");
			 int indexStart=ExtractInfo.result.indexOf("<Symbol>");
			 int pOver=ExtractInfo.result.indexOf("</Last>");
			 int pStart=ExtractInfo.result.indexOf("<Last>");
			 int indexname=indexStart+8;
			 int indexprice= pStart+6;
			 Symbol[i]= ExtractInfo.result.substring(indexname,indexOver);
			 Price[i]= ExtractInfo.result.substring(indexprice,pOver);
			 Combine[i]="<"+Symbol[i]+", "+Price[i]+">";
		}
		return Combine;
		
	}
}
