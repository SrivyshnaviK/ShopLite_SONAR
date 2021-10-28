package com;

import com.zemoso.entity.User;
import com.zemoso.repository.UserRepository;
import com.zemoso.service.UserService;
import com.zemoso.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {com.zemoso.ShopLiteApplication.class})
class ShopLiteApplicationTests {
	@Test
	void contextLoads() {}
	private UserService userService;
	@Mock
	private UserRepository mockRepository;
	@BeforeEach
	void initUseCase(){
		userService=new UserServiceImpl(mockRepository);
	}
	@Test
	public void findByIdMocked_WhenInvoked_ReturnsMockUser(){
		User user=new User("Sai","Krishna","krishna@gmail.com","krishnasai");
		//System.out.println(user);
		Mockito.when(mockRepository.findById(1)).thenReturn(java.util.Optional.of(user));
		//System.out.println("------>"+mockRepository.findById(1));
		Assert.assertEquals(user,userService.findById(1));
		Mockito.verify(mockRepository).findById(1);
	}
	@Test
	public void findPasswordByIdMocked_WhenInvoked_ReturnsMockPassword(){
		Mockito.when(mockRepository.findPasswordById(1)).thenReturn("dummyPassword");
		Assert.assertEquals("dummyPassword",userService.findPasswordById(1));
		Mockito.verify(mockRepository).findPasswordById(1);
	}
	@Test
	public void saveUser_WhenInvoked_Success(){
		User user=new User("Sai133344","Krishna","krishna@gmail.com","krishnasai");
		Mockito.when(mockRepository.save(user)).thenReturn(user);
		User theUser=userService.save(user);
		assertThat(theUser.getFirstName()).isNotNull();
	}
}
