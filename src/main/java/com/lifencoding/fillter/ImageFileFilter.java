package com.lifencoding.fillter;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;

public class ImageFileFilter implements FTPFileFilter{

	@Override
	public boolean accept(FTPFile file) {
		boolean isAccept = false;

		String fileName = file.getName();

        if (file.isFile() && (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpeg") || fileName.toLowerCase().endsWith(".png")
        		|| fileName.toLowerCase().endsWith(".bmp") || fileName.toLowerCase().endsWith(".svg")))
        {
            isAccept = true;
        }
        else
        {
            isAccept = false;
        }

        return isAccept;
	}

}
