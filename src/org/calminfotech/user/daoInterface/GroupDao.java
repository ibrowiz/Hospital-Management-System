package org.calminfotech.user.daoInterface;

import java.util.List;

import org.calminfotech.user.models.Group;
import org.calminfotech.utils.models.Organisation;

public interface GroupDao {

	void save(Group group);

	void update(Group group);

	void delete(Group group);

	Group getGroupById(int id);

	List<Group> fetchAll();

	public List<Group> fetchAllByOrganisation(Organisation organisation);
	
	public Group getAdminGroupByOrganisation(Organisation organisation);

}
