package rbs.cpb.usp.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("camel")
public class Camel extends RouteBuilder {

	CamelContext context = new DefaultCamelContext();

	@Autowired
    private EmailProcessor eprocessor;

	// Endpoint endpoint=endpoint()

	@Override
	public void configure() throws Exception {

		// Predicate predicate = method("com.howtodoinjava.demo.main.Camel",
		// "saySomething");
		// context.setStreamCaching(true);

		// MockEndpoint resultEndpoint = context.getEndpoint("mock:result",
		// MockEndpoint.class);
		// resultEndpoint.expectedBodiesReceived("Helloo");

        // "direct:start"
        // "timer://myTimer?period=2000"
        // "timer://runOnce?repeatCount=1&delay=5000"

		from("timer://runOnce?repeatCount=3").to("class:rbs.cpb.usp.camel.DemoService?method=emailService")
				.choice()
				.when(body().isEqualTo("indubalas@gmail.com")).process(eprocessor)
				.to("class:rbs.cpb.usp.camel.DemoService?method=requestHandlerFirst(${body})")
				.when(body().isEqualTo("rajam@gmail.com")).process(eprocessor)
				.to("class:rbs.cpb.usp.camel.DemoService?method=requestHandlerSecond(${body})")
				.otherwise()
				.log("Please check your MailId is valid");

        /*from("timer://runOnce?repeatCount=3").to("class:rbs.cpb.usp.camel.DemoService?method=emailService").transform(${body})
	choice()
                .when(body().convertTo(String.class). isEqualTo("indubalas@gmail.com")).process(eprocessor)
                .to("class:rbs.cpb.usp.camel.DemoService?method=requestHandlerFirst(${body})")
                .when(body().isEqualTo("rajam@gmail.com")).process(eprocessor)
                .to("class:rbs.cpb.usp.camel.DemoService?method=requestHandlerSecond(${body})")
                .otherwise().log("Please check your MailId is valid");*/

	}
}
