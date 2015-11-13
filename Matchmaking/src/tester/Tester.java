package tester;

import com.pubnub.api.Callback;

import matchmaking.*;

public class Tester {
	
	public static void main(String[] args) {
		// Add own pubnub keys.
		Matchmaker match = new Matchmaker("", "");
		match.setName("Player " + (int)Math.floor(5 + Math.random() * 4));
		match.setToken(6);
		
		match.setCallback("talk", new Callback() {
			
			@Override
			public void successCallback(String channel, Object message) {
				System.out.println("Talk: " + channel + ", " + message);
			}
			
		});
		
		match.setCallback("connect", new Callback() {
			
			@Override
			public void successCallback(String channel, Object message) {
				System.out.println("Connect: " + channel + ", " + message);
			}
			
		});
		
		match.setCallback("disconnect", new Callback() {
			
			@Override
			public void successCallback(String channel, Object message) {
				System.out.println("Disconnect: " + channel + ", " + message);
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
			match.send(new Message("talk").put("value", "Hello there from " + match.getName()));
		}
		match.leave();
		match.sleep(2000);
		match.close();
		
	}
	
}
