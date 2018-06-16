
package myBot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.security.auth.login.LoginException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.User;

public class App {

	public static void main(String[] args) throws LoginException, InterruptedException, JSONException, IOException {

		JSONObject root = new JSONObject(new JSONTokener(new FileInputStream(new File("secret.json"))));
		JDABuilder builder = new JDABuilder(AccountType.BOT);
		String Token = root.getString("token");
		builder.setToken(Token);
		builder.addEventListener(new Messages(new Exchange(root)));
		
		// put JDA object into the variable
		JDA jda = builder.buildBlocking();
		User asuka = jda.getUserById(root.getLong("userId"));
		asuka.openPrivateChannel();
		
		

	}

}
