package com.fingolfintek.dao;

import com.fingolfintek.model.UserAuthenticationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthenticationInfoRepository extends JpaRepository<UserAuthenticationInfo, Long> {

    UserAuthenticationInfo findByUsername(String username);
}
