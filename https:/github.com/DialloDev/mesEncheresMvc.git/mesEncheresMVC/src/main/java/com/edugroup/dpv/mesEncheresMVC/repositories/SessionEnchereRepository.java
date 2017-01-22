package com.edugroup.dpv.mesEncheresMVC.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.edugroup.dpv.mesEncheresMVC.metier.Enchere;

public interface SessionEnchereRepository extends PagingAndSortingRepository<Enchere, Integer> {

}
