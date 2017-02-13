package com.edugroup.dpv.mesEncheresMVC.repositories;

import java.io.File;
import java.util.Optional;

import com.edugroup.dpv.mesEncheresMVC.metier.Image;

public interface ImageRepositoryCustom {
	boolean saveImage( Image image, File file );
	
	boolean saveImage( int id, File file );
	
	Optional<File> loadImage(int id );
}
