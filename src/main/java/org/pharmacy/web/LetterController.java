package org.pharmacy.web;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.pharmacy.domain.Letter;
import org.pharmacy.domain.LetterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/")
@Controller
@Transactional
public class LetterController {

	@Autowired
	private Validator validator;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private LetterValidator letterValidator;

	@Value("${uploadRelativePath}")
	private String uploadRelativePath;

	private Random random = new Random();
	static Logger log = Logger.getLogger(LetterController.class.getName());

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@ModelAttribute("letters")
	public List<Letter> listLetters() {
		List<Letter> letters = Letter.findAllLetters();        
		return letters;
	}
	@ModelAttribute("letter")
	public Letter getLetter() {
		Letter letter = new Letter();        
		return letter;
	}
	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String getLetters(Model uiModel){
		
		return "letters/list";
	}

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String addLetter(@Valid @ModelAttribute("letter") Letter letter, BindingResult result, Model uiModel, final RedirectAttributes redirectAttributes){
		letterValidator.validate(letter, result);
		
		if (result.hasErrors()){
			uiModel.addAttribute("letter", letter);
			return "letters/list";
		}
		else {
			MultipartFile uploadedFile = letter.getFile();
			
			InputStream input = null;
			OutputStream output = null;
			File storedFile = null;
			try{
				storedFile = createUniqueFileName(uploadedFile.getOriginalFilename());
				input = uploadedFile.getInputStream();
				output = new FileOutputStream(storedFile);
				IOUtils.copy(input, output);
				letter.setFileName(storedFile.getName());
				letter.persist();
			} catch (IOException e){
				FileUtils.deleteQuietly(storedFile);
				redirectAttributes.addFlashAttribute("error_message", Boolean.TRUE);
				log.error("IOException occured during file uploading", e);
			}finally {
			    IOUtils.closeQuietly(output);
			    IOUtils.closeQuietly(input);
			}
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/publish", method = RequestMethod.GET)
	public String publish(@RequestParam("id") Long id){
		Letter letter = Letter.findLetter(id);
		letter.setIsPublished(true);
		letter.merge();
		return "letters/published";
	}
	
	/**
	 * Create the file with unique name in directory
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private File createUniqueFileName(String fileName) throws IOException{
		//remove all spaces from file name
		String unspacedFileName = fileName.replaceAll("\\s+","_");
		
		String path = servletContext.getRealPath(uploadRelativePath+"/" + unspacedFileName);
		File storedFile = new File(path);
		if(storedFile.exists()){
			return createUniqueFileName(random.nextInt() + unspacedFileName);
		}
		log.info("try to create the file: " + path );
		storedFile.createNewFile();
		return storedFile;
	}
}