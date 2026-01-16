package com.example.gestionstock.factory;

import com.example.gestionstock.DTO.StockDTO;
import com.example.gestionstock.domain.Stock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class StockFactory {

    public static StockDTO stockToStockDTO(Stock stock) {
        if (stock != null) {
            StockDTO stockDTO = new StockDTO();
            stockDTO.setIdStock(stock.getIdStock());
            stockDTO.setQte(stock.getQte());
            stockDTO.setDatePeremption(stock.getDatePeremption());
            return stockDTO;
        } else {
            return null;
        }
    }

    public static Collection<StockDTO> stocksToStockDTOs(Collection<Stock> stocks) {
        List<StockDTO> stockDTOs = new ArrayList<>();
        for (Stock stock : stocks) {
            StockDTO stockDTO = stockToStockDTO(stock);
            stockDTOs.add(stockDTO);
        }
        return stockDTOs;
    }
}
