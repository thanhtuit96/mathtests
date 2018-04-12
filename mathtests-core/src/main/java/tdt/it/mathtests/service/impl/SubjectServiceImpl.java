package tdt.it.mathtests.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tdt.it.mathtests.models.Group;
import tdt.it.mathtests.models.Subject;
import tdt.it.mathtests.repository.GroupRepository;
import tdt.it.mathtests.repository.SubjectRepository;

@Service
public class SubjectServiceImpl{

    @Autowired
	private SubjectRepository subjectRepository;
    
    @Autowired
    private GroupRepository groupRepository;
	
	public Subject addSubject(long idGroup, String name) {
		Group group = groupRepository.findGroupById(idGroup);
		Subject s = new Subject(name, group);
		subjectRepository.save(s);
		return s;
	}

	public Group getGroupById(long id) {
		return groupRepository.findGroupById(id);
	}
	
	public Subject updateSubject(long id, String newName) {
		Subject sb = subjectRepository.findSubjectById(id);
		sb.setName(newName);
		subjectRepository.save(sb);
		return sb;
	}
	
}
