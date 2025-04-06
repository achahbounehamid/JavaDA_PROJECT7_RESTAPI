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
//
//import com.nnk.springboot.domain.Rule;
//import com.nnk.springboot.repositories.RuleRepository;
//import com.nnk.springboot.service.RuleService;
//public class RuleServiceTest {
//    @Mock
//    private RuleRepository ruleRepository;
//
//    @InjectMocks
//    private RuleService ruleService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testFindAll() {
//        // Arrange
//        Rule rule1 = new Rule();
//        Rule rule2 = new Rule();
//        List<Rule> expectedRules = Arrays.asList(rule1, rule2);
//        when(ruleRepository.findAll()).thenReturn(expectedRules);
//
//        // Act
//        List<Rule> actualRules = ruleService.findAll();
//
//        // Assert
//        assertEquals(expectedRules, actualRules);
//        verify(ruleRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testSave() {
//        // Arrange
//        Rule rule = new Rule();
//        when(ruleRepository.save(rule)).thenReturn(rule);
//
//        // Act
//        Rule savedRule = ruleService.save(rule);
//
//        // Assert
//        assertEquals(rule, savedRule);
//        verify(ruleRepository, times(1)).save(rule);
//    }
//
//    @Test
//    public void testFindById() {
//        // Arrange
//        Integer id = 1;
//        Rule expectedRule = new Rule();
//        when(ruleRepository.findById(id)).thenReturn(Optional.of(expectedRule));
//
//        // Act
//        Optional<Rule> actualRule = ruleService.findById(id);
//
//        // Assert
//        assertTrue(actualRule.isPresent());
//        assertEquals(expectedRule, actualRule.get());
//        verify(ruleRepository, times(1)).findById(id);
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Arrange
//        Integer id = 1;
//
//        // Act
//        ruleService.deleteById(id);
//
//        // Assert
//        verify(ruleRepository, times(1)).deleteById(id);
//    }
//}
