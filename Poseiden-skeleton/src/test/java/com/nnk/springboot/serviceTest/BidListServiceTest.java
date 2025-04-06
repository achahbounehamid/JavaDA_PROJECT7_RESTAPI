//package com.nnk.springboot.serviceTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import com.nnk.springboot.domain.BidList;
//import com.nnk.springboot.repositories.BidListRepository;
//import com.nnk.springboot.service.BidListService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//public class BidListServiceTest {
//
//    @Mock
//    private BidListRepository bidListRepository;
//
//    @InjectMocks
//    private BidListService bidListService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testFindAll() {
//        // Arrange
//        BidList bidList1 = new BidList();
//        BidList bidList2 = new BidList();
//        List<BidList> expectedBidLists = Arrays.asList(bidList1, bidList2);
//        when(bidListRepository.findAll()).thenReturn(expectedBidLists);
//
//        // Act
//        List<BidList> actualBidLists = bidListService.findAll();
//
//        // Assert
//        assertEquals(expectedBidLists, actualBidLists);
//        verify(bidListRepository, times(1)).findAll();
//    }
//
//    @Test
//    public void testFindById() {
//        // Arrange
//        Integer id = 1;
//        BidList expectedBidList = new BidList();
//        when(bidListRepository.findById(id)).thenReturn(Optional.of(expectedBidList));
//
//        // Act
//        Optional<BidList> actualBidList = bidListService.findById(id);
//
//        // Assert
//        assertTrue(actualBidList.isPresent());
//        assertEquals(expectedBidList, actualBidList.get());
//        verify(bidListRepository, times(1)).findById(id);
//    }
//
//    @Test
//    public void testSave() {
//        // Arrange
//        BidList bidList = new BidList();
//        when(bidListRepository.save(bidList)).thenReturn(bidList);
//
//        // Act
//        BidList savedBidList = bidListService.save(bidList);
//
//        // Assert
//        assertEquals(bidList, savedBidList);
//        verify(bidListRepository, times(1)).save(bidList);
//    }
//
//    @Test
//    public void testDeleteById() {
//        // Arrange
//        Integer id = 1;
//
//        // Act
//        bidListService.deleteById(id);
//
//        // Assert
//        verify(bidListRepository, times(1)).deleteById(id);
//    }
//}
