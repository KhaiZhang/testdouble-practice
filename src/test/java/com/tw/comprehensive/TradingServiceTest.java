package com.tw.comprehensive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

class TradingServiceTest {

    @Test
    void should_log_new_trade_when_create_trade_given_new_trade() {
        //given
        TradeRepository tradeRepository = new TradeRepository();
        AuditService auditService = new AuditService();
        AuditService spyAuditService = Mockito.spy(auditService);
        TradingService tradingService = new TradingService(tradeRepository, spyAuditService);
        Trade trade = new Trade("test", "test");

        //when
        tradingService.createTrade(trade);

        //then
        Mockito.verify(spyAuditService).logNewTrade(trade);
    }

    @Test
    void should_return_same_trade_with_trade_find_by_trade_repository_when_trading_service_find_trade() {
        //given
        TradeRepository tradeRepository = mock(TradeRepository.class);
        AuditService auditService = new AuditService();
        TradingService tradingService = new TradingService(tradeRepository, auditService);
        Trade trade = new Trade("test", "test");
        Mockito.when(tradeRepository.findById(1L)).thenReturn(trade);

        //when
        Trade findTrade = tradingService.findTrade(1L);

        //then
        Assertions.assertEquals(trade, findTrade);
    }
}