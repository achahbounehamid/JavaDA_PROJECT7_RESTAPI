package com.nnk.springboot.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurvePointService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
public class CurvePointServiceTest {
    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
    private CurvePointService curvePointService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        // Arrange
        CurvePoint curvePoint = new CurvePoint();
        when(curvePointRepository.save(curvePoint)).thenReturn(curvePoint);

        // Act
        CurvePoint savedCurvePoint = curvePointService.save(curvePoint);

        // Assert
        assertEquals(curvePoint, savedCurvePoint);
        verify(curvePointRepository, times(1)).save(curvePoint);
    }

    @Test
    public void testFindAll() {
        // Arrange
        CurvePoint curvePoint1 = new CurvePoint();
        CurvePoint curvePoint2 = new CurvePoint();
        List<CurvePoint> expectedCurvePoints = Arrays.asList(curvePoint1, curvePoint2);
        when(curvePointRepository.findAll()).thenReturn(expectedCurvePoints);

        // Act
        List<CurvePoint> actualCurvePoints = curvePointService.findAll();

        // Assert
        assertEquals(expectedCurvePoints, actualCurvePoints);
        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        // Arrange
        Integer id = 1;
        CurvePoint expectedCurvePoint = new CurvePoint();
        when(curvePointRepository.findById(id)).thenReturn(Optional.of(expectedCurvePoint));

        // Act
        Optional<CurvePoint> actualCurvePoint = curvePointService.findById(id);

        // Assert
        assertTrue(actualCurvePoint.isPresent());
        assertEquals(expectedCurvePoint, actualCurvePoint.get());
        verify(curvePointRepository, times(1)).findById(id);
    }

    @Test
    public void testDeleteById() {
        // Arrange
        Integer id = 1;

        // Act
        curvePointService.deleteById(id);

        // Assert
        verify(curvePointRepository, times(1)).deleteById(id);
    }
}
