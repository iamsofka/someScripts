package tests;

import dtos.GroupDTO;
import implementation.GroupRepository;
import org.junit.Assert;

import org.junit.Test;
import repositories.IGroupRepository;


import java.util.Collections;


public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

    @Test
    public void add() {
        GroupDTO groupDTO = new GroupDTO(7, "GroupAdd", "added group");
        get_repository().add(groupDTO);

        Assert.assertEquals(get_repository().getCount(), 1);
        Assert.assertTrue(get_repository().exists(groupDTO));
    }

    @Test
    public void update() {
        GroupDTO groupDTO1 = new GroupDTO(1, "GroupAdd", "added group");
        get_repository().add(groupDTO1);
        Assert.assertTrue(get_repository().exists(groupDTO1));

        String name = "groupUpdate";
        String description = "Updated group";

        GroupDTO groupDTO2 = new GroupDTO(1, name, description);
        get_repository().update(groupDTO2);
        Assert.assertTrue(get_repository().exists(groupDTO2));

        Assert.assertEquals(get_repository().getCount(), 1);
        Assert.assertEquals(get_repository().findById(1).getName(), name);
        Assert.assertEquals(get_repository().findById(1).getDescription(), description);
    }

    @Test
    public void addOrUpdate() {
        String name1 = "groupAdd";
        String description1 = "added group";
        GroupDTO groupDTO1 = new GroupDTO(2, name1, description1);
        get_repository().addOrUpdate(groupDTO1);

        Assert.assertEquals(get_repository().getCount(), 1);
        Assert.assertEquals(get_repository().findById(2).getName(), name1);
        Assert.assertEquals(get_repository().findById(2).getDescription(), description1);

        String name2 = "groupUpdate";
        String description2 = "Updated group";
        GroupDTO groupDTO2 = new GroupDTO(2, name2, description2);
        get_repository().addOrUpdate(groupDTO2);

        Assert.assertEquals(get_repository().getCount(), 1);
        Assert.assertEquals(get_repository().findById(2).getDescription(), description2);
        Assert.assertEquals(get_repository().findById(2).getName(), name2);
    }

    @Test
    public void delete() {
        String name = "groupAdd";
        String desc = "something";
        GroupDTO groupDTO = new GroupDTO(2, name, desc);
        get_repository().add(groupDTO);
        Assert.assertTrue(get_repository().exists(groupDTO));

        Assert.assertEquals(get_repository().findById(2).getName(), name);
        Assert.assertEquals(get_repository().getCount(), 1);

        groupDTO = new GroupDTO(2, name, desc);
        get_repository().delete(groupDTO);
        Assert.assertEquals(get_repository().getCount(), 0);
    }

    @Test
    public void findById() {
        int id = 3;
        GroupDTO groupDTO1 = new GroupDTO(id, "a name", "description");
        get_repository().add(groupDTO1);
        Assert.assertTrue(get_repository().exists(groupDTO1));

        GroupDTO groupDTO2 = get_repository().findById(id);
        Assert.assertTrue(groupDTO1.equals(groupDTO2));
    }

    @Test
    public void findByName() {

        GroupDTO groupDTO = new GroupDTO(0, "GroupUpdate", "Updated group");
        get_repository().add(groupDTO);

        Assert.assertEquals(get_repository().findByName("GroupUpdate"), Collections.singletonList(groupDTO));
    }

    @Override
    protected IGroupRepository Create() {
        return new GroupRepository();
    }
}
