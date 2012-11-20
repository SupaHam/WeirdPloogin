package me.ialihd.weirdploogin2.schedulers;

import me.ialihd.weirdploogin2.WeirdPloogin;


public class AntiSpamScheduler implements Runnable{

	@Override
	public void run() {
		WeirdPloogin.spamMessages.clear();
	}
	
}
