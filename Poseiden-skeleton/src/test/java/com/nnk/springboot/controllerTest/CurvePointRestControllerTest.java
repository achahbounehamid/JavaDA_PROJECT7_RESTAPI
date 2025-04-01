package com.nnk.springboot.controllerTest;

import com.nnk.springboot.controller.rest.CurvePointRestController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.security.SecurityConfig;
import com.nnk.springboot.service.CurvePointService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CurvePointRestController.class)
@Import(SecurityConfig.class)
public class CurvePointRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurvePointService curvePointService;

    private CurvePoint createCurve(Integer curveId, Double term, Double value) {
        CurvePoint c = new CurvePoint();
        c.setCurveId(curveId);
        c.setTerm(term);
        c.setValue(value);
        return c;
    }

    @Test
    public void testGetAllCurvePoints() throws Exception {
        List<CurvePoint> list = Arrays.asList(
                createCurve(1, 10.0, 20.0),
                createCurve(2, 15.0, 30.0)
        );
        Mockito.when(curvePointService.findAll()).thenReturn(list);

        mockMvc.perform(get("/api/curvePoint/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testAddCurvePoint() throws Exception {
        CurvePoint curve = createCurve(3, 5.0, 100.0);
        Mockito.when(curvePointService.save(Mockito.any())).thenReturn(curve);

        mockMvc.perform(post("/api/curvePoint/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"curveId\":3,\"term\":5.0,\"value\":100.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.curveId").value(3));
    }

    @Test
    public void testUpdateCurvePoint() throws Exception {
        CurvePoint updated = createCurve(1, 8.0, 99.9);
        Mockito.when(curvePointService.save(Mockito.any())).thenReturn(updated);

        mockMvc.perform(put("/api/curvePoint/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"curveId\":1,\"term\":8.0,\"value\":99.9}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.term").value(8.0));
    }

    @Test
    public void testDeleteCurvePoint() throws Exception {
        mockMvc.perform(delete("/api/curvePoint/delete/1"))
                .andExpect(status().isNoContent());
    }

}
