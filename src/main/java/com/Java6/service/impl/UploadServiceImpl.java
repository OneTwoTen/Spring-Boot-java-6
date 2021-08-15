package com.Java6.service.impl;

import java.io.File;
import java.util.UUID;

import com.Java6.service.UploadService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class UploadServiceImpl implements UploadService {

    @Override
    public File save(MultipartFile file, String folder) {
        String folderPath = "E:\\New folder\\J602-1\\src\\main\\resources\\static\\assets\\images";
		File myUploadFolder = new File(folderPath);
		
//		kiểm tra thử mục lưu trữ file có tồn tại hay không
		if(!myUploadFolder.exists()) {
			myUploadFolder.mkdir();
		}
		
		File saveFile = null;
		String fileName = null;
		try {
//		Lưu file vào thư mục đã chọn
			fileName = folder;
			saveFile = new File(myUploadFolder, fileName);
			file.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saveFile;
    }
    
}
