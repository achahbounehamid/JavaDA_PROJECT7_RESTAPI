//package com.nnk.springboot;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class PasswordEncoderTest {
//
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
//
////    @Test
////    void testPasswordCheck() {
////        String rawPassword = "03032025";
////        String hashedPassword = "$2a$10$Sl8o7dKkpcKWFcdGolGJg.6b6htw3alb9/9zWEhKG/T8W0t7ES1i2";
////
////        boolean match = passwordEncoder.matches(rawPassword, hashedPassword);
////
////        System.out.println("Password match? " + match);
////        assertTrue(match); // ✅ Fails if password doesn't match
////    }
//@Test
//void generateHashForPassword() {
//    String rawPassword = "03032025";
//    String hash = passwordEncoder.encode(rawPassword);
//    System.out.println("Generated hash: " + hash);
//}
//
//}
