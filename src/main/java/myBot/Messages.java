package myBot;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Messages extends ListenerAdapter{
	Exchange exchange;
	
	public Messages(Exchange exchange){
		this.exchange = exchange;
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {	
		MessageChannel channel = event.getChannel();
		Message message = event.getMessage();		
		String msg = message.getContentDisplay();
		User author = event.getAuthor();
		boolean bot = author.isBot();
		
		if(bot) {
			return;
		}
		try {
			this.exchange.showRates(msg, channel);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (msg.equalsIgnoreCase("hello")) {
			channel.sendMessage("Welcome!").queue();
		}else if (msg.equalsIgnoreCase("hi")) {
			channel.sendMessage("Welcome to Discord!").queue();
		}else if (msg.equalsIgnoreCase("good morning")) {
			channel.sendMessage("Good morning! Did you sleep well?").queue();
		}else if (msg.contains("good")||msg.contains("Good")) {
			channel.sendMessage("yay!").queue();
		}else if (!msg.contains("rate")) {
			channel.sendMessage("ack " + msg).queue();
		}
	}

}
