package com.autoclaim.api.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.autoclaim.api.entity.Picture;
import com.autoclaim.api.model.request.PictureDetailsRequestModel;
import com.autoclaim.api.repository.PictureRepository;
import com.autoclaim.api.shared.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.autoclaim.api.shared.Utils;

@RestController
@RequestMapping("picture")
public class PictureController {
	
	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;
	
	@Autowired
	Utils utils;

	@Autowired
	PictureRepository pictureRepository;

	@PostMapping("/upload")
	public ArrayList<PictureDetailsRequestModel> filesUpload(@RequestParam("File") ArrayList<MultipartFile> files) throws IOException {
		FileUtils fileUtils = new FileUtils();
		ArrayList<PictureDetailsRequestModel> returnValue = new ArrayList<>();
		for(MultipartFile file: files) {
			PictureDetailsRequestModel newPicture = new PictureDetailsRequestModel();
			String filePath = FILE_DIRECTORY + utils.generateRandomString(30) + '_' + file.getOriginalFilename();
			fileUtils.saveFile(filePath, file);
			newPicture.setPath(filePath);
			returnValue.add(newPicture);
		}
		return returnValue;
	}

	@GetMapping("/download/{id}")
	public ResponseEntity<?> downloadFile(@PathVariable("id") String id) {

		Picture picture = pictureRepository.findPictureByPublicId(id);
		if(picture == null) {
			return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
		}

		FileUtils fileUtils = new FileUtils();

		Resource resource = null;

		try {
			resource = fileUtils.getFileAsResource(picture.getPath());
		} catch (IOException e) {
			return ResponseEntity.internalServerError().build();
		}

		if (resource == null) {
			return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
		}

		String contentType = "application/octet-stream";
		String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
				.body(resource);
	}
}
