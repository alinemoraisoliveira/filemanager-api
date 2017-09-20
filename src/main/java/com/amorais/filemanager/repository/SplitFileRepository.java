package com.amorais.filemanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.amorais.filemanager.domain.SplitFile;

public interface SplitFileRepository extends PagingAndSortingRepository<SplitFile, Long>, JpaSpecificationExecutor<SplitFile> {
	
	List<SplitFile> findByFileId(long fileId);
	
}
