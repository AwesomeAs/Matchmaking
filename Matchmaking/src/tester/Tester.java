package tester;

import com.pubnub.api.Callback;

import matchmaking.*;

public class Tester {
	
	public static void main(String[] args) {
		// Add own pubnub keys.
		Matchmaker match = new Matchmaker("my pubnub publish key", "my pubnub subscribe key");
		match.setName("Player " + (int)Math.floor(5 + Math.random() * 4));
		match.setToken(6);
		
		match.setCallback("talk", new Callback() {
			
			@Override
			public void successCallback(String channel, Object message) {
				System.out.println("Talk: " + channel + ", " + message);
			}
			
		});
		
		match.sleep(1000);
		System.out.println(match.getName());
		match.sleep(2000);
		System.out.println("are we in match_head? " + match.inChannel("match_head"));
		match.search(1);
		match.sleep(5000);
		System.out.println("Searching for a match: " + match.isSearching() + ", in match: " + match.inMatch());
		System.out.println("No longer searching for a match");
		if (match.inMatch()) {
			match.send(new Message("talk", "Hello there from " + match.getName()));
		}
		match.stop();
		match.sleep(2000);
		match.close();
		
	}
	
}
