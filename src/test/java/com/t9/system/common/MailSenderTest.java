package com.t9.system.common;

import org.junit.Before;
import org.junit.Test;

public class MailSenderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSendout() {
		MailSender themail = new MailSender();
		themail.sendout();
	}

}
