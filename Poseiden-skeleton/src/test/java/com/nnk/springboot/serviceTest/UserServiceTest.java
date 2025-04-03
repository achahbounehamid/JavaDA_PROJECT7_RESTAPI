//package com.nnk.springboot.serviceTest;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import com.nnk.springboot.domain.User;
//import com.nnk.springboot.repositories.UserRepository;
//import com.nnk.springboot.service.UserService;
//public class UserServiceTest {
//    @Mock
//    private UserRepository userRepository;
//
//    @Mock
//    private BCryptPasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    private UserService userService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testSave() {
//        // Arrange
//        User user = new User();
//        user.setPassword("password");
//        String encodedPassword = "encodedPassword";
//        when(passwordEncoder.encode(user.getPassword())).thenReturn(encodedPassword);
//        when(userRepository.save(user)).thenReturn(user);
//
//        // Act
//        User savedUser = userService.save(user);
//
//        // Assert
//        assertEquals(encodedPassword, savedUser.getPassword());
//        verify(passwordEncoder, times(1)).encode(user.getPassword());
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    public void testFindAll() {
//        // Arrange
//        User user1 = new User();
//        User user2 = new User();
//        List<User> expectedUsers = Arrays.asList(user1, user2);
//        when(userRepository.findAll()).thenReturn(expectedUsers);
//
//        // Act
//        List<User> actualUsers = userService.findAll();
//
//        // Assert
//        assertEquals(expectedUsers, actualUsers);
//        verify(userRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testFindById() {
//        // Arrange
//        Integer id = 1;
//        User expectedUser = new User();
//        when(userRepository.findById(id)).thenReturn(Optional.of(expectedUser));
//
//        // Act
//        Optional<User> actualUser = userService.findById(id);
//
//        // Assert
//        assertTrue(actualUser.isPresent());
//        assertEquals(expectedUser, actualUser.get());
//        verify(userRepository, times(1)).findById(id);
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Arrange
//        Integer id = 1;
//
//        // Act
//        userService.deleteById(id);
//
//        // Assert
//        verify(userRepository, times(1)).deleteById(id);
//    }
//}
