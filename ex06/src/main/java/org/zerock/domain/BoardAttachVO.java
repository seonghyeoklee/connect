package org.zerock.domain;

import java.util.List;

import lombok.Data;

@Data
public class BoardAttachVO {

	private String uuid;
	private String uploadPath;
	private String fileName;
	private boolean fileType;

	private Long bno;

	private List<BoardAttachVO> attachList;
}
