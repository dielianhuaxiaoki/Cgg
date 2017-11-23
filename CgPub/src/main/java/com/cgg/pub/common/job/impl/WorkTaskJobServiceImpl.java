package com.cgg.pub.common.job.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgg.pub.common.email.IEmailService;
import com.cgg.pub.common.email.entity.MailModel;
import com.cgg.pub.common.job.IWorkjobService;

@Service("workTaskService")
public class WorkTaskJobServiceImpl implements IWorkjobService {

	@Autowired
	protected IEmailService emailService;


	public void work() {
		System.out.println("start work------------");

		MailModel model = new MailModel();
		model.setToEmails("493016409@qq.com");
		model.setSubject("主题");


//		emailService.sendEmail(model);
	}
}
