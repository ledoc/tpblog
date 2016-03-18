package fr.treeptik.notify;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class NotifierAdvice {
	private Notifier notifier;

	public void setNotifier(Notifier notifier) {
		this.notifier = notifier;
	}

	@AfterReturning(
			"execution(* fr.treeptik.service.impl.*.save(..))"
			)
	public void handleNotification(JoinPoint jp) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/y H:m:s");
		dateFormat.format(now);
		StringBuilder stb = null;
		for (Object iterable : jp.getArgs()) {
		stb = new StringBuilder();
			stb.append(iterable.toString()+ " - ");
		}
		String arguments = stb.toString();
		notifier.notify(new StringMessage(now + " - " +  arguments));
	}
}