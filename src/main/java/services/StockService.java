package services;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import models.StockDTO;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class StockService {
    
    private final String apiKey = "API_KEY9c0AMPNKUPDIUUHRUHL48M6YW76VBQZ4";
    
    public List<StockDTO> find_stocks(String stocks) throws ClientProtocolException, IOException{        
        String url = "https://api.finage.co.uk/last/stocks/?apikey="+apiKey+"&symbols="+stocks;
        List<StockDTO> outputList = null;
        
        HttpGet request = new HttpGet(url);
        try(CloseableHttpClient httpClient = HttpClientBuilder.create().disableDefaultUserAgent().build();
                CloseableHttpResponse response = httpClient.execute(request)){
            HttpEntity entity = response.getEntity();
            
            if (entity != null){
                String result = EntityUtils.toString(entity);
                Gson gson = new Gson();
                StockDTO[] list_stocks_response = gson.fromJson(result, StockDTO[].class);
                outputList = Arrays.asList(list_stocks_response);
            }
        }
        
        return outputList;
    }

    public List<StockDTO> find_stocks_test()throws ClientProtocolException, IOException {
        String response = "[{'symbol':'AAPL','ask':177.08,'bid':177.06,'asize':3,'bsize':15,'timestamp':1694529526609},{'symbol':'TSLA','ask':274.02,'bid':273.97,'asize':2,'bsize':9,'timestamp':1694529526491},{'symbol':'GOGL','ask':7.39,'bid':7.38,'asize':30,'bsize':1,'timestamp':1694529518185},{'symbol':'AMZN','ask':159.5,'bid':141.29,'asize':5,'bsize':4,'timestamp':1694529526480},{'symbol':'MSFT','ask':334.26,'bid':334.23,'asize':1,'bsize':3,'timestamp':1694529526477},{'symbol':'IBM','ask':147.01,'bid':147,'asize':2,'bsize':5,'timestamp':1694529522342}]";
        
        Gson gson = new Gson();
        StockDTO[] list_stocks_response = gson.fromJson(response, StockDTO[].class);
        List<StockDTO> stocks_list = Arrays.asList(list_stocks_response);
        
        //https://docs.marketdata.app/api/stocks/quotes
        //https://api.marketdata.app/v1/stocks/quotes/AAPL/
        //https://finage.co.uk/docs/api/multiple-stock-last-quotes
        
        return stocks_list;
    }
    
}
