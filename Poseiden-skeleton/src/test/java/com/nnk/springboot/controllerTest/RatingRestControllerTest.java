package com.nnk.springboot.controllerTest;

import com.nnk.springboot.controller.rest.RatingRestController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.security.SecurityConfig;
import com.nnk.springboot.service.RatingService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RatingRestController.class)
@Import(SecurityConfig.class)
public class RatingRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    private Rating createRating(String moodys, String sandp, String fitch, int order) {
        Rating rating = new Rating();
        rating.setMoodysRating(moodys);
        rating.setSandPRating(sandp);
        rating.setFitchRating(fitch);
        rating.setOrderNumber(order);
        return rating;
    }

    @Test
    void testGetAllRatings() throws Exception {
        List<Rating> ratings = Arrays.asList(
                createRating("MoodyA", "S&P A", "Fitch A", 1),
                createRating("MoodyB", "S&P B", "Fitch B", 2)
        );

        Mockito.when(ratingService.findAll()).thenReturn(ratings);

        mockMvc.perform(get("/api/rating/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(ratings.size()));
    }

    @Test
    void testAddRating() throws Exception {
        Rating rating = createRating("MoodyX", "S&P X", "Fitch X", 3);
        Mockito.when(ratingService.save(any())).thenReturn(rating);

        mockMvc.perform(post("/api/rating/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"moodysRating\":\"MoodyX\",\"sandPRating\":\"S&P X\",\"fitchRating\":\"Fitch X\",\"orderNumber\":3}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.moodysRating").value("MoodyX"))
                .andExpect(jsonPath("$.orderNumber").value(3));
    }

    @Test
    void testUpdateRating() throws Exception {
        Rating updated = createRating("MoodyY", "S&P Y", "Fitch Y", 4);
        Mockito.when(ratingService.save(any())).thenReturn(updated);

        mockMvc.perform(put("/api/rating/update/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"moodysRating\":\"MoodyY\",\"sandPRating\":\"S&P Y\",\"fitchRating\":\"Fitch Y\",\"orderNumber\":4}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.moodysRating").value("MoodyY"))
                .andExpect(jsonPath("$.orderNumber").value(4));
    }

    @Test
    void testDeleteRating() throws Exception {
        mockMvc.perform(delete("/api/rating/delete/1"))
                .andExpect(status().isNoContent());
    }
}
