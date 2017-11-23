package com.cgg.pub.common.email;

import com.cgg.pub.common.email.entity.MailModel;

public interface IEmailService {

	void emailManage();
	void sendEmail(MailModel mail);
	
}
