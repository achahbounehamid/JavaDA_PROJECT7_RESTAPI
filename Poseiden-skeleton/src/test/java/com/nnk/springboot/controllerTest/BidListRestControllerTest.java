package com.nnk.springboot.controllerTest;
import com.nnk.springboot.controller.rest.BidListRestController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(BidListRestController.class)
@AutoConfigureMockMvc(addFilters = false)
class BidListRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BidListService bidListService;

    private BidList createBid(String account, String type, Double quantity) {
        BidList bid = new BidList();
        bid.setAccount(account);
        bid.setType(type);
        bid.setBidQuantity(quantity);
        return bid;
    }

    @Test
    void testGetAllBids() throws Exception {
        List<BidList> bids = Arrays.asList(
                createBid("Account1", "Type1", 100.00),
                createBid("Account2", "Type2", 200.00)
        );
        Mockito.when(bidListService.findAll()).thenReturn(bids);

        mockMvc.perform(get("/api/bidList/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(bids.size()));
    }


    @Test
    void testAddBid() throws Exception {
        BidList bid = createBid("AccountTest", "TypeTest", 300.0);

        Mockito.when(bidListService.save(Mockito.any())).thenReturn(bid);

        mockMvc.perform(post("/api/bidList/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"account\":\"AccountTest\",\"type\":\"TypeTest\",\"bidQuantity\":300.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.account").value("AccountTest"))
                .andExpect(jsonPath("$.type").value("TypeTest"))
                .andExpect(jsonPath("$.bidQuantity").value(300.0));
    }
}
