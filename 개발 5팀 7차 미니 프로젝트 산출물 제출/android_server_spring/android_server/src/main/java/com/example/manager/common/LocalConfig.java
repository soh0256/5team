package com.example.manager.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Component
public class LocalConfig {
    /**
     * application.yml 에서 설정한 upload path // 회원가입시 프로필 사진
     */
    @Value("${serverInfo.upload_path}")
    private String uploadFilePath;

    /**
     * application.yml 에서 설정한 base url // 회원가입시 프로필 사진
     */
    @Value("${serverInfo.base_url}")
    private String baseUrl;
    
    
    // @Value("a") : 변수에 a라는 값을 주입
    // @Value("${..}) : application.yml의 값을 변수에 주입
    // https://bcp0109.tistory.com/227
    
    @Value("${boardInfo.image_path}")
    private String BoardPath;
    
    @Value("${boardInfo.image_url}")
    private String BoardUrl;

}
