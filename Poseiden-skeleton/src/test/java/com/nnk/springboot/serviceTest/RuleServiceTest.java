package com.nnk.springboot.serviceTest;

import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.repositories.RuleRepository;
import com.nnk.springboot.service.RuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RuleServiceTest {

    @InjectMocks
    private RuleService ruleService;

    @Mock
    private RuleRepository ruleRepository;

    private Rule rule;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rule = new Rule();
        rule.setId(1);
        rule.setName("Test Rule");
        rule.setDescription("Description");
        rule.setJson("{\"field\":\"value\"}");
        rule.setTemplate("template");
        rule.setSqlStr("SELECT * FROM test");
        rule.setSqlPart("WHERE id=1");
    }

    @Test
    void findAll_shouldReturnRuleList() {
        List<Rule> ruleList = Arrays.asList(rule);
        when(ruleRepository.findAll()).thenReturn(ruleList);

        List<Rule> result = ruleService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Test Rule");
        verify(ruleRepository, times(1)).findAll();
    }

    @Test
    void save_shouldReturnSavedRule() {
        when(ruleRepository.save(any(Rule.class))).thenReturn(rule);

        Rule saved = ruleService.save(rule);

        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isEqualTo("Test Rule");
        verify(ruleRepository, times(1)).save(rule);
    }

    @Test
    void findById_shouldReturnRuleIfExists() {
        when(ruleRepository.findById(1)).thenReturn(Optional.of(rule));

        Optional<Rule> found = ruleService.findById(1);

        assertThat(found).isPresent();
        assertThat(found.get().getId()).isEqualTo(1);
        verify(ruleRepository, times(1)).findById(1);
    }

    @Test
    void deleteById_shouldInvokeRepositoryDelete() {
        doNothing().when(ruleRepository).deleteById(1);

        ruleService.deleteById(1);

        verify(ruleRepository, times(1)).deleteById(1);
    }
}
