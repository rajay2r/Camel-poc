package rbs.cpb.usp.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class EmailProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		String payload = exchange.getIn().getBody(String.class);
		String id = null;
		if (payload.equalsIgnoreCase("indubalas@gmail.com"))
			id = "balas";
		else if (payload.equalsIgnoreCase("rajam@gmail.com"))
			id = "mraj";
		else
			id = "This is not a valid mail";

		exchange.getIn().setBody(id);

	}

}
