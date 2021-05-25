package com.springbook.biz.user;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

//VO(Value Object)
@Getter @Setter @Generated
@XmlAccessorType(XmlAccessType.FIELD)
public class UserVO {
	@XmlAnyAttribute
	private String id;
	private String pw;
	private String email;
	private String name;
	private String birth;
	private String phone_num;
	private String address;
	private String post_num;
	private Date ent_date;
	private String grade;
	
	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pw=" + pw + ", email=" + email + ", name=" 
				+ name + ", birth=" 
				+ birth + ", phone_num=" + phone_num + ", address=" + address + ", post_num=" 
				+ post_num + ", ent_date=" + ent_date + ", grade=" + grade + "]";
	}
}