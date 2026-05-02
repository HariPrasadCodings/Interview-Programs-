package com.interview.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ProtoTypeBean {

//	@Autowired
//	private SingleTonBean singleTonBean;

	public ProtoTypeBean() {
		System.out.println("ProtoTypeBean constructor instantiated...!");
	}

//	public SingleTonBean getSingleTonBean() {
//		return singleTonBean;
//	}

}
