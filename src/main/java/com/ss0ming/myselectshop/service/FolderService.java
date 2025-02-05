package com.ss0ming.myselectshop.service;

import com.ss0ming.myselectshop.dto.FolderResponseDto;
import com.ss0ming.myselectshop.entity.Folder;
import com.ss0ming.myselectshop.entity.User;
import com.ss0ming.myselectshop.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FolderService {

    private final FolderRepository folderRepository;

    public void addFolders(List<String> folderNames, User user) {

        List<Folder> existFolderList = folderRepository.findAllByUserAndNameIn(user, folderNames);

        List<Folder> folderList = new ArrayList<>();

        log.info("=====================");
        log.info(folderNames.toString());

        for (String folderName : folderNames) {
            if(!isExistFolderName(folderName, existFolderList)) {
                Folder folder = new Folder(folderName, user);
                folderList.add(folder);
            } else {
                throw new IllegalArgumentException("폴더명이 중복되었습니다.");
            }
        }

        folderRepository.saveAll(folderList);
    }

    public List<FolderResponseDto> getFolders(User user) {
        List<Folder> folderList = folderRepository.findAllByUser(user);
        List<FolderResponseDto> responseDtoList = new ArrayList<>();

        for (Folder folder : folderList) {
            responseDtoList.add(new FolderResponseDto(folder));
        }

        return responseDtoList;
    }

    private boolean isExistFolderName(String folderName, List<Folder> existFolderList) {
        for (Folder existFolder : existFolderList) {
            if(folderName.equals(existFolder.getName())) {
                return true;
            }
        }
        return false;
    }
}
