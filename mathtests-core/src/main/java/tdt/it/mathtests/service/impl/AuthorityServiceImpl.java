package tdt.it.mathtests.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tdt.it.mathtests.models.Authority;
import tdt.it.mathtests.repository.AuthorityRepository;
import tdt.it.mathtests.service.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
	private AuthorityRepository authorityRepository;
    
	@Override
	public Authority getAuthorityByName(String name) {
		// TODO Auto-generated method stub
		return authorityRepository.findByName(name);
	}

}
