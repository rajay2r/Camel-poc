package rbs.cpb.usp.camel;

import org.json.JSONObject;

import java.io.IOException;

public class DemoService {

	String userMail = "rajam@gmail.com";

	public String saySomething() {
		System.out.println("say");
		return "Hello All";

	}

	public String emailService() {
		return userMail;
	}

	public void requestHandlerFirst(String id) {
		System.out.println("requestHandlerFirst :" + id);
	}

	public void requestHandlerSecond(String id) {
		System.out.println("requestHandlerSecond :" + id);
	}


	public String initiateEmailNotificationProcess() {
		JSONObject emailDet = new JSONObject();
		emailDet.put("itemId", "Ite-id-12389u7-jkaksdf-988");
		emailDet.put("hasAttachment", true);
		emailDet.put("mailSrc", "sender");

		return emailDet.toString();
    }

    public boolean hasAttachment(String body) {
		JSONObject emailDet = new JSONObject(body);
		return emailDet.getBoolean("hasAttachment");
	}

}
