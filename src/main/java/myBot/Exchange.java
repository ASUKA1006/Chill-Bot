package myBot;

import java.io.IOException;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Exchange {
	JSONObject root;
	String AccessKey;
	String BaseURL;
	public final String endpoint  = "live";
	
	public Exchange(JSONObject root) {
		this.root =root;
		this.AccessKey = root.getString("accesskey");
		this.BaseURL = root.getString("url");
	
	}
	
	
	public void showRates(String msg, MessageReceivedEvent event) throws ClientProtocolException, IOException {
		//API
		// get the response from there
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		// access the web site
		// send a request to it
		HttpGet get = new HttpGet(BaseURL + endpoint +"?access_key=" + AccessKey);
		
		Date date = new Date();
		TextChannel textChannel = event.getTextChannel();
		
		//Note, if you want to use a few channel type and don't check it before using a specific channel,
		//this might return null pointer exception
		if (event.isFromType(ChannelType.TEXT)) 
		{
			if (msg.contains("rate")||msg.equalsIgnoreCase("rate")) {
				
				try 
				{
					CloseableHttpResponse response = httpClient.execute(get);
					HttpEntity entity = response.getEntity();
					JSONObject exchangeRates = new JSONObject(EntityUtils.toString(entity));
					
					textChannel.sendMessage("Currency Exchange Rates Today \n").queue();
					textChannel.sendMessage(": " + date.toString() + "\n").queue();
					textChannel.sendMessage("1 " + exchangeRates.getString("source") + " in CAD : " + exchangeRates.getJSONObject("quotes").getDouble("USDCAD")).queue();
					response.close();
				}
				catch(ClientProtocolException e) 
				{
					System.out.println(e.getMessage());
				}
				catch(IOException e) 
				{
					System.out.println(e.getMessage());
				}
				catch(JSONException e)
				{
					System.out.println(e.getMessage());
				}
			}
			else {
				
			}
		}
	}



}	