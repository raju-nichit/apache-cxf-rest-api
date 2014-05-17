package com.example.rest.interceptor;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class CustomOutFaultInterceptor extends
		AbstractPhaseInterceptor<Message> {
	private boolean handleMessageCalled;

	public CustomOutFaultInterceptor() {
		super(Phase.MARSHAL);
	}


	public void handleMessage(Message message) throws Fault {
		handleMessageCalled = true;
		Exception ex = message.getContent(Exception.class);
		if (ex == null) {
			throw new RuntimeException("Exception is expected");
		}
		if (!(ex instanceof Fault)) {
			throw new RuntimeException("Fault is expected");
		}
		// deal with the actual exception : fault.getCause()
		HttpServletResponse response = (HttpServletResponse) message.get(AbstractHTTPDestination.HTTP_RESPONSE);
		response.setStatus(500);
		message.getInterceptorChain().abort();

	}

	protected boolean handleMessageCalled() {
		return handleMessageCalled;
	}
}