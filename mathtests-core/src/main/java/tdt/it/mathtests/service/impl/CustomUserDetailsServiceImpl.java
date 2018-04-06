package tdt.it.mathtests.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tdt.it.mathtests.models.User;
import tdt.it.mathtests.repository.UserRepository;

/**
 * Created by fan.jin on 2016-10-31.
 */

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    protected final Log LOGGER = LogFactory.getLog(getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }

    public UserDetails loadUserById(long id) {
    	User user = userRepository.findById(id);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with ID '%s'.", id));
        } else {
            return user;
        }
    }
    
    public List<User> getAll(){
    	return (List<User>) userRepository.findAll();
    }
    
    public void changeLastAccess() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        LOGGER.debug("Changing Last Acess for user '"+ username + "'");

        User user = (User) loadUserByUsername(username);

        user.setLastaccess(new Timestamp(System.currentTimeMillis()));
        
        userRepository.save(user);
    }
    
    public void changePassword(String oldPassword, String newPassword) {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        if (authenticationManager != null) {
            LOGGER.debug("Re-authenticating user '"+ username + "' for password change request.");

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
            LOGGER.debug("No authentication manager set. can't change Password!");

            return;
        }

        LOGGER.debug("Changing password for user '"+ username + "'");

        User user = (User) loadUserByUsername(username);

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

    }
    
    public void registryUser(User u) throws SecurityException{
    	u.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
    	u.setEnabled(true);
    	u.setPassword(passwordEncoder.encode(u.getPassword()));
    	if(u.getAuthorities().stream().anyMatch(o -> o.getAuthority().contains("ADMIN"))) {
    		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        	User user = (User) loadUserByUsername(currentUser.getName());
        	if(user.getAuthorities().stream().anyMatch(o -> o.getAuthority().contains("SUPERADMIN")))
            	userRepository.save(u);
        	else 
        		throw new SecurityException("Permission denied" + currentUser.getName());
    	} else {
    		userRepository.save(u);
    	}
    }

    public void disableUser(User u) {
    	u.setEnabled(false);
    	userRepository.save(u);
    }

    public void enableUser(User u) {
    	u.setEnabled(true);
    	userRepository.save(u);
    }

    
}
