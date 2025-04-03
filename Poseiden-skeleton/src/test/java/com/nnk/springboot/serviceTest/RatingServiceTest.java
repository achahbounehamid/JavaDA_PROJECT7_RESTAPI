package com.nnk.springboot.serviceTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class RatingServiceTest {
    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        // Arrange
        Rating rating1 = new Rating();
        Rating rating2 = new Rating();
        List<Rating> expectedRatings = Arrays.asList(rating1, rating2);
        when(ratingRepository.findAll()).thenReturn(expectedRatings);

        // Act
        List<Rating> actualRatings = ratingService.findAll();

        // Assert
        assertEquals(expectedRatings, actualRatings);
        verify(ratingRepository, times(1)).findAll();
    }

    @Test
    public void testSave() {
        // Arrange
        Rating rating = new Rating();
        when(ratingRepository.save(rating)).thenReturn(rating);

        // Act
        Rating savedRating = ratingService.save(rating);

        // Assert
        assertEquals(rating, savedRating);
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    public void testFindById() {
        // Arrange
        Integer id = 1;
        Rating expectedRating = new Rating();
        when(ratingRepository.findById(id)).thenReturn(Optional.of(expectedRating));

        // Act
        Optional<Rating> actualRating = ratingService.findById(id);

        // Assert
        assertTrue(actualRating.isPresent());
        assertEquals(expectedRating, actualRating.get());
        verify(ratingRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Integer id = 1;

        // Act
        ratingService.deleteById(id);

        // Assert
        verify(ratingRepository, times(1)).deleteById(id);
    }
}
