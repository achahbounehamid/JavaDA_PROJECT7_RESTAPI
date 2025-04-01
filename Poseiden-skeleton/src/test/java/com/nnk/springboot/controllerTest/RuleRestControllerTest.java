package com.nnk.springboot.controllerTest;
import com.nnk.springboot.controller.rest.RuleRestController;
import com.nnk.springboot.domain.Rule;
import com.nnk.springboot.security.SecurityConfig;
import com.nnk.springboot.service.RuleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RuleRestController.class)
@Import(SecurityConfig.class)
class RuleRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RuleService ruleService;

    private Rule createRule(String name, String description) {
        Rule rule = new Rule();
        rule.setName(name);
        rule.setDescription(description);
        rule.setJson("someJson");
        rule.setTemplate("template");
        rule.setSqlStr("SELECT *");
        rule.setSqlPart("WHERE id=1");
        return rule;
    }

    @Test
    void testGetAllRules() throws Exception {
        List<Rule> rules = Arrays.asList(
                createRule("Rule1", "Description1"),
                createRule("Rule2", "Description2")
        );
        Mockito.when(ruleService.findAll()).thenReturn(rules);

        mockMvc.perform(get("/api/ruleName/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(rules.size()));
    }

    @Test
    void testAddRule() throws Exception {
        Rule rule = createRule("RuleTest", "DescTest");
        Mockito.when(ruleService.save(Mockito.any())).thenReturn(rule);

        mockMvc.perform(post("/api/ruleName/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"RuleTest\",\"description\":\"DescTest\",\"json\":\"someJson\",\"template\":\"template\",\"sqlStr\":\"SELECT *\",\"sqlPart\":\"WHERE id=1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("RuleTest"));
    }

    @Test
    void testUpdateRule() throws Exception {
        Rule rule = createRule("UpdatedRule", "UpdatedDesc");
        Mockito.when(ruleService.save(Mockito.any())).thenReturn(rule);

        mockMvc.perform(put("/api/ruleName/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"UpdatedRule\",\"description\":\"UpdatedDesc\",\"json\":\"someJson\",\"template\":\"template\",\"sqlStr\":\"SELECT *\",\"sqlPart\":\"WHERE id=1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("UpdatedRule"));
    }

    @Test
    void testDeleteRule() throws Exception {
        mockMvc.perform(delete("/api/ruleName/delete/1"))
                .andExpect(status().isNoContent());
    }
}
