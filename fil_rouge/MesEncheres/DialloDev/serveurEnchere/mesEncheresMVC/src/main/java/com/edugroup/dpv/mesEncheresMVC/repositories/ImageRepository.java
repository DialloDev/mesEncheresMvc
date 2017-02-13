package com.edugroup.dpv.mesEncheresMVC.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroup.dpv.mesEncheresMVC.metier.Image;

public interface ImageRepository extends PagingAndSortingRepository<Image, Integer> {
	
}
