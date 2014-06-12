package org.pharmacy.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.pharmacy.domain.Letter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/download")
@Controller
@Transactional
public class DownloadController {
	@Autowired
	private ServletContext servletContext;
	
	@Value("${uploadRelativePath}")
	private String uploadRelativePath;
	
	static Logger log = Logger.getLogger(DownloadController.class.getName());
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public void getFile(@PathVariable("id") Long id, HttpServletResponse response, HttpServletRequest request) throws IOException{

		Letter letter = Letter.findLetter(id);
		String fileName = letter.getFileName();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition","attachment;filename*=utf-8" +  "''" + java.net.URLEncoder.encode(fileName, "UTF-8") + ";");
		
		if (request.getHeader("user-agent") != null) {
			//in case of Internet Explorer
			if (request.getHeader("user-agent").indexOf("MSIE") != -1) {
				response.setHeader("Content-disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			}
		}

		String path = servletContext.getRealPath(uploadRelativePath + "/" + fileName);
		File file = new File(path);

		FileInputStream in = null;
		ServletOutputStream out = null;
		try{
			in = new FileInputStream(file);
			out = response.getOutputStream();
			log.info("try to download the file: " + path);
			IOUtils.copy(in, out);
		}catch(IOException e){
			log.error("IOException occured during file downloading", e);
		}finally{
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
	}
}