package tdt.it.mathtests.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdt.it.mathtests.models.Group;
import tdt.it.mathtests.models.User;
import tdt.it.mathtests.repository.GroupRepository;

@Service
public class GroupServiceImpl{

    @Autowired
	private GroupRepository groupRepository;
    
	public List<Group> getAll() {
		// TODO Auto-generated method stub
		return ( List<Group> ) groupRepository.findAll();
	}
	
	public Group addGroup(String name, User teacher) {
		Group group = new Group(name, teacher);
		groupRepository.save(group);
		return group;
	}

	public Group getGroupById(long id) {
		return groupRepository.findGroupById(id);
	}
	
	public Group updateGroup(long id, String newName) {
		Group group = this.getGroupById(id);
		group.setName(newName);
		groupRepository.save(group);
		return group;
	}
	
}
