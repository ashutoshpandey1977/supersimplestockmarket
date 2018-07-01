package test.stockmarket.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import test.stockmarket.model.Stock;
import test.stockmarket.model.StockType;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(Parameterized.class)
public class CalculatePERatioTest {
    private Stock stock;
    private BigDecimal price;
    private BigDecimal expectedResult;
    private CalculationServiceImpl calculationService;

    @Before
    public void setUp() {
        calculationService = new CalculationServiceImpl();
    }

    @Parameterized.Parameters(name = "{index}: test(Stock{0} Price{1}) = {3}")
    public static Collection stockPrice() {
        return Arrays.asList(new Object[][]{
                {new Stock("TEA", StockType.COMMON, BigDecimal.ZERO, null, BigDecimal.valueOf(100)), BigDecimal.valueOf(90), null},
                {new Stock("POP", StockType.COMMON, BigDecimal.valueOf(8), null, BigDecimal.valueOf(100)), BigDecimal.valueOf(90), BigDecimal.valueOf(11.25).setScale(2, RoundingMode.HALF_UP)},
                {new Stock("ALE", StockType.COMMON, BigDecimal.valueOf(23), null, BigDecimal.valueOf(60)), BigDecimal.valueOf(90), BigDecimal.valueOf(3.91).setScale(2, RoundingMode.HALF_UP)},
                {new Stock("GIN", StockType.PREFERRED, BigDecimal.valueOf(8), BigDecimal.valueOf(2), BigDecimal.valueOf(100)), BigDecimal.valueOf(90), BigDecimal.valueOf(11.25).setScale(2, RoundingMode.HALF_UP)},
                {new Stock("JOE", StockType.COMMON, BigDecimal.valueOf(13), BigDecimal.valueOf(2), BigDecimal.valueOf(100)), BigDecimal.valueOf(90), BigDecimal.valueOf(6.92).setScale(2, RoundingMode.HALF_UP)}
        });
    }

    public CalculatePERatioTest(Stock stock, BigDecimal price, BigDecimal expectedResult) {
        this.stock = stock;
        this.price = price;
        this.expectedResult = expectedResult;
    }

    @Test
    public void whenGivenStockAndPriceCalculatePERatio() {
        BigDecimal result = calculationService.calculatePERatio(stock, price);
        if (expectedResult == null) assertNull(result);
        else assertEquals(expectedResult, result.setScale(expectedResult.scale(), RoundingMode.HALF_UP));

    }


}