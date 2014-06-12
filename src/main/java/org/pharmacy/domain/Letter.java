package org.pharmacy.domain;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.web.multipart.MultipartFile;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Letter {

	@NotNull(message="{validation.number_emty}")
	@Size(min=1,max=20, message="{validation.number_size}")
	@Column(length=20, nullable=false)
	private String number;
	
	@NotNull(message="{validation.date_empty}")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style="S-")
	@Column(nullable=false)
	private Date date;
	
	@NotNull(message="{validation.subject_empty}")
	@Size(min=1,max=250,message="{validation.subject_size}")
	@Column(length=250, nullable=false)  
	private String subject;
	
	private boolean isPublished;
	
	@Column(nullable=false)
	private String fileName;
	
	@Transient
	private MultipartFile file;
	
	@Size(max=1000, message="{validation.note_size}")
	@Column(length=1000)
	private String note;
}