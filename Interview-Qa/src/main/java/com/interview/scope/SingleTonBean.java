package com.interview.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class SingleTonBean {

//	@Autowired
//	private ProtoTypeBean protoTypeBean;
//	@Autowired
//	ApplicationContext context;
	@Autowired
	private ObjectFactory<ProtoTypeBean> objectFactory;

	public SingleTonBean() {
		System.out.println("SingleTonBean constructor instantiated...!");
	}

//	public ProtoTypeBean getProtoTypeBean() {
//		return context.getBean(ProtoTypeBean.class);
//	}

//	public ProtoTypeBean getProtoTypeBean() {
//		return objectFactory.getObject();
//	}

	public ProtoTypeBean getProtoTypeBean() {
		return getInstance();
	}

	@Lookup
	public ProtoTypeBean getInstance() {
		return null;
	}

//	public void setProtoTypeBean(ProtoTypeBean protoTypeBean) {
//		this.protoTypeBean = protoTypeBean;
//	}

}
