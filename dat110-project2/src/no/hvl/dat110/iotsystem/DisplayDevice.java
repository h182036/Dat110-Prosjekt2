package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");

		Client client = new Client("display", Common.BROKERHOST, Common.BROKERPORT);

		client.connect();

		client.createTopic(Common.TEMPTOPIC);
		client.subscribe(Common.TEMPTOPIC);

		for (int i = 0; i < COUNT; i++) {
			PublishMsg msg = (PublishMsg) client.receive();

			System.out.println("Temperature is: " + msg.getMessage());

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		client.disconnect();

		System.out.println("Display stopping ... ");
	}
}
