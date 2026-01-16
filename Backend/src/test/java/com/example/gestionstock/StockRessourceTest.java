/*package com.example.gestionstock;

import com.example.gestionstock.service.StockService;
import com.example.gestionstock.web.rest.Ressource.StockRessource;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = StockRessource.class)
@Import(StockRessourceTest.MockConfig.class)  // ✅ Injecting mocks manually
class StockRessourceTest {

    @Autowired
    private MockMvc mockMvc;

    static StockService mockStockService = Mockito.mock(StockService.class);  // ✅ Pure Mockito mock

    // ✅ Manual config to replace @MockBean
    @Configuration
    static class MockConfig {
        @Bean
        public StockService stockService() {
            return mockStockService;
        }

        @Bean
        public StockRessource stockRessource() {
            // If StockRessource has other constructor args (like ArticleRepository), you need to mock/provide them too
            return new StockRessource(stockService(), null, null); // or inject real/mock repos if used in test
        }
    }

    @Test
    void testTestEndpoint() throws Exception {
        mockMvc.perform(get("/api/stock/test"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello from /api/stock/test!")));
    }
}*/
