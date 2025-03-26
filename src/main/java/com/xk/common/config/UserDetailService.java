package com.xk.common.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.xk.upms.domain.dao.repository.UpmsRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.xk.upms.application.model.UpmsRoleResponseDTO;
import com.xk.upms.domain.dao.repository.UpmsUserRepository;
import com.xk.upms.domain.model.po.UpmsUser;

@Component
public class UserDetailService  implements UserDetailsService{
	
	@Autowired
	private UpmsUserRepository upmsUserRepository;

	@Autowired
	private UpmsRoleRepository upmsRoleRepository;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UpmsUser> optionalUser = upmsUserRepository.findByIsDeletedFalseAndUsername(username);
		
		if(optionalUser.isPresent()) {
			UpmsUser user = optionalUser.get();
			String userna = user.getUsername();
			String password = user.getPassword();
			
			List<UpmsRoleResponseDTO> roles =  new ArrayList<>();
			upmsRoleRepository.findRolesByUserId(user.getId());
			
			List<GrantedAuthority> authorities = new ArrayList<>();
			coverToAuthority(roles);
			
			//回傳Spring Security 格式
			return new User(userna ,password,authorities);
			
		}else {
			throw new UsernameNotFoundException("UserName Not Found " +  username);
		}
	}
	
	
	private List<GrantedAuthority> coverToAuthority(List<UpmsRoleResponseDTO> roles){
		
		List <GrantedAuthority> authorities = new ArrayList<>();
		
		for(UpmsRoleResponseDTO roleResponse :roles) {
			authorities.add(new SimpleGrantedAuthority(roleResponse.getTitle()));
		}
		return authorities;
	}

}
