package tests;

import dtos.UserDTO;
import implementation.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import repositories.IUserRepository;

import java.util.Collections;


public final class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {

    @Test
    public void add() {
        UserDTO userDTO = new UserDTO(7, "UserAdd", "added user");
        get_repository().add(userDTO);

        Assert.assertEquals(get_repository().getCount(), 1);
        Assert.assertTrue(get_repository().exists(userDTO));
    }

    @Test
    public void update() {
        UserDTO userDTO = new UserDTO(1, "UserAdd", "added user");
        get_repository().add(userDTO);
        Assert.assertTrue(get_repository().exists(userDTO));

        String login = "userUp";
        String password = "Updated use";

        UserDTO groupDTO2 = new UserDTO(1, login, password);
        get_repository().update(groupDTO2);
        Assert.assertTrue(get_repository().exists(groupDTO2));

        Assert.assertEquals(get_repository().getCount(), 1);
        Assert.assertEquals(get_repository().findById(1).getLogin(), login);
        Assert.assertEquals(get_repository().findById(1).getPassword(), password);

    }

    @Test
    public void addOrUpdate() {
        String login = "login";
        String password = "password";
        UserDTO userDTO1 = new UserDTO(2, login, password);
        get_repository().addOrUpdate(userDTO1);

        Assert.assertEquals(get_repository().getCount(), 1);
        Assert.assertEquals(get_repository().findById(2).getLogin(), login);
        Assert.assertEquals(get_repository().findById(2).getPassword(), password);

        String login2 = "login2";
        String password2 = "password2";
        UserDTO userDTO2 = new UserDTO(2, login2, password2);
        get_repository().addOrUpdate(userDTO2);

        Assert.assertEquals(get_repository().getCount(), 1);
        Assert.assertEquals(get_repository().findById(2).getPassword(), password2);
        Assert.assertEquals(get_repository().findById(2).getLogin(), login2);
    }

    @Test
    public void delete() {
        String login = "login";
        String password = "password";
        UserDTO userDTO = new UserDTO(2, login, password);
        get_repository().add(userDTO);
        Assert.assertTrue(get_repository().exists(userDTO));

        System.out.println(get_repository().findById(2).getLogin());
        // Assert.assertEquals(get_repository().findById(2).getLogin(), login);
        Assert.assertEquals(get_repository().getCount(), 1);

        userDTO = new UserDTO(2, login, password);
        get_repository().delete(userDTO);
        Assert.assertEquals(get_repository().getCount(), 0);
    }

    @Test
    public void findById() {
        int id = 3;
        UserDTO userDTO = new UserDTO(id, "login", "password");
        get_repository().add(userDTO);
        Assert.assertTrue(get_repository().exists(userDTO));

        UserDTO userDTO2 = get_repository().findById(id);
        Assert.assertTrue(userDTO.equals(userDTO2));
    }

    @Test
    public void findByName() {
        UserDTO userDTO = new UserDTO(0, "login", "password");
        get_repository().add(userDTO);

        Assert.assertEquals(get_repository().findByName("login"), Collections.singletonList(userDTO));
    }

    @Override
    protected IUserRepository Create() {
        return new UserRepository();
    }
}