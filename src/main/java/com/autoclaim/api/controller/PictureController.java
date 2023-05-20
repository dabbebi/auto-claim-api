package com.autoclaim.api.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.autoclaim.api.shared.Utils;

@RestController
@RequestMapping("picture")
public class PictureController {
	
	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;
	
	@Autowired
	Utils utils;
	
	@PostMapping("/uploadPicture")
	public ResponseEntity<Object> fileUpload(@RequestParam("File") MultipartFile file) throws IOException{
		String filePath = FILE_DIRECTORY + utils.generateRandomString(30) + '_' + file.getOriginalFilename();
		File myFile = new File(filePath);
		myFile.createNewFile();
		FileOutputStream fos =new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();
		return new ResponseEntity<Object>("The File Uploaded Successfully", HttpStatus.OK);
	}
}
