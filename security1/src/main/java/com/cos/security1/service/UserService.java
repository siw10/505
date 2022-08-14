package com.cos.security1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.security1.model.RoleType;
import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private BCryptPasswordEncoder encode;
	private AuthenticationManager authenticationManager;
	
	@Autowired
	public UserService(UserRepository userRepository, BCryptPasswordEncoder encode,
			AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.encode = encode;
		this.authenticationManager = authenticationManager;
	}
	
    @Transactional // 전체가 성공시 Commit, 실패시 Rollback - springframework
    public void userCreate(User user) {
        String rawPassword = user.getPassword(); // 원본
        String encPassword = encode.encode(rawPassword); // 해쉬 비밀번호
        user.setPassword(encPassword);
        user.setRole(RoleType.ROLE_USER);
        userRepository.save(user);
    }
	
	@Transactional
	public void userModify(User user) {
		
        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정한다.
        // SELECT를 해서 User오브젝트를 DB로부터 가져오는 이유는 영속화하기 위해서이다.
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.

        User persistance = userRepository.findById(user.getId()).orElseThrow(() -> {
            return new IllegalArgumentException("회원 찾기 실패");
        });


        
        String rawPassword = user.getPassword();
        String encPassword = encode.encode(rawPassword);
        persistance.setPassword(encPassword);
        persistance.setEmail(user.getEmail());
        

        persistance.setEmail(user.getEmail());


        // 회원수정 함수 종료시 == 서비스 종료 == 트랜잭션 종료 == commit이 자동으로 됨
        // 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 변경된 것들을 update해준다.
		
		//세션 등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}
	
}
