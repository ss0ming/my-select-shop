package com.ss0ming.myselectshop.repository;

import com.ss0ming.myselectshop.entity.Folder;
import com.ss0ming.myselectshop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findAllByUser(User user);
    List<Folder> findAllByUserAndNameIn(User user, List<String> folderNames);
}
