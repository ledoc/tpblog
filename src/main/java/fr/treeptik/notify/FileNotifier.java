package fr.treeptik.notify;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


// Simple implémentation pour afficher le message dans la console
public class FileNotifier implements Notifier {
	public void notify(Message message) {
		File file = new File("log.txt");
				try {
					FileWriter fileWriter = new FileWriter(file, false);
					BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
					bufferedWriter.write(message.toString());
					bufferedWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		
		System.out.println(message.toString());
	}
}