//
package myBot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.security.auth.login.LoginException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class App extends ListenerAdapter {
	
	public static void main(String [] args) throws LoginException, InterruptedException, JSONException, FileNotFoundException {		
		JSONObject root = new JSONObject(new JSONTokener(new FileInputStream(new File("secret.json"))));
		JDABuilder builder = new JDABuilder(AccountType.BOT);
		String Token = root.getString("token");
		builder.setToken(Token);
		builder.addEventListener(new App());
		//put JDA object into the variable
		JDA jda = builder.buildBlocking();		
		User asuka = jda.getUserById(root.getLong("userId"));
		asuka.openPrivateChannel();
		
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		Message message = event.getMessage();
		MessageChannel channel = event.getChannel();
		String msg = message.getContentDisplay();
		User author = event.getAuthor();
		boolean bot = author.isBot();
		
		if(bot) {
			return;
		}
		
		if (msg.equalsIgnoreCase("hello")) {
			channel.sendMessage("Welcome!").queue();
		}else if (msg.equalsIgnoreCase("hi")) {
			channel.sendMessage("Welcome to Discord!").queue();
		}else if (msg.equalsIgnoreCase("good morning")) {
			channel.sendMessage("Good morning! Did you sleep well?").queue();
		}else if (msg.contains("good")||msg.contains("Good")) {
			channel.sendMessage("yay!").queue();
		}else {
			channel.sendMessage("ack " + msg).queue();
		}
	}
	
}	
